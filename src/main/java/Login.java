import igu.Autenticacion;
import persistencia.UsuarioJpaController;

/**
 * Clase principal del sistema de Login.
 * Inicializa la capa de persistencia y lanza la interfaz de autenticación.
 * 
 * @author kabal
 */
public class Login {

    /**
     * Método principal. Inicializa la base de datos y muestra la ventana de autenticación.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // Inicializa el controlador JPA para asegurar la conexión y la creación de tablas
        UsuarioJpaController controller = new UsuarioJpaController();
        controller.getUsuarioCount();  // Ejecuta un SELECT para disparar la creación de tablas si es necesario

        // Crea y muestra la interfaz de autenticación
        Autenticacion autenticacion = new Autenticacion();
        autenticacion.setVisible(true);
        autenticacion.setLocationRelativeTo(null);
    }
}