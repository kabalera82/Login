package controlador;

import java.util.List;

import persistencia.ControladorPersistencia;

/**
 * Clase de lógica de negocio para la autenticación de usuarios.
 * <p>
 * Proporciona métodos para validar credenciales de usuario utilizando la capa de persistencia.
 * </p>
 * 
 * @author kabalera
 */
public class Controlador {

    /**
     * Instancia del controlador de persistencia para acceder a los datos de usuarios.
     */
    private ControladorPersistencia controlPersis = new ControladorPersistencia();

    /**
     * Valida las credenciales de un usuario.
     *
     * @param usuario Nombre de usuario a validar.
     * @param pass    Contraseña a validar.
     * @return Mensaje indicando el resultado de la validación.
     */
    public String validarUsuario(String usuario, String pass) {
        String mensaje = "";
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