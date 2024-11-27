public class YHat {

    Totales tl = new Totales();
    DataSet dt = new DataSet();

    double [] yHat = new double[dt.x.length];

    public YHat (){
        this.yHat = calcularYHat(dt.x, tl.totalB0, tl.totalB1);
    }

    public double [] calcularYHat (double [] x, double b0,double b1){
        double [] yHat = new double[x.length];
        for(int i=0; i<x.length; i++){
            yHat [i] = (b1 * x[i]) + b0;
        }
        return yHat;
    }

}
