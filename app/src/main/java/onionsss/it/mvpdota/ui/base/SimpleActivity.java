package onionsss.it.mvpdota.ui.base;

import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * 作者：张琦 on 2016/8/13 19:29
 */
public abstract class SimpleActivity extends ToolbarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        initView();

        initData();
    }

    protected void initData(){}

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected abstract void initView();

    public abstract int getLayoutId();



}
