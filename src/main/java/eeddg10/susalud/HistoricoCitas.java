package eeddg10.susalud;

import java.util.Date;
import javax.swing.JOptionPane;

public class HistoricoCitas {
    private NodoCita cabeza;
    private NodoCita ultimo;
    
    public boolean esVacia(){
        if(cabeza == null){
           return true;
        }else{
           return false;
        }
    }

    public void agregarCita() {
        Cita cita = solicitarInformacion();
        NodoCita nuevaCita = new NodoCita(cita);
        if (esVacia()) {
            cabeza = nuevaCita;
            ultimo = cabeza;
            ultimo.setSiguiente(cabeza);
        } else {
            NodoCita auxiliar = new NodoCita(cita);
            auxiliar.setSiguiente(cabeza);
            cabeza = auxiliar;
            ultimo.setSiguiente(cabeza);
        }
    }
    
    public Cita solicitarInformacion(){
        Cita cita = new Cita();
        cita.setNombreDoctor(JOptionPane.showInputDialog("Ingrese el nombre del doctor: "));
        cita.setDiagnostico(JOptionPane.showInputDialog("Ingrese el diagnostico del paciente: "));
        return cita;
    }
    
    public void mostrarCitas() {       
        NodoCita aux = cabeza;
        
        if(aux != null)
        {
            String respuesta = "Citas";
            respuesta = respuesta + "\nNombre doctor: "+aux.getCita().getNombreDoctor()+", Diagnostico: "+aux.getCita().getDiagnostico();
            aux = aux.getSiguiente();
        
            while(aux != cabeza)
            {
                respuesta = respuesta + "\nNombre doctor: "+aux.getCita().getNombreDoctor()+", Diagnostico: "+aux.getCita().getDiagnostico();
                aux = aux.getSiguiente();
            }
            JOptionPane.showMessageDialog(null, respuesta);
        }else{
            JOptionPane.showMessageDialog(null, "El paciente no tiene medicamentos preescritos");
        }
    }
}

class NodoCita {
    private Cita cita;
    private NodoCita siguiente;

    public NodoCita() {
        this.cita = new Cita();
        this.siguiente = null;
    }
    
    public NodoCita(Cita cita) {
        this.cita = cita;
        this.siguiente = null;
    }

    public NodoCita getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCita siguiente) {
        this.siguiente = siguiente;
    }
    
    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita Cita) {
        this.cita = cita;
    }
}

class Cita {
    private Date fecha;
    private String nombreDoctor;
    private String diagnostico;
    
    public Cita(String nombreDoctor, String diagnostico) {
        this.fecha = new Date();
        this.nombreDoctor = nombreDoctor;
        this.diagnostico = diagnostico;
    } 
    
    public Cita() {
        this.fecha = new Date();
        this.nombreDoctor = "";
        this.diagnostico = "";
    }
    
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String getNombreDoctor() {
        return this.nombreDoctor;
    }
    
    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }
    
    public String getDiagnostico() {
        return this.diagnostico;
    }
    
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
}