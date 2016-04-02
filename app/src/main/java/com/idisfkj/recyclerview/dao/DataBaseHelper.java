package com.idisfkj.recyclerview.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by idisfkj on 16/3/30.
 * Email : idisfkj@qq.com.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "recycleView.db";
    private static final int VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        RecyclerDataHelper.ItemDBInfo.TABLE.createTab(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
