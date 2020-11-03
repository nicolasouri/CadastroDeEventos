package br.senai.sc.cadastrodeeventos.database.contract;

import br.senai.sc.cadastrodeeventos.database.entity.LocalEntity;

public final class LocalContract {

    private LocalContract(){}

    public static final String criarTabela(){
        return "CREATE TABLE " + LocalEntity.TABLE_NAME + " ("
                + LocalEntity._ID + " INTEGER PRIMARY KEY,"
                + LocalEntity.COLUMN_NAME_NOME_LOCAL + " TEXT,"
                + LocalEntity.COLUMN_NAME_BAIRRO + " TEXT,"
                + LocalEntity.COLUMN_NAME_CIDADE + " TEXT,"
                + LocalEntity.COLUMN_NAME_CAPACIDADE_PUBLICO + " INTEGER)";
    }

    public static final String removerTabela(){
        return "DROP IF TABLE EXISTS " + LocalEntity.TABLE_NAME;
    }
}
