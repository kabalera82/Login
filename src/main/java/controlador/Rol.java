package controlador;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entidad que representa el Rol de los usuarios del sistema.
 * <p>
 * Incluye los atributos identificador, nombre y descripción del rol, y está mapeada a una tabla de base de datos
 * mediante JPA.
 * </p>
 * 
 * @author kabalera
 */
@Entity
public class Rol implements Serializable {

    /** Identificador único del rol (clave primaria). */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    /** Nombre del rol. */
    private String nombreRol;

    /** Descripción del rol. */
    private String descripcion;

    /** Constructor por defecto requerido por JPA. */
    public Rol(){}

    /**
     * Constructor con parámetros.
     * 
     * @param id Identificador del rol.
     * @param nombreRol Nombre del rol.
     * @param descripcion Descripción del rol.
     */
    public Rol(int id, String nombreRol, String descripcion){
        this.id = id;
        this.nombreRol = nombreRol;
        this.descripcion = descripcion;
    }

    /**
     * Recupera el identificador del rol.
     * @return id del rol.
     */
    public int getId() {return id;}

    /**
     * Establece el identificador del rol.
     * @param id Nuevo identificador.
     */
    public void setId(int id) {this.id = id;}

    /**
     * Recupera el nombre del rol.
     * @return nombre del rol.
     */
    public String getNombreRol() {return nombreRol;}

    /**
     * Establece el nombre del rol.
     * @param nombreRol Nuevo nombre del rol.
     */
    public void setNombreRol(String nombreRol) {this.nombreRol = nombreRol;}

    /**
     * Recupera la descripción del rol.
     * @return descripción del rol.
     */
    public String getDescripcion() {return descripcion;}

    /**
     * Establece la descripción del rol.
     * @param descripcion Nueva descripción del rol.
     */
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
}