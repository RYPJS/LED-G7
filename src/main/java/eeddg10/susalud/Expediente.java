package eeddg10.susalud;

public class Expediente {
    private String nombreCompleto;
    private Integer cedula;
    private Integer edad;
    private String genero;
    private HistoricoMedicamentos historicoMedicamentos;
    private HistoricoCitas historicoCitas;

    public Expediente(Integer cedula, String nombreCompleto, Integer edad, String genero, HistoricoMedicamentos historicoMedicamentos) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.genero = genero;
        this.historicoMedicamentos = new HistoricoMedicamentos();
        this.historicoCitas = new HistoricoCitas();
    } 
    
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