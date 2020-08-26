package com.prom.eazy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class VenteActivity extends AppCompatActivity implements BottomSheetDialog.BottomSheetListener  {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vente);

        ArrayList<ProduitItemVente> exampleList = new ArrayList<>();
        exampleList.add(new ProduitItemVente(1, "LOYA 1KG" , 250.99f, 10));
        exampleList.add(new ProduitItemVente(1, "LOYA 2KG" , 400.99f, 30));
        exampleList.add(new ProduitItemVente(1, "AMILA 1KG" , 300.99f, 20));
        exampleList.add(new ProduitItemVente(1, "TWISCO 1KG" , 500.99f, 10));
        exampleList.add(new ProduitItemVente(1, "LOYA 5KG" , 6000.99f, 10));
        exampleList.add(new ProduitItemVente(1, "LOYA 2KG" , 25.99f, 20));



        mRecyclerView = findViewById(R.id.recyclerView);
        //mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ProduitVenteAdapter(exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        FloatingActionButton buttonOpenBottomSheet = (FloatingActionButton) findViewById(R.id.annuler_fab);

        buttonOpenBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheet = new BottomSheetDialog();
                bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
            }
        });
    }

    @Override
    public void onButtonClicked(String text) {

    }
}
