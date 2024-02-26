package eeddg10.susalud;
import javax.swing.JOptionPane;

public class Main {
    
    private static int pacientesPAtendidos = 0;

    public static int getPacientesPAtendidos() {
        return pacientesPAtendidos;
    }

    public static void incrementarPacientesPAtendidos() {
        pacientesPAtendidos++;
    }
    
    public static void reestablecerPacientesPAtendidos() {
        pacientesPAtendidos = 0;
    }

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Bienvenido al sistema");
        
        Cola preferenciales = new Cola();
        Cola regulares = new Cola();
        
        int mainOption = -1; // Inicializamos con un valor diferente de 0 para que entre al bucle
        
        AtenderPacientes atenderPacientes = new AtenderPacientes();

        while (mainOption != 0) {
            try {
                String input = JOptionPane.showInputDialog(
                        "Seleccione una opción:\n" +
                        "1. Gestionar Llegada de Pacientes\n" +
                        "2. Ayuda\n" +
                        "3. Salir"
                );
 
                if (input == null) {
                    mainOption = 0; // Si se presiona Cancelar, salir del bucle
                } else {
                    mainOption = Integer.parseInt(input);
                }
                
                switch(mainOption){
                    case 1:
                        atenderPacientes.gestionarLlegadaPacientes(preferenciales, regulares);
                        break;
                    case 2:
                        //Mostrar mensaje de ayuda
                        JOptionPane.showMessageDialog(null, "Avance 1 V 1.0.1");
                        break;
                    case 3:
                        mainOption = 0;
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
            }
        }
    }
}