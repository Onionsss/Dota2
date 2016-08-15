package onionsss.it.mvpdota.callback;

import java.util.List;

import onionsss.it.mvpdota.bean.NewsList;



/**
 * Created by lenov0 on 2016/4/9.
 */
public interface INewsView {
    void onCacheLoaded();
    void setBanner(List<NewsList.NewsEntity> bannerEntityList);
    void setNewsList(List<NewsList.NewsEntity> newsEntityList, boolean append);
    void setRefreshFailed(boolean loadMore);
    void hideProgress(boolean loadMore);
}
