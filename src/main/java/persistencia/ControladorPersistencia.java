package persistencia;

import java.util.List;

import controlador.Usuario;

/**
 * Clase de fachada para la capa de persistencia.
 * <p>
 * Proporciona métodos para interactuar con los datos de usuarios a través del controlador JPA.
 * </p>
 * 
 * @author kabalera
 */
public class ControladorPersistencia {
    /**
     * Controlador JPA para operaciones sobre la entidad Usuario.
     */
    private UsuarioJpaController usuJpa = new UsuarioJpaController();

    /**
     * Recupera la lista de todos los usuarios registrados en la base de datos.
     *
     * @return Lista de objetos {@link Usuario}.
     */
    public List<Usuario> traerUsuarios() {
        return usuJpa.findUsuarioEntities();
        // SELECT * FROM USUARIOS
    }
}