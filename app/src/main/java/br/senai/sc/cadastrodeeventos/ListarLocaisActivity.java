package br.senai.sc.cadastrodeeventos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//estaeregg
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.senai.sc.cadastrodeeventos.database.EventoDAO;
import br.senai.sc.cadastrodeeventos.database.LocalDAO;
import br.senai.sc.cadastrodeeventos.modelo.Evento;
import br.senai.sc.cadastrodeeventos.modelo.Local;


public class ListarLocaisActivity extends AppCompatActivity {

    private ListView listaLocais;
    private ArrayAdapter<Local> adapterLocais;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_locais);
        setTitle("Locais");

        listaLocais = findViewById(R.id.listView_evento);
        listaLocais.setLongClickable(true);

        definirOnClickListenerListView();
        definirOnLongClickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        adapterLocais = new ArrayAdapter<Local>(ListarLocaisActivity.this, android.R.layout.simple_list_item_1, localDAO.listar());
        listaLocais.setAdapter(adapterLocais);
    }

    private void definirOnClickListenerListView(){
        listaLocais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Local localClicado = adapterLocais.getItem(position);
                Intent intent = new Intent(ListarLocaisActivity.this, CadastroLocalActivity.class);
                intent.putExtra("localEdicao", localClicado);
                startActivity(intent);
            }
        });
    }

    private void definirOnLongClickListenerListView(){
        listaLocais.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Local localClicado = adapterLocais.getItem(position);
                LocalDAO localDAO = new LocalDAO(getBaseContext());
                localDAO.excluir(localClicado);
                adapterLocais.remove(localClicado);
                adapterLocais.notifyDataSetChanged();
                Toast.makeText(ListarLocaisActivity.this, "Evento Deletado", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    public void onClickNovoLocal(View v){
        Intent intent = new Intent(ListarLocaisActivity.this, CadastroLocalActivity.class);
        startActivity(intent);
    }

    public void onClickEventos(View v){
        Intent intent = new Intent(ListarLocaisActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

