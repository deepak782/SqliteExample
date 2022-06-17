package com.example.karthikapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class UserHelper extends SQLiteOpenHelper {
    Context context;
    public static final String DATABASE_NAME="PRASADATABASE.DB";
    public static final int DATABASE_VERSION=1;
    /*public static final String TABLE_NAME="UserTable";
    public static final String COLUMN_SNO="SNo";
    public static final String COLUMN_NAME="Name";
    public static final String COLUMN_MAIL="Mail";
    public static final String COLUMN_MOBILE="Mobile";*/

    public UserHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create Table TABLE_NAME(COLUMN_SNO Interger primary key,COLUMN_NAME text,COLUMN_MAIL text,COLUMN_MOBILE text)");
        Toast.makeText(context, "Sql Started", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long CreateMethod(SQLiteDatabase db,int sno,String name,String mail,String mobile)
    {
        ContentValues cv=new ContentValues();
        cv.put("COLUMN_SNO",sno);
        cv.put("COLUMN_NAME",name);
        cv.put("COLUMN_MAIL",mail);
        cv.put("COLUMN_MOBILE",mobile);

        long set=db.insert("TABLE_NAME",null,cv);
        return set;
    }
    public long UpdateMethod(SQLiteDatabase db,int sno,String name,String mail,String mobile)
    {
        ContentValues cv=new ContentValues();
        cv.put("COLUMN_SNO",sno);
        cv.put("COLUMN_NAME",name);
        cv.put("COLUMN_MAIL",mail);
        cv.put("COLUMN_MOBILE",mobile);

        long set=db.update("TABLE_NAME",cv,"COLUMN_SNO="+sno,null);
        return set;
    }
    public long DeleteMethod(SQLiteDatabase db,int sno)
    {
        ContentValues cv=new ContentValues();
        cv.put("COLUMN_SNO",sno);

        long set=db.delete("TABLE_NAME","COLUMN_SNO="+sno,null);
        return set;
    }


}
