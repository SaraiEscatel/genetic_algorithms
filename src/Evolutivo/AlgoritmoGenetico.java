package Evolutivo;

import java.util.ArrayList;
import java.util.Random;

public class AlgoritmoGenetico {

    private double tasaCruce = 0.7;
    private double tasaMutacion = 0.1;
    private int tamanoPoblacion = 10;
    private double fitness = 0.95;
    private Random random = new Random();

    // Getters para los campos privados
    public double getTasaCruce() {
        return tasaCruce;
    }

    public double getTasaMutacion() {
        return tasaMutacion;
    }

    public int getTamanoPoblacion() {
        return tamanoPoblacion;
    }

    public double getFitness() {
        return fitness;
    }

    public AlgoritmoGenetico() {
        // Constructor sin cambios, puedes inicializar los valores aquí si los necesitas
    }


    public ArrayList<double[]> inicializarPoblacion() {
        ArrayList<double[]> poblacion = new ArrayList<>();
        for (int i = 0; i < tamanoPoblacion; i++) {
            poblacion.add(generarCromosomaAleatorio());
        }
        return poblacion;
    }


    public void imprimirPoblacion(ArrayList<double[]> poblacion) {
        System.out.println("Población actual:");
        for (double[] cromosoma : poblacion) {
            System.out.printf("[%.2f, %.2f]%n", cromosoma[0], cromosoma[1]);
        }
    }


    private double[] generarCromosomaAleatorio() {
        return new double[]{
                random.nextDouble() * 100, random.nextDouble() * 100
        };
    }


    private double[] seleccionarAleatorio(ArrayList<double[]> poblacion) {
        return poblacion.get(random.nextInt(poblacion.size()));
    }


    public static void main(String[] args) {
        AlgoritmoGenetico ag = new AlgoritmoGenetico();

        // Inicializar y mostrar la población
        ArrayList<double[]> poblacion = ag.inicializarPoblacion();
        ag.imprimirPoblacion(poblacion);
    }

    public void ejecutarAlgoritmoGenetico(DataSetEvolutivo dataSetEvolutivo) {
        ArrayList<double[]> poblacion = inicializarPoblacion();
        int generaciones = 0;
        while (generaciones < 1000) { // Número máximo de generaciones
            // Evaluar y seleccionar
            ArrayList<double[]> nuevaPoblacion = realizarCruce(poblacion);
            realizarMutacion(nuevaPoblacion);

            // Reemplazar la población vieja con la nueva
            poblacion = nuevaPoblacion;

            // Evaluar el fitness de la mejor solución
            double mejorFitness = 0;
            for (double[] cromosoma : poblacion) {
                double fitness = evaluarFitness(cromosoma, dataSetEvolutivo);
                if (fitness > mejorFitness) {
                    mejorFitness = fitness;
                }
            }

            System.out.println("Generación " + generaciones + " - Mejor fitness: " + mejorFitness);
            generaciones++;
        }
    }

    public void realizarMutacion(ArrayList<double[]> poblacion) {
        for (double[] cromosoma : poblacion) {
            if (random.nextDouble() < tasaMutacion) {
                int genAMutar = random.nextInt(cromosoma.length);
                cromosoma[genAMutar] = random.nextDouble() * 100; // Generar un nuevo valor aleatorio para ese gen
            }
        }
    }

    public ArrayList<double[]> realizarCruce(ArrayList<double[]> poblacion) {
        ArrayList<double[]> nuevaPoblacion = new ArrayList<>();
        while (nuevaPoblacion.size() < tamanoPoblacion) {
            double[] padre1 = seleccionarAleatorio(poblacion);
            double[] padre2 = seleccionarAleatorio(poblacion);

            if (random.nextDouble() < tasaCruce) {
                // Cruce de un punto
                int puntoCruce = random.nextInt(padre1.length);
                double[] hijo1 = new double[padre1.length];
                double[] hijo2 = new double[padre2.length];

                // Copiar los genes hasta el punto de cruce
                System.arraycopy(padre1, 0, hijo1, 0, puntoCruce);
                System.arraycopy(padre2, puntoCruce, hijo1, puntoCruce, padre2.length - puntoCruce);
                System.arraycopy(padre2, 0, hijo2, 0, puntoCruce);
                System.arraycopy(padre1, puntoCruce, hijo2, puntoCruce, padre1.length - puntoCruce);

                nuevaPoblacion.add(hijo1);
                nuevaPoblacion.add(hijo2);
            } else {
                nuevaPoblacion.add(padre1);
                nuevaPoblacion.add(padre2);
            }
        }
        return nuevaPoblacion;
    }

    // Supongamos que estamos usando una regresión lineal, donde el cromosoma representa los coeficientes
// de la recta: y = a * x + b
    public double evaluarFitness(double[] cromosoma, DataSetEvolutivo dataSetEvolutivo) {
        double errorTotal = 0;
        for (int i = 0; i < dataSetEvolutivo.x.length; i++) {
            double prediccion = cromosoma[0] * dataSetEvolutivo.x[i] + cromosoma[1]; // y = a * x + b
            errorTotal += Math.pow(dataSetEvolutivo.y[i] - prediccion, 2); // Error cuadrático
        }
        return 1 / (1 + errorTotal); // Fitness: entre 0 y 1 (cuanto menor es el error, mayor es el fitness)
    }




}
