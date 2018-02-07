package com.silence.rxjavademo.base;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Silence
 *
 * @time 2018/1/31 0:27
 * @des ${TODO}
 */

public abstract class BaseObserver<T> implements Observer<T> {

    protected final IView mView;

    public BaseObserver(IView view) {
        mView = view;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (null != mView) {
            mView.addDisposable(d);
        }
    }

    @Override
    public void onError(Throwable e) {
        String msg = e.getMessage();
        int code = 0x01;
        onError(msg, code);
    }

    private void onError(String msg, int code) {
        if (null != mView) {
            mView.onError(msg, code);
        }
        onNetFinish();
    }

    @Override
    public void onComplete() {
        onNetFinish();
    }

    private void onNetFinish() {
        if (null != mView) {
            mView.onNetFinish();
        }
    }
}
