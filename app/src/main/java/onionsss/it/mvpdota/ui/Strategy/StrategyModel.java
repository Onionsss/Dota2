package onionsss.it.mvpdota.ui.Strategy;

import android.content.Context;

import onionsss.it.mvpdota.api.RetrofitUtil;
import onionsss.it.mvpdota.bean.StrategyList;
import onionsss.it.mvpdota.ui.model.MvpBaseModel;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：张琦 on 2016/8/15 15:53
 */
public class StrategyModel extends MvpBaseModel{
    public StrategyModel(Context context) {
        super(context);
    }

    public Observable<StrategyList> getModel(String type){
        return RetrofitUtil.getinstance(getContext()).buildStrategy().refreshStrategies(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(nodel -> nodel.getResultModel()).first();
    }
}
