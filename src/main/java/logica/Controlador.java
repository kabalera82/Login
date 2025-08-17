/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.List;
import persistencia.ControladorPersistencia;

/**
 *
 * @author kabalera
 */
public class Controlador {

    ControladorPersistencia controlPersis = new ControladorPersistencia();

    public String validarUsuario(String usuario, String pass) {
        
        String mensaje="";
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();
        
        for (Usuario usu : listaUsuarios) {
            if (usu.getNombre().equals(usuario)) {
                if (usu.getPass().equals(pass)) {
                    mensaje = "Usuario y contraseña Correctos";
                    return mensaje;
                    
                } else {
                    mensaje = "Contraseña incorrecta";
                    return mensaje;
                }
            } else {
                mensaje = "Usuario no encontrado";
            }
        }
        return mensaje;
    }

}
