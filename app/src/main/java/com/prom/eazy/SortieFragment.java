package com.prom.eazy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
  * A simple {@link Fragment} subclass.
  */

public class SortieFragment extends Fragment {
    public static final String EXTRA_INFO_AGENT_SORTIE = "com.prom.eazy.EXTRA_INFO_AGENT_SORTIE";
    public static final int timeInterval = 60000;
    public static HashMap<Integer,MyEntry<VendeurItemModel,Boolean>> hashmap;
    ArrayList<VendeurItem> vendeurList;

    FragmentActionListener fragmentActionListener;
    View rootView ;
    Context context;
    ImageView hamb;
    private RecyclerView mRecyclerView;
    private VendeurAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ShimmerFrameLayout mShimmerViewContainer;
    private SwipeRefreshLayout swipeRefreshLayout;


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
        hashmap = new HashMap<>();

        context  = getContext();
        hamb = (ImageView) rootView.findViewById(R.id.hamb);
        mShimmerViewContainer = rootView.findViewById(R.id.shimmer_view_container);
        mShimmerViewContainer.startShimmer();
        //mShimmerViewContainer.setScrollBarFadeDuration(1500);
        swipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(Color.rgb(98,38,158));

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

        vendeurList = new ArrayList<>();

        //vendeurList.add(new VendeurItem(1,R.drawable.ic_list_agents,"Mohammed KERTANE","1234511216","5"));
        //vendeurList.add(new VendeurItem(1,R.drawable.ic_list_agents,"Brahim ZAKZOUK","1265711216","5"));
        //vendeurList.add(new VendeurItem(1,R.drawable.ic_list_agents,"Abdelkader TOUNSI","1258911216","6"));
        //vendeurList.add(new VendeurItem(1,R.drawable.ic_list_agents,"Mohammed SALMI","1262711216","5"));
        String loggedUsename = SharedPref.getInstance(getActivity().getApplicationContext()).LoggedInUser();
        fun(loggedUsename);

        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new VendeurAdapter(vendeurList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //final ArrayList<VendeurItem> cl = vendeurList;


        mAdapter.setOnItemClickListener(new VendeurAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                refreshMyHashMap(loggedUsename);
                int id = vendeurList.get(position).getId();

                if(!ProduitVenteAdapter.myList.isEmpty()){
                    for(int i=0;i<ProduitVenteAdapter.myList.size();i++)
                        ProduitVenteAdapter.myList.put(i,"");
                }
                if(!hashmap.get(id).getValue()) {
                    Intent intent = new Intent(getActivity(), PointageSortieActivity.class);
                    intent.putExtra(EXTRA_INFO_AGENT_SORTIE,  hashmap.get(id).getKey());
                    startActivity(intent);
                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                vendeurList.clear();
                mAdapter.notifyDataSetChanged();
                mShimmerViewContainer.setVisibility(View.VISIBLE);
                mShimmerViewContainer.startShimmer();

                String loggedUsename = SharedPref.getInstance(getActivity().getApplicationContext()).LoggedInUser();
                fun(loggedUsename);
            }
        });

        Timer _timer = new Timer();

        _timer.schedule(new TimerTask() {
            @Override
            public void run() {
                refreshMyHashMap(loggedUsename);
            }
        }, timeInterval);
    }

    public void fun(String username){
        Api api = ApiAgent.getAgent().create(Api.class);
        Call<ModelListAgents> getAgentsSortie = api.getAgentsSortie(username);
        getAgentsSortie.enqueue(new Callback<ModelListAgents>() {
            @Override
            public void onResponse(Call<ModelListAgents> call, Response<ModelListAgents> response) {
                if (response.body().getIsSuccess() == 1) {
                    hashmap.clear();
                    Log.d("khraa","on response 1");

                    for (VendeurItemModel vi : response.body().getVendeursList()) {
                        vendeurList.add(new VendeurItem(vi.getCode_agent(), R.drawable.ic_list_agents,
                                vi.getNom() +" "+ vi.getPrenom() +" et "+ vi.getChauffeur(), vi.getMatricule(), vi.getCode_sec()));
                        hashmap.put(new Integer(vi.getCode_agent()),new MyEntry<>(vi,new Boolean(false)));
                    }
                    mAdapter.notifyDataSetChanged();
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                    refreshMyHashMap(username);

                }else {
                    Log.d("khraa","on response 0");

                }

            }

            @Override
            public void onFailure(Call<ModelListAgents> call, Throwable t) {
                Log.d("khraa","on failure");

            }
        });
    }

    public void refreshMyHashMap(String username){
        Api api = ApiAgent.getAgent().create(Api.class);
        Call<ModelListeVendeursPointes> getListeVendeursPointesSortie = api.getListeVendeursPointesSortie(username);
        getListeVendeursPointesSortie.enqueue(new Callback<ModelListeVendeursPointes>() {
            @Override
            public void onResponse(Call<ModelListeVendeursPointes> call, Response<ModelListeVendeursPointes> response) {
                if (response.body().getIsSuccess() == 1) {
                    for(int element : response.body().getListeVendeursPointes()){
                        VendeurItemModel value = hashmap.get(new Integer(element)).getKey();
                        hashmap.put(new Integer(element),new MyEntry<>(value,new Boolean(true)));
                    }



                }else{

                }

            }

            @Override
            public void onFailure(Call<ModelListeVendeursPointes> call, Throwable t) {

            }
        });

    }
}
