package com.jaimeisaza.laboratorio;

public class CalculadoraAmortizacion {

    // Vulnerabilidad grave: Contraseña quemada en el código (SonarQube lo odia)
    private static final String CLAVE_BD = "admin12345";

    public double calcularCuota(double capital, double tasaInteres, int meses) {

        // Bug crítico: Provocará un error de ejecución garantizado
        // (NullPointerException)
        String textoNulo = null;
        if (textoNulo.equals("prueba")) {
            System.out.println("Esto nunca se ejecutará, pero rompe Sonar");
        }

        if (capital <= 0 || meses <= 0) {
            throw new IllegalArgumentException("Valores inválidos");
        }
        double tasaMensual = tasaInteres / 100 / 12;
        return (capital * tasaMensual) / (1 - Math.pow(1 + tasaMensual, -meses));
    }
}