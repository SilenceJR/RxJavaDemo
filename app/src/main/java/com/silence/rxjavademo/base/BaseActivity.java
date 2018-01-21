package com.silence.rxjavademo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.silence.rxjavademo.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
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


    protected BaseToolbar initToolBar(boolean showBack, View toolbarView) {
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

    protected void setToolbarBgColor(int color) {
        if (null != mToolbar)
            mToolbar.setBackgroundColor(color);
    }

    protected void hideToolbar() {
        if (null != mToolbar)
            mToolbar.setVisibility(View.GONE);
    }

    protected void initToolbarDefaultBg() {
        if (null != mToolbar)
            mToolbar.setDefaultRedBg();
    }

    // 获取Activity
    protected abstract int getActivityContentView();

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
