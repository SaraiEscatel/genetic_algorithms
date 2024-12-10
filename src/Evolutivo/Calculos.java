package Evolutivo;

import java.util.ArrayList;
public class Calculos {

    public Calculos() {

    }

    // Calcular yHat para un cromosoma
    public double[] calcularYHat(double[] datosX, double[] cromosoma) {
        double[] yHat = new double[datosX.length];
        for (int i = 0; i < datosX.length; i++) {
            yHat[i] = cromosoma[0] + cromosoma[1] * datosX[i]; // yHat = b0 + b1 * x
        }
        return yHat;
    }

    // Calcular R^2 para un cromosoma
    public double calcularR2(double[] yReales, double[] yPredichos) {
        // calcular promedio de y
        double sumaY = 0.0;
        for (double y : yReales) {
            sumaY += y;
        }
        double mediaY = sumaY / yReales.length;

        // denominador de la funcion R2
        double denominador = 0.0;
        for (double y : yReales) {
            denominador += Math.pow(y - mediaY, 2);
        }

        // calcular numerador de la funcion R2
        double numerador = 0.0;
        for (int i = 0; i < yReales.length; i++) {
            numerador += Math.pow(yReales[i] - yPredichos[i], 2);
        }

        // Calcular R cuadrada
        double R2 = 1 - (numerador / denominador);
        return R2;
    }


    // Calcular R^2 para todos los cromosomas y guardarlos en un ArrayList
    public double[] calcularR2Poblacion(ArrayList<double[]> poblacion, double[] datosX, double[] datosY) {
        // Crear un arreglo para almacenar los valores de R^2
        double[] rCuadradas = new double[poblacion.size()];

        // Iterar sobre la población
        for (int i = 0; i < poblacion.size(); i++) {
            double[] cromosoma = poblacion.get(i); // Obtener el cromosoma
            double[] yHat = calcularYHat(datosX, cromosoma); // Calcular yHat
            double r2 = calcularR2(datosY, yHat); // Calcular R^2 para el cromosoma
            rCuadradas[i] = r2; // Guardar R^2 en el arreglo
        }

        return rCuadradas; // Retornar el arreglo con los R^2
    }

    public void imprimirCalcularR2Poblacion(double[] calculoR2Poblacion) {
        System.out.println("Calculo R2 de la poblacion");
        for (int i = 0; i < calculoR2Poblacion.length; i++) {
            System.out.println(calculoR2Poblacion[i]);
        }
    }

    public boolean seAlcanzoElFitness(double[] R2, double fitness) {
        boolean seAlcanzo = false;
        for (double rCuadrada : R2) {
            if (rCuadrada >= fitness) {
                seAlcanzo = true;
            }
        }
        return seAlcanzo;
    }

}

