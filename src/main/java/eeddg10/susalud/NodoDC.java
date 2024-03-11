package eeddg10.susalud;

public class NodoDC {
    private Expediente expediente;
    private NodoDC siguiente;
    private NodoDC anterior;

    public NodoDC(Expediente expediente) {
        this.expediente = expediente;
        this.siguiente = null;
        this.anterior = null;
    }
    
    public NodoDC() {
        this.expediente = new Expediente();
        this.siguiente = null;
        this.anterior = null;
    }
    
    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public NodoDC getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDC siguiente) {
        this.siguiente = siguiente;
    }
    
    public NodoDC getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDC anterior) {
        this.anterior = anterior;
    }
}