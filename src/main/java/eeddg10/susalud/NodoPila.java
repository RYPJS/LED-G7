package eeddg10.susalud;
/**
 * La clase NodoPila representa un nodo en una pila de quejas.
 * Cada objeto NodoPila contiene una queja y una referencia al nodo siguiente en la pila.
 */
public class NodoPila {
    private String queja;
    private NodoPila siguiente;

    public NodoPila(String queja) {
        this.queja = queja;
    }

    public String getQueja() {
        return queja;
    }

    public NodoPila getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPila siguiente) {
        this.siguiente = siguiente;
    } 
}