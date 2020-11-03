package br.senai.sc.cadastrodeeventos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.cadastrodeeventos.database.entity.LocalEntity;
import br.senai.sc.cadastrodeeventos.modelo.Local;

public class LocalDAO {

    private final String SQL_LISTAR_TODOS = "SELECT * FROM " + LocalEntity.TABLE_NAME;
    private DBGateway dbGateway;

    public LocalDAO(Context context){
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar(Local local) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalEntity.COLUMN_NAME_NOME_LOCAL, local.getNomeLocal());
        contentValues.put(LocalEntity.COLUMN_NAME_BAIRRO, local.getBairro());
        contentValues.put(LocalEntity.COLUMN_NAME_CIDADE, local.getCidade());
        contentValues.put(LocalEntity.COLUMN_NAME_CAPACIDADE_PUBLICO, local.getCapacidadePublico());
        if(local.getId() > 0){
            return dbGateway.getDatabase().update(LocalEntity.TABLE_NAME,
                    contentValues,
                    LocalEntity._ID + "=?",
                    new String[]{String.valueOf(local.getId())}) > 0;
        }else{
            return dbGateway.getDatabase().insert(LocalEntity.TABLE_NAME, null, contentValues) > 0;
        }

    }

    public List<Local> listar(){
        List<Local> locais = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(LocalEntity._ID));
            String nomeLocal = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME_LOCAL));
            String bairro = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_BAIRRO));
            String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CIDADE));
            int capacidadePublico = cursor.getInt(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE_PUBLICO));
            locais.add(new Local(id, nomeLocal, bairro, cidade, capacidadePublico));
        }
        cursor.close();
        return locais;
    }

    public void excluir(Local local){
        dbGateway.getDatabase().delete(LocalEntity.TABLE_NAME, LocalEntity._ID + "=?", new String[]{String.valueOf(local.getId())});
    }
}
