package onionsss.it.mvpdota.ui.Strategy;

import java.util.ArrayList;
import java.util.List;

import onionsss.it.mvpdota.bean.TitleAndPage;
import onionsss.it.mvpdota.ui.base.BaseTabFragment;
import onionsss.it.mvpdota.utils.NavUtil;

/**
 * 作者：张琦 on 2016/8/15 14:52
 */
public class StrategyFragment extends BaseTabFragment{

    @Override
    protected void childView() {

    }

    @Override
    protected void childData() {

    }

    @Override
    public List<TitleAndPage> getList() {
        List<TitleAndPage> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(new TitleAndPage(new StrategyPage(mActivity,NavUtil.strategyTypeList[i],mCompositeSubscription), NavUtil.strategyTypeList[i]));
        }
        return list;
    }


}
