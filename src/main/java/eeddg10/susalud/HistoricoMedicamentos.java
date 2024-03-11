package eeddg10.susalud;

import java.util.Date;
import javax.swing.JOptionPane;

public class HistoricoMedicamentos {
    private NodoMedicamento cabeza;
    private NodoMedicamento ultimo;
    
    public boolean esVacia(){
        if(cabeza == null){
           return true;
        }else{
           return false;
        }
    }

    public void agregarMedicamento() {
        Medicamento medicamento = solicitarInformacion();
        NodoMedicamento nuevoMedicamento = new NodoMedicamento(medicamento);
        if (esVacia()) {
            cabeza = nuevoMedicamento;
            ultimo = cabeza;
            ultimo.setSiguiente(cabeza);
        } else {
            NodoMedicamento auxiliar = new NodoMedicamento(medicamento);
            auxiliar.setSiguiente(cabeza);
            cabeza = auxiliar;
            ultimo.setSiguiente(cabeza);
        }
    }
    
    public Medicamento solicitarInformacion(){
        Medicamento medicamento = new Medicamento();
        medicamento.setMedicamentoPrescrito(JOptionPane.showInputDialog("Ingrese el medicamento preescrito: "));
        return medicamento;
    }
    
    public void mostrarMedicamentos() {       
        NodoMedicamento aux = cabeza;
        
        if(aux != null)
        {
            String respuesta = "Medicamentos";
            respuesta = respuesta + "\nFecha prescripcion: "+aux.getMedicamento().getFechaPreescripcion()+", Medicamento: "+aux.getMedicamento().getMedicamentoPrescrito();
            aux = aux.getSiguiente();
        
            while(aux != cabeza)
            {
                respuesta = respuesta + "\nFecha prescripcion: "+aux.getMedicamento().getFechaPreescripcion()+", Medicamento: "+aux.getMedicamento().getMedicamentoPrescrito();
                aux = aux.getSiguiente();
            }
            JOptionPane.showMessageDialog(null, respuesta);
        }else{
            JOptionPane.showMessageDialog(null, "El paciente no tiene medicamentos preescritos");
        }
    }
}

class NodoMedicamento {
    private Medicamento medicamento;
    private NodoMedicamento siguiente;

    public NodoMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
        this.siguiente = null;
    }
    
    public NodoMedicamento() {
        this.medicamento = new Medicamento();
        this.siguiente = null;
    }
    
    public NodoMedicamento getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoMedicamento siguiente) {
        this.siguiente = siguiente;
    }
    
    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }
}

class Medicamento {
    private Date fechaPreescripcion;
    private String medicamentoPrescrito;
    
    public Medicamento(String medicamentoPrescrito) {
        this.fechaPreescripcion = new Date();
        this.medicamentoPrescrito = medicamentoPrescrito;
    } 
    
    public Medicamento() {
        this.fechaPreescripcion = new Date();
        this.medicamentoPrescrito = "";
    }
    
    public Date getFechaPreescripcion() {
        return this.fechaPreescripcion;
    }
    
    public void setFechaPreescripcion(Date fechaPreescripcion) {
        this.fechaPreescripcion = fechaPreescripcion;
    }
    
    public String getMedicamentoPrescrito() {
        return this.medicamentoPrescrito;
    }
    
    public void setMedicamentoPrescrito(String medicamentoPrescrito) {
        this.medicamentoPrescrito = medicamentoPrescrito;
    }
}