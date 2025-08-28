package persistencia;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import controlador.Rol;
import controlador.Usuario;

/**
 * Fachada de persistencia para Usuarios y Roles.
 * Centraliza llamadas a los controladores JPA y ofrece métodos de uso común.
 */
public class ControladorPersistencia {

    private static final String PU_NAME = "loginPU";

    private final EntityManagerFactory emf;
    private final UsuarioJpaController usuJpa;
    private final RolJpaController rolJpa;

    /** Constructor por defecto: usa la PU definida en persistence.xml */
    public ControladorPersistencia() {
        this(Persistence.createEntityManagerFactory(PU_NAME));
    }

    /** Constructor inyectable (tests/contenedor) */
    public ControladorPersistencia(EntityManagerFactory emf) {
        this.emf = emf;
        this.usuJpa = new UsuarioJpaController(this.emf);
        this.rolJpa = new RolJpaController(this.emf);
    }

    /* ===================== USUARIOS ===================== */

    /** SELECT * FROM Usuario */
    public List<Usuario> traerUsuarios() {
        return usuJpa.findUsuarioEntities();
    }

    /** SELECT * FROM Usuario con paginado */
    public List<Usuario> traerUsuarios(int max, int first) {
        return usuJpa.findUsuarioEntities(max, first);
    }

    /** SELECT u FROM Usuario u WHERE u.id = :id */
    public Usuario traerUsuarioPorId(int id) {
        return usuJpa.findUsuario(id);
    }

    /** SELECT u FROM Usuario u WHERE u.nombre = :nombre */
    public Usuario traerUsuarioPorNombre(String nombre) {
        return usuJpa.findByNombre(nombre);
    }

    /** Crea un nuevo usuario */
    public void crearUsuario(Usuario u) {
        usuJpa.create(u);
    }

    /** Actualiza un usuario existente */
    public void editarUsuario(Usuario u) {
        usuJpa.edit(u);
    }

    /** Elimina un usuario por id */
    public void eliminarUsuario(int id) {
        usuJpa.destroy(id);
    }

    /** COUNT(*) de usuarios */
    public int contarUsuarios() {
        return usuJpa.getUsuarioCount();
    }

    /**
     * Valida credenciales: consulta directa por nombre y pass.
     * En producción, compara contra hash (no guardes pass en claro).
     * @return true si coincide usuario+pass; false en caso contrario.
     */
    public boolean validarCredenciales(String nombre, String pass) {
        return usuJpa.findByNombreAndPass(nombre, pass) != null;
    }

    /* ======================= ROLES ======================= */

    /** SELECT * FROM Rol */
    public List<Rol> traerRoles() {
        return rolJpa.findRolEntities();
    }

    public List<Rol> traerRoles(int max, int first) {
        return rolJpa.findRolEntities(max, first);
    }

    public Rol traerRolPorId(int id) {
        return rolJpa.findRol(id);
    }

    public Rol traerRolPorNombre(String nombreRol) {
        return rolJpa.findByNombreRol(nombreRol);
    }

    public void crearRol(Rol r) {
        rolJpa.create(r);
    }

    public void editarRol(Rol r) {
        rolJpa.edit(r);
    }

    public void eliminarRol(int id) {
        rolJpa.destroy(id);
    }

    public int contarRoles() {
        return rolJpa.getRolCount();
    }
}
