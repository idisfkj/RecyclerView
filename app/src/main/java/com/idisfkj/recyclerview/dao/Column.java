package com.idisfkj.recyclerview.dao;

/**
 * Created by idisfkj on 16/3/30.
 * Email : idisfkj@qq.com.
 */
public class Column {

    public static enum Constraint {
        UNIQUE("UNIQUE"), NOT("NOT"), NULL("NULL"), CHECK("CHECK"), FOREIGN_KEY("FOREIGN KEY"), PRIMARY_KEY("PRIMARY KEY");
        private String value;

        private Constraint(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public static enum DataType {
        NULL, INTEGER, REAL, TEXT, BLOD
    }

    private String mColumnName;
    private Constraint mConstraint;
    private DataType mDatatype;

    public Column(String columnName, Constraint constraint, DataType dataType) {
        mColumnName = columnName;
        mConstraint = constraint;
        mDatatype = dataType;
    }

    public String getColumnName() {
        return mColumnName;
    }

    public Constraint getConstraint() {
        return mConstraint;
    }

    public DataType getDataType() {
        return mDatatype;
    }

}
