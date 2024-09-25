
import java.io.Serializable;
import java.util.Random;


public class EntrenadorBOT extends Entrenador implements Serializable{
    
    Random rnd;

    public EntrenadorBOT(String nombre) {
        super(nombre);
        this.rnd = new Random();
    }
    
    
    
     public int BOTseleccionarInicial() {  //Todos los metodos BOT replican las acciones de un jugador, pero mediante elecciones random.
        int r = rnd.nextInt(3);
        return r;
    }

    public void BOTTurno() {  //El BOT no se rinde, tiene un 85% de posibilidades de atacar, y un 15% de cambiar de pokemon.
        int r = rnd.nextInt(100);
        if (this.totalVida() == super.getPokeActivo().getHp()) {
            super.setTurno(1);
        } else {
            if (r <= 84) {
                super.setTurno(1);
            } else {
                super.setTurno(2);
            }
        }
    }

    public int BOTCambiarPokemon(Pokemon pokeActivo, int posicionPokeActivo) {
        boolean error = true;
        int respuesta = 0;
        String PokemonEntra, PokemonSale;
        PokemonEntra = pokeActivo.getNombre();
        while (error == true) {
            respuesta = rnd.nextInt(3);
            if (super.getEquipo()[respuesta].getHp() == 0) {
                respuesta = rnd.nextInt(3);
            } else if (respuesta == posicionPokeActivo) {
                respuesta = rnd.nextInt(3);
            } else {
                PokemonSale = super.getEquipo()[respuesta].getNombre();
                System.out.println(this.getNombre() + " cambia a " + PokemonEntra + " por " + PokemonSale);
                error = false;
            }
        }
        return respuesta;
    }

    public int BOTSeleccionarAtaque() {
        int r = rnd.nextInt(4) + 1;
        return r;
    }

    @Override
    public String toString() {
        String r;
        r = this.getNombre()+"\n";
        for (int i = 0; i < 3; i++) {
            r+= "  " + (i + 1) + "- " + super.getEquipo()[i].getNombre() + " (" + super.getEquipo()[i].getHp() + "/" + super.getEquipo()[i].getTotalhp() + ")\n";
        }
        return r;
    }
    
}
