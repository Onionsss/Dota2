package onionsss.it.mvpdota.ui.contract;

import android.webkit.WebView;
import android.widget.ProgressBar;

import onionsss.it.mvpdota.BaseView;
import onionsss.it.mvpdota.ui.presenter.BasePresenter;

/**
 * 作者：张琦 on 2016/8/14 19:42
 */
public class NewsDeteilContract {
    public interface View extends BaseView<Presenter> {
        void loadUrl(String url);
        void updateState(int state);
    }

    public interface Presenter extends BasePresenter {
        void loadData(String date,String nid);
        void initWebView(WebView webView, ProgressBar progressBar,String data);
    }
}
