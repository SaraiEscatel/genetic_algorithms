public class PolynomialRegression {

    DataSet dataSet = new DataSet();
    MethodoPolinomial methodoPolinomial = new MethodoPolinomial();
    Operations operations = new Operations();

    double [] bDeHat;

    public PolynomialRegression(){
        this.bDeHat = calcularCoeficientes();
    }

    public double[] calcularCoeficientes() {
        //X^T
        // Crear matriz X
        double[][] matrizX = methodoPolinomial.crearMatriz(dataSet.grado, dataSet.x);
        double[][] matrizXT = methodoPolinomial.crearMatrizT(matrizX, dataSet.x);

        //(X^T X)
        double[][] xtx = operations.productoMatrices(matrizXT, matrizX, dataSet.grado);

        //(X^T X)^-1
        double[][] xtxInversa = operations.IversaProducto(xtx);

        //X^T * Y
        double[] xtY = operations.productoMatrizTranspuestaPorVector(matrizXT, dataSet.y);

        //B de Hat=(X^T X )^-1 * X^T * Y
        double[] BHat = operations.productoMatrizTranspuestaPorVector(xtxInversa, xtY);

        return BHat;


    }
}
