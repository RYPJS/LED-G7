package eeddg10.susalud;

import java.util.Date;

/**
 * La clase Bitacora representa una bitácora de atención a pacientes.
 * Cada objeto Bitacora contiene la ficha del paciente, la fecha de atención, la cédula, el nombre y la fecha de llegada del paciente.
 */
public class Bitacora {
    /**
     * La clase Bitacora representa una bitácora de atención a pacientes.
     * Cada objeto Bitacora contiene la ficha del paciente, la fecha de atención, la cédula, el nombre y la fecha de llegada del paciente.
     */
    private String ficha;
    private Date fechaAtencion;
    private Integer cedula;
    private String nombre;
    private Date fechaLlegada;

    /**
     * Constructor con todos los parámetros.
     *
     * @param ficha         La ficha del paciente.
     * @param fechaAtencion La fecha de atención del paciente.
     * @param cedula        La cédula del paciente.
     * @param nombre        El nombre del paciente.
     * @param fechaLlegada  La fecha de llegada del paciente.
     */
    public Bitacora(String ficha, Date fechaAtencion, Integer cedula, String nombre, Date fechaLlegada) {
        this.ficha = ficha;
        this.fechaAtencion = fechaAtencion;
        this.cedula = cedula;
        this.nombre = nombre;
        this.fechaLlegada = fechaLlegada;
    }

    /**
     * Constructor sin parámetros. Inicializa los campos con valores por defecto.
     */
    public Bitacora() {
        this.ficha = "";
        this.fechaAtencion = new Date();
        this.cedula = 0;
        this.nombre = "";
        this.fechaLlegada = new Date();
    }

    /**
     * @return La ficha del paciente.
     */
    public String getFicha() {
        return this.ficha;
    }

    /**
     * @param ficha La nueva ficha del paciente.
     */
    public void setFicha(String ficha) {
        this.ficha = ficha;
    }

    /**
     * @return La fecha de atención del paciente.
     */
    public Date getFechaAtencion() {
        return this.fechaAtencion;
    }

    /**
     * @param fechaAtencion La nueva fecha de atención del paciente.
     */
    public void setFechaAtencion(Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    /**
     * @return La cédula del paciente.
     */
    public Integer getCedula() {
        return this.cedula;
    }

    /**
     * @param cedula La nueva cédula del paciente.
     */
    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    /**
     * @return El nombre del paciente.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * @param nombre El nuevo nombre del paciente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return La fecha de llegada del paciente.
     */
    public Date getFechaLlegada() {
        return this.fechaLlegada;
    }

    /**
     * @param fechaLlegada La nueva fecha de llegada del paciente.
     */
    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }
}