package com.example.luisalex.farmaciaapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USUARIOS (ID INTEGER PRIMARY KEY AUTOINCREMENT,NICK TEXT NOT NULL,PASS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Borramos la tabla antigua
        db.execSQL("DROP TABLE IF EXISTS USUARIOS" );
        // Creamos la tabla de nuevo
        onCreate(db);
    }

    public UsuarioInicio obtenerUsuario(String nick, String pass){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("USUARIOS",new String[]{"ID","NICK","PASS"}, "NICK=? AND PASS=?",new String[]{nick,pass},null,null,null,"1" );
        cursor.moveToFirst();
        if(cursor != null && cursor.getCount()>0){
            return new UsuarioInicio(cursor.getString(1),cursor.getString(2));
        }
        else{
            return null;
        }
    }

    public void addUsuario(UsuarioInicio u){
        SQLiteDatabase db = this.getWritableDatabase();

        // Montamos o preparamos la fila que vamos a insertar
        ContentValues values = new ContentValues();
        values.put("NICK", u.getNick());
        values.put("PASS", u.getPass());

        // Insertamos la fila
        db.insert("USUARIOS", null, values);
        db.close(); // Closing database connection
    }
}
