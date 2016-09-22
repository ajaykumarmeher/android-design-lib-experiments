package com.example.android.materialdesigncodelab.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.example.android.materialdesigncodelab.R;

public class TickDetailsDialogFragment extends DialogFragment {

  private SectionsPagerAdapter sectionsPagerAdapter;
  private ViewPager viewPager;
  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment TickDetailsDialogFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static TickDetailsDialogFragment newInstance(int position, String tittle, String desc) {
    TickDetailsDialogFragment fragment = new TickDetailsDialogFragment();
    Bundle args = new Bundle();
    args.putString("tittle", tittle);
    args.putString("desc", desc);
    args.putInt("image", position);
    fragment.setArguments(args);
    return fragment;
  }

  public TickDetailsDialogFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    Bundle args = getArguments();
    String title = args.getString("tittle");
    View view = inflater.inflate(R.layout.fragment_tick_details_dialog, container, false);
    // tab slider
    sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
    viewPager = (ViewPager)view.findViewById(R.id.pager);
    viewPager.setAdapter(sectionsPagerAdapter);
    Toolbar toolbar = (Toolbar) view.findViewById(R.id.my_toolbar);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        // Handle the menu item
        return true;
      }
    });
    toolbar.inflateMenu(R.menu.menu_main);
    toolbar.setTitle(title);
    return view;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override public void onDetach() {
    super.onDetach();
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Dialog dialog = super.onCreateDialog(savedInstanceState);
    // request a window without the title
    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    return dialog;
  }

  @Override
  public void onActivityCreated(Bundle arg0) {
    super.onActivityCreated(arg0);
    //getDialog().getWindow()
    //    .getAttributes().windowAnimations = R.style.DialogAnimation;
  }

  // ------------------------------------------------------------------------
  // inner classes
  // ------------------------------------------------------------------------

  /**
   * Used for tab paging...
   */
  public class SectionsPagerAdapter extends FragmentPagerAdapter
  {

    public SectionsPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      if (position == 0)
      {
        // find first fragment...
        Fragment_Tab_1 ft1 = new Fragment_Tab_1();
        return ft1;
      }
      if (position == 1)
      {
        // find first fragment...
        Fragment_Tab_2 ft2 = new Fragment_Tab_2();
        return ft2;
      }
      else if (position == 2)
      {
        // find first fragment...
        Fragment_Tab_3 ft3 = new Fragment_Tab_3();
        return ft3;
      }

      return null;
    }

    @Override
    public int getCount() {
      // Show 2 total pages.
      return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      switch (position) {
        case 0:
          return "First Tab";
        case 1:
          return "Second Tab";
        case 2:
          return "Third Tab";
      }
      return null;
    }
  }

}
