package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class NotasAf extends AppCompatActivity {

    private TextInputEditText Af1;
    private TextInputEditText Af2;
    private TextView txtResultadoAf;
    private TextView txtSituaçãoAf;
    private TextView txtDiscAf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_af);

        Af1 = findViewById(R.id.txtAf1);
        Af2 = findViewById(R.id.txtAf2);
        txtResultadoAf = findViewById(R.id.txtResultadoAf);
        txtSituaçãoAf = findViewById(R.id.txtSituaçãoAf);
        txtDiscAf = findViewById(R.id.txtDiscAf);

        String spinnerDisciplina = getIntent().getStringExtra("spinnerDisciplina");
        txtDiscAf.setText(spinnerDisciplina);
        Intent intent= getIntent();
        String notaMaior = intent.getStringExtra("notaAfMaior");
        Af1.setText(notaMaior);
    }

    public void voltar(View view) {
        Intent it = new Intent(getApplicationContext(), Disciplina.class);
        startActivity(it);
    }

    public void fecharAf(View view) {
        finishAffinity();
        finish();
    }

    public void calcularAf(View view) {
        String valorAf1 = Af1.getText().toString();
        String valorAf2 = Af2.getText().toString();

        //Converter string para double
        double valorDigitadoAf1 = Double.parseDouble(valorAf1);
        double valorDigitadoAf2 = Double.parseDouble(valorAf2);

        // Calculo a nota final
        Double totalAf = valorDigitadoAf1 + valorDigitadoAf2;

        // Mostrando o resultado
        txtResultadoAf.setText("Resultado: " + (totalAf));

        if (valorAf1 == null || valorAf2.equals("")) {
            Toast.makeText(
                    getApplicationContext(),
                    "Preencha os dois campos com as notas!",
                    Toast.LENGTH_SHORT
            ).show();

        } else if (totalAf >= 5.75) {
            String spinnerDisciplina = getIntent().getStringExtra("spinnerDisciplina");
            txtSituaçãoAf.setText("Parabéns! Você foi aprovado em " + spinnerDisciplina );
            txtSituaçãoAf.setTextColor(getColor(R.color.colorPass));
        } else {
            String spinnerDisciplina = getIntent().getStringExtra("spinnerDisciplina");
            txtSituaçãoAf.setText("Infelizmente você está reprovado " + spinnerDisciplina);
            txtSituaçãoAf.setTextColor(getColor(R.color.colorFail));
        }
    }
}
