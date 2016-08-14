package onionsss.it.mvpdota.ui.contract;

import onionsss.it.mvpdota.ui.presenter.BasePresenter;
import onionsss.it.mvpdota.BaseView;
import onionsss.it.mvpdota.bean.NewsList;
import onionsss.it.mvpdota.view.MyHeadViewPager;

/**
 * 作者：张琦 on 2016/8/14 10:19
 */
public class HomeContract {
    public interface View extends BaseView<Presenter> {
        void updateState(int state);
        void setData(NewsList data, boolean isFlush);
        void swipeClose();
        void showSnackBar(int content);
        void refresh(NewsList data);
        void loadMore(NewsList data);
        void loadMoreSuccess(boolean flag);
    }

    public interface Presenter extends BasePresenter {
        void loadData(boolean isFlush);
        void loadMore();
        void refresh();
        void loadHead(NewsList data, MyHeadViewPager home_head_vp);
    }
}
