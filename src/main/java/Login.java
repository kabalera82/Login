
import igu.Autenticacion;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistencia.UsuarioJpaController;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author kabal
 */

public class Login {

    public static void main(String[] args) {
    // fuerza inicialización de JPA
    UsuarioJpaController controller = new UsuarioJpaController();
    controller.getUsuarioCount();  // hace un SELECT y dispara DDL si no existen las tablas

    // ahora abre la interfaz
    Autenticacion autenticacion = new Autenticacion();
    autenticacion.setVisible(true);
    autenticacion.setLocationRelativeTo(null);
}

}