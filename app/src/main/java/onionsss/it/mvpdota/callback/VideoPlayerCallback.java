package onionsss.it.mvpdota.callback;


import java.util.List;

import onionsss.it.mvpdota.bean.RelatedVideoList;
import onionsss.it.mvpdota.bean.VideoDetailInfo;
import onionsss.it.mvpdota.bean.VideoSetList;

/**
 * Created by Administrator on 2016/4/20.
 */
public interface VideoPlayerCallback {
    public void onGetVideoSetSuccess(VideoSetList videoSetList);
    public void onGetVideoSetFailed();
    public void onGetVideoDetailSuccess(VideoDetailInfo videoDetailInfo);
    public void onGetVideoDetailFailed();
    public void onGetYoukuVidSuccess(int index, String vid);
    public void onGetYoukuVidFailed();
    public void onGetRelatedVideoListSuccess(List<RelatedVideoList.RelatedVideoEntity> relatedVideoEntityList);
    public void onGetRelatedVideoListFailed();
    public void onGetDetailAndRelatedVideoList(VideoDetailInfo videoDetailInfo, List<RelatedVideoList.RelatedVideoEntity> relatedVideoEntityList);
}
