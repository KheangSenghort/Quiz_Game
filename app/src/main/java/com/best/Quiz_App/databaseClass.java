package com.best.Quiz_App;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;



public class databaseClass extends SQLiteOpenHelper {


//    String myPath = DB_PATH + DBNAME;
    public static final String  myPath = "/data/data/com.best.Quiz_App/databases/";
    public static final String DBNAME = "myDataBase.db";
    public static final String DBLOCATION = "/data/data/com.best.Quiz_App/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public databaseClass (Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if(mDatabase!=null) {
            mDatabase.close();
        }
    }

    public List<item> getListProduct() {
        item product = null;
        List<item> productList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM tablo", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            product = new item(cursor.getInt(0),
                               cursor.getString(1),
                               cursor.getString(2),
                               cursor.getString(3),
                               cursor.getString(4),
                               cursor.getString(5),
                               cursor.getInt(6));
            productList.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return productList;
    }

}
