package com.example.akiva.cmsc434doodler;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;

/**
 * Created by Akiva on 3/20/16.
 */
public class SettingActivity extends Activity{
    //brush size, color, and opacity as well as clear the current sketch

    private RadioGroup _brushSize;
    private SeekBar _opacity;
    private Button _red;
    private Button _orange;
    private Button _yellow;
    private Button _green;
    private Button _blue;
    private Button _violet;
    private Button _black;
    private Button _teal;
    private Button _white;
    private Button _reset;
    private Button _apply;
    private String  _currColor=BLUE;
    private int _opacityLevel=255;
    private final static int SMALL_BRUSH_SIZE=10;
    private final static int MEDIUM_BRUSH_SIZE=20;
    private final static int LARGE_BRUSH_SIZE=30;
    private final static String RED="#fc0000";
    private final static String ORANGE="#f76a05";
    private final static String YELLOW="#f8e725";
    private final static String GREEN="#36f00c";
    private final static String BLUE="#092beb";
    private final static String VIOLET="#b107f4";
    private final static String BLACK="#000000";
    private final static String TEAL="#06f4d1";
    private final static String WHITE="#ffffff";
    public final static String BRUSH_SIZE="brushSize";
    public final static String COLOR="color";
    public final static String OPAC="opacity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        //brush size radio group
        _brushSize= (RadioGroup) findViewById(R.id.radioGroupBrushSize);

        //buttons for color options
        _red=(Button) findViewById(R.id.RED);
        _orange=(Button) findViewById(R.id.ORANGE);
        _yellow=(Button) findViewById(R.id.YELLOW);
        _green= (Button) findViewById(R.id.GREEN);
        _blue= (Button) findViewById(R.id.BLUE);
        _violet=(Button) findViewById(R.id.VIOLET);
        _black= (Button) findViewById(R.id.BLACK);
        _teal= (Button) findViewById(R.id.TEAL);
        _white= (Button) findViewById(R.id.WHITE);


        //SeekBar for Opacity
        _opacity=(SeekBar) findViewById(R.id.opacitySeekBar);

        //Apply and reset buttons
        _apply=(Button) findViewById(R.id.applyChanges);
        _reset=(Button) findViewById(R.id.resetDefault);



        //setup
        if(getIntent()!=null) {

            //get color out of intent and make it "selected" in the SettingActivity
            if (getIntent().getStringExtra(COLOR).equals(RED)) {
                resetOrigColors();
                _red.setBackground(getDrawable(R.drawable.red_highlight));
                _currColor=RED;
            } else if (getIntent().getStringExtra(COLOR).equals(ORANGE)) {
                resetOrigColors();
                _orange.setBackground(getDrawable(R.drawable.orange_highlight));
                _currColor=ORANGE;
            } else if (getIntent().getStringExtra(COLOR).equals(YELLOW)) {
                resetOrigColors();
                _yellow.setBackground(getDrawable(R.drawable.yellow_highlight));
                _currColor=YELLOW;
            } else if (getIntent().getStringExtra(COLOR).equals(GREEN)) {
                resetOrigColors();
                _green.setBackground(getDrawable(R.drawable.green_highlight));
                _currColor=GREEN;
            } else if (getIntent().getStringExtra(COLOR).equals(BLUE)) {
                resetOrigColors();
                _blue.setBackground(getDrawable(R.drawable.blue_highlight));
                _currColor=BLUE;
            } else if (getIntent().getStringExtra(COLOR).equals(VIOLET)) {
                resetOrigColors();
                _violet.setBackground(getDrawable(R.drawable.violet_highlight));
                _currColor=VIOLET;
            } else if (getIntent().getStringExtra(COLOR).equals(BLACK)) {
                resetOrigColors();
                _black.setBackground(getDrawable(R.drawable.black_highlight));
                _currColor=BLACK;
            } else if (getIntent().getStringExtra(COLOR).equals(TEAL)) {
                resetOrigColors();
                _teal.setBackground(getDrawable(R.drawable.teal_highlight));
                _currColor=TEAL;
            } else if (getIntent().getStringExtra(COLOR).equals(WHITE)) {
                resetOrigColors();
                _white.setBackground(getDrawable(R.drawable.white_highlight));
                _currColor=WHITE;

            }

            //update paintbrush size
            if (Integer.parseInt(getIntent().getStringExtra(BRUSH_SIZE)) == SMALL_BRUSH_SIZE) {
                _brushSize.check(R.id.small);
            } else if (Integer.parseInt(getIntent().getStringExtra(BRUSH_SIZE)) == MEDIUM_BRUSH_SIZE) {
                _brushSize.check(R.id.medium);
            } else if (Integer.parseInt(getIntent().getStringExtra(BRUSH_SIZE)) == LARGE_BRUSH_SIZE) {
                _brushSize.check(R.id.large);
            }

            //update opacity
            _opacity.setProgress(Integer.parseInt(getIntent().getStringExtra(OPAC)));
            _opacityLevel=Integer.parseInt(getIntent().getStringExtra(OPAC));


        }

        //button to apply changes
        _apply.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data= new Intent();
                data.putExtra(BRUSH_SIZE, ""+getBrushSize());
                data.putExtra(OPAC, ""+_opacityLevel);
                data.putExtra(COLOR, ""+_currColor);


                setResult(RESULT_OK, data);
                finish();

            }
        });

        //button to reset default settings
        _reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                _currColor=BLUE;
                resetOrigColors();
                _blue.setBackground(getDrawable(R.drawable.blue_highlight));
                _brushSize.check(R.id.medium);
                _opacity.setProgress(255);

            }
        });


        //functionality to highlight new selected button
        _red.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    _currColor=RED;
                    resetOrigColors();
                    _red.setBackground(getDrawable(R.drawable.red_highlight));
                }
        });
        _orange.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                _currColor=ORANGE;
                resetOrigColors();

                _orange.setBackground(getDrawable(R.drawable.orange_highlight));
            }
        });
        _yellow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                _currColor=YELLOW;
                resetOrigColors();
                _yellow.setBackground(getDrawable(R.drawable.yellow_highlight));
            }
        });
        _green.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                _currColor=GREEN;
                resetOrigColors();
                _green.setBackground(getDrawable(R.drawable.green_highlight));
            }
        });
        _blue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                _currColor=BLUE;
                resetOrigColors();
                _blue.setBackground(getDrawable(R.drawable.blue_highlight));
            }
        });
        _violet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                _currColor=VIOLET;
                resetOrigColors();
                _violet.setBackground(getDrawable(R.drawable.violet_highlight));
            }
        });
        _black.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                _currColor=BLACK;
                resetOrigColors();
                _black.setBackground(getDrawable(R.drawable.black_highlight));
            }
        });
        _teal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                _currColor=TEAL;
                resetOrigColors();
                _teal.setBackground(getDrawable(R.drawable.teal_highlight));
            }
        });
        _white.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                _currColor = WHITE;
                resetOrigColors();
                _white.setBackground(getDrawable(R.drawable.white_highlight));
            }
        });


        //track changes to SeekBar to update opacity
        _opacity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                _opacity.setProgress(progress);
                _opacityLevel =progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }



    //to simplify process of changing selected color, all color buttons are reset to original colors
    //and then then the selected color gets modified above
    private void resetOrigColors(){

        //resetting red
        GradientDrawable _setRemainderRed = new GradientDrawable();
        _setRemainderRed.setColor(Color.parseColor(RED));
        _red.setBackground(_setRemainderRed);


        //resetting orange
        GradientDrawable _setRemainderOrange = new GradientDrawable();
        _setRemainderOrange.setColor(Color.parseColor(ORANGE));
        _orange.setBackground(_setRemainderOrange);

        //resetting yellow
        GradientDrawable _setRemainderYellow = new GradientDrawable();
        _setRemainderYellow.setColor(Color.parseColor(YELLOW));
        _yellow.setBackground(_setRemainderYellow);

        //resetting green
        GradientDrawable _setRemainderGreen = new GradientDrawable();
        _setRemainderGreen.setColor(Color.parseColor(GREEN));
        _green.setBackground(_setRemainderGreen);

        //resetting blue
        GradientDrawable _setRemainderBlue = new GradientDrawable();
        _setRemainderBlue.setColor(Color.parseColor(BLUE));
        _blue.setBackground(_setRemainderBlue);

        //resetting violet
        GradientDrawable _setRemainderViolet = new GradientDrawable();
        _setRemainderViolet.setColor(Color.parseColor(VIOLET));
        _violet.setBackground(_setRemainderViolet);

        //resetting black
        GradientDrawable _setRemainderBlack = new GradientDrawable();
        _setRemainderBlack.setColor(Color.parseColor(BLACK));
        _black.setBackground(_setRemainderBlack);

        //resetting teal
        GradientDrawable _setRemainderTeal = new GradientDrawable();
        _setRemainderTeal.setColor(Color.parseColor(TEAL));
        _teal.setBackground(_setRemainderTeal);

        //resetting white
        GradientDrawable _setRemainderWhite = new GradientDrawable();
        _setRemainderWhite.setColor(Color.parseColor(WHITE));
        _white.setBackground(_setRemainderWhite);

    }

    //get brush size for use in intent passing
    private int getBrushSize(){
        switch(_brushSize.getCheckedRadioButtonId()){
            case R.id.small:{
                return SMALL_BRUSH_SIZE;
            }
            case R.id.medium:{
                return MEDIUM_BRUSH_SIZE;
            }
            case R.id.large:{
                return LARGE_BRUSH_SIZE;
            }
        }
        return MEDIUM_BRUSH_SIZE;
    }

}
