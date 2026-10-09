package com.uth.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private EditText primerNumero;
    private EditText segundoNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pantalla.preparar(this, R.layout.activity_main);
        primerNumero = findViewById(R.id.primer_numero);
        segundoNumero = findViewById(R.id.segundo_numero);
        findViewById(R.id.sumar).setOnClickListener(v -> calcular("suma"));
        findViewById(R.id.restar).setOnClickListener(v -> calcular("resta"));
        findViewById(R.id.multiplicar).setOnClickListener(v -> calcular("multiplicacion"));
        findViewById(R.id.dividir).setOnClickListener(v -> calcular("division"));
    }

    private Double leer(EditText campo) {
        try {
            return EntradaNumerica.convertir(campo.getText().toString());
        } catch (IllegalArgumentException e) {
            campo.setError(e.getMessage());
            return null;
        }
    }

    private void calcular(String operacion) {
        primerNumero.setError(null);
        segundoNumero.setError(null);

        Double primero = leer(primerNumero);
        Double segundo = leer(segundoNumero);

        if (primero == null || segundo == null) {
            (primero == null ? primerNumero : segundoNumero).requestFocus();
            return;
        }

        // Los setters guardan los números en el objeto.
        OperacionesMatematicas matematicas = new OperacionesMatematicas();
        matematicas.setPrimero(primero);
        matematicas.setSegundo(segundo);

        try {
            double resultado;
            switch (operacion) {
                case "suma": resultado = matematicas.sumar(); break;
                case "resta": resultado = matematicas.restar(); break;
                case "multiplicacion": resultado = matematicas.multiplicar(); break;
                case "division": resultado = matematicas.dividir(); break;
                default: throw new IllegalArgumentException("Operación desconocida.");
            }

            Intent intent = new Intent(this, ResultadoActivity.class);

            // Los getters recuperan los números para enviarlos a la otra pantalla.
            intent.putExtra(ResultadoActivity.EXTRA_PRIMERO, matematicas.getPrimero());
            intent.putExtra(ResultadoActivity.EXTRA_SEGUNDO, matematicas.getSegundo());
            intent.putExtra(ResultadoActivity.EXTRA_RESULTADO, resultado);
            intent.putExtra(ResultadoActivity.EXTRA_OPERACION, operacion);

            startActivity(intent);
        } catch (ArithmeticException e) {
            if ("division".equals(operacion) && segundo == 0) {
                segundoNumero.setError(e.getMessage());
                segundoNumero.requestFocus();
            } else {
                Snackbar.make(findViewById(R.id.main), e.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        }
    }
}
