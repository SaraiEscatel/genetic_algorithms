public class DiscreteMathematcs {

    //Sumatorio de X
    public double sumatoriaX (double[] x){
        double resultado = 0;
        for (int i =0; i<x.length; i++){
            resultado += x[i];
        }
        return resultado;
    }


    //Sumatoria de Y
    public double sumatoriaY(double [] y){
        double resultado = 0;
        for(int i=0; i<y.length; i++){
            resultado += y [i];
        }
        return resultado;
    }
    //Multiplicacion de  x * y
    public  double[]  multiplicacionXY(double [] x, double [] y) {
        double[] multiplicacionXY = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            multiplicacionXY[i] = (x[i] * y[i]);
        }
        return multiplicacionXY;
    }

    public double sumatoriaXY(double[] multiplicacionXY){
        double resultado = 0;
        for (int i = 0; i < multiplicacionXY.length; i++) {
            resultado += multiplicacionXY[i];
        }
        return resultado;
    }

    //Calcular Dividendo N * SUMATORIA Xi*Yi - [SUMATORIA Xi * SUMATORIA Yi]
    public double dividendo(double [] x, double [] y) {
        double sumatoriaXi = 0;
        double sumatoriaYi = 0;
        double sumatoriaXiYi = 0;
        double resultado = 0 ;
        for (int i = 0; i < x.length; i++) {
            sumatoriaXi += x[i];
            sumatoriaYi += y[i];
            sumatoriaXiYi += (x[i] * y[i]);
        }
        resultado = (9 * sumatoriaXiYi - (sumatoriaXi * sumatoriaYi));
        return resultado;
    }

    //Calcular Divisor
    //
    //N  * SUMATORIA  X ^2i  -  (SUMATORIA Xi) ^2
    public double divisor(double [] x, double [] y){
        double sumatoriaX = 0;
        double  sumatoriaXCuadrada = 0;
        double sumatoriaXi = 0;
        double resultado = 0;
        double suma = 0;
        int N = x.length;
        double [] operacion3 = new double[x.length];
        for (int i=0; i<x.length; i++){
            operacion3 [i] = (x[i] * x[i]);
            suma += ( operacion3[i]);
            sumatoriaX += x[i];

        }
        sumatoriaXCuadrada = (sumatoriaX *  sumatoriaX);
        resultado = (N *  suma) -  sumatoriaXCuadrada;

        return  resultado;

    }



    // (SUMATORIA  X^2i * sumatoria Yi) - (sumatoria Xi * Sumatoria X*Y)

    public double dividendoB0(double [] x,double [] y){
        double sumatoriaXCuadrada = 0;
        double sumatoriaXi = 0;
        double sumatoriaXY = 0;
        double sumatoriaY = 0;
        double resultado = 0;
        for(int i=0; i<x.length; i++){
            sumatoriaXCuadrada += (x[i] * x[i]);
            sumatoriaY += y[i];
            sumatoriaXi += x[i];
            sumatoriaXY += (x[i] * y[i]);
        }
        resultado = (sumatoriaXCuadrada * sumatoriaY) - (sumatoriaXi * sumatoriaXY);
        return  resultado;
    }

    // N * Sumatoria X^2i - (SUMATORIA Xi) ^2
    public  double divisorB0(double [] x, double [] y){
        double sumatoriaXcuadrada = 0;
        double sumatoriaX = 0;
        double resultado = 0;
        int n = x.length;
        for(int i=0; i<x.length; i++){
            sumatoriaXcuadrada += (x[i] * x[i]);
            sumatoriaX += x[i];
        }
        resultado = n * (sumatoriaXcuadrada) - (sumatoriaX * sumatoriaX);
        return resultado;
    }


}
