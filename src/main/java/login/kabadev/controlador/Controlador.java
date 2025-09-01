package login.kabadev.controlador;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import login.kabadev.persistencia.ControladorPersistencia;

public class Controlador {

    private final ControladorPersistencia controlPersis;

    public Controlador() {
        this.controlPersis = new ControladorPersistencia();
    }

    /**
     * Valida credenciales. Devuelve el Usuario si coinciden, o null si no.
     */
    public Usuario validarUsuario(String nombre, String pass) {
        String n = nombre == null ? "" : nombre.trim();
        String p = pass == null ? "" : pass.trim();

        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();
        if (listaUsuarios == null || listaUsuarios.isEmpty()) return null;

        for (Usuario u : listaUsuarios) {
            if (u == null) continue;
            String un = u.getNombre();
            String up = u.getPass();
            if (un != null && up != null && un.equals(n) && up.equals(p)) {
                return u;
            }
        }
        return null;
    }

    public List<Usuario> traerUsuarios() {
        return controlPersis.traerUsuarios();
    }

    public List<Rol> traerRoles() {
        return controlPersis.traerRoles();
    }

    /**
     * Alta de usuario.
     * Si tu tabla USUARIO no tiene AUTO_INCREMENT, se asigna ID max+1.
     * Si TIENE AUTO_INCREMENT, comenta la línea de setId(...)
     */
    public void crearUsuario(String usuario, String contra, String rolRecibido) {
        String u = usuario == null ? "" : usuario.trim();
        String p = contra  == null ? "" : contra.trim();

        if (u.isEmpty() || p.isEmpty()) {
            throw new IllegalArgumentException("Nombre y contraseña son obligatorios");
        }

        Usuario usu = new Usuario();
        usu.setNombre(u);
        usu.setPass(p);

        // Resolver rol por nombre (puede ser null si no existe)
        Rol rolEncontrado = traerRol(rolRecibido);
        if (rolEncontrado != null) {
            usu.setUnRol(rolEncontrado);
        }
        // Si QUIERES forzar rol por defecto cuando no exista:
        // else {
        //     Rol rolUser = traerRol("user");
        //     if (rolUser != null) usu.setUnRol(rolUser);
        // }

        // Asignar ID SOLO si NO tienes AUTO_INCREMENT en BD
        int id = buscarSiguienteIdUsuarios();
        usu.setId(id);

        controlPersis.crearUsuario(usu);
    }

    private Rol traerRol(String rolRecibido) {
        if (rolRecibido == null) return null;
        List<Rol> listaRoles = controlPersis.traerRoles();
        if (listaRoles == null || listaRoles.isEmpty()) return null;

        for (Rol rol : listaRoles) {
            if (rol != null && Objects.equals(rol.getNombreRol(), rolRecibido)) {
                return rol;
            }
        }
        return null;
    }

    /**
     * Obtiene el siguiente ID seguro (max + 1).
     * Si la lista está vacía -> 1
     */
    private int buscarSiguienteIdUsuarios() {
        List<Usuario> listaUsuarios = this.traerUsuarios();
        if (listaUsuarios == null || listaUsuarios.isEmpty()) {
            return 1;
        }
        // max(id) + 1 para no depender de orden
        Integer max = listaUsuarios.stream()
                .filter(Objects::nonNull)
                .map(Usuario::getId)
                .filter(Objects::nonNull)
                .max(Comparator.naturalOrder())
                .orElse(0);
        return max + 1;
    }

    public void borrarUsuario(int id_usuario) {
        controlPersis.borrarUsuario(id_usuario);
    }

    public Usuario traerUsuario(int id_usuario) {
        return controlPersis.traerUsuario(id_usuario);
    }

    public void editarUsuario(Usuario usu, String usuario, String contra, String rolRecibido) {
        if (usu == null) throw new IllegalArgumentException("Usuario inexistente");

        String u = usuario == null ? "" : usuario.trim();
        String p = contra  == null ? "" : contra.trim();
        if (u.isEmpty() || p.isEmpty()) {
            throw new IllegalArgumentException("Nombre y contraseña son obligatorios");
        }

        usu.setNombre(u);
        usu.setPass(p);

        Rol rolEncontrado = traerRol(rolRecibido);
        if (rolEncontrado != null) {
            usu.setUnRol(rolEncontrado);
        }
        // Si prefieres forzar un rol por defecto cuando venga nulo, descomenta:
        // else {
        //     Rol rolUser = traerRol("user");
        //     if (rolUser != null) usu.setUnRol(rolUser);
        // }

        controlPersis.editarUsuario(usu);
    }
}
