package com.example.android.materialdesigncodelab.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android.materialdesigncodelab.R;


public class TickDetailsDialogFragment extends DialogFragment {
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
    TextView tv = (TextView) view.findViewById(R.id.text);
    String desc = args.getString("desc");
    tv.setText(desc);
    int position = args.getInt("image");
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
    Resources resources = getActivity().getResources();
    TypedArray a = resources.obtainTypedArray(R.array.place_avator);
    ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
    imageView.setImageDrawable(a.getDrawable(position));
    imageView.setTag(position);
    imageView.setDrawingCacheEnabled(false);
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

}
