package onionsss.it.mvpdota.ui.deteil;

import android.content.Context;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import onionsss.it.mvpdota.ui.contract.NewsDeteilContract;
import onionsss.it.mvpdota.ui.manager.LoadStatus;
import onionsss.it.mvpdota.utils.LogUtil;
import rx.subscriptions.CompositeSubscription;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * 作者：张琦 on 2016/8/14 19:43
 */
public class DeteilPresenter implements NewsDeteilContract.Presenter{
    private CompositeSubscription mCompositeSubscription;
    public Context mContext;
    public NewsDeteilContract.View newsDeteilView;
    private NewsDeteilModel mNewsModel;

    public DeteilPresenter(NewsDeteilContract.View view,Context context,CompositeSubscription compositeSubscription){
        mNewsModel = new NewsDeteilModel(context);
        mContext = context;
        newsDeteilView = checkNotNull(view);
        newsDeteilView.setPresenter(this);
        this.mCompositeSubscription = compositeSubscription;
    }


    @Override
    public void loadData(String date,String nid) {
        mNewsModel.getDeteilModel(date,nid)
                .subscribe(data -> newsDeteilView.loadUrl(data),
                        error -> newsDeteilView.updateState(LoadStatus.STATUS_ERROR),
                        () -> {newsDeteilView.updateState(LoadStatus.STATUS_SUCCESS);
                            LogUtil.ShowLog("success");});
    }

    @Override
    public void initWebView(WebView webView, ProgressBar progressBar,String data) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setSupportZoom(false);
        settings.setPluginState(WebSettings.PluginState.ON);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        webView.loadDataWithBaseURL(null, data, "text/html", "UTF-8", null);
    }
}
