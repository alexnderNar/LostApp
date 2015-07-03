package com.dot.myapplication.Application;

import android.app.Fragment;

import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dot.myapplication.R;

import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {





    private Toolbar toolbar;
    View root;
    NavigationView nav_draw;
    DrawerLayout drawer_layout;
    Fragment frag1;

    Fragment fragMap;
    Fragment fragMapTemp;
    Fragment fragActionMap;
    FragmentTransaction fTrans;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        root = getLayoutInflater().inflate(R.layout.activity_main, null);
        setContentView(root);



        frag1 = new Fragment1();
        fragMap = new MyMapFragment();
        fragMapTemp = new MyMapFragment();
        fragActionMap = new ActionMapFragment();




        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        /*final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_action_menu);
        ab.setDisplayHomeAsUpEnabled(true);*/

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav_draw = (NavigationView) findViewById(R.id.nav_draw);
        nav_draw.setNavigationItemSelectedListener(this);

   /* getSupportFragmentManager().beginTransaction()
            .replace(R.id.container, new AboutPagerFragment())
            .commit();*/

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer_layout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void RemoveMapFragments(){ //Нужно т.к. frame_map_container перекрывает frame_container
        fTrans.remove(fragActionMap);
        fTrans.remove(fragMap);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        int id = menuItem.getItemId();
        fTrans = getFragmentManager().beginTransaction();

        switch (id){
            case R.id.navigation_item_1:
                //fTrans.remove(fragMap);
                fTrans.replace(R.id.frame_map_container, fragActionMap);
                break;
            case R.id.navigation_item_2:
                fTrans.replace(R.id.frame_map_container,fragMap);

                break;
            case R.id.navigation_sub_item_1:
                RemoveMapFragments();
                fTrans.replace(R.id.frame_container, frag1);

                break;
            default:
                break;

        }
        fTrans.addToBackStack(null);
        fTrans.commit();
        menuItem.setChecked(true);
        drawer_layout.closeDrawers();

        return false;
    }



@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
}

@Override
public void onBackPressed() {
    if(drawer_layout.isDrawerOpen(Gravity.LEFT)){
        drawer_layout.closeDrawer(Gravity.LEFT);
    }
    else if (getFragmentManager().getBackStackEntryCount() > 0){
        getFragmentManager().popBackStack();
    }

    else {
        super.onBackPressed();
    }

}
}

