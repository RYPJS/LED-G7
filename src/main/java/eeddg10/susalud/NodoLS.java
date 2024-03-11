package eeddg10.susalud;

public class NodoLS {
    
    private NodoLS siguiente;
    private Bitacora bitacora;
    
    public NodoLS() {
        this.bitacora = new Bitacora();
        this.siguiente = null;
    }
    
    public NodoLS(Bitacora bitacora) {
        this.bitacora = bitacora;
        this.siguiente = null;
    }

    public Bitacora getBitacora() {
        return bitacora;
    }

    public void setBitacora(Bitacora bitacora) {
        this.bitacora = bitacora;
    }

    public NodoLS getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLS siguiente) {
        this.siguiente = siguiente;
    }
    
    @Override
    public String toString() {
        return this.bitacora.toString();
    }
}