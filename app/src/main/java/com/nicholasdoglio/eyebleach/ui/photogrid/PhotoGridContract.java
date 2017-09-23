package com.nicholasdoglio.eyebleach.ui.photogrid;


import com.nicholasdoglio.eyebleach.data.model.ChildData;
import com.nicholasdoglio.eyebleach.data.model.Multireddit;
import com.nicholasdoglio.eyebleach.ui.base.BasePresenter;
import com.nicholasdoglio.eyebleach.ui.base.BaseView;

import java.util.List;

public interface PhotoGridContract {
    interface View extends BaseView<Presenter> {
        void initViews();

        void fetchData();

        void updateListMulti(Multireddit multireddit);

        void updateListChildData(List<ChildData> childData);
    }

    interface Presenter extends BasePresenter<View> {
    }
}
