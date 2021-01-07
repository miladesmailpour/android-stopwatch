package ca.i3th.stopwatchcode_ver02.AuxFun;

import android.view.View;
import android.view.ViewGroup;

public class FragmentInfo {
    private ViewGroup.LayoutParams layoutParams;
    private View view;

    private boolean flag = false;
    public FragmentInfo(View view) {
        this.view = view;
        this.layoutParams = view.getLayoutParams();
    }

    public ViewGroup.LayoutParams setFragmentHeight(int height) {
        this.layoutParams.height = height;
        return this.layoutParams;
    }

    public ViewGroup.LayoutParams setFragmentWeight(int weight) {
        this.layoutParams.width = weight;
        return this.layoutParams;
    }

    public ViewGroup.LayoutParams setFragmentHeightWeight(int height, int weight) {
        this.layoutParams.height = height;
        this.layoutParams.width = weight;
        return this.layoutParams;
    }
}
