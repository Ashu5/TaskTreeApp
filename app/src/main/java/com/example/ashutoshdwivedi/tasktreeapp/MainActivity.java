package com.example.ashutoshdwivedi.tasktreeapp;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static androidx.appcompat.app.ActionBar.*;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener,ProfileFragment.OnFragmentInteractionListener,
        AddExpenses.OnFragmentInteractionListener,SearchFragment.OnFragmentInteractionListener, ExpensiaApp.ExpensiaAppController,ConnectivityReceiver.ConnectivityReceiverListener

 {

    private ActionBar mActionbar;
    private  AppCompatTextView mTitleTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActionbar = getSupportActionBar();
        mActionbar.setTitle("Expensia");
        //mActionbar.setCustomView(R.layout.custom_toolbar);
         //onBindViews();
        //loadFragment(new HomeFragment());
        checkNetworkAvailable();

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnSelectedItemListener);

        
    }



private BottomNavigationView.OnNavigationItemSelectedListener mOnSelectedItemListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id=menuItem.getItemId();

        switch(id){

            case R.id.home_nav:

               loadFragment(new HomeFragment());
                mActionbar.setTitle("Expensia");
                break;
            case R.id.add_nav:
                mActionbar.setTitle("Add Expenses");
                loadFragment(new AddExpenses());
                break;
            case R.id.search_nav:
                mActionbar.setTitle("Search");
                loadFragment(new SearchFragment());
                break;
            case R.id.profile_nav:
                mActionbar.setTitle("Profile");
                loadFragment(new ProfileFragment());
                break;

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
         transaction.replace(R.id.frame_container, fragment,fragment.getClass().getSimpleName());
         transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
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


     @Override
     public void onBindViews() {

         //mTitleTextView = new AppCompatTextView(getApplicationContext());
         //mTitleTextView.setSingleLine();
         LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
         layoutParams.gravity = Gravity.CENTER;
         //mActionbar.setCustomView(mTitleTextView, layoutParams);
         //mActionbar.setDisplayOptions(DISPLAY_SHOW_CUSTOM | DISPLAY_HOME_AS_UP);
         //mTitleTextView.setText("Expensia");
         //mTitleTextView.setTextAppearance(getApplicationContext(), android.R.style.TextAppearance_Medium);
     }


     @Override
     public void onBackPressed() {
      // trackFragments();
       int count=getSupportFragmentManager().getBackStackEntryCount();
       if(count==0){
           super.onBackPressed();
       }
       else {
           getSupportFragmentManager().popBackStack();
       }

     }


     /*private void trackFragments(){
         List<Fragment> fragments=getSupportFragmentManager().getFragments();

         for(Fragment fragment:fragments){
             if(fragment!=null && fragment instanceof HomeFragment){
                 ((HomeFragment) fragment).onBackPressed();
             }
         }
     }*/
 }
