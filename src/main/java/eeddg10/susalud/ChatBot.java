package eeddg10.susalud;

import javax.swing.JOptionPane;

public class ChatBot {
    
    Arbol arbol = new Arbol();
    
    public ChatBot() {
        arbol.agregarNodo("1", "Emergencias", null);
        arbol.agregarNodo("1", "Consulta Externa", null);
        
        arbol.agregarNodo("11", "Emergencias Menores", null);
        ListaPreguntas preguntas = new ListaPreguntas();
        preguntas.insertarAlPrincipio(new Pregunta("Horario", "Atendemos 24 horas del dias"));
        preguntas.insertarAlPrincipio(new Pregunta("Sucursales", "Tenemos sucursales en \n" +
                                                    "todo el país, ingrese a \n" +
                                                    "www.susalud.com/\n" +
                                                    "sucursales para mayor \n" +
                                                    "información"));
        preguntas.insertarAlPrincipio(new Pregunta("Aplicar a una plaza", "Ingrese a \n" +
                                        "www.susalud.com\n" +
                                        "/reclutamiento"));
        arbol.agregarNodo("11", "Emergencias Mayores", preguntas);
        arbol.agregarNodo("12", "Servicios", null);
        
        preguntas = new ListaPreguntas();
        preguntas.insertarAlPrincipio(new Pregunta("¿Cuál es el costo de consulta para adultos?", "El costo de atención en emergencias para un adulto es de 20,000 colones"));
        preguntas.insertarAlPrincipio(new Pregunta("¿Cuál es el tiempo promedio de espera?", "Por tratarse de un servicio de emergencia no podemos garantizar\n" +
                                        "un tiempo mínimo para atenciones menores. Sin embargo, en promedio demoramos 30 minutos."));
        
        arbol.agregarNodo("111", "Mayores de 18 años", preguntas);
        preguntas = new ListaPreguntas();
        preguntas.insertarAlPrincipio(new Pregunta("¿Qué especialidad atiende a los niños?", "Contamos con pediatra las 24 horas del día."));
        preguntas.insertarAlPrincipio(new Pregunta("¿Se puede aplicar el seguro estudiantil?", "Si, recibimos todos los seguros médicos del mercado."));
        arbol.agregarNodo("111", "Menores de edad", null);
        arbol.agregarNodo("121", "Laboratorio", null);
        arbol.agregarNodo("121", "Farmacia", null);
    }
    
    public void subMenu() {
        int subOption = -1;

        while (subOption != 3) {
            try {
                String input = JOptionPane.showInputDialog(
                        "Seleccione una opción para ChatBot:\n"
                                + "1. Ver ChatBot\n"
                                + "2. Mantenimiento ChatBot\n"
                                + "3. Regresar"
                );

                if (input == null) {
                    subOption = 3; // Si se presiona Cancelar, salir del bucle
                } else {
                    subOption = Integer.parseInt(input);

                    switch (subOption) {
                        case 1:
                            verChatBot();
                            break;
                        case 2:
                            menuMantenimiento();
                            break;
                        case 3:
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, elige una opción disponible.");
                            break;
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
            }
        }
    }
    
    public void verChatBot() {
        JOptionPane.showMessageDialog(null, "¡Bienvenido al ChatBot de Preguntas Frecuentes!");
        // Crear y mostrar el árbol de preguntas
        arbol.navegarArbol();
        
        JOptionPane.showMessageDialog(null, "¡Gracias por usar nuestro ChatBot! ¡Hasta luego!");
    }
    
    public void menuMantenimiento() {
        int option = -1;

        while (option != 4) {
            try {
                String input = JOptionPane.showInputDialog(
                        "Seleccione una opción para Mantenimiento del ChatBot:\n"
                                + "1. Insertar/Modificar pregunta padre\n"
                                + "2. Insertar/Modificar pregunta hija\n"
                                + "3. Imprimir preguntas de una pregunta padre\n"
                                + "4. Regresar"
                );

                if (input == null) {
                    option = 4; // Si se presiona Cancelar, salir del bucle
                } else {
                    option = Integer.parseInt(input);

                    switch (option) {
                        case 1:
                            arbol.insertarModificarPreguntaPadre();
                            break;
                        case 2:
                            arbol.insertarModificarPreguntaHija();
                            break;
                        case 3:
                            arbol.imprimirPreguntasPadre();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, elige una opción disponible.");
                            break;
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
            }
        }
    }
}