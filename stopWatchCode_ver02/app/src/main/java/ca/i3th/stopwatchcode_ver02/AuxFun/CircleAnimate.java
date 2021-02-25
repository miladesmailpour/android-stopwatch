package ca.i3th.stopwatchcode_ver02.AuxFun;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.google.android.gms.maps.model.Circle;

import ca.i3th.stopwatchcode_ver02.R;

public class CircleAnimate {
    private final String TAG = "CircleAnimate";
    private AnimationSet animSet;
    private Handler mHandler;
    private float firstPoint = 0.0f, prvPoint = 0.0f, newPoint = 0.0f, rate = 1.0f;
    private int i = -1, counter_1 = 0;
    private String str = "";
    private boolean flag = true, sw = true;
    private ImageView imageView;
    private ImageView[] qImageView;
    private Thread thread;
    private long sleepTime = 1000;
    private RotateAnimation animRotate;
    private char scoop = 's';


    public CircleAnimate(ImageView imageView, char scoop, ImageView[] qList) {
        this.mHandler = new Handler();
        this.animSet = new AnimationSet(true);
        this.imageView = imageView;
        this.qImageView = qList;
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

//                    Log.d("TAG", "run: " + i);
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


                                if (scoop == 's') {
                                    setPointerPosition(counter_1++);
                                }
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
    private int setPointerPosition(int counter) {
        int q = 0;
        if (counter >= 60) {
            q = counter % 60;
        } else {
            q = counter;
        }
        if (q >= 0 && q < 14) {
            qImageView[0].setVisibility(View.INVISIBLE);
            qImageView[1].setVisibility(View.VISIBLE);
            qImageView[2].setVisibility(View.VISIBLE);
            qImageView[3].setVisibility(View.VISIBLE);

//            Animation fadeIn = new AlphaAnimation(0, 1);
//
//            fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
//            fadeIn.setDuration(15000);
//
////            Animation fadeOut = new AlphaAnimation(1, 0);
////            fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
////            fadeOut.setStartOffset(15000);
////            fadeOut.setDuration(15000);
//
//            AnimationSet animation = new AnimationSet(false); //change to false
//            animation.addAnimation(fadeIn);
//            animation.addAnimation(fadeOut);

//            qImageView[0].setAnimation(animation);
//            qImageView[1].setAnimation(animation);
//            qImageView[2].setAnimation(animation);
//            qImageView[3].setAnimation(animation);
            return 1;
        } else if (q >= 14 && q < 29) {
            qImageView[0].setVisibility(View.VISIBLE);
            qImageView[1].setVisibility(View.INVISIBLE);
            qImageView[2].setVisibility(View.VISIBLE);
            qImageView[3].setVisibility(View.VISIBLE);
            return 2;
        } else if (q >= 29 && q < 44) {
            qImageView[0].setVisibility(View.VISIBLE);
            qImageView[1].setVisibility(View.VISIBLE);
            qImageView[2].setVisibility(View.INVISIBLE);
            qImageView[3].setVisibility(View.VISIBLE);
            return 3;
        } else if (q >= 44 && q < 60) {
            qImageView[0].setVisibility(View.VISIBLE);
            qImageView[1].setVisibility(View.VISIBLE);
            qImageView[2].setVisibility(View.VISIBLE);
            qImageView[3].setVisibility(View.INVISIBLE);
            return 4;
        }
        return 0;
    }


}
