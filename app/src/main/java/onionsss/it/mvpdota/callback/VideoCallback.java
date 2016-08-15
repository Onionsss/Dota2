package onionsss.it.mvpdota.callback;


import java.util.List;

import onionsss.it.mvpdota.bean.VideoList;

/**
 * Created by Administrator on 2016/4/19.
 */
public interface VideoCallback {
    void onUpdateSuccessed(List<VideoList.VideoEntity> videoEntityList, boolean loadmore);
    void onUpdateFailed(boolean loadmore);
    void onGetCachedVideo(List<VideoList.VideoEntity> videoEntityList);
    void onCacheEmpty();
}
