package com.example.projectpopularlibraries.model.photoupload.files;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;

public class ImageCache {

    private static final String TAG = "ImageCache";
    private static final String APP_FOLDER = "ProjectPopularLibraries";

    public void saveImage(Bitmap bitmap, String login) {

    }

    public void getImageDir() {

    }

    public File getImagePath(String fileName) {
        return new File(Environment.getExternalStorageState() + File.separator + APP_FOLDER
                + File.separator + fileName + ".png");
    }
}
