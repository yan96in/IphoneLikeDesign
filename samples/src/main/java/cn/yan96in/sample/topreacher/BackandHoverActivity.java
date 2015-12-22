package cn.yan96in.sample.topreacher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import cn.yan96in.ildesign.util.ITopReacher;
import cn.yan96in.sample.R;


public class BackandHoverActivity extends Activity {

    private ITopReacher mITopReacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_and_hover);
        mITopReacher = new ITopReacher(this);
        mITopReacher.makeHoverView(ITopReacher.Position.RIGHT);

        findViewById(R.id.switch_hover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mITopReacher.switchHover();
            }
        });
        findViewById(R.id.switch_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mITopReacher.switchBack();
            }
        });

    }
}
