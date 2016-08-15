package onionsss.it.mvpdota.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youku.player.base.YoukuBasePlayerManager;
import com.youku.player.base.YoukuPlayer;
import com.youku.player.base.YoukuPlayerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import onionsss.it.mvpdota.R;
import onionsss.it.mvpdota.callback.VideoStateCallback;
import onionsss.it.mvpdota.ui.base.ToolbarActivity;
import onionsss.it.mvpdota.ui.contract.NewsDeteilContract;
import onionsss.it.mvpdota.ui.deteil.DeteilPresenter;
import onionsss.it.mvpdota.ui.home.HomeFragment;
import onionsss.it.mvpdota.ui.manager.LoadStatus;
import onionsss.it.mvpdota.utils.BlurTransformation;
import onionsss.it.mvpdota.utils.LogUtil;
import onionsss.it.mvpdota.view.YoukuPluginPlayer;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class NewsDeteilActivity extends ToolbarActivity implements NewsDeteilContract.View {


    @Bind(R.id.main_toolbar)
    Toolbar mMainToolbar;
    @Bind(R.id.vs_web)
    ViewStub mVsWeb;
    @Bind(R.id.vs_video)
    ViewStub mVsVideo;

    private ProgressBar mWebviewProgressbar;

    private WebView mWebview;

    private NewsDeteilContract.Presenter mDeteilPresenter;
    private LoadStatus mLoadStatus;
    private String mDate;
    private String mUid;
    private boolean isVideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadStatus = new LoadStatus(this);

        mLoadStatus.addView(LayoutInflater.from(this).inflate(R.layout.activity_news_deteil, null, false));
        setContentView(mLoadStatus);
        ButterKnife.bind(this);

        initView();

        initData();
    }

    private void initView() {
        mDeteilPresenter = new DeteilPresenter(this, this, mCompositeSubscription);

        mMainToolbar.setNavigationIcon(R.drawable.finish_select);
        mMainToolbar.setNavigationOnClickListener(v -> finish());
        setToolbar2(mMainToolbar, "详情", R.menu.menu_watched_video);
    }

    private void initData() {
        Intent intent = getIntent();
        mDate = intent.getStringExtra(HomeFragment.DATE);
        mUid = intent.getStringExtra(HomeFragment.UID);
        mDeteilPresenter.loadData(mDate, mUid);
    }

    @Override
    public void setPresenter(NewsDeteilContract.Presenter presenter) {
        mDeteilPresenter = checkNotNull(presenter);
    }

    @Override
    public void loadUrl(String url) {
        if (url.length() > 20) {
            mVsWeb.inflate();
            findWebViews();
            mDeteilPresenter.initWebView(mWebview, mWebviewProgressbar, url);
        } else {
            mVsVideo.inflate();
            findVideoViews();
            initVideo(url, true);
        }
    }

    private void findVideoViews() {
        mTitleTextView = ButterKnife.findById(this, R.id.tv_title);
        mDateTextView = ButterKnife.findById(this, R.id.tv_date);
        mDescription = ButterKnife.findById(this, R.id.tv_description);
        mBlurImageView = ButterKnife.findById(this, R.id.iv_blur);
        mBlurImageContainer = ButterKnife.findById(this, R.id.rl_blur_container);
        mYoukuPlayerView = ButterKnife.findById(this, R.id.youku_player);
    }

    private void findWebViews() {
        mWebviewProgressbar = ButterKnife.findById(this, R.id.webview_progressbar);
        mWebview = ButterKnife.findById(this, R.id.webview);
    }

    @Override
    public void updateState(int state) {
        mLoadStatus.updateView(state);
    }


    private YoukuPlayerView mYoukuPlayerView;
    private ImageView mBlurImageView;
    private RelativeLayout mBlurImageContainer;
    private TextView mTitleTextView;
    private TextView mDateTextView;
    private TextView mDescription;
    public static final String NEWS_DATE = "news_date";
    public static final String NEWS_NID = "news_nid";
    public static final String VIDEO_TITLE = "video_title";
    public static final String VIDEO_DATE = "video_date";
    public static final String VIDEO_DESCRIPTION = "video_description";
    public static final String VIDEO_BACKGROUND = "video_background";
    private YoukuBasePlayerManager mYoukuBasePlayerManager;
    private String mVid;
    private String mTitle;
    private String mBackgroundUrl;
    public YoukuPluginPlayer mPluginPlayer;
    private YoukuPlayer mYoukuPlayer;
    private int mVideoDurationMillis;
    private boolean mIsVideoStarted = false;
    private boolean mIsVideoEnded = false;
    private int mCurrentPlayTimeMills;
    private boolean mVideoMode;
    private void initVideo(String url, boolean flag) {
        mBlurImageView.setOnClickListener(v -> {mYoukuBasePlayerManager.onStart();LogUtil.ShowLog("dianji l ");});
        isVideo = flag;
        LogUtil.ShowLog(url);
        mVid = url;
        mTitle = getIntent().getStringExtra(HomeFragment.TITLE);
        mTitleTextView.setText(mTitle);
        mBackgroundUrl = getIntent().getStringExtra(VIDEO_BACKGROUND);
        String date = getIntent().getStringExtra(VIDEO_DATE);
        String description = getIntent().getStringExtra(VIDEO_DESCRIPTION);
        mDateTextView.setText(date);
        mDescription.setText(description);
        Glide.with(this).load(mBackgroundUrl).asBitmap().placeholder(R.color.black).transform(new BlurTransformation(this)).into(mBlurImageView);
        mYoukuBasePlayerManager = new YoukuBasePlayerManager(this) {
            @Override
            public void setPadHorizontalLayout() {
                // TODO Auto-generated method stub
            }

            @Override
            public void onInitializationSuccess(YoukuPlayer player) {
                // TODO Auto-generated method stub
                mPluginPlayer = new YoukuPluginPlayer(this, mediaPlayerDelegate, mBackgroundUrl);
                mPluginPlayer.setVideoStateCallback(new VideoStateListener());
                addPlugins(mPluginPlayer);
                mYoukuPlayer = player;
                if (mVid != null) {
                    mYoukuPlayer.playVideo(mVid);
                    mBlurImageContainer.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onSmallscreenListener() {
                // TODO Auto-generated method stub
                mMainToolbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFullscreenListener() {
                // TODO Auto-generated method stub
                mMainToolbar.setVisibility(View.GONE);
            }
        };
        mYoukuBasePlayerManager.onCreate();

        mYoukuPlayerView.setSmallScreenLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        mYoukuPlayerView.setFullScreenLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        mYoukuPlayerView.initialize(mYoukuBasePlayerManager);

    }
    private class VideoStateListener implements VideoStateCallback {
        @Override
        public void onProgressChanged(int currentTimeMillis) {
            mCurrentPlayTimeMills = currentTimeMillis;
        }

        @Override
        public void onVideoStart(int durationMillis) {
            mVideoDurationMillis = durationMillis;
            mIsVideoStarted = true;
            mIsVideoEnded = false;
        }

        @Override
        public void onVideoEnd() {
            mIsVideoStarted = false;
            mIsVideoEnded = true;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (isVideo) {
            mYoukuBasePlayerManager.onResume();
        }
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (isVideo) {
            mYoukuBasePlayerManager.onLowMemory();
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (isVideo) {
            mYoukuBasePlayerManager.onConfigurationChanged(newConfig);
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                mBlurImageContainer.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                mBlurImageContainer.requestLayout();
                ButterKnife.findById(this, R.id.v_divider).setVisibility(View.GONE);
                mDateTextView.setVisibility(View.GONE);
                mDescription.setVisibility(View.GONE);
                mTitleTextView.setVisibility(View.GONE);
            }
            if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                mBlurImageContainer.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.video_player_height)));
                mBlurImageContainer.requestLayout();
                ButterKnife.findById(this, R.id.v_divider).setVisibility(View.VISIBLE);
                mDateTextView.setVisibility(View.VISIBLE);
                mDescription.setVisibility(View.VISIBLE);
                mTitleTextView.setVisibility(View.VISIBLE);
            }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (isVideo) {
            LogUtil.ShowLog("resurme"+isVideo);
            mYoukuBasePlayerManager.onPause();
//            cacheWatchedVideo();
        }
    }
//
//    protected void cacheWatchedVideo() {
//        if (mIsVideoStarted || mIsVideoEnded) {
//            VideoCacheManager.INSTANCE.cacheWatchedVideo(mVid, mBackgroundUrl, mTitle
//                    , mVideoDurationMillis, mCurrentPlayTimeMills, mIsVideoEnded);
//        }
//    }
    @Override
    protected void onStart() {
        super.onStart();
        if (isVideo) {
            mYoukuBasePlayerManager.onStart();
        }
    }
    @Override
    public boolean onSearchRequested() {
        if (isVideo) {
            return mYoukuBasePlayerManager.onSearchRequested();
        }
        return super.onSearchRequested();
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (isVideo) {
            mYoukuBasePlayerManager.onStop();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isVideo) {
            mYoukuBasePlayerManager.onBackPressed();
        }
    }
    /**
     * 返回上一个webview
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(isVideo){
            if(keyCode == KeyEvent.KEYCODE_BACK){
                finish();
            }
            return true;
        }
        if(mWebview == null){
           return false;
        }
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebview.canGoBack()) {
                mWebview.goBack();
                mDeteilPresenter.loadData(mDate, mUid);
            }
            // goBack()表示返回WebView的上一页面
            return true;
    }
    @Override
    protected void onDestroy() {
        if (isVideo) {
            mYoukuBasePlayerManager.onDestroy();
        } else {
            if (mWebview != null) {
                mWebview.destroy();
            }
        }
        super.onDestroy();
    }
}
