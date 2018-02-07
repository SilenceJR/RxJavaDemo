package com.silence.rxjavademo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.silence.rxjavademo.base.BaseActivity;
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

    public void initRefreshLayout(SmartRefreshLayout smartRefreshLayout, OnRefreshListener onRefreshListener, boolean loadMoreEnable, OnLoadmoreListener onLoadmoreListener) {
        assert ((BaseActivity) getActivity()) != null;
        ((BaseActivity) getActivity()).initRefreshLayout(smartRefreshLayout, onRefreshListener, loadMoreEnable, onLoadmoreListener);
    }

    public void initToolbar(boolean showBack, View toolBarCommView) {
        assert ((BaseActivity) getActivity()) != null;
        ((BaseActivity) getActivity()).initToolBar(showBack, toolBarCommView);
    }

    @Override
    public void onError(String msg, int code) {
        assert ((BaseActivity) getActivity()) != null;
        ((BaseActivity) getActivity()).onError(msg, code);
    }

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
