package info.omia.sport;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import java.io.IOException;


public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DataBaseHelper dataBaseHelper;

    private RelativeLayout home;
    private RelativeLayout edit;
    private RelativeLayout show;
    private RelativeLayout settings;

    private RelativeLayout createschue;
    private Button createschueb;
    private TextView createschueT;
    private EditText namet;
    private EditText vornamet;
    private Spinner klasses;
    private Spinner geschlechts;

    private RelativeLayout createKlasse;
    private Button createklasb;
    private TextView createklasT;

    private GridView edit_list;

    private String[] edit_buttons = {"edit_Klassen","edit_Schüler","edit_Daten","Create_Klassen","Create_Schüler","Create_Daten"};
    private String[] geschlecht_spinner = new String[] {"male", "female"};
    private ArrayAdapter<String> spinnerAdapterklassecre;

    private Spinner showspin1;
    private Spinner showspin2;
    private Spinner showspin3;
    private ArrayAdapter<String> shoespinnadap1;
    private ArrayAdapter<String> shoespinnadap2;
    private ArrayAdapter<String> shoespinnadap3;
    private Button showbut;
    private TextView showText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.menue_home);


        dataBaseHelper= new DataBaseHelper(this);
        try {
            dataBaseHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataBaseHelper.openDataBase();
        home= findViewById(R.id.home_show);
        show= findViewById(R.id.show_show);
        edit= findViewById(R.id.edit_show);
        settings= findViewById(R.id.settings_show);

        createschue= findViewById(R.id.edit_create_view_schueler);
        createschueT=findViewById(R.id.create_schue_text);
        geschlechts= findViewById(R.id.geschlecht_spinner);
        namet= findViewById(R.id.editcreate_Name);
        vornamet= findViewById(R.id.editcreate_vorname);
        ArrayAdapter<String> adaptergeschl = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, geschlecht_spinner);
        adaptergeschl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        geschlechts.setAdapter(adaptergeschl);
        createschueb=findViewById(R.id.create_schue_button);
        klasses= findViewById(R.id.Klasse_spinner);
        spinnerAdapterklassecre = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapterklassecre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        klasses.setAdapter(spinnerAdapterklassecre);
        final int[] createschue = {0};
        createschueb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (createschue[0] ==0) {
                    if (namet.getText() == null || namet.getText().equals("")) {
                        createklasT.setText(null);
                        createklasT.append("Name fehlt! \n");
                        createschue[0] =-1;
                    }
                    if (vornamet.getText() == null|| vornamet.getText().equals("")) {
                        createklasT.append("Vorname fehlt! \n");
                        createschue[0] =-1;
                    }
                    if (createschue[0]==0){
                        createklasT.setText(null);createklasT.append("Beim naechsten drücken des Knopfen werden die Daten eingespeichert");
                        createschue[0]=1;
                    }
                }else if (createschue[0]==1){
                    //TODO insert into sql
                    createschue[0]=0;
                    hideall();
                    edit.setVisibility(View.VISIBLE);
                }
            }
        });

        //System.out.println(cursorToString(dataBaseHelper.getTestData("select * from sportdata")));

        createKlasse= findViewById(R.id.edit_create_view_Klasse);
        createklasb= findViewById(R.id.create_klasse_button);
        createklasb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        createklasT= findViewById(R.id.create_klass_text);

        edit_list= findViewById(R.id.edit_chose_show);

        edit_list.setAdapter(new ButtonAdapter(this,this,edit_buttons));
        home.setVisibility(View.VISIBLE);


        showspin1=findViewById(R.id.show_spin1);
        shoespinnadap1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        shoespinnadap1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        showspin1.setAdapter(shoespinnadap1);

        showspin2=findViewById(R.id.show_spin2);
        shoespinnadap2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        shoespinnadap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        showspin2.setAdapter(shoespinnadap2);

        showspin3=findViewById(R.id.show_spin3);
        shoespinnadap3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        shoespinnadap3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        showspin3.setAdapter(shoespinnadap3);

        showText=findViewById(R.id.show_table);
        showbut=findViewById(R.id.show_but1);
        showbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showText.setText(null);
                String Stufe = showspin1.getSelectedItem().toString();
                String Sport = showspin2.getSelectedItem().toString();
                String gesch = showspin3.getSelectedItem().toString();

                showText.setText(cursorToTable(dataBaseHelper.getTestData("select  Info,Wert,Punkte as Notenpunkte from sportdata where stufe='"+Stufe+"' and sportart='"+Sport+"' and geschlecht='"+gesch+"'")));
            }
        });


        update();
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
            hideall();
            home.setVisibility(View.VISIBLE);
        } else if (id == R.id.menue_show) {
            hideall();
            show.setVisibility(View.VISIBLE);
        } else if (id == R.id.menue_edit) {
            hideall();
            edit.setVisibility(View.VISIBLE);
        } else if (id == R.id.menue_edit) {
            hideall();
            settings.setVisibility(View.VISIBLE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void hideall(){
        home.setVisibility(View.INVISIBLE);
        show.setVisibility(View.INVISIBLE);
        edit.setVisibility(View.INVISIBLE);
        settings.setVisibility(View.INVISIBLE);
        createschue.setVisibility(View.INVISIBLE);
        createKlasse.setVisibility(View.INVISIBLE);

    }

    private void update(){
        removeallinfo();
        spinnerAdapterklassecre.clear();
        addcursertospinner(spinnerAdapterklassecre,dataBaseHelper.getTestData("select klasse from klassen"));


        shoespinnadap1.clear();
        shoespinnadap2.clear();
        shoespinnadap3.clear();
        addcursertospinner(shoespinnadap1,dataBaseHelper.getTestData("select distinct stufe from sportdata"));
        addcursertospinner(shoespinnadap2,dataBaseHelper.getTestData("select distinct Sportart from sportdata"));
        addcursertospinner(shoespinnadap3,dataBaseHelper.getTestData("select distinct Geschlecht from sportdata"));
    }

    private void removeallinfo(){
        createschueT.setText(null);
        createschueT.append("Daten zum erstellen eines Schülers angeben.");
        namet.setText(null);
        vornamet.setText(null);
        showText.setText(null);

    }


    public void buttonprest(String Name){
        System.out.println(Name);
        hideall();
        switch (Name){
            case "edit_Klassen":break;
            case "edit_Schüler":break;
            case "edit_Daten":break;
            case "Create_Klassen":createKlasse.setVisibility(View.VISIBLE);break;
            case "Create_Schüler":createschue.setVisibility(View.VISIBLE);break;
            case "Create_Daten":break;
        }
        }

    private String cursorToTable(Cursor cursor){
        String cursorString = "";
        if (cursor.moveToFirst() ){
            String[] columnNames = cursor.getColumnNames();
            for (String name: columnNames)
                cursorString += " [ "+String.format("%s ]", name);
            cursorString += "\n";
            do {
                for (String name: columnNames) {
                    cursorString += " [ "+String.format("%s ]",
                            cursor.getString(cursor.getColumnIndex(name)));
                }
                cursorString += "\n";
            } while (cursor.moveToNext());
        }
        return cursorString;
    }

    private void addcursertospinner(ArrayAdapter Spinner,Cursor cursor){
        if (cursor.moveToFirst() ){
            String[] columnNames = cursor.getColumnNames();
            for (String name: columnNames)do {Spinner.add(cursor.getString(cursor.getColumnIndex(name)));}while (cursor.moveToNext());

        }
    }
}

