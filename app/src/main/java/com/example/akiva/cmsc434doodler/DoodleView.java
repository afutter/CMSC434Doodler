package com.example.akiva.cmsc434doodler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Akiva on 3/2/16.
 */
public class DoodleView extends View {
    private Canvas _canvas;
    private Paint _paintDoodle= new Paint();
    private Path _path= new Path();
    private ArrayList<FullDrawObj> undoStack = new ArrayList<FullDrawObj>();
    private ArrayList<FullDrawObj> redoStack = new ArrayList<FullDrawObj>();
    private final static int ORIG_BRUSH_SIZE=20;
    private final static int ORIG_OPACITY=255;
    private String _currColor;
    private int _currBrushSize;
    private int _currOpacity;


    //objected used for managing undo/redo functionality
    private class FullDrawObj{
        Paint paint;
        Path path;
        FullDrawObj(Paint currPaint, Path currPath){
            this.paint=currPaint;
            this.path=currPath;
        }
    }

    public DoodleView(Context context){
        super(context);
        init(null, 0);
    }
    public DoodleView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(attrs,0);
    }
    public DoodleView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    //set up Doodleview
    private void init(AttributeSet attrs, int defStyle){
        _paintDoodle.setColor(Color.parseColor("#092beb"));
        _paintDoodle.setAntiAlias(true);
        _paintDoodle.setStyle(Paint.Style.STROKE);
        _paintDoodle.setStrokeWidth(ORIG_BRUSH_SIZE);
        _currColor="#092beb";
        _currBrushSize=ORIG_BRUSH_SIZE;
        _currOpacity=ORIG_OPACITY;


    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);


        for(FullDrawObj obj: undoStack) {
            canvas.drawPath(obj.path, obj.paint);
        }
        canvas.drawPath(_path, _paintDoodle);


    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float touchX = motionEvent.getX();
        float touchY = motionEvent.getY();

        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                _path.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                _path.lineTo(touchX, touchY);

                break;
            case MotionEvent.ACTION_UP:
                Paint currPaint=new Paint();
                currPaint.set(_paintDoodle);
                undoStack.add(new FullDrawObj(currPaint, _path));
                redoStack = new ArrayList<FullDrawObj>();
                _path=new Path();

                break;
        }
        invalidate();
        return true;
    }

    //functionality for clearing DoodleView
    public void resetPath(){

        _path.rewind();
        undoStack.clear();
        redoStack.clear();
        invalidate();

    }


    //undo draw
    public void onOndo(){
        if(undoStack.isEmpty()){
            Context context=getContext();
            CharSequence text="Can't Undo";
            int duration= Toast.LENGTH_SHORT;

            Toast toast=Toast.makeText(context, text, duration);
            toast.show();

        }
        if(undoStack.size()>0){
            redoStack.add(undoStack.remove(undoStack.size()-1));
            invalidate();
        }
    }

    //redo draw
    public void onRedo(){

        if(redoStack.isEmpty()){
            Context context=getContext();
            CharSequence text="Can't Redo";
            int duration= Toast.LENGTH_SHORT;

            Toast toast=Toast.makeText(context, text, duration);
            toast.show();

        }

        if(redoStack.size()>0){
            undoStack.add(redoStack.remove((redoStack.size()-1)));
            invalidate();
        }


    }

    //update DoodleView based on setting changes
    public void updateArt(String color, String brushSize, String opacity){
        _paintDoodle.setColor(Color.parseColor(color));
        _paintDoodle.setStrokeWidth(Integer.parseInt(brushSize));
        _paintDoodle.setAlpha(Integer.parseInt(opacity));
        _currColor=color;
        _currBrushSize=Integer.parseInt(brushSize);
        _currOpacity=Integer.parseInt(opacity);
    }

    //getters used for intent passing
    public String get_currColor(){return _currColor;}
    public int get_currBrushSize(){return _currBrushSize;}
    public int get_currOpacity(){return _currOpacity;}


}
