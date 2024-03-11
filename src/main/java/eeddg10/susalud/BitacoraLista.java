package eeddg10.susalud;

import java.util.Date;
import javax.swing.JOptionPane;

public class BitacoraLista {
    private NodoLS cabeza;

    public BitacoraLista(){
        this.cabeza = null;
    }
    
    public boolean esVacia(){
        if(cabeza == null){
           return true;
        }else{
           return false;
        }
    }
    
    public void insertarBitacora(Paciente paciente)
    {
        Bitacora bitacora = new Bitacora();
        bitacora.setFicha(paciente.getFicha());
        bitacora.setFechaAtencion(new Date());
        bitacora.setCedula(paciente.getCedula());
        bitacora.setNombre(paciente.getNombreCompleto());
        bitacora.setFechaAtencion(paciente.getIngreso());
        
        if(cabeza == null)
        {
            cabeza = new NodoLS(bitacora);
        }
        else
        {
            if(bitacora.getCedula() < cabeza.getBitacora().getCedula())
            {
                //Insertamos al inicio
                NodoLS auxiliar = new NodoLS(bitacora);
                auxiliar.setSiguiente(cabeza);
                cabeza = auxiliar;
            }
            else
            {
                if(cabeza.getSiguiente() == null)
                {
                    //Insertamos al final
                    NodoLS auxiliar = new NodoLS(bitacora);
                    cabeza.setSiguiente(auxiliar);
                }
                else
                {
                    //Insertamos en el medio de la lista
                    NodoLS auxiliar = cabeza;
                    while(auxiliar.getSiguiente() != null && 
                          auxiliar.getSiguiente().getBitacora().getCedula() < bitacora.getCedula())
                    {
                        auxiliar = auxiliar.getSiguiente();
                    }
                    
                    NodoLS temporal = new NodoLS(bitacora);
                    temporal.setSiguiente(auxiliar.getSiguiente());
                    auxiliar.setSiguiente(temporal);
                }
            }
        }
    }
    
    @Override
    public String toString() {
        NodoLS auxiliar = cabeza;
        String respuesta = "Lista: \n";
        
        while(auxiliar != null)
        {
            respuesta += auxiliar.toString() + "\n";
            auxiliar = auxiliar.getSiguiente();
        }
        
        return respuesta;
    }
}