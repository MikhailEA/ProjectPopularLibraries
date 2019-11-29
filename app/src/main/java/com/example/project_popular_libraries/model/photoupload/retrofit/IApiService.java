package com.example.project_popular_libraries.model.photoupload.retrofit;

import com.example.project_popular_libraries.model.photoupload.entity.Photo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IApiService {
    @GET("api")
    Observable<Photo> getPhoto(@Query("key") String key);
}
