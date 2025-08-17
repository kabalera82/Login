/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author kabalera
 */

@Entity
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String nombre;
    private String pass;
    
    public Usuario(){}
    
    public Usuario(int id, String nombre, String pass){
        this.id= id;
        this.nombre= nombre;
        this.pass = pass;
    }
    
    // Getters and setters
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre= nombre;
    }
    
    public String getPass(){
        return pass;
    }
    public void setPass(String contraseña){
        this.pass = pass;
    }

}
