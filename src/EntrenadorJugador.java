
import java.util.InputMismatchException;
import java.util.Scanner;

public class EntrenadorJugador extends Entrenador {

    

    public EntrenadorJugador(String nombre) {
        super(nombre);
        
    }

    

    public int seleccionarInicial() {   //Al empezar el turno, debes seleccionar el pokemon con el que quieres empezar
        Scanner tcl = new Scanner(System.in);
        int eleccion = 0;
        System.out.println(super.getNombre() + ", ¿Con que pokemon quieres empezar?");
        this.mostrarEquipo();
        while (eleccion != 1 && eleccion != 2 && eleccion != 3) {
            try {
                eleccion = tcl.nextInt();
                if (eleccion < 1 || eleccion > 3) {
                    System.out.println("Introduce un numero del menu.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error, no has introducido un numero.");
                tcl.next();
            }
        }
        return eleccion;
    }

    public int seleccionarAtaque(Pokemon poke) {  //Muestra los ataque de tu pokemon y permire seleccionar uno
        Scanner tcl = new Scanner(System.in);
        int respuestaCombate = 0;
        if (poke.getHp() > 0) {
            System.out.println(this.getNombre() + ", selecciona un ataque: ");
            System.out.println("  1- " + poke.getA1().getNombre() + " 2- " + poke.getA2().getNombre() + " 3- " + poke.getA3().getNombre() + " 4- " + poke.getA4().getNombre());
            while (respuestaCombate != 1 && respuestaCombate != 2 && respuestaCombate != 3 && respuestaCombate != 4) {
                try {
                    respuestaCombate = tcl.nextInt();
                    if (respuestaCombate < 1 || respuestaCombate > 4) {
                        System.out.println("Introduce un numero del menu.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error, no has introducido un numero.");
                    tcl.next();
                }
            }
        }
        return respuestaCombate;
    }

    public int cambiarPokemon(Pokemon pokeActivo, int posicionPokeActivo) { //Cambias al pokemon que estas utilizando por otro de tu equipo
        Scanner tcl = new Scanner(System.in);
        boolean error = true;
        int respuesta = 0;
        String PokemonEntra, PokemonSale;
        PokemonEntra = pokeActivo.getNombre();
            System.out.println(this.getNombre() + " ¿Por que pokemon quieres sustituir a " + pokeActivo.getNombre() + "?");
            this.mostrarEquipo();
        while (error == true) {
            try {
                respuesta = tcl.nextInt() - 1;
                if (respuesta < 0 || respuesta > 2){
                    System.out.println("Introduce un numero del menu");
                } else if (respuesta == posicionPokeActivo) {
                    System.out.println("Este pokemon ya está en combate");
                } else if (super.getEquipo()[respuesta].getHp() == 0) {
                    System.out.println("Este pokemon ya está debilitado.");
                } else {
                    PokemonSale = super.getEquipo()[respuesta].getNombre();
                    System.out.println(this.getNombre() + " cambia a " + PokemonEntra + " por " + PokemonSale);
                    error = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error, no has introducido un numero.");
                tcl.next();
            } 

        }
        return respuesta;
    }

    public void rendirse(Entrenador rival) {
        System.out.println("El combate finaliza por una retirada.");
        System.out.println(rival.getNombre() + " ha ganado.");
        for (int i = 0; i < this.getNumeroPokemons(); i++) {
            this.getEquipo()[i].setHp(0);
        }
    }

    public void dobleRendición() {  //Sirve para que en caso de que ambos jugadores se rindan a la vez, no salte el mensaje de que uno se ha rendido
        //y gana el otro
        for (int i = 0; i < this.getNumeroPokemons(); i++) {
            this.getEquipo()[i].setHp(0);
        }
    }

    public void turno(Pokemon poke, int posicionPoke, Entrenador rival) { //Te da a elegir entre atacar, cambiar pokemon, o rendirte
        Scanner tcl = new Scanner(System.in);
        int respuesta = 0;
        System.out.println("Turno de " + this.getNombre());
        boolean error = true;
            System.out.println("1- Combatir   2- Cambiar Pokemon 3- Rendirse");
        while (error == true) {
            try {
                respuesta = tcl.nextInt();
                if (respuesta < 1 || respuesta > 3) {
                    System.out.println("Introduce un numero del menu.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error, no has introducido un numero.");
                tcl.next();
            }
            if (respuesta == 1) {
                super.setTurno(1);
                error = false;
            }
            if (respuesta == 2) {
                if (this.totalVida() != super.getPokeActivo().getHp()) {
                    super.setTurno(2);
                    error = false;
                } else {
                    System.out.println("No te quedan mas pokemons.");
                }
            }
            if (respuesta == 3) {
                super.setTurno(3);
                error = false;
            }
        }

    }

}
