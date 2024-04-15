package eeddg10.susalud;

import java.util.Date;

/**
 * La clase Paciente representa a un paciente en el sistema.
 * Cada objeto Paciente contiene el nombre completo, la cédula, el tipo, la ficha y la fecha de ingreso del paciente.
 */
public class Paciente {
    private String nombreCompleto;
    private Integer cedula;
    private String tipo;
    private String ficha;
    private Date ingreso;

    /**
     * Constructor con todos los parámetros.
     *
     * @param nombreCompleto El nombre completo del paciente.
     * @param cedula         La cédula del paciente.
     * @param tipo           El tipo de paciente.
     * @param ficha          La ficha del paciente.
     */
    public Paciente(String nombreCompleto, Integer cedula, String tipo, String ficha) {
        this.nombreCompleto = nombreCompleto;
        this.cedula = cedula;
        this.tipo = tipo;
        this.ficha = ficha;
        this.ingreso = new Date();
    }

    /**
     * Constructor sin parámetros. Inicializa el nombre completo, la cédula y el tipo con valores por defecto, y la fecha de ingreso con la fecha actual.
     */
    public Paciente() {
        this.nombreCompleto = "";
        this.cedula = 0;
        this.tipo = "";
        this.ingreso = new Date();
    }

    public String getNombreCompleto() {
        return this.nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getCedula() {
        return this.cedula;
    }

    public void setCedula(Integer cedula) {
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