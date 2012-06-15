package ProyectoCurso.vista.interfaz;

import java.awt.Component;
import javax.swing.JOptionPane;

public class AvisoMensaje 
{
    private Component frame;
    
    /**
     * Método que muestra un mensaje de aviso
     * @param titulo Variable que contiene el titulo de mensaje intruducido por el usuario
     * @param contenido Variable que contiene el contenido del mensaje introducido por el usuario
     */
    public AvisoMensaje(String titulo, String contenido)
    {
        JOptionPane.showMessageDialog(frame,contenido,titulo,JOptionPane.WARNING_MESSAGE);
    }

}
