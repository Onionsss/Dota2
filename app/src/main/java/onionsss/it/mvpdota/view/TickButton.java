package onionsss.it.mvpdota.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

import onionsss.it.mvpdota.R;


/**
 * Created by Administrator on 2016/7/21.
 */
public class TickButton extends Button {
    private int mSelectBgId;
    private int mUnSelectBgId;

    private boolean mSelected;

    public TickButton(Context context) {
        this(context, null);
    }

    public TickButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TickButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TickButton, 0, 0);
        mUnSelectBgId = ta.getResourceId(R.styleable.TickButton_tb_unselect_background, R.drawable.shape_tick_empty);
        mSelectBgId = ta.getResourceId(R.styleable.TickButton_tb_select_background, R.drawable.ic_tick_filled);
        ta.recycle();

        setSelected(false);
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
        if (selected) {
            setBackgroundResource(mSelectBgId);
        } else {
            setBackgroundResource(mUnSelectBgId);
        }
    }
}
