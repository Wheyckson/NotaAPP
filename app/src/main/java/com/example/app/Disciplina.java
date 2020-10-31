package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Disciplina extends AppCompatActivity {

    private EditText disciplina;
    private ListView listaDisciplinas;
    private Button btnGravar;
    private Button btnVoltar;
    private Button btnAvancar;
    private ArrayList lista;
    private TextView preenchaCampos;
    ArrayAdapter<String> arrayAdapter;

    public Disciplina() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplina);

        TextView tvResp = findViewById(R.id.tvResp);
        btnGravar = findViewById(R.id.idBtnGravar);
        btnVoltar = findViewById(R.id.idBtnVoltar);
        btnAvancar = findViewById(R.id.idBtnAvancar);
        disciplina = findViewById(R.id.idDisciplina);
        listaDisciplinas = findViewById(R.id.idListaDisciplinas);
        //preenchaCampos = findViewById(R.id.idPreenchaCampos);

        lista = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, lista);

        Intent intent = getIntent();
        String nome = intent.getStringExtra("nomeHome");
        String rgm = intent.getStringExtra("rgmHome");
        tvResp.setText("Olá, " + nome + "\nRGM: " + rgm);

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String disciplinaValor = disciplina.getText().toString();
                if((disciplinaValor != null) && (!disciplinaValor.equals(""))){
                    lista.add(disciplinaValor);
                    listaDisciplinas.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();
                    disciplina.setText("");
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onVoltar();
            }
        });

        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!lista.isEmpty()) {
                    onAvancar(lista);
                    Toast.makeText(
                            getApplicationContext(),
                            "Clique em uma disciplina da lista!",
                            Toast.LENGTH_SHORT
                    ).show();
                }else{
                    Toast.makeText(
                            getApplicationContext(),
                            "Preencha a lista com sua(s) disciplina !",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }

    public void onAvancar(ArrayList<String> listaDisciplinas ){
        String LIST_KEY="disciplina";
        //preenchaCampos.setText("*Clique em uma disciplina da lista!");
        Intent intent = new Intent(this, Notas.class);
        intent.putExtra(LIST_KEY, listaDisciplinas);
        startActivity(intent);
    }

    public void onVoltar(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}