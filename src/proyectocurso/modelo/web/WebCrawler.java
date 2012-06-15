package proyectocurso.modelo.web;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

public class WebCrawler implements  Runnable 
{
    private ArrayList<String> enlacesListado;//Listado de enlaces que encuentra y va a recorrer.
    private ArrayList<String> enlacesRecorridos;//Listado de enlaces que ya ha recorrido.
    private ArrayList<String> enlacesImagenes;//Listado de enlaces de imágenes.
    private ArrayList<String> enlacesDocumentos;//Listado de enlaces de documentos.
    private ArrayList<String> enlacesVideos;
    private String rutaInicio;//enlace con la que se inicia la extracción.
    private URL rutaInicioURL;//Objeto URL del enlace con el que se inicia la extracción.
    private boolean finalizado;

    private Thread threadBusqueda;//Guarda la instancia que está buscando.

    public WebCrawler(String ruta) 
    {
        this.enlacesListado = new ArrayList<String>();
        this.finalizado=false;
        this.enlacesRecorridos = new ArrayList<String>();
        this.enlacesImagenes = new ArrayList<String>();
        this.enlacesDocumentos = new ArrayList<String>();
        this.enlacesVideos = new ArrayList<String>();
        this.rutaInicio = ruta;
        try {this.rutaInicioURL = new URL(ruta);}
        catch (MalformedURLException e) {}

        URLConnection.setDefaultAllowUserInteraction(false);

        //>>CREAR UN NUEVO THREAD SI NO SE HA INSTANCIADO YA.
        if (threadBusqueda == null)
            threadBusqueda = new Thread(this);
    }
    
    public void comenzarBusqueda()
    {threadBusqueda.start();}

    private void eliminarThread() 
    {
	if (threadBusqueda != null)
            threadBusqueda = null;
    }

    @Override
    public void run() 
    {
        String strURL = this.rutaInicio;
 
        if (strURL.length() == 0) 
        {
            threadBusqueda = null;
            return;
        }

        enlacesListado.add(strURL);
        enlacesRecorridos.add(strURL);

        while (enlacesListado.size() > 0 && (Thread.currentThread() == threadBusqueda)) 
        {
            // get the first element from the to be searched list
            strURL = (String) enlacesListado.get(0);

            URL url;
            try {url = new URL(strURL);}
            catch (MalformedURLException e) {break;}

            // Get URL off the enlacesListado and mark as being visited
            enlacesListado.remove(enlacesListado.get(0));
            if(!url.getHost().equals(this.rutaInicioURL.getHost()))
                continue;
            enlacesRecorridos.add(strURL);

            // Can only search http: protocol URLs
            if (url.getProtocol().compareTo("http") != 0) 
                    continue;

            try 
            {
                // try opening the URL
                URLConnection urlConnection = url.openConnection();
                urlConnection.setRequestProperty("User-Agent", "hardingbot");				
                urlConnection.setAllowUserInteraction(false);

                // Only access pages returning 200 OK status
                String httpHeader = urlConnection.getHeaderField(null);
                if (httpHeader == null || !httpHeader.contains("200 OK")) {
                        continue;
                }

                //String type = urlConnection.getHeaderField("Content-Type");
                String type = urlConnection.getContentType();

                if (type == null || !type.startsWith("text/html"))
                    continue;
                this.threadBusqueda.sleep(1000);
                this.seguirEnlace(url);
            } 
            catch (InterruptedException ex) {Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);}
            catch (IOException e){continue;}
            catch (NullPointerException e){continue;}		
        }
        this.finalizado=true;
    }
    
    private void seguirEnlace(URL url)
    {
        int indice = 0;
        String contenido = getPagina(url);
        String contenidoMinuscula = contenido.toLowerCase();
        
        while ((indice = contenidoMinuscula.indexOf("<a", indice)) != -1)
        {
            if ((indice = contenidoMinuscula.indexOf("href", indice)) == -1) 
                break;
            if ((indice = contenidoMinuscula.indexOf("=", indice)) == -1) 
                break;

            indice++;

            StringTokenizer trozoEnlace = new StringTokenizer(contenido.substring(indice), "\"");
            String enlaceString = trozoEnlace.nextToken();

            URL enlaceURL;
            try 
            {
                enlaceURL = new URL(url, enlaceString);
                enlaceString = enlaceURL.toString();
            } 
            catch (MalformedURLException e){continue;}

            if(enlaceURL.getProtocol().compareTo("http")!=0)
                break;

            String extension = enlaceURL.getFile();
            
            if (extension.endsWith(".html") || extension.endsWith(".htm") || extension.endsWith("/") || extension.contains("?")) 
            {
                if (!enlacesRecorridos.contains(enlaceString) && enlaceURL.getHost().equals(this.rutaInicioURL.getHost())) 
                    enlacesListado.add(enlaceString);	
            }
        }
        
        this.extraerImagenes(contenido,url);
        this.extraerDocumentos(contenido, url);
    }
    
    /**
     * Guarda todos los enlaces que extrae de la página web que se está recorriendo actualmente
     * @param cont Contenido del html en String
     * @param url Dirección de la web que se está recorriendo en URL
     */
    private void extraerImagenes(String cont, URL url)
    {
        int indice = 0,inh=0,ins=0;
        String contenido = cont;
        String contenidoMinuscula = contenido.toLowerCase();
        
        //Busca en el String algún posible enlace que apunte a una imagen-
        inh = contenidoMinuscula.indexOf("href", indice);
        ins = contenidoMinuscula.indexOf("src", indice);
        indice=inh<ins?inh:ins;
        while (indice != -1)
        {
            if ((indice = contenidoMinuscula.indexOf("=", indice)) == -1) 
                break;

            indice++;
            
            //Extrae el contenido de las comillas, presumiblemente es el enlace. Si contiene la cadena que define a los
            //formatos típicos de imagen, intenta guardarla en el array.
            StringTokenizer trozoEnlace = new StringTokenizer(contenido.substring(indice), "\"");
            String enlaceString = trozoEnlace.nextToken();
            if(!enlaceString.contains(".gif") && !enlaceString.contains(".jpg") && !enlaceString.contains(".png") && !enlaceString.contains(".png"))
            {
                inh = contenidoMinuscula.indexOf("href", indice);
                ins = contenidoMinuscula.indexOf("src", indice);
                indice=inh<ins?inh:ins;
                continue;
            }
            
            URL enlaceURL;
            try 
            {
                enlaceURL = new URL(url, enlaceString);
                enlaceString = enlaceURL.toString();
            } 
            catch (MalformedURLException e)
            {
                try 
                {
                    enlaceURL = new URL(url,this.rutaInicioURL.getPath() + enlaceString );
                    enlaceString = enlaceURL.toString();
                } 
                catch (MalformedURLException ee){continue;}
            }
            if(!this.enlacesImagenes.contains(enlaceString))
                this.enlacesImagenes.add(enlaceString);
            
            inh = contenidoMinuscula.indexOf("href", indice);
            ins = contenidoMinuscula.indexOf("src", indice);
            indice=inh<ins?inh:ins;
        }
    }
    
    private void extraerDocumentos(String cont, URL url)
    {
        int indice = 0,inh=0,ins=0;
        String contenido = cont;
        String contenidoMinuscula = contenido.toLowerCase();
        
        //Busca en el String algún posible enlace que apunte a una imagen-
        inh = contenidoMinuscula.indexOf("href", indice);
        ins = contenidoMinuscula.indexOf("src", indice);
        indice=inh<ins?inh:ins;
        while (indice != -1)
        {
            if ((indice = contenidoMinuscula.indexOf("=", indice)) == -1) 
                break;

            indice++;
            
            //Extrae el contenido de las comillas, presumiblemente es el enlace. Si contiene la cadena que define a los
            //formatos típicos de documento, intenta guardarla en el array.
            StringTokenizer trozoEnlace = new StringTokenizer(contenido.substring(indice), "\"");
            String enlaceString = trozoEnlace.nextToken();
            if(!enlaceString.contains(".txt") && !enlaceString.contains(".doc") && !enlaceString.contains(".pdf") && !enlaceString.contains(".docx") && !enlaceString.contains(".odt"))
            {
                inh = contenidoMinuscula.indexOf("href", indice);
                ins = contenidoMinuscula.indexOf("src", indice);
                indice=inh<ins?inh:ins;
                continue;
            }
            
            URL enlaceURL;
            try 
            {
                enlaceURL = new URL(url, enlaceString);
                enlaceString = enlaceURL.toString();
            } 
            catch (MalformedURLException e)
            {
                try 
                {
                    enlaceURL = new URL(url,this.rutaInicioURL.getPath() + enlaceString );
                    enlaceString = enlaceURL.toString();
                } 
                catch (MalformedURLException ee){continue;}
            }
            if(!this.enlacesDocumentos.contains(enlaceString))
                this.enlacesDocumentos.add(enlaceString);
            
            inh = contenidoMinuscula.indexOf("href", indice);
            ins = contenidoMinuscula.indexOf("src", indice);
            indice=inh<ins?inh:ins;
        }
    }
    
    private void extraerVideo(String cont, URL url)
    {
        int indice = 0,inh=0,ins=0;
        String contenido = cont;
        String contenidoMinuscula = contenido.toLowerCase();
        
        //Busca en el String algún posible enlace que apunte a una imagen-
        inh = contenidoMinuscula.indexOf("href", indice);
        ins = contenidoMinuscula.indexOf("src", indice);
        indice=inh<ins?inh:ins;
        while (indice != -1)
        {
            if ((indice = contenidoMinuscula.indexOf("=", indice)) == -1) 
                break;

            indice++;
            
            //Extrae el contenido de las comillas, presumiblemente es el enlace. Si contiene la cadena que define a los
            //formatos típicos de documento, intenta guardarla en el array.
            StringTokenizer trozoEnlace = new StringTokenizer(contenido.substring(indice), "\"");
            String enlaceString = trozoEnlace.nextToken();
            if(!enlaceString.contains("youtube.com/v/") && !enlaceString.contains(".mp4") && !enlaceString.contains(".mpg") && !enlaceString.contains(".avi") && !enlaceString.contains(".mkv") && !enlaceString.contains(".wmv"))
            {
                inh = contenidoMinuscula.indexOf("href", indice);
                ins = contenidoMinuscula.indexOf("src", indice);
                indice=inh<ins?inh:ins;
                continue;
            }
            
            URL enlaceURL;
            try 
            {
                enlaceURL = new URL(url, enlaceString);
                enlaceString = enlaceURL.toString();
            } 
            catch (MalformedURLException e)
            {
                try 
                {
                    enlaceURL = new URL(url,this.rutaInicioURL.getPath() + enlaceString );
                    enlaceString = enlaceURL.toString();
                } 
                catch (MalformedURLException ee){continue;}
            }
            if(!this.enlacesVideos.contains(enlaceString))
                this.enlacesVideos.add(enlaceString);
            
            inh = contenidoMinuscula.indexOf("href", indice);
            ins = contenidoMinuscula.indexOf("src", indice);
            indice=inh<ins?inh:ins;
        }
    }
	
    private String getPagina(URL url) 
    {		
        StringWriter writer = new StringWriter();
        try {IOUtils.copy(url.openStream(), writer);} 
        catch (IOException ex) {Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);}

        String contenidoString = writer.toString();
        return contenidoString;
    }
 
    public static void main (String argv[])
    {
        WebCrawler web = new WebCrawler("http://gaialaleyenda.foroactivo.com/");
        web.comenzarBusqueda();
        
        while(web.finalizado!=true)
        {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("TOTAL ENLACES VIDEOS: " + web.enlacesVideos.size());
        }
        System.out.println("SE ACABO");
    }
}