package proyectocurso.modelo.ficheros;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Intenta abrir archivos.
 * @author Arturo y Toni.
 */
public class AbrirArchivo 
{
    private File fichero;
    private String ruta;
    private String nombreFichero;
    private String extFichero;
    
    public AbrirArchivo(String rut)
    {
        this.ruta = rut;
        this.nombreFichero = rut.substring(rut.lastIndexOf("/")+1,rut.lastIndexOf("."));
        this.extFichero = rut.substring(rut.lastIndexOf("."));
        this.fichero = new File(rut);
        //try {this.fichero = File.createTempFile(this.nombreFichero,this.extFichero);} 
        //catch (IOException ex) {Logger.getLogger(AbrirArchivo.class.getName()).log(Level.SEVERE, null, ex);}
        this.fichero.deleteOnExit();
    }
    
    public File getArchivo()
    {return this.fichero;}
    
    public String getRuta()
    {return this.ruta;}
    
    public String getNombre()
    {return this.nombreFichero;}
    
    public String getExtension()
    {return this.extFichero;}
}
