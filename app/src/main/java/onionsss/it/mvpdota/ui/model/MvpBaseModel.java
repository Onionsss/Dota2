package onionsss.it.mvpdota.ui.model;

import android.content.Context;

import onionsss.it.mvpdota.BaseModel;

/**
 * 作者: Dream on 16/7/22 23:03
 * QQ:510278658
 * E-mail:510278658@qq.com
 */
public abstract class MvpBaseModel implements BaseModel{

    private Context context;

    public MvpBaseModel(Context context){
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
