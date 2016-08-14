package onionsss.it.mvpdota.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import onionsss.it.mvpdota.ui.activity.MainActivity;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 作者：张琦 on 2016/8/14 08:52
 */
public abstract class BaseFragment extends Fragment {

    public Activity mActivity;
    public MainActivity mMainAct;
    public CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    private View mRootView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        if(mActivity instanceof MainActivity){
            mMainAct = (MainActivity) mActivity;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater,container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRootView = getView();
        ButterKnife.bind(this,mRootView);
        initData(mRootView);
    }

    public void initData(View rootView) {}

    public abstract View initView(LayoutInflater inflater, ViewGroup container);

    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        return this.mCompositeSubscription;
    }


    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        this.mCompositeSubscription.add(s);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCompositeSubscription.unsubscribe();
        ButterKnife.unbind(this);

    }
}
