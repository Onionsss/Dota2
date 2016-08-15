package onionsss.it.mvpdota.callback;

/**
 * Created by dear33 on 2016/7/18.
 */
public interface VideoStateCallback {
    void onProgressChanged(int currentTimeMillis);
    void onVideoStart(int durationMillis);
    void onVideoEnd();
}
