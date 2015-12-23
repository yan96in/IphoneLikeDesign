package cn.yan96in.ildesign.util;


import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.lang.reflect.Method;

import cn.yan96in.ildesign.R;
import cn.yan96in.ildesign.listener.BackAnimationListener;
import cn.yan96in.ildesign.listener.HoverAnimationListener;

public class TopReacher {
    /**
     * position of HoverView
     * */
    public enum Position {
        LEFT, CENTER, RIGHT
    }

    private Context mContext;
    private ViewGroup mRootView;
    private View mMoveView;
    private View mContentView;
    private FrameLayout mFloatLayout;
    private ImageView mHoverView = null;
    private boolean mHasCustomView = false;
    private int mDrawablePull = 0;
    private int mDrawablePush = 0;

    private ObjectAnimator mInAnimator = null;
    private ObjectAnimator mOutAnimator = null;

    private BackAnimationListener mBackListener = null;
    private HoverAnimationListener mHoverListener = null;

    private float startY;
    private float endY;
    private float halfWindow;

    private final static String TAG = "Reachability";
    private final static String STATUS_BAR = "statusbar";
    private final static String STATUS_BAR_NAME = "android.app.StatusBarManager";
    private final static String STATUS_BAR_OPEN = "expandNotificationsPanel";
    private final static String STATUS_BAR_OPEN_OLD = "expand";
    private final static int DURATION_TIME = 300;
    private final static int MARGIN = 48;
    private final static float THRESHOLD = 50f;

    /**
     * Constructor
     * @param context context
     * */
    public TopReacher(Context context) {
        this.mContext = context;
        mRootView = ((ViewGroup)((Activity)mContext).getWindow().getDecorView());
        mContentView = mRootView.findViewById(android.R.id.content);
        mMoveView = mRootView.getChildAt(0);
        mFloatLayout = new FrameLayout(mContext);
        halfWindow = getHalfWindow();
        mBackListener = new BackAnimationListener(false);
        mHoverListener = new HoverAnimationListener(false);
        initHoverView();
    }

    private float getHalfWindow() {
        WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return (size.y/5)*2;
    }

    private void initHoverView() {
        if (mHoverView == null) {
            mHoverView = new ImageView(mContext);
            mHoverView.setImageResource(R.drawable.back_pull);
            mHoverView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            mHoverView.setBackgroundResource(R.drawable.button_selector);
            mHoverView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchBack();
                }
            });
        }
    }

    /**
     *<p> You can set custom HoverView only ImageView.<br>
     *     If this method use, should call before makeHoverView</p>
     * @param view ImageView
     * @param pullResource pull image resource id
     * @param pushResource push image resource id
     * */
    public void setHoverView(ImageView view, int pullResource, int pushResource) {
        mHasCustomView = true;
        mHoverView = view;
        mDrawablePull = pullResource;
        mDrawablePush = pushResource;
        mHoverView.setImageResource(mDrawablePull);
        mHoverView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchBack();
            }
        });
    }

    /**
     * HoverView add to FloatLayout
     * @param position LEFT, CENTER, RIGHT
     * */
    public void makeHoverView(Position position) {

        int gravity;
        if (Position.LEFT.equals(position)) {
            gravity = Gravity.BOTTOM|Gravity.LEFT;
        } else if (Position.RIGHT.equals(position)) {
            gravity = Gravity.BOTTOM|Gravity.RIGHT;
        } else {
            gravity = Gravity.BOTTOM| Gravity.CENTER;
        }

        FrameLayout.LayoutParams frameParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        frameParams.gravity = gravity;
        mFloatLayout.setLayoutParams(frameParams);

        FrameLayout.LayoutParams wrapParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        wrapParams.gravity = gravity;
        wrapParams.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);

        mFloatLayout.addView(getHoverView(), wrapParams);
        mFloatLayout.setBackgroundColor(Color.argb(0, 255, 255, 255));
        mRootView.addView(mFloatLayout);
        switchHover();
    }

    /**
     * Set image to RootView
     * @param resourceId image resource id
     * */
    public void setBackImageResource(int resourceId) {
        mRootView.setBackgroundResource(resourceId);
    }

    /**
     * Set color of RootView
     * @param color color id
     * */
    public void setBackColor(int color) {
        mRootView.setBackgroundColor(color);
    }

    /**
     * Can touch to RootView
     * @param bool true: can touch. false: can't touch.
     * */
    public void canTouchableBackView(boolean bool) {
        if (bool) {
            setListener();
        }
    }

    /**
     * <p>show status bar <br>
     * requirement AndroidManifest.xml <uses-permission android:name="android.permission.EXPAND_STATUS_BAR" /></p>
     * */
    public void showStatusBar() {
        String open = STATUS_BAR_OPEN;
        if (Build.VERSION.SDK_INT < 17) {
            open = STATUS_BAR_OPEN_OLD;
        }
        try {
            Object service = mContext.getSystemService(STATUS_BAR);
            Class clazz = Class.forName(STATUS_BAR_NAME);
            Method method = clazz.getMethod(open);
            method.invoke(service);
        } catch (Exception e) {
            Log.w(TAG, "Not permission. You write to uses-permission STATUS_BAR in manifest");
            e.printStackTrace();
        }
    }

    /**
     * <p>Move MoveView.<br>
     *     Animation does not overlap.</p>
     * */
    public void switchBack() {
        if (mBackListener.isLocked()) {
            return;
        }
        if (mBackListener.isNear()) {
            push();
        } else {
            pull();
        }
    }

    /**
     * <p>Move HoverView.<br>
     *     Animation does not overlap.</p>
     * */
    public void switchHover() {
        if (mHoverListener.isLocked()) {
            return;
        }
        if (isSetCustomAnimation()) {
            if (mHoverListener.isShown()) {
                customSlideOut();
            } else {
                customSlideIn();
            }
        } else {
            if (mHoverListener.isShown()) {
                slideOut();
            } else {
                slideIn();
            }
        }
    }

    private boolean isSetCustomAnimation() {
        if (mInAnimator != null && mOutAnimator != null) {
            return true;
        }
        Log.i(TAG, "Not set custom animation.");
        return false;
    }

    private void pull() {
        if (mHasCustomView && mDrawablePush != 0) {
            getHoverView().setImageResource(mDrawablePush);
        } else {
            getHoverView().setImageResource(R.drawable.back_push);
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(mMoveView, "translationY", 0f, halfWindow);
        animator.setDuration(DURATION_TIME);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(mBackListener);
        animator.start();
        mBackListener.setDistanceStatus(true);
    }

    private void push() {
        if (mHasCustomView && mDrawablePull != 0) {
            getHoverView().setImageResource(mDrawablePull);
        } else {
            getHoverView().setImageResource(R.drawable.back_pull);
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(mMoveView, "translationY", halfWindow, 0f);
        animator.setDuration(DURATION_TIME);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(mBackListener);
        animator.start();
        mBackListener.setDistanceStatus(false);
    }

    private void slideIn() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(getHoverView(), "translationY", 300f, 0f);
        animator.setDuration(DURATION_TIME);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(mHoverListener);
        animator.start();
        mHoverListener.setShowStatus(true);
    }

    private void slideOut() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(getHoverView(), "translationY", 0f, 300f);
        animator.setDuration(DURATION_TIME);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(mHoverListener);
        animator.start();
        mHoverListener.setShowStatus(false);
    }


    /**
     * <p>You can set custom animation to HoverView slide in.<br>
     * It is not necessary to set the target of the animation  </p>
     * @param duration Animation duration
     * @param interpolator Animatoin interpolator
     * @param holders PropertyValueHolder...
     * */
    public void setCustomSlideInAnimation(int duration, TimeInterpolator interpolator, PropertyValuesHolder... holders) {
        if (mInAnimator == null) {
            mInAnimator = ObjectAnimator.ofPropertyValuesHolder(getHoverView(), holders);
            mInAnimator.setDuration(duration);
            mInAnimator.setInterpolator(interpolator);
            mInAnimator.addListener(mHoverListener);
        }
    }

    /**
     * <p>You can set custom animation to HoverView slide out.<br>
     * It is not necessary to set the target of the animation  </p>
     * @param duration Animation duration
     * @param interpolator Animatoin interpolator
     * @param holders PropertyValueHolder...
     * */
    public void setCustomSlideOutAnimation(int duration, TimeInterpolator interpolator, PropertyValuesHolder... holders) {
        if (mOutAnimator == null) {
            mOutAnimator = ObjectAnimator.ofPropertyValuesHolder(getHoverView(), holders);
            mOutAnimator.setDuration(duration);
            mOutAnimator.setInterpolator(interpolator);
            mOutAnimator.addListener(mHoverListener);
        }
    }

    private void customSlideIn() {
        if (mInAnimator == null) {
            Log.w(TAG, "Not set slide in animation");
            return;
        }
        mInAnimator.start();
        mHoverListener.setShowStatus(true);
    }

    private void customSlideOut() {
        if (mOutAnimator == null) {
            Log.w(TAG, "Not set slide out animation");
            return;
        }
        mOutAnimator.start();
        mHoverListener.setShowStatus(false);
    }

    private void setListener() {
        mContentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;//RootView unreachable
            }
        });
        mRootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    startY = event.getY();
                } else if (MotionEvent.ACTION_UP == event.getAction()) {
                    endY = event.getY();

                    if ((endY - startY) > THRESHOLD) {
                        showStatusBar();
                    }
                    if ((startY - endY) > THRESHOLD) {
                        switchBack();
                    }
                }
                return false;
            }
        });
    }

    /**
     * Getter of HoverView
     * */
    private ImageView getHoverView() {
        return this.mHoverView;
    }
}
