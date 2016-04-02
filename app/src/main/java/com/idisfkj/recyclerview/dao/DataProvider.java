package com.idisfkj.recyclerview.dao;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.idisfkj.recyclerview.App.App;

/**
 * Created by idisfkj on 16/3/31.
 * Email : idisfkj@qq.com.
 */
public class DataProvider extends ContentProvider {

    private static final String TAG = DataProvider.class.getSimpleName();
    public static Object DBLock = new Object();
    private static final String AUTHOROTY = "com.idisfkj.recyclerview";
    private static final String SCHEME = "content://";

    private static final String PATH_ITEMS = "/items";
    public static final Uri ITEMS_CONTENT_URI = Uri.parse(SCHEME + AUTHOROTY + PATH_ITEMS);
    private static final String ITEMS_CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.idisfkj.item";
    private static final int ITEMS = 1;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHOROTY, "items", ITEMS);
    }

    private static DataBaseHelper mDataBaseHelper;

    public static DataBaseHelper getDataBaseHelper() {
        if (mDataBaseHelper == null) {
            mDataBaseHelper = new DataBaseHelper(App.getContext());
        }
        return mDataBaseHelper;
    }

    private String matchTable(Uri uri) {
        String table = null;
        switch (uriMatcher.match(uri)) {
            case ITEMS:
                table = RecyclerDataHelper.ItemDBInfo.TABLE_NAME;
                break;
            default:
                break;
        }
        return table;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        String type = null;
        switch (uriMatcher.match(uri)) {
            case ITEMS:
                type = ITEMS_CONTENT_TYPE;
                break;
            default:
                break;
        }
        return type;
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        synchronized (DBLock) {
            // Assist the query
            SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
            String table = matchTable(uri);
            queryBuilder.setTables(table);
            SQLiteDatabase db = getDataBaseHelper().getReadableDatabase();
            Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
            return cursor;
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        synchronized (DBLock) {
            String table = matchTable(uri);
            SQLiteDatabase db = getDataBaseHelper().getWritableDatabase();
            long rowId = 0;
            db.beginTransaction();
            try {
                rowId = db.insert(matchTable(uri), null, values);
                db.setTransactionSuccessful();
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            } finally {
                db.endTransaction();
            }

            if (rowId > 0) {
                // made uri add rowId
                Uri resultUri = ContentUris.withAppendedId(uri, rowId);
                getContext().getContentResolver().notifyChange(uri, null);
                return resultUri;
            }
            throw new SQLException("Fialed to insert row into" + table);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        synchronized (DBLock) {
            SQLiteDatabase db = getDataBaseHelper().getWritableDatabase();
            int count = 0;
            db.beginTransaction();
            try {
                count = db.delete(matchTable(uri), selection, selectionArgs);
                db.setTransactionSuccessful();
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            } finally {
                db.endTransaction();
            }
            getContext().getContentResolver().notifyChange(uri, null);
            return count;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        synchronized (DBLock) {
            SQLiteDatabase db = getDataBaseHelper().getWritableDatabase();
            int count = 0;
            db.beginTransaction();
            try{
                count = db.update(matchTable(uri),values,selection,selectionArgs);
                db.setTransactionSuccessful();
            }catch (Exception e){
                Log.d(TAG,e.getMessage());
            }finally {
                db.endTransaction();
            }
            getContext().getContentResolver().notifyChange(uri,null);
            return count;
        }
    }
}
