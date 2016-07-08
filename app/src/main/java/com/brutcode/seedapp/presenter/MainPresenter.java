package com.brutcode.seedapp.presenter;

import android.net.Uri;
import android.util.Log;

import com.brutcode.seedapp.Content;
import com.brutcode.seedapp.MyApp;
import com.brutcode.seedapp.MySnappyDb;
import com.brutcode.seedapp.R;
import com.brutcode.seedapp.di.module.OmdbApiModule;
import com.brutcode.seedapp.view.MainView;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Danilo on 06/07/2016.
 */
public class MainPresenter implements Presenter<MainView> {

    private static final String TAG = MainPresenter.class.getSimpleName();

    @Inject
    OmdbApiModule.OmdbApiInterface mOmdbApiInterface;

    private MainView mView;

    private Subscription mGetContentSubscription;
    private Content mContent;
    private CloseableReference<CloseableImage> mRef;

    @Override
    public void setView(MainView view) {
        ((MyApp) view.getApp()).getOmdbComponent().inject(this);
        mView = view;
    }

    public void getMovie(String title, String year) {
        mView.showProgress();
        mGetContentSubscription = mOmdbApiInterface
                .getContent(title, year)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<Content>>() {
                    @Override
                    public void onCompleted() {
                        if (mContent == null) {
                            mView.longToast(R.string.service_failure);
                        } else if (mContent.response.equals("True")) {
                            MySnappyDb.getInstance(mView.getViewContext()).insertContent(mContent);
                            getMoviePoster(mContent);
                        } else {
                            mView.longToast(R.string.str_toast_no_movie);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mContent = null;
                        mView.hideProgress();
                        Log.i(TAG,"service_failure");
                        mView.longToast(R.string.service_failure);
                    }

                    @Override
                    public void onNext(Response<Content> contentResponse) {
                        mContent = contentResponse.body();
                    }
                });
    }

    private void getMoviePoster(final Content content) {
        if (content.getPoster() == null || !content.getPoster().contains("http://"))
            return ;
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(content.getPoster()))
                .build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        final CacheKey cacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(request, null);
        DataSource<CloseableReference<CloseableImage>>
                dataSource = imagePipeline.fetchDecodedImage(request, null);
        DataSubscriber<CloseableReference<CloseableImage>> dataSubscriber =
                new BaseDataSubscriber<CloseableReference<CloseableImage>>() {
                    @Override
                    protected void onNewResultImpl(
                            DataSource<CloseableReference<CloseableImage>> dataSource) {
                        if (!dataSource.isFinished()) {
                            // if we are not interested in the intermediate images,
                            // we can just return here.
                            return;
                        }
                        // keep the closeable reference
                        mRef = dataSource.getResult();
                        // do something with the result
                        storeOnInternalStorage(content.getImdbID(), cacheKey);
                        mView.runOnUi(new Runnable() {
                            @Override
                            public void run() {
                                mView.presentContent(content);
                            }
                        });
                    }

                    @Override
                    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                        Throwable t = dataSource.getFailureCause();
                        Log.e(TAG, t.getLocalizedMessage());
                    }
                };
        dataSource.subscribe(dataSubscriber, CallerThreadExecutor.getInstance());
    }



    private Boolean storeOnInternalStorage(String filename, CacheKey cacheKey) {
        Boolean returnValue = new Boolean(false);
        FileOutputStream outputStream = null;
        FileInputStream inputStream = null;
        if (ImagePipelineFactory.getInstance().getMainFileCache().hasKey(cacheKey)) {
            try {
                BinaryResource resource = ImagePipelineFactory.getInstance().getMainFileCache().getResource(cacheKey);
                File cachedFile = ((FileBinaryResource) resource).getFile();
                inputStream = new FileInputStream(cachedFile);
                File dir = new File(mView.getViewContext().getFilesDir(), "posters");
                if (!dir.exists())
                    dir.mkdirs();
                File toIntStoreFile = new File(dir, filename);
                outputStream = new FileOutputStream(toIntStoreFile);
                int byteStream;
                while ((byteStream = inputStream.read()) != -1)
                    outputStream.write(byteStream);
                returnValue = true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null)
                        inputStream.close();
                    if (outputStream != null)
                        outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    returnValue = false;
                }
            }
        }
        CloseableReference.closeSafely(mRef);
        mRef = null;
        return returnValue;
    }

    public void cancelGetMovieRequest() {
        if (mGetContentSubscription != null)
            mGetContentSubscription.unsubscribe();
    }
        /** you can do it by DataSubscriber to retrieve the Bitmap or raw data
         * We only need the file by now
         *
        DataSubscriber<CloseableReference<CloseableImage>> dataSubscriber =
                new BaseDataSubscriber<CloseableReference<CloseableImage>>() {
                    @Override
                    protected void onNewResultImpl(
                            DataSource<CloseableReference<CloseableImage>> dataSource) {
                        if (!dataSource.isFinished()) {
                            // if we are not interested in the intermediate images,
                            // we can just return here.
                            return;
                        }
                        // keep the closeable reference
                        mRef = dataSource.getResult();
                        // do something with the result
                        CloseableImage result = mRef.get();
                        storeOnInternalStorage(filename, cacheKey);
                    }

                    @Override
                    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                        Throwable t = dataSource.getFailureCause();
                        Log.e(TAG, t.getLocalizedMessage());
                        // handle failure
                    }
                };

        dataSource.subscribe(dataSubscriber, CallerThreadExecutor.getInstance());
         /... later you must close the reference../
         CloseableReference.closeSafely(mRef);
         mRef = null; // Avoiding memory leaks (=
        **/


}
