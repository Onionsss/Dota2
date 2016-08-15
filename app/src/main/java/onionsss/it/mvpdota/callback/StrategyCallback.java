package onionsss.it.mvpdota.callback;


import java.util.List;

import onionsss.it.mvpdota.bean.StrategyList;

/**
 * Created by lenov0 on 2016/4/17.
 */
public interface StrategyCallback {
    void onGetCachedStrategies(List<StrategyList.StrategyEntity> strategyEntityList);
    void onCachedStrategiesEmpty();
    void onUpdateSuccessed(List<StrategyList.StrategyEntity> strategyEntityList, boolean loadmore);
    void onUpdateFailed(boolean loadmore);
}
