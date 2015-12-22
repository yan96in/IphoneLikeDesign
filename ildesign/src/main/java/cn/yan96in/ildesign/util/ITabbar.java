package cn.yan96in.ildesign.util;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import cn.yan96in.ildesign.R;

public class ITabbar {
    TabActivity context;

    public ITabbar(TabActivity context) {
        this.context = context;
    }

    public void addTab(String labelId, int drawableId, Class<?> c)
    {
        TabHost tabHost = context.getTabHost();
        Intent intent = new Intent(context, c);
        TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);
        View tabIndicator = LayoutInflater.from(context).inflate(R.layout.tab_indicator, context.getTabWidget(), false);
        TextView title = (TextView) tabIndicator.findViewById(R.id.title);
        title.setText(labelId);
        ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
        icon.setImageResource(drawableId);
        spec.setIndicator(tabIndicator);
        spec.setContent(intent);
        tabHost.addTab(spec);
    }
}
