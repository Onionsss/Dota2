package onionsss.it.mvpdota.ui.home;

import android.content.Context;

import onionsss.it.mvpdota.R;
import onionsss.it.mvpdota.ui.contract.HomeContract;
import onionsss.it.mvpdota.ui.manager.LoadStatus;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * 作者：张琦 on 2016/8/14 10:30
 * P层  负责分发数据 回调V层
 */
public class HomePresenter implements HomeContract.Presenter {
    private CompositeSubscription mSubscription;
    private HomeContract.View HomeView ;
    private Context mContext;
    private final HomeModel mHomeModel;
    private String mNid;
    public HomePresenter(HomeContract.View view, CompositeSubscription compositeSubscription, Context context){
        mHomeModel = new HomeModel(context);
        mContext = context;
        HomeView = checkNotNull(view);
        view.setPresenter(this);
        mSubscription = compositeSubscription;
    }

    @Override
    public void loadData(boolean isFlush) {
        Subscription subscribe = mHomeModel.getModel()
                .subscribe(bean -> {HomeView.setData(bean,isFlush);
                    mNid = bean.getNews().get(bean.getNews().size() - 1).getNid();},
                        error -> HomeView.updateState(LoadStatus.STATUS_ERROR),
                        () -> {HomeView.updateState(LoadStatus.STATUS_SUCCESS);
                            HomeView.swipeClose();});

        mSubscription.add(subscribe);
    }

    @Override
    public void loadMore() {
        Subscription subscribe = mHomeModel.loadMoreModel(mNid)
                .subscribe(bean -> {
                    HomeView.loadMore(bean);
            mNid = bean.getNews().get(bean.getNews().size() - 1).getNid();
        }, error -> HomeView.showSnackBar(R.string.refresherror), () -> {
            HomeView.updateState(LoadStatus.STATUS_SUCCESS);
            HomeView.loadMoreSuccess(true);
        });

        mSubscription.add(subscribe);
    }

    @Override
    public void refresh() {
        Subscription subscribe = mHomeModel.refresh()
                .subscribe(bean -> {if(bean == null) return;
                    HomeView.refresh(bean);},
                        error -> HomeView.showSnackBar(R.string.refresherror),
                        () -> {HomeView.swipeClose();
                            HomeView.updateState(LoadStatus.STATUS_SUCCESS);
                            HomeView.showSnackBar(R.string.tip_guide);});

        mSubscription.add(subscribe);
    }


}

