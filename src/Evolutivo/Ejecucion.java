package Evolutivo;

import java.util.ArrayList;

public class Ejecucion {
    private ArrayList<double[]> poblacion;
    private Ruleta ruleta = new Ruleta();

    public void ejecutar(DataSetEvolutivo dataSetEvolutivo) {

        AlgoritmoGenetico ag = new AlgoritmoGenetico();
        Calculos calculos = new Calculos();
       // DataSet dataSet = new DataSet();


        poblacion = ag.inicializarPoblacion();
        ag.imprimirPoblacion(poblacion);


        double[] r2Poblacion = calculos.calcularR2Poblacion(poblacion, dataSetEvolutivo.x, dataSetEvolutivo.y);
        calculos.imprimirCalcularR2Poblacion(r2Poblacion);


        if (calculos.seAlcanzoElFitness(r2Poblacion, ag.getFitness())) {
            System.out.println("Se alcanzó el fitness, termina la ejecución.");
            return;
        }


        for (int generacion = 2; generacion < 20; generacion++) {
            System.out.println("\n=== Generación " + generacion + " ===");


            ArrayList<Double> ruletaAcumulada = ruleta.calcularProbabilidades(r2Poblacion);
            ruleta.imprimirRuleta(ruletaAcumulada);


            CrossOver co = new CrossOver(ag, r2Poblacion);
            ArrayList<double[]> nuevaPoblacion = co.realizarCruce(poblacion);


            poblacion = nuevaPoblacion;
            ag.imprimirPoblacion(poblacion);


            co.realizarMutacion(poblacion);
            poblacion = co.manejarDuplicados(poblacion);


            r2Poblacion = calculos.calcularR2Poblacion(poblacion, dataSetEvolutivo.x, dataSetEvolutivo.y);
            calculos.imprimirCalcularR2Poblacion(r2Poblacion);


            if (calculos.seAlcanzoElFitness(r2Poblacion, ag.getFitness())) {
                System.out.println("Se alcanzó el fitness en la generación " + generacion);
                break;
            }
        }
    }
}
