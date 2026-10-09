package com.uth.calculadora;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {
    public static final String EXTRA_PRIMERO = "com.uth.calculadora.PRIMERO";
    public static final String EXTRA_SEGUNDO = "com.uth.calculadora.SEGUNDO";
    public static final String EXTRA_RESULTADO = "com.uth.calculadora.RESULTADO";
    public static final String EXTRA_OPERACION = "com.uth.calculadora.OPERACION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pantalla.preparar(this, R.layout.activity_resultado);

        if (!getIntent().hasExtra(EXTRA_RESULTADO) || !getIntent().hasExtra(EXTRA_PRIMERO)
                || !getIntent().hasExtra(EXTRA_SEGUNDO)) { finish(); return; }


        String operacion = getIntent().getStringExtra(EXTRA_OPERACION);

        String simbolo;
        int titulo;
        if ("suma".equals(operacion)) { simbolo = "+"; titulo = R.string.suma; }
        else if ("resta".equals(operacion)) { simbolo = "−"; titulo = R.string.resta; }
        else if ("multiplicacion".equals(operacion)) { simbolo = "×"; titulo = R.string.multiplicacion; }
        else if ("division".equals(operacion)) { simbolo = "÷"; titulo = R.string.division; }
        else { finish(); return; }

        double primero = getIntent().getDoubleExtra(EXTRA_PRIMERO, 0);
        double segundo = getIntent().getDoubleExtra(EXTRA_SEGUNDO, 0);
        double resultado = getIntent().getDoubleExtra(EXTRA_RESULTADO, 0);

        if (!Double.isFinite(primero) || !Double.isFinite(segundo) || !Double.isFinite(resultado)) {
            finish(); return;
        }

        ((TextView) findViewById(R.id.nombre_operacion)).setText(titulo);
        ((TextView) findViewById(R.id.expresion)).setText(getString(R.string.expresion,
                FormatoNumero.formatear(primero), simbolo, FormatoNumero.formatear(segundo)));
        ((TextView) findViewById(R.id.resultado)).setText(FormatoNumero.formatear(resultado));
        findViewById(R.id.volver).setOnClickListener(v -> finish());
    }

}
