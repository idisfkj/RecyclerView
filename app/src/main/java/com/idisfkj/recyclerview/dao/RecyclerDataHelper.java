package com.idisfkj.recyclerview.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.idisfkj.recyclerview.bean.ItemInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by idisfkj on 16/3/30.
 * Email : idisfkj@qq.com.
 */
public class RecyclerDataHelper extends BaseDataHelper {

    public RecyclerDataHelper(Context context) {
        super(context);
    }

    @Override
    public Uri getContentUri() {
        return DataProvider.ITEMS_CONTENT_URI;
    }

    public ContentValues getContentValues(ItemInfo info) {
        ContentValues values = new ContentValues();
        values.put(ItemDBInfo.ITEM_CONTENT, info.getContetn());
        return values;
    }

    public Cursor query(int id) {
        Cursor cursor = query(null, ItemDBInfo._ID + "=?"
                , new String[]{String.valueOf(id)}, null);
        return cursor;
    }

    public void insert(ItemInfo info) {
        ContentValues values = getContentValues(info);
        insert(values);
    }

    public void bulkInsert(List<ItemInfo> list) {
        ArrayList<ContentValues> contentValues = new ArrayList<>();
        for (ItemInfo item : list) {
            contentValues.add(getContentValues(item));
        }
        ContentValues[] values = new ContentValues[contentValues.size()];
        bulkInsert(contentValues.toArray(values));
    }

    public int delete(int id) {
        int result = delete(ItemDBInfo._ID + "=?", new String[]{String.valueOf(id)});
        return result;
    }

    public int deleteAll(){
        synchronized (DataProvider.DBLock){
            SQLiteDatabase db = DataProvider.getDataBaseHelper().getWritableDatabase();
            int row = db.delete(ItemDBInfo.TABLE_NAME,null,null);
            return row;
        }
    }

    public int update(ItemInfo info ,int id){
        synchronized (DataProvider.DBLock) {
            ContentValues values = getContentValues(info);
            int result = update(values, ItemDBInfo._ID + "=?", new String[]{String.valueOf(id)});
            return result;
        }
    }

    public CursorLoader getCursorLoader(){
       return getCursorLorder(null,null,null,ItemDBInfo._ID+" ASC");
    }

    public static final class ItemDBInfo implements BaseColumns {
        public ItemDBInfo() {
        }

        public static final String TABLE_NAME = "items";

        public static final String ITEM_CONTENT = "content";

        public static final SQLiteTable TABLE = new SQLiteTable(TABLE_NAME)
                .addColumn(ITEM_CONTENT, Column.DataType.TEXT);

    }

}
