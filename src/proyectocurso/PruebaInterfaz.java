/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocurso;

import com.sun.jna.Native;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import com.sun.jna.NativeLibrary;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.mrl.FileMrl;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import proyectocurso.modelo.ficheros.*;



//CLASE DE PRUEBA TUTORIAL.
/**
 *
 * @author Arturo
 */
public class PruebaInterfaz extends JFrame 
{
    private JPanel imagen;
    private JLabel imagenLabel,texto1,texto2,texto3,texto4,texto5;
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
   
    public PruebaInterfaz()
    {
        super("PRUEBA IMAGENES");
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Archivos de programa/VideoLAN/VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        this.setLayout(new GridLayout(1,1));
        this.setSize(600, 300); 
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(imagen = new JPanel());
        this.add(imagen = new JPanel());
        this.add(texto1 = new JLabel());
        this.add(texto2 = new JLabel());
        this.add(texto3 = new JLabel());
        this.add(texto4 = new JLabel());
        this.add(texto5 = new JLabel());
    }
    
    public PruebaInterfaz(ManejadorPadre archivo)
    {
        super("PRUEBA IMAGENES");
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Archivos de programa/VideoLAN/VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        
        
        this.setLayout(new GridLayout(1,1));
        this.setSize(600, 300); 
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(imagen = new JPanel());
        this.add(texto1 = new JLabel());
        this.add(texto2 = new JLabel());
        this.add(texto3 = new JLabel());
        this.add(texto4 = new JLabel());
        this.add(texto5 = new JLabel());
        //this.setCursor();
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();


        this.add(mediaPlayerComponent);
        
        mediaPlayerComponent.getMediaPlayer().playMedia(archivo.getRuta());
    }
    
    public void cargarSonido(String dato)
    {
        this.texto1.setText(dato);
    }        
    
    public void cargarImagen(BufferedImage img)
    {
        imagen.add(imagenLabel = new JLabel());
        imagenLabel.setIcon(new ImageIcon(img));   
    }
    
    public void cargarMetadato(String dato1, String dato2, String dato3, String dato4, String dato5)
    {
        this.texto1.setText(dato1);
        this.texto2.setText(dato2);
        this.texto3.setText(dato3);
        this.texto4.setText(dato4);
        this.texto5.setText(dato5);
    }
}

/*    http://www.capricasoftware.co.uk/vlcj/tutorial1.php
public class Tutorial1B 
{
    public static void main(String[] args) 
    {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "/home/linux/vlc/install/lib");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
    }
}*/