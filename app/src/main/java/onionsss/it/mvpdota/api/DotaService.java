package onionsss.it.mvpdota.api;

import com.cpoopc.retrofitrxcache.RxCacheResult;

import onionsss.it.mvpdota.bean.NewsList;
import onionsss.it.mvpdota.bean.StrategyList;
import onionsss.it.mvpdota.bean.VideoList;
import onionsss.it.mvpdota.bean.VideoSetList;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 作者：张琦 on 2016/8/14 10:59
 */
public interface DotaService {
    @GET("/api/v1.0/news/refresh")
    Observable<RxCacheResult<NewsList>> refreshNews();
    @GET("/api/v1.0/news/loadmore/{nid}")
    Observable<RxCacheResult<NewsList>> loadMoreNews(@Path("nid") String nid);
    @GET("/api/v1.0/updates/refresh")
    Observable<RxCacheResult<NewsList>> refreshUpdates();
    @GET("/api/v1.0/newsdetail/{date}/{nid}")
    Observable<RxCacheResult<String>> getNewsDetail(@Path("date") String date, @Path("nid") String nid);
    @GET("/api/v1.0/video/youkuvid/{date}/{vid}")
    Observable<String> getYoukuVid(@Path("date") String date, @Path("vid") String vid);


    @GET("/api/v1.0/updates/loadmore/{nid}")
    Observable<NewsList> loadMoreUpdates(@Path("nid") String nid);
    @GET("/api/v1.0/strategy/refresh/{strategy_type}")
    Observable<RxCacheResult<StrategyList>> refreshStrategies(@Path("strategy_type") String strategyType);
    @GET("/api/v1.0/strategy/loadmore/{strategy_type}/{nid}")
    Observable<RxCacheResult<StrategyList>> loadMoreStrategies(@Path("strategy_type") String strategyType, @Path("nid") String nid);
    @GET("/api/v1.0/video/refresh/{video_type}")
    Observable<VideoList> refreshVideos(@Path("video_type") String videoType);
    @GET("/api/v1.0/video/loadmore/{video_type}/{vid}")
    Observable<VideoList> loadMoreVideos(@Path("video_type") String videoType, @Path("vid") String vid);
    @GET("/api/v1.0/video/videoset/{date}/{vid}")
    Observable<VideoSetList> getVideoSetInfo(@Path("date") String date, @Path("vid") String vid);
}
