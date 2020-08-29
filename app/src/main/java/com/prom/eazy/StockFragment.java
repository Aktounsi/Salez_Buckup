package com.prom.eazy;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StockFragment extends Fragment {

    FragmentActionListener fragmentActionListener ;
    View rootView ;
    Context context;
    ImageView hamb;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    public StockFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_stock, container, false);
        initUI();
        return rootView;
    }

    public void setFragmentActionListener(FragmentActionListener fragmentActionListener){
        this.fragmentActionListener = fragmentActionListener;
    }

    private void initUI(){
        context  = getContext();
        hamb = (ImageView) rootView.findViewById(R.id.hambStock);
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

        ArrayList<ProduitItem> exampleList = new ArrayList<>();
        exampleList.add(new ProduitItem(1, "LOYA 1KG" ,50,20,30, 250.99f));
        exampleList.add(new ProduitItem(2, "LOYA 2KG" ,40,20,20, 400.99f));
        exampleList.add(new ProduitItem(3, "AMILA 1KG" ,50,10,30, 300.99f));
        exampleList.add(new ProduitItem(4, "TWISCO 1KG" ,60,20,40, 500.99f));
        exampleList.add(new ProduitItem(5, "LOYA 5KG" ,50,20,30, 6000.99f));
        exampleList.add(new ProduitItem(6, "LOYA 2KG" ,10,5,5, 25.99f));



        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        //mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new ProduitAdapter(exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

}
