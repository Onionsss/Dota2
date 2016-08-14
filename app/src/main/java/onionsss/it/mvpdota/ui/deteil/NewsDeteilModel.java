package onionsss.it.mvpdota.ui.deteil;

import android.content.Context;

import onionsss.it.mvpdota.api.RetrofitUtil;
import onionsss.it.mvpdota.ui.model.MvpBaseModel;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：张琦 on 2016/8/14 19:46
 */
public class NewsDeteilModel extends MvpBaseModel {
    public NewsDeteilModel(Context context) {
        super(context);
    }

    public Observable<String> getDeteilModel(String date,String nid){
        return RetrofitUtil.getinstance(getContext()).buildDeteil().getNewsDetail(date,nid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(nodel -> nodel.getResultModel()).first();
    }
}
