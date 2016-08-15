package onionsss.it.mvpdota.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youku.player.VideoQuality;

import butterknife.ButterKnife;
import onionsss.it.mvpdota.R;
import onionsss.it.mvpdota.utils.VideoQualityUtil;

/**
 * Created by dear33 on 2016/7/10.
 */
public class VideoQualityTextView extends LinearLayout {
    private Context mContext;
    private TextView mQualityTextView;
    private View mDivider;
    private VideoQuality mQuality;

    public VideoQualityTextView(Context context) {
        this(context, null);
    }

    public VideoQualityTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoQualityTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;

        LayoutInflater.from(context).inflate(R.layout.layout_video_quality, this);
        mQualityTextView = ButterKnife.findById(this, R.id.tv_quality);
        mDivider = ButterKnife.findById(this, R.id.v_divider);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.VideoQualityTextView, 0, 0);
        int textColor = ta.getColor(R.styleable.VideoQualityTextView_vqt_text_color, 0x8affffff);
        int backgroundColor = ta.getColor(R.styleable.VideoQualityTextView_vqt_text_view_background, 0xbf000000);
        ta.recycle();

        mQualityTextView.setTextColor(textColor);
        mQualityTextView.setBackgroundColor(backgroundColor);
    }

    public void setTextColor(int resId) {
        mQualityTextView.setTextColor(mContext.getResources().getColor(resId));
    }

    public void setQuality(VideoQuality videoQuality) {
        mQuality = videoQuality;
        int resId = VideoQualityUtil.getQualityResId(videoQuality);
        mQualityTextView.setText(resId);
    }

    public void setDividerVisibility(boolean show) {
        if (show) {
            mDivider.setVisibility(VISIBLE);
        } else {
            mDivider.setVisibility(GONE);
        }
    }

    public VideoQuality getQuality() {
        return mQuality;
    }
}
