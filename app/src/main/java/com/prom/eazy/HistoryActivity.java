package com.prom.eazy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    public static boolean bool = true;
    private RecyclerView mRecyclerView;
    private BonPointageAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ShimmerFrameLayout mShimmerViewContainer;
    private ImageView mImgView;
    private ArrayList<ListObject> consolidatedList;
    ArrayList<BonPointageItemModel> bonsList;
    ArrayList<BonPointageItemModel> bonsListNeverContainsNull;



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
        bonsList = new  ArrayList<>();
        bonsListNeverContainsNull = new ArrayList<>();
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

        //here
        mAdapter.setOnItemLongClickListener(new BonPointageAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                bool = true;

                final CharSequence[] items = {"Supprimer", "modifier"};

                AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);

                builder.setTitle("Selectionner une action");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        //int code_bp = consolidatedList.get(position).getCode_bp();

                        int code_bp = bonsList.get(position).getCode_bp();
                        if(item==0){
                            //deleteBonDePointage(code_bp);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if(bool){
                                    deleteBonDePointage(code_bp);
                                    consolidatedList.remove(position);
                                    bonsList.remove(position);
                                    refreshConsolidatedList();
                                    refreshBonsList();

                                    /*try {
                                        if((consolidatedList.get(position-1).getType()==ListObject.TYPE_DATE) &&
                                                (consolidatedList.get(position+1).getType()==ListObject.TYPE_DATE)) {
                                            consolidatedList.remove(position);
                                            consolidatedList.remove(position-1);
                                        }else{
                                            consolidatedList.remove(position);
                                        }


                                    }catch (Exception ex){
                                        if(consolidatedList.get(position-1).getType()==ListObject.TYPE_DATE){
                                            consolidatedList.remove(position);
                                            consolidatedList.remove(position-1);
                                        }else{
                                            consolidatedList.remove(position);
                                        }
                                    }*/

                                    mAdapter.notifyDataSetChanged();
                                }
                                }
                            }, 1500);

                            Snackbar.make(findViewById(R.id.coordinator), "document supprimé"+new Integer(code_bp).toString(), Snackbar.LENGTH_LONG)
                                    .setAction("Annuler", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            bool = false;
                                        }
                                    }).show();

                        }
                        if (item==1){

                        }

                    }
                });
                builder.show();
                return true;

                }
        });




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
                    /*for(BonPointageItemModel bonPointageItemModel : response.body().getBonsPointageList())
                    bonsList.add(bonPointageItemModel);*/
                    groupDataIntoHashMap(response.body().getBonsPointageList());
                    int index = 0;
                    for(ListObject elmt : consolidatedList){
                        if(elmt.getType()==ListObject.TYPE_DATE){
                            bonsList.add(null);
                        }else{
                            bonsList.add(response.body().getBonsPointageList().get(index));
                            index++;
                        }
                    }

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

    public void deleteBonDePointage(int code_bp){
        Api api = ApiAgent.getAgent().create(Api.class);
        Call<ModelIsSuccess> delete = api.deleteBon(code_bp);
        delete.enqueue(new Callback<ModelIsSuccess>() {
            @Override
            public void onResponse(Call<ModelIsSuccess> call, Response<ModelIsSuccess> response) {
                if(response.body().getIsSuccess()==1){
                   /* // Changing action button text color
                    View sbView = snackbar.getView();
                    TextView textView = (TextView)
                            sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackbar.show();*/


                }else{
                    Snackbar.make(findViewById(R.id.coordinator), "Erreur lors de la suppression du document", Snackbar.LENGTH_LONG)
                            .show();

                }

            }

            @Override
            public void onFailure(Call<ModelIsSuccess> call, Throwable t) {
                Snackbar.make(findViewById(R.id.coordinator), "Erreur, tentative de connexion au serveur échouée", Snackbar.LENGTH_LONG)
                        .show();

            }
        });
    }

    public void refreshConsolidatedList(){
        for (int i = 0; i < consolidatedList.size() ; i++) {
           try {
               if((consolidatedList.get(i).getType()==ListObject.TYPE_DATE) &&
                       (consolidatedList.get(i+1).getType()==ListObject.TYPE_DATE))
                   consolidatedList.remove(i);
           }catch (IndexOutOfBoundsException ex){
               consolidatedList.remove(i);
           }

        }
    }

    public void refreshBonsList(){
        for (int i = 0; i < bonsList.size() ; i++) {
            try {
                if((bonsList.get(i)==null) &&
                        (bonsList.get(i+1)==null))
                    bonsList.remove(i);
            }catch (IndexOutOfBoundsException ex){
                bonsList.remove(i);
            }

        }
    }


}
