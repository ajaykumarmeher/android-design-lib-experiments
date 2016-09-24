package com.example.android.materialdesign.ui;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

/**
 * Created by ajayk on 9/20/2016.
 */

public interface MainScreenHandler {
    Toolbar getToolbar();
    TabLayout getTabLayout();
    AppBarLayout getAppBarLayout();
    AppBarLayout getFloatingActionButton();
    void reset();
}
