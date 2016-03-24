package com.example.akiva.cmsc434doodler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private android.support.design.widget.FloatingActionButton _clearDoodle;
    private android.support.design.widget.FloatingActionButton _undo;
    private android.support.design.widget.FloatingActionButton _redo;
    private android.support.design.widget.FloatingActionButton _settings;
    private static final int SETTINGS = 0;

    View _doodleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create views and buttons
        _doodleView = (View) findViewById(R.id.doodleView);
        _clearDoodle = (android.support.design.widget.FloatingActionButton) findViewById(R.id.clearFab);
        _undo = (android.support.design.widget.FloatingActionButton) findViewById(R.id.undoFab);
        _redo = (android.support.design.widget.FloatingActionButton) findViewById(R.id.redoFab);
        _settings =(android.support.design.widget.FloatingActionButton)  findViewById(R.id.fab);


        //set up button listeners
        _clearDoodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((DoodleView) _doodleView).resetPath();
            }
        });
        _undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((DoodleView) _doodleView).onOndo();

            }
        });

        _redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((DoodleView) _doodleView).onRedo();

            }
        });

        _settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pass current settings to settings page. This is important for when numerous changes to settings made
                Intent outgoing = new Intent(MainActivity.this, SettingActivity.class);
                outgoing.putExtra(SettingActivity.COLOR, "" + ((DoodleView) _doodleView).get_currColor());
                outgoing.putExtra(SettingActivity.BRUSH_SIZE, "" + ((DoodleView) _doodleView).get_currBrushSize());
                outgoing.putExtra(SettingActivity.OPAC, "" + ((DoodleView) _doodleView).get_currOpacity());


                startActivityForResult(outgoing, SETTINGS);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(SETTINGS==requestCode && RESULT_OK==resultCode){
            //update DoodleView with changed settings
            ((DoodleView) _doodleView).updateArt(data.getStringExtra(SettingActivity.COLOR), data.getStringExtra(SettingActivity.BRUSH_SIZE), data.getStringExtra(SettingActivity.OPAC));
        }
    }
}


