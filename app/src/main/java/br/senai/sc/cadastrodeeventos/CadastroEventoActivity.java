package br.senai.sc.cadastrodeeventos;

import androidx.appcompat.app.AppCompatActivity;
//esteregg
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.senai.sc.cadastrodeeventos.database.EventoDAO;
import br.senai.sc.cadastrodeeventos.modelo.Evento;
import br.senai.sc.cadastrodeeventos.modelo.Local;
import br.senai.sc.cadastrodeeventos.database.LocalDAO;


public class CadastroEventoActivity extends AppCompatActivity {

    private int id = 0;
    private Spinner spinnerLocais;
    private ArrayAdapter<Local> locaisAdapter;
    private EditText editTextNome, editTextData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro de Evento");
        spinnerLocais = findViewById(R.id.spinner_locais);

        editTextNome = findViewById(R.id.editText_nome);
        editTextData = findViewById(R.id.editText_data);

        carregarLocais();
        carregarEvento();
    }

    private void carregarLocais(){
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        locaisAdapter = new ArrayAdapter<Local>(CadastroEventoActivity.this, android.R.layout.simple_spinner_item, localDAO.listar());
        spinnerLocais.setAdapter(locaisAdapter);
    }

    public void carregarEvento(){
        Intent intent = getIntent();
        if(intent != null && intent.getExtras() != null && intent.getExtras().get("eventoEdicao") != null) {

            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");

            editTextNome.setText(evento.getNome());
            editTextData.setText(evento.getData());
            int posicaoLocal = obterPosicaoLocal(evento.getLocal());
            spinnerLocais.setSelection(posicaoLocal);

            id = evento.getId();
        }
    }

    private int obterPosicaoLocal(Local local){
        for(int posicao = 0; posicao < locaisAdapter.getCount(); posicao++){
            if(locaisAdapter.getItem(posicao).getId() == local.getId()){
                return posicao;
            }
        }
        return 0;
    }

    public void onClickSalvar(View v){
        String nome = editTextNome.getText().toString();
        String data = editTextData.getText().toString();
        Local local = (Local) spinnerLocais.getSelectedItem();

        Evento evento = new Evento(id, nome, local, data);
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        boolean salvou = eventoDAO.salvar(evento);
        if(salvou){
            finish();
        }else{
            Toast.makeText(CadastroEventoActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
        }
        finish();
    }

    public void onClickVoltar(View v){
        finish();
    }
}