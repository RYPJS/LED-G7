package eeddg10.susalud;

public class NodoCola {
    private Paciente paciente;
    private NodoCola atras;

    public NodoCola(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public NodoCola() {
        this.paciente = new Paciente("", "", "", "");
    }
    
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente cliente) {
        this.paciente = cliente;
    }
    
    public NodoCola getAtras() {
        return atras;
    }
    
    public void setAtras(NodoCola atras) {
        this.atras = atras;
    }

    @Override
    public String toString() {
        return this.paciente.getNombreCompleto();
    }
}