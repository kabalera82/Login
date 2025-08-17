/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package persistencia;

import java.util.List;
import logica.Usuario;

/**
 *
 * @author kabalera
 */
public class ControladorPersistencia {
    UsuarioJpaController usuJpa = new UsuarioJpaController ();
    
    // metodo para traer todos los usuarios.

    public List<Usuario> traerUsuarios() {
        return usuJpa.findUsuarioEntities();
        //SELECT * FROM USUARIOS
    }
}
