
package eeddg10.susalud;

import java.util.Date;

/**
 * La clase ConsultaBitacoraCitas se encarga de consultar la bitácora de citas.
 * Cada objeto ConsultaBitacoraCitas contiene una lista de bitácoras.
 */
public class ConsultaBitacoraCitas {

    private BitacoraLista bitacoraLista;

    /**
     * Constructor con todos los parámetros.
     *
     * @param bitacoraLista La lista de bitácoras.
     */
    public ConsultaBitacoraCitas(BitacoraLista bitacoraLista) {
        this.bitacoraLista = bitacoraLista;
    }

    /**
     * Este método consulta la bitácora de citas.
     * Imprime la información de cada cita en la bitácora, incluyendo el nombre del paciente y el tiempo de espera.
     * El tiempo de espera se colorea en verde si es menor o igual a 30 segundos, en amarillo si es menor o igual a 60 segundos, y en rojo en caso contrario.
     */
    public void consultarBitacoraCitas() {
        NodoLS auxiliar = bitacoraLista.cabeza;

        if (auxiliar == null) {
            System.out.println("No hay citas en la bitácora del día de hoy.");
            return;
        }

        StringBuilder bitacoraCitas = new StringBuilder();

        while (auxiliar != null) {
            Bitacora cita = auxiliar.getBitacora();
            Date horaLlegada = cita.getFechaLlegada();
            Date horaAtencion = cita.getFechaAtencion();
            long tiempoEsperaSegundos = (horaAtencion.getTime() - horaLlegada.getTime()) / 1000;

            String color;
            if (tiempoEsperaSegundos <= 30) {
                color = "verde";
            } else if (tiempoEsperaSegundos <= 60) {
                color = "amarillo";
            } else {
                color = "rojo";
            }

            bitacoraCitas.append("Paciente: ").append(cita.getNombre()).append("\nTiempo de espera: ").append(tiempoEsperaSegundos).append(" segundos\n");
            auxiliar = auxiliar.getSiguiente();
        }

        System.out.println(bitacoraCitas.toString());
    }
}