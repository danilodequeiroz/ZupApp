package com.brutcode.seedapp.ui.result;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.brutcode.seedapp.Constants;
import com.brutcode.seedapp.model.Content;
import com.brutcode.seedapp.R;
import com.brutcode.seedapp.databinding.ActivityResultBinding;
import com.brutcode.seedapp.ui.BaseActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;

import net.qiujuer.genius.blur.StackBlur;

import org.parceler.Parcels;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends BaseActivity {

    private static final String TAG = ResultActivity.class.getSimpleName();
    @BindView(R.id.drawee_result_poster)
    public SimpleDraweeView mPosterDraw;

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    @BindView(R.id.drawee_result_bg)
    public SimpleDraweeView mBGDraw;

    @BindView(R.id.textview_result_main_text)
    public TextView mMainTxt;

    public Content mContent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityResultBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            mContent = Parcels.unwrap(getIntent().getExtras().getParcelable(Constants.CONTENT_BUNDLE));
        } else {
            mContent = Parcels.unwrap(
                    savedInstanceState.getParcelable(Constants.CONTENT_STATE_BUNDLE));
        }
        binding.setContent(mContent);
        if(isThereLocalFile(mContent)){
            Log.i(TAG,"Using local file... for "+mContent.toString());
            setUpPosterImg(getLocalUri(mContent.imdbID));
        }else{
            Log.i(TAG,"Using network file... for "+mContent.toString());
            setUpPosterImg(Uri.parse(mContent.getPoster()));
        }
        super.onCreate(savedInstanceState);
    }

    private boolean isThereLocalFile(Content content) {
        File defaultPicDir = new File(this.getFilesDir(), "posters");
        File localStoragePicture = new File(defaultPicDir, content.getImdbID());
        boolean exists = localStoragePicture.exists();
        return exists;
    }

    private Uri getLocalUri(String imdb){
        File defaultPicDir = new File(this.getFilesDir(), "posters");
        File localStoragePicture = new File(defaultPicDir, imdb);
        return Uri.fromFile(localStoragePicture);
    }

    private void setUpPosterImg(Uri uri) {
        Postprocessor redMeshPostprocessor = new BasePostprocessor() {
            @Override
            public String getName() {
                return "whiteDotPosterBlur";
            }

            @Override
            public void process(Bitmap bitmap) {
                for (int x = 0; x < bitmap.getWidth(); x+=2) {
                    for (int y = 0; y < bitmap.getHeight(); y+=2) {
                        bitmap.setPixel(x, y, Color.WHITE);
                    }
                }
                StackBlur.blurNatively(bitmap, 5, true);
            }
        };
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .build();
        ImageRequest requestBG = ImageRequestBuilder.newBuilderWithSource(uri)
                .setPostprocessor(redMeshPostprocessor)
                .build();

        mBGDraw.setController(
                Fresco.newDraweeControllerBuilder()
                        .setImageRequest(requestBG)
                        .setOldController(mBGDraw.getController())
                        .build());
        mPosterDraw.setController(
                Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(mPosterDraw.getController())
                        .build());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.CONTENT_STATE_BUNDLE, Parcels.wrap(mContent));
    }

}
