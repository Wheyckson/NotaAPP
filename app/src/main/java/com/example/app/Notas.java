package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class Notas extends AppCompatActivity {

    private TextInputEditText Nota1;
    private TextInputEditText Nota2;
    private TextView txtResultadoNota;
    private TextView txtSituaçãoFinal;
    private Button btnVerAF;
    private Button btnVoltarMenor;
    private Button btnVoltarMaior;
    private Spinner spinnerDisiciplina;
    private TextView txtNomeSelecionado;
    private ArrayList<String> list;
    private Double notaMaior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        Nota1 = findViewById(R.id.txtAf1);
        Nota2 = findViewById(R.id.txtAf2);
        txtResultadoNota = findViewById(R.id.txtResultadoNota);
        txtSituaçãoFinal = findViewById(R.id.txtSituaçãoFinal);
        btnVerAF = findViewById(R.id.btnVerAF);
        btnVoltarMenor = findViewById(R.id.btnVoltarMenor);
        btnVoltarMaior = findViewById(R.id.btnVoltarMaior);
        spinnerDisiciplina = findViewById(R.id.spinnerDisiciplina);

        String LIST_KEY="disciplina";
        Intent intent = getIntent();
        list = intent.getStringArrayListExtra(LIST_KEY);
        spinnerDisiciplina.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, list));
        btnVerAF.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valorA1 = Nota1.getText().toString();
                String valorA2 = Nota2.getText().toString();

                //Converter string para double
                double valorDigitadoA1 = Double.parseDouble(valorA1);
                double valorDigitadoA2 = Double.parseDouble(valorA2);
                ArrayList<Double> listaNotas = new ArrayList<Double>();
                listaNotas.add(valorDigitadoA1);
                listaNotas.add(valorDigitadoA2);
                notaMaior = returnHighestNote(listaNotas) ;
                proximo(notaMaior.toString());
            }
        });
    }

    public void calcular(View view) {
        String valorA1 = Nota1.getText().toString();
        String valorA2 = Nota2.getText().toString();

        //Converter string para double
        double valorDigitadoA1 = Double.parseDouble(valorA1);
        double valorDigitadoA2 = Double.parseDouble(valorA2);
        // Calculo a nota final
        Double total = valorDigitadoA1 + valorDigitadoA2;

        // Mostrando o resultado
        txtResultadoNota.setText("Resultado: " + (total));

        if (valorA1 == null || valorA2.equals("")) {
            Toast.makeText(
                    getApplicationContext(),
                    "Preencha os dois campos com as notas!",
                    Toast.LENGTH_SHORT
            ).show();

        } else if (total >= 5.75) {
            txtSituaçãoFinal.setText("Parabéns! Você foi aprovado ");
            txtSituaçãoFinal.setTextColor(getColor(R.color.colorPass));
            btnVerAF.setVisibility(View.INVISIBLE);

        } else {
            txtSituaçãoFinal.setText("Infelizmente você precisa fazer a AF");
            txtSituaçãoFinal.setTextColor(getColor(R.color.colorFail));
            btnVoltarMaior.setVisibility(View.INVISIBLE);
            btnVoltarMenor.setVisibility(View.VISIBLE);
            btnVerAF.setVisibility(View.VISIBLE);
        }
    }
    private Double returnHighestNote(ArrayList<Double> noteList) {
        double highNote = noteList.get(0);
        for (int i = 1; i < noteList.size(); i++) {

            if (noteList.get(i) > highNote) {
                highNote = noteList.get(i);
            }
        }
        return highNote;
    }
    public void voltar(View view) {
        Intent it = new Intent(getApplicationContext(), Disciplina.class);
        startActivity(it);
    }

    public void proximo(String notaMaior) {
        Intent it = new Intent(getApplicationContext(), NotasAf.class);
        it.putExtra("spinnerDisciplina", spinnerDisiciplina.getSelectedItem().toString());
        it.putExtra( "notaAfMaior", notaMaior);
        startActivity(it);

    }
}
