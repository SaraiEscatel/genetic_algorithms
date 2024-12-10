package Evolutivo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class CrossOver {
    private Random random = new Random();
    private int tamanoPoblacion;
    private double tasaCruce;
    private double tasaMutacion;
    private double[] rCuadrada;
    private Ruleta ruleta;

    // Constructor que inicializa los parámetros para el cruce y mutación
    public CrossOver(AlgoritmoGenetico ag, double[] r2Poblacion) {
        this.tamanoPoblacion = ag.getTamanoPoblacion();
        this.tasaCruce = ag.getTasaCruce();
        this.tasaMutacion = ag.getTasaMutacion();
        this.rCuadrada = r2Poblacion;
        this.ruleta = new Ruleta();  // Inicializar la ruleta
    }

    public ArrayList<double[]> realizarCruce(ArrayList<double[]> poblacion) {
        ArrayList<double[]> nuevaPoblacion = new ArrayList<>();
        Set<Integer> cromosomasSeleccionados = new HashSet<>(); // Evitar duplicados

        // Calcular probabilidades acumuladas para la ruleta
        ArrayList<Double> ruletaAcumulada = ruleta.calcularProbabilidades(rCuadrada);
        ruleta.mostrarRuleta(ruletaAcumulada);

        // Realizar cruce en la población
        for (double[] cromosoma : poblacion) {
            int genParaCrossover = random.nextInt(cromosoma.length); // Selección de gen a cruzar

            if (nuevaPoblacion.size() >= tamanoPoblacion) break;

            if (seleccionarPadre(tasaCruce)) {
                // Seleccionar el segundo cromosoma usando la ruleta
                double[] segundoCromosoma = ruleta.girarRuleta(ruletaAcumulada, cromosomasSeleccionados, poblacion);

                if (segundoCromosoma == null) {
                    System.out.println("No se pudo seleccionar un segundo cromosoma.");
                    continue;
                }

                // Cruzar los cromosomas seleccionados
                hacerCrossOverGenes(cromosoma, segundoCromosoma, genParaCrossover);
                nuevaPoblacion.add(cromosoma);
                nuevaPoblacion.add(segundoCromosoma);
            } else {
                nuevaPoblacion.add(cromosoma);
            }
        }

        // Aplicar mutación y manejar duplicados
        realizarMutacion(nuevaPoblacion);
        nuevaPoblacion = manejarDuplicados(nuevaPoblacion);

        System.out.println("Tamaño de la nueva población: " + nuevaPoblacion.size());
        return nuevaPoblacion;
    }

    // Cruce de genes entre dos cromosomas en el gen especificado
    private void hacerCrossOverGenes(double[] cromosoma1, double[] cromosoma2, int genParaCruzar) {
        double aux = cromosoma1[genParaCruzar];
        cromosoma1[genParaCruzar] = cromosoma2[genParaCruzar];
        cromosoma2[genParaCruzar] = aux;
    }


    public boolean seleccionarPadre(double tasaCruce) {
        return random.nextDouble() < tasaCruce;
    }

    // Aplicar mutación a los cromosomas
    public void realizarMutacion(ArrayList<double[]> poblacion) {
        for (double[] cromosoma : poblacion) {
            if (random.nextDouble() < tasaMutacion) {
                int genAMutar = random.nextInt(cromosoma.length);
                cromosoma[genAMutar] = random.nextDouble() * 100;
            }
        }
    }

    // Eliminar cromosomas duplicados en la población
    public ArrayList<double[]> manejarDuplicados(ArrayList<double[]> poblacion) {
        Set<String> cromosomasUnicos = new HashSet<>();
        ArrayList<double[]> nuevaPoblacion = new ArrayList<>();

        for (double[] cromosoma : poblacion) {
            String representacion = String.join(",", Double.toString(cromosoma[0]), Double.toString(cromosoma[1]));
            if (!cromosomasUnicos.contains(representacion)) {
                cromosomasUnicos.add(representacion);
                nuevaPoblacion.add(cromosoma);
            } else {
                nuevaPoblacion.add(generarNuevoCromosoma(cromosoma.length)); // Generar nuevo cromosoma si hay duplicado
            }
        }
        return nuevaPoblacion;
    }

    // Generar un nuevo cromosoma aleatorio
    public double[] generarNuevoCromosoma(int tamano) {
        double[] nuevoCromosoma = new double[tamano];
        for (int i = 0; i < tamano; i++) {
            nuevoCromosoma[i] = random.nextDouble() * 100;
        }
        return nuevoCromosoma;
    }
}
