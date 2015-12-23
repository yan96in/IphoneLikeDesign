package cn.yan96in.sample.topreacher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import cn.yan96in.ildesign.util.TopReacher;
import cn.yan96in.sample.R;


public class BackandHoverActivity extends Activity {

    private TopReacher mTopReacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_and_hover);
        mTopReacher = new TopReacher(this);
        mTopReacher.makeHoverView(TopReacher.Position.RIGHT);

        findViewById(R.id.switch_hover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopReacher.switchHover();
            }
        });
        findViewById(R.id.switch_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopReacher.switchBack();
            }
        });

    }
}
