package onionsss.it.mvpdota.callback;


import java.util.List;

import onionsss.it.mvpdota.bean.StrategyList;

/**
 * Created by lenov0 on 2016/4/17.
 */
public interface IStrategyView {
    void setStrategyList(List<StrategyList.StrategyEntity> strategyEntityList, boolean append);
    void setRefreshFailed(boolean loadMore);
    void hideProgress(boolean loadMore);
    void showNoMoreToast();
    void onCacheLoaded();
}
