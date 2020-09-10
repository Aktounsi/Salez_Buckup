package com.prom.eazy;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class RetourFragment extends Fragment {
    FragmentActionListener fragmentActionListener;
    View rootView ;
    Context context;
    ImageView hamb;

    public RetourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_retour, container, false);
        initUI();
        return rootView;
    }

    public void setFragmentActionListener(FragmentActionListener fragmentActionListener){
        this.fragmentActionListener = fragmentActionListener;
    }

    private void initUI(){
        context  = getContext();
        hamb = (ImageView) rootView.findViewById(R.id.hamb);
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
    }

}
