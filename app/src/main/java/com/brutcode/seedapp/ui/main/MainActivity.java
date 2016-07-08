package com.brutcode.seedapp.ui.main;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.brutcode.seedapp.Constants;
import com.brutcode.seedapp.Content;
import com.brutcode.seedapp.ContentAdapter;
import com.brutcode.seedapp.MySnappyDb;
import com.brutcode.seedapp.R;
import com.brutcode.seedapp.presenter.MainPresenter;
import com.brutcode.seedapp.ui.BaseActivity;
import com.brutcode.seedapp.ui.result.ResultActivity;
import com.brutcode.seedapp.view.MainView;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView, ContentAdapter.OnItemClickListener{


    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.recycler_act_main_list)
    public RecyclerView mRecycler;

    @Inject
    SharedPreferences mSharedPreferences;

    private MainPresenter mPresenter;
    private MaterialDialog mSearchDialog;
    private MaterialDialog mProgressDialog;

    @OnClick(R.id.fab_search_dlg)
    void fabSearchClick(){
        mSearchDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter();
        mPresenter.setView(this);
        setUpDialogs();
        Log.i(TAG,"onCreate");
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(llm);
        ContentAdapter content = new ContentAdapter(this,this);
        mRecycler.setAdapter(content);
        content.insertItens(MySnappyDb.getInstance(this).getAllContents());
        content.notifyDataSetChanged();
        super.onCreate(savedInstanceState);
    }


    private void setUpDialogs() {
        MaterialDialog.SingleButtonCallback positiveCallback = new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                EditText inputName = (EditText) dialog.findViewById(R.id.edtx_dialog_input_name);
                EditText inputYear = (EditText) dialog.findViewById(R.id.edtx_dialog_input_year);
                mPresenter.getMovie(inputName.getText().toString(), inputYear.getText().toString());
            }
        };
        DialogInterface.OnCancelListener progCancelListener = new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mPresenter.cancelGetMovieRequest();
            }
        };
        mSearchDialog = new MaterialDialog.Builder(this)
                .onPositive(positiveCallback)
                .customView(R.layout.view_search_film, true)
                .title(R.string.str_dlg_actv_main_query_message)
                .positiveText(R.string.str_dlg_actv_main_query_positive)
                .negativeText(R.string.str_dlg_actv_main_query_negative)
                .autoDismiss(true)
                .build();
        mProgressDialog = new MaterialDialog.Builder(this)
                .progress(true, 0)
                .content(R.string.loading)
                .autoDismiss(true)
                .cancelListener(progCancelListener)
                .build();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(Constants.PROGRESS_DIALOG_STATE_BUNDLE, mProgressDialog.isShowing());
        outState.putBoolean(Constants.SEARCH_DIALOG_STATE_BUNDLE, mSearchDialog.isShowing());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState.getBoolean(Constants.SEARCH_DIALOG_STATE_BUNDLE)){
            mSearchDialog.show();
        }
    }

    @Override
    public void runOnUi(Runnable runnable) {
        runOnUiThread(runnable);
    }

    @Override
    public void presentContent(Content content) {
        MySnappyDb.getInstance(this).insertContent(content);
        ((ContentAdapter)mRecycler.getAdapter()).insertOneItem(content);
        hideProgress();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public Application getApp() {
        return getApplication();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public Resources getAppResources() {
        return getResources();
    }

    @Override
    public void showProgress() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.cancel();
    }

    @Override
    public void longToast(int stringResource) {
        Toast.makeText(MainActivity.this, stringResource, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void shortToast(int stringResource) {
        Toast.makeText(MainActivity.this, stringResource, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Content item) {
        String someString = item.toString();
        Log.i(TAG,someString);
        Parcelable wrappedParcelable = Parcels.wrap(item);
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(Constants.CONTENT_BUNDLE,wrappedParcelable);
        startActivity(intent);
    }
}
