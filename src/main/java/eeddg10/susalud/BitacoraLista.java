package eeddg10.susalud;

import java.util.Date;
import javax.swing.JOptionPane;

/**
 * La clase BitacoraLista representa una lista de bitácoras de atención a pacientes.
 * Cada objeto BitacoraLista contiene un nodo que representa la cabeza de la lista.
 */
public class BitacoraLista {
    NodoLS cabeza;

    /**
     * Constructor sin parámetros. Inicializa la cabeza de la lista con null.
     */
    public BitacoraLista() {
        this.cabeza = null;
    }

    /**
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
     * Este método inserta una bitácora en la lista.
     * La bitácora se inserta en orden ascendente según la cédula del paciente.
     *
     * @param paciente El paciente para el que se va a crear la bitácora.
     */
    public void insertarBitacora(Paciente paciente) {
        Bitacora bitacora = new Bitacora();
        bitacora.setFicha(paciente.getFicha());
        bitacora.setFechaAtencion(new Date());
        bitacora.setCedula(paciente.getCedula());
        bitacora.setNombre(paciente.getNombreCompleto());
        bitacora.setFechaAtencion(paciente.getIngreso());

        if (cabeza == null) {
            cabeza = new NodoLS(bitacora);
        } else {
            if (bitacora.getCedula() < cabeza.getBitacora().getCedula()) {
                //Insertamos al inicio
                NodoLS auxiliar = new NodoLS(bitacora);
                auxiliar.setSiguiente(cabeza);
                cabeza = auxiliar;
            } else {
                if (cabeza.getSiguiente() == null) {
                    //Insertamos al final
                    NodoLS auxiliar = new NodoLS(bitacora);
                    cabeza.setSiguiente(auxiliar);
                } else {
                    //Insertamos en el medio de la lista
                    NodoLS auxiliar = cabeza;
                    while (auxiliar.getSiguiente() != null &&
                            auxiliar.getSiguiente().getBitacora().getCedula() < bitacora.getCedula()) {
                        auxiliar = auxiliar.getSiguiente();
                    }

                    NodoLS temporal = new NodoLS(bitacora);
                    temporal.setSiguiente(auxiliar.getSiguiente());
                    auxiliar.setSiguiente(temporal);
                }
            }
        }
    }

    /**
     * @return Una representación en cadena de la lista de bitácoras.
     */
    @Override
    public String toString() {
        NodoLS auxiliar = cabeza;
        String respuesta = "Lista: \n";

        while (auxiliar != null) {
            respuesta += auxiliar.toString() + "\n";
            auxiliar = auxiliar.getSiguiente();
        }

        return respuesta;
    }
}