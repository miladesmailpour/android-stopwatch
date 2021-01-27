package ca.i3th.stopwatchcode_ver02.AuxFun;

import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import ca.i3th.stopwatchcode_ver02.R;

public class CircleAnimate {
    private final String TAG = "CircleAnimate";
    private AnimationSet animSet;
    private Handler mHandler;
    private float firstPoint = 0.0f, prvPoint = 0.0f, newPoint = 0.0f, rate = 1.0f;
    private int i = -1;
    private String str = "";
    private boolean flag = true;
    private ImageView imageView, imageViewBK;
    private Thread thread;
    private int sleepTime = 1000;
    private RotateAnimation animRotate;
    //    public CircleAnimate(Handler mHandler, AnimationSet animSet) {
//        this.mHandler = mHandler;
//        this.animSet = animSet;
//    }
    public CircleAnimate(ImageView imageView) {
        this.mHandler = new Handler();
        this.animSet = new AnimationSet(true);
        this.imageView = imageView;
        this.imageViewBK = imageView;
    }

    public void startAnimate() {
        this.animSet.setInterpolator(new DecelerateInterpolator());
        this.animSet.setFillAfter(true);
        this.animSet.setFillEnabled(true);
        startThread();
    }

    public void pauseAnimate() {
        setFlag(true);
        try {
            Log.d(TAG, "stopAnimate: B>>>>>>>>>>>>>>>>>>>>>>>>>>" + thread.isInterrupted());
            thread.interrupt();
            Log.d(TAG, "stopAnimate: A>>>>>>>>>>>>>>>>>>>>>>>>>>" + thread.isInterrupted());
        } catch (Exception e) {
            e.printStackTrace();
        }
        counter(firstPoint);
        firstPoint = 0.0f;
        prvPoint = 0.0f;
        newPoint = 0.0f;


    }

    public void stopAnimate() {
        setFlag(true);
        try {
            Log.d(TAG, "stopAnimate: B>>>>>>>>>>>>>>>>>>>>>>>>>>" + thread.isInterrupted());
            thread.interrupt();
            imageView.clearAnimation();

            this.animSet = new AnimationSet(true);


            Log.d(TAG, "stopAnimate: A>>>>>>>>>>>>>>>>>>>>>>>>>>" + thread.isInterrupted());
        } catch (Exception e) {
            e.printStackTrace();
        }
        counter(firstPoint);
        firstPoint = 0.0f;
        prvPoint = 0.0f;
        newPoint = 0.0f;

    }

    private float counter(float currPoint) {
        prvPoint = currPoint;
        str = Float.toString(prvPoint);
        return currPoint + rate;
    }

    private void startThread() {
//        i = 0;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (i = i; ; i++) {
                    Log.d("TAG", "run: " + i);
                    if (flag) {
                        animRotate.reset();
                        break;
                    }
                    try {
                        Thread.sleep(sleepTime);
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                animRotate = new RotateAnimation(0.0f, 6.0f,
                                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                                Log.d("TAG", "run: current thread +++++++++++++++++++++++++++" + " : " + firstPoint + prvPoint + "->" + newPoint);
                                newPoint = counter(newPoint);
                                animSet.addAnimation(animRotate);
                                imageView.startAnimation(animSet);
                            }
                        });
                    } catch (Exception e) {
                    }
                }
            }
        });
        thread.start();
    }


    public boolean getFlag() {
        return this.flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean setPointerScoop(char scoop) {
        boolean bool = false;
        switch (scoop) {
            case 'h':
                sleepTime = 3600000;
                bool = true;
                break;
            case 'm':
                sleepTime = 60000;
                bool = true;
                break;
            case 's':
                sleepTime = 1000;
                bool = true;
                break;
            default:
                break;
        }
        return bool;
    }
}
