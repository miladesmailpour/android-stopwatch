package ca.i3th.stopwatchcode_ver02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import ca.i3th.stopwatchcode_ver02.Fragments.Btns;
import ca.i3th.stopwatchcode_ver02.Fragments.Circles;
import ca.i3th.stopwatchcode_ver02.Fragments.Lap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.mainContainer_top, new Lap())
                    .add(R.id.mainContainer_middle, new Circles())
                    .add(R.id.mainContainer_bottom, new Btns())
                    .setReorderingAllowed(true)
                    .commit();
        }

    }
}