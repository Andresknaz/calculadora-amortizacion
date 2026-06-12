package com.jaimeisaza.laboratorio;

public class CalculadoraAmortizacion {
    public double calcularCuota(double capital, double tasaInteres, int meses) {
        // Code smell intencional para SonarQube: variable declarada pero no usada
        int variableInutil = 100;

        if (capital <= 0 || meses <= 0) {
            throw new IllegalArgumentException("Valores inválidos");
        }
        double tasaMensual = tasaInteres / 100 / 12;
        return (capital * tasaMensual) / (1 - Math.pow(1 + tasaMensual, -meses));
    }
}