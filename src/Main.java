
public class Main {

    public static void main(String[] args) {
        EntrenadorJugador e1;
        int eleccion = 0;
        Menu menu = new Menu();
        Reproductor rm = new Reproductor("menu.wav");
        Reproductor rc = new Reproductor("combate.wav");

        rm.reproducirMusica();
        while (eleccion != 6) {

            eleccion = menu.Principal();

            switch (eleccion) {
                case 1 -> {
                    e1 = menu.crearEntrenador();
                    EntrenadorBOT rival = menu.elegirEntrenador();
                    rm.pararMusica();
                    rc.reproducirMusica();
                    menu.combate1jugador(e1, rival);
                    rc.pararMusica();
                    rm.reproducirMusica();
                }
                case 2 -> {
                    e1 = menu.crearEntrenador();
                    EntrenadorJugador e2 = menu.crearEntrenador();
                    rm.pararMusica();
                    rc.reproducirMusica();
                    menu.combate2Jugadores(e1, e2);
                    rc.pararMusica();
                    rm.reproducirMusica();
                }
                case 3 ->
                    menu.imprimirPokemons();
                case 4 ->
                    menu.imprimirAtaques();
                case 5 ->
                    menu.imprimirInstrucciones();
                case 6 -> {
                    rm.pararMusica();
                }
            }
        }
    }
}
