package com.silence.rxjavademo.view;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silence.rxjavademo.R;

/**
 * Created by Silence
 *
 * @time 2018/1/21 20:18
 * @des ${TODO}
 */

public class ToolBarCommView extends RelativeLayout {

    private Button mToolBarBack;
    private TextView mToolBarTitle;
    private TextView mToolBarSubTitle;
    private ImageView mToolBarIcon;

    public ToolBarCommView(Context context) {
        this(context, null);
    }

    public ToolBarCommView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.toolbar_comm, this);
        mToolBarBack = findViewById(R.id.toolbar_back);
        mToolBarTitle = findViewById(R.id.toolbar_title);
        mToolBarSubTitle = findViewById(R.id.toolbar_subtitle);
        mToolBarIcon = findViewById(R.id.toolbar_icon);

        mToolBarBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack(v);
            }
        });
    }


    private void onBack(View v) {
        Context context = getContext();
        if (context instanceof Activity) {
            ((Activity) context).onBackPressed();
        }
    }


    public void setImgRightIcon(int resid) {
        mToolBarIcon.setImageResource(resid);
    }

    public void setImgRightIconEvent(View.OnClickListener listener) {
        mToolBarIcon.setOnClickListener(listener);
    }

    /**
     * 显示退出按钮
     *
     * @param isShow
     */
    public ToolBarCommView showBack(boolean isShow) {
        if (!isShow) {
            mToolBarBack.setVisibility(View.GONE);
        } else {
            mToolBarBack.setVisibility(VISIBLE);
        }
        return this;
    }

    //标题
    public ToolBarCommView setTitlte(String title) {
        mToolBarTitle.setText(title);
        return this;
    } //标题

    public ToolBarCommView setTitlte(int title) {
        mToolBarTitle.setText(title);
        return this;
    }

    //默认标题
    public ToolBarCommView setDefTitle() {
        Context context = getContext();
        if (context instanceof Activity) {
            try {
                PackageManager packageManager = context.getPackageManager();
                ActivityInfo activityInfo = packageManager.getActivityInfo(((Activity) context).getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
                CharSequence charSequence = activityInfo.loadLabel(packageManager);
                setTitlte(charSequence.toString());
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    /**
     * 右边按钮
     */
    public ToolBarCommView setRightTitle(String rightTitle) {
        mToolBarSubTitle.setText(rightTitle);
        return this;
    }

    /**
     * 右边按钮事件
     */
    public ToolBarCommView setRightEvent(OnClickListener listener) {
        mToolBarSubTitle.setOnClickListener(listener);
        return this;
    }

}
