public class Predictions {

    public double[] calcularPredicciones(double[] coeficientes) {
        int[] predecir = new int[] {60, 80, 100, 25, 50};
        double[] resultado = new double[predecir.length];
        for (int i = 0; i < predecir.length; i++) {
            double x = predecir[i];
            resultado[i] = coeficientes[0]
                    + coeficientes[1] * x
                    + coeficientes[2] * Math.pow(x, 2)
                    + coeficientes[3] * Math.pow(x, 3)
                    + coeficientes[4] * Math.pow(x, 4);
            // Asegúrate de que el número de coeficientes sea adecuado
        }




        return resultado;
    }

}
