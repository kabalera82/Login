/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package login.kabadev.controlador;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author kabalera
 */
@Entity
public class Rol implements Serializable {

    /** Identificador único del rol (clave primaria). */
    @Id
    private int id;

    /** Nombre del rol. */
    private String nombreRol;

    /** Descripción del rol. */
    private String descripcion;

    @OneToMany (mappedBy = "unRol")
    private List<Usuario> listaUsuarios;
    /** Constructor por defecto requerido por JPA. */
    public Rol(){}

    /**
     * Constructor con parámetros.
     *
     * @param id Identificador del rol.
     * @param nombreRol Nombre del rol.
     * @param descripcion Descripción del rol.
     */
    public Rol(int id, String nombreRol, String descripcion, List<Usuario> listaUsuarios){
        this.id = id;
        this.nombreRol = nombreRol;
        this.descripcion = descripcion;
        this.listaUsuarios= listaUsuarios;
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

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
}
