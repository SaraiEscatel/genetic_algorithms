package Evolutivo;

import java.util.ArrayList;

public class Ejecucion {
    private ArrayList<double[]> poblacion;
    private Ruleta ruleta = new Ruleta();

    public void ejecutar(DataSetEvolutivo dataSetEvolutivo) {
        // Inicializar los objetos necesarios
        AlgoritmoGenetico ag = new AlgoritmoGenetico();
        Calculos calculos = new Calculos();
       // DataSet dataSet = new DataSet();

        // Generar la población inicial
        poblacion = ag.inicializarPoblacion();
        ag.imprimirPoblacion(poblacion);

        // Calcular el R2 de la población inicial
        double[] r2Poblacion = calculos.calcularR2Poblacion(poblacion, dataSetEvolutivo.x, dataSetEvolutivo.y);
        calculos.imprimirCalcularR2Poblacion(r2Poblacion);

        // Verificar si ya se alcanzó el fitness
        if (calculos.seAlcanzoElFitness(r2Poblacion, ag.getFitness())) {
            System.out.println("Se alcanzó el fitness, termina la ejecución.");
            return;
        }

        // Si no se alcanzó el fitness, comenzar las generaciones
        for (int generacion = 2; generacion < 20; generacion++) {
            System.out.println("\n=== Generación " + generacion + " ===");

            // Calcular la ruleta acumulada
            ArrayList<Double> ruletaAcumulada = ruleta.calcularProbabilidades(r2Poblacion);
            ruleta.imprimirRuleta(ruletaAcumulada);

            // Seleccionar cromosomas usando la ruleta para el cruce
            CrossOver co = new CrossOver(ag, r2Poblacion);
            ArrayList<double[]> nuevaPoblacion = co.realizarCruce(poblacion);

            // Actualizar la población
            poblacion = nuevaPoblacion;
            ag.imprimirPoblacion(poblacion);

            // Realizar mutación y manejar duplicados
            co.realizarMutacion(poblacion);
            poblacion = co.manejarDuplicados(poblacion);

            // Calcular el R2 de la nueva población
            r2Poblacion = calculos.calcularR2Poblacion(poblacion, dataSetEvolutivo.x, dataSetEvolutivo.y);
            calculos.imprimirCalcularR2Poblacion(r2Poblacion);

            // Verificar si se alcanzó el fitness
            if (calculos.seAlcanzoElFitness(r2Poblacion, ag.getFitness())) {
                System.out.println("Se alcanzó el fitness en la generación " + generacion);
                break;
            }
        }
    }
}
