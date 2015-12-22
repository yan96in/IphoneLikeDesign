package cn.yan96in.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.yan96in.sample.topreacher.TopReacherActivity;
import cn.yan96in.sample.ui.IEditTextActivity;
import cn.yan96in.sample.ui.IndexableListViewActivity;
import cn.yan96in.sample.ui.TabBarActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
         findViewById(R.id.tabbar).setOnClickListener(this);
        findViewById(R.id.indexable_listview).setOnClickListener(this);
        findViewById(R.id.i_edit_text).setOnClickListener(this);
        findViewById(R.id.i_top_reacher).setOnClickListener(this);
//        findViewById(R.id.tabbar).setOnClickListener(this);
//        findViewById(R.id.tabbar).setOnClickListener(this);
//        findViewById(R.id.tabbar).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tabbar:
                startActivity(TabBarActivity.class);
                break;
            case R.id.indexable_listview:
                startActivity(IndexableListViewActivity.class);
                break;
            case R.id.i_edit_text:
                startActivity(IEditTextActivity.class);
            case R.id.i_top_reacher:
                startActivity(TopReacherActivity.class);
        }
    }

    public void startActivity(Class<?> cls){
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }

}
