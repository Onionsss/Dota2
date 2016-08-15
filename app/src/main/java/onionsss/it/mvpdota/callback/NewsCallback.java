package onionsss.it.mvpdota.callback;


import java.util.List;

import onionsss.it.mvpdota.bean.NewsList;

/**
 * Created by lenov0 on 2016/4/9.
 */
public interface NewsCallback {
    void onGetBanner(List<NewsList.NewsEntity> bannerEntityList);
    void onGetCache(List<NewsList.NewsEntity> bannerEntityList, List<NewsList.NewsEntity> newsEntityList, boolean updateNews);
    void onCacheEmpty();
    void onUpdateSuccessed(List<NewsList.NewsEntity> newsEntityList, boolean loadmore);
    void onUpdateFailed(boolean loadmore);
}
