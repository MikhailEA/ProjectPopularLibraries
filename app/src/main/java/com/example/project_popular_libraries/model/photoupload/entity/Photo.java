package com.example.project_popular_libraries.model.photoupload.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photo {

    @Expose
    @SerializedName("totalHits")
    public int totalHits;

    @Expose
    @SerializedName("hits")
    public List<Hit> hits;
}
