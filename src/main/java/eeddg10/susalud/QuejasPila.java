
package eeddg10.susalud;
/**
 * La clase QuejasPila representa una pila de quejas.
 * Cada objeto QuejasPila contiene una referencia al nodo superior de la pila (tope).
 */
public class QuejasPila {

    private NodoPila tope;


    public String sacarQueja() {
        if (esVacia()) {
            return null;
        }

        String queja = tope.getQueja();
        tope = tope.getSiguiente();
        return queja;
    }

    public boolean esVacia() {
        return tope == null;
    }

    public void agregarQuejas(String queja) {
        NodoPila nuevoNodo = new NodoPila(queja);
        nuevoNodo.setSiguiente(tope);
        tope = nuevoNodo;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        NodoPila current = tope;

        while (current != null) {
            result.append(current.getQueja()).append("\n");
            current = current.getSiguiente();
        }

        return result.toString();
    }
    
}
