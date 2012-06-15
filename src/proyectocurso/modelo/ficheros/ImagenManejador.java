package proyectocurso.modelo.ficheros;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Clase para abrir imagenes. También se puede acceder a sus metadatos.
 * @author Arturo y Toni
 */
public class ImagenManejador extends ManejadorPadre<BufferedImage> 
{
    private BufferedImage imagen;

    /**
     * Carga la imagen de forma local o desde internet.
     * @param direccion La ruta donde se haya el archivo.
     */
    public ImagenManejador(AbrirArchivo archivo)
    {
        super(archivo);//El proceso de encontrar el archivo lo hace la clase abstracta "ManejadorPadre"
        this.info = new MediaInfo();
        try {imagen = ImageIO.read(this.ficheroTemporal);} 
        catch (IOException ex) {Logger.getLogger(ImagenManejador.class.getName()).log(Level.SEVERE, null, ex);}
        if(this.ficheroTemporal.exists())this.extraerMetadatos();//Guarda los metadatos en la clase.
    }
    
    public ImagenManejador(String direccion)
    {
        super(direccion);//El proceso de encontrar el archivo lo hace la clase abstracta "ManejadorPadre"
 
    }

    @Override
    public BufferedImage getArchivo() 
    {
        return this.imagen;
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
        else if(metadato.equals("ancho"))
            return this.Metadatos.get(3);
        else if(metadato.equals("alto"))
            return this.Metadatos.get(4);
        else if(metadato.equals("formato"))
            return this.Metadatos.get(5);
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
            case 3:return this.getMetadato("ancho");
            case 4:return this.getMetadato("alto");
            case 5:return this.getMetadato("formato");
            default: return "No encontrado";
        }
    }
    
    @Override
    protected final void extraerMetadatos() 
    {
        this.info.open(this.ficheroTemporal);
        //>>ASIGNAR METADATOS DE IMAGEN GENERAL
        
        //3>> Ancho
        this.Metadatos.add(info.get(MediaInfo.StreamKind.Image, 0,"Width",MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name));
        //4>> Alto
        this.Metadatos.add(info.get(MediaInfo.StreamKind.Image, 0,"Height",MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name));
        //5>> Formato
        this.Metadatos.add(info.get(MediaInfo.StreamKind.Image, 0,"Format",MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name));
    }

    @Override
    public void crearFichero(String ruta) 
    {
        try 
        {
            File outputfile = new File(ruta + this.getMetadato("nombre") + this.getMetadato("extension"));
            ImageIO.write(this.imagen,this.getMetadato("formato"), outputfile);
        } catch (IOException e) {
          
        }
    }
}
