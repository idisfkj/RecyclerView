package com.idisfkj.recyclerview.App;

import android.app.Application;
import android.content.Context;

/**
 * Created by idisfkj on 16/3/29.
 * Email : idisfkj@qq.com.
 */
public class App extends Application {
    public static final int LINEAR_LAYOUT = 0;
    public static final int GRID_LAYOUT = 1;
    public static final int STAGGERED_GRID_LAYOUT = 2;
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}
