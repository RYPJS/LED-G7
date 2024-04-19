package eeddg10.susalud;

public class ListaNodosHijos {
    private NodoHijo cabeza;

    public ListaNodosHijos() {
        this.cabeza = null;
    }

    // Método para insertar un nodo al principio de la lista
    public void insertarAlPrincipio(NodoA nodo) {
        NodoHijo nuevoNodo = new NodoHijo(nodo);
        nuevoNodo.setSiguiente(cabeza);
        cabeza = nuevoNodo;
    }

    // Método para imprimir la lista
    public void imprimirLista() {
        NodoHijo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.getNodo().getCodigo() + " ");
            actual = actual.getSiguiente();
        }
        System.out.println();
    }

    public NodoHijo getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoHijo cabeza) {
        this.cabeza = cabeza;
    }
}

class NodoHijo {
    private NodoA nodo;
    private NodoHijo siguiente;

    public NodoHijo(NodoA nodo) {
        this.nodo = nodo;
        this.siguiente = null;
    }

    public NodoA getNodo() {
        return nodo;
    }

    public void setNodo(NodoA nodo) {
        this.nodo = nodo;
    }

    public NodoHijo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoHijo siguiente) {
        this.siguiente = siguiente;
    }    
}