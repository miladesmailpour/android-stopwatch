package ca.i3th.stopwatchcode_ver02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;

import java.util.HashMap;

import ca.i3th.stopwatchcode_ver02.AuxFun.ScreenInfo;
import ca.i3th.stopwatchcode_ver02.Fragments.Btns;
import ca.i3th.stopwatchcode_ver02.Fragments.Circles;
import ca.i3th.stopwatchcode_ver02.Fragments.Lap;

public class MainActivity extends AppCompatActivity {
    private String TAG ="MainActivity";
    private ScreenInfo screenInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        String h = "";
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.mainContainer_top, new Lap())
                    .add(R.id.mainContainer_middle, new Circles())
                    .add(R.id.mainContainer_bottom, new Btns())
                    .setReorderingAllowed(true)
                    .commit();
        }


        screenInfo = new ScreenInfo(new DisplayMetrics(), this);
        Toast.makeText(this, screenInfo.size() + " : " + screenInfo.screenLayout(), Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate: -------------------------------> : " + screenInfo.size()
                + " : " + screenInfo.screenLayout() + " : " + screenInfo.density()
                + " : " +  screenInfo.sizePicker());

    }
    public  HashMap<String, Object> screenInfo(int option) {
        String[] op = {"0 : (double)Screen Size", "1 : (char)Screen Layout", "0 : (int)Screen Density", "0 : (int)Screen Picker"};
        HashMap<String, Object> result = new HashMap<>();
        ScreenInfo screenInfo = new ScreenInfo(new DisplayMetrics(), MainActivity.this);
        switch (option) {
            case 0:
                result.put("Screen Size", screenInfo.screenLayout());
                break;
            case 1:
                result.put("Screen Layout", screenInfo.screenLayout());
                break;
            case 2:
                result.put("Screen Density", screenInfo.density());
                break;
            case 3:
                result.put("Screen SizePicker", screenInfo.sizePicker());
                break;
            default:
                result.put("List of options", op);
        }
        return result;
    }
}