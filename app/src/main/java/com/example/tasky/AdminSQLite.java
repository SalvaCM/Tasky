package com.example.tasky;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLite extends SQLiteOpenHelper {

    private static final String DB_NAME = "NewTasky";
    private static final int DB_VERSION = 1;

    public AdminSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public AdminSQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table taskytareas(codigo integer primary key AUTOINCREMENT,nombre text,descripcion text,prioridad text,fecha date,precio real,realizada integer)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
