package eeddg10.susalud;

public class ListaPreguntas {
    private NodoPregunta cabeza;

    public ListaPreguntas() {
        this.cabeza = null;
    }

    // Método para insertar un nodo al principio de la lista
    public void insertarAlPrincipio(Pregunta pregunta) {
        NodoPregunta nuevoNodo = new NodoPregunta(pregunta);
        nuevoNodo.setSiguiente(cabeza);
        cabeza = nuevoNodo;
    }

    // Método para imprimir la lista
    public void imprimirLista() {
        NodoPregunta actual = cabeza;
        while (actual != null) {
            System.out.print(actual.getPregunta().getCodigo() + " ");
            actual = actual.getSiguiente();
        }
        System.out.println();
    }

    public NodoPregunta getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoPregunta cabeza) {
        this.cabeza = cabeza;
    }
}

class NodoPregunta {
    private Pregunta pregunta;
    private NodoPregunta siguiente;

    public NodoPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
        this.siguiente = null;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public NodoPregunta getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPregunta siguiente) {
        this.siguiente = siguiente;
    }    
}