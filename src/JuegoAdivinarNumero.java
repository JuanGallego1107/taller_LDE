import java.util.Random;
import java.util.Scanner;

class Nodo {
    int numero;
    Nodo anterior;
    Nodo siguiente;

    public Nodo(int numero) {
        this.numero = numero;
        this.anterior = null;
        this.siguiente = null;
    }
}

class ListaDoblementeEnlazada {
    Nodo cabeza;
    Nodo cola;

    public ListaDoblementeEnlazada() {
        this.cabeza = null;
        this.cola = null;
    }

    public void insertarAlFinal(int numero) {
        Nodo nuevoNodo = new Nodo(numero);
        if (cola == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            nuevoNodo.anterior = cola;
            cola.siguiente = nuevoNodo;
            cola = nuevoNodo;
        }
    }

    public int obtenerNumeroAleatorio() {
        Random random = new Random();
        return random.nextInt(100); // Genera números aleatorios entre 0 y 99
    }

    public void jugar() {
        int numeroSeleccionado = obtenerNumeroAleatorio();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Se ha seleccionado un número aleatorio entre 0 y 99.");
        System.out.println("¿Puede adivinar el número?");

        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println("¿Es " + actual.numero + "? (s/n)");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                if (actual.numero == numeroSeleccionado) {
                    System.out.println("¡Felicidades! ¡Has adivinado el número!");
                    return;
                } else {
                    System.out.println("Lo siento, ese no es el número.");
                }
            } else if (respuesta.equalsIgnoreCase("n")) {
                actual = actual.siguiente;
                if (actual == null) {
                    System.out.println("Llegaste al final de la lista. ¡Perdiste!");
                    return;
                }
            } else {
                System.out.println("Por favor, ingrese 's' para sí o 'n' para no.");
            }
        }
    }
}

public class JuegoAdivinarNumero {
    public static void main(String[] args) {
        ListaDoblementeEnlazada lista = new ListaDoblementeEnlazada();

        // Generar una lista de 10 números aleatorios
        for (int i = 0; i < 10; i++) {
            lista.insertarAlFinal(lista.obtenerNumeroAleatorio());
        }

        lista.jugar();
    }
}
