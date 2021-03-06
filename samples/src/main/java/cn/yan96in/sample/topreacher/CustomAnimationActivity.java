package cn.yan96in.sample.topreacher;

import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;


import cn.yan96in.ildesign.util.TopReacher;
import cn.yan96in.sample.R;


public class CustomAnimationActivity extends Activity {

    private TopReacher mTopReacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_animation);

        ImageView view = new ImageView(this);
        view.setBackgroundResource(R.drawable.custom_button_selector);
        view.setScaleType(ImageView.ScaleType.CENTER);

        mTopReacher = new TopReacher(this);
        mTopReacher.canTouchableBackView(true);
        mTopReacher.setBackImageResource(R.drawable.tiles);
        // Should call before makeHoverView!
        mTopReacher.setHoverView(view, android.R.drawable.ic_partial_secure, android.R.drawable.ic_secure);
        mTopReacher.makeHoverView(TopReacher.Position.CENTER);
        mTopReacher.setCustomSlideInAnimation(1000, new AnticipateOvershootInterpolator(), fromLeftAnimation());
        mTopReacher.setCustomSlideOutAnimation(1000, new AnticipateOvershootInterpolator(), toRightAnimation());

        findViewById(R.id.switch_hover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopReacher.switchHover();
            }
        });
    }

    private PropertyValuesHolder[] fromLeftAnimation() {

        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat( "translationX", -getWidth(), 0f );
        PropertyValuesHolder holderR = PropertyValuesHolder.ofFloat("rotation", 0f, 360f);
        PropertyValuesHolder[] holders = {holderX, holderR};
        return holders;
    }

    private PropertyValuesHolder[] toRightAnimation() {

        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat( "translationX", 0f, getWidth() );
        PropertyValuesHolder holderR = PropertyValuesHolder.ofFloat("rotation", 0f, 360f);
        PropertyValuesHolder[] holders = {holderX, holderR};
        return holders;
    }

    private float getWidth() {
        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return ((size.x/5)*3);
    }
}
