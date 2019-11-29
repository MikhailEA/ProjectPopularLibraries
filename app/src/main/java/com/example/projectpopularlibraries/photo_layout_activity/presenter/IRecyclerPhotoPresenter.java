package com.example.projectpopularlibraries.photo_layout_activity.presenter;

import com.example.projectpopularlibraries.photo_layout_activity.view.IRecyclerPhotoViewHolder;

public interface IRecyclerPhotoPresenter {
    void bindView(IRecyclerPhotoViewHolder iViewHolder);
    int getItemCount();
    void setCountIncrementToModel();
    int getCountFromModel();
    void changeActivity(String url);

    void onFirstViewAttach();
}