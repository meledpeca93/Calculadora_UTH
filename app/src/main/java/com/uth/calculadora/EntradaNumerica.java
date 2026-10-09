package com.uth.calculadora;

public final class EntradaNumerica {
    private EntradaNumerica() { }

    public static double convertir(String entrada) {

        String valor = entrada.trim();
        if (valor.isEmpty()) throw new IllegalArgumentException("Ingresa un número.");

        if (!valor.matches("[+-]?(?:[0-9]+(?:[.,][0-9]*)?|[.,][0-9]+)")) {
            throw new IllegalArgumentException("Ingresa un número válido; usa punto o coma decimal.");
        }
        double numero;
        try {
            numero = Double.parseDouble(valor.replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El número excede el rango permitido.");
        }
        if (!Double.isFinite(numero)) {
            throw new IllegalArgumentException("El número excede el rango permitido.");
        }
        return numero;
    }
}
