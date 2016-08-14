package onionsss.it.mvpdota.ui.model;

import android.content.Context;

import onionsss.it.mvpdota.api.RetrofitUtil;
import onionsss.it.mvpdota.bean.NewsList;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：张琦 on 2016/8/14 12:18
 */
public class HomeModel extends MvpBaseModel{

    public HomeModel(Context context) {
        super(context);
    }

    public Observable<NewsList> getModel(){
        return RetrofitUtil.getinstance(getContext()).buildNews().refreshNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(nodel -> nodel.getResultModel());
    }

    public Observable<NewsList> loadMoreModel(String nid){
        return RetrofitUtil.getinstance(getContext()).buildNews().loadMoreNews(nid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(nodel -> nodel.getResultModel());
    }

    public Observable<NewsList> refresh(){
        return RetrofitUtil.getinstance(getContext()).buildNews().refreshNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(nodel -> nodel.getResultModel());
    }
}
