package onionsss.it.mvpdota.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import onionsss.it.mvpdota.R;
import onionsss.it.mvpdota.bean.NewsList;
import onionsss.it.mvpdota.ui.contract.HomeContract;
import onionsss.it.mvpdota.ui.manager.LoadStatus;
import onionsss.it.mvpdota.view.MyHeadViewPager;
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

    @Override
    public void loadHead(NewsList data, MyHeadViewPager home_head_vp) {
        List<String> banlist = new ArrayList<>();
        banlist.add(data.getBanner().get(0).getBackground());
        banlist.add(data.getBanner().get(1).getBackground());
        banlist.add(data.getBanner().get(2).getBackground());

        home_head_vp.setPages(() -> new NetworkImageHolderView(),banlist)
                .setPageIndicator(new int[]{R.drawable.head_vp_shape, R.drawable.head_vp_shape_wh})
                .setCanLoop(true);
        home_head_vp.startTurning(2500);
    }

    public class NetworkImageHolderView implements Holder<String> {

        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Glide.with(mContext).load(data).placeholder(Color.TRANSPARENT)
                    .error(R.mipmap.ic_book_default)
                    .centerCrop()
                    .into(imageView);
        }
    }
}

