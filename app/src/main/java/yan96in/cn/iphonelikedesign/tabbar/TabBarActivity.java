package yan96in.cn.iphonelikedesign.tabbar;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import yan96in.cn.iphonelikedesign.R;


public class TabBarActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_bar);
        setTabs() ;
    }
    private void setTabs()
    {
        addTab("Home", R.drawable.tab_home, ArrowsActivity.class);
        addTab("Search", R.drawable.tab_search, OptionsActivity.class);

        addTab("Home", R.drawable.tab_home, ArrowsActivity.class);
        addTab("Search", R.drawable.tab_search, OptionsActivity.class);
    }

    private void addTab(String labelId, int drawableId, Class<?> c)
    {
        TabHost tabHost = getTabHost();
        Intent intent = new Intent(this, c);
        TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);

        View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
        TextView title = (TextView) tabIndicator.findViewById(R.id.title);
        title.setText(labelId);
        ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
        icon.setImageResource(drawableId);

        spec.setIndicator(tabIndicator);
        spec.setContent(intent);
        tabHost.addTab(spec);
    }
}
