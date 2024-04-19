package eeddg10.susalud;

public class Pregunta {
    private static int contadorCodigo = 0; // Variable estática para el código autoincremental
    private Integer codigo;
    private String nombre;
    private String respuesta;
    
    public Pregunta(String nombre, String respuesta) {
        this.codigo = contadorCodigo++;
        this.nombre = nombre;
        this.respuesta = respuesta;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) { 
        this.respuesta = respuesta;
    }
}