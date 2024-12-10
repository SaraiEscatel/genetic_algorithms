package Evolutivo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class Ruleta {
    private Random random = new Random();


    public ArrayList<Double> calcularProbabilidades(double[] rCuadrada) {
        ArrayList<Double> ruletaAcumulada = new ArrayList<>();
        double sumaTotal = 0;

        // Calcular la suma total de las probabilidades (R^2)
        for (double probabilidad : rCuadrada) {
            sumaTotal += probabilidad;
        }

        // Calcular las probabilidades acumuladas
        double acumulado = 0;
        for (double probabilidad : rCuadrada) {
            acumulado += probabilidad / sumaTotal;
            ruletaAcumulada.add(acumulado);
        }

        return ruletaAcumulada;
    }


    public double[] girarRuleta(ArrayList<Double> ruletaAcumulada, Set<Integer> cromosomasSeleccionados, ArrayList<double[]> poblacion) {
        // Generar un número aleatorio dentro del rango acumulado de la ruleta
        double puntoAleatorio = random.nextDouble() * ruletaAcumulada.get(ruletaAcumulada.size() - 1);

        // Buscar el índice en la ruleta acumulada donde se encuentra el punto aleatorio
        int index = -1;
        for (int i = 0; i < ruletaAcumulada.size(); i++) {
            if (puntoAleatorio <= ruletaAcumulada.get(i)) {
                index = i;
                break;
            }
        }

        // Si el índice ya ha sido seleccionado, elegir otro cromosoma
        if (index != -1 && !cromosomasSeleccionados.contains(index)) {
            cromosomasSeleccionados.add(index);
            return poblacion.get(index); // Retorna el cromosoma seleccionado
        } else {
            // Si el cromosoma ya fue seleccionado o no se encontró un índice válido, vuelve a llamar a girarRuleta
            return girarRuleta(ruletaAcumulada, cromosomasSeleccionados, poblacion);
        }
    }


    public void mostrarRuleta(ArrayList<Double> ruletaAcumulada) {
        System.out.println("Ejecutivo.Ruleta:");
        for (int i = 0; i < ruletaAcumulada.size(); i++) {
            System.out.println("Cromosoma " + i + " con probabilidad acumulada: " + ruletaAcumulada.get(i));
        }
    }


    public void imprimirRuleta(ArrayList<Double> ruletaAcumulada) {
        for (int i = 0; i < ruletaAcumulada.size(); i++) {
            System.out.println("Cromosoma " + i + ": " + ruletaAcumulada.get(i));
        }
    }
}
