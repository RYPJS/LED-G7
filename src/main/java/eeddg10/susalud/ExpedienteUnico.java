package eeddg10.susalud;

import javax.swing.JOptionPane;

public class ExpedienteUnico {
    private NodoDC cabeza;
    private NodoDC ultimo;

    public void insertaPaciente()
    {
        NodoDC nuevo = new NodoDC();
        Expediente expediente = solicitarInformacion();
        nuevo.setExpediente(expediente);
        Expediente expedienteExistente = validarExpedienteExiste(expediente.getCedula());
        if(expedienteExistente != null){
            JOptionPane.showMessageDialog(null, "Expediente del cliente\n"+"Nombre: "+expedienteExistente.getNombreCompleto()+"\nCedula: "+expedienteExistente.getCedula()+"\nEdad: "+expedienteExistente.getEdad()+"\nGenero: "+expedienteExistente.getGenero());
            expedienteExistente.getHistoricoCitas().agregarCita();
            expedienteExistente.getHistoricoMedicamentos().agregarMedicamento();
        }else{
            if(cabeza == null)
            {
                cabeza = new NodoDC(expediente);
                ultimo = cabeza;
                cabeza.setAnterior(ultimo);
                cabeza.setSiguiente(ultimo);
                ultimo.setSiguiente(cabeza);
                ultimo.setAnterior(cabeza);
            }
            else
            {
                if(cabeza.getExpediente().getCedula()> expediente.getCedula())
                {
                    NodoDC aux = new NodoDC(expediente);
                    aux.setSiguiente(cabeza);
                    cabeza.setAnterior(aux);
                    cabeza = aux;
                    cabeza.setAnterior(ultimo);
                    ultimo.setSiguiente(cabeza);
                }
                else
                { 
                    if(expediente.getCedula() > ultimo.getExpediente().getCedula())
                    {
                        NodoDC aux = new NodoDC(expediente);
                        aux.setAnterior(ultimo);
                        ultimo.setSiguiente(aux);
                        ultimo = aux;
                        ultimo.setSiguiente(cabeza);
                        cabeza.setAnterior(ultimo);
                    }
                    else
                    {
                        NodoDC aux = cabeza.getSiguiente();
                        while(aux.getExpediente().getCedula() < expediente.getCedula())
                        {
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
            
            JOptionPane.showMessageDialog(null,"Paciente "+expediente.getNombreCompleto()+" asiste a consulta por primera vez");
            expediente.getHistoricoCitas().agregarCita();
            expediente.getHistoricoMedicamentos().agregarMedicamento();
        }
    }

    @Override
    public String toString() {
        String respuesta = "Lista doble circular: \n";
        
        if(cabeza != null)
        {
            NodoDC aux = cabeza;
            
            respuesta += aux.toString() + "\n";
             
            aux = aux.getSiguiente();
            
            while(aux != cabeza)
            {
                respuesta += aux.toString() + "\n";
                aux = aux.getSiguiente();
            }
        }
        else
        {
            respuesta += "Vacía";
        }
        
        return respuesta;
    }
    
    public Expediente solicitarInformacion(){
        Boolean error = false;
        Expediente expediente = new Expediente();
        expediente.setNombreCompleto(JOptionPane.showInputDialog("Ingrese el nombre completo del paciente: "));
        do{
            try{ 
                expediente.setCedula(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cedula del paciente: ")));
                error = false;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor numerico");
                error = true;
            }
        }while(error == true);
        
        do{
            try{ 
                expediente.setEdad(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del paciente: ")));
                error = false;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor numerico");
                error = true;
            }
        }while(error == true);
        expediente.setGenero(JOptionPane.showInputDialog("Ingrese el genero del paciente: "));
        return expediente;
    }
    
    public Expediente validarExpedienteExiste(Integer cedula){
        if(cabeza != null)
        {
            NodoDC aux = cabeza; 
            aux = aux.getSiguiente();
            
            if (aux.getExpediente().getCedula() == cedula) {
                return aux.getExpediente();
            }
            
            while(aux != cabeza)
            {
                if (aux.getExpediente().getCedula() == cedula) {
                    return aux.getExpediente();
                }
                aux = aux.getSiguiente();
            }
        }

        return null; // Elemento no encontrado
    }
}