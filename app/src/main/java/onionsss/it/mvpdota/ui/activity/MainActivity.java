package onionsss.it.mvpdota.ui.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.ArrayList;

import butterknife.Bind;
import etong.bottomnavigation.lib.BottomNavigationBar;
import onionsss.it.mvpdota.R;
import onionsss.it.mvpdota.ui.base.BaseFragment;
import onionsss.it.mvpdota.ui.base.SimpleActivity;
import onionsss.it.mvpdota.ui.home.HomeFragment;

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
    private ActionBar mActionBar;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private ArrayList<BaseFragment> mFragmentList;
    private int preFragment;

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
        if(mFragmentList == null){
            mFragmentList = new ArrayList<>();
        }
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new HomeFragment());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content,mFragmentList.get(0),""+0).commit();

    }
    private void initListener() {
        mMainBottom.setOnTabListener(position -> {
            if(preFragment == position){
                return;
            }
            BaseFragment baseFragment = mFragmentList.get(position);
            if(baseFragment.isAdded()){
                getSupportFragmentManager().beginTransaction()
                        .hide(mFragmentList.get(preFragment)).show(baseFragment).commit();
            }else{
                getSupportFragmentManager().beginTransaction()
                        .hide(mFragmentList.get(preFragment)).add(R.id.content,baseFragment,""+position).commit();
            }

            preFragment = position;
        });
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


}
