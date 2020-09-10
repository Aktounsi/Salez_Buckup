package com.prom.eazy;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.prom.eazy.ui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView textViewIndex1;
    Button refreshBtn;
    ImageView hamb = null;
    Animation animation;
    static boolean isAnimated = false;
    TextView textViewIndex2;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textViewIndex1 = findViewById(R.id.textIndex1);
        textViewIndex2 = findViewById(R.id.textIndex2);
        refreshBtn = findViewById(R.id.refresh);
        hamb =findViewById(R.id.hamb);
        progressBar = findViewById(R.id.loadingg);
        //
        //check if user is logged in
        if (!SharedPref.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        else{
            String loggedUsename = SharedPref.getInstance(this).LoggedInUser();
            textViewIndex1.setText("Bonjour "+loggedUsename+",");

           /* //check if user accepted by admin
            boolean inscrit = false;
            if(!inscrit){
                textViewIndex2.setText("Vous êtes un nouveau utilisateur, veillez attendre la réponse de l'administrateur.");
            }else {


                //get autorisation value if (qte_bc == qte_bs)
                boolean autorisation = false;
                if (!autorisation) {
                    textViewIndex2.setText("Vous n'avez pas l'autorisation pour débuter votre mission.");
                } else {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
            }*/
           fun(loggedUsename);
            progressBar.setVisibility(View.VISIBLE);

        }


/////////////////////////////////////////////////////////////////////////////

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(300);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update isAnimated here!
                                isAnimated = false ;
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        thread.start();

/////////////////////////////////////////////////////////////////////////////

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View view, float v) {


                    if (v > .5) {
                        hamb = (ImageView) findViewById(R.id.hamb);
                        animation = (Animation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_hamb);
                        hamb.startAnimation(animation);
                        isAnimated = true;
                    }

                    if ((v > .2) && (!isAnimated)) {
                        hamb = (ImageView) findViewById(R.id.hamb);
                        animation = (Animation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_hamb);
                        hamb.startAnimation(animation);

                    }

            }

            @Override
            public void onDrawerOpened(View view) {

                //Toast toast=Toast. makeText(getApplicationContext()," hola! ",Toast. LENGTH_SHORT);
                //toast. show();
            }

            @Override
            public void onDrawerClosed(View view) {
                //Toast toast=Toast. makeText(getApplicationContext()," hola! ",Toast. LENGTH_SHORT);
                //toast. show();

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

        toggle.setDrawerIndicatorEnabled(false);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        hamb.setClickable(true);
        hamb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    drawer.openDrawer(GravityCompat.START);
                    Animation animation = AnimationUtils.loadAnimation(HomeActivity.this,R.anim.rotate_hamb);
                    hamb.startAnimation(animation);



            }
        });

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  //check if user accepted by admin
                boolean inscrit = true;
                if (inscrit) {
                    //get autorisation value if (qte_bc == qte_bs)
                    boolean autorisation = true;
                    if (autorisation) {
                        startActivity(new Intent(HomeActivity.this, MainActivity.class));
                        finish();
                    }

                }*/
                String loggedUsename = SharedPref.getInstance(getApplicationContext()).LoggedInUser();
                fun(loggedUsename);
                progressBar.setVisibility(View.GONE);


            }
        });

    }

        @Override
        public void onBackPressed() {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            //logging out
            SharedPref.getInstance(getApplicationContext()).logout();
            startActivity(new Intent(this, LoginActivity.class));
            finish();


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void fun(String username) {
        progressBar.setVisibility(View.VISIBLE);
        Api api = ApiAgent.getAgent().create(Api.class);
        Call<ModelTypeAgent> type = api.type(username);
        type.enqueue(new Callback<ModelTypeAgent>() {
            @Override
            public void onResponse(Call<ModelTypeAgent> call, Response<ModelTypeAgent> response) {
                if (response.body().getIsSuccess() == 1) {
                    //account exist
                    //check account type : v->vendeur, c->Chauffeur, t->temporaire
                    Log.d("khraa","1");
                    Character typeAgent = new Character(response.body().getType());
                    Log.d("khraa","2");

                    if(typeAgent.equals(new Character('c'))){
                        textViewIndex2.setText("Un administrateur vous a désigné comme chauffeur.");

                    }
                    if(typeAgent.equals(new Character('t'))){
                        textViewIndex2.setText("Vous êtes un nouveau utilisateur, veillez " +
                                "attendre la réponse de l'administrateur.");

                    }
                    if(typeAgent.equals(new Character('v'))){
                        //check autorisation debut de journee
                        Log.d("khraa","3");

                        if(response.body().getAutorisation() == 1){
                            Log.d("khraa","4");
                            startActivity(new Intent(HomeActivity.this, MainActivity.class));
                            finish();

                        }else{
                            textViewIndex2.setText("Vous n'avez pas l'autorisation pour débuter votre mission, " +
                                    "veuillez demander aux administrateurs de vérifier les quantitées chargées.");

                        }

                    }
                    if(typeAgent.equals(new Character('p'))){
                        startActivity(new Intent(HomeActivity.this, MainPointeurActivity.class));
                        finish();
                    }

                } else {
                    //account doesn't exist
                    textViewIndex2.setText("Ce compte est supprimé par un administrateur, veuillez contacter un administrateur.");

                    //SharedPref.getInstance(getApplicationContext()).logout();

                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ModelTypeAgent> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                textViewIndex2.setText("La connexion au serveur a échouée.");


            }
        });

    }

}

