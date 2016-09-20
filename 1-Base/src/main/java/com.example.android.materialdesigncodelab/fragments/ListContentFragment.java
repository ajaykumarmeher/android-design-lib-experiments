package com.example.android.materialdesigncodelab.fragments;

/**
 * Created by ajayk on 9/5/2016.
 */

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android.materialdesigncodelab.Listeners.TickDetailListener;
import com.example.android.materialdesigncodelab.R;
import com.example.android.materialdesigncodelab.transitions.DetailsTransition;

/**
 * Provides UI for the view with List.
 */
public class ListContentFragment extends Fragment implements TickDetailListener {


  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    RecyclerView recyclerView =
        (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
    ListContentFragment.ContentAdapter adapter =
        new ListContentFragment.ContentAdapter(getActivity(),this);
    recyclerView.setAdapter(adapter);
    recyclerView.setHasFixedSize(true);

    // Set padding for Tiles (not needed for Cards/Lists!)
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    return recyclerView;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    private final TickDetailListener mListener;
    public ImageView avator;
    public TextView name;
    public TextView description;

    public ViewHolder(LayoutInflater inflater, ViewGroup parent, TickDetailListener listener) {
      super(inflater.inflate(R.layout.item_list, parent, false));
      this.mListener = listener;
      avator = (ImageView) itemView.findViewById(R.id.list_avatar);
      name = (TextView) itemView.findViewById(R.id.list_title);
      description = (TextView) itemView.findViewById(R.id.list_desc);
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          if(mListener != null){
            mListener.onTickDetailClicked((Integer) avator.getTag(),name.getText().toString(),description.getText().toString());
          }
        }
      });
    }
  }

  /**
   * Adapter to display recycler view.
   */
  public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
    // Set numbers of List in RecyclerView.
    private static final int LENGTH = 18;

    private final String[] mPlaces;
    private final String[] mPlaceDesc;
    private final Drawable[] mPlaceAvators;
    private final TickDetailListener mListener;

    public ContentAdapter(Context context,TickDetailListener listener) {
      this.mListener = listener;
      Resources resources = context.getResources();
      mPlaces = resources.getStringArray(R.array.places);
      mPlaceDesc = resources.getStringArray(R.array.place_desc);
      TypedArray a = resources.obtainTypedArray(R.array.place_avator);
      mPlaceAvators = new Drawable[a.length()];
      for (int i = 0; i < mPlaceAvators.length; i++) {
        mPlaceAvators[i] = a.getDrawable(i);
      }
      a.recycle();
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new ViewHolder(LayoutInflater.from(parent.getContext()), parent,mListener);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
      holder.avator.setImageDrawable(mPlaceAvators[position % mPlaceAvators.length]);
      holder.avator.setTag(position % mPlaceAvators.length);

      holder.name.setText(mPlaces[position % mPlaces.length]);
      holder.description.setText(mPlaceDesc[position % mPlaceDesc.length]);
    }

    @Override public int getItemCount() {
      return LENGTH;
    }
  }

  @Override public void onTickDetailClicked(int position, String tittle, String desc) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        TickDetailsDialogFragment dialog = TickDetailsDialogFragment.newInstance(position,tittle,desc);
        //dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.MyCustomTheme);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          dialog.setSharedElementEnterTransition(new DetailsTransition());
          dialog.setEnterTransition(new Fade());
          dialog.setExitTransition(new Fade());
          dialog.setSharedElementReturnTransition(new DetailsTransition());
        }
        dialog.show(fm, "Dialog");
    //getActivity().getSupportFragmentManager()
    //    .beginTransaction()
    //    .addSharedElement(holder.image, "kittenImage")
    //    .replace(R.id.container, kittenDetails)
    //    .addToBackStack(null)
    //    .commit();
  }
}
