package eeddg10.susalud;

import java.util.Date;
import javax.swing.JOptionPane;

public class BitacoraLista {
    private NodoLS cabeza;
    private NodoLS ultimo;

    public BitacoraLista(){
        this.cabeza = null;
        this.ultimo = null;
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
            ultimo = cabeza;
            ultimo.setSiguiente(cabeza);
        }
        else
        {
            if(bitacora.getCedula() < cabeza.getBitacora().getCedula())
            {
                //Insertamos al inicio
                NodoLS auxiliar = new NodoLS(bitacora);
                auxiliar.setSiguiente(cabeza);
                cabeza = auxiliar;
                ultimo.setSiguiente(cabeza);
            }
            else
            {
                if(ultimo.getBitacora().getCedula() < bitacora.getCedula())
                {
                    //Insertamos al final
                    NodoLS auxiliar = new NodoLS(bitacora);
                    ultimo.setSiguiente(auxiliar);
                    ultimo = auxiliar;
                    ultimo.setSiguiente(cabeza);
                }
                else
                {
                    //Insertamos en el medio de la lista
                    NodoLS auxiliar = cabeza;
                    while(auxiliar.getSiguiente().getBitacora().getCedula() < bitacora.getCedula())
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
        String respuesta = "Lista circular: \n";
        
        NodoLS auxiliar = cabeza;
        
        if(auxiliar != null)
        {
            //Para determinar que no está vacía
            respuesta += auxiliar.toString() + "\n";
            auxiliar = auxiliar.getSiguiente();
            while(auxiliar != cabeza)
            {
                respuesta += auxiliar.toString() + "\n";
                auxiliar = auxiliar.getSiguiente();
            }
        }
        else
        {
            respuesta += "Vacía";
        }
        
        return respuesta;
    }
}