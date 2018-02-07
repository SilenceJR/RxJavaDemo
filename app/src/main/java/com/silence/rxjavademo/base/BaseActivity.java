package com.silence.rxjavademo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.silence.rxjavademo.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Silence
 *
 * @time 2018/1/21 16:03
 * @des ${TODO}
 */

public abstract class BaseActivity extends AppCompatActivity implements IView{

    private CompositeDisposable mDisposable = new CompositeDisposable();
    private Unbinder mUnbinder;
    private LinearLayout mRootView;
    private BaseToolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        mRootView = findViewById(R.id.root_view);
        FrameLayout flConnView = (FrameLayout) findViewById(R.id.fl_conn_view);
        flConnView.addView(LayoutInflater.from(this).inflate(getActivityContentView(), flConnView, false));

        mUnbinder = ButterKnife.bind(this);
    }


    public BaseToolbar initToolBar(boolean showBack, View toolbarView) {
        if (null != mToolbar) {
            new IllegalArgumentException("toolbar is not null!");
        }
        mToolbar = new BaseToolbar(this);
        mToolbar.addView(toolbarView);
        mRootView.addView(mToolbar, 0);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mToolbar.fitTranslucentStatus(showBack);
        return mToolbar;
    }

    // 获取Activity
    abstract int getActivityContentView();

    public void initRefreshLayout(SmartRefreshLayout smartRefreshLayout, OnRefreshListener onRefreshListener, boolean loadMoreEnable, OnLoadmoreListener onLoadmoreListener) {
        smartRefreshLayout.setOnRefreshListener(onRefreshListener);
        if (loadMoreEnable) {
            smartRefreshLayout.setOnLoadmoreListener(onLoadmoreListener);
        }
        smartRefreshLayout.setEnableLoadmore(loadMoreEnable);
    }

    public void showToast(String msg, int type) {
        switch (type) {
            case ToastType.ERROR :
                Toasty.error(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                break;
            case ToastType.INFO:
                Toasty.info(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                break;
            case ToastType.NORMAL:
                Toasty.normal(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                break;
            case ToastType.WARNING:
                Toasty.warning(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                break;
            case ToastType.SUCCESS:
                Toasty.success(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onError(String msg, int code) {
        showToast(msg, ToastType.ERROR);
    }

    public class ToastType {
        public static final int ERROR = 0x01;
        public static final int INFO = 0x02;
        public static final int NORMAL = 0x03;
        public static final int WARNING = 0x04;
        public static final int SUCCESS = 0x05;

    }


    @Override
    public void addDisposable(Disposable d) {
        mDisposable.add(d);
        mUnbinder.unbind();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.clear();
    }
}
