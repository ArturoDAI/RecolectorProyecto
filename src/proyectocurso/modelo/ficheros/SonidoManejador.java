package proyectocurso.modelo.ficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Clase para abrir archivos de audio. También se puede acceder a sus metadatos.
 * @author Arturo y Toni
 */
public class SonidoManejador extends ManejadorPadre<File>
{
    private File audio;
    
    public SonidoManejador(AbrirArchivo archivo)
    {
        super(archivo);//El proceso de encontrar el archivo lo hace la clase abstracta "ManejadorPadre"
        this.info = new MediaInfo();
        this.audio = this.ficheroTemporal;
        this.extraerMetadatos();//Guarda los metadatos en la clase.
    }
    
    public SonidoManejador(String direccion)
    {
        super(direccion);//El proceso de encontrar el archivo lo hace la clase abstracta "ManejadorPadre"
    }
    
    @Override
    public File getArchivo() 
    {
        return this.audio;
    }

    @Override
    protected final void extraerMetadatos() 
    {
        this.info.open(audio);
        //>>ASIGNAR METADATOS DE VIDEO GENERAL
        
        //>>3 Duración
            Integer segundo = Integer.parseInt(info.get(MediaInfo.StreamKind.Audio, 0,"Duration",MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name)) / 1000;
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
        //>>4 Bitrate
            Integer bitrate = Integer.parseInt(info.get(MediaInfo.StreamKind.Audio, 0,"BitRate",MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name)) / 1000;
        this.Metadatos.add(bitrate.toString() + " KBPS");   
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
        else if(metadato.equals("bitrate"))
            return this.Metadatos.get(4);
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
            case 3:return this.getMetadato("duracion");
            case 4:return this.getMetadato("bitrate");
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
            from = new FileInputStream(this.audio);
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
