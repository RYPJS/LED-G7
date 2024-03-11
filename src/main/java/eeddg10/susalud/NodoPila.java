package eeddg10.susalud;

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