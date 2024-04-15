package eeddg10.susalud;

import javax.swing.JOptionPane;

/**
 * La clase ExpedienteUnico representa una lista circular doblemente enlazada de expedientes médicos.
 * Cada objeto ExpedienteUnico contiene un nodo que representa la cabeza y el último elemento de la lista.
 */
public class ExpedienteUnico {
    private NodoDC cabeza;
    private NodoDC ultimo;

    /**
     * Este método inserta un paciente en la lista.
     * Si el expediente del paciente ya existe, se muestra la información del expediente y se agrega una cita y un medicamento al historial del paciente.
     * Si el expediente del paciente no existe, se crea un nuevo expediente y se inserta en la lista en la posición correcta según la cédula del paciente.
     */
    public void insertaPaciente() {
        NodoDC nuevo = new NodoDC();
        Expediente expediente = solicitarInformacion();
        nuevo.setExpediente(expediente);
        Expediente expedienteExistente = validarExpedienteExiste(expediente.getCedula());
        if (expedienteExistente != null) {
            JOptionPane.showMessageDialog(null, "Expediente del cliente\n" + "Nombre: " + expedienteExistente.getNombreCompleto() + "\nCedula: " + expedienteExistente.getCedula() + "\nEdad: " + expedienteExistente.getEdad() + "\nGenero: " + expedienteExistente.getGenero());
            expedienteExistente.getHistoricoCitas().agregarCita();
            expedienteExistente.getHistoricoMedicamentos().agregarMedicamento();
        } else {
            if (cabeza == null) {
                cabeza = new NodoDC(expediente);
                ultimo = cabeza;
                cabeza.setAnterior(ultimo);
                cabeza.setSiguiente(ultimo);
                ultimo.setSiguiente(cabeza);
                ultimo.setAnterior(cabeza);
            } else {
                if (cabeza.getExpediente().getCedula() > expediente.getCedula()) {
                    NodoDC aux = new NodoDC(expediente);
                    aux.setSiguiente(cabeza);
                    cabeza.setAnterior(aux);
                    cabeza = aux;
                    cabeza.setAnterior(ultimo);
                    ultimo.setSiguiente(cabeza);
                } else {
                    if (expediente.getCedula() > ultimo.getExpediente().getCedula()) {
                        NodoDC aux = new NodoDC(expediente);
                        aux.setAnterior(ultimo);
                        ultimo.setSiguiente(aux);
                        ultimo = aux;
                        ultimo.setSiguiente(cabeza);
                        cabeza.setAnterior(ultimo);
                    } else {
                        NodoDC aux = cabeza.getSiguiente();
                        while (aux.getExpediente().getCedula() < expediente.getCedula()) {
                            aux = aux.getSiguiente();
                        }

                        NodoDC temp = new NodoDC(expediente);
                        temp.setAnterior(aux.getAnterior());
                        temp.setSiguiente(aux);
                        aux.setAnterior(temp);
                        temp.getAnterior().setSiguiente(temp);
                    }
                }
            }

            JOptionPane.showMessageDialog(null, "Paciente " + expediente.getNombreCompleto() + " asiste a consulta por primera vez");
            expediente.getHistoricoCitas().agregarCita();
            expediente.getHistoricoMedicamentos().agregarMedicamento();
        }
    }

    /**
     * @return Una representación en cadena de la lista de expedientes.
     */
    @Override
    public String toString() {
        String respuesta = "Lista doble circular: \n";

        if (cabeza != null) {
            NodoDC aux = cabeza;

            respuesta += aux.toString() + "\n";

            aux = aux.getSiguiente();

            while (aux != cabeza) {
                respuesta += aux.toString() + "\n";
                aux = aux.getSiguiente();
            }
        } else {
            respuesta += "Vacía";
        }

        return respuesta;
    }

    /**
     * Este método solicita la información del paciente y crea un nuevo expediente.
     *
     * @return El expediente creado.
     */
    public Expediente solicitarInformacion() {
        Boolean error = false;
        Expediente expediente = new Expediente();
        expediente.setNombreCompleto(JOptionPane.showInputDialog("Ingrese el nombre completo del paciente: "));
        do {
            try {
                expediente.setCedula(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cedula del paciente: ")));
                error = false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor numerico");
                error = true;
            }
        } while (error == true);

        do {
            try {
                expediente.setEdad(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del paciente: ")));
                error = false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor numerico");
                error = true;
            }
        } while (error == true);
        expediente.setGenero(JOptionPane.showInputDialog("Ingrese el genero del paciente: "));
        return expediente;
    }

    /**
     * Este método verifica si un expediente existe en la lista según la cédula del paciente.
     *
     * @param cedula La cédula del paciente.
     * @return El expediente si existe, null en caso contrario.
     */
    public Expediente validarExpedienteExiste(Integer cedula) {
        if (cabeza != null) {
            NodoDC aux = cabeza;
            aux = aux.getSiguiente();

            if (aux.getExpediente().getCedula() == cedula) {
                return aux.getExpediente();
            }

            while (aux != cabeza) {
                if (aux.getExpediente().getCedula() == cedula) {
                    return aux.getExpediente();
                }
                aux = aux.getSiguiente();
            }
        }

        return null; // Elemento no encontrado
    }

    /**
     * Este método consulta los expedientes en la lista.
     * Muestra la información de cada expediente y permite al usuario navegar a través de los expedientes.
     */
    public void consultarExpedientes() {
        NodoDC auxiliar = cabeza;

        if (auxiliar == null) {
            JOptionPane.showMessageDialog(null, "No hay expedientes registrados.");
            return;
        }

        do {
            mostrarExpediente(auxiliar.getExpediente());

            String opcion = JOptionPane.showInputDialog("Seleccione una opción:\n"
                    + "1. Siguiente expediente\n"
                    + "2. Anterior expediente\n"
                    + "3. Salir de la consulta");

            switch (opcion) {
                case "1":
                    auxiliar = auxiliar.getSiguiente();
                    break;
                case "2":
                    auxiliar = auxiliar.getAnterior();
                    break;
                case "3":
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, seleccione una opción válida.");
            }
        } while (true);
    }

    /**
     * Este método muestra la información de un expediente.
     *
     * @param expediente El expediente a mostrar.
     */
    private void mostrarExpediente(Expediente expediente) {
        StringBuilder infoExpediente = new StringBuilder();
        infoExpediente.append("Información del paciente:\n")
                .append("Nombre: ").append(expediente.getNombreCompleto()).append("\n")
                .append("Cedula: ").append(expediente.getCedula()).append("\n")
                .append("Edad: ").append(expediente.getEdad()).append("\n")
                .append("Genero: ").append(expediente.getGenero()).append("\n")
                .append("\nHistorico de citas:\n")
                .append(expediente.getHistoricoCitas().toString()).append("\n")
                .append("\nHistorico de medicamentos:\n")
                .append(expediente.getHistoricoMedicamentos().toString());

        JOptionPane.showMessageDialog(null, infoExpediente.toString());
    }

}