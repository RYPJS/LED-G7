package eeddg10.susalud;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

class Arbol {
    private NodoA raiz;
    
    public Arbol() {
        // Creamos la raíz con el código 1 y nombre "Preguntas Frecuentes"
        this.raiz = new NodoA("1", "Preguntas Frecuentes", null);
    }
    
    // Método para navegar por el árbol de preguntas
    public void navegarArbol() {
        NodoA nodoActual = raiz;
        boolean mostrandoRespuesta = false;

        while (nodoActual != null && !mostrandoRespuesta) {
            // Mientras no se esté mostrando una respuesta y haya hijos, mostrar opciones y permitir al usuario seleccionar
            while (!mostrandoRespuesta && (nodoActual.getEnlaceIzq() != null || nodoActual.getEnlaceDer() != null)) {
                String opciones = generarOpciones(nodoActual);
                int opcionElegida;
                try {
                    String input = JOptionPane.showInputDialog(opciones);
                    if (input == null) {
                        // Si el usuario presiona "Cancelar", salimos del chatbot
                        return;
                    }
                    opcionElegida = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa un número válido.");
                    continue; // Volver al inicio del bucle mientras si ocurre una excepción
                }
                if (opcionElegida == 0) {
                    // Regresar al nivel anterior solo si no se está mostrando una respuesta
                    if (nodoActual.getEnlacePadre() != null) {
                        nodoActual = nodoActual.getEnlacePadre();
                    } else {
                        mostrandoRespuesta = true; // Salir del bucle si estamos en la raíz y no se está mostrando una respuesta
                    }
                } else {
                    if (opcionElegida == 1 || opcionElegida == 2) {
                        NodoA siguienteNodo = opcionElegida == 1 ? nodoActual.getEnlaceIzq() : nodoActual.getEnlaceDer();
                        if (siguienteNodo != null) {
                            nodoActual = siguienteNodo;
                        } else {
                            JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, elige una opción disponible.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, elige una opción disponible.");
                    }
                }
            }

            // Si es una hoja, mostrar preguntas disponibles o cerrar el chatbot si no hay preguntas
            if (!mostrandoRespuesta) {
                if (nodoActual.getListaPreguntas() == null) {
                    return; // Salir del método si no hay preguntas disponibles
                } else {
                    mostrandoRespuesta = mostrarPreguntas(nodoActual); // Actualizar mostrandoRespuesta con el resultado de mostrarPreguntas
                }
            }
        }
    }

    // Método para generar las opciones disponibles en cada nivel del árbol
    private String generarOpciones(NodoA nodo) {
        StringBuilder opciones = new StringBuilder();
        opciones.append("Opciones disponibles:\n");
        if (nodo.getEnlacePadre() != null) {
            opciones.append("0. Regresar al nivel anterior\n");
        } else {
            opciones.append("0. Salir del ChatBot\n");
        }
        if (nodo.getEnlaceIzq() != null) {
            opciones.append("1. ").append(nodo.getEnlaceIzq().getNombre()).append("\n");
        }
        if (nodo.getEnlaceDer() != null) {
            opciones.append("2. ").append(nodo.getEnlaceDer().getNombre()).append("\n");
        }
        return opciones.toString();
    }

    // Método para mostrar las preguntas disponibles en un nodo hoja
    private boolean mostrarPreguntas(NodoA nodo) {
        StringBuilder opciones = new StringBuilder();
        opciones.append("Preguntas disponibles:\n");
        ListaPreguntas preguntas = nodo.getListaPreguntas();

        NodoPregunta actual = preguntas.getCabeza();
        Integer i = 0;
        while (actual != null) {
            opciones.append(i + 1).append(". ").append(actual.getPregunta().getNombre()).append("\n");
            actual = actual.getSiguiente();
            i++;
        }

        int numeroPreguntas = contarPreguntas(preguntas); // Contar el número de preguntas en la lista enlazada
        int opcionElegida = Integer.parseInt(JOptionPane.showInputDialog(opciones));
        if (opcionElegida == 0) {
            // Regresar al nivel anterior
            return false;
        } else if (opcionElegida > 0 && opcionElegida <= numeroPreguntas) { // Comparar con el número de preguntas
            // Mostrar la respuesta correspondiente a la pregunta seleccionada
            NodoPregunta preguntaSeleccionada = obtenerPreguntaEnPosicion(preguntas, opcionElegida);
            JOptionPane.showMessageDialog(null, preguntaSeleccionada.getPregunta().getRespuesta());
            return true; // Cerrar el chatbot después de mostrar la respuesta
        }
        JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, elige una opción disponible.");
        return false; // Devolver false si no se elige regresar o mostrar la respuesta
    }

    // Método para contar el número de preguntas en la lista enlazada
    private int contarPreguntas(ListaPreguntas preguntas) {
        int count = 0;
        NodoPregunta actual = preguntas.getCabeza();
        while (actual != null) {
            count++;
            actual = actual.getSiguiente();
        }
        return count;
    }

    // Método para obtener la pregunta en una posición específica de la lista enlazada
    private NodoPregunta obtenerPreguntaEnPosicion(ListaPreguntas preguntas, int posicion) {
        NodoPregunta actual = preguntas.getCabeza();
        for (int i = 1; i < posicion; i++) {
            actual = actual.getSiguiente();
        }
        return actual;
    }
   
    public void agregarNodo(String codigoPadre, String nombreNuevo, ListaPreguntas listaPreguntasNuevo) {
        // Buscar el nodo padre en el árbol
        NodoA padre = buscarNodo(raiz, codigoPadre);
        if (padre == null) {
            JOptionPane.showMessageDialog(null, "El nodo padre no existe.");
            return;
        }

        // Generar el código para el nuevo nodo
        String codigoNuevo = generarCodigo(padre);

        // Crear el nuevo nodo
        NodoA nuevoNodo = new NodoA(codigoNuevo, nombreNuevo, listaPreguntasNuevo);
        // Establecer el padre del nuevo nodo
        nuevoNodo.setEnlacePadre(padre);

        // Obtener la lista de nodos hijos del padre
        ListaNodosHijos nodosHijos = padre.getNodosHijos();
        if (nodosHijos == null) {
            nodosHijos = new ListaNodosHijos(); // Creamos una nueva instancia de ListaNodosHijos
            padre.setNodosHijos(nodosHijos);
        }

        // Insertar el nuevo nodo en la lista de nodos hijos
        nodosHijos.insertarAlPrincipio(nuevoNodo);
        
        if (padre.getEnlaceIzq() == null) {
            padre.setEnlaceIzq(nuevoNodo);
        } else if (padre.getEnlaceDer() == null) {
            padre.setEnlaceDer(nuevoNodo);
        } else {
            JOptionPane.showMessageDialog(null, "El nodo padre ya tiene dos hijos.");
            return;
        }
    }

    public String generarCodigo(NodoA nodoPadre) {
        String codigoPadre = nodoPadre.getCodigo();
        int numHijos = contarNodosHijos(nodoPadre) + 1;
        return codigoPadre + numHijos;
    }

    private int contarNodosHijos(NodoA nodoPadre) {
        int count = 0;
        ListaNodosHijos nodosHijos = nodoPadre.getNodosHijos();
        if (nodosHijos != null) {
            NodoHijo actual = nodosHijos.getCabeza();
            while (actual != null) {
                count++;
                actual = actual.getSiguiente();
            }
        }
        return count;
    }
    
    private NodoA buscarNodo(NodoA nodoActual, String codigo) {
        if (nodoActual == null) {
            return null;
        }

        if (nodoActual.getCodigo().equals(codigo)) {
            return nodoActual;
        }

        // Buscar en el subárbol izquierdo
        NodoA nodoIzq = buscarNodo(nodoActual.getEnlaceIzq(), codigo);
        if (nodoIzq != null) {
            return nodoIzq;
        }

        // Buscar en el subárbol derecho
        return buscarNodo(nodoActual.getEnlaceDer(), codigo);
    }
    
    public void insertarModificarPreguntaPadre() {
        String codigoPadre = JOptionPane.showInputDialog("Ingrese el código del nodo padre:");
        NodoA padre = buscarNodo(raiz, codigoPadre);

        if (padre == null) {
            JOptionPane.showMessageDialog(null, "El nodo padre no existe.");
            return;
        }

        String nombreNuevo = JOptionPane.showInputDialog("Ingrese el nombre de la nueva pregunta padre:");
        ListaPreguntas listaPreguntasNuevo = new ListaPreguntas();

        agregarNodo(codigoPadre, nombreNuevo, listaPreguntasNuevo);
    }
    
    public void insertarModificarPreguntaHija() {
        String codigoPadre = JOptionPane.showInputDialog("Ingrese el código del nodo padre al que desea agregar la pregunta hija:");
        NodoA padre = buscarNodo(raiz, codigoPadre);

        if (padre == null) {
            JOptionPane.showMessageDialog(null, "El nodo padre no existe.");
            return;
        }

        if (padre.getListaPreguntas() != null) {
            JOptionPane.showMessageDialog(null, "No se pueden agregar preguntas hijas a un nodo que no es hoja.");
            return;
        }

        String nombreNuevaPregunta = JOptionPane.showInputDialog("Ingrese el nombre de la nueva pregunta hija:");
        String respuestaNuevaPregunta = JOptionPane.showInputDialog("Ingrese la respuesta de la nueva pregunta hija:");

        Pregunta preguntaNueva = new Pregunta(nombreNuevaPregunta, respuestaNuevaPregunta);
        // Insertar la pregunta en la lista de preguntas del nodo padre
        padre.getListaPreguntas().insertarAlPrincipio(preguntaNueva);
    }
    
    public void imprimirPreguntasPadre() {
        String codigoPadre = JOptionPane.showInputDialog("Ingrese el código del nodo padre del que desea imprimir las preguntas:");
        NodoA padre = buscarNodo(raiz, codigoPadre);

        if (padre == null) {
            JOptionPane.showMessageDialog(null, "El nodo padre no existe.");
            return;
        }

        if (padre.getListaPreguntas() == null) {
            JOptionPane.showMessageDialog(null, "El nodo padre no tiene preguntas asociadas.");
            return;
        }

        ListaPreguntas preguntasPadre = padre.getListaPreguntas();
        StringBuilder mensaje = new StringBuilder("Preguntas del nodo padre:\n");
        NodoPregunta actual = preguntasPadre.getCabeza();
        while (actual != null) {
            mensaje.append(actual.getPregunta().getNombre()).append("\n");
            actual = actual.getSiguiente();
        }
        JOptionPane.showMessageDialog(null, mensaje.toString());
    }
}

class NodoA {
    private String codigo;
    private String nombre;
    private ListaPreguntas listaPreguntas;
    private ListaNodosHijos nodosHijos;
    private NodoA enlaceIzq;
    private NodoA enlaceDer;
    private NodoA enlacePadre;
    
    public NodoA(String codigo, String nombre, ListaPreguntas listaPreguntas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.listaPreguntas = listaPreguntas;
        this.nodosHijos = new ListaNodosHijos();
        this.enlaceIzq = null;
        this.enlaceDer = null;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaPreguntas getListaPreguntas() {
        return listaPreguntas;
    }

    public void setListaPreguntas(ListaPreguntas listaPreguntas) {
        this.listaPreguntas = listaPreguntas;
    }

    public NodoA getEnlaceIzq() {
        return enlaceIzq;
    }

    public void setEnlaceIzq(NodoA enlaceIzq) {
        this.enlaceIzq = enlaceIzq;
    }

    public NodoA getEnlaceDer() {
        return enlaceDer;
    }

    public void setEnlaceDer(NodoA enlaceDer) {
        this.enlaceDer = enlaceDer;
    }
    
    public NodoA getEnlacePadre() {
        return enlacePadre;
    }

    public void setEnlacePadre(NodoA enlacePadre) {
        this.enlacePadre = enlacePadre;
    }
    
    public ListaNodosHijos getNodosHijos() {
        return nodosHijos;
    }

    public void setNodosHijos(ListaNodosHijos nodosHijos) {
        this.nodosHijos = nodosHijos;
    }
}