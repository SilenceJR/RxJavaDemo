package com.silence.rxjavademo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.silence.rxjavademo.base.BaseActivity;
import com.silence.rxjavademo.base.BaseToolbar;
import com.silence.rxjavademo.fragment.TodayFragment;
import com.silence.rxjavademo.view.ToolBarCommView;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.frame_container)
    FrameLayout mFrameContainer;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layoyut)
    DrawerLayout mDrawerLayoyut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTool();
    }

    private void setTool() {
        ToolBarCommView toolBarCommView = new ToolBarCommView(this);
        toolBarCommView.setDefTitle().showBack(false);
        BaseToolbar baseToolbar = initToolBar(false, toolBarCommView);
        baseToolbar.setDefaultRedBg();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayoyut, baseToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayoyut.addDrawerListener(toggle);
        toggle.syncState();

        mNavView.setNavigationItemSelectedListener(this);
    }


    @Override
    protected int getActivityContentView() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.nav_today:
                if (null == getFragmentManager().findFragmentByTag(TodayFragment.TAG)) {

                }
                break;
            case R.id.nav_android:
                break;
            case R.id.nav_ios:
                break;
            case R.id.nav_about:
                break;
            case R.id.nav_feedback:
                break;
            default:
                break;

        }

        mDrawerLayoyut.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            //            startActivity(SearchActivity.newIntent(this));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onError(String msg, int code) {

    }

    @Override
    public void onNetFinish() {

    }
}
