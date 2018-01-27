package com.northdroid.imagetowidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    private static final String TAG = "NewAppWidget";
    AppWidgetManager mAppWidgetManager;
    static int  dWidth = 0;
    static int dHeight = 0;
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        final Bundle appWidgetOptions = appWidgetManager.getAppWidgetOptions(appWidgetId);
        if (appWidgetOptions != null) {
            dWidth = appWidgetOptions.getInt("appWidgetMinWidth");
            dHeight = appWidgetOptions.getInt("appWidgetMinHeight");
        }
        Log.d(TAG,""+YdpToPx(context,dHeight));
        Log.d(TAG,""+XdpToPx(context,dWidth));
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


    @Override
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        mAppWidgetManager = AppWidgetManager.getInstance(context);
        super.onReceive(context, intent);
        int appWidgetId=0;
        Bundle extras = intent.getExtras();
        if(extras!=null)
        {
            appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID);
        }

        final Bundle appWidgetOptions = mAppWidgetManager.getAppWidgetOptions(appWidgetId);
        if (appWidgetOptions != null) {
            int tempWidth = appWidgetOptions.getInt("appWidgetMinWidth");
            int tempHeight = appWidgetOptions.getInt("appWidgetMinHeight");
            dWidth= tempWidth>0?tempWidth:dWidth;
            dHeight=tempHeight>0?tempHeight:dHeight;
        }
        Log.d(TAG,""+YdpToPx(context,dHeight));
        Log.d(TAG,""+XdpToPx(context,dWidth));

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        views.setTextViewTextSize(R.id.timeClock, TypedValue.COMPLEX_UNIT_DIP,fontSize(7));
        views.setTextViewTextSize(R.id.timeClock_ampm, TypedValue.COMPLEX_UNIT_DIP,fontSize(2));


        views.setTextViewTextSize(R.id.dateTextClock, TypedValue.COMPLEX_UNIT_DIP,fontSize(3));
        views.setTextViewTextSize(R.id.daytextClock, TypedValue.COMPLEX_UNIT_DIP,fontSize(3));
        views.setTextViewTextSize(R.id.alarmTextView, TypedValue.COMPLEX_UNIT_DIP,fontSize(2));




        mAppWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static int fontSize(int size){

        float f = size/10f;
        Log.d(TAG,"size:" +size);
        Log.d(TAG,"f:" +f);

        Log.d(TAG,"t:" + Math.round((dWidth*(size/10)))/4);


        return Math.round((dWidth*(size/10f)))/4;
    }
    public static int XdpToPx(Context context,int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
    public static int YdpToPx(Context context,int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.ydpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}

