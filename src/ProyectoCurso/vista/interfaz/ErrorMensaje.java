package ProyectoCurso.vista.interfaz;

import java.awt.Component;
import javax.swing.JOptionPane;

public class ErrorMensaje 
{
    private Component frame;
    
    /**
     * Método que muestra un mensaje de error
     * @param titulo Variable que contiene el titulo de mensaje intruducido por el usuario
     * @param contenido Variable que contiene el contenido del mensaje introducido por el usuario
     */
    public ErrorMensaje(String titulo, String contenido)
    {
        JOptionPane.showMessageDialog(frame,contenido,titulo,JOptionPane.ERROR_MESSAGE);
    }
}
