package com.rytalo.realmproduct;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import Model.Person;
import Model.Products;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MainActivity extends ActionBarActivity {
    private Realm realm;
    private RealmConfiguration realmConfig;
    public static final String TAG=MainActivity.class.getName();
    private LinearLayout rootLayout = null;
    Button addproducts;
    Button deleteproducts;
    Button viewproducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootLayout = ((LinearLayout) findViewById(R.id.container));
        rootLayout.removeAllViews();
        addproducts=(Button)findViewById(R.id.add);
        deleteproducts=(Button)findViewById(R.id.delete);
        viewproducts=(Button)findViewById(R.id.view);
        // Create the Realm configuration
        realmConfig = new RealmConfiguration.Builder(this).build();
        // Open the Realm for the UI thread.
        realm = Realm.getInstance(realmConfig);
        addproducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });
viewproducts.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        viewProduct();
    }
});
        deleteproducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProduct();
            }
        });
    }

    private void showStatus(String txt) {
        Log.i(TAG, txt);
        TextView tv = new TextView(this);
        tv.setText(txt);
        rootLayout.addView(tv);
    }
    private void addProduct(){
        realm.beginTransaction();


        for (int i = 0; i < 10; i++) {
            Person person = realm.createObject(Person.class);

            person.setName("Person no. :" + i);


            // The field tempReference is annotated with @Ignore.
            // This means setTempReference sets the Person tempReference
            // field directly. The tempReference is NOT saved as part of
            // the RealmObject:


            for (int j = 0; j < i; j++) {
                Products products = realm.createObject(Products.class);
                products.setName("products_" + j);
                person.getProductses().add(products);
            }
        }
        realm.commitTransaction();

        // Implicit read transactions allow you to access your objects

    }
    private void deleteProduct(){

        realm.beginTransaction();
        realm.allObjects(Person.class).remove(0);
        // showStatus(person.getName()+":"+person.getAge());
        realm.commitTransaction();
    }
    private  void viewProduct(){
        showStatus( "\nNumber of persons: " + realm.allObjects(Person.class).size());
        for (Person pers : realm.allObjects(Person.class)) {



            showStatus( "\n" + pers.getName() +":" + pers.getProductses().size());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
