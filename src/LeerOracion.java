import java.util.Scanner;

class Nodo {
    String palabra;
    int longitud;
    Nodo anterior;
    Nodo siguiente;

    public Nodo(String palabra) {
        this.palabra = palabra;
        this.longitud = palabra.length();
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

    public void insertarAlFinal(String palabra) {
        Nodo nuevoNodo = new Nodo(palabra);
        if (cola == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            nuevoNodo.anterior = cola;
            cola.siguiente = nuevoNodo;
            cola = nuevoNodo;
        }
    }

    public void imprimirEnOrdenInverso() {
        Nodo actual = cola;
        while (actual != null) {
            System.out.print(actual.palabra + " ");
            actual = actual.anterior;
        }
        System.out.println();
    }

    public void borrarNodo(String palabra) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.palabra.equals(palabra)) {
                if (actual == cabeza && actual == cola) {
                    cabeza = null;
                    cola = null;
                } else if (actual == cabeza) {
                    cabeza = actual.siguiente;
                    cabeza.anterior = null;
                } else if (actual == cola) {
                    cola = actual.anterior;
                    cola.siguiente = null;
                } else {
                    actual.anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = actual.anterior;
                }
                return;
            }
            actual = actual.siguiente;
        }
        System.out.println("La palabra '" + palabra + "' no se encontró en la lista.");
    }

    public void imprimirAnteriorYSiguiente(String palabra) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.palabra.equals(palabra)) {
                if (actual.anterior != null) {
                    System.out.println("Palabra anterior: " + actual.anterior.palabra);
                } else {
                    System.out.println("No hay palabra anterior a '" + palabra + "'.");
                }
                if (actual.siguiente != null) {
                    System.out.println("Palabra siguiente: " + actual.siguiente.palabra);
                } else {
                    System.out.println("No hay palabra siguiente a '" + palabra + "'.");
                }
                return;
            }
            actual = actual.siguiente;
        }
        System.out.println("La palabra '" + palabra + "' no se encontró en la lista.");
    }
}

public class LeerOracion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaDoblementeEnlazada lista = new ListaDoblementeEnlazada();

        System.out.println("Ingrese una oración:");
        String[] palabras = scanner.nextLine().split("\\s+");

        for (String palabra : palabras) {
            lista.insertarAlFinal(palabra);
        }

        System.out.println("Oración en orden inverso:");
        lista.imprimirEnOrdenInverso();

        System.out.println("Ingrese una palabra para eliminar de la lista:");
        String palabraAEliminar = scanner.nextLine();
        lista.borrarNodo(palabraAEliminar);

        System.out.println("Ingrese una palabra para ver la palabra anterior y siguiente:");
        String palabraABuscar = scanner.nextLine();
        lista.imprimirAnteriorYSiguiente(palabraABuscar);

        scanner.close();
    }
}
