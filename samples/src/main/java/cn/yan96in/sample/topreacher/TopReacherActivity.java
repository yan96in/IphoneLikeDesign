package cn.yan96in.sample.topreacher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cn.yan96in.ildesign.util.ITopReacher;
import cn.yan96in.sample.R;

public class TopReacherActivity extends Activity {

    private ITopReacher mITopReacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mITopReacher = new ITopReacher(this);
        mITopReacher.setBackImageResource(R.drawable.tiles);
        mITopReacher.canTouchableBackView(true);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mITopReacher.switchBack();
            }
        });
        findViewById(R.id.fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopReacherActivity.this, DrawerActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.hover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopReacherActivity.this, BackandHoverActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopReacherActivity.this, CustomAnimationActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
