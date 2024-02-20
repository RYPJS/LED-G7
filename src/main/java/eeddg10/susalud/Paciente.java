package eeddg10.susalud;
import java.util.Date;

public class Paciente {
    private String nombreCompleto;
    private String cedula;
    private String tipo;
    private String ficha;
    private Date ingreso;

    public Paciente(String nombreCompleto, String cedula, String tipo, String ficha) {
        this.nombreCompleto = nombreCompleto;
        this.cedula = cedula;
        this.tipo = tipo;
        this.ficha = ficha;
        this.ingreso = new Date();
    } 
    
    public Paciente() {
        this.nombreCompleto = "";
        this.cedula = "";
        this.tipo = "";
        this.ingreso = new Date();
    } 

    public String getNombreCompleto() {
        return this.nombreCompleto;
    }
    
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public String getCedula() {
        return this.cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getFicha() {
        return this.ficha;
    }
    
    public void setFicha(String ficha) {
        this.ficha = ficha;
    }
    
    public Date getIngreso() {
        return this.ingreso;
    }
}