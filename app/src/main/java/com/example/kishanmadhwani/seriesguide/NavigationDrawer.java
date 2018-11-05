package com.example.kishanmadhwani.seriesguide;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class NavigationDrawer extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    FragmentManager fragmentManager;
    Context ctx;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer=findViewById(R.id.nav_view);
        ctx=getApplicationContext();
        //this.deleteDatabase("SeriesGuide.db");

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.content_frame, new ShowsMainFragment()).commitNow();

        initNavigationDrawer(nvDrawer);
    }


    //handles navigation drawer's actions
    public void initNavigationDrawer(NavigationView navigationView ){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Fragment fragment=null;

                int id = menuItem.getItemId();

                switch (id){
                    case R.id.shows:
                        Log.d("checkclick","shows clicked");
                        fragment =new ShowsMainFragment();
                        break;
                    case R.id.lists:
                        fragment =new ListsMainFragment();
                        break;
                    case R.id.settings:
                        i = new Intent(getApplicationContext() , Settings.class);
                        startActivity(i);
                        mDrawer.closeDrawers();
                        break;
                    case R.id.statistics:
                        mDrawer.closeDrawers();
                        break;
                    case R.id.movies:
                        fragment =new MoviesMainFragment();
                        break;
                    case R.id.unlock:
                        i = new Intent(getApplicationContext() , Unlock.class);
                        startActivity(i);
                        mDrawer.closeDrawers();
                        break;
                    case R.id.help:
                        i = new Intent(getApplicationContext() , Help.class);
                        startActivity(i);
                        mDrawer.closeDrawers();
                        break;
                }

                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commitNow();
                menuItem.setChecked(true);
                mDrawer.closeDrawers();
                return true;
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
