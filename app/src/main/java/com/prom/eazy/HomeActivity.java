package com.prom.eazy;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.prom.eazy.ui.login.LoginActivity;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView textViewIndex1;
    Button refreshBtn;
    ImageView hamb = null;
    Animation animation;
    static boolean isAnimated = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textViewIndex1 = findViewById(R.id.textIndex1);
        refreshBtn = findViewById(R.id.refresh);
        hamb =findViewById(R.id.hamb);
        //
        //check if user is logged in
        if (!SharedPref.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        else{
            //get autorisation value if (qte_bc == qte_bs)
            boolean autorisation = false;
            if(!autorisation){
                    String loggedUsename = SharedPref.getInstance(this).LoggedInUser();
                    textViewIndex1.setText("Bonjour "+loggedUsename+",");
            }
            else{
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
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
                //get autorisation value if (qte_bc == qte_bs)
                boolean autorisation = true;
                if(autorisation){
                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
                    finish();
                }
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


}
