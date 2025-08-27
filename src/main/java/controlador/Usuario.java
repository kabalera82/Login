package controlador;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entidad que representa a un usuario del sistema.
 * <p>
 * Incluye los atributos identificador, nombre y contraseña, y está mapeada a una tabla de base de datos
 * mediante JPA.
 * </p>
 * 
 * @author kabalera
 */
@Entity
public class Usuario implements Serializable {
    
    /*** Identificador único del usuario (clave primaria).*/
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    /*** Nombre del usuario.*/
    private String nombre;

    /*** Contraseña del usuario.*/
    private String pass;
    
    /*** Constructor por defecto requerido por JPA.*/
    public Usuario(){}
    
    /**
     * Constructor con parámetros.
     * 
     * @param id Identificador del usuario.
     * @param nombre Nombre del usuario.
     * @param pass Contraseña del usuario.
     */
    public Usuario(int id, String nombre, String pass){
        this.id = id;
        this.nombre = nombre;
        this.pass = pass;
    }
    
    /**
     * Obtiene el identificador del usuario.
     * @return id del usuario.
     */
    public int getId(){return id;}

    /**
     * Establece el identificador del usuario.
     * @param id Nuevo identificador.
     */
    public void setId(int id){this.id = id;}
    
    /**
     * Obtiene el nombre del usuario.
     * @return nombre del usuario.
     */
    public String getNombre() {return nombre;}

    /**
     * Establece el nombre del usuario.
     * @param nombre Nuevo nombre.
     */
    public void setNombre(String nombre){this.nombre = nombre;}
    
    /**
     * Obtiene la contraseña del usuario.
     * @return contraseña.
     */
    public String getPass(){return pass;}

    /**
     * Establece la contraseña del usuario.
     * @param pass Nueva contraseña.
     */
    public void setPass(String pass){this.pass = pass;}
}