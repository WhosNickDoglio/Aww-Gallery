package com.nicholasdoglio.eyebleach.ui.photodetail;


import com.nicholasdoglio.eyebleach.ui.base.BasePresenter;
import com.nicholasdoglio.eyebleach.ui.base.BaseView;

public interface PhotoDetailContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {
    }
}
