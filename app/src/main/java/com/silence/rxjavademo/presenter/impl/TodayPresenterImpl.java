package com.silence.rxjavademo.presenter.impl;

import com.silence.rxjavademo.base.BasePresenter;
import com.silence.rxjavademo.presenter.TodayPresenter;
import com.silence.rxjavademo.view.TodayView;

/**
 * Created by Silence
 *
 * @time 2018/1/31 0:03
 * @des ${TODO}
 */

public class TodayPresenterImpl extends BasePresenter<TodayView> implements TodayPresenter {
    public TodayPresenterImpl(TodayView view) {
        super(view);
    }

    @Override
    public void getTodayData() {

    }
}
