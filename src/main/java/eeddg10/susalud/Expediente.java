package eeddg10.susalud;

/**
 * La clase Expediente representa un expediente médico.
 * Cada objeto Expediente contiene el nombre completo, la cédula, la edad, el género del paciente, y su historial de medicamentos y citas.
 */
public class Expediente {
    private String nombreCompleto;
    private Integer cedula;
    private Integer edad;
    private String genero;
    private HistoricoMedicamentos historicoMedicamentos;
    private HistoricoCitas historicoCitas;

    /**
     * Constructor con todos los parámetros.
     *
     * @param cedula                La cédula del paciente.
     * @param nombreCompleto        El nombre completo del paciente.
     * @param edad                  La edad del paciente.
     * @param genero                El género del paciente.
     * @param historicoMedicamentos El historial de medicamentos del paciente.
     */
    public Expediente(Integer cedula, String nombreCompleto, Integer edad, String genero, HistoricoMedicamentos historicoMedicamentos) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.genero = genero;
        this.historicoMedicamentos = new HistoricoMedicamentos();
        this.historicoCitas = new HistoricoCitas();
    }

    /**
     * Constructor sin parámetros. Inicializa los campos con valores por defecto.
     */
    public Expediente() {
        this.nombreCompleto = "";
        this.cedula = 0;
        this.edad = 0;
        this.genero = "";
        this.historicoMedicamentos = new HistoricoMedicamentos();
        this.historicoCitas = new HistoricoCitas();
    }

    public Integer getCedula() {
        return this.cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return this.nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getEdad() {
        return this.edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setHistoricoMedicamentos(HistoricoMedicamentos historicoMedicamentos) {
        this.historicoMedicamentos = historicoMedicamentos;
    }

    public HistoricoMedicamentos getHistoricoMedicamentos() {
        return historicoMedicamentos;
    }

    public void setHistoricoCitas(HistoricoCitas historicoMedicamentos) {
        this.historicoCitas = historicoCitas;
    }

    public HistoricoCitas getHistoricoCitas() {
        return historicoCitas;
    }
}