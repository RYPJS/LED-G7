package eeddg10.susalud;

/**
 * La clase Cola representa una estructura de datos de tipo cola.
 * Cada objeto Cola contiene un nodo que representa el frente y el último elemento de la cola.
 */
public class Cola {
    private NodoCola frente;
    private NodoCola ultimo;

    /**
     * Este método agrega un elemento al final de la cola.
     *
     * @param elemento El elemento a agregar.
     */
    public void encola(NodoCola elemento) {
        if (frente == null) {
            frente = elemento;
            ultimo = elemento;
        } else {
            ultimo.setAtras(elemento);
            ultimo = elemento;
        }
    }

    /**
     * Este método elimina y devuelve el elemento del frente de la cola.
     *
     * @return El elemento del frente de la cola.
     */
    public NodoCola salirDeCola() {
        NodoCola actual = frente;

        if (frente != null) {
            frente = frente.getAtras();
            actual.setAtras(null);
        }

        return actual;
    }

    /**
     * @return Verdadero si la cola está vacía, falso en caso contrario.
     */
    public boolean esVacia() {
        return frente == null;
    }

    /**
     * @return El último elemento de la cola.
     */
    public NodoCola getCima() {
        return ultimo;
    }

    /**
     * @return Una representación en cadena del frente de la cola.
     */
    @Override
    public String toString() {
        return this.frente.toString();
    }
}
