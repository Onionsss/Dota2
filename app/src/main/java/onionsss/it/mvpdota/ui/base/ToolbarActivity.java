package onionsss.it.mvpdota.ui.base;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

/**
 * 作者：张琦 on 2016/8/13 19:17
 */
public class ToolbarActivity extends BaseActivity{

    public ActionBar setToolbar(Toolbar toolbar, String title, int menuId){
        if(toolbar == null) return null;
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        if(menuId == -1)
            return getSupportActionBar();
        toolbar.inflateMenu(menuId);
        return getSupportActionBar();
    }

    public void setToolbar2(Toolbar toolbar, String title, int menuId){
        if(toolbar == null) return;
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(title);
        if(menuId == -1)
            return;
        toolbar.inflateMenu(menuId);
    }
}
