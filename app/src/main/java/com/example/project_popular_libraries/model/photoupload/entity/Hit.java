package com.example.project_popular_libraries.model.photoupload.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "table_hits")
public class Hit {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @Expose
    @SerializedName("webformatURL")
    public String webformatURL;

    @Override
    public String toString() {
        return "Hit{" + "id=" + id +
                ", webURL = '" + webformatURL + '\'' + '}';
    }
}
