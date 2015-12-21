package cn.yan96in.sample.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.yan96in.sample.R;

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
//        findViewById(R.id.tabbar).setOnClickListener(this);
//        findViewById(R.id.tabbar).setOnClickListener(this);
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
        }
    }

    public void startActivity(Class<?> cls){
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }

}
