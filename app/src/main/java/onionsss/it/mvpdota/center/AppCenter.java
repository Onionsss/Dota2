package onionsss.it.mvpdota.center;

import android.app.Application;
import android.content.Context;

/**
 * 作者：张琦 on 2016/8/14 10:24
 */
public class AppCenter extends Application{
    public static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }
}
