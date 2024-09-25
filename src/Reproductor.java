
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Reproductor {
    private Clip clip;
    private String nombreArchivo;

    public Reproductor(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    

    public void reproducirMusica() {
        try {
            File f = new File(nombreArchivo);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(f));
            FloatControl volumen = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumen.setValue(-18.0f);
            clip.start();
        } catch (Exception e) {
            System.err.println("Error playing audio: " + e.getMessage());
        }
    }

    public void pararMusica() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}