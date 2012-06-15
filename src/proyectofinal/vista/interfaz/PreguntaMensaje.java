package proyectofinal.vista.interfaz;

import java.awt.Component;
import javax.swing.JOptionPane;

public class PreguntaMensaje 
{
    private Component frame;
    private int respuesta;
    
    /**
     * Método que muestra un mensaje de pregunta al usuario
     * @param contenido Esta variable contiene la pregunta del mensaje, introducido por el usuario
     */
    public PreguntaMensaje(String contenido)
    {
        Object[] options = {"Yes, por favor","No, gracias","No huevos, imbecil!"};
        this.respuesta = JOptionPane.showOptionDialog(frame,contenido,"Arturo es tonto",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
    }
    
    /**
     * Método que devuelve la variable respuesta
     * @return 
     */
    public int devuelveRespuesta()
    {
        return this.respuesta;
    }
}

