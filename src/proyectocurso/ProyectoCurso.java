/*z
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocurso;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;
import proyectocurso.*;
import proyectocurso.modelo.ficheros.*;
/**
 *
 * @author Arturo
 */
public class ProyectoCurso {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //"http://www.oracleimg.com/us/assets/oralogo-small.gif"
        //"C://oracle.gif"
        //final ImagenManejador imagen = new ImagenManejador("C://oracle.gif");
        String bg = "http://s3.amazonaws.com/ksr/projects/171189/video-106773-h264_high.mp4";
        String videoE = "C://Vídeo003.mp4";
        String bb = "M://Black Books - Season 1//Black Books S01E05 - The Big Lock-Out.mkv";
        String cancion = "M://Golden Greats//Golden Greats - Cd 1//01 - Duke Ellington - Golden Greats - Cd 1 - Slippery Horn.mp3";
        String canpeste = "C://06 - There Is A Light That Never Goes Out.mp3";
        String blind ="C:\\blind.mid";
        DescargarArchivo archivo = new DescargarArchivo(bg);
        final VideoManejador video = new VideoManejador(new AbrirArchivo(archivo.getRuta()));
        //final SonidoManejador sonido = new SonidoManejador(cancion);
        SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            PruebaInterfaz prue = new PruebaInterfaz(video);
            //prue.cargarImagen(imagen.getArchivo());
            //prue.cargarMetadato(imagen.getMetadato(0),imagen.getMetadato(1),imagen.getMetadato(2),imagen.getMetadato(3),imagen.getMetadato(5));
            //imagen.crearFichero("C://Documents and Settings//Arturo//Escritorio//");
            //sonido.crearFichero("C://Documents and Settings//Arturo//Escritorio//");
        }
        });
        //new PruebaInterfaz();//.cargarImagen(imagen.getArchivo(),imagen.getMetadato(3));
    }
}
