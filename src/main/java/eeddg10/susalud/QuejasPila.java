/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eeddg10.susalud;

/**
 *
 * @author kenda
 */
public class QuejasPila {

    private NodoPila tope;


    public String sacarQueja() {
        if (esVacia()) {
            return null;
        }

        String queja = tope.getQueja();
        tope = tope.getSiguiente();
        return queja;
    }

    public boolean esVacia() {
        return tope == null;
    }

    public void agregarQuejas(String queja) {
        NodoPila nuevoNodo = new NodoPila(queja);
        nuevoNodo.setSiguiente(tope);
        tope = nuevoNodo;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        NodoPila current = tope;

        while (current != null) {
            result.append(current.getQueja()).append("\n");
            current = current.getSiguiente();
        }

        return result.toString();
    }
    
}
