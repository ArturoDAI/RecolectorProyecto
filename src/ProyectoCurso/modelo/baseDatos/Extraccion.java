package ProyectoCurso.modelo.baseDatos;

import java.util.ArrayList;

public class Extraccion 
{
    private BD extraccion;
    private String ruta;
    private int id_proyecto;
    private String nombre_proyecto;
    private ArrayList enlaces_imagenes;
    private ArrayList enlaces_audios;
    private ArrayList enlaces_videos;
    private ArrayList enlaces_documentos;
    private ArrayList enlaces_otros;
    private ArrayList enlaces_recorridos;
    private ArrayList enlaces_sin_recorrer;
    
    private String[] camposProyecto;
    
    public Extraccion(int id, String ruta)
    {
        this.id_proyecto = id;
        this.ruta = ruta;
        this.extraccion = new BD(this.ruta);
        this.enlaces_imagenes = new ArrayList();
        this.enlaces_audios = new ArrayList();
        this.enlaces_videos = new ArrayList();
        this.enlaces_documentos = new ArrayList();
        this.enlaces_otros = new ArrayList();
        this.enlaces_recorridos = new ArrayList();
        this.enlaces_sin_recorrer = new ArrayList();
        this.abrirExtraccion(id);
    }
    
    public Extraccion(String ruta,String nom_pro, ArrayList enl_img, ArrayList enl_aud, ArrayList enl_vid, ArrayList enl_doc, ArrayList enl_otr, ArrayList enl_rec, ArrayList enl_sin_rec)
    {
        this.ruta = ruta;
        this.extraccion = new BD(this.ruta);
        this.enlaces_imagenes = new ArrayList(enl_img);
        this.enlaces_audios = new ArrayList(enl_aud);
        this.enlaces_videos = new ArrayList(enl_vid);
        this.enlaces_documentos = new ArrayList(enl_doc);
        this.enlaces_otros = new ArrayList(enl_otr);
        this.enlaces_recorridos = new ArrayList(enl_rec);
        this.enlaces_sin_recorrer = new ArrayList(enl_sin_rec);
        this.nombre_proyecto = nom_pro;
        this.camposProyecto = new String[2];        
        this.camposProyecto[0]="id_proyecto";this.camposProyecto[1]="nombre_proyecto";
        this.insertarExtraccion();
    }
    
    public void insertarExtraccion()
    {
        ArrayList nom = new ArrayList();
        nom.add(this.nombre_proyecto);
        extraccion.insertarRegistros("proyecto", nom);
        int ultimoReg = extraccion.extraerUltimoRegistro("proyecto", camposProyecto);
        this.id_proyecto=ultimoReg;
        
        ArrayList datos = new ArrayList();
        
        for(int i = 0 ; i < this.enlaces_imagenes.size() ; i++)
        {
            datos.add(ultimoReg);
            datos.add(this.enlaces_imagenes.get(i).toString());
            extraccion.insertarRegistros("enlaces_imagen", datos);
            datos.removeAll(datos);
        }
         
        for(int i = 0 ; i < this.enlaces_audios.size() ; i++)
        {
            datos.add(ultimoReg);
            datos.add(this.enlaces_audios.get(i));
            extraccion.insertarRegistros("enlaces_audio", datos);
            datos.removeAll(datos);
        } 

        for(int i = 0 ; i < this.enlaces_videos.size() ; i++)
        {
            datos.add(ultimoReg);
            datos.add(this.enlaces_videos.get(i));
            extraccion.insertarRegistros("enlaces_video", datos);
            datos.removeAll(datos);
        }
        
        for(int i = 0 ; i < this.enlaces_documentos.size() ; i++)
        {
            datos.add(ultimoReg);
            datos.add(this.enlaces_documentos.get(i));
            extraccion.insertarRegistros("enlaces_documento", datos);
            datos.removeAll(datos);
        }
        
        for(int i = 0 ; i < this.enlaces_otros.size() ; i++)
        {
            datos.add(ultimoReg);
            datos.add(this.enlaces_otros.get(i));
            extraccion.insertarRegistros("enlaces_otro", datos);
            datos.removeAll(datos);
        }
        
        for(int i = 0 ; i < this.enlaces_recorridos.size() ; i++)
        {
            datos.add(ultimoReg);
            datos.add(this.enlaces_recorridos.get(i));
            extraccion.insertarRegistros("enlaces_recorrido", datos);
            datos.removeAll(datos);
        }
        
        for(int i = 0 ; i < this.enlaces_sin_recorrer.size() ; i++)
        {
            datos.add(ultimoReg);
            datos.add(this.enlaces_sin_recorrer.get(i));
            extraccion.insertarRegistros("enlaces_sin_recorrer", datos);
            datos.removeAll(datos);
        }
    }
    
    public void abrirExtraccion(int id)
    {
        ArrayList nom;
        
        String[] enlace;
        String[] campos = new String[]{"id_enl_audio","id_proyecto","nombre_enlace"};
        nom = extraccion.busquedaBD("enlaces_audio", "indice_enlaces_audioid_proyecto", campos, id);        
        for(int i=0;i<nom.size();i++)
        {
            enlace = (String[])nom.get(i);
            this.enlaces_audios.add(enlace[2]);
        }
        
        campos = new String[]{"id_enl_imagen","id_proyecto","nombre_enlace"};
        nom = extraccion.busquedaBD("enlaces_imagen", "indice_enlaces_imagenid_proyecto", campos, id);
        for(int i=0;i<nom.size();i++)
        {
            enlace = (String[])nom.get(i);
            this.enlaces_imagenes.add(enlace[2]);
        }
        
        campos = new String[]{"id_enl_video","id_proyecto","nombre_enlace"};
        nom = extraccion.busquedaBD("enlaces_video", "indice_enlaces_videoid_proyecto", campos, id);
        for(int i=0;i<nom.size();i++)
        {
            enlace = (String[])nom.get(i);
            this.enlaces_videos.add(enlace[2]);
        }
        
        campos = new String[]{"id_enl_documento","id_proyecto","nombre_enlace"};
        nom = extraccion.busquedaBD("enlaces_documento", "indice_enlaces_documentoid_proyecto", campos, id);
        for(int i=0;i<nom.size();i++)
        {
            enlace = (String[])nom.get(i);
            this.enlaces_documentos.add(enlace[2]);
        }
        
        campos = new String[]{"id_enl_otro","id_proyecto","nombre_enlace"};
        nom = extraccion.busquedaBD("enlaces_otro", "indice_enlaces_otroid_proyecto", campos, id);
        for(int i=0;i<nom.size();i++)
        {
            enlace = (String[])nom.get(i);
            this.enlaces_otros.add(enlace[2]);
        }
        
        campos = new String[]{"id_enl_recorrido","id_proyecto","nombre_enlace"};
        nom = extraccion.busquedaBD("enlaces_recorrido", "indice_enlaces_recorridoid_proyecto", campos, id);
        for(int i=0;i<nom.size();i++)
        {
            enlace = (String[])nom.get(i);
            this.enlaces_recorridos.add(enlace[2]);
        }
        
        campos = new String[]{"id_enl_sin_recorrer","id_proyecto","nombre_enlace"};
        nom = extraccion.busquedaBD("enlaces_sin_recorrer", "indice_enlaces_sin_recorrerid_proyecto", campos, id);
        for(int i=0;i<nom.size();i++)
        {
            enlace = (String[])nom.get(i);
            this.enlaces_sin_recorrer.add(enlace[2]);
        }
    }
    
    public void eliminarProyectoExtraccion()
    {
        extraccion.borrarRegistros("proyecto", this.id_proyecto);
        extraccion.borrarRegistrosIndice("enlaces_imagen", "indice_enlaces_imagenid_proyecto", this.id_proyecto);
        extraccion.borrarRegistrosIndice("enlaces_audio", "indice_enlaces_audioid_proyecto", this.id_proyecto);
        extraccion.borrarRegistrosIndice("enlaces_video", "indice_enlaces_videoid_proyecto", this.id_proyecto);
        extraccion.borrarRegistrosIndice("enlaces_documento", "indice_enlaces_documentoid_proyecto", this.id_proyecto);   
        extraccion.borrarRegistrosIndice("enlaces_otro", "indice_enlaces_otroid_proyecto", this.id_proyecto);
        extraccion.borrarRegistrosIndice("enlaces_recorrido", "indice_enlaces_recorridoid_proyecto", this.id_proyecto);
        extraccion.borrarRegistrosIndice("enlaces_sin_recorrer", "indice_enlaces_sin_recorrerid_proyecto",this.id_proyecto);
    }

    public int getIdProyecto()
    {
        return this.id_proyecto;
    }

    public String getNombreProyecto()
    {
        return this.nombre_proyecto;
    }
    
    public ArrayList getEnlacesSinRecorrer()
    {
        return this.enlaces_sin_recorrer;
    }
    
    public ArrayList getEnlacesRecorridos()
    {
        return this.enlaces_recorridos;
    }
    
    public ArrayList getEnlacesOtros()
    {
        return this.enlaces_otros;
    }
    
    public ArrayList getEnlacesDocumentos()
    {
        return this.enlaces_documentos;
    }
    
    public ArrayList getEnlacesVideos()
    {
        return this.enlaces_videos;
    }
    
    public ArrayList getEnlacesAudios()
    {
        return this.enlaces_audios;
    }
    
    public ArrayList getEnlacesImagenes()
    {
        return this.enlaces_imagenes;
    }
}
