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

import br.senai.sc.cadastrodeeventos.modelo.Evento;


public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE_NOVO_EVENTO = 1, RESULT_CODE_NOVO_EVENTO = 10;
    private final int REQUEST_CODE_EVENTO_ATUALIZADO = 2, RESULT_CODE_EVENTO_ATUALIZADO = 20;

    private ListView listaEventos;
    private ArrayAdapter<Evento> adapterEventos;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Eventos");

        listaEventos = findViewById(R.id.listView_evento);
        listaEventos.setLongClickable(true);
        ArrayList<Evento> produtos = new ArrayList<Evento>();

        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this, android.R.layout.simple_list_item_1, produtos);
        listaEventos.setAdapter(adapterEventos);

        definirOnClickListenerListView();
        definirOnLongClickListenerListView();
    }

    private void definirOnClickListenerListView(){
        listaEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClicado = adapterEventos.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
                intent.putExtra("eventoEdicao", eventoClicado);
                startActivityForResult(intent, REQUEST_CODE_EVENTO_ATUALIZADO);
            }
        });
    }

    private void definirOnLongClickListenerListView(){
        listaEventos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Evento evento = adapterEventos.getItem(position);
                adapterEventos.remove(evento);
                adapterEventos.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Evento Deletado", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }


    public void onClickNovoEvento(View v){
        Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
        startActivityForResult(intent, REQUEST_CODE_NOVO_EVENTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_NOVO_EVENTO && resultCode == RESULT_CODE_NOVO_EVENTO){
            Evento evento = (Evento) data.getExtras().getSerializable("novoEvento");
            evento.setId(++id);
            this.adapterEventos.add(evento);
        }else if(requestCode == REQUEST_CODE_EVENTO_ATUALIZADO && resultCode == RESULT_CODE_EVENTO_ATUALIZADO){
            Evento produtoEditado = (Evento) data.getExtras().get("eventoEditado");
            Toast.makeText(MainActivity.this, "Evento Editado", Toast.LENGTH_LONG).show();
            for(int i = 0; i < adapterEventos.getCount(); i++){
                Evento produto = adapterEventos.getItem(i);
                if(produto.getId() == produtoEditado.getId()){
                    adapterEventos.remove(produto);
                    adapterEventos.insert(produtoEditado, i);
                    break;
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
