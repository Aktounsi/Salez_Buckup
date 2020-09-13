package com.prom.eazy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
  * A simple {@link Fragment} subclass.
  */

public class SortieFragment extends Fragment {
    public static final String EXTRA_ID_AGENT = "com.prom.eazy.EXTRA_ID_AGENT";

    FragmentActionListener fragmentActionListener;
    View rootView ;
    Context context;
    ImageView hamb;
    private RecyclerView mRecyclerView;
    private VendeurAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public SortieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_sortie, container, false);
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

        ArrayList<VendeurItem> vendeurList = new ArrayList<>();
        vendeurList.add(new VendeurItem(1,R.drawable.ic_list_agents,"Mohammed KERTANE","1234511216","5"));
        vendeurList.add(new VendeurItem(1,R.drawable.ic_list_agents,"Brahim ZAKZOUK","1265711216","5"));
        vendeurList.add(new VendeurItem(1,R.drawable.ic_list_agents,"Abdelkader TOUNSI","1258911216","6"));
        vendeurList.add(new VendeurItem(1,R.drawable.ic_list_agents,"Mohammed SALMI","1262711216","5"));


        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new VendeurAdapter(vendeurList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        final ArrayList<VendeurItem> cl = vendeurList;


        mAdapter.setOnItemClickListener(new VendeurAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int id = cl.get(position).getId();

                Intent intent = new Intent(getActivity(), PointageSortieActivity.class);
                intent.putExtra(EXTRA_ID_AGENT, id);
                startActivity(intent);
            }
        });
    }
}
