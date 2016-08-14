package onionsss.it.mvpdota.ui.fragment;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import onionsss.it.mvpdota.R;
import onionsss.it.mvpdota.adapter.NewsAdapter;
import onionsss.it.mvpdota.bean.NewsList;
import onionsss.it.mvpdota.ui.base.BaseFragment;
import onionsss.it.mvpdota.ui.contract.HomeContract;
import onionsss.it.mvpdota.ui.manager.LoadStatus;
import onionsss.it.mvpdota.ui.presenter.HomePresenter;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * 作者：张琦 on 2016/8/14 09:54
 */
public class HomeFragment extends BaseFragment implements HomeContract.View{

    @Bind(R.id.home_recy)
    RecyclerView mHomeRecy;
    @Bind(R.id.home_swipe)
    SwipeRefreshLayout mHomeSwipe;

    private HomeContract.Presenter mHomePresenter;
    private LoadStatus mLoadStatus;
    private LinearLayoutManager mLinearLayoutManager;
    private NewsAdapter mNewsAdapter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mLoadStatus = new LoadStatus(getContext());
        mLoadStatus.addView(view);
        mHomePresenter = new HomePresenter(this,mCompositeSubscription,mActivity);
        return mLoadStatus;
    }

    @Override
    public void initData(View rootView) {
        showView();
        initListener();
        mHomePresenter.loadData(false);
    }

    private void initListener() {
        mHomeSwipe.setOnRefreshListener(() -> mHomePresenter.refresh());
    }

    private void showView() {
        mLinearLayoutManager = new LinearLayoutManager(mActivity);
        mHomeRecy.setLayoutManager(mLinearLayoutManager);
        mNewsAdapter = new NewsAdapter(getActivity());
        mNewsAdapter.setOnFootListener(() -> mHomePresenter.loadMore());
        mHomeRecy.setAdapter(mNewsAdapter);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mHomePresenter = checkNotNull(presenter);
    }

    @Override
    public void updateState(int state) {
        mLoadStatus.updateView(state);
    }

    @Override
    public void setData(NewsList data, boolean isFlush) {
        mNewsAdapter.flush(data,isFlush);
    }

    @Override
    public void swipeClose() {
        mHomeSwipe.setRefreshing(false);
    }

    @Override
    public void showSnackBar(int content) {
        Snackbar.make(mHomeRecy, getString(content), Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void refresh(NewsList data) {
        mNewsAdapter.refresh(data);
    }

    @Override
    public void loadMore(NewsList data) {
        mNewsAdapter.loadMore(data);
    }

    @Override
    public void loadMoreSuccess(boolean flag) {

    }
}
