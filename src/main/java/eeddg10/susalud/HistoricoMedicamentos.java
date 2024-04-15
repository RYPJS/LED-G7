package eeddg10.susalud;

import java.util.Date;
import javax.swing.JOptionPane;

/**
 * La clase HistoricoMedicamentos representa una lista circular de medicamentos prescritos.
 * Cada objeto HistoricoMedicamentos contiene un nodo que representa la cabeza y el último elemento de la lista.
 */
public class HistoricoMedicamentos {
    private NodoMedicamento cabeza;
    private NodoMedicamento ultimo;

    /**
     * Este método verifica si la lista está vacía.
     *
     * @return Verdadero si la lista está vacía, falso en caso contrario.
     */
    public boolean esVacia() {
        if (cabeza == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Este método agrega un medicamento a la lista.
     * Solicita la información del medicamento, crea un nuevo nodo con el medicamento y lo inserta en la lista.
     */
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

    /**
     * Este método solicita la información del medicamento y crea un nuevo medicamento.
     *
     * @return El medicamento creado.
     */
    public Medicamento solicitarInformacion() {
        Medicamento medicamento = new Medicamento();
        medicamento.setMedicamentoPrescrito(JOptionPane.showInputDialog("Ingrese el medicamento preescrito: "));
        return medicamento;
    }

    /**
     * Este método muestra todos los medicamentos en la lista.
     * Recorre la lista y muestra la información de cada medicamento.
     */
    public void mostrarMedicamentos() {
        NodoMedicamento aux = cabeza;

        if (aux != null) {
            String respuesta = "Medicamentos";
            respuesta = respuesta + "\nFecha prescripcion: " + aux.getMedicamento().getFechaPreescripcion() + ", Medicamento: " + aux.getMedicamento().getMedicamentoPrescrito();
            aux = aux.getSiguiente();

            while (aux != cabeza) {
                respuesta = respuesta + "\nFecha prescripcion: " + aux.getMedicamento().getFechaPreescripcion() + ", Medicamento: " + aux.getMedicamento().getMedicamentoPrescrito();
                aux = aux.getSiguiente();
            }
            JOptionPane.showMessageDialog(null, respuesta);
        } else {
            JOptionPane.showMessageDialog(null, "El paciente no tiene medicamentos preescritos");
        }
    }
}

/**
 * La clase NodoMedicamento representa un nodo en la lista circular de medicamentos.
 * Cada objeto NodoMedicamento contiene un medicamento y una referencia al siguiente nodo en la lista.
 */
class NodoMedicamento {
    private Medicamento medicamento;
    private NodoMedicamento siguiente;

    /**
     * Constructor con todos los parámetros.
     *
     * @param medicamento El medicamento del nodo.
     */
    public NodoMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
        this.siguiente = null;
    }

    /**
     * Constructor sin parámetros. Inicializa el medicamento con un nuevo medicamento y el siguiente nodo con null.
     */
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