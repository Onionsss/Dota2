package onionsss.it.mvpdota.bean;

import onionsss.it.mvpdota.ui.base.BasePage;

/**
 * 作者：张琦 on 2016/8/15 15:16
 */
public class TitleAndPage {
    public BasePage page;

    public TitleAndPage(BasePage page, String title) {
        this.page = page;
        this.title = title;
    }

    public String title;

}
