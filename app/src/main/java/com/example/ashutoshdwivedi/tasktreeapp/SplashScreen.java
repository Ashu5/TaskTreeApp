package com.example.ashutoshdwivedi.tasktreeapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    TextView tv;
    ImageView imgView;
    View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.splash_screen);
        tv=rootView.findViewById(R.id.welcome);
        imgView=rootView.findViewById(R.id.img);



    }
}
