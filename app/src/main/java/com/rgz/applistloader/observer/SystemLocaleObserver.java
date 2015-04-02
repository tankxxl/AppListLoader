package com.rgz.applistloader.observer;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.rgz.applistloader.loader.AppListLoader;


/**
 * Used by the {@link AppListLoader}. An observer that intercepts system-wide
 * locale changes (and notifies the loader when these changes are detected).
 */
public class SystemLocaleObserver extends BroadcastReceiver {
    private static final String TAG = "ADP_SystemLocaleObserver";
    private static final boolean DEBUG = true;

    private AppListLoader mLoader;

    public SystemLocaleObserver() {

    }

    public SystemLocaleObserver(AppListLoader loader) {
        mLoader = loader;
        IntentFilter filter = new IntentFilter(Intent.ACTION_LOCALE_CHANGED);
        mLoader.getContext().registerReceiver(this, filter);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onReceive(Context context, Intent intent) {
        if (DEBUG)
            Log.i(TAG, "+++observer detected a locale change!Notifying Loader+++");

        // Tell the loader about the change.
        mLoader.onContentChanged();
    }
}