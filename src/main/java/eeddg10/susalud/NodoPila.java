/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eeddg10.susalud;

/**
 *
 * @author kenda
 */
public class NodoPila {
    private String queja;
    private NodoPila siguiente;

    public NodoPila(String queja) {
        this.queja = queja;
    }

    public String getQueja() {
        return queja;
    }

    public NodoPila getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPila siguiente) {
        this.siguiente = siguiente;
    }
    
}