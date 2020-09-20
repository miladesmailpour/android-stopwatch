package ca.i3th.stopwatch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;

import java.util.ArrayList;
import java.util.List;

public class Stopwatch {

    private Chronometer chronometer;
    private Handler handler;
    private long timeMilliSecond, timeStart, timeBuffer, timeUpdate = 0L;
    private int minute, second, millisecond = 0;
    private List<String> list = new ArrayList<>();
    private boolean isActive, isRest;

    public Stopwatch(Chronometer chronometer, Handler handler) {
        this.chronometer = chronometer;
        this.handler = handler;
    }

    public void startTime() {
        if (!isActive){
            timeStart = SystemClock.uptimeMillis();
            handler.postDelayed(runnable, 0);
            chronometer.start();
            isActive = true;
            isRest = false;
        }
        else  {
            timeBuffer += timeMilliSecond;
            handler.removeCallbacks(runnable);
            chronometer.stop();
            isActive = false;
        }
    }

    public void stopTime() {
        if (!isActive) {
            timeBuffer = timeMilliSecond = timeStart = timeUpdate = 0L;
            minute = second = millisecond = 0;
            chronometer.setText("00:00:00");
        }
        else if (!isRest) {
            timeBuffer += timeMilliSecond;
            handler.removeCallbacks(runnable);
            chronometer.stop();
            timeBuffer = timeMilliSecond = timeStart = timeUpdate = 0L;
            minute = second = millisecond = 0;
            chronometer.setText("00:00:00");
            isActive = false;
        }
    }

    public String recordList() {
        String str = "";
        int i = 1;
        for (String l : list) {
            str += "Record #" + String.valueOf(i) + " : " + l + '\n';
            i++;
        }
        return str;
    }

    public String saveRecord() {
        if (list.size() > 9)
            list.clear();
        String lastR = (String.format("%02d", minute) + ":" + String.format("%02d", second)
                + ":" + String.format("%02d", millisecond));

        if (!lastR.equals("00:00:00")) {
            if (list.isEmpty())
                list.add(lastR);
            else if (!lastR.equals(list.get(list.size() - 1)))
                list.add(lastR);
        }
        return lastR;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            timeMilliSecond = SystemClock.uptimeMillis() - timeStart;
            timeUpdate = timeBuffer + timeMilliSecond;
            second = (int) (timeUpdate / 1000);
            minute = second / 60;
            second = second % 60;
            millisecond = (int) (timeUpdate % 100);
            chronometer.setText(String.format("%02d", minute) + ":" + String.format("%02d", second)
                    + ":" + String.format("%02d", millisecond));
            handler.postDelayed(this, 60);
        }
    };
}
