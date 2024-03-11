package eeddg10.susalud;

import static eeddg10.susalud.Main.getPacientesPAtendidos;
import static eeddg10.susalud.Main.incrementarPacientesPAtendidos;
import static eeddg10.susalud.Main.reestablecerPacientesPAtendidos;
import java.awt.Color;
import javax.swing.JOptionPane;

public class AtenderPacientes {

    private static QuejasPila quejasPila = new QuejasPila(); // Declaración fuera del método

    public static void gestionarLlegadaPacientes(Cola preferenciales, Cola regulares, ExpedienteUnico expedienteUnico, BitacoraLista bitacoraLista) {
        int subOption = -1;

        while (subOption != 6) {
            try {
                String input = JOptionPane.showInputDialog(
                        "Seleccione una opción para Gestionar Llegada de Pacientes:\n"
                        + "1. Seleccionar Ficha\n"
                        + "2. Atender Paciente\n"
                        + "3. Abandonar Cola de Pacientes\n"
                        + "4. Mostrar Fichas Pendientes\n"
                        + "5. Mostrar Quejas recibidas\n"
                        + "6. Regresar"
                );

                if (input == null) {
                    subOption = 6; // Si se presiona Cancelar, salir del bucle
                } else {
                    subOption = Integer.parseInt(input);

                    switch (subOption) {
                        case 1:
                            seleccionarFicha(preferenciales, regulares);
                            break;
                        case 2:
                            Paciente paciente = atenderPaciente(preferenciales, regulares);
                            if(paciente != null){
                                expedienteUnico.insertaPaciente();
                                bitacoraLista.insertarBitacora(paciente);
                                JOptionPane.showMessageDialog(null, "Paciente " + paciente.getNombreCompleto() + " su cita ha concluido");
                            }
                            break;
                        case 3:
                            abandonarCola(preferenciales, regulares);
                            break;
                        case 4:
                            mostrarFichasPendientes(preferenciales, regulares);
                            break;
                        case 5:
                            mostrarQuejasRecibidas();
                            break;
                        // No es necesario agregar un caso para la opción 6, ya que el bucle se romperá
                        default:
                            break;
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                // Puedes dejar subOption en su valor actual para repetir la solicitud
            }
        }
    }

    private static void seleccionarFicha(Cola preferenciales, Cola regulares) {
        Object[] options = {"1. Paciente Regular", "2. Paciente Preferencial", "3. Regresar"};
        int result = JOptionPane.showOptionDialog(null, "Seleccione una opción:",
                "Opciones", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        if (result == JOptionPane.YES_OPTION) {
            String nuevaFicha = "R1";
            if (!regulares.esVacia()) {
                String fichaActual = regulares.getCima().getPaciente().getFicha();
                // Utilizar expresión regular para extraer el número de la ficha actual
                String numeroActual = fichaActual.replaceAll("[^0-9]", "");

                try {
                    // Incrementar el número y construir la nueva ficha
                    int numeroNuevo = Integer.parseInt(numeroActual) + 1;
                    nuevaFicha = "R" + numeroNuevo;
                } catch (NumberFormatException e) {
                    System.out.println("Error al extraer el número de la ficha actual.");
                }
            }
            agregarPaciente(regulares, "R", nuevaFicha);
        } else if (result == JOptionPane.NO_OPTION) {
            String nuevaFicha = "P1";
            if (!preferenciales.esVacia()) {
                String fichaActual = preferenciales.getCima().getPaciente().getFicha();
                // Utilizar expresión regular para extraer el número de la ficha actual
                String numeroActual = fichaActual.replaceAll("[^0-9]", "");

                try {
                    // Incrementar el número y construir la nueva ficha
                    int numeroNuevo = Integer.parseInt(numeroActual) + 1;
                    nuevaFicha = "P" + numeroNuevo;
                } catch (NumberFormatException e) {
                    System.out.println("Error al extraer el número de la ficha actual.");
                }
            }
            agregarPaciente(preferenciales, "P", nuevaFicha);
        }
    }

    private static void abandonarCola(Cola preferenciales, Cola regulares) {
        String fichaAbandonar = JOptionPane.showInputDialog("Ingrese el número de ficha a abandonar:");

        if (buscarYEliminarFicha(preferenciales, fichaAbandonar)) {
            String queja = JOptionPane.showInputDialog("Por favor, ingrese la queja correspondiente a la ficha #" + fichaAbandonar + ":");
            quejasPila.agregarQuejas("Ficha #" + fichaAbandonar + " de la cola preferencial abandonó la cola sin ser atendido (a). Queja: " + queja);
            JOptionPane.showMessageDialog(null, "Ficha #" + fichaAbandonar + " de la cola preferencial abandona la cola sin ser atendido (a). Queja registrada.");
        } else if (buscarYEliminarFicha(regulares, fichaAbandonar)) {
            String queja = JOptionPane.showInputDialog("Por favor, ingrese la queja correspondiente a la ficha #" + fichaAbandonar + ":");
            quejasPila.agregarQuejas("Ficha #" + fichaAbandonar + " de la cola regular abandonó la cola sin ser atendido (a). Queja: " + queja);
            JOptionPane.showMessageDialog(null, "Ficha #" + fichaAbandonar + " de la cola regular abandona la cola sin ser atendido (a). Queja registrada.");
        } else {
            JOptionPane.showMessageDialog(null, "La ficha #" + fichaAbandonar + " no se encontró en ninguna cola.");
        }
    }

    private static boolean buscarYEliminarFicha(Cola cola, String ficha) {
        Cola tempCola = new Cola();
        boolean encontrada = false;

        while (!cola.esVacia()) {
            NodoCola pacienteEnTurno = cola.salirDeCola();
            if (!pacienteEnTurno.getPaciente().getFicha().equals(ficha)) {
                tempCola.encola(pacienteEnTurno);
            } else {
                encontrada = true;
            }
        }

        // Restaurar la cola original
        while (!tempCola.esVacia()) {
            cola.encola(tempCola.salirDeCola());
        }

        return encontrada;
    }

    private static void agregarPaciente(Cola cola, String tipo, String ficha) {
        Paciente paciente = new Paciente();
        paciente.setFicha(ficha);
        Boolean error = false;
        do{
            try{
                paciente.setCedula(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de cédula del paciente: ")));
                error = false;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Solo se permite ingresar valores numericos");
                error = true;
            }
        }while(error == true);
        paciente.setNombreCompleto(JOptionPane.showInputDialog("Ingrese el nombre del paciente: "));
        paciente.setTipo(tipo);
        cola.encola(new NodoCola(paciente));
        JOptionPane.showMessageDialog(null, "Su número de ficha es: " + ficha);
    }

    private static Paciente atenderPaciente(Cola preferenciales, Cola regulares) {
        // Lógica para atender al paciente según las reglas especificadas

        if (preferenciales.esVacia() && regulares.esVacia()) {
            JOptionPane.showMessageDialog(null, "No hay pacientes en espera.");
        }else{
            Paciente paciente = null;

            //Valida que se hayan atendido menos de 2 preferenciales y que hayan pacientes en preferenciales
            if (getPacientesPAtendidos() < 2 && !preferenciales.esVacia()) {
                paciente = atenderPacienteEnTurno(preferenciales);
                incrementarPacientesPAtendidos();
            } else {
                if (!regulares.esVacia()) {
                    paciente = atenderPacienteEnTurno(regulares);
                    reestablecerPacientesPAtendidos();
                }
            }
            return paciente;
        }
        return null;
    }

    private static Paciente atenderPacienteEnTurno(Cola cola) {
        NodoCola pacienteEnTurno = cola.salirDeCola();
        Paciente paciente = pacienteEnTurno.getPaciente();
        mostrarInfoPaciente(paciente);
        return paciente;
        /*JOptionPane.showMessageDialog(null, "Ficha #" + paciente.getFicha()
                + " con cédula " + paciente.getCedula() + " pasar a consulta médica.");*/
    }

    private static void mostrarFichasPendientes(Cola preferenciales, Cola regulares) {
        // Mostrar en verde las fichas de tipo Paciente Regular
        System.out.println("Fichas Pendientes de Pacientes Regulares (verde):");
        mostrarFichasCola(regulares, Color.GREEN);

        // Mostrar en naranja las fichas de tipo Paciente Preferencial
        System.out.println("Fichas Pendientes de Pacientes Preferenciales (naranja):");
        mostrarFichasCola(preferenciales, Color.ORANGE);
    }

    private static void mostrarFichasCola(Cola cola, Color color) {
        Cola tempCola = new Cola();
        while (!cola.esVacia()) {
            NodoCola pacienteEnTurno = cola.salirDeCola();
            Paciente paciente = pacienteEnTurno.getPaciente();
            String mensaje = "Ficha #" + paciente.getFicha()
                    + " con cédula " + paciente.getCedula() + " pendiente de atención.";

            // Imprimir mensaje en consola
            System.out.println(mensaje);

            // Mostrar mensaje en JOptionPane con color especificado
            mostrarMensajeJOptionPane(mensaje, color);

            tempCola.encola(pacienteEnTurno);
        }

        // Restaurar la cola original
        while (!tempCola.esVacia()) {
            cola.encola(tempCola.salirDeCola());
        }
    }

    private static void mostrarMensajeJOptionPane(String mensaje, Color color) {
        JOptionPane.showMessageDialog(null, mensaje, "Ficha Pendiente", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void mostrarQuejasRecibidas() {
        JOptionPane.showMessageDialog(null, "Quejas Recibidas:\n\n" + quejasPila.toString());
    }
    
    private static void mostrarInfoPaciente(Paciente paciente){
        JOptionPane.showMessageDialog(null, "Información del paciente \n Nombre: "+paciente.getNombreCompleto()+"\n Cedula: "+paciente.getCedula()+"\n Tipo de Paciente: "+paciente.getTipo()+"\n Ficha: "+paciente.getFicha());
    }

}
