public class Operations {

    //(X^T * X)
    public double [][] productoMatrices(double [][] Xt, double [][] Xg, int grados) {

        System.out.println("Dimensiones de Xt: " + Xt.length + "x" + Xt[0].length);
        System.out.println("Dimensiones de Xg: " + Xg.length + "x" + Xg[0].length);


        if (Xt[0].length != Xg.length) {
            throw new IllegalArgumentException("Las dimensiones de las matrices no son compatibles para multiplicación.");
        }

        int filasXt = Xt.length;
        int columnasXg = Xg[0].length;
        double [][] resultado = new double[filasXt][columnasXg];


        for (int i = 0; i < filasXt; i++) {
            for (int j = 0; j < columnasXg; j++) {
                for (int k = 0; k < Xt[0].length; k++) {
                    resultado[i][j] += Xt[i][k] * Xg[k][j];
                }
            }
        }
        return resultado;
    }

    /*      6 9 | 1 0
            8 7 | 0 1

            (n) R1 + R2

            1 0 | inversa

     */

    public double [][] IversaProducto(double [][] x) {

        int n = x.length;

        double[][] matrizAumentada = new double[n][n * 2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizAumentada[i][j] = x[i][j];
                matrizAumentada[i][j + n] = (i == j) ? 1 : 0;
            }
        }

        for (int i = 0; i < n; i++) {

            double pivote = matrizAumentada[i][i];
            if (pivote == 0) {
                throw new ArithmeticException("La matriz no es invertible");
            }
            for (int j = 0; j < n * 2; j++) {
                matrizAumentada[i][j] /= pivote;
            }

            //Se hace 0 para la columnas de pivote para las otras filas
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = matrizAumentada[k][i];
                    for (int j = 0; j < n * 2; j++) {
                        matrizAumentada[k][j] -= factor * matrizAumentada[i][j];
                    }
                }
            }
        }

        double[][] inversa = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inversa[i][j] = matrizAumentada[i][j + n];
            }

        }

        return inversa;
    }

    public double []  productoMatrizTranspuestaPorVector(double [][] x, double [] y){
        int filas = x.length;
        int columnas = x[0].length;

        if(columnas != y.length){
            throw  new IllegalArgumentException("Las dimenciones de la matriz y el vector no son conpatibles para la multiplicacion");
        }

        double[] resultado = new  double[filas];

        for(int i=0; i<filas; i++){
            resultado[i] = 0;
            for(int j=0; j < columnas; j++){
                resultado[i] += x[i][j] * y[j];
            }
        }
        return  resultado;

    }





}
