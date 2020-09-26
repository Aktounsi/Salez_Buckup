package com.prom.eazy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class SettingsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ParametresAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView mImgView;
    private ArrayList<ListParametresObject> consolidatedList;
    private HashMap<Integer,ArrayList<SubTitleModelObject>> mhashmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        consolidatedList = new ArrayList<>();
        mhashmap = new HashMap<>();

        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ParametresAdapter(consolidatedList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mImgView = (ImageView) findViewById(R.id.back);
        mImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //adding new Title with subtitles
        TitleModelObject title1 = new TitleModelObject();
        title1.setTitle(new TitleModelItem(R.drawable.ic_expand_more_black_24dp,R.drawable.ic_account,"Paramètres de profil"));
        title1.setChecked(false);
        consolidatedList.add(title1);

            //add new hashmap entry
            mhashmap.put(new Integer(0),new ArrayList<SubTitleModelObject>());
            //new subtitle
            SubTitleModelObject subtitle1_1 = new SubTitleModelObject();
            subtitle1_1.setSubTitle(new SubTitleModelItem(0,R.drawable.ic_vpn_key_black_24dp,"Modifier le mot de passe"));
            mhashmap.get(0).add(subtitle1_1);
            //new subtitle
            SubTitleModelObject subtitle1_2 = new SubTitleModelObject();
            subtitle1_2.setSubTitle(new SubTitleModelItem(0,R.drawable.param2,"Modifier vos informations personnelles"));
            mhashmap.get(0).add(subtitle1_2);
            //new subtitle
            SubTitleModelObject subtitle1_3 = new SubTitleModelObject();
            subtitle1_3.setSubTitle(new SubTitleModelItem(0,R.drawable.param3,"Accéder vos informations personnelles"));
            mhashmap.get(0).add(subtitle1_3);

        //adding new Title with subtitles
        TitleModelObject title2= new TitleModelObject();
        title2.setTitle(new TitleModelItem(R.drawable.ic_expand_more_black_24dp,R.drawable.param4,"Préférences"));
        consolidatedList.add(title2);

            //add new hashmap entry
            mhashmap.put(new Integer(1),new ArrayList<SubTitleModelObject>());
            //new subtitle
            SubTitleModelObject subtitle2_1 = new SubTitleModelObject();
            subtitle2_1.setSubTitle(new SubTitleModelItem(0,R.drawable.param5,"Langue"));
            mhashmap.get(1).add(subtitle2_1);
            //new subtitle
            SubTitleModelObject subtitle2_2 = new SubTitleModelObject();
            subtitle2_2.setSubTitle(new SubTitleModelItem(0,R.drawable.param6,"Thème"));
            mhashmap.get(1).add(subtitle2_2);

        //adding new Title with subtitles
        TitleModelObject title3= new TitleModelObject();
        title3.setTitle(new TitleModelItem(R.drawable.ic_expand_more_black_24dp,R.drawable.ic_settings,"Configuration serveur"));
        consolidatedList.add(title3);

            //add new hashmap entry
            mhashmap.put(new Integer(2),new ArrayList<SubTitleModelObject>());
            //new subtitle
            SubTitleModelObject subtitle3_1 = new SubTitleModelObject();
            subtitle3_1.setSubTitle(new SubTitleModelItem(0,R.drawable.ic_menu_send,"Configuration serveur"));
            mhashmap.get(2).add(subtitle3_1);

        //adding new Title with subtitles
        TitleModelObject title4= new TitleModelObject();
        title4.setTitle(new TitleModelItem(R.drawable.ic_expand_more_black_24dp,R.drawable.ic_info,"Aide et assistance"));
        consolidatedList.add(title4);

            //add new hashmap entry
            mhashmap.put(new Integer(3),new ArrayList<SubTitleModelObject>());
            //new subtitle
            SubTitleModelObject subtitle4_1 = new SubTitleModelObject();
            subtitle4_1.setSubTitle(new SubTitleModelItem(0,R.drawable.ic_settings,"Pages d'aide"));
            mhashmap.get(3).add(subtitle4_1);
            //new subtitle
            SubTitleModelObject subtitle4_2 = new SubTitleModelObject();
            subtitle4_2.setSubTitle(new SubTitleModelItem(0,R.drawable.param9,"Signaler un problème"));
            mhashmap.get(3).add(subtitle4_2);

        //adding new Title with subtitles
        TitleModelObject title5= new TitleModelObject();
        title5.setTitle(new TitleModelItem(0,0,""));
        consolidatedList.add(title5);

            //add new hashmap entry
            mhashmap.put(new Integer(4),new ArrayList<SubTitleModelObject>());

        //notify adapter changes
        mAdapter.notifyDataSetChanged();



        mAdapter.setOnItemClickListener(new ParametresAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                if(consolidatedList.get(position).getType()==ListParametresObject.TYPE_TITLE){
                    if(position==consolidatedList.size()-1) return;
                    consolidatedList.get(position).setChecked(!consolidatedList.get(position).isChecked());
                    if(!consolidatedList.get(position).isChecked() ){
                        consolidatedList.get(position).setRightImage(R.drawable.ic_expand_more_black_24dp);
                        //Toast.makeText(SettingsActivity.this,new Boolean(consolidatedList.get(position).isChecked()).toString(),Toast.LENGTH_LONG).show();
                        while( (position+1<consolidatedList.size()) && (consolidatedList.get(position+1).getType()==ListParametresObject.TYPE_SUBTITLE) ) {
                                consolidatedList.remove(position+1);
                        }
                    }else{
                        consolidatedList.get(position).setRightImage(R.drawable.ic_expand_less_black_24dp);
                        int index = 0;
                        int i = 0;

                            while( (i<consolidatedList.size()) && (i<position) ){
                                if(consolidatedList.get(i).getType() == ListParametresObject.TYPE_SUBTITLE) index++;
                                i++;
                            }


                        for (int j = mhashmap.get(position-index).size()-1; j >=0 ; j--) {
                            consolidatedList.add(position+1,mhashmap.get(position-index).get(j));
                        }

                    }

                }

                if(consolidatedList.get(position).getType()==ListParametresObject.TYPE_SUBTITLE){
                    //Toast.makeText(SettingsActivity.this,"subtitle",Toast.LENGTH_LONG).show();
                }

                mAdapter.notifyDataSetChanged();
            }
        });


    }


}
