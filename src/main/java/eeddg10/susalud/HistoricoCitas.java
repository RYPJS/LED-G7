package eeddg10.susalud;

import java.util.Date;
import javax.swing.JOptionPane;

/**
 * La clase HistoricoCitas representa una lista circular de citas médicas.
 * Cada objeto HistoricoCitas contiene un nodo que representa la cabeza y el último elemento de la lista.
 */
public class HistoricoCitas {
    private NodoCita cabeza;
    private NodoCita ultimo;

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
     * Este método agrega una cita a la lista.
     * Solicita la información de la cita, crea un nuevo nodo con la cita y lo inserta en la lista.
     */
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

    /**
     * Este método solicita la información de la cita y crea una nueva cita.
     *
     * @return La cita creada.
     */
    public Cita solicitarInformacion() {
        Cita cita = new Cita();
        cita.setNombreDoctor(JOptionPane.showInputDialog("Ingrese el nombre del doctor: "));
        cita.setDiagnostico(JOptionPane.showInputDialog("Ingrese el diagnostico del paciente: "));
        return cita;
    }

    /**
     * Este método muestra todas las citas en la lista.
     * Recorre la lista y muestra la información de cada cita.
     */
    public void mostrarCitas() {
        NodoCita aux = cabeza;

        if (aux != null) {
            String respuesta = "Citas";
            respuesta = respuesta + "\nNombre doctor: " + aux.getCita().getNombreDoctor() + ", Diagnostico: " + aux.getCita().getDiagnostico();
            aux = aux.getSiguiente();

            while (aux != cabeza) {
                respuesta = respuesta + "\nNombre doctor: " + aux.getCita().getNombreDoctor() + ", Diagnostico: " + aux.getCita().getDiagnostico();
                aux = aux.getSiguiente();
            }
            JOptionPane.showMessageDialog(null, respuesta);
        } else {
            JOptionPane.showMessageDialog(null, "El paciente no tiene medicamentos preescritos");
        }
    }
}

/**
 * La clase NodoCita representa un nodo en la lista circular de citas.
 * Cada objeto NodoCita contiene una cita y una referencia al siguiente nodo en la lista.
 */
class NodoCita {
    private Cita cita;
    private NodoCita siguiente;

    /**
     * Constructor sin parámetros. Inicializa la cita con una nueva cita y el siguiente nodo con null.
     */
    public NodoCita() {
        this.cita = new Cita();
        this.siguiente = null;
    }

    /**
     * Constructor con todos los parámetros.
     *
     * @param cita La cita del nodo.
     */
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

/**
 * La clase Cita representa una cita médica.
 * Cada objeto Cita contiene la fecha de la cita, el nombre del doctor y el diagnóstico.
 */
class Cita {
    private Date fecha;
    private String nombreDoctor;
    private String diagnostico;

    /**
     * Constructor con todos los parámetros.
     *
     * @param nombreDoctor El nombre del doctor.
     * @param diagnostico  El diagnóstico.
     */
    public Cita(String nombreDoctor, String diagnostico) {
        this.fecha = new Date();
        this.nombreDoctor = nombreDoctor;
        this.diagnostico = diagnostico;
    }

    /**
     * Constructor sin parámetros. Inicializa la fecha con la fecha actual, el nombre del doctor con una cadena vacía y el diagnóstico con una cadena vacía.
     */
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