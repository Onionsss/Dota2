package onionsss.it.mvpdota.ui.base;

import android.content.Context;
import android.view.View;

import onionsss.it.mvpdota.BaseView;

/**
 * 作者：张琦 on 2016/8/15 14:35
 */
public abstract class BasePage<V extends BaseView> {

    public Context mContext;
    public View mRootVieww;

    public BasePage(Context context){
        mContext = context;
        mRootVieww = initView();
    }

    public abstract View initView();

    public abstract Object initData();
}
