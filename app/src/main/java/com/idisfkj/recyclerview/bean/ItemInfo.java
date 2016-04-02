package com.idisfkj.recyclerview.bean;

import android.database.Cursor;

import com.idisfkj.recyclerview.dao.RecyclerDataHelper;

/**
 * Created by idisfkj on 16/3/31.
 * Email : idisfkj@qq.com.
 */
public class ItemInfo {
    public String contetn;

    public String getContetn() {
        return contetn;
    }

    public void setContetn(String contetn) {
        this.contetn = contetn;
    }

    public static ItemInfo formCursor(Cursor cursor) {
        ItemInfo info = new ItemInfo();
        info.contetn = cursor.getString(cursor.getColumnIndex(RecyclerDataHelper.ItemDBInfo.ITEM_CONTENT));
        return info;
    }

}
