
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Lector {
    private FileReader fr;
    private FileInputStream fis;
    private ObjectInputStream ois;
    
    private ObjectOutputStream oos;
    private FileOutputStream fos;
    
    
    public Lector(){}
    
    public void abrirFileReader(String archivo) {
        try {
            fr = new FileReader(new File(archivo));
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public void leerFileReader() {
        try {
            int c = fr.read();
            while (c != -1) {
                System.out.print((char) c);
                c = fr.read();
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public void cerrarFileReader() {
        if (fr != null) {
            try {
                fr.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
    }
    public void abrirLecturaObjeto(String archivo) {
        try {
            fis = new FileInputStream(archivo);
            ois = new ObjectInputStream(fis);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void guardarEntrenadorDeFicheroEnArrayList(ArrayList <EntrenadorBOT> array) {
        Boolean finArchivo = false;
        while (!finArchivo) {
            try {
                EntrenadorBOT ent = (EntrenadorBOT) ois.readObject();
                array.add(ent);
            } catch (EOFException ex) {
                finArchivo = true;

            } catch (FileNotFoundException ex) {
                System.out.println(ex.toString());
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.toString());
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
    }
    
    public void cerrarLecturaObjetos() {
        if (ois != null)
            try {
            ois.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void leerAtaques() {
        Boolean finArchivo = false;
        while (!finArchivo) {
            try {
                Ataque atk = (Ataque) ois.readObject();
                System.out.println(atk);
                System.out.println();
            } catch (EOFException ex) {
                finArchivo = true;

            } catch (FileNotFoundException ex) {
                System.out.println(ex.toString());
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.toString());
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
    }
    
    public void leerPokemons() {
        Boolean finArchivo = false;
        while (!finArchivo) {
            try {
                Pokemon pk = (Pokemon) ois.readObject();
                System.out.println(pk);
                System.out.println();
            } catch (EOFException ex) {
                finArchivo = true;

            } catch (FileNotFoundException ex) {
                System.out.println(ex.toString());
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.toString());
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
    }
    
    public void guardarPokemons(ArrayList <Pokemon> al) {
        Boolean finArchivo = false;
        while (!finArchivo) {
            try {
                Pokemon pk = (Pokemon) ois.readObject();
                al.add(pk);
            } catch (EOFException ex) {
                finArchivo = true;

            } catch (FileNotFoundException ex) {
                System.out.println(ex.toString());
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.toString());
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
    }
    
}
