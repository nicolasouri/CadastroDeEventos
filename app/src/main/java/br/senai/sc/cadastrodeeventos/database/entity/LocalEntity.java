package br.senai.sc.cadastrodeeventos.database.entity;

import android.provider.BaseColumns;

public class LocalEntity implements BaseColumns {

    private LocalEntity(){}

    public static final String TABLE_NAME = "localEventos";
    public static final String COLUMN_NAME_NOME_LOCAL = "nomeLocal";
    public static final String COLUMN_NAME_BAIRRO = "bairro";
    public static final String COLUMN_NAME_CIDADE = "cidade";
    public static final String COLUMN_NAME_CAPACIDADE_PUBLICO = "capacidadePublico";

}
