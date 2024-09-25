
import java.io.Serializable;


abstract class Entrenador implements Serializable{
    private int numeroPokemons; //Los pokemons del equipo
    final private String nombre; //El nombre del Entrenador
    private int turno; //La eleccion que hace el jugador en cada turno
    private Pokemon[] equipo = new Pokemon[3]; //Los 3 pokemons que están en el equipo
    private Pokemon pokeActivo; //El pokemon que combate
    private int pokeActivoN; //Posición del pokeon que combate
    private int eleccion;
    
    public Entrenador(String nombre){
        this.nombre = nombre;
        this.numeroPokemons = 0;
    }
    
    public void agregaPokemon(Pokemon a) {  //agrega un pokemon al equipo
        this.equipo[numeroPokemons] = a;
        numeroPokemons++;
    }

    public void mostrarEquipo() {  //muestra el equipo del entrenador
        for (int i = 0; i < 3; i++) {
            System.out.println("  " + (i + 1) + "- " + equipo[i].getNombre() + " (" + equipo[i].getHp() + "/" + equipo[i].getTotalhp() + ")");
        }
    }
    
    public void setEleccion(int eleccion) {
        this.eleccion = eleccion;
    }
    
    public int getEleccion() {
        return eleccion;
    }

    public int seleccionTurno() {
        return turno;
    }

    public Pokemon damePoke(int a) {   //Devuelve el pokemon de la posición indicada
        return equipo[a];
    }

    public int totalVida() {  //Devuelve la suma de la vida de los 3 pokemon del equipo.
        int r = 0;
        for (int i = 0; i < equipo.length; i++) {
            r += this.damePoke(i).getHp();
        }
        return r;
    }

    public void setPokeActivoN(int pokeActivoN) {
        this.pokeActivoN = pokeActivoN;
    }

    

    public int getPokeActivoN() {
        return pokeActivoN;
    }

    public int getTurno() {
        return turno;
    }

    public Pokemon getPokeActivo() {
        return pokeActivo;
    }

    public void setPokeActivo(Pokemon pokeActivo) {
        this.pokeActivo = pokeActivo;
    }

    public String getNombre() {
        return nombre;
    }

    public int numPokes() {
        return numeroPokemons;
    }

    public int getNumeroPokemons() {
        return numeroPokemons;
    }

    public Pokemon[] getEquipo() {
        return equipo;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    
}
