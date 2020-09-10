package com.prom.eazy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.prom.eazy.ui.login.LoginActivity;

public class MainPointeurActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentActionListener{
    Fragment fragSortie = null;    Fragment fragRetour = null;

    View myViewFragment = null;
    ImageView hamb = null;
    Animation animation;
    static int selected = 1;


    static boolean isAnimated = false;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //Fragment frag = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //frag = new HomeFragment();
                    selected = 1;


                    getSupportFragmentManager().beginTransaction()
                            //.addToBackStack(null)
                            .replace(R.id.contenu, fragSortie)
                            .commit();

                    //myViewFragment = frag.getView();
                    //hamb = (ImageView) myViewFragment.findViewById(R.id.hamb);
                    return true;
                case R.id.navigation_dashboard:
                    //frag = new ClientFragment();
                    selected = 2;


                    getSupportFragmentManager().beginTransaction()
                            //.addToBackStack(null)
                            .replace(R.id.contenu, fragRetour)
                            .commit();

                    //myViewFragment = fragClient.getView();
                    //hamb = (ImageView) myViewFragment.findViewById(R.id.hambClient);
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pointeur);
        if (!SharedPref.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }else{fragSortie = new SortieFragment();
            fragRetour = new RetourFragment();
            ((SortieFragment) fragSortie).setFragmentActionListener(this);
            ((RetourFragment) fragRetour).setFragmentActionListener(this);

            getSupportFragmentManager().beginTransaction()

                    //.addToBackStack(null)
                    .add(R.id.contenu, fragSortie)
                    .commit();
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
                                    isAnimated = false;
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                    }
                }
            };

            thread.start();

/////////////////////////////////////////////////////////////////////////////
            BottomNavigationView navView = findViewById(R.id.bottom_nav_view);
            ///////////////////////////////////////////////////////////////////////////////
            int[][] states = new int[][]{
                    //new int[] { android.R.attr.state_enabled}, // enabled
                    //new int[] {-android.R.attr.state_enabled}, // disabled
                    new int[]{-android.R.attr.state_checked}, // unchecked
                    new int[]{android.R.attr.state_checked}  // pressed
            };

            int[] colors = new int[]{
                    //Color.BLACK,
                    //Color.RED,
                    Color.GRAY,
                    Color.rgb(44, 8, 88)
            };

            ColorStateList myList = new ColorStateList(states, colors);

            ///////////////////////////////////////////////////////////////////////////////
            navView.setItemIconTintList(myList);
            navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            NavigationView navigationView = findViewById(R.id.nav_view);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
                @Override
                public void onDrawerSlide(View view, float v) {

                    if (selected == 1) {

                        if (v > .5) {
                            myViewFragment = fragSortie.getView();
                            hamb = (ImageView) myViewFragment.findViewById(R.id.hamb);
                            animation = (Animation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_hamb);
                            hamb.startAnimation(animation);
                            isAnimated = true;
                        }

                        if ((v > .2) && (!isAnimated)) {
                            myViewFragment = fragSortie.getView();
                            hamb = (ImageView) myViewFragment.findViewById(R.id.hamb);
                            animation = (Animation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_hamb);
                            hamb.startAnimation(animation);

                        }
                    }

                    if (selected == 2) {

                        if (v > .5) {
                            myViewFragment = fragRetour.getView();
                            hamb = (ImageView) myViewFragment.findViewById(R.id.hamb);
                            animation = (Animation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_hamb);
                            hamb.startAnimation(animation);
                            isAnimated = true;
                        }

                        if ((v > .2) && (!isAnimated)) {
                            myViewFragment = fragRetour.getView();
                            hamb = (ImageView) myViewFragment.findViewById(R.id.hamb);
                            animation = (Animation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_hamb);
                            hamb.startAnimation(animation);

                        }
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
        }
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

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }else if (id == R.id.nav_logout) {
            //logging out
            SharedPref.getInstance(getApplicationContext()).logout();
            startActivity(new Intent(this, LoginActivity.class));
            finish();


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onActionPerformed(Bundle bundle) {

    }



    @Override
    public void openDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(!drawer.isDrawerOpen(GravityCompat.START))
            drawer.openDrawer(GravityCompat.START);
    }



}
