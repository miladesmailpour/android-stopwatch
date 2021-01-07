package ca.i3th.stopwatchcode_ver02.AuxFun;

import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class CircleAnimate {
    private AnimationSet animSet;
    private Handler mHandler;
    private float prvPoint = 0.0f, newPoint = 1.0f, rate = 1.0f;
    private int i;
    private String str = "";
    private boolean flag = false;
    private ImageView imageView;
    //    public CircleAnimate(Handler mHandler, AnimationSet animSet) {
//        this.mHandler = mHandler;
//        this.animSet = animSet;
//    }
    public CircleAnimate(ImageView imageView) {
        this.mHandler = new Handler();
        this.animSet = new AnimationSet(true);
        this.imageView = imageView;
    }

    public void startAnimate() {
        this.animSet.setInterpolator(new DecelerateInterpolator());
        this.animSet.setFillAfter(true);
        this.animSet.setFillEnabled(true);
        runThread();
    }
    public void resumeAnimate(){
        runThread();
    }
    private float counter(float currPoint) {
        prvPoint = currPoint;
        str = Float.toString(prvPoint);
        return currPoint + rate;
    }
    private void runThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (i = 0; ; i++) {
                    Log.d("TAG", "run: " + i);
                    if (flag) {
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                final RotateAnimation animRotate = new RotateAnimation(0.0f, 6.0f,
                                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                                Log.d("TAG", "run: " + " : " + prvPoint + "->" + newPoint);
                                newPoint = counter(newPoint);

                                animSet.addAnimation(animRotate);
//                                ivCircle.startAnimation(animSet);
//                                View ivLine = imageView;
                                imageView.startAnimation(animSet);
                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();
    }
    public boolean getFlag() {
        return this.flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
