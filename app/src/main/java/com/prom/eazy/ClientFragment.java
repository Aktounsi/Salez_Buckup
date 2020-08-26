package com.prom.eazy;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.prom.eazy.ui.main.PageViewModel;
import com.prom.eazy.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClientFragment extends Fragment {


    FragmentActionListener fragmentActionListener ;
    View rootView ;
    Context context;
    ImageView hamb;

   private RecyclerView mRecyclerView;
   private RecyclerView.Adapter mAdapter;
  //  private RecyclerView.LayoutManager mLayoutManager;
   // private PageViewModel pageViewModel;


    public ClientFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_client, container, false);

        Log.d("success","OnCreateView ClientFragment");

        initUI();
        return rootView;
    }



    public void setFragmentActionListener(FragmentActionListener fragmentActionListener){
        this.fragmentActionListener = fragmentActionListener;
    }

    private void initUI(){
        context  = getContext();
        hamb = (ImageView) rootView.findViewById(R.id.hambClient);
        hamb.setClickable(true);
        hamb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentActionListener != null){
                    fragmentActionListener.openDrawer();
                    Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_hamb);
                    hamb.startAnimation(animation);
                }


            }
        });

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getActivity(), getChildFragmentManager());
        ViewPager viewPager = rootView.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = rootView.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
 /*       pageViewModel.getText().observe(this, new Observer<ArrayList<ClientItem>>() {
            @Override
            public void onChanged(@Nullable ArrayList<ClientItem> clientList) {
                Log.d("success","OnCreateView ClientFragment3 Onchanged");
                // textView.setText(s);
                if (getContext() != null) mLayoutManager = new LinearLayoutManager(getContext());
                mAdapter = new ClientAdapter(clientList);
                mRecyclerView.setLayoutManager(mLayoutManager);

                //mAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mAdapter);


            }
        });
 */
        //mAdapter.notifyDataSetChanged();

       // mRecyclerView.setAdapter(mAdapter);

    }



}
