package onionsss.it.mvpdota.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.bigkoo.convenientbanner.ConvenientBanner;


/**
 * 作者：张琦 on 2016/6/15 10:12
 */
public class MyHeadViewPager extends ConvenientBanner {

    private int mStartX;
    private int mStartY;

    public MyHeadViewPager(Context context) {
        super(context);
    }

    public MyHeadViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mStartX = (int) ev.getX();
                mStartY = (int) ev.getY();
            break;
            case MotionEvent.ACTION_MOVE:
                int currX = (int) ev.getX();
                int currY = (int) ev.getY();

                int dx = currX - mStartX;
                int dy = currY - mStartY;
                if(Math.abs(dx) > Math.abs(dy)){
                    if(dx > 0){
                        /**
                         * 向左滑
                         */
                        if(getCurrentItem() == 0){
                            getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    }else{
                        /**
                         * 向右滑
                         */
                        int count = getChildCount();
                        if(getCurrentItem() == count - 1){
                            getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    }
                }else{
                    /**
                     * 上下
                     */
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
