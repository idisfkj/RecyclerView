package com.idisfkj.recyclerview.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by idisfkj on 16/3/30.
 * Email : idisfkj@qq.com.
 */
public abstract class BaseDataHelper {

    private Context mContext;

    public BaseDataHelper(Context context) {
        mContext = context;
    }

    public abstract Uri getContentUri();

    protected Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = mContext.getContentResolver().query(getContentUri(), projection, selection, selectionArgs, sortOrder);
        return cursor;
    }

    protected Uri insert(ContentValues values) {
        Uri uri = mContext.getContentResolver().insert(getContentUri(), values);
        return uri;
    }

    protected int bulkInsert(ContentValues[] values) {
        int result = mContext.getContentResolver().bulkInsert(getContentUri(), values);
        return result;
    }

    protected int update(ContentValues values, String where, String[] selectionArgs) {
        int result = mContext.getContentResolver().update(getContentUri(), values, where, selectionArgs);
        return result;
    }

    protected int delete(String where,String[] selectionArgs) {
        int result = mContext.getContentResolver().delete(getContentUri(), where, selectionArgs);
        return result;
    }

    protected CursorLoader getCursorLorder(String[] projection,String selection,String[] selectionArgs,String sortOrder){
        return new CursorLoader(mContext,getContentUri(),projection,selection,selectionArgs,sortOrder);
    }
}
