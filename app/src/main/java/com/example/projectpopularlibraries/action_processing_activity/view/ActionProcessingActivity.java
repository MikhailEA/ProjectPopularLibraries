package com.example.projectpopularlibraries.action_processing_activity.view;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.util.Log;

import android.widget.ImageView;

import com.example.projectpopularlibraries.R;
import com.example.projectpopularlibraries.action_processing_activity.presenter.ActionProcessingPresenter;
import com.example.projectpopularlibraries.model.photoupload.PicassoLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class ActionProcessingActivity extends MvpAppCompatActivity implements IActionView {

    private static final String TAG = "ActionProcessingActivity";

    @InjectPresenter
    ActionProcessingPresenter actionProcessingPresenter;

    @BindView(R.id.imageViewAction)
    ImageView imageView;

    private PicassoLoader picassoLoader;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_processing);

        ButterKnife.bind(this);
        Bundle arguments = getIntent().getExtras();

        if (arguments != null) {
            String url = arguments.get("url").toString();
            this.picassoLoader = new PicassoLoader(this);
            picassoLoader.loadImage(url, imageView);
        } else {
            Log.d(TAG, "argument is null");
        }
    }
}
