package login.kabadev.persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import login.kabadev.controlador.Rol;
import login.kabadev.controlador.Usuario;
import login.kabadev.persistencia.exceptions.NonExistentEntityException;

/**
 * Fachada de persistencia para Usuarios y Roles.
 * Centraliza llamadas a los controladores JPA y ofrece métodos de uso común.
 */
public class ControladorPersistencia {


    UsuarioJpaController usuJpa = new UsuarioJpaController();
    RolJpaController rolJpa = new RolJpaController();

    public List<Usuario> traerUsuarios() {

        List<Usuario> listaUsuario = usuJpa.findUsuarioEntities();
        return listaUsuario;
    }

    public List<Rol> traerRoles() {
        return rolJpa.findRolEntities();

    }

    public void crearUsuario(Usuario usu) {
        usuJpa.create(usu);
    }

    public void borrarUsuario(int id_usuario) {
        try {
            usuJpa.destroy(id_usuario);
        } catch (NonExistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario traerUsuario(int id_usuario) {
        return usuJpa.findUsuario(id_usuario);
    }

    public void editarUsuario(Usuario usu) {
        try {
            usuJpa.edit(usu);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
