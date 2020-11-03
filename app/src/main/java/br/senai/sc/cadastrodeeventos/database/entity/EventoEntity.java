package br.senai.sc.cadastrodeeventos.database.entity;

import android.provider.BaseColumns;

public final class EventoEntity implements BaseColumns {

    private EventoEntity(){}

    public static final String TABLE_NAME = "evento";
    public static final String COLUMN_NAME_NOME = "nome";
    public static final String COLUMN_NAME_ID_LOCAL = "idlocal";
    public static final String COLUMN_NAME_DATA = "data";

}
