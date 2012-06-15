package proyectocurso.modelo.ficheros;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Descargar un archivo según la dirección provista.
 * @author Arturo y Toni.
 */
public class DescargarArchivo 
{
    private String direccion;
    private File ficheroTemporal;
    
    public DescargarArchivo(String url)
    {
        String nombreFichero = url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
        String extFichero = url.substring(url.lastIndexOf("."));
        InputStream in = null;
        FileOutputStream fout = null;
        
        try//Guarda el flujo de datos de internet en memoria. 
        { 
            this.ficheroTemporal = new File(nombreFichero + extFichero);
            this.direccion = this.ficheroTemporal.getAbsolutePath();
            
            in = new URL(url).openStream();

            fout = new FileOutputStream(ficheroTemporal);  
            byte[] buf = new byte[1024];  
            int len;  
            while((len = in.read(buf)) != -1) 
            {fout.write(buf, 0, len);}  
        }
        catch(IOException e)
        {}
        finally 
        {  
            try 
            {
                if(in != null) 
                {in.close();}
                if(fout != null) 
                {fout.close();} 
            } 
            catch (IOException ex) 
            {Logger.getLogger(DescargarArchivo.class.getName()).log(Level.SEVERE, null, ex);}
        }
    }
    
    public String getRuta()
    {
        return this.direccion;
    }
}
