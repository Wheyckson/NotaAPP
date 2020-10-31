package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Home extends AppCompatActivity {

    private TextInputEditText txtRgm;
    private TextInputEditText txtNome;
    private Spinner cmbCurso;
    private Spinner cmbSemestre;
    private Button btnProximoHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txtRgm = findViewById(R.id.txtRgm);
        txtNome = findViewById(R.id.txtNome);
        cmbCurso = findViewById(R.id.cmbCurso);
        cmbSemestre = findViewById(R.id.cmbSemestre);
        btnProximoHome = findViewById(R.id.btnProximoHome);

    }

    public void limpar(View view) {
        txtRgm.setText(null);
        txtNome.setText(null);
        cmbCurso.getSelectedItem();
        cmbSemestre.getSelectedItem();
    }

    public void proximo(View view) {
        if (txtRgm == null && txtNome.equals("")) {
            Toast.makeText(
                    getApplicationContext(),
                    "Preencha os campos primeiro",
                    Toast.LENGTH_SHORT
            ).show();
        }

        Intent it = new Intent(getApplicationContext(), Disciplina.class);
        it.putExtra("nomeHome", txtNome.getText().toString());
        it.putExtra("rgmHome", txtRgm.getText().toString());
        startActivity(it);
    }
}
