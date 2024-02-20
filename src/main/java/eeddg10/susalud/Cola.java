package eeddg10.susalud;

public class Cola{
    private NodoCola frente;
    private NodoCola ultimo;
    
    public void encola(NodoCola elemento)
    {
        if(frente == null)
        {
            frente = elemento;
            ultimo = elemento;
        }
        else
        {
            ultimo.setAtras(elemento);
            ultimo = elemento;
        }
    }
    
    public NodoCola salirDeCola()
    {
        NodoCola actual = frente;
        
        if(frente != null)
        {
            frente = frente.getAtras();
            actual.setAtras(null);
        }
        
        return actual;
    }
    
    public boolean esVacia() {
        return frente == null;
    }
    
    public NodoCola getCima() {
        return ultimo;
    }
    
    @Override
    public String toString() {
        return this.frente.toString();
    }
}
