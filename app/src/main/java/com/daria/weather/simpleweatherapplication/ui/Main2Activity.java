package com.daria.weather.simpleweatherapplication.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.daria.weather.simpleweatherapplication.R;

//import com.daria.weather.simpleweatherapplication.di.TestDIInterface;

public class Main2Activity extends AppCompatActivity {

//    @Inject
//    TestDIInterface testDIInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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


//        testDIInterface = DaggerTestDIInterface.builder().build();
//        getApplicationContext()
//                .getApplicationComponent()
//                .newActivityComponentBuilder()
//                .activity(this)
//                .build()
//                .inject(this);
    }


}
