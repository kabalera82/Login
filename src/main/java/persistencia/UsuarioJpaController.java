/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import logica.Usuario;

/**
 *
 * @author kabalera
 */
public class UsuarioJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    // == CREAMOS UN CONSTRUCTOR ===============================================
    public UsuarioJpaController() {
        // El nombre "loginPU" debe coincidir con el <persistence-unit name="loginPU">
        this.emf = Persistence.createEntityManagerFactory("loginPU");
    }
    
   
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear un nuevo usuario
    public void create(Usuario usuario) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar un usuario existente
    public void edit(Usuario usuario) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            usuario = em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new RuntimeException("Error al editar el usuario con id " + usuario.getId(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Eliminar un usuario por ID
    public void destroy(int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId(); // Forzar carga
            } catch (EntityNotFoundException enfe) {
                throw new RuntimeException("El usuario con id " + id + " no existe.", enfe);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Buscar un usuario por ID
    public Usuario findUsuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    // Listar todos los usuarios
    @SuppressWarnings("unchecked")
    public List<Usuario> findUsuarioEntities() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT u FROM Usuario u");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    // Contar usuarios en la BD
    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT COUNT(u) FROM Usuario u");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
