package com.example.android.sbfpsoundboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mattPage = (TextView)findViewById(R.id.matt);
        TextView patPage = (TextView)findViewById(R.id.pat);
        TextView liamPage = (TextView)findViewById(R.id.liam);
        TextView wooliePage = (TextView)findViewById(R.id.woolie);
        TextView groupPage = (TextView)findViewById(R.id.group);

        mattPage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new intent to open the {@link NumbersActivity}
                Intent mattIntent = new Intent(MainActivity.this, MattActivity.class);

                //Start the new activity
                startActivity(mattIntent);
            }
        });

        patPage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new intent to open the {@link NumbersActivity}
                Intent patIntent = new Intent(MainActivity.this, PatActivity.class);

                //Start the new activity
                startActivity(patIntent);
            }
        });

        liamPage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new intent to open the {@link NumbersActivity}
                Intent liamIntent = new Intent(MainActivity.this, LiamActivity.class);

                //Start the new activity
                startActivity(liamIntent);
            }
        });

        wooliePage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new intent to open the {@link NumbersActivity}
                Intent woolieIntent = new Intent(MainActivity.this, WoolieActivity.class);

                //Start the new activity
                startActivity(woolieIntent);
            }
        });

        groupPage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new intent to open the {@link NumbersActivity}
                Intent groupIntent = new Intent(MainActivity.this, GroupActivity.class);

                //Start the new activity
                startActivity(groupIntent);
            }
        });
    }
}
