package br.senai.sc.cadastrodeeventos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//estaeregg
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.senai.sc.cadastrodeeventos.database.EventoDAO;
import br.senai.sc.cadastrodeeventos.modelo.Evento;


public class MainActivity extends AppCompatActivity {

    private ListView listaEventos;
    private ArrayAdapter<Evento> adapterEventos;
    private int id = 0;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.local:
                onClickLocais();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Eventos");

        listaEventos = findViewById(R.id.listView_evento);
        listaEventos.setLongClickable(true);

        definirOnClickListenerListView();
        definirOnLongClickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this, android.R.layout.simple_list_item_1, eventoDAO.listar());
        listaEventos.setAdapter(adapterEventos);
    }

    private void definirOnClickListenerListView(){
        listaEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClicado = adapterEventos.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
                intent.putExtra("eventoEdicao", eventoClicado);
                startActivity(intent);
            }
        });
    }

    private void definirOnLongClickListenerListView(){
        listaEventos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Evento evento = adapterEventos.getItem(position);
                EventoDAO eventoDAO = new EventoDAO(getBaseContext());
                eventoDAO.excluir(evento);
                adapterEventos.remove(evento);
                adapterEventos.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Evento Deletado", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    public void onClickNovoEvento(View v){
        Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
        startActivity(intent);
    }

    public void onClickLocais(){
        Intent intent = new Intent(MainActivity.this, ListarLocaisActivity.class);
        startActivity(intent);
        finish();
    }
}
