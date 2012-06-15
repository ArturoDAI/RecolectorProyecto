package ProyectoCurso.modelo.baseDatos;

import java.util.ArrayList;

public class Biblioteca 
{
    // Creo las variables
    // Ruta donde se guarda la direccion de la base de datos
    private String ruta;
    // Creamos el objeto BD
    private BD biblioteca;
    // El los siguientes arrays guardo los campos de los que se compone cada tabla
    private String[] camposAudio;
    private String[] camposVideo;
    private String[] camposImagen;
    private String[] camposDocumento;
    private String[] camposOtro;
    private String[] camposLogin;
        
    /**
     * En el constructor inicializaremos las propiedades
     * @param ruta Variable que contiene la ruta de la base de datos
     */
    public Biblioteca(String ruta)
    {
        // En cada uno de estos arrays introduzco todos los nombres de los campos de las tablas
        this.camposAudio = new String[10];
        this.camposAudio[0]="id_audio";this.camposAudio[1]="url";this.camposAudio[2]="nombre_aud";this.camposAudio[3]="localizacion";this.camposAudio[4]="tamano";this.camposAudio[5]="web_origen";this.camposAudio[6]="formato";this.camposAudio[7]="fecha_descarga";this.camposAudio[8]="duracion";this.camposAudio[9]="bit_rate";
        this.camposVideo = new String[10];
        this.camposVideo[0]="id_video";this.camposVideo[1]="url";this.camposVideo[2]="nombre_vid";this.camposVideo[3]="localizacion";this.camposVideo[4]="tamano";this.camposVideo[5]="web_origen";this.camposVideo[6]="formato";this.camposVideo[7]="fecha_descarga";this.camposVideo[8]="duracion";this.camposVideo[9]="resolucion";
        this.camposImagen = new String[10];
        this.camposImagen[0]="id_imagen";this.camposImagen[1]="url";this.camposImagen[2]="nombre_img";this.camposImagen[3]="localizacion";this.camposImagen[4]="tamano";this.camposImagen[5]="web_origen";this.camposImagen[6]="formato";this.camposImagen[7]="fecha_descarga";this.camposImagen[8]="ancho";this.camposImagen[9]="alto";
        this.camposDocumento = new String[9];
        this.camposDocumento[0]="id_doc";this.camposDocumento[1]="url";this.camposDocumento[2]="nombre_doc";this.camposDocumento[3]="localizacion";this.camposDocumento[4]="tamano";this.camposDocumento[5]="web_origen";this.camposDocumento[6]="formato";this.camposDocumento[7]="fecha_descarga";this.camposDocumento[8]="num_paginas";
        this.camposOtro = new String[8];
        this.camposOtro[0]="id_otro";this.camposOtro[1]="url";this.camposOtro[2]="nombre_otro";this.camposOtro[3]="localizacion";this.camposOtro[4]="tamano";this.camposOtro[5]="web_origen";this.camposOtro[6]="formato";this.camposOtro[7]="fecha_descarga";
        this.camposLogin = new String[5];
        this.camposLogin[0]="id_login";this.camposLogin[1]="direccion_web";this.camposLogin[2]="nombre_web";this.camposLogin[3]="nombre_usuario";this.camposLogin[4]="contrasena";
        this.ruta = ruta;
        this.biblioteca = new BD(ruta);
    }
    
    /**
     * Método que crea la tabla audio junto a sus índices.
     */
    public void crearTablaAudio()
    {
        String tablaAudio = "CREATE TABLE audio (id_audio  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , url TEXT, nombre_aud TEXT, localizacion TEXT, tamano FLOAT, web_origen TEXT, formato TEXT, fecha_descarga TEXT, duracion INTEGER, bit_rate INTEGER)";
        String[] valor;
        // Array que guarda el nombre de los campos sobre los que se van a hacer los índices
        valor = new String[5];
        valor[0] = "id_audio";        
        valor[1] = "nombre_aud";
        valor[2] = "tamano";
        valor[3] = "formato";
        valor[4] = "web_origen";
        biblioteca.crearTabla("audio", tablaAudio, valor);
    }
    
    /**
     * Método que crea la tabla video junto a sus índices.
     */
    public void crearTablaVideo()
    {
        String tablaVideo = "CREATE TABLE video (id_video  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , url TEXT, nombre_vid TEXT, localizacion TEXT, tamano FLOAT, web_origen TEXT, formato TEXT, fecha_descarga TEXT, duracion INTEGER, resolucion TEXT)";
        String[] valor;
        // Array que guarda el nombre de los campos sobre los que se van a hacer los índices
        valor = new String[5];
        valor[0] = "id_video";        
        valor[1] = "nombre_vid";
        valor[2] = "tamano";
        valor[3] = "formato";
        valor[4] = "web_origen";
        biblioteca.crearTabla("video", tablaVideo, valor);
    }
    
    /**
     * Método que crea la tabla imagen junto a sus índices.
     */
    public void crearTablaImagen()
    {
        String tablaImagen = "CREATE TABLE imagen (id_imagen  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , url TEXT, nombre_img TEXT, localizacion TEXT, tamano INTEGER, web_origen TEXT, formato TEXT, fecha_descarga TEXT, ancho FLOAT, alto FLOAT)";
        String[] valor;
        // Array que guarda el nombre de los campos sobre los que se van a hacer los índices
        valor = new String[5];
        valor[0] = "id_imagen";        
        valor[1] = "nombre_img";
        valor[2] = "tamano";
        valor[3] = "formato";
        valor[4] = "web_origen";
        biblioteca.crearTabla("imagen",tablaImagen, valor);
    }
    
    /**
     * Método que crea la tabla documento junto a sus índices.
     */
    public void crearTablaDocumento()
    {
        String tablaDocumento = "CREATE TABLE documento (id_doc  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , url TEXT, nombre_doc TEXT, localizacion TEXT, tamano FLOAT, web_origen TEXT, formato TEXT, fecha_descarga TEXT, num_paginas INTEGER)";
        String[] valor;
        // Array que guarda el nombre de los campos sobre los que se van a hacer los índices
        valor = new String[5];
        valor[0] = "id_doc";        
        valor[1] = "nombre_doc";
        valor[2] = "tamano";
        valor[3] = "formato";
        valor[4] = "web_origen";
        biblioteca.crearTabla("documento", tablaDocumento, valor);
    }
    
    /**
     * Método que crea la tabla otro junto a sus índices.
     */
    public void crearTablaOtro()
    {
        String tablaOtro = "CREATE TABLE otro (id_otro  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , url TEXT, nombre_otro TEXT, localizacion TEXT, tamano FLOAT, web_origen TEXT, formato TEXT, fecha_descarga TEXT)";
        String[] valor;
        // Array que guarda el nombre de los campos sobre los que se van a hacer los índices
        valor = new String[5];
        valor[0] = "id_otro";        
        valor[1] = "nombre_otro";
        valor[2] = "tamano";
        valor[3] = "formato";
        valor[4] = "web_origen";
        biblioteca.crearTabla("otro" ,tablaOtro, valor);
    }
    
    /**
     * Método que crea la tabla login junto a sus índices, además inserta unos valores por defecto, que no podrán ser modificados.
     */
    public void crearTablaLogin()
    {
        String tablaLogin = "CREATE TABLE login (id_login  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, direccion_web  TEXT NOT NULL, nombre_web TEXT NOT NULL, nombre_usuario TEXT, contrasena TEXT)";
        String[] valor;
        // Array que guarda el nombre del campos sobre el que se va a crear el índice
        valor = new String[1];
        valor[0] = "id_login";        
        biblioteca.crearTabla("login", tablaLogin, valor);
        // Una vez creada la tabla, se insertan los valores
        ArrayList campos = new ArrayList();
        campos.add("www.tuenti.com");
        campos.add("tuenti");
        biblioteca.insertarRegistros("login", campos);
    }
    
    /**
     * Método que modifica un login de los que hay por defecto, solo cambiará el nombre de usuario y la contraseña de ese usuario
     * @param id Variable que contiene la id del logen que se va a modificar
     * @param nombreUsuario Variable que contiene el nombre de usuario que se le desea poner al login
     * @param contrasena Variable que contiene la contraseña que se le quiere poner al login
     */
    public void modificarLogin(int id, String nombreUsuario, String contrasena)
    {
        // En este array se guardarán los nuevos campos, los que no se van a cambiar, 
        // que van por defecto, se ponen a null, para que solo cambie aquellos que cambian
        ArrayList campos = new ArrayList();
        campos.add(null);
        campos.add(null);
        campos.add(null);
        campos.add(nombreUsuario);
        campos.add(contrasena);
        biblioteca.modificarRegistrosUpdate("login", id, campos);
    }
         
    /**
     * Método que borra la tabla audio 
     */
    public void borrarTablaAudio()
    {
        biblioteca.borrarTabla("audio");
    }
    
    /**
     * Método que borra la tabla imagen 
     */
    public void borrarTablaImagen()
    {
        biblioteca.borrarTabla("imagen");
    }    
    
    /**
     * Método que borra la tabla video 
     */
    public void borrarTablaVideo()
    {
        biblioteca.borrarTabla("video");
    }
    
    /**
     * Método que borra la tabla documento 
     */
    public void borrarTablaDocumento()
    {
        biblioteca.borrarTabla("documeto");
    }
    
    /**
     * Método que borra la tabla otro 
     */
    public void borrarTablaOtro()
    {
        biblioteca.borrarTabla("otro");
    }    
    
    /**
     * Método que borra la tabla login 
     */
    public void borrarTablaLogin()
    {
        biblioteca.borrarTabla("login");
    }
    
    /**
     * Método que borra un registro de la tabla audio.
     * @param id Variable que guarda el id del archivo audio a borrar
     */
    public void borrarArchivoAudio(int id)
    {
        biblioteca.borrarRegistros("audio", id);
    }
    
    /**
     * Método que borra un registro de la tabla imagen.
     * @param id Variable que guarda el id del archivo imagen a borrar
     */
    public void borrarArchivoImagen(int id)
    {
        biblioteca.borrarRegistros("imagen", id);
    }
    
    /**
     * Método que borra un registro de la tabla video.
     * @param id Variable que guarda el id del archivo video a borrar
     */
    public void borrarArchivoVideo(int id)
    {
        biblioteca.borrarRegistros("video", id);
    }
    
    /**
     * Método que borra un registro de la tabla documento.
     * @param id Variable que guarda el id del archivo documento a borrar
     */
    public void borrarArchivoDocumento(int id)
    {
        biblioteca.borrarRegistros("documento", id);
    }
    
    /**
     * Método que borra un registro de la tabla otro.
     * @param id Variable que guarda el id del archivo otro a borrar
     */
    public void borrarArchivoOtro(int id)
    {
        biblioteca.borrarRegistros("otro", id);
    }
    
    /**
     * Método que busca registros por nombre en todas las tablas disponibles
     * @param nombreTabla Variable que contiene el nombre de la tabla a hacer la búsqueda
     * @param valor Variable que contiene el nombre de registro que se desea buscar
     * @return Devuelve un arrayList con los resultados de la búsqueda
     */
    public ArrayList<String[]> busquedaNombre(String nombreTabla, String[] valor)
    {
        // Ponemos los datos introducidos por el usuario a minúscula, ya que en la base de datos todos los datos esta´n en minúscula
        nombreTabla = nombreTabla.toLowerCase();
        for(int i = 0; i < valor.length ; i++)
            valor[i]=valor[i].toLowerCase();
        // Si el usuario ha elegido un valor a buscar se hace un tipo de búsqueda
        if(valor.length == 1)
        {
            String valor1 = valor[0];
            if("audio".equals(nombreTabla))
                return biblioteca.busquedaBD("audio", "indice_audio"+this.camposAudio[2], this.camposAudio, valor1);
            else if("imagen".equals(nombreTabla))
                return biblioteca.busquedaBD("imagen", "indice_imagen"+this.camposImagen[2], this.camposImagen, valor1);
            else if("video".equals(nombreTabla))
                return biblioteca.busquedaBD("video", "indice_video"+this.camposVideo[2], this.camposVideo, valor1);
            else if("documento".equals(nombreTabla))
                return biblioteca.busquedaBD("documento", "indice_documento"+this.camposDocumento[2], this.camposDocumento, valor1);
            else if("otro".equals(nombreTabla))
                return biblioteca.busquedaBD("otro", "indice_otro"+this.camposOtro[2], this.camposOtro, valor1);
        }
        // Si el usuario ha elegido un rango de dos valores a buscar se hace otro tipo de búsqueda
        else if (valor.length == 2)
        {
            if("audio".equals(nombreTabla))
                return biblioteca.busquedaBD("audio", "indice_audio"+this.camposAudio[2], this.camposAudio, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("imagen".equals(nombreTabla))
                return biblioteca.busquedaBD("imagen", "indice_imagen"+this.camposImagen[2], this.camposImagen, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("video".equals(nombreTabla))
                return biblioteca.busquedaBD("video", "indice_video"+this.camposVideo[2], this.camposVideo, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("documento".equals(nombreTabla))
                return biblioteca.busquedaBD("documento", "indice_documento"+this.camposDocumento[2], this.camposDocumento, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("otro".equals(nombreTabla))
                return biblioteca.busquedaBD("otro", "indice_otro"+this.camposOtro[2], this.camposOtro, new Object[]{valor[0]}, new Object[]{valor[1]});
        }
        return null;
    }

    /**
     * Método que busca registros por id en todas las tablas disponibles
     * @param nombreTabla Variable que contiene el nombre de la tabla a hacer la búsqueda
     * @param valor Variable que contiene la id del registro que se desea buscar
     * @return Devuelve un arrayList con los resultados de la búsqueda
     */
    public ArrayList<String[]> busquedaId(String nombreTabla, Integer[] valor)
    {
        // Si el usuario ha elegido un valor a buscar se hace un tipo de búsqueda
        if(valor.length == 1)
        {
            int valor1 = valor[0];
            if("audio".equals(nombreTabla))
                return biblioteca.busquedaBD("audio", "indice_audio"+this.camposAudio[0], this.camposAudio, valor1);
            else if("imagen".equals(nombreTabla))
                return biblioteca.busquedaBD("imagen", "indice_imagen"+this.camposImagen[0], this.camposImagen, valor1);
            else if("video".equals(nombreTabla))
                return biblioteca.busquedaBD("video", "indice_video"+this.camposVideo[0], this.camposVideo, valor1);
            else if("documento".equals(nombreTabla))
                return biblioteca.busquedaBD("documento", "indice_documento"+this.camposDocumento[0], this.camposDocumento, valor1);
            else if("otro".equals(nombreTabla))
                return biblioteca.busquedaBD("otro", "indice_otro"+this.camposOtro[0], this.camposOtro, valor1);
        }
        // Si el usuario ha elegido un rango de dos valores a buscar se hace otro tipo de búsqueda
        else if (valor.length == 2)
        {
            if("audio".equals(nombreTabla))
                return biblioteca.busquedaBD("audio", "indice_audio"+this.camposAudio[0], this.camposAudio, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("imagen".equals(nombreTabla))
                return biblioteca.busquedaBD("imagen", "indice_imagen"+this.camposImagen[0], this.camposImagen, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("video".equals(nombreTabla))
                return biblioteca.busquedaBD("video", "indice_video"+this.camposVideo[0], this.camposVideo, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("documento".equals(nombreTabla))
                return biblioteca.busquedaBD("documento", "indice_documento"+this.camposDocumento[0], this.camposDocumento, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("otro".equals(nombreTabla))
                return biblioteca.busquedaBD("otro", "indice_otro"+this.camposOtro[0], this.camposOtro, new Object[]{valor[0]}, new Object[]{valor[1]});
        }
        return null;
    }
    
    /**
     * Método que busca registros por tamaño en todas las tablas disponibles
     * @param nombreTabla Variable que contiene el nombre de la tabla a hacer la búsqueda
     * @param valor Variable que contiene el tamaño del registro que se desea buscar
     * @return Devuelve un arrayList con los resultados de la búsqueda
     */
    public ArrayList<String[]> busquedatamano(String nombreTabla, float[] valor)
    {
        // Si el usuario ha elegido un valor a buscar se hace un tipo de búsqueda
        if(valor.length == 1)
        {
            float valor1 = valor[0];
            if("audio".equals(nombreTabla))
                return biblioteca.busquedaBD("audio", "indice_audio"+this.camposAudio[4], this.camposAudio, valor1);
            else if("imagen".equals(nombreTabla))
                return biblioteca.busquedaBD("imagen", "indice_imagen"+this.camposImagen[4], this.camposImagen, valor1);
            else if("video".equals(nombreTabla))
                return biblioteca.busquedaBD("video", "indice_video"+this.camposVideo[4], this.camposVideo, valor1);
            else if("documento".equals(nombreTabla))
                return biblioteca.busquedaBD("documento", "indice_documento"+this.camposDocumento[4], this.camposDocumento, valor1);
            else if("otro".equals(nombreTabla))
                return biblioteca.busquedaBD("otro", "indice_otro"+this.camposOtro[4], this.camposOtro, valor1);
        }
        // Si el usuario ha elegido un rango de dos valores a buscar se hace otro tipo de búsqueda
        else if (valor.length == 2)
        {
            if("audio".equals(nombreTabla))
                return biblioteca.busquedaBD("audio", "indice_audio"+this.camposAudio[4], this.camposAudio, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("imagen".equals(nombreTabla))
                return biblioteca.busquedaBD("imagen", "indice_imagen"+this.camposImagen[4], this.camposImagen, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("video".equals(nombreTabla))
                return biblioteca.busquedaBD("video", "indice_video"+this.camposVideo[4], this.camposVideo, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("documento".equals(nombreTabla))
                return biblioteca.busquedaBD("documento", "indice_documento"+this.camposDocumento[4], this.camposDocumento, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("otro".equals(nombreTabla))
                return biblioteca.busquedaBD("otro", "indice_otro"+this.camposOtro[4], this.camposOtro, new Object[]{valor[0]}, new Object[]{valor[1]});
        }
        return null;
    }
    
    /**
     * Método que busca registros por formato en todas las tablas disponibles
     * @param nombreTabla Variable que contiene el nombre de la tabla a hacer la búsqueda
     * @param valor Variable que contiene el formato del registro que se desea buscar
     * @return Devuelve un arrayList con los resultados de la búsqueda
     */
    public ArrayList<String[]> busquedaFormato(String nombreTabla, String[] valor)
    {
        // Ponemos los datos introducidos por el usuario a minúscula, ya que en la base de datos todos los datos esta´n en minúscula
        nombreTabla = nombreTabla.toLowerCase();
        for(int i = 0; i < valor.length ; i++)
            valor[i]=valor[i].toLowerCase();
        // Si el usuario ha elegido un valor a buscar se hace un tipo de búsqueda
        if(valor.length == 1)
        {
            String valor1 = valor[0];
            if("audio".equals(nombreTabla))
                return biblioteca.busquedaBD("audio", "indice_audio"+this.camposAudio[6], this.camposAudio, valor1);
            else if("imagen".equals(nombreTabla))
                return biblioteca.busquedaBD("imagen", "indice_imagen"+this.camposImagen[6], this.camposImagen, valor1);
            else if("video".equals(nombreTabla))
                return biblioteca.busquedaBD("video", "indice_video"+this.camposVideo[6], this.camposVideo, valor1);
            else if("documento".equals(nombreTabla))
                return biblioteca.busquedaBD("documento", "indice_documento"+this.camposDocumento[6], this.camposDocumento, valor1);
            else if("otro".equals(nombreTabla))
                return biblioteca.busquedaBD("otro", "indice_otro"+this.camposOtro[6], this.camposOtro, valor1);
        }
        // Si el usuario ha elegido un rango de dos valores a buscar se hace otro tipo de búsqueda
        else if (valor.length == 2)
        {
            if("audio".equals(nombreTabla))
                return biblioteca.busquedaBD("audio", "indice_audio"+this.camposAudio[6], this.camposAudio, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("imagen".equals(nombreTabla))
                return biblioteca.busquedaBD("imagen", "indice_imagen"+this.camposImagen[6], this.camposImagen, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("video".equals(nombreTabla))
                return biblioteca.busquedaBD("video", "indice_video"+this.camposVideo[6], this.camposVideo, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("documento".equals(nombreTabla))
                return biblioteca.busquedaBD("documento", "indice_documento"+this.camposDocumento[6], this.camposDocumento, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("otro".equals(nombreTabla))
                return biblioteca.busquedaBD("otro", "indice_otro"+this.camposOtro[6], this.camposOtro, new Object[]{valor[0]}, new Object[]{valor[1]});
        }
        return null;
    }
    
    /**
     * Método que busca registros por la web origen en todas las tablas disponibles
     * @param nombreTabla Variable que contiene el nombre de la tabla a hacer la búsqueda
     * @param valor Variable que contiene la web origen del registro que se desea buscar
     * @return Devuelve un arrayList con los resultados de la búsqueda
     */
    public ArrayList<String[]> busquedaWebOrigen(String nombreTabla, String[] valor)
    {
        // Ponemos los datos introducidos por el usuario a minúscula, ya que en la base de datos todos los datos esta´n en minúscula
        nombreTabla = nombreTabla.toLowerCase();
        for(int i = 0; i < valor.length ; i++)
            valor[i]=valor[i].toLowerCase();
        // Si el usuario ha elegido un valor a buscar se hace un tipo de búsqueda
        if(valor.length == 1)
        {
            String valor1 = valor[0];
            if("audio".equals(nombreTabla))
                return biblioteca.busquedaBD("audio", "indice_audio"+this.camposAudio[5], this.camposAudio, valor1);
            else if("imagen".equals(nombreTabla))
                return biblioteca.busquedaBD("imagen", "indice_imagen"+this.camposImagen[5], this.camposImagen, valor1);
            else if("video".equals(nombreTabla))
                return biblioteca.busquedaBD("video", "indice_video"+this.camposVideo[5], this.camposVideo, valor1);
            else if("documento".equals(nombreTabla))
                return biblioteca.busquedaBD("documento", "indice_documento"+this.camposDocumento[5], this.camposDocumento, valor1);
            else if("otro".equals(nombreTabla))
                return biblioteca.busquedaBD("otro", "indice_otro"+this.camposOtro[5], this.camposOtro, valor1);
        }
        // Si el usuario ha elegido un rango de dos valores a buscar se hace otro tipo de búsqueda
        else if (valor.length == 2)
        {
            if("audio".equals(nombreTabla))
                return biblioteca.busquedaBD("audio", "indice_audio"+this.camposAudio[5], this.camposAudio, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("imagen".equals(nombreTabla))
                return biblioteca.busquedaBD("imagen", "indice_imagen"+this.camposImagen[5], this.camposImagen, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("video".equals(nombreTabla))
                return biblioteca.busquedaBD("video", "indice_video"+this.camposVideo[5], this.camposVideo, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("documento".equals(nombreTabla))
                return biblioteca.busquedaBD("documento", "indice_documento"+this.camposDocumento[5], this.camposDocumento, new Object[]{valor[0]}, new Object[]{valor[1]});
            else if("otro".equals(nombreTabla))
                return biblioteca.busquedaBD("otro", "indice_otro"+this.camposOtro[5], this.camposOtro, new Object[]{valor[0]}, new Object[]{valor[1]});
        }
        return null;
    }
    
    /**
     * Método que elimina la base de datos
     */
    public void eliminarBD()
    {
        biblioteca.eliminarBD();
    }
    
    /**
     * Método que inserta una imagen en la base de datos
     * @param datos Variable que contiene los datos de la nueva imagen
     */
    public void InsertarImagen(ArrayList datos)
    {
        // Cambiamos todos los datos, que se puedan cambiar, a minúsculas
        for(int i = 0 ; i < datos.size() ; i++)
        {
            try{datos.set(i, datos.get(i).toString().toLowerCase());}
            catch(Exception e){}
        }
        biblioteca.insertarRegistros("imagen", datos);
    }
    
    /**
     * Método que inserta un video en la base de datos
     * @param datos Variable que contiene los datos del nuevo video
     */
    public void InsertarVideo(ArrayList datos)
    {
        // Cambiamos todos los datos, que se puedan cambiar, a minúsculas
        for(int i = 0 ; i < datos.size() ; i++)
        {
            try{datos.set(i, datos.get(i).toString().toLowerCase());}
            catch(Exception e){}
        }
        biblioteca.insertarRegistros("video", datos);
    }
    
    /**
     * Método que inserta un sonido en la base de datos
     * @param datos Variable que contiene los datos del nuevo audio
     */
    public void InsertarAudio(ArrayList datos)
    {
        // Cambiamos todos los datos, que se puedan cambiar, a minúsculas
        for(int i = 0 ; i < datos.size() ; i++)
        {
            try{datos.set(i, datos.get(i).toString().toLowerCase());}
            catch(Exception e){}
        }
        biblioteca.insertarRegistros("audio", datos);
    }
    
    /**
     * Método que inserta un documento en la base de datos
     * @param datos Variable que contiene los datos del nuevo documento
     */
    public void InsertarDocumento(ArrayList datos)
    {
        // Cambiamos todos los datos, que se puedan cambiar, a minúsculas
        for(int i = 0 ; i < datos.size() ; i++)
        {
            try{datos.set(i, datos.get(i).toString().toLowerCase());}
            catch(Exception e){}
        }
        biblioteca.insertarRegistros("documento", datos);
    }
    
    /**
     * Método que inserta un registroen la tabla otro, en la base de datos
     * @param datos Variable que contiene los datos que se van a insertar
     */
    public void InsertarOtro(ArrayList datos)
    {
        // Cambiamos todos los datos, que se puedan cambiar, a minúsculas
        for(int i = 0 ; i < datos.size() ; i++)
        {
            try{datos.set(i, datos.get(i).toString().toLowerCase());}
            catch(Exception e){}
        }
        biblioteca.insertarRegistros("otro", datos);
    }
    
    /**
     * Método que modifica los datos de una imagen
     * @param id Variable que contiene la id de la imagen que vamos a modificar
     * @param datos Variable que contiene los datos de la nueva imagen
     */
    public void modificarImagen(int id, ArrayList datos)
    {
        // Cambiamos todos los datos, que se puedan cambiar, a minúsculas
        for(int i = 0 ; i < datos.size() ; i++)
        {
            try{datos.set(i, datos.get(i).toString().toLowerCase());}
            catch(Exception e){}
        }
        biblioteca.modificarRegistros("imagen", id, datos);
    }
    
    /**
     * Método que modifica los datos de un sonido
     * @param id Variable que contiene la id del audio que vamos a modificar
     * @param datos Variable que contiene los datos del nuevo audio
     */
    public void modificarAudio(int id, ArrayList datos)
    {
        // Cambiamos todos los datos, que se puedan cambiar, a minúsculas
        for(int i = 0 ; i < datos.size() ; i++)
        {
            try{datos.set(i, datos.get(i).toString().toLowerCase());}
            catch(Exception e){}
        }
        biblioteca.modificarRegistros("audio", id, datos);
    }
    
    /**
     * Método que modifica los datos de un video
     * @param id Variable que contiene la id del video que vamos a modificar
     * @param datos Variable que contiene los datos del nuevo video
     */
    public void modificarVideo(int id, ArrayList datos)
    {
        // Cambiamos todos los datos, que se puedan cambiar, a minúsculas
        for(int i = 0 ; i < datos.size() ; i++)
        {
            try{datos.set(i, datos.get(i).toString().toLowerCase());}
            catch(Exception e){}
        }
        biblioteca.modificarRegistros("video", id, datos);
    }
    
    /**
     * Método que modifica los datos de un documento
     * @param id Variable que contiene la id del documento que vamos a modificar
     * @param datos Variable que contiene los datos del nuevo documento
     */
    public void modificarDocumento(int id, ArrayList datos)
    {
        // Cambiamos todos los datos, que se puedan cambiar, a minúsculas
        for(int i = 0 ; i < datos.size() ; i++)
        {
            try{datos.set(i, datos.get(i).toString().toLowerCase());}
            catch(Exception e){}
        }
        biblioteca.modificarRegistros("documento", id, datos);
    }
    
    /**
     * Método que modifica los datos de un registro de la tabla otro
     * @param id Variable que contiene la id del registro de la tabla otro que vamos a modificar
     * @param datos Variable que contiene los datos del nuevo registro
     */
    public void modificarOtro(int id, ArrayList datos)
    {
        // Cambiamos todos los datos, que se puedan cambiar, a minúsculas
        for(int i = 0 ; i < datos.size() ; i++)
        {
            try{datos.set(i, datos.get(i).toString().toLowerCase());}
            catch(Exception e){}
        }
        biblioteca.modificarRegistros("otro", id, datos);
    }
    
    /**
     * Método que muestra los datos de la tabla login
     * @return Devuelve un arrayList con todos los datos de la tabla login
     */
    public ArrayList<String[]> mostrarDatosLogin()
    {
        return biblioteca.mostrarDatos("login", this.camposLogin);
    }
    
    /**
     * Método que muestra los datos de la tabla imagen
     * @return Devuelve un arrayList con todos los datos de la tabla imagen
     */
    public ArrayList<String[]> mostrarDatosImagen()
    {
        return biblioteca.mostrarDatos("imagen", camposImagen);
    }
    
    /**
     * Método que muestra los datos de la tabla audio
     * @return Devuelve un arrayList con todos los datos de la tabla audio
     */
    public ArrayList<String[]> mostrarDatosAudio()
    {
        return biblioteca.mostrarDatos("audio", camposAudio);
    }
    
    /**
     * Método que muestra los datos de la tabla video
     * @return Devuelve un arrayList con todos los datos de la tabla video
     */
    public ArrayList<String[]> mostrarDatosVideo()
    {
        return biblioteca.mostrarDatos("video", camposVideo);
    }
    
    /**
     * Método que muestra los datos de la tabla documento
     * @return Devuelve un arrayList con todos los datos de la tabla documento
     */
    public ArrayList<String[]> mostrarDatosDocumento()
    {
        return biblioteca.mostrarDatos("documento", camposDocumento);
    }
    
    /**
     * Método que muestra los datos de la tabla otro
     * @return Devuelve un arrayList con todos los datos de la tabla otro
     */
    public ArrayList<String[]> mostrarDatosOtro()
    {
        return biblioteca.mostrarDatos("otro", camposOtro);
    }
    
    public static void main(String[] args)
    {
        Biblioteca pro = new Biblioteca("C:\\Users\\Legado Secreto\\Desktop\\proyecto.bd");
        ArrayList datos = new ArrayList();
        
        //datos = pro.busquedaId("otro", new Integer[]{1});
        //datos = pro.busquedaWebOrigen("otro", new String[]{"facebook"});
        //datos = pro.busquedaFormato("otro", new String[]{"OTRO3"});
        //datos = pro.busquedaNombre("otro", new String[]{"pruebaOtro2"});
        //datos = pro.busquedatamano("otro", new float[]{232});
        //datos = pro.busquedatamano("otro", new float[]{3333,666666});
        //datos = pro.busquedaNombre("otro", new String[]{"p","z"});
        
        //pro.crearTablaLogin();
        //pro.modificarLogin(1, "arturo", "12345");
        //datos = pro.mostrarDatosLogin();
        
        //pro.crearTablaImagen();        
        //datos.add("PRueba");datos.add("pruebA");datos.add("prueba");datos.add(123411);datos.add(45.5);datos.add("prUEba4");datos.add("una prueba");datos.add(1024);datos.add(640);
        //pro.InsertarImagen(datos);
        
        //pro.crearTablaVideo();        
        //datos.add("pruebavideo");datos.add("pruebavideo");datos.add("pruebavideo");datos.add(123411);datos.add(45.5);datos.add("pruebavideo");datos.add("una pruebavideo");datos.add(1024);datos.add(640);
        //pro.InsertarVideo(datos);
        
        //pro.crearTablaAudio();        
        //datos.add("pruebaaudio");datos.add("pruebaaudio");datos.add("pruebaaudio");datos.add(123411);datos.add(45.5);datos.add("pruebaaudio");datos.add("pruebaaudio");datos.add(1024);datos.add(640);
        //pro.InsertarAudio(datos);
        
        //pro.crearTablaDocumento();        
        //datos.add("pruebadocumento2");datos.add("pruebadocumento2");datos.add("pruebadocumento2");datos.add(123411);datos.add(45.5);datos.add("pruebadocumento2");datos.add("pruebadocumento2");datos.add(1024);
        //pro.InsertarDocumento(datos);
        
        //pro.crearTablaOtro();        
        //datos.add("pruebaOtro2");datos.add("pruebaOtro2");datos.add("pruebaOtro2");datos.add(123411);datos.add(45.5);datos.add("pruebaOtro2");datos.add("pruebaOtro2");
        //pro.InsertarOtro(datos);
 
        //datos=pro.mostrarDatosImagen();
        //int contador=0;
        //while(contador<datos.size())
        //{
            //String[] resultado=(String[])datos.get(contador);
            //for(int i=0;i<resultado.length;i++)
               // System.out.println(resultado[i]);
            //contador++;
        //}
    }
}
