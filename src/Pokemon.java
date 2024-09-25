
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Pokemon implements Comparable<Pokemon>, Serializable{

    final private String nombre;
    private int hp;
    final private int totalhp;
    final private int atk;
    final private int def;
    final private int spa;
    final private int spd;
    final private int spe;
    final private int tipo1;
    final private tipos tipo1n;
    final private int tipo2;
    final private tipos tipo2n;
    final ArrayList <Ataque> ataques;

    public Pokemon(String nombre, int hp, int totalhp, int atk, int def, int spa, int spd, int spe, int tipo1, tipos tipo1n, int tipo2, tipos tipo2n, Ataque a1, Ataque a2, Ataque a3, Ataque a4) {
        this.nombre = nombre;
        this.hp = hp;
        this.totalhp = totalhp;
        this.atk = atk;
        this.def = def;
        this.spa = spa;
        this.spd = spd;
        this.spe = spe;
        this.tipo1 = tipo1;
        this.tipo1n = tipo1n;
        this.tipo2 = tipo2;
        this.tipo2n = tipo2n;
        ataques = new ArrayList <>();
        ataques.add(a1);
        ataques.add(a2);
        ataques.add(a3);
        ataques.add(a4);
    }

    public String getNombre() {
        return nombre;
    }

    public int getHp() {
        return hp;
    }

    public int getTotalhp() {
        return totalhp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getSpa() {
        return spa;
    }

    public int getSpd() {
        return spd;
    }

    public int getSpe() {
        return spe;
    }

    public int getTipo1() {
        return tipo1;
    }

    public int getTipo2() {
        return tipo2;
    }

    public Ataque getA1() {
        return ataques.get(0);
    }

    public Ataque getA2() {
        return ataques.get(1);
    }

    public Ataque getA3() {
        return ataques.get(2);
    }

    public Ataque getA4() {
        return ataques.get(3);
    }

    public tipos getTipo1n() {
        return tipo1n;
    }

    public tipos getTipo2n() {
        return tipo2n;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int compareTo(Pokemon o) {
        return this.getNombre().compareTo(o.getNombre());
    }

    @Override
    public String toString() {
        return this.getNombre() + " (" + this.getTipo1n() + "/" + this.getTipo2n() + ")"
                + "\n  HP: "+hp+" Atk: "+atk+" Def: "+def+" SpA: "+spa+" SpD: "+spd+" Vel: "+spe
                + "\n  Ataque 1: " + this.getA1().getNombre() + " - Potencia: " + this.getA1().getPotencia() + " - Tipo: " + this.getA1().getTipo()
                + "\n  Ataque 2: " + this.getA2().getNombre() + " - Potencia: " + this.getA2().getPotencia() + " - Tipo: " + this.getA2().getTipo()
                + "\n  Ataque 3: " + this.getA3().getNombre() + " - Potencia: " + this.getA3().getPotencia() + " - Tipo: " + this.getA3().getTipo()
                + "\n  Ataque 4: " + this.getA4().getNombre() + " - Potencia: " + this.getA4().getPotencia() + " - Tipo: " + this.getA4().getTipo();
    }

    public void ataque(Ataque n, Pokemon x) { //Esta es la formula que se utiliza para que un pokemon ataque a otro
        Random rnd = new Random();
        double [][] tabla = {
        {1    ,2    ,1    ,1    ,1    ,1    ,1    ,0    ,1    ,1    ,1    ,1    ,1    ,1    ,1    ,1    ,1    ,1},
        {1    ,1    ,2    ,1    ,1    ,0.5  ,0.5  ,1    ,1    ,1    ,1    ,1    ,1    ,2    ,1    ,1    ,0.5  ,2},
        {1    ,0.5  ,1    ,1    ,0    ,2    ,0.5  ,1    ,1    ,1    ,1    ,0.5  ,2    ,1    ,2    ,1    ,1    ,1},
        {1    ,0.5  ,1    ,0.5  ,2    ,1    ,0.5  ,1    ,1    ,1    ,1    ,0.5  ,1    ,2    ,1    ,1    ,1    ,0.5},
        {1    ,1    ,1    ,0.5  ,1    ,0.5  ,1    ,1    ,1    ,1    ,2    ,2    ,0    ,1    ,2    ,1    ,1    ,1},
        {0.5  ,2    ,0.5  ,0.5  ,2    ,1    ,1    ,1    ,2    ,0.5  ,2    ,2    ,1    ,1    ,1    ,1    ,1    ,1}, 
        {1    ,0.5  ,2    ,1    ,0.5  ,2    ,1    ,1    ,1    ,2    ,1    ,0.5  ,1    ,1    ,1    ,1    ,1    ,1},
        {0    ,0    ,1    ,0.5  ,1    ,1    ,0.5  ,2    ,1    ,1    ,1    ,1    ,1    ,1    ,1    ,1    ,2    ,1},
        {0.5  ,2    ,0.5  ,0    ,2    ,0.5  ,0.5  ,1    ,0.5  ,2    ,1    ,0.5  ,1    ,0.5  ,0.5  ,0.5  ,1    ,0.5},
        {1    ,1    ,1    ,1    ,2    ,2    ,0.5  ,1    ,0.5  ,0.5  ,2    ,0.5  ,1    ,1    ,0.5  ,1    ,1    ,0.5},
        {1    ,1    ,1    ,1    ,1    ,1    ,1    ,1    ,0.5  ,0.5  ,0.5  ,2    ,2    ,1    ,0.5  ,1    ,1    ,1},
        {1    ,1    ,2    ,2    ,0.5  ,1    ,2    ,1    ,1    ,2    ,0.5  ,0.5  ,0.5  ,1    ,2    ,1    ,1    ,1},
        {1    ,1    ,0.5  ,1    ,2    ,1    ,1    ,1    ,0.5  ,1    ,1    ,1    ,0.5  ,1    ,1    ,1    ,1    ,1},
        {1    ,0.5  ,1    ,1    ,1    ,1    ,2    ,2    ,1    ,1    ,1    ,1    ,1    ,0.5  ,1    ,1    ,2    ,1},
        {1    ,2    ,1    ,1    ,1    ,2    ,1    ,1    ,2    ,2    ,1    ,1    ,1    ,1    ,0.5  ,1    ,1    ,1},
        {1    ,1    ,1    ,1    ,1    ,1    ,1    ,1    ,1    ,0.5  ,0.5  ,0.5  ,0.5  ,1    ,2    ,2    ,1    ,2},
        {1    ,2    ,1    ,1    ,1    ,1    ,2    ,0.5  ,1    ,1    ,1    ,1    ,1    ,0    ,1    ,1    ,0.5  ,2},
        {1    ,0.5  ,1    ,2    ,1    ,1    ,0.5  ,1    ,2    ,1    ,1    ,1    ,1    ,1    ,1    ,0    ,0.5  ,1}};

        float bonificacion;
        double efectividad, dmg;
        int variacion, dmgFinal;

        System.out.println(this.getNombre() + " ha usado " + n.getNombre());

        if (this.tipo1n == n.getTipo() || this.tipo2n == n.getTipo()) {
            bonificacion = 1.5f;
        } else {
            bonificacion = 1;
        }

        efectividad = 1 * tabla[x.getTipo1()][n.getTipoE()] * tabla[x.getTipo2()][n.getTipoE()];
        if (tabla[x.getTipo1()][n.getTipoE()] == 0.5 && tabla[x.getTipo2()][n.getTipoE()] == 0.5) {
            efectividad = 0.25;
        }
        if (tabla[x.getTipo1()][n.getTipoE()] == 2 && tabla[x.getTipo2()][n.getTipoE()] == 2) {
            efectividad = 4;
        }
        if (tabla[x.getTipo1()][n.getTipoE()] == 0.5 && tabla[x.getTipo2()][n.getTipoE()] == 2 || tabla[x.getTipo1()][n.getTipoE()] == 2 && tabla[x.getTipo2()][n.getTipoE()] == 0.5) {
            efectividad = 1;
        }

        variacion = rnd.nextInt(16) + 85;

        if (n.getCat() == Categoria.fisico) { //dependiendo de la categoria, se utiliza el ataque y defensa físico o especial de los pokemon. 
            dmg = (0.01 * bonificacion * efectividad * variacion * (((0.2 * 50 + 1) * this.getAtk() * n.getPotencia()) / (25 * x.getDef()) + 2))/1.75;
        } else {
            dmg = (0.01 * bonificacion * efectividad * variacion * (((0.2 * 50 + 1) * this.getSpa() * n.getPotencia()) / (25 * x.getSpd()) + 2))/1.75;
        }

        dmgFinal = (int) dmg;

        x.hp = x.getHp() - dmgFinal;

        if (x.hp < 0) {
            x.hp = 0;
        }

        if (efectividad == 0) {
            System.out.println(n.getNombre() + " no afecta a " + x.getNombre());
        }
        if (efectividad == 0.25) {
            System.out.println("Es muy poco eficaz.");
        }
        if (efectividad == 0.5) {
            System.out.println("Es poco eficaz.");
        }
        if (efectividad == 2) {
            System.out.println("¡Es muy eficaz!");
        }
        if (efectividad == 4) {
            System.out.println("¡Es supereficaz!");
        }

        if (x.getHp() == 0) {
            System.out.println(x.getNombre() + " se ha debilitado.");
            System.out.println();
        }

    }

    public void atacar(Pokemon rival, int eleccion) {  //Se calula la vida del rival dependiendo del ataque que hemos seleccionado
        switch (eleccion) {
            case 1:
                this.ataque(this.getA1(), rival);
                break;
            case 2:
                this.ataque(this.getA2(), rival);
                break;
            case 3:
                this.ataque(this.getA3(), rival);
                break;
            case 4:
                this.ataque(this.getA4(), rival);
                break;
        }
    }

}
