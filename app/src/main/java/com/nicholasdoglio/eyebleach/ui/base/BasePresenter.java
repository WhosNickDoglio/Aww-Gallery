package com.nicholasdoglio.eyebleach.ui.base;

public interface BasePresenter<T> {
    void takeView(T view);

    void dropView();

    void load();

    void clear();
}