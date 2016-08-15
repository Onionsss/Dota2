package onionsss.it.mvpdota.ui.Strategy;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import onionsss.it.mvpdota.R;
import onionsss.it.mvpdota.adapter.StrategyAdapter;
import onionsss.it.mvpdota.bean.StrategyList;
import onionsss.it.mvpdota.ui.base.BasePage;
import onionsss.it.mvpdota.ui.contract.StrategyContract;
import onionsss.it.mvpdota.ui.manager.LoadStatus;
import rx.subscriptions.CompositeSubscription;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * 作者：张琦 on 2016/8/15 14:54
 */
public class StrategyPage extends BasePage implements StrategyContract.View{
    private String type;
    private StrategyContract.Presenter strategyPresenter;
    private CompositeSubscription mSubscription;
    private LoadStatus mLoadStatus;
    private RecyclerView mRecy;
    private LinearLayoutManager mLayoutManager;
    private StrategyAdapter mStrategyAdapter;

    public StrategyPage(Context context, String s, CompositeSubscription compositeSubscription) {
        super(context);
        type = s;
        mSubscription = compositeSubscription;
    }

    @Override
    public View initView() {
        mLoadStatus = new LoadStatus(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.page_strategy, null, false);
        mRecy = (RecyclerView) view.findViewById(R.id.strategy_recy);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecy.setLayoutManager(mLayoutManager);
        mStrategyAdapter = new StrategyAdapter(mContext);
        mRecy.setAdapter(mStrategyAdapter);
        mLoadStatus.addView(view);
        return mLoadStatus;
    }

    @Override
    public Object initData() {
        strategyPresenter = new StrategyPresenter(mContext,type,mSubscription,this);
        strategyPresenter.loadData(type);
        return null;
    }

    @Override
    public void setPresenter(StrategyContract.Presenter presenter) {
        strategyPresenter = checkNotNull(presenter);
    }

    @Override
    public void updateState(int state) {
        mLoadStatus.updateView(state);
    }

    @Override
    public void showData(StrategyList bean) {
        mStrategyAdapter.flush(bean);
    }
}
