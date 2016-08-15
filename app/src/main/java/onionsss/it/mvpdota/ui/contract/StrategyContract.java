package onionsss.it.mvpdota.ui.contract;

import onionsss.it.mvpdota.BaseView;
import onionsss.it.mvpdota.bean.StrategyList;
import onionsss.it.mvpdota.ui.presenter.BasePresenter;

/**
 * 作者：张琦 on 2016/8/15 15:57
 */
public class StrategyContract {
    public interface View extends BaseView<Presenter> {
        void updateState(int state);
        void showData(StrategyList bean);
    }

    public interface Presenter extends BasePresenter {
        void loadData(String type);
    }
}
