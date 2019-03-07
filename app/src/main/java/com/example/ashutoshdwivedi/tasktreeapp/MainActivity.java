package com.example.ashutoshdwivedi.tasktreeapp;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener,ProfileFragment.OnFragmentInteractionListener,
        AddExpenses.OnFragmentInteractionListener,SearchFragment.OnFragmentInteractionListener, ConnectivityReceiver.ConnectivityReceiverListener

 {

    private ActionBar mActionbar;

    int SELECTED_INDEX=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActionbar = getSupportActionBar();
        mActionbar.setTitle("Expensia");



        //loadFragment(new HomeFragment());
        checkNetworkAvailable();

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnSelectedItemListener);

        
    }



private BottomNavigationView.OnNavigationItemSelectedListener mOnSelectedItemListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        switch(menuItem.getItemId()){

            case R.id.home_nav:
                loadFragment(new HomeFragment());
                mActionbar.setTitle("Expensia");
                return true;

            case R.id.profile_nav:
                loadFragment(new ProfileFragment());
                mActionbar.setTitle("Profile");

                return true;
            case R.id.add_nav:
                loadFragment(new AddExpenses());
                mActionbar.setTitle("Add Expenses");
                return true;
            case R.id.search_nav:
                loadFragment(new SearchFragment());
                mActionbar.setTitle("Search");
                Log.d("Test","Testing Clicks33");
                return true;
        }

   return true;
    }
};



 public void isNetworkAvailable(boolean isConnected) {

     if (!isConnected) {
         Toast.makeText(this, "Network is Unavailabe .Please try again.", Toast.LENGTH_LONG).show();
         Log.i("NETWORK_TEST","Testing for network!!");
         /*Snackbar snackbar= Snackbar.make(,"Network is Unavailabe .Please try again",Snackbar.LENGTH_LONG);
         snackbar.show();*/
     }

 }

 public void checkNetworkAvailable(){
     boolean checkConnection=ConnectivityReceiver.isConnected();
     isNetworkAvailable(checkConnection);
 }


    private  void loadFragment(Fragment fragment){
     if(fragment!=null){
         FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
         transaction.replace(R.id.frame_container, fragment);
         transaction.addToBackStack(null);
         transaction.commit();
        }
        else
     {
         Toast.makeText(this,"Try again ",Toast.LENGTH_SHORT).show();
     }

    }

     @Override
     public void onFragmentInteraction(Uri uri) {

     }


     @Override
     public void onNetworkConnectionChanged(boolean isConnected) {
     isNetworkAvailable(isConnected);
     }

     @Override
     protected void onResume() {
         super.onResume();
         ExpensiaApp.getInstance().setConnectivityListener(this);
     }

 }
