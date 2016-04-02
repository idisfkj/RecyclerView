package com.idisfkj.recyclerview.dao;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by idisfkj on 16/3/30.
 * Email : idisfkj@qq.com.
 */
public class SQLiteTable {

    private String mTabName;
    private List<Column> mColumnDefinitions = new ArrayList<>();

    public SQLiteTable(String tabName) {
        mTabName = tabName;
        mColumnDefinitions.add(new Column(BaseColumns._ID, Column.Constraint.PRIMARY_KEY, Column.DataType.INTEGER));
    }

    private String getTabName() {
        return mTabName;
    }

    public SQLiteTable addColumn(Column cloumnDefinition) {
        mColumnDefinitions.add(cloumnDefinition);
        return this;
    }

    public SQLiteTable addColumn(String columnName, Column.DataType dataType) {
        mColumnDefinitions.add(new Column(columnName, null, dataType));
        return this;
    }

    public SQLiteTable addColumn(String columnName, Column.Constraint constraint, Column.DataType dataType) {
        mColumnDefinitions.add(new Column(columnName, constraint, dataType));
        return this;
    }

    public void createTab(SQLiteDatabase db) {
        String format = " %s";
        int size = mColumnDefinitions.size();
        StringBuffer buffer = new StringBuffer();
        buffer.append("CREATE TABLE IF NOT EXISTS ");
        buffer.append(mTabName);
        buffer.append("(");
        int index = 0;
        for (Column columnDefinition : mColumnDefinitions) {
            //add Column name and DataType
            buffer.append(columnDefinition.getColumnName())
                    .append(String.format(format, columnDefinition.getDataType().name()));
            Column.Constraint constraint = columnDefinition.getConstraint();
            if (constraint != null) {
                //add Column constraint
                buffer.append(String.format(format, constraint.toString()));
            }
            if (index < size - 1) {
                buffer.append(",");
            }
            index++;
        }
        buffer.append(");");
        db.execSQL(buffer.toString());
    }

    public void deleteTab(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXITST" + mTabName);
    }
}
