package proyectocurso.modelo.ficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Clase para abrir archivos que no sean audio, vídeo, imágenes o texto. También se puede acceder a sus metadatos.
 * @author Arturo y y Toni
 */
public class OtrosManejador extends ManejadorPadre<File>
{
    private File fichero;
    
    public OtrosManejador(AbrirArchivo archivo)
    {
        super(archivo);//El proceso de encontrar el archivo lo hace la clase abstracta "ManejadorPadre"
        this.info = new MediaInfo();
        this.fichero = this.ficheroTemporal;
        this.extraerMetadatos();//Guarda los metadatos en la clase.
    }
    
    public OtrosManejador(String direccion)
    {
        super(direccion);//El proceso de encontrar el archivo lo hace la clase abstracta "ManejadorPadre"
        this.info = new MediaInfo();
        this.fichero = this.ficheroTemporal;
        if(this.ficheroTemporal.exists())this.extraerMetadatos();//Guarda los metadatos en la clase.
    }
    
    @Override
    public File getArchivo() 
    {
        return this.fichero;
    }

    @Override
    protected final void extraerMetadatos() 
    {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getMetadato(String metadato) 
    {
        if(metadato.equals("nombre"))
            return this.Metadatos.get(0);
        else if(metadato.equals("extension"))
            return this.Metadatos.get(1);
        else if(metadato.equals("tam"))
            return this.Metadatos.get(2);
        return "No encontrado";
    }

    @Override
    public String getMetadato(int metadato) 
    {
        switch (metadato)
        {
            case 0:return this.getMetadato("nombre");
            case 1:return this.getMetadato("extension");
            case 2:return this.getMetadato("tam");
            default: return "No encontrado";
        }
    }

    @Override
    public void crearFichero(String ruta) 
    {
        FileInputStream from = null;
        FileOutputStream to = null;
        File toFile = new File(ruta + this.getMetadato("nombre") + this.getMetadato("extension"));
        
        try 
        {
            from = new FileInputStream(this.fichero);
            to = new FileOutputStream(toFile);
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = from.read(buffer)) != -1)
                to.write(buffer, 0, bytesRead); // write
        }
        catch (IOException e) {}
        finally 
        {
            if (from != null)
            try {from.close();} 
            catch (IOException e) {}
            if (to != null)
            try {to.close();} 
            catch (IOException e) {}
        }
    }
    
}
