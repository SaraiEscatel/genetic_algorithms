public class MethodoPolinomial {


    public double [][] crearMatriz(  int grado, double [] x){
        int n = x.length;
        double [][] X = new double[n][grado + 1]; //Creamos la matriz X de tamano de los valores de x con el aumento en 1 de nuestra potencia

        for(int i=0; i<n; i++){//es el bucle que recorre cada valor de x
            for(int j=0; j<=grado; j++){
                X[i][j] = Math.pow(x[i], j);
            }
        }
        return X;
    }


    public double [][] crearMatrizT(double [][] x, double [] y){
        int filas = x.length;
        int columnas = x[0].length;
        double [][] xt = new double[columnas][filas];
        //Intercambiamos las filas por columnas
        for(int i=0; i < filas; i++){
            for(int j=0; j < columnas; j++){
                xt[j][i] = x[i][j];
            }
        }
        return xt;
    }


}
