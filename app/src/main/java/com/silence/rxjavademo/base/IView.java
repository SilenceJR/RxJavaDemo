package com.silence.rxjavademo.base;

import io.reactivex.disposables.Disposable;

/**
 * Created by Silence
 *
 * @time 2018/1/21 16:04
 * @des ${TODO}
 */

public interface IView {

    void addDisposable(Disposable d);

    void onError(String msg, int code);

    void onNetFinish();
}
