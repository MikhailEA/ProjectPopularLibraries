package com.example.project_popular_libraries.model.photoupload;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PicassoLoader {

    private Context context;

    public PicassoLoader(Context context) {
        this.context = context;
    }

    public void loadImage(String url, ImageView imageView){
        Glide
                .with(context)
                .load(url)
                .into(imageView);
    }
}
