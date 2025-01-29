import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

public class Ejercicio4 {

    public static void main(String[] args) {
        // Parámetros configurables
        int cantidadDias = 10;
        int ventanaDias = 3;
        int gastoMaximo = 100;

        int[] gastos = generarGastosAleatorios(cantidadDias, gastoMaximo);

        System.out.println("Gastos generados aleatoriamente: ");
        mostrarGastos(gastos);

        int notificaciones = contarNotificaciones(gastos, ventanaDias);
        System.out.println("Cantidad de notificaciones: " + notificaciones);
    }

    public static int[] generarGastosAleatorios(int cantidadDias, int gastoMaximo) {
        Random random = new Random();
        int[] gastos = new int[cantidadDias];
        for (int i = 0; i < cantidadDias; i++) {
            gastos[i] = random.nextInt(gastoMaximo) + 1;
        }
        return gastos;
    }

    public static void mostrarGastos(int[] gastos) {
        for (int gasto : gastos) {
            System.out.print(gasto + " ");
        }
        System.out.println();
    }

    public static int contarNotificaciones(int[] gastos, int ventanaDias) {
        int notificaciones = 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < gastos.length; i++) {
            if (i >= ventanaDias) {
                double mediana = obtenerMediana(minHeap, maxHeap);
                if (gastos[i] >= 2 * mediana) {
                    notificaciones++;
                }

                int gastoRemover = gastos[i - ventanaDias];
                if (!maxHeap.remove(gastoRemover)) {
                    minHeap.remove(gastoRemover);
                }
                balancearHeaps(minHeap, maxHeap);
            }

            if (maxHeap.isEmpty() || gastos[i] <= maxHeap.peek()) {
                maxHeap.add(gastos[i]);
            } else {
                minHeap.add(gastos[i]);
            }
            balancearHeaps(minHeap, maxHeap);
        }

        return notificaciones;
    }

    private static double obtenerMediana(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        return maxHeap.size() > minHeap.size() ? maxHeap.peek() : (maxHeap.peek() + minHeap.peek()) / 2.0;
    }

    private static void balancearHeaps(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        }
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }
}
