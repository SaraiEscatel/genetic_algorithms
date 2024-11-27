import java.io.Serializable;

public class ResultOperations  implements Serializable {
    private String matrizX;
    private String matrizXT;
    private String productoXTX;
    private String inversa;
    private String XTY;
    private String Bhat;


    private  String SumX;
    private  String SumY;
    private  String mulXY;
    private  String sumXY;
    private  String divisor;
    private  String dividendo;
    private  String calB1;
    private  String calB0;



    public ResultOperations (String matrizX, String matrizXT, String productoXTXStr,String inversaStr, String XTY, String Bhat, String SumX, String SumY, String mulXY,
                             String sumXY, String divisor, String dividendo, String calB1, String calB0
    ){
        this.matrizX = matrizX;
        this.matrizXT = matrizXT;
        this.productoXTX = productoXTXStr;
        this.inversa = inversaStr;
        this.XTY = XTY;
        this.Bhat = Bhat;
        this.SumX = SumX;
        this.SumY = SumY;
        this.mulXY = mulXY;
        this.sumXY = sumXY;
        this.divisor = divisor;
        this.dividendo = dividendo;
        this.calB1 =calB1;
        this.calB0 = calB0;


    }

    //Getters
    public  String getMatrizX(){
        return matrizX;
    }

    public String getMatrizXT(){
        return matrizXT;
    }

    public String getProductoXTX(){
        return  productoXTX;
    }

    public String getInversa(){
        return  inversa;
    }

    public String getXTY(){
        return XTY;
    }

    public String getBhat(){
        return Bhat;
    }

    public String getSumX(){
        return  SumX;
    }

    public  String getSumY(){
        return  SumY;
    }

    public String getMulXY(){
        return  mulXY;
    }

    public  String getSumXY(){
        return sumXY;
    }

    public  String getDivisor(){
        return  divisor;
    }

    public  String getDividendo(){
        return  dividendo;
    }

    public  String getCalB1(){
        return  calB1;
    }

    public  String getCalB0(){
        return  calB0;
    }

}
