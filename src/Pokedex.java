

import java.util.ArrayList;
import java.util.Collections;


public class Pokedex {

    ArrayList<Pokemon> listaPokemon = new ArrayList();
    Lector lec;

    public Pokedex() {
          lec = new Lector();
          lec.abrirLecturaObjeto("Pokemons.obj");
          lec.guardarPokemons(listaPokemon);
          lec.cerrarLecturaObjetos();
 
    }

    public void listarPokedex() {
        Collections.sort(listaPokemon);
        for (int i = 0; i < listaPokemon.size(); i++) {
            System.out.println((i + 1) + " - " + listaPokemon.get(i).getNombre());
        }
    }

    public String nombrePokemon(int i) {
        String a = listaPokemon.get(i).getNombre();
        return a;
    }

    public int numeroDex() {
        return listaPokemon.size();
    }
    
    public Pokemon damePoke(int a){
        return listaPokemon.get(a);
    }
    
    public ArrayList <Pokemon> a(){
        return listaPokemon;
    }
    
    
}
