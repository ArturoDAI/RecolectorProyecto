package proyectocurso.modelo.ficheros;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Clase base que usarán el resto de manejadores de archivo para abrirlos y ver su metadatos.
 * @author Arturo y Toni
 */
public abstract class ManejadorPadre<tipoArchivo>
{
    protected MediaInfo info;//Clase para leer metadatos de los archivos.
    protected File ficheroTemporal;//Objeto sobre el cuál ver metadatos y cargar los archivos abiertos.
    protected String ruta;//Indica la dirección del archivo.
    protected ArrayList<String> Metadatos;//Contendrá todos los metadatos en formato String.
    
    /**
     * Intenta abrir el archivo, ya sea por internet o de forma local.
     * @param direccion Ruta en la que se encuentra el archivo a abrir (Internet o local)
     * @param descargar Si es true, descargará el archivo en caso de no tenerlo de forma local.
     */
    public ManejadorPadre(AbrirArchivo archivo)
    {
        this.info = new MediaInfo();
        this.Metadatos = new ArrayList<String>();
        this.ficheroTemporal = archivo.getArchivo();
        this.ruta = this.ficheroTemporal.getAbsolutePath();
        
        this.Metadatos.add(archivo.getNombre());//0 Nombre de fichero
        this.Metadatos.add(archivo.getExtension());//1 Extensión Fichero
        Long tam = this.ficheroTemporal.length();
        this.Metadatos.add(this.calcularTam(tam.doubleValue()));//2 Tamaño
    }
    
    public ManejadorPadre(String archivo)
    {
        this.ruta = archivo;
        this.info = new MediaInfo();
        this.Metadatos = new ArrayList<String>();
        this.Metadatos.add(archivo.substring(archivo.lastIndexOf("/")+1,archivo.lastIndexOf(".")));//0 Nombre de fichero
        this.Metadatos.add(archivo.substring(archivo.lastIndexOf(".")));
    }
    
    /**
     * Devolverá el objeto con el archivo específico, sea imagen, vídeo, etc.
     * @return El archivo que contenga la clase.
     */
    abstract public tipoArchivo getArchivo();
    
    /**
     * El constructor guardará los metadatos del archivo específicos del formato antes de finalizar su tarea.
     */
    abstract protected void extraerMetadatos();
    
    /**
     * Devuelve el metadato indicado por parámetro de tipo string.
     * @param metadato Metadato a devolver.
     * @return metadato en String.
     */
    abstract public String getMetadato(String metadato);
    
    /**
     * Devuelve el metadato indicado por parámetro numérico.
     * @param metadato Metadato a devolver.
     * @return metadato en String.
     */
    abstract public String getMetadato(int metadato);
    
    /**
     * Devuelve un string con el tamaño del archivo
     * @param tam Tamaño del archivo en Bytes
     * @return tamaño del archivo en la medida precisa.
     */
    private String calcularTam(Double tam)
    {
        int cont = 2;
        while(cont != 0 & tam >= 1024)
        {
            cont--;
            tam = tam / 1024;
        }
        
        DecimalFormat df = new DecimalFormat("#.##");//Redondear
        
        if(cont==2)
            return df.format(tam) + " Bytes";
        else if(cont==1)
            return df.format(tam) + " KB";
        else if(cont==0)
            return df.format(tam) + " MB";
        return df.format(tam);
    }
    
    /**
     * Crea un archivo en el sistema idéntico al que contiene el objeto.
     * @param ruta Dirección donde se creará.
     */
    abstract public void crearFichero(String ruta);
    
    public String getRuta()
    {return this.ruta;}
}
