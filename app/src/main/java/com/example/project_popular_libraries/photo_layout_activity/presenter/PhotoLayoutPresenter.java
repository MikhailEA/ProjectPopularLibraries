package com.example.project_popular_libraries.photo_layout_activity.presenter;


import android.util.Log;

import com.example.project_popular_libraries.app.App;
import com.example.project_popular_libraries.model.Model;
import com.example.project_popular_libraries.model.database.HitDao;
import com.example.project_popular_libraries.model.photoupload.entity.Hit;
import com.example.project_popular_libraries.model.photoupload.entity.Photo;
import com.example.project_popular_libraries.model.photoupload.retrofit.ApiHelper;
import com.example.project_popular_libraries.photo_layout_activity.view.IPhotoLayoutView;
import com.example.project_popular_libraries.photo_layout_activity.view.IRecyclerPhotoViewHolder;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class PhotoLayoutPresenter extends MvpPresenter<IPhotoLayoutView> {

    private static final String TAG = "PhotoGridPresenter";

    private RecyclerPresenter recyclerPresenter = new RecyclerPresenter();
    private HitDao hitDao = App.getAppDataBase().hitDao();

    public RecyclerPresenter getRecyclerPresenter() {
        return recyclerPresenter;
    }


    class RecyclerPresenter implements IRecyclerPhotoPresenter {

        private static final String TAG_IN = "RecyclerPresenter";

        private Model recyclerModel= new Model();

        private ApiHelper apiHelper = new ApiHelper();
        private List<Hit> hitList;

        @Override
        public void bindView(IRecyclerPhotoViewHolder iViewHolder) {
            iViewHolder.setImage(hitList.get(iViewHolder.getPos()).webformatURL);
        }

        @Override
        public int getItemCount() {
            if (hitList != null) {
                return hitList.size();
            }
            return 0;
        }

        @Override
        public void setCountIncrementToModel() {
            recyclerModel.setCountIncrement();
        }

        @Override
        public int getCountFromModel() {
            return recyclerModel.getCount();
        }

        @Override
        public void changeActivity(String url) {
            getViewState().openCardActivity(url);
        }

        public void getAllPhotoFromServer() {
            Observable<Photo> single = apiHelper.requestServer();
            Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(photos -> {
                hitList = photos.hits;
                getViewState().updateRecyclerView();
            }, throwable -> {
                Log.e(TAG_IN, "onError " + throwable);
            });
        }

        public void setHitToDb(Hit hit) {
            Log.d(TAG, "putHitToDB: save Hit to BD ");
            Disposable disposable = hitDao.insert(hit).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(id -> {
                        Log.d(TAG, "putHitToDb: Hit сохранён с id " + id);
                    }, throwable -> {
                        Log.d(TAG, "putData: " + throwable);
                    });
        }

        public String getHitFromDbById(int id) {
            final String[] url = new String[1];
            Disposable disposable = hitDao.getHitById(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(photo -> {
                        Log.d(TAG, "getData: " + photo + " " + Thread.currentThread().getName());
                        url[0] = photo.webformatURL;
                    }, throwable -> {
                        Log.d(TAG, "getData: " + throwable);
                    });
            return url[0];
        }

        @Override
        public void onFirstViewAttach() { recyclerPresenter.getAllPhotoFromServer(); }
    }
}
