package com.prom.eazy;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.prom.eazy.ui.login.LoginActivity;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentActionListener {
    Fragment frag = null;    Fragment fragClient = null;     Fragment fragStock = null;
    View  myViewFragment = null;
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
                            .replace(R.id.contenu, frag)
                            .commit();

                    //myViewFragment = frag.getView();
                    //hamb = (ImageView) myViewFragment.findViewById(R.id.hamb);
                    return true;
                case R.id.navigation_dashboard:
                    //frag = new ClientFragment();
                    selected = 2;


                    getSupportFragmentManager().beginTransaction()
                            //.addToBackStack(null)
                            .replace(R.id.contenu, fragClient)
                            .commit();

                    //myViewFragment = fragClient.getView();
                    //hamb = (ImageView) myViewFragment.findViewById(R.id.hambClient);
                    return true;
                case R.id.navigation_notifications:
                    //frag = new StockFragment();
                    selected = 3;

                    getSupportFragmentManager().beginTransaction()
                            //.addToBackStack(null)
                            .replace(R.id.contenu, fragStock)
                            .commit();

                    //myViewFragment = fragStock.getView();
                    //hamb = (ImageView) myViewFragment.findViewById(R.id.hambStock);
                    return true;
            }


            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!SharedPref.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }else{

        frag = new HomeFragment();
        fragClient = new ClientFragment();
        fragStock = new StockFragment();
        ((HomeFragment) frag).setFragmentActionListener(this);
        ((ClientFragment) fragClient).setFragmentActionListener(this);
        ((StockFragment) fragStock).setFragmentActionListener(this);
        //myViewFragment = frag.getView();
        //hamb = (ImageView) myViewFragment.findViewById(R.id.hamb);

        getSupportFragmentManager().beginTransaction()

                //.addToBackStack(null)
                .add(R.id.contenu, frag)
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

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
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
                        myViewFragment = frag.getView();
                        hamb = (ImageView) myViewFragment.findViewById(R.id.hamb);
                        animation = (Animation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_hamb);
                        hamb.startAnimation(animation);
                        isAnimated = true;
                    }

                    if ((v > .2) && (!isAnimated)) {
                        myViewFragment = frag.getView();
                        hamb = (ImageView) myViewFragment.findViewById(R.id.hamb);
                        animation = (Animation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_hamb);
                        hamb.startAnimation(animation);

                    }
                }

                if (selected == 2) {

                    if (v > .5) {
                        myViewFragment = fragClient.getView();
                        hamb = (ImageView) myViewFragment.findViewById(R.id.hambClient);
                        animation = (Animation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_hamb);
                        hamb.startAnimation(animation);
                        isAnimated = true;
                    }

                    if ((v > .2) && (!isAnimated)) {
                        myViewFragment = fragClient.getView();
                        hamb = (ImageView) myViewFragment.findViewById(R.id.hambClient);
                        animation = (Animation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_hamb);
                        hamb.startAnimation(animation);

                    }
                }

                if (selected == 3) {

                    if (v > .5) {
                        myViewFragment = fragStock.getView();
                        hamb = (ImageView) myViewFragment.findViewById(R.id.hambStock);
                        animation = (Animation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_hamb);
                        hamb.startAnimation(animation);
                        isAnimated = true;
                    }

                    if ((v > .2) && (!isAnimated)) {
                        myViewFragment = fragStock.getView();
                        hamb = (ImageView) myViewFragment.findViewById(R.id.hambStock);
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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
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

