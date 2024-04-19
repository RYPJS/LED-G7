package eeddg10.susalud;

import javax.swing.JOptionPane;

/**
 * @version 1.0.1
 * @Kendall
 * @Robert
 * @Joshua La clase Main es la clase principal del programa.
 * Contiene el método main que se ejecuta al iniciar el programa.
 */
public class Main {
    /**
     * Número de pacientes atendidos.
     */
    private static int pacientesPAtendidos = 0;

    /**
     * @return El número de pacientes atendidos.
     */
    public static int getPacientesPAtendidos() {
        return pacientesPAtendidos;
    }

    /**
     * Incrementa el número de pacientes atendidos.
     */
    public static void incrementarPacientesPAtendidos() {
        pacientesPAtendidos++;
    }

    /**
     * Restablece el número de pacientes atendidos a cero.
     */
    public static void reestablecerPacientesPAtendidos() {
        pacientesPAtendidos = 0;
    }

    /**
     * Método principal del programa.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Bienvenido al sistema");

        Cola preferenciales = new Cola();
        Cola regulares = new Cola();

        int mainOption = -1; // Inicializamos con un valor diferente de 0 para que entre al bucle

        AtenderPacientes atenderPacientes = new AtenderPacientes();
        ExpedienteUnico expedienteUnico = new ExpedienteUnico();
        BitacoraLista bitacoraLista = new BitacoraLista();
        ConsultaBitacoraCitas consultaBitacora = new ConsultaBitacoraCitas(bitacoraLista);
        ChatBot chatBot = new ChatBot();

        while (mainOption != 0) {
            try {
                String input = JOptionPane.showInputDialog(
                        "Seleccione una opción:\n" +
                                "1. Gestionar Llegada de Pacientes\n" +
                                "2. Consulta de Bitácora de Citas del día\n" +
                                "3. Consulta de Expediente único de Pacientes\n" +
                                "4. Chat bot\n" +
                                "5. Ayuda\n" +
                                "6. Salir"
                );

                if (input == null) {
                    mainOption = 0; // Si se presiona Cancelar, salir del bucle
                } else {
                    mainOption = Integer.parseInt(input);
                }

                switch (mainOption) {
                    case 1:
                        atenderPacientes.gestionarLlegadaPacientes(preferenciales, regulares, expedienteUnico, bitacoraLista);
                        break;
                    case 2:
                        consultaBitacora.consultarBitacoraCitas();
                        break;
                    case 3:
                        expedienteUnico.consultarExpedientes(); // Aquí se agrega la opción de consulta de expedientes
                        break;
                    case 4:
                        chatBot.subMenu();
                        break;
                    case 5:
                        //Mostrar mensaje de ayuda
                        JOptionPane.showMessageDialog(null, "Avance 1 V 1.0.1");
                        break;
                    case 6:
                        mainOption = 0;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, elige una opción disponible.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
            }
        }
    }
}