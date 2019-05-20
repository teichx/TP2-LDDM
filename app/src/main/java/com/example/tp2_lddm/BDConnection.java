package com.example.tp2_lddm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BDConnection {
    myDbHelper myhelper;

    public BDConnection(Context context) {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String desc, String father) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.DESCRIPTION, desc);
        contentValues.put(myDbHelper.FATHER, father);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }


    public String getData(String father) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.DESCRIPTION};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME, columns,"father = '" + father + "'",null,null,null,myDbHelper.DESCRIPTION);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext()) {
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.DESCRIPTION));
            buffer.append(name + "\n");
        }
        return buffer.toString();
    }

    public String getData() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.DESCRIPTION};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME, columns,"father is null",null,null,null,myDbHelper.DESCRIPTION);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext()) {
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.DESCRIPTION));
            buffer.append(name + "\n");
        }
        return buffer.toString();
    }

    public  int delete(String desc) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={};

        int count = db.delete(myDbHelper.TABLE_NAME ,"description = '" + desc + "'",whereArgs);
        //db.delete(myDbHelper.TABLE_NAME ,"father = '" + desc + "'",whereArgs);
        return  count;
    }

    public int updateName(String oldName , String newName) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.DESCRIPTION,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.DESCRIPTION+" = ?",whereArgs );
        return count;
    }

    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "myTable";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        // private static final String UID="_id";     // Column I (Primary Key)
        private static final String DESCRIPTION = "Description";    //Column II
        private static final String FATHER= "Father";    // Column III
        //private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
        //      " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+DESCRIPTION+" VARCHAR(255) PRIMARY KEY,"+ FATHER+" VARCHAR(225));";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+DESCRIPTION+" VARCHAR(255) PRIMARY KEY,"+ FATHER+" VARCHAR(225));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
    }
}
