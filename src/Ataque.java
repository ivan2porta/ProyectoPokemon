
import java.io.Serializable;


public class Ataque implements Serializable{
    private String nombre;
    private int potencia;
    private tipos tipo;
    private int tipoE;
    private Categoria cat;
    
    public Ataque(String nombre, int potencia, tipos tipo,int tipoE, Categoria cat){
        this.nombre=nombre;
        this.potencia=potencia;
        this.tipo=tipo;
        this.tipoE=tipoE;
        this.cat=cat;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPotencia() {
        return potencia;
    }

    public tipos getTipo() {
        return tipo;
    }

    public Categoria getCat() {
        return cat;
    }

    public int getTipoE() {
        return tipoE;
    }

    @Override
    public String toString() {
        return nombre + " - Potencia = " + potencia + " - Tipo = " + tipo + " - Categoria = " + cat ;
    }
    
    
    
}
