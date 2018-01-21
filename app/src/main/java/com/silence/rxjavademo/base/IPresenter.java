package com.silence.rxjavademo.base;

/**
 * Created by Silence
 *
 * @time 2018/1/21 17:42
 * @des ${TODO}
 */

public interface IPresenter<V extends IView> {

    V getView();

    /**
     * 解绑View
     */
    void unAttach();

}
