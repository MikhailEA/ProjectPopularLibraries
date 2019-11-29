package com.example.projectpopularlibraries.photo_layout_activity.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectpopularlibraries.R;

import com.example.projectpopularlibraries.action_processing_activity.view.ActionProcessingActivity;
import com.example.projectpopularlibraries.photo_layout_activity.presenter.PhotoLayoutPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class PhotoLayoutActivity extends MvpAppCompatActivity implements IPhotoLayoutView {

    private static final String TAG = "PhotoLayoutActivity";

    private PhotoLayoutAdapter photoLayoutAdapter;

    @BindView(R.id.activity_recycler)
    RecyclerView recyclerView;

    @InjectPresenter
    PhotoLayoutPresenter mainPhotoPresenter;

    @ProvidePresenter
    public PhotoLayoutPresenter providePresenter() {
        return new PhotoLayoutPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_layout);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        photoLayoutAdapter = new PhotoLayoutAdapter(this, mainPhotoPresenter.getRecyclerPresenter());
        recyclerView.setAdapter(photoLayoutAdapter);
    }

    @Override
    public void openCardActivity(String url) {
        Intent intent = new Intent(PhotoLayoutActivity.this, ActionProcessingActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    @Override
    public void updateRecyclerView() {
        Log.d(TAG, "updateRecyclerView: ");
        photoLayoutAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPhotoFromStorage(String fileName) {
        Log.d(TAG, "showPhotoFromStorage: фотография берется из хранилища fileName " + fileName);
    }
}
