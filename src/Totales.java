public class Totales {
    DiscreteMathematcs dm = new DiscreteMathematcs();
    DataSet ds = new DataSet();

    double totalB1 = 0;
    double totalB0 = 0;

    public Totales(){
        this.totalB0 = calcularB0(ds.x, ds.y);
        this.totalB1 = calcularB1(ds.x, ds.y);
    }

    public double calcularB1(double [] x, double [] y){
        double resultado = 0 ;
        resultado = (dm.dividendo(x,y) / dm.divisor(x,y));
        return resultado;
    }
    public double calcularB0(double [] x, double [] y){
        double resultado = 0 ;
        resultado = ((dm.dividendoB0(x,y) / dm.divisorB0(x,y)));
        return resultado;

    }
}
