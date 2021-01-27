package ca.i3th.stopwatchcode_ver02.AuxFun;

import android.os.Handler;
import android.util.Log;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class CircleAnimate {
    private final String TAG = "CircleAnimate";
    private AnimationSet animSet;
    private Handler mHandler;
    private float firstPoint = 0.0f, prvPoint = 0.0f, newPoint = 0.0f, rate = 1.0f;
    private int i = -1;
    private String str = "";
    private boolean flag = true;
    private ImageView imageView;
    private Thread thread;
    private long sleepTime = 1000;
    private RotateAnimation animRotate;
    private char scoop = 's';
    //    public CircleAnimate(Handler mHandler, AnimationSet animSet) {
//        this.mHandler = mHandler;
//        this.animSet = animSet;
//    }
    public CircleAnimate(ImageView imageView, char scoop) {
        this.mHandler = new Handler();
        this.animSet = new AnimationSet(true);
        this.imageView = imageView;
        this.scoop = scoop;
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
//            Log.d(TAG, "stopAnimate: B>>>>>>>>>>>>>>>>>>>>>>>>>>" + thread.isInterrupted());
            thread.interrupt();
//            Log.d(TAG, "stopAnimate: A>>>>>>>>>>>>>>>>>>>>>>>>>>" + thread.isInterrupted());
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
        i = -1;
        try {

            thread.interrupt();
            imageView.clearAnimation();

            this.animSet = new AnimationSet(true);

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
//                        animRotate.reset();
                        break;
                    }
                    try {

                        Thread.sleep(setPointerScoop(scoop));
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                animRotate = new RotateAnimation(0.0f, 6.0f,
                                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//                                Log.d("TAG", "run: current thread +++++++++++++++++++++++++++" + " : " + firstPoint + prvPoint + "->" + newPoint);
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

    public long setPointerScoop(char scoop) {
        switch (scoop) {
            case 'h':
                sleepTime = 3600000;
                break;
            case 'm':
                sleepTime = 60000;
                break;
            case 's':
                sleepTime = 1000;
                break;
            default:
                break;
        }
        return sleepTime;
    }
}
