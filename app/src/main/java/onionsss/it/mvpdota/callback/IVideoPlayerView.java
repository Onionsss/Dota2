package onionsss.it.mvpdota.callback;

import java.util.List;

import onionsss.it.mvpdota.bean.RelatedVideoList;
import onionsss.it.mvpdota.bean.VideoSetList;

/**
 * Created by Administrator on 2016/4/20.
 */
public interface IVideoPlayerView {
    void setVideoList(List<VideoSetList.VideoDateVidEntity> videoList);
    void setAnthologyGridGone();
    void setYoukuVid(boolean queryVideoDetail, int index, String youkuVid);
    void onGetInfoFailed(String error);
    void setVideoDetail(String title, String published, String watchedCount, String upCount, String downCount);
    void setRelatedVideoList(List<RelatedVideoList.RelatedVideoEntity> relatedVideoList);
    void setNoRelatedVideo();
    void hideProgressBar();
}
