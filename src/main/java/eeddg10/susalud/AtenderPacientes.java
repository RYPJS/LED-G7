package eeddg10.susalud;

import static eeddg10.susalud.Main.getPacientesPAtendidos;
import static eeddg10.susalud.Main.incrementarPacientesPAtendidos;
import static eeddg10.susalud.Main.reestablecerPacientesPAtendidos;
import javax.swing.JOptionPane;

public class AtenderPacientes {
    
    public static void gestionarLlegadaPacientes(Cola preferenciales, Cola regulares) {
        int subOption = -1;

        while (subOption != 6) {
            try {
                String input = JOptionPane.showInputDialog(
                        "Seleccione una opción para Gestionar Llegada de Pacientes:\n" +
                        "1. Seleccionar Ficha\n" +
                        "2. Atender Paciente\n" +
                        "3. Abandonar Cola de Pacientes\n" +
                        "4. Mostrar Fichas Pendientes\n" +
                        "5. Mostrar Quejas recibidas\n" +
                        "6. Regresar"
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
                            atenderPaciente(preferenciales, regulares);
                            // Lógica para la opción 2
                            break;
                        case 3:
                            // Lógica para la opción 3
                            break;
                        case 4:
                            // Lógica para la opción 4
                            break;
                        case 5:
                            // Lógica para la opción 5
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
            if(!regulares.esVacia()){
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
            if(!preferenciales.esVacia()){
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
    
    private static void agregarPaciente(Cola cola, String tipo, String ficha) {
        Paciente paciente = new Paciente();
        paciente.setFicha(ficha);
        paciente.setCedula(JOptionPane.showInputDialog("Ingrese el número de cédula del paciente: "));
        paciente.setNombreCompleto(JOptionPane.showInputDialog("Ingrese el nombre del paciente: "));
        paciente.setTipo(tipo);
        cola.encola(new NodoCola(paciente));
        JOptionPane.showMessageDialog(null, "Su número de ficha es: " + ficha);
    }
    
    private static void atenderPaciente(Cola preferenciales, Cola regulares) {
        // Lógica para atender al paciente según las reglas especificadas
        
        if(preferenciales.esVacia() && regulares.esVacia()){
            JOptionPane.showMessageDialog(null, "No hay pacientes en espera.");
        }

        //Valida que se hayan atendido menos de 2 preferenciales y que hayan pacientes en preferenciales
        if (getPacientesPAtendidos() < 2 && !preferenciales.esVacia()) {
            atenderPacienteEnTurno(preferenciales);
            incrementarPacientesPAtendidos();
        }else{
            if (!regulares.esVacia()) {
                atenderPacienteEnTurno(regulares);
                reestablecerPacientesPAtendidos();
            }
        }
    }

    private static void atenderPacienteEnTurno(Cola cola) {
        NodoCola pacienteEnTurno = cola.salirDeCola();
        Paciente paciente = pacienteEnTurno.getPaciente();
        JOptionPane.showMessageDialog(null, "Ficha #" + paciente.getFicha() +
                " con cédula " + paciente.getCedula() + " pasar a consulta médica.");
    }
}
