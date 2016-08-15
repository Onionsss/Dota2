package onionsss.it.mvpdota.ui.Strategy;

import android.content.Context;

import onionsss.it.mvpdota.ui.contract.StrategyContract;
import onionsss.it.mvpdota.ui.manager.LoadStatus;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * 作者：张琦 on 2016/8/15 15:56
 */
public class StrategyPresenter implements StrategyContract.Presenter{
    public Context mContext;
    private String type;
    private CompositeSubscription mSubscription;
    private StrategyContract.View strategyView;
    private StrategyModel mStrategyModel;
    public StrategyPresenter(Context context,String type,CompositeSubscription subscription,StrategyContract.View view){
        mStrategyModel = new StrategyModel(context);
        mContext = context;
        this.type = type;
        mSubscription = subscription;
        strategyView = checkNotNull(view);
        strategyView.setPresenter(this);
    }

    @Override
    public void loadData(String type) {
        Subscription subscribe = mStrategyModel.getModel(type)
                .subscribe(bean -> strategyView.showData(bean),
                error -> strategyView.updateState(LoadStatus.STATUS_ERROR),
                () -> strategyView.updateState(LoadStatus.STATUS_SUCCESS));

        mSubscription.add(subscribe);
    }
}
