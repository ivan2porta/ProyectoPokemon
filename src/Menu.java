
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Menu {

    private final Lector lec;

    public Menu() {
        lec = new Lector();
    }

    public int Principal() { //Muestra el menu principal
        Scanner tcln = new Scanner(System.in);
        int r1 = 0;
        boolean f = true;
        System.out.println();
        imprimirLogo();
        System.out.println();
        System.out.println("=====================");
        System.out.println("1- Modo 1 jugador\n2- Modo 2 jugadores\n3- Lista de pokemons\n4- Lista de movimientos\n5- Instrucciones\n6- Salir");
        System.out.println("=====================");
        System.out.println();
        while (f == true) {
            try {
                r1 = tcln.nextInt();
                if (r1 < 1 || r1 > 6) {
                    System.out.println("Introduce un numero del menu.");
                } else {
                    f = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error, no has introducido un numero del menu.");
                tcln.next();
            }
        }
        return r1;
    }

    public EntrenadorJugador crearEntrenador() { //Permite crear un entrenador, y elegir los 3 pokemons de tu equipo.
        Pokedex pkdx = new Pokedex();
        Scanner tcl = new Scanner(System.in);
        String nombre;

        int r1;
        boolean acabar;
        System.out.println("============================================");
        System.out.print("Introduce tu nombre: ");
        nombre = tcl.nextLine();
        EntrenadorJugador a = new EntrenadorJugador(nombre);
        System.out.println();
        System.out.println("Introduce el numero de los 3 pokemons que quieres elegir (Pulsa intro despues de cada numero)");
        pkdx.listarPokedex();
        System.out.println();
        for (int i = 0; i < 3; i++) {
            acabar = false;
            while (acabar == false) {
                try {
                    r1 = tcl.nextInt() - 1;
                    if (r1 < 0 || r1 > pkdx.numeroDex() - 1) {
                        System.out.println("El numero que has introducido no pertenece a ningun pokemon");
                    } else if (esta(a, pkdx, r1) == true) {
                        System.out.println(pkdx.nombrePokemon(r1) + " ya esta en tu equipo");
                    } else {
                        System.out.println("¡" + pkdx.nombrePokemon(r1) + " se ha unido a tu equipo!");
                        a.agregaPokemon(pkdx.damePoke(r1));
                        acabar = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error, no has introducido un numero");
                    tcl.next();
                }
            }
        }
        System.out.println("============================================");
        System.out.println();

        return a;
    }

    public boolean esta(EntrenadorJugador a, Pokedex p, int n) {
        boolean esta = false;
        for (Pokemon equipo : a.getEquipo()) {
            try {
                if (p.listaPokemon.get(n).equals(equipo)) {
                    esta = true;
                }
            } catch (NullPointerException e) {
            }
        }
        return esta;
    }

    public EntrenadorBOT elegirEntrenador() { //Hay 4 entrenadores en un fichero, te permite elegir contra cual quieres combatir
        Scanner tcl = new Scanner(System.in);
        boolean f = true;
        int r = 0;
        ArrayList<EntrenadorBOT> entrenadores = new ArrayList<>();
        EntrenadorBOT en;
        lec.abrirLecturaObjeto("EntrenadoresBOT.obj");
        lec.guardarEntrenadorDeFicheroEnArrayList(entrenadores);
        lec.cerrarLecturaObjetos();
        System.out.println("¿Contra quien queres combatir?");
        for (int i = 0; i < entrenadores.size(); i++){
            System.out.println((i+1)+" "+entrenadores.get(i).toString());
        System.out.println();
        }

        while (f == true) {
            try {
                r = tcl.nextInt();
                if (r < 1 || r > 4) {
                    System.out.println("Introduce un numero del menu.");
                } else {
                    f = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error, no has introducido un numero.");
                tcl.next();
            }
        }
        en = entrenadores.get(r - 1);
        return en;
    }

    public void combate2Jugadores(EntrenadorJugador a, EntrenadorJugador b) { //Combate entre 2 jugadores

        int turno = 1;
        boolean rendirse = false;

        System.out.println();
        System.out.println("============================================================");
        System.out.println("Empieza el combate entre " + a.getNombre() + " y " + b.getNombre());
        System.out.println("============================================================");
        System.out.println();
        System.out.println("============================================================");
        a.setPokeActivoN(a.seleccionarInicial() - 1);
        a.setPokeActivo(a.damePoke(a.getPokeActivoN()));
        System.out.println("============================================================");
        System.out.println();
        System.out.println("============================================================");
        b.setPokeActivoN(b.seleccionarInicial() - 1);
        b.setPokeActivo(b.damePoke(b.getPokeActivoN()));
        System.out.println("============================================================");

        while ((a.totalVida() > 0 && b.totalVida() > 0) && rendirse == false) {  //El combate dura mientras la vida de los 2 equipos sea superior a 0,
            //el booleano se utiliza para que aparezca un mensaje distinto si uno, o ambos jugadores se rinden.

            imagenCombate(a, b, turno);

            System.out.println("============================================================");
            a.turno(a.getPokeActivo(), a.getPokeActivoN(), b);
            System.out.println("============================================================");

            System.out.println();

            System.out.println("============================================================");
            b.turno(b.getPokeActivo(), b.getPokeActivoN(), a);
            System.out.println("============================================================");

            if (a.getTurno() == 3 && b.getTurno() == 3) { //ambos jugadores se rinden
                rendirse = RendRend(a, b);
            }

            if (a.getTurno() == 3 && b.getTurno() != 3) { //uno se rinde
                rendirse = RendOtro(a, b);
            }

            if (a.getTurno() != 3 && b.getTurno() == 3) { //uno se rinde
                rendirse = RendOtro(b, a);
            }

            if (a.getTurno() == 1 && b.getTurno() == 2) { //uno ataca y otro cambia
                AtaqueCambio(a, b);
            }

            if (a.getTurno() == 2 && b.getTurno() == 1) { //uno cambia y otro ataca
                AtaqueCambio(b, a);
            }

            if (a.getTurno() == 2 && b.getTurno() == 2) { //ambos cambian
                Cambio(a);
                Cambio(b);
            }

            if (a.getTurno() == 1 && b.getTurno() == 1) { //ambos atacan (primero el mas rapido)
                System.out.println("============================================================");
                a.setEleccion(a.seleccionarAtaque(a.getPokeActivo()));
                System.out.println("============================================================");
                b.setEleccion(b.seleccionarAtaque(b.getPokeActivo()));
                System.out.println("============================================================");

                if (a.getPokeActivo().getSpe() > b.getPokeActivo().getSpe()) {
                    AtaqueAtaque(a, b);
                } else if (a.getPokeActivo().getSpe() < b.getPokeActivo().getSpe()) {
                    AtaqueAtaque(b, a);
                } else if (a.getPokeActivo().getSpe() == b.getPokeActivo().getSpe()) { //Si los 2 son igual de rapidos, el que ataca primero se decide aleatoriamente
                    Random rnd = new Random();
                    int moneda = rnd.nextInt(2);
                    if (moneda == 0) {
                        AtaqueAtaque(a, b);
                    } else {
                        AtaqueAtaque(b, a);
                    }
                }
            }
            System.out.println("============================================================");
            turno++;
        }
        System.out.println();
        if (a.totalVida() == 0 && a.getTurno() != 3 && rendirse == false) {
            System.out.println("*** El combate ha finalizado, " + b.getNombre() + " ha ganado. ***");
        }

        if (b.totalVida() == 0 && b.getTurno() != 3 && rendirse == false) {
            System.out.println("*** El combate ha finalizado, " + a.getNombre() + " ha ganado. ***");
        }
        System.out.println();
    }

    public void imagenCombate(Entrenador a, Entrenador b, int turno) { //Muestra el estado de los pokemons en cada turno
        System.out.println("********************************************************");
        System.out.println("                                                Turno:" + turno);
        System.out.println(a.getNombre() + ": " + a.getPokeActivo().getNombre() + " (" + a.getPokeActivo().getTipo1n() + " / " + a.getPokeActivo().getTipo2n() + ") (" + a.getPokeActivo().getHp() + "/" + a.getPokeActivo().getTotalhp() + ")");
        System.out.println();
        System.out.println(b.getNombre() + ": " + b.getPokeActivo().getNombre() + " (" + b.getPokeActivo().getTipo1n() + " / " + b.getPokeActivo().getTipo2n() + ") (" + b.getPokeActivo().getHp() + "/" + b.getPokeActivo().getTotalhp() + ")");
        System.out.println();
        System.out.println("********************************************************");
    }

    public void combate1jugador(EntrenadorJugador a, EntrenadorBOT b) { //Parecido al combate de 2 jugadores, pero utilizando los metodos random para el entrenador b. 
        int turno = 1;
        boolean rendirse = false;

        System.out.println();
        System.out.println("============================================================");
        System.out.println("Empieza el combate entre " + a.getNombre() + " y " + b.getNombre());
        System.out.println("============================================================");
        a.setPokeActivoN(a.seleccionarInicial() - 1);
        a.setPokeActivo(a.damePoke(a.getPokeActivoN()));
        System.out.println();
        System.out.println("============================================================");
        b.setPokeActivoN(b.BOTseleccionarInicial());
        b.setPokeActivo(b.damePoke(b.getPokeActivoN()));
        System.out.println("============================================================");

        while ((a.totalVida() > 0 && b.totalVida() > 0) && rendirse == false) {  //El combate dura mientras la vida de los 2 equipos sea superior a 0,
            //el booleano se utiliza para que aparezca un mensaje distinto si uno, o ambos jugadores se rinden.

            imagenCombate(a, b, turno);

            System.out.println("============================================================");
            a.turno(a.getPokeActivo(), a.getPokeActivoN(), b);
            System.out.println("============================================================");

            b.BOTTurno();

            if (a.getTurno() == 3 && b.getTurno() != 3) { //jugador se rinde
                rendirse = RendOtro(a, b);
            }

            if (a.getTurno() == 1 && b.getTurno() == 2) { //Jugador ataca, bot cambia
                AtaqueBOTCambio(a, b);
            }

            if (a.getTurno() == 2 && b.getTurno() == 1) { //bot cambia, jugador ataca
                BOTAtaqueCambio(a, b);
            }

            if (a.getTurno() == 2 && b.getTurno() == 2) { //ambos cambian
                Cambio(a);
                BOTCambio(b);
            }

            if (a.getTurno() == 1 && b.getTurno() == 1) { //ambos atacan (primero el mas rapido)
                System.out.println("============================================================");
                a.setEleccion(a.seleccionarAtaque(a.getPokeActivo()));
                System.out.println("============================================================");
                b.setEleccion(b.BOTSeleccionarAtaque());

                if (a.getPokeActivo().getSpe() > b.getPokeActivo().getSpe()) {
                    AtaqueBOTAtaque(a, b);
                } else if (a.getPokeActivo().getSpe() < b.getPokeActivo().getSpe()) {
                    BOTAtaqueAtaque(a, b);
                } else if (a.getPokeActivo().getSpe() == b.getPokeActivo().getSpe()) { //Si los 2 son igual de rapidos, el que ataca primero se decide aleatoriamente
                    Random rnd = new Random();
                    int moneda = rnd.nextInt(2);
                    if (moneda == 0) {
                        AtaqueBOTAtaque(a, b);
                    } else {
                        BOTAtaqueAtaque(a, b);
                    }
                }
            }
            System.out.println("============================================================");
            turno++;
        }
        System.out.println();
        if (a.totalVida() == 0 && a.getTurno() != 3) {
            System.out.println("*** El combate ha finalizado, " + b.getNombre() + " ha ganado. ***");
        }

        if (b.totalVida() == 0 && b.getTurno() != 3) {
            System.out.println("*** El combate ha finalizado, " + a.getNombre() + " ha ganado. ***");
        }
        System.out.println();
    }

    public boolean RendRend(EntrenadorJugador a, EntrenadorJugador b) { //Envia el mensaje en caso de que ambos se rindan
        a.dobleRendición();
        b.dobleRendición();
        System.out.println("Ambos jugadores se han rendido, el combate finaliza con un empate.");
        System.out.println();

        return true;
    }

    public boolean RendOtro(EntrenadorJugador a, Entrenador b) { //Se utiliza cuando solo se rinde un jugador
        a.rendirse(b);
        return true;
    }

    public void AtaqueAtaque(EntrenadorJugador a, EntrenadorJugador b) {  //Los 2 jugadores atacan
        a.getPokeActivo().atacar(b.getPokeActivo(), a.getEleccion());
        if (b.getPokeActivo().getHp() > 0) {
            b.getPokeActivo().atacar(a.getPokeActivo(), b.getEleccion());
            if (a.getPokeActivo().getHp() == 0 && a.totalVida() > 0) {
                Cambio(a);
            }
        } else if (b.getPokeActivo().getHp() == 0 && b.totalVida() > 0) {
            Cambio(b);
        }
    }

    public void Cambio(EntrenadorJugador a) { //Cambia el pokemon activo, y su posicion
        a.setPokeActivoN(a.cambiarPokemon(a.getPokeActivo(), a.getPokeActivoN()));
        a.setPokeActivo(a.damePoke(a.getPokeActivoN()));
    }

    public void AtaqueCambio(EntrenadorJugador a, EntrenadorJugador b) { //Un jugador ataca y otro cambia
        System.out.println("============================================================");
        a.setEleccion(a.seleccionarAtaque(a.getPokeActivo()));
        System.out.println("============================================================");
        b.setPokeActivoN(b.cambiarPokemon(b.getPokeActivo(), b.getPokeActivoN()));
        System.out.println("============================================================");
        b.setPokeActivo(b.getEquipo()[b.getPokeActivoN()]);
        a.getPokeActivo().atacar(b.getPokeActivo(), a.getEleccion());
        if (b.getPokeActivo().getHp() == 0 && b.totalVida() > 0) {
            Cambio(b);
        }
    }

    public void BOTAtaqueCambio(EntrenadorJugador a, EntrenadorBOT b) { //El bot ataca, y el jugador cambia
        b.setEleccion(b.BOTSeleccionarAtaque());
        System.out.println("============================================================");
        a.setPokeActivoN(a.cambiarPokemon(a.getPokeActivo(), a.getPokeActivoN()));
        System.out.println("============================================================");
        a.setPokeActivo(a.getEquipo()[a.getPokeActivoN()]);
        b.getPokeActivo().atacar(a.getPokeActivo(), b.getEleccion());
        if (a.getPokeActivo().getHp() == 0 && a.totalVida() > 0) {
            Cambio(a);
        }
    }

    public void AtaqueBOTCambio(EntrenadorJugador a, EntrenadorBOT b) {  //El jugdor ataca y el bot cambia
        System.out.println("============================================================");
        a.setEleccion(a.seleccionarAtaque(a.getPokeActivo()));
        System.out.println("============================================================");
        BOTCambio(b);
        a.getPokeActivo().atacar(b.getPokeActivo(), a.getEleccion());
        if (b.getPokeActivo().getHp() == 0 && b.totalVida() > 0) {
            BOTCambio(b);
        }
    }

    public void BOTCambio(EntrenadorBOT b) { //El bot cambia su poquemon activo, y la posicion de este
        b.setPokeActivoN(b.BOTCambiarPokemon(b.getPokeActivo(), b.getPokeActivoN()));
        b.setPokeActivo(b.damePoke(b.getPokeActivoN()));
    }

    public void BOTAtaqueAtaque(EntrenadorJugador a, EntrenadorBOT b) { //El bot y el jugador atacan (Primero el bot)
        b.getPokeActivo().atacar(a.getPokeActivo(), b.getEleccion());
        if (a.getPokeActivo().getHp() > 0) {
            a.getPokeActivo().atacar(b.getPokeActivo(), a.getEleccion());
            if (b.getPokeActivo().getHp() == 0 && b.totalVida() > 0) {
                BOTCambio(b);
            }
        } else if (a.getPokeActivo().getHp() == 0 && a.totalVida() > 0) {
            Cambio(a);
        }
    }

    public void AtaqueBOTAtaque(EntrenadorJugador a, EntrenadorBOT b) { //El bot y el jugador atacan (Primero el jugador)
        a.getPokeActivo().atacar(b.getPokeActivo(), a.getEleccion());
        if (b.getPokeActivo().getHp() > 0) {
            b.getPokeActivo().atacar(a.getPokeActivo(), b.getEleccion());
            if (a.getPokeActivo().getHp() == 0 && a.totalVida() > 0) {
                Cambio(a);
            }
        } else if (b.getPokeActivo().getHp() == 0 && b.totalVida() > 0) {
            BOTCambio(b);
        }
    }
    
        public void imprimirInstrucciones() { //Las instrucciones están en un fichero
        lec.abrirFileReader("Instrucciones.txt");
        lec.leerFileReader();
        lec.cerrarFileReader();
        System.out.println();
        System.out.println();
    }
    
    public void imprimirLogo() { 
        lec.abrirFileReader("PokemonLogo.txt");
        lec.leerFileReader();
        lec.cerrarFileReader();
        System.out.println();
        System.out.println();
    }

    public void imprimirAtaques() {  //Los ataques están en un fichero
        lec.abrirLecturaObjeto("Ataques.obj");
        lec.leerAtaques();
        lec.cerrarLecturaObjetos();
        System.out.println();
        System.out.println();
    }

    public void imprimirPokemons() { //Los pokemons están en un fichero
        lec.abrirLecturaObjeto("Pokemons.obj");
        lec.leerPokemons();
        lec.cerrarLecturaObjetos();
        System.out.println();
        System.out.println();
    }

}
