package com.example.macas.appcadastro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

///******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Renata Maçãs
//******************************************************

public class DBHelper {

    private static final String DATABASE_NOME = "bancodedados.db";
    private static final int DATABASE_VERSION = 1 ;
    private static final String TABLE_NAME = "contato.db";

    private Context context;
    private SQLiteDatabase db;

    private SQLiteStatement insertStat;
    private static final String INSERT = "insert into " + TABLE_NAME + " (nome, endereco, empresa) values (?,?,?)";

    public DBHelper (Context context) {
        this.context = context;
        OPenHelper openHelper = new OPenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStat = this.db.compileStatement(INSERT);

    }
                public long insert (String nome, String endereco, String empresa) {
                    this.insertStat.bindString(1, nome);
                    this.insertStat.bindString(2, endereco);
                    this.insertStat.bindString(3, empresa);
                    return this.insertStat.executeInsert();
                }

                public void deleteAll() {
                    this.db.delete(TABLE_NAME, null, null);
                }

                public List<Contato> queryGetAll() {
                    List<Contato> list = new ArrayList<Contato>();

                    try {
                        Cursor cursor = this.db.query (TABLE_NAME,  new String[] {"nome", "endereco", "empresa"},
                        null, null, null, null, null, null);
                        int nregistros = cursor.getCount();
                        if (nregistros!=0) {
                            cursor.moveToFirst();
                            do {
                                Contato contato = new Contato(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                                list.add(contato);

                            }while (cursor.moveToNext());

                            if (cursor !=null && ! cursor.isClosed())
                                cursor.close();
                            return list;
                        }else
                            return null;
                    }
                    catch (Exception err) {
                        return null;
                    }
                }

                private static class OPenHelper extends SQLiteOpenHelper {
                    OPenHelper(Context context) {
                        super (context, DATABASE_NOME, null, DATABASE_VERSION);
                    }
                    public void onCreate (SQLiteDatabase db) {
                        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +"  (id INTEGER PRIMARY KEY AUTO_INCREMENT, nome TEXT, endereco TEXT, empresa TEXT)" ;
                        db.execSQL(sql);
                    }

                    @Override
                    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
                        onCreate(db);
                    }


                }



    }





