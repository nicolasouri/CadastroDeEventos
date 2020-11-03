package br.senai.sc.cadastrodeeventos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.cadastrodeeventos.database.entity.EventoEntity;
import br.senai.sc.cadastrodeeventos.database.entity.LocalEntity;
import br.senai.sc.cadastrodeeventos.modelo.Evento;
import br.senai.sc.cadastrodeeventos.modelo.Local;

public class EventoDAO {

    private final String SQL_LISTAR_TODOS = "SELECT evento._id, nome, data, idLocal, nomeLocal, bairro, cidade, capacidadePublico FROM " +
            EventoEntity.TABLE_NAME + " INNER JOIN " +
            LocalEntity.TABLE_NAME + " ON " +
            EventoEntity.COLUMN_NAME_ID_LOCAL + " = " + LocalEntity.TABLE_NAME + "." + LocalEntity._ID;

    private DBGateway dbGateway;

    public EventoDAO(Context context){
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar(Evento evento) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUMN_NAME_NOME, evento.getNome());
        contentValues.put(EventoEntity.COLUMN_NAME_DATA, evento.getData());
        contentValues.put(EventoEntity.COLUMN_NAME_ID_LOCAL, evento.getLocal().getId());
        if(evento.getId() > 0){
            return dbGateway.getDatabase().update(EventoEntity.TABLE_NAME,
                    contentValues,
                    EventoEntity._ID + "=?",
                    new String[]{String.valueOf(evento.getId())}) > 0;
        }else{
            return dbGateway.getDatabase().insert(EventoEntity.TABLE_NAME, null, contentValues) > 0;
        }


    }

    public List<Evento> listar(){
        List<Evento> produtos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_DATA));
            int idLocal = cursor.getInt(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_ID_LOCAL));

            String nomeLocal = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME_LOCAL));
            String bairro = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_BAIRRO));
            String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CIDADE));
            int capacidadePublico = cursor.getInt(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE_PUBLICO));
            Local local = new Local(idLocal, nomeLocal, bairro, cidade, capacidadePublico);

            produtos.add(new Evento(id, nome, local, data));
        }
        cursor.close();
        return produtos;
    }

    public void excluir(Evento evento){
        dbGateway.getDatabase().delete(EventoEntity.TABLE_NAME, EventoEntity._ID + "=?", new String[]{String.valueOf(evento.getId())});
    }
}
