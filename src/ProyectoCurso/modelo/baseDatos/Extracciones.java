package ProyectoCurso.modelo.baseDatos;

import java.io.File;
import java.util.ArrayList;

public class Extracciones 
{
    private BD BDextracciones;
    private ArrayList<Extraccion> proyectos;
    private ArrayList<Integer> idProyecto;
    
    public Extracciones(String ruta)
    {        
        this.BDextracciones = new BD(ruta);
        this.idProyecto = new ArrayList<Integer>();
        this.proyectos = new ArrayList<Extraccion>();
        int[] totalExtracciones;
        File bd = new File(ruta);
        if(bd.length() > 2000)
        {
            totalExtracciones = this.listadoExtracciones();
            for(int i=0;i<totalExtracciones.length;i++)
            {
                this.proyectos.add(new Extraccion(totalExtracciones[i],ruta));
                this.idProyecto.add(totalExtracciones[i]);
            }
        }
    }
    
    /**
     * Método que crea la tabla enlaces imagen junto a sus índices.
     */
    public void crearTablaEnlacesImagen()
    {
        String tablaEnlacesImagen = "CREATE TABLE enlaces_imagen (id_enl_imagen  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , id_proyecto INTEGER, nombre_enlace TEXT)";
        String[] valor;
        // Array que guarda el nombre de los campos sobre los que se van a hacer los índices
        valor = new String[2];
        valor[0] = "id_enl_imagen";        
        valor[1] = "id_proyecto";
        BDextracciones.crearTabla("enlaces_imagen", tablaEnlacesImagen, valor);
    }
    
    /**
     * Método que crea la tabla enlaces audios junto a sus índices.
     */
    public void crearTablaEnlacesAudio()
    {
        String tablaEnlacesAudios = "CREATE TABLE enlaces_audio (id_enl_audio  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , id_proyecto INTEGER, nombre_enlace TEXT)";
        String[] valor;
        // Array que guarda el nombre de los campos sobre los que se van a hacer los índices
        valor = new String[2];
        valor[0] = "id_enl_audio";        
        valor[1] = "id_proyecto";
        BDextracciones.crearTabla("enlaces_audio", tablaEnlacesAudios, valor);
    }
    
    /**
     * Método que crea la tabla enlaces videos junto a sus índices.
     */
    public void crearTablaEnlacesVideo()
    {
        String tablaEnlacesVideos = "CREATE TABLE enlaces_video (id_enl_video  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , id_proyecto INTEGER, nombre_enlace TEXT)";
        String[] valor;
        // Array que guarda el nombre de los campos sobre los que se van a hacer los índices
        valor = new String[2];
        valor[0] = "id_enl_video";        
        valor[1] = "id_proyecto";
        BDextracciones.crearTabla("enlaces_video", tablaEnlacesVideos, valor);
    }
    
    /**
     * Método que crea la tabla enlaces documentos junto a sus índices.
     */
    public void crearTablaEnlacesDocumento()
    {
        String tablaEnlacesDocumentos = "CREATE TABLE enlaces_documento (id_enl_documento  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , id_proyecto INTEGER, nombre_enlace TEXT)";
        String[] valor;
        // Array que guarda el nombre de los campos sobre los que se van a hacer los índices
        valor = new String[2];
        valor[0] = "id_enl_documento";        
        valor[1] = "id_proyecto";
        BDextracciones.crearTabla("enlaces_documento", tablaEnlacesDocumentos, valor);
    }
    
    /**
     * Método que crea la tabla enlaces otros junto a sus índices.
     */
    public void crearTablaEnlacesOtro()
    {
        String tablaEnlacesOtro = "CREATE TABLE enlaces_otro (id_enl_otro  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , id_proyecto INTEGER, nombre_enlace TEXT)";
        String[] valor;
        // Array que guarda el nombre de los campos sobre los que se van a hacer los índices
        valor = new String[2];
        valor[0] = "id_enl_otro";        
        valor[1] = "id_proyecto";
        BDextracciones.crearTabla("enlaces_otro", tablaEnlacesOtro, valor);
    }
    
    /**
     * Método que crea la tabla enlaces recorridos junto a sus índices.
     */
    public void crearTablaEnlacesRecorridos()
    {
        String tablaEnlacesRecorrido = "CREATE TABLE enlaces_recorrido (id_enl_recorrido  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , id_proyecto INTEGER, nombre_enlace TEXT)";
        String[] valor;
        // Array que guarda el nombre de los campos sobre los que se van a hacer los índices
        valor = new String[2];
        valor[0] = "id_enl_recorrido";        
        valor[1] = "id_proyecto";
        BDextracciones.crearTabla("enlaces_recorrido", tablaEnlacesRecorrido, valor);
    }
    
    /**
     * Método que crea la tabla enlaces sin recorrer junto a sus índices.
     */
    public void crearTablaEnlacesSinRecorrer()
    {
        String tablaEnlacesSinRecorrer = "CREATE TABLE enlaces_sin_recorrer (id_enl_sin_recorrer  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , id_proyecto INTEGER, nombre_enlace TEXT)";
        String[] valor;
        // Array que guarda el nombre de los campos sobre los que se van a hacer los índices
        valor = new String[2];
        valor[0] = "id_enl_sin_recorrer";        
        valor[1] = "id_proyecto";
        BDextracciones.crearTabla("enlaces_sin_recorrer", tablaEnlacesSinRecorrer, valor);
    }
    
     /**
     * Método que crea la tabla proyecto junto a sus índices.
     */
    public void crearTablaPyoyecto()
    {
        String tablaProyecto = "CREATE TABLE proyecto (id_proyecto  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , nombre_proyecto TEXT)";
        String[] valor;
        // Array que guarda el nombre de los campos sobre los que se van a hacer los índices
        valor = new String[1];
        valor[0] = "id_proyecto";        
        BDextracciones.crearTabla("proyecto", tablaProyecto, valor);
    }
    
    public int[] listadoExtracciones()
    {
        ArrayList devolver = new ArrayList();
        String[] nombre,campos;
        int[] solucion;
        campos = new String[]{"id_proyecto","nombre_proyecto"};
        devolver = BDextracciones.mostrarDatos("proyecto", campos);
        solucion = new int[devolver.size()];
        for(int i=0;i<devolver.size();i++)
        {
            nombre = (String[])devolver.get(i);
            solucion[i] = Integer.parseInt(nombre[0]);
        }
        return solucion;
    }
    
    public void guardarExtraccion(String ruta,String nom_pro, ArrayList enl_img, ArrayList enl_aud, ArrayList enl_vid, ArrayList enl_doc, ArrayList enl_otr, ArrayList enl_rec, ArrayList enl_sin_rec)
    {
        String[] campos = new String[2];        
        campos[0]="id_proyecto";campos[1]="nombre_proyecto";
        this.proyectos.add(new Extraccion(ruta,nom_pro,enl_img,enl_aud,enl_vid,enl_doc,enl_otr,enl_rec, enl_sin_rec)); 
        this.idProyecto.add(this.BDextracciones.extraerUltimoRegistro("proyecto", campos));
    }
    
    public void eliminarExtraccion(int pos)
    {
        this.proyectos.get(pos).eliminarProyectoExtraccion();
        this.idProyecto.remove(pos);
        this.proyectos.remove(pos);
    }
    
    public void modificarExtraccion(String ruta,String nom_pro, ArrayList enl_img, ArrayList enl_aud, ArrayList enl_vid, ArrayList enl_doc, ArrayList enl_otr, ArrayList enl_rec, ArrayList enl_sin_rec)
    {
        int cont=0;
        while (cont < this.proyectos.size() && this.proyectos.get(cont).getNombreProyecto() != nom_pro)
        {
            cont++;
        }
        this.proyectos.get(cont).eliminarProyectoExtraccion();
        this.idProyecto.remove(cont);
        this.proyectos.remove(cont);
        this.guardarExtraccion(ruta, nom_pro, enl_img, enl_aud, enl_vid, enl_doc, enl_otr, enl_rec, enl_sin_rec);
    }
    
    public ArrayList getEnlacesSinRecorrer(int posicion)
    {  
        return this.proyectos.get(posicion).getEnlacesSinRecorrer();
    }
    
    public ArrayList getEnlacesRecorridos(int posicion)
    {  
        return this.proyectos.get(posicion).getEnlacesRecorridos();
    }
    
    public ArrayList getEnlacesOtros(int posicion)
    {  
        return this.proyectos.get(posicion).getEnlacesOtros();
    }
    
    public ArrayList getEnlacesDocumentos(int posicion)
    {  
        return this.proyectos.get(posicion).getEnlacesDocumentos();
    }
    
    public ArrayList getEnlacesVideos(int posicion)
    {  
        return this.proyectos.get(posicion).getEnlacesVideos();
    }
    
    public ArrayList getEnlacesImagenes(int posicion)
    {  
        return this.proyectos.get(posicion).getEnlacesImagenes();
    }
    
    public ArrayList getEnlacesAudios(int posicion)
    {  
        return this.proyectos.get(posicion).getEnlacesAudios();
    }
    
    public static void main(String[] args)
    {
        String ruta = "C:\\Documents and Settings\\Arturo\\Escritorio\\BD.sqlite";
        Extracciones ext = new Extracciones(ruta);
        
                
//        ext.crearTablaEnlacesAudio();
//        ext.crearTablaEnlacesDocumento();
//        ext.crearTablaEnlacesImagen();
//        ext.crearTablaEnlacesOtro();
//        ext.crearTablaEnlacesRecorridos();
//        ext.crearTablaEnlacesSinRecorrer();
//        ext.crearTablaEnlacesVideo();
//        ext.crearTablaPyoyecto();
//        
//        ArrayList datosImg = new ArrayList();
//        datosImg.add("www.facebook.com/images/arturo.jpg");
//        datosImg.add("www.facebook.com/images/caca.jpg");
//        
//        ArrayList datosSon = new ArrayList();
//        datosSon.add("www.facebook.com/audio/arturo.mp3");
//        datosSon.add("www.facebook.com/audio/caca.wma");
//        
//        ArrayList datosVid = new ArrayList();
//        datosVid.add("www.facebook.com/video/arturo.mp4");
//        datosVid.add("www.facebook.com/video/caca.mkv");
//        
//        ArrayList datosDoc = new ArrayList();
//        datosDoc.add("www.facebook.com/documentos/arturo.org");
//        datosDoc.add("www.facebook.com/documentos/caca.pdf");
//        
//        ArrayList datosOtr = new ArrayList();
//        datosOtr.add("www.facebook.com/otros/arturo.iso");
//        datosOtr.add("www.facebook.com/otros/caca.rar");
//        
//        ArrayList datosRec = new ArrayList();
//        datosRec.add("www.facebook.com/images");
//        datosRec.add("www.facebook.com/audio");
//        datosRec.add("www.facebook.com/video");
//        datosRec.add("www.facebook.com/documentos");
//        datosRec.add("www.facebook.com/otros");
//        
//        ArrayList datosSinRec = new ArrayList();
//        datosSinRec.add("www.facebook.com/sinRecorrer");
//        
//        ext.guardarExtraccion(ruta, "proyectoPrueba2", datosImg, datosSon, datosVid, datosDoc, datosOtr, datosRec, datosSinRec);
                
        ext.eliminarExtraccion(0);
    }
}
