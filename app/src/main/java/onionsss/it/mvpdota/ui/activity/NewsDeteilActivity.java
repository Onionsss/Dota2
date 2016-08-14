package onionsss.it.mvpdota.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import onionsss.it.mvpdota.R;
import onionsss.it.mvpdota.ui.base.ToolbarActivity;
import onionsss.it.mvpdota.ui.contract.NewsDeteilContract;
import onionsss.it.mvpdota.ui.deteil.DeteilPresenter;
import onionsss.it.mvpdota.ui.home.HomeFragment;
import onionsss.it.mvpdota.ui.manager.LoadStatus;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class NewsDeteilActivity extends ToolbarActivity implements NewsDeteilContract.View{

    @Bind(R.id.main_toolbar)
    Toolbar mMainToolbar;
    @Bind(R.id.webview_progressbar)
    ProgressBar mWebviewProgressbar;
    @Bind(R.id.webview)
    WebView mWebview;

    private NewsDeteilContract.Presenter mDeteilPresenter;
    private LoadStatus mLoadStatus;
    private String mDate;
    private String mUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadStatus = new LoadStatus(this);

        mLoadStatus.addView(LayoutInflater.from(this).inflate(R.layout.activity_news_deteil,null,false));
        setContentView(mLoadStatus);
        ButterKnife.bind(this);

        initView();

        initData();
    }

    private void initView() {
        mDeteilPresenter = new DeteilPresenter(this,this,mCompositeSubscription);

        mMainToolbar.setNavigationIcon(R.drawable.finish_select);
        mMainToolbar.setNavigationOnClickListener(v -> finish());
        setToolbar2(mMainToolbar, "详情", R.menu.menu_watched_video);
    }

    private void initData() {
        Intent intent = getIntent();
        mDate = intent.getStringExtra(HomeFragment.DATE);
        mUid = intent.getStringExtra(HomeFragment.UID);
        mDeteilPresenter.loadData(mDate,mUid);
    }

    @Override
    public void setPresenter(NewsDeteilContract.Presenter presenter) {
        mDeteilPresenter = checkNotNull(presenter);
    }

    @Override
    public void loadUrl(String url) {
        mDeteilPresenter.initWebView(mWebview,mWebviewProgressbar,url);
    }

    @Override
    public void updateState(int state) {
        mLoadStatus.updateView(state);
    }

    /**
     * 返回上一个webview
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebview.canGoBack()) {
            mWebview.goBack(); // goBack()表示返回WebView的上一页面
            mDeteilPresenter.loadData(mDate,mUid);
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

}
