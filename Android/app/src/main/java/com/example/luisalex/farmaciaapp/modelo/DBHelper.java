package com.example.luisalex.farmaciaapp.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;

import com.example.luisalex.farmaciaapp.modelo.Producto;
import com.example.luisalex.farmaciaapp.modelo.UsuarioInicio;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USUARIOS (ID INTEGER PRIMARY KEY AUTOINCREMENT,NICK TEXT NOT NULL,PASS TEXT)");
        db.execSQL("CREATE TABLE PRODUCTOS (ID TEXT PRIMARY KEY,NOMBRE TEXT NOT NULL,CANTIDAD TEXT, PRECIO TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Borramos la tabla antigua
        db.execSQL("DROP TABLE IF EXISTS USUARIOS" );
        db.execSQL("DROP TABLE IF EXISTS PRODUCTOS" );
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

    public Producto obtenerProducto(String id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("PRODUCTOS",new String[]{"ID","NOMBRE","CANTIDAD","PRECIO"}, "ID=?",new String[]{id},null,null,null,"1" );
        cursor.moveToFirst();
        if(cursor != null && cursor.getCount()>0){
            return new Producto(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),null);
        }
        else{
            return null;
        }
    }

    public ArrayList<Producto> obtenerProductos(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PRODUCTOS",null);
        ArrayList<Producto> salida = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Producto p = new Producto(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),null);
                salida.add(p);
            } while (cursor.moveToNext());
        }
        return salida;
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

    public void addProducto(Producto p){
        SQLiteDatabase db = this.getWritableDatabase();

        // Montamos o preparamos la fila que vamos a insertar
        ContentValues values = new ContentValues();
        values.put("ID", p.getID());
        values.put("NOMBRE", p.getNombre());
        values.put("CANTIDAD", p.getCantidad());
        values.put("PRECIO", p.getPrecio());

        // Insertamos la fila
        db.insert("PRODUCTOS", null, values);
        db.close(); // Closing database connection
    }

    public void removeProducto(String id){
        SQLiteDatabase db = this.getReadableDatabase();

        db.delete("PRODUCTOS","ID LIKE ?",new String[]{id});
    }

    public void removeProductos(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM PRODUCTOS");
    }
}
