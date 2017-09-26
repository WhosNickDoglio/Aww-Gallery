package com.nicholasdoglio.eyebleach.ui.photodetail;


import com.nicholasdoglio.eyebleach.data.model.ChildData;
import com.nicholasdoglio.eyebleach.ui.base.BasePresenter;
import com.nicholasdoglio.eyebleach.ui.base.BaseView;

import java.util.List;

public interface PhotoDetailContract {
    interface View extends BaseView<Presenter> {

        void updateList(List<ChildData> childDataList);


    }

    interface Presenter extends BasePresenter<View> {
    }
}
