package info.omia.sport;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;


public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RelativeLayout home;
    private RelativeLayout edit;
    private RelativeLayout show;
    private RelativeLayout settings;

    private GridView edit_list;

    private String[] edit_buttons = {"Klassen","Schüler","Daten","Klassen","Schüler","Daten"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.menue_home);


        home= findViewById(R.id.home_show);
        show= findViewById(R.id.show_show);
        edit= findViewById(R.id.edit_show);
        settings= findViewById(R.id.settings_show);

        edit_list= findViewById(R.id.edit_chose_show);

        edit_list.setAdapter(new ButtonAdapter(this,this,edit_buttons));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
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
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menue_home){
            home.setVisibility(View.VISIBLE);
            show.setVisibility(View.INVISIBLE);
            edit.setVisibility(View.INVISIBLE);
            settings.setVisibility(View.INVISIBLE);
        } else if (id == R.id.menue_show) {
            home.setVisibility(View.INVISIBLE);
            show.setVisibility(View.VISIBLE);
            edit.setVisibility(View.INVISIBLE);
            settings.setVisibility(View.INVISIBLE);
        } else if (id == R.id.menue_edit) {
            home.setVisibility(View.INVISIBLE);
            show.setVisibility(View.INVISIBLE);
            edit.setVisibility(View.VISIBLE);
            settings.setVisibility(View.INVISIBLE);
        } else if (id == R.id.menue_edit) {
            home.setVisibility(View.INVISIBLE);
            show.setVisibility(View.INVISIBLE);
            edit.setVisibility(View.INVISIBLE);
            settings.setVisibility(View.VISIBLE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void buttonprest(String Name){}
}
