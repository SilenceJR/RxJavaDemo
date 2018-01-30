package com.silence.rxjavademo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silence.rxjavademo.base.IView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Silence
 *
 * @time 2018/1/30 23:56
 * @des ${TODO}
 */

public abstract class BaseFragment extends Fragment implements IView {

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = getContentView(inflater, container, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, contentView);
        return contentView;
    }

    protected abstract View getContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void addDisposable(Disposable d) {
        mCompositeDisposable.add(d);
    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.clear();
        mUnbinder.unbind();
        super.onDestroy();
    }
}
