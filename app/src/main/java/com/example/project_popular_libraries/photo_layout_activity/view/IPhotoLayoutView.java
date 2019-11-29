package com.example.project_popular_libraries.photo_layout_activity.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface IPhotoLayoutView extends MvpView {

    @StateStrategyType(value = SkipStrategy.class)
    void openCardActivity(String url);

    @StateStrategyType(value = AddToEndStrategy.class)
    void updateRecyclerView();

    @StateStrategyType(value = SkipStrategy.class)
    void showPhotoFromStorage(String login);
}
