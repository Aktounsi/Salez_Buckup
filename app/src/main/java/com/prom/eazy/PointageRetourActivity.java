package com.prom.eazy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mazenrashed.printooth.Printooth;
import com.mazenrashed.printooth.data.printable.Printable;
import com.mazenrashed.printooth.data.printable.RawPrintable;
import com.mazenrashed.printooth.data.printable.TextPrintable;
import com.mazenrashed.printooth.data.printer.DefaultPrinter;
import com.mazenrashed.printooth.ui.ScanningActivity;
import com.mazenrashed.printooth.utilities.Printing;
import com.mazenrashed.printooth.utilities.PrintingCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PointageRetourActivity extends AppCompatActivity
        implements BottomSheetDialogPt.BottomSheetListener,BottomSheetDialogValidatePt.BottomSheetListener, PrintingCallback {
    public static final String EXTRA_INFO_AGENT = "com.prom.eazy.EXTRA_INFO_AGENT";
    private ListView mRecyclerView;
    private ProduitVenteAdapter mAdapter;
    private ShimmerFrameLayout mShimmerViewContainer;
    ArrayList<ProduitItemVente> exampleList;


    DialogBoxSuccess dialogBoxSuccess = new DialogBoxSuccess(PointageRetourActivity.this);
    DialogBoxError dialogBoxError = new DialogBoxError(PointageRetourActivity.this);
    HashMap<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
    Printing printing;
    private ImageView mImgView;
    VendeurItemModel vendeurFromIntent;// = (VendeurItemModel) getIntent().getSerializableExtra(EXTRA_INFO_AGENT);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointage_retour);

        vendeurFromIntent = (VendeurItemModel) getIntent().getSerializableExtra(EXTRA_INFO_AGENT);

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        mShimmerViewContainer.startShimmer();

        exampleList = new ArrayList<>();
        getListProducts();
        //exampleList.add(new ProduitItemVente(1, "LOYA 1KG" , 250.99f, 10));
        //exampleList.add(new ProduitItemVente(2, "LOYA 2KG" , 400.99f, 30));
        //exampleList.add(new ProduitItemVente(3, "AMILA 1KG" , 300.99f, 20));
        //exampleList.add(new ProduitItemVente(4, "TWISCO 1KG" , 500.99f, 10));
        //exampleList.add(new ProduitItemVente(5, "LOYA 5KG" , 6000.99f, 10));
        //exampleList.add(new ProduitItemVente(6, "LOYA 2KG" , 25.99f, 20));

        if(printing != null) printing.setPrintingCallback(this);



        mRecyclerView = findViewById(R.id.recyclerView);
        //mRecyclerView.setHasFixedSize(true);
        //mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ProduitVenteAdapter(exampleList,this);
        //mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        FloatingActionButton buttonMotifBottomSheet = (FloatingActionButton) findViewById(R.id.annuler_fab);
        FloatingActionButton buttonValidateCommandBottomSheet = (FloatingActionButton) findViewById(R.id.valider_fab);
        mImgView = (ImageView) findViewById(R.id.back);

        buttonMotifBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogPt bottomSheet = new BottomSheetDialogPt();
                bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
            }
        });



        mImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        buttonValidateCommandBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("success","nombres "+mAdapter.getItemCount());

                for (int i = 0; i < mAdapter.getCount(); i++) {

                    //View view = mRecyclerView.getChildAt(i);
                    View view = getViewByPosition(i,mRecyclerView);

                    Log.d("success","getChild "+i);
                    EditText nameEditText = (EditText) view.findViewById(R.id.etQte); Log.d("success","findviewbyid  "+i);


                    Log.d("success","nb"+i);
                    int qte=0; Log.d("success","int qte=0");
                    try {
                        qte = Integer.parseInt( nameEditText.getText().toString());
                        Log.d("success","try "+qte);
                    } catch(NumberFormatException ex) {
                        qte = 0; Log.d("success","catch"+qte);
                    }
                    int id = mAdapter.getItem(i).getId(); Log.d("success","getId "+id);
                    if(qte>0) hashMap.put(id,qte); Log.d("success","hashmap put");
                    Log.d("success","nb"+i);
                }
                if(hashMap.isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "Please enter at least one product quantite !",Toast.LENGTH_LONG).show();
                    return;
                }
                BottomSheetDialogValidatePt bottomSheet = new BottomSheetDialogValidatePt();
                bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
            }
        });



    }


    @Override
    public void onButtonClicked(String motif) {
        //insert choosen motif IN TABLE MOTIF
        boolean  insert = true;
        //afficher boite de dialogue RESULT: SUCCESS/ERROR DUREE: 2s
        if(insert) {

            dialogBoxSuccess.startSuccessDialog();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialogBoxSuccess.dissmissDialog();
                    //Log.d("success","aaaaaall");
                }
            }, 1500);
        }else{
            dialogBoxError.startSuccessDialog();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialogBoxError.dissmissDialog();
                    //Log.d("success","aaaaaall");
                }
            }, 1500);
        }


        //retourner à ClientFragment ie. activité précédente
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1800);
        //update interface telle que réalisation

    }


    @Override
    public void onValidateCommand(boolean facturer) {

        //insert facture and insert ligne facture for each product if qte>0

        /*for (int i = 0; i < mAdapter.getItemCount(); i++) {
            View view = mRecyclerView.getChildAt(i);
            EditText nameEditText = (EditText) view.findViewById(R.id.etQte);
            int qte;
            try {
                qte = Integer.parseInt(nameEditText.getText().toString());
            } catch(NumberFormatException ex) {
                qte = 0;
            }
            int id = (int) mAdapter.getItemId(i);
            if(qte>0) hashMap.put(id,qte);
        }*/
        VendeurItemModel v = (VendeurItemModel) getIntent().getSerializableExtra(EXTRA_INFO_AGENT);
        int code_ag = v.getCode_agent();
        int code_vehicule = Integer.parseInt(v.getCode_vehicule());
        int sec = Integer.parseInt(v.getCode_sec());
        String username = SharedPref.getInstance(getApplicationContext()).LoggedInUser();

        fun(username,code_vehicule,code_ag,sec);

        boolean  insert = true;
        //Imprimer if insert=true
        if(facturer){
            //Imprimer
            if(!Printooth.INSTANCE.hasPairedPrinter()) {
                startActivityForResult(new Intent(PointageRetourActivity.this, ScanningActivity.class),ScanningActivity.SCANNING_FOR_PRINTER);
            }else{
                printText();
            }
        }
        //afficher boite de dialogue RESULT: SUCCESS/ERROR DUREE: 2s
        /*if(insert) {

            dialogBoxSuccess.startSuccessDialog();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialogBoxSuccess.dissmissDialog();
                    //Log.d("success","aaaaaall");
                }
            }, 1500);
        }else{
            dialogBoxError.startSuccessDialog();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialogBoxError.dissmissDialog();
                    //Log.d("success","aaaaaall");
                }
            }, 1500);
        }*/
        //dialogBoxSuccess.animateDialog();



        //retourner à ClientFragment ie. activité précédente
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                //Log.d("success","aaaaaall");
            }
        }, 2400);
        //update interface telle que stock (qte rst)

    }




    private void printText() {
        ArrayList<Printable> printables = new ArrayList<>();
        printables.add(new RawPrintable.Builder( new byte [] {27,100,4}).build() );

        //text1 not customized
        printables.add(new TextPrintable.Builder()
                .setText("Hello world")
                .setCharacterCode(DefaultPrinter.Companion.getCHARCODE_PC1252())
                .setNewLinesAfter(1)
                .build());
        //text2 customized
        printables.add(new TextPrintable.Builder()
                .setText("Text 2")
                .setLineSpacing(DefaultPrinter.Companion.getLINE_SPACING_60())
                .setEmphasizedMode(DefaultPrinter.Companion.getEMPHASIZED_MODE_BOLD())
                .setNewLinesAfter(1)
                .build());
        printing.print(printables);
    }

    @Override
    public void connectingWithPrinter() {
        Toast.makeText(getApplicationContext(),
                "Connected",Toast.LENGTH_LONG).show();
    }

    @Override
    public void connectionFailed(String s) {
        Toast.makeText(getApplicationContext(),
                "Connection failed" + s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(String s) {
        Toast.makeText(getApplicationContext(),
                "Eroor Print" + s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMessage(String s) {
        Toast.makeText(getApplicationContext(), s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void printingOrderSentSuccessfully() {
        Toast.makeText(getApplicationContext(),
                "printed successfully",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ScanningActivity.SCANNING_FOR_PRINTER && resultCode == Activity.RESULT_OK)
            initPrinting();
    }


    private void initPrinting() {
        if(!Printooth.INSTANCE.hasPairedPrinter()) {
            printing = Printooth.INSTANCE.printer();
        }
        if(printing != null){
            printing.setPrintingCallback(this);
        }


    }

    public View getViewByPosition(int position, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition =firstListItemPosition + listView.getChildCount() - 1;

        if (position < firstListItemPosition || position > lastListItemPosition ) {
            return listView.getAdapter().getView(position, listView.getChildAt(position), listView);
        } else {
            final int childIndex = position - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    public void fun(String username, int code_vehicule, int code_ag, int sec){
        Api api = ApiAgent.getAgent().create(Api.class);
        Call<ModelIsSuccess> getAgents = api.insertBonDeRetour(username,code_vehicule,code_ag,sec);
        getAgents.enqueue(new Callback<ModelIsSuccess>() {
            @Override
            public void onResponse(Call<ModelIsSuccess> call, Response<ModelIsSuccess> response) {
                if (response.body().getIsSuccess() == 1) {
                    ///
                    for (Integer key: hashMap.keySet()) {
                    funQtes(key.intValue(),hashMap.get(key).intValue(),response.body().getCode_bp());
                    }
                    ///

                }else { Log.d("khraa","onsuccess but");
                    dialogBoxError.startSuccessDialog();
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialogBoxError.dissmissDialog();
                            //Log.d("success","aaaaaall");
                        }
                    }, 1500);
                }

            }

            @Override
            public void onFailure(Call<ModelIsSuccess> call, Throwable t) {
                Log.d("khraa","onsuccess");
                dialogBoxError.startSuccessDialog();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogBoxError.dissmissDialog();
                        //Log.d("success","aaaaaall");
                    }
                }, 1500);
            }
        });
    }


    public void funQtes(int code_pr,int qte,int code_bp){
        Api api = ApiAgent.getAgent().create(Api.class);
        Call<ModelIsSuccess> getAgents = api.insertQteBonDeRetour(code_pr,qte,code_bp);
        getAgents.enqueue(new Callback<ModelIsSuccess>() {
            @Override
            public void onResponse(Call<ModelIsSuccess> call, Response<ModelIsSuccess> response) {
                if (response.body().getIsSuccess() == 1) {
                    Log.d("khraa","funQtes onresponse 1"+response.body().getMessage());

                    if(code_pr == hashMap.size()){
                        Log.d("khraa","onsuccess");
                        dialogBoxSuccess.startSuccessDialog();
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialogBoxSuccess.dissmissDialog();
                            }
                        }, 1500);


                        RetourFragment.hashmap.put(new Integer(vendeurFromIntent.getCode_agent()),new MyEntry<>(vendeurFromIntent,new Boolean(true)));

                    }

                }else {
                    Log.d("khraa","funQtes onresponse 0"+response.body().getMessage());
                    //supprimer tous les qte de ce bon + supprimer ce bon
                    deleteBonDeRetour(code_bp);
                    //set the RetourFragment hashmap value to false so he can click him again
                    RetourFragment.hashmap.put(new Integer(vendeurFromIntent.getCode_agent()),new MyEntry<>(vendeurFromIntent,new Boolean(false)));


                    //box dialog error
                    dialogBoxError.startSuccessDialog();
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialogBoxError.dissmissDialog();
                        }
                    }, 1500);

                }
            }

            @Override
            public void onFailure(Call<ModelIsSuccess> call, Throwable t) {
                Log.d("khraa","funQtes onfailure ");
                //supprimer tous les qte de ce bon + supprimer ce bon
                deleteBonDeRetour(code_bp);
                //set the RetourFragment hashmap value to false so he can click him again
                RetourFragment.hashmap.put(new Integer(vendeurFromIntent.getCode_agent()),new MyEntry<>(vendeurFromIntent,new Boolean(false)));

                //box dialog error
                dialogBoxError.startSuccessDialog();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogBoxError.dissmissDialog();
                    }
                }, 1500);

            }
        });
    }

    public void deleteBonDeRetour(int code_bp){
        Api api = ApiAgent.getAgent().create(Api.class);
        Call<ModelIsSuccess> delete = api.deleteQteBonDeRetour(code_bp);
        delete.enqueue(new Callback<ModelIsSuccess>() {
            @Override
            public void onResponse(Call<ModelIsSuccess> call, Response<ModelIsSuccess> response) {

            }

            @Override
            public void onFailure(Call<ModelIsSuccess> call, Throwable t) {

            }
        });
    }

    public void getListProducts(){
        Log.d("khraa","getListProducts()1");
        Api api = ApiAgent.getAgent().create(Api.class);
        Log.d("khraa","getListProducts(2)");

        Call<ModelListProduct> getList = api.getListProducts("pointeur");
        Log.d("khraa","getListProducts(3)");

        getList.enqueue(new Callback<ModelListProduct>() {

            @Override
            public void onResponse(Call<ModelListProduct> call, Response<ModelListProduct> response) {
                Log.d("khraa","getListProducts() response");

                if (response.body().getIsSuccess() == 1) {
                    Log.d("khraa","1");
                    for (ProductItemModel prod : response.body().getProductsList()) {
                        exampleList.add(new ProduitItemVente(prod.getCode_pr(), prod.getDesignation() , (float) prod.getPrix_u(), prod.getStock()));
                    }
                    mAdapter.notifyDataSetChanged();
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);

                }else{

                }
            }

            @Override
            public void onFailure(Call<ModelListProduct> call, Throwable t) {
                Log.d("khraa","getListProducts() fail");

            }
        });
    }

}
