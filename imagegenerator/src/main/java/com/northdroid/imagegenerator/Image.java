package com.northdroid.imagegenerator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


/**
 * Created by jorundm on 12.01.2018.
 */

public class Image {
    public static Bitmap render(Context context){

        return  BitmapFactory.decodeResource(context.getResources(),
                R.drawable.sample);
    }
}
