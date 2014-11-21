package com.minephone.png2gifview;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by touch_ping on 2014/11/21.
 */
public class Png2GifView extends View {
    private int xcount;
    private int ycount;
    private Drawable drawable;
    private int duration;
    private boolean isOneShot;
    private AnimationDrawable animationDrawable;

    public Png2GifView(Context context) {
        this(context, null, 0);
    }

    public Png2GifView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Png2GifView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Png2GifView);
        xcount = a.getInt(R.styleable.Png2GifView_xcount, 0);
        ycount = a.getInt(R.styleable.Png2GifView_ycount, 0);
        drawable = a.getDrawable(R.styleable.Png2GifView_drawable);
        duration = a.getInt(R.styleable.Png2GifView_duration,1000);
        isOneShot = a.getBoolean(R.styleable.Png2GifView_isOneShot,false);
        a.recycle();

        if (drawable instanceof BitmapDrawable) {
            Bitmap bm = ((BitmapDrawable)drawable).getBitmap();
            ArrayList<ImagePiece> list = ImageSplitter.split(bm,xcount,ycount);
            bm.recycle();
            animationDrawable  = new AnimationDrawable();
            for (ImagePiece imgp:list) {
                animationDrawable.addFrame(new BitmapDrawable(imgp.bitmap),duration);
            }
            animationDrawable.setOneShot(isOneShot);
            setBackgroundDrawable(animationDrawable);
        }
    }
    
    public boolean isRunning() {
    	return animationDrawable.isRunning();
    }

    public void setRun(boolean run) {
    	if (run==animationDrawable.isRunning()) {
    		return;
    	}
    	if (run || animationDrawable.isOneShot()) {
    		animationDrawable.run();
    	} else  {
    		animationDrawable.stop();
    	}
    }



}
