package com.silence.rxjavademo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.silence.rxjavademo.R;
import com.silence.rxjavademo.base.BaseToolbar;
import com.silence.rxjavademo.presenter.TodayPresenter;
import com.silence.rxjavademo.presenter.impl.TodayPresenterImpl;
import com.silence.rxjavademo.view.TodayView;
import com.silence.rxjavademo.view.ToolBarCommView;

import butterknife.BindView;

/**
 * Created by Silence
 *
 * @time 2018/1/30 23:56
 * @des ${TODO}
 */

public class TodayFragment extends BaseFragment implements TodayView, OnLoadmoreListener, OnRefreshListener {

    public static final String TAG = TodayFragment.class.getSimpleName();
    @BindView(R.id.toolbar_comm)
    ToolBarCommView mToolbarComm;
    @BindView(R.id.base_toolbar)
    BaseToolbar mBaseToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout mSmartRefresh;
    private RecyclerView mViewRecyclerView;
    private SmartRefreshLayout mRefreshSmartRefreshLayout;
    private TodayPresenter mPresenter;

    public TodayFragment() {

    }

    public static TodayFragment newInstance() {

        Bundle args = new Bundle();

        TodayFragment fragment = new TodayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new TodayPresenterImpl(this);
        initView();

    }

    private void initView() {
        initToolBar();
        initRefreshLayout(mSmartRefresh, this, true, this);
    }

    @Override
    public void onStart() {
        super.onStart();

        initData();
    }

    private void initData() {

    }

    private void initToolBar() {
        mToolbarComm.setTitlte("每日推荐").showBack(false);
        mBaseToolbar.fitTranslucentStatus(false).setDefaultRedBg();
    }

    @Override
    public void onNetFinish() {

    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

    }
}
