package br.senai.sc.cadastrodeeventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.senai.sc.cadastrodeeventos.database.LocalDAO;
import br.senai.sc.cadastrodeeventos.modelo.Local;

public class CadastroLocalActivity extends AppCompatActivity {

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_local);
        setTitle("Cadastro de Local");

        carregarLocal();
    }

    public void carregarLocal(){
        Intent intent = getIntent();
        if(intent != null && intent.getExtras() != null && intent.getExtras().get("localEdicao") != null) {

            Local local = (Local) intent.getExtras().get("localEdicao");

            EditText editTextNomeLocal = findViewById(R.id.editText_nomeLocal);
            EditText editTextBairro = findViewById(R.id.editText_bairro);
            EditText editTextCidade = findViewById(R.id.editText_cidade);
            EditText editTextCapacidadePublico = findViewById(R.id.editText_capacidadePublico);
            editTextNomeLocal.setText(local.getNomeLocal());
            editTextBairro.setText(local.getBairro());
            editTextCidade.setText(local.getCidade());
            editTextCapacidadePublico.setText(local.getCapacidadePublico());

            id = local.getId();
        }
    }

    public void onClickSalvar(View v){

        EditText editTextNomeLocal = findViewById(R.id.editText_nomeLocal);
        EditText editTextBairro = findViewById(R.id.editText_bairro);
        EditText editTextCidade = findViewById(R.id.editText_cidade);
        EditText editTextCapacidadePublico = findViewById(R.id.editText_capacidadePublico);
        String nomeLocal = editTextNomeLocal.getText().toString();
        String bairro = editTextBairro.getText().toString();
        String cidade = editTextCidade.getText().toString();
        int capacidadePublico = Integer.valueOf(editTextCapacidadePublico.getText().toString());

        Local local = new Local(id, nomeLocal, bairro, cidade, capacidadePublico);
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        boolean salvou = localDAO.salvar(local);
        if(salvou){
            finish();
        }else{
            Toast.makeText(CadastroLocalActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
        }
        finish();
    }

    public void onClickVoltar(View v){
        finish();
    }
}
