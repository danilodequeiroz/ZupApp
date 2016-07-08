package com.brutcode.seedapp;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Danilo on 24/06/2016.
 */
public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {
    private static final String TAG = ContentAdapter.class.getSimpleName();
    // Set numbers of List in RecyclerView.
    private List<Content> mContList;
    private Context mContext;
    private OnItemClickListener mListener;



    @Inject
    public ContentAdapter(Context context, OnItemClickListener listenet) {
        mContext = context;
        mContList = new ArrayList<>();
        mListener = listenet;
    }

    public void insertItens(List<Content> contents){
        mContList.addAll(contents);
    }


    public interface OnItemClickListener {
        void onItemClick(Content item);
    }

    public void insertOneItem(Content content){
        mContList.add(content);
        Collections.sort(mContList);
        notifyDataSetChanged();
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position) {
        Content content = mContList.get(position);
        File defaultPicDir = new File(mContext.getFilesDir(), "posters");
        File localStoragePicture = new File(defaultPicDir, mContList.get(position).getImdbID());
        boolean exists = localStoragePicture.exists();
        if(exists) {
            Log.i(TAG,"Using local file... for "+content.toString());
            holder.poster.setController(Fresco.newDraweeControllerBuilder()
                    .setUri(Uri.fromFile(localStoragePicture)).build());
        }else{
            Log.i(TAG,"Using network file... for "+content.toString());
            holder.poster.setController(Fresco.newDraweeControllerBuilder()
                    .setUri(Uri.parse(mContList.get(position).getPoster())).build());
        }
        holder.mainCard.setTag(mContList.get(position));
        holder.title.setText(mContList.get(position).getTitle());
        holder.director.setText(mContList.get(position).getDirector());
        holder.genre_year.setText(mContList.get(position).getGenre() + ", " + mContList.get(position).getYear());
    }

    @Override
    public int getItemCount() {
        return mContList.size();
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_main_item_father)
        public CardView mainCard;

        @BindView(R.id.drawee_main_item_poster)
        public DraweeView poster;

        @BindView(R.id.txtview_main_item_title)
        public TextView title;

        @BindView(R.id.txtview_main_item_director)
        public TextView director;

        @BindView(R.id.txtview_main_item_genre_year)
        public TextView genre_year;

        @OnClick(R.id.card_main_item_father)
        public void itemClick(CardView view){
            Content content = ((Content)view.getTag());
            mListener.onItemClick(content);
        }

        public ContentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}

