package cn.yan96in.sample.ui;

import android.app.TabActivity;
import android.os.Bundle;

//import cn.yan96in.sample.R;
import cn.yan96in.ildesign.widget.ITabbar;
import cn.yan96in.sample.R;
import cn.yan96in.sample.tabbar.ArrowsActivity;
import cn.yan96in.sample.tabbar.OptionsActivity;

public class TabBarActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_bar);
        setTabs() ;
    }
    private void setTabs()
    {
        new ITabbar(this).addTab("Home", R.drawable.tab_home, ArrowsActivity.class);
        new ITabbar(this).addTab("Search", R.drawable.tab_search, OptionsActivity.class);
        new ITabbar(this).addTab("Home", R.drawable.tab_home, ArrowsActivity.class);
        new ITabbar(this).addTab("Search", R.drawable.tab_search, OptionsActivity.class);
    }

}
