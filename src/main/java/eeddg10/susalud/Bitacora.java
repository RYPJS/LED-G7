package eeddg10.susalud;

import java.util.Date;

public class Bitacora {
    private String ficha;
    private Date fechaAtencion;
    private Integer cedula;
    private String nombre;
    private Date fechaLlegada;

    public Bitacora(String ficha, Date fechaAtencion, Integer cedula, String nombre, Date fechaLlegada) {
        this.ficha = ficha;
        this.fechaAtencion = fechaAtencion;
        this.cedula = cedula;
        this.nombre = nombre;
        this.fechaLlegada = fechaLlegada;
    } 
    
    public Bitacora() {
        this.ficha = "";
        this.fechaAtencion = new Date();
        this.cedula = 0;
        this.nombre = "";
        this.fechaLlegada = new Date();
    }
    
    public String getFicha() {
        return this.ficha;
    }
    
    public void setFicha(String ficha) {
        this.ficha = ficha;
    }
    
    public Date getFechaAtencion() {
        return this.fechaAtencion;
    }
    
    public void setFechaAtencion(Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }
    
    public Integer getCedula() {
        return this.cedula;
    }
    
    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Date getFechaLlegada() {
        return this.fechaLlegada;
    }
    
    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }
}