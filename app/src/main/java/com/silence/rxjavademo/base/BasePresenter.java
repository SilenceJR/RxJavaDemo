package com.silence.rxjavademo.base;

import java.lang.ref.WeakReference;

/**
 * Created by Silence
 *
 * @time 2018/1/21 17:44
 * @des ${TODO}
 */

public class BasePresenter<V extends IView> implements IPresenter {

    private final WeakReference<V> mWeakReference;

    public BasePresenter(V view) {
        mWeakReference = new WeakReference<V>(view);
    }

    @Override
    public IView getView() {
        V v = mWeakReference.get();
        if (null != v) {
            return v;
        }
        return null;
    }

    @Override
    public void unAttach() {
        mWeakReference.clear();
    }
}
