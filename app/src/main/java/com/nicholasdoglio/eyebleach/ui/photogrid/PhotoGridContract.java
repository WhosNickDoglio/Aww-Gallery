package com.nicholasdoglio.eyebleach.ui.photogrid;


import com.nicholasdoglio.eyebleach.ui.base.BasePresenter;
import com.nicholasdoglio.eyebleach.ui.base.BaseView;

public interface PhotoGridContract {
    interface View extends BaseView<Presenter> {
        void initViews();

        void fetchData();
    }

    interface Presenter extends BasePresenter<View> {
    }
}