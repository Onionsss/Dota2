package onionsss.it.mvpdota.callback;


import java.util.List;

import onionsss.it.mvpdota.bean.VideoList;

/**
 * Created by Administrator on 2016/4/19.
 */
public interface IVideoView {
    void setVideoList(List<VideoList.VideoEntity> videoEntityList, boolean append);
    void setRefreshFailed(boolean loadMore);
    void hideProgress(boolean loadMore);
    void showNoMoreToast();
    void onCacheLoaded();
}
