package onionsss.it.mvpdota.api;

import android.content.Context;

import com.cpoopc.retrofitrxcache.BasicCache;
import com.cpoopc.retrofitrxcache.RxCacheCallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：张琦 on 2016/8/14 11:08
 */
public class RetrofitUtil {
    private Context mContext;
    private Retrofit mRetrofit;
    private static RetrofitUtil sRetrofitUtil;
    private RetrofitUtil(Context context){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder().baseUrl("http://dota2xufserver.duapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(new RxCacheCallAdapterFactory(BasicCache.fromCtx(context.getApplicationContext())))
                    .build();
        }
    }

    public static RetrofitUtil getinstance(Context context){
        if(sRetrofitUtil == null){
            sRetrofitUtil = new RetrofitUtil(context);
        }
        return sRetrofitUtil;
    }

    public DotaService buildNews(){
        return mRetrofit.create(DotaService.class);
    }

}
