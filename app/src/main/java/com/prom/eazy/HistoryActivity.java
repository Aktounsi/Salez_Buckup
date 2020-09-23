package com.prom.eazy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private BonPointageAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ShimmerFrameLayout mShimmerViewContainer;
    private ImageView mImgView;
    private ArrayList<ListObject> consolidatedList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("exxx","mazal madkhalt adapter oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        mShimmerViewContainer.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmer();
        String loggedUsename = SharedPref.getInstance(this).LoggedInUser();
        consolidatedList = new ArrayList<>();
        getListBonsPointage(loggedUsename);

         //ArrayList<BonPointageItemModel> ex = new ArrayList<>();

         /*ex.add(new BonPointageItemModel(6,"nom","prenom"
                 ,"1122272727","5","1","Chauffeur",
                 "22/09/2020","sortie"));
        ex.add(new BonPointageItemModel(6,"nom","prenom"
                ,"1122272727","5","1","Chauffeur",
                "22/09/2020","retour"));
        ex.add(new BonPointageItemModel(6,"nom","prenom"
                ,"1122272727","5","1","Chauffeur",
                "23/09/2020","sortie"));
         groupDataIntoHashMap(ex);*/

        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mShimmerViewContainer.stopShimmer();
        Log.d("exxx","mazal madkhalt adapter");
        mAdapter = new BonPointageAdapter(consolidatedList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mImgView = (ImageView) findViewById(R.id.back);
        mImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void getListBonsPointage(String username){
        Api api = ApiAgent.getAgent().create(Api.class);
        Call<ModelListBonsPointage> getList = api.getListBonsPointage(username);
        getList.enqueue(new Callback<ModelListBonsPointage>() {
            @Override
            public void onResponse(Call<ModelListBonsPointage> call, Response<ModelListBonsPointage> response) {
                if (response.body().getIsSuccess() == 1) {
                    groupDataIntoHashMap(response.body().getBonsPointageList());

                    mAdapter.notifyDataSetChanged();
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);

                }else{

                }
            }

            @Override
            public void onFailure(Call<ModelListBonsPointage> call, Throwable t) {

            }
        });
    }

    private void groupDataIntoHashMap(ArrayList<BonPointageItemModel> bonPointageModelList) {
        LinkedHashMap<String, Set<BonPointageItemModel>> groupedHashMap = new LinkedHashMap<>();
        Set<BonPointageItemModel> list = null;
        for (BonPointageItemModel bonPointageModel : bonPointageModelList) {
            //Log.d(TAG, travelActivityDTO.toString());
            String hashMapKey = bonPointageModel.getDate_pt();
            //Log.d(TAG, "start date: " + DateParser.convertDateToString(travelActivityDTO.getStartDate()));
            if (groupedHashMap.containsKey(hashMapKey)) {
                // The key is already in the HashMap; add the pojo object
                // against the existing key.
                groupedHashMap.get(hashMapKey).add(bonPointageModel);
            } else {
                // The key is not there in the HashMap; create a new key-value pair
                list = new LinkedHashSet<>();
                list.add(bonPointageModel);
                groupedHashMap.put(hashMapKey, list);
            }
        }
        //Generate list from map
        generateListFromMap(groupedHashMap);

    }

    private ArrayList<ListObject> generateListFromMap(LinkedHashMap<String, Set<BonPointageItemModel>> groupedHashMap) {
        // We linearly add every item into the consolidatedList.

        for (String date : groupedHashMap.keySet()) {
            DateObject dateItem = new DateObject();
            dateItem.setDate(date);
            consolidatedList.add(dateItem);
            for (BonPointageItemModel bonPointageModel : groupedHashMap.get(date)) {
                BonPointageItemModelObject generalItem = new BonPointageItemModelObject();
                generalItem.setBonPointageItemModel(bonPointageModel);
                consolidatedList.add(generalItem);
            }
        }

        //mAdapter.setDataChange();
        return consolidatedList;
    }
}
