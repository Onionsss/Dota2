package onionsss.it.mvpdota.center;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.youku.player.YoukuPlayerBaseConfiguration;

import onionsss.it.mvpdota.ui.base.BaseActivity;

/**
 * 作者：张琦 on 2016/8/14 10:24
 */
public class AppCenter extends Application{
    private static YoukuPlayerBaseConfiguration configuration;
    public static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;

//        initYoukuConfiguration();
    }

    private void initYoukuConfiguration() {
        configuration = new YoukuPlayerBaseConfiguration(this) {
            @Override
            public Class<? extends Activity> getCachingActivityClass() {
                return BaseActivity.class;
            }

            @Override
            public Class<? extends Activity> getCachedActivityClass() {
                return BaseActivity.class;
            }

            @Override
            public String configDownloadPath() {
                return null;
            }
        };
    }
}
