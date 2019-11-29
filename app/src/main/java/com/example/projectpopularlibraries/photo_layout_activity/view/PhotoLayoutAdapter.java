package com.example.projectpopularlibraries.photo_layout_activity.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectpopularlibraries.R;
import com.example.projectpopularlibraries.model.photoupload.PicassoLoader;
import com.example.projectpopularlibraries.photo_layout_activity.presenter.IRecyclerPhotoPresenter;
import com.example.projectpopularlibraries.photo_layout_activity.presenter.PhotoLayoutPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotoLayoutAdapter extends RecyclerView.Adapter<PhotoLayoutAdapter.MyPhotoRecyclerViewHolder> {

    private static final String TAG = "PhotoLayoutAdapter";

    private IRecyclerPhotoPresenter iMyRecyclerPhotoPresenter;
    private PicassoLoader picassoLoader;

    public PhotoLayoutAdapter(Context context, IRecyclerPhotoPresenter iRecyclerPhotoPresenter) {
        this.iMyRecyclerPhotoPresenter = iRecyclerPhotoPresenter;
        picassoLoader = new PicassoLoader(context);
    }

    @NonNull
    @Override
    public MyPhotoRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_item, parent, false);
        return new MyPhotoRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPhotoRecyclerViewHolder holder, int position) {
        holder.position = position;
        iMyRecyclerPhotoPresenter.bindView(holder);

    }

    @Override
    public int getItemCount() {
        return iMyRecyclerPhotoPresenter.getItemCount();
    }


    public class MyPhotoRecyclerViewHolder extends RecyclerView.ViewHolder implements IRecyclerPhotoViewHolder {

        private int position = 0;
        private String url;

        @BindView(R.id.card_view)
        CardView cardButton;

        @BindView(R.id.imageView)
        ImageView imageView;


        public MyPhotoRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setImage(String url) {
            picassoLoader.loadImage(url, imageView);
            this.url = url;
        }

        @Override
        public int getPos() {
            return position;
        }

        @OnClick(R.id.card_view)
        public void onCardClick() {
            iMyRecyclerPhotoPresenter.setCountIncrementToModel();
            int count = iMyRecyclerPhotoPresenter.getCountFromModel();
            Log.d(TAG, "count press: " + count);
            iMyRecyclerPhotoPresenter.changeActivity(url);
        }
    }
}
