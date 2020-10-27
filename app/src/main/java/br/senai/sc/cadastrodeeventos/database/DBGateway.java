package br.senai.sc.cadastrodeeventos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBGateway {

    private static DBGateway dbGateway;
    private SQLiteDatabase db;

    public static DBGateway getInstance(Context context){
        if(dbGateway == null){
            dbGateway = new DBGateway(context);
        }
        return dbGateway;
    }

    private DBGateway(Context context){
        DatabaseDBHelper dbHelper = new DatabaseDBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getDatabase(){
        return db;
    }
}
