package com.uth.calculadora;

/** Guarda los números con setters y permite consultarlos con getters. */
public final class OperacionesMatematicas {
    private double primero;
    private double segundo;

    public void setPrimero(double primero) {
        if (!Double.isFinite(primero)) {
            throw new IllegalArgumentException("Ingresa números finitos.");
        }
        this.primero = primero;
    }

    public double getPrimero() {
        return primero;
    }

    public void setSegundo(double segundo) {
        if (!Double.isFinite(segundo)) {
            throw new IllegalArgumentException("Ingresa números finitos.");
        }
        this.segundo = segundo;
    }

    public double getSegundo() {
        return segundo;
    }

    public double sumar() { return validarResultado(primero + segundo); }
    public double restar() { return validarResultado(primero - segundo); }
    public double multiplicar() { return validarResultado(primero * segundo); }
    public double dividir() {
        if (segundo == 0) {
            throw new ArithmeticException("No se puede dividir entre cero.");
        }
        return validarResultado(primero / segundo);
    }

    private double validarResultado(double resultado) {
        if (!Double.isFinite(resultado)) {
            throw new ArithmeticException("El resultado excede el rango permitido.");
        }
        return resultado;
    }
}
