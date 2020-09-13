package com.prom.eazy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.mazenrashed.printooth.utilities.Printing;

import java.util.HashMap;

public class PointageSortieActivity extends AppCompatActivity {
    private ListView mRecyclerView;
    private ProduitVenteAdapter mAdapter;

    HashMap<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
    Printing printing;
    private ImageView mImgView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointage_sortie);
    }
}
