package onionsss.it.mvpdota.ui.base;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import onionsss.it.mvpdota.R;
import onionsss.it.mvpdota.bean.TitleAndPage;

/**
 * 作者：张琦 on 2016/8/15 14:27
 */
public abstract class BaseTabFragment extends BaseFragment{

    @Bind(R.id.tab)
    TabLayout mTab;
    @Bind(R.id.vp)
    ViewPager mVp;

    private List<TitleAndPage> mPageList;
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.tabfragment, container, false);
        return view;
    }

    @Override
    public void initData(View rootView) {
        tabAdapter tabAdapter = new tabAdapter();
        mPageList = getList();

        mVp.setAdapter(tabAdapter);
        mTab.setupWithViewPager(mVp);
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPageList.get(position).page.initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        
        childView();
        childData();
    }

    protected abstract void childView();

    protected abstract void childData();

    public abstract List<TitleAndPage> getList();


    class tabAdapter extends PagerAdapter{
        @Override
        public CharSequence getPageTitle(int position) {
            return mPageList.get(position).title;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePage basePage = mPageList.get(position).page;
            View rootVieww = basePage.mRootVieww;
            container.addView(rootVieww);
            if(position == 0){
                basePage.initData();
            }
            return rootVieww;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public int getCount() {
            return mPageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
