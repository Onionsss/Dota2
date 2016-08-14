package onionsss.it.mvpdota.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import etong.bottomnavigation.lib.BottomNavigationBar;
import onionsss.it.mvpdota.R;
import onionsss.it.mvpdota.ui.base.BaseFragment;
import onionsss.it.mvpdota.ui.base.SimpleActivity;
import onionsss.it.mvpdota.ui.home.HomeFragment;
import onionsss.it.mvpdota.utils.RxBus;
import onionsss.it.mvpdota.utils.RxEvent;

public class MainActivity extends SimpleActivity {


    @Bind(R.id.main_toolbar)
    Toolbar mMainToolbar;
    @Bind(R.id.content)
    FrameLayout mContent;
    @Bind(R.id.left)
    FrameLayout mLeft;
    @Bind(R.id.main_bottom)
    BottomNavigationBar mMainBottom;
    @Bind(R.id.DrawerLayout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.floatr)
    FloatingActionButton mFloatr;

    public static final String SCROLL_TOP = "top";
    private ActionBar mActionBar;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private ArrayList<BaseFragment> mFragmentList;
    private int preFragment;
    private long exitTime;

    @Override
    protected void initView() {
        initBottom();

        initActionBar();

        initPage();
        initListener();
    }

    private void initActionBar() {
        mActionBar = setToolbar(mMainToolbar, "首页", -1);

        mActionBar.setTitle("MvpDota");
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState();//同步状态
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
    }

    private void initPage() {
        if (mFragmentList == null) {
            mFragmentList = new ArrayList<>();
        }
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new HomeFragment());
        getSupportFragmentManager().beginTransaction().add(R.id.content, mFragmentList.get(0), "" + 0).commit();
        mFloatr.setVisibility(View.VISIBLE);

    }

    private void initListener() {
        mMainBottom.setOnTabListener(position -> {
            if (preFragment == position) {
                return;
            }
            if(position == 0){
                mFloatr.setVisibility(View.VISIBLE);
            }else{
                mFloatr.setVisibility(View.GONE);
            }
            BaseFragment baseFragment = mFragmentList.get(position);
            if (baseFragment.isAdded()) {
                getSupportFragmentManager().beginTransaction().hide(mFragmentList.get(preFragment)).show(baseFragment).commit();
            } else {
                getSupportFragmentManager().beginTransaction().hide(mFragmentList.get(preFragment)).add(R.id.content, baseFragment, "" + position).commit();
            }

            preFragment = position;
        });

        mFloatr.setOnClickListener(v -> RxBus.getDefault().post(new RxEvent(SCROLL_TOP,1)));
    }

    private void initBottom() {
        mMainBottom.addTab(R.drawable.ome, "首页", 0xff000000);
        mMainBottom.addTab(R.drawable.selector_news, "视频", 0xff000000);
        mMainBottom.addTab(R.drawable.selector_books, "发现", 0xff000000);
        mMainBottom.addTab(R.drawable.wode, "我的", 0xff000000);
    }

    //设置开关
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //得到我们的id
        switch (item.getItemId()) {
            case android.R.id.home://箭头对象
                mActionBarDrawerToggle.onOptionsItemSelected(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 用户需要点击两次退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
