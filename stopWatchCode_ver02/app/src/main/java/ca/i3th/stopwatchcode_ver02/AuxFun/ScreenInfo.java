package ca.i3th.stopwatchcode_ver02.AuxFun;

import android.app.Activity;
import android.content.res.Configuration;
import android.util.DisplayMetrics;


public class ScreenInfo {

    private DisplayMetrics displayMetric;
    private Activity activity;

    public ScreenInfo(DisplayMetrics dm, Activity activity) {
        this.displayMetric = dm;
        this.activity = activity;
    }

    public double size() {
        this.activity.getWindowManager().getDefaultDisplay().getMetrics(this.displayMetric);
        double x = Math.pow(this.displayMetric.widthPixels / this.displayMetric.xdpi, 2);
        double y = Math.pow(this.displayMetric.heightPixels / this.displayMetric.ydpi, 2);
        double screenInches = Math.sqrt(x + y);

        return ((double) Math.round(screenInches * 10) / 10);
    }

    public char screenLayout() {
        char size;
        int screenSize = this.activity.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        switch (screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                size = 'L'; // Large Screen
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                size = 'M'; // Normal Screen
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                size = 'S'; // Small Screen
                break;
            default:
                size = 'N'; // Neither one
        }
        return size;
    }

    public int density() {
        DisplayMetrics metrics = this.activity.getResources().getDisplayMetrics();
        return (int)(metrics.density * 160f);
    }

    public int sizePicker() {
        int size = -1;
        String s = String.valueOf(size());
        switch (s) {
            case "3.9":
                size = 0;
                break;
            case "4.0":
                size = 1;
                break;
            case "4.7":
                size = 2;
                break;
            case "5.0":
                size = 3;
                break;
            case "5.1":
                size = 4;
                break;
            case "5.2":
                size = 5;
                break;
            case "5.5":
                size = 6;
                break;
            case "5.7":
                size = 7;
                break;
            case "5.8":
                size = 8;
                break;
            case "6.0":
                size = 9;
                break;
            case "6.2":
                size = 10;
                break;
            default:
        }
        return size;
    }

    public DisplayMetrics getDisplayMetric() {
        return displayMetric;
    }

    public void setDisplayMetric(DisplayMetrics dm) {
        this.displayMetric = dm;
    }
}
