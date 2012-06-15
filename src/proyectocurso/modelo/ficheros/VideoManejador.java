package proyectocurso.modelo.ficheros;

import com.sun.corba.se.spi.copyobject.CopierManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para abrir vídeos. También se puede acceder a sus metadatos.
 * @author Arturo y Toni
 */
public final class VideoManejador extends ManejadorPadre<File>
{
    private File video;
    
    public VideoManejador(AbrirArchivo archivo)
    {
        super(archivo);//El proceso de encontrar el archivo lo hace la clase abstracta "ManejadorPadre"
        this.info = new MediaInfo();
        this.video = this.ficheroTemporal;
        if(this.ficheroTemporal.exists())this.extraerMetadatos();//Guarda los metadatos en la clase.
    }
    
    public VideoManejador(String direccion)
    {
        super(direccion);//El proceso de encontrar el archivo lo hace la clase abstracta "ManejadorPadre"
    }
    
    @Override
    public File getArchivo() 
    {
        return this.video;
    }

    @Override
    protected void extraerMetadatos() 
    {
        this.info.open(this.ficheroTemporal);
        //>>ASIGNAR METADATOS DE VIDEO GENERAL
        
        //>>3 Duración
            Integer segundo = Integer.parseInt(info.get(MediaInfo.StreamKind.Video, 0,"Duration",MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name)) / 1000;
            Integer hora = 0, minuto = 0;
            String duracion="";

            //Parsear la duración en segundos a String, calculando si llega a minutos y a horas.
            while(segundo > 60)
            {
                segundo = segundo - 60;
                minuto++;
            }
            while(minuto > 60)
            {
                minuto = minuto - 60;
                hora++;
            }
            if(hora!=0)
                duracion += hora.toString() + "h ";
            if(minuto!=0)
                duracion += minuto.toString() + "min ";
            if(segundo!=0)
                duracion += segundo.toString() + "seg ";
        this.Metadatos.add(duracion);
        
        //>>4 Resolución
            String resolucion = info.get(MediaInfo.StreamKind.Video, 0,"Width",MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
            resolucion = resolucion + " x " + info.get(MediaInfo.StreamKind.Video, 0,"Height",MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        this.Metadatos.add(resolucion);
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
        else if(metadato.equals("duracion"))
            return this.Metadatos.get(3);
        else if(metadato.equals("resolucion"))
            return this.Metadatos.get(4);
        else if(metadato.equals("ruta"))
            return this.ruta;
        return "No encontrado.";
    }

    @Override
    public String getMetadato(int metadato) 
    {
        switch (metadato)
        {
            case 0:return this.getMetadato("nombre");
            case 1:return this.getMetadato("extension");
            case 2:return this.getMetadato("tam");
            case 3:return this.getMetadato("duracion");
            case 4:return this.getMetadato("resolucion");
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
            from = new FileInputStream(this.video);
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
