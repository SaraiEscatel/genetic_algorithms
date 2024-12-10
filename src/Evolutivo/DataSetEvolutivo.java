package Evolutivo;

import java.io.Serializable;

public class DataSetEvolutivo implements Serializable {

    double[] x;
    double[] y;
    int numGenes;

    public DataSetEvolutivo(double [] x, double [] y){
        this.x = x;
        this.y = y;
        this.numGenes = 2;
    }

    public double[] getY() {
        return y;
    }

    public double[] getX() {
        return x;
    }
}
