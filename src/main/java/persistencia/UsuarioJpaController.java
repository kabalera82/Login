package persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import controlador.Usuario;

/**
 * Controlador JPA para la entidad {@link Usuario}.
 * <p>
 * Proporciona métodos para crear, editar, eliminar, buscar y listar usuarios en la base de datos
 * utilizando la API de persistencia JPA.
 * </p>
 * 
 * <p>
 * El nombre de la unidad de persistencia debe coincidir con el definido en el archivo persistence.xml.
 * </p>
 * 
 * @author kabalera
 */
public class UsuarioJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    /**
     * Constructor por defecto.
     * Inicializa la factoría de entidades con la unidad de persistencia "loginPU".
     */
    public UsuarioJpaController() {
        // El nombre "loginPU" debe coincidir con el <persistence-unit name="loginPU">
        this.emf = Persistence.createEntityManagerFactory("loginPU");
    }

    /**
     * Obtiene una nueva instancia de {@link EntityManager} para operaciones de persistencia.
     *
     * @return EntityManager para interactuar con la base de datos.
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Persiste un nuevo usuario en la base de datos.
     *
     * @param usuario Usuario a crear.
     */
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

    /**
     * Actualiza un usuario existente en la base de datos.
     *
     * @param usuario Usuario con los datos actualizados.
     * @throws RuntimeException si ocurre un error durante la edición.
     */
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

    /**
     * Elimina un usuario de la base de datos por su identificador.
     *
     * @param id Identificador del usuario a eliminar.
     * @throws RuntimeException si el usuario no existe.
     */
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

    /**
     * Busca un usuario por su identificador.
     *
     * @param id Identificador del usuario.
     * @return Usuario encontrado o {@code null} si no existe.
     */
    public Usuario findUsuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene la lista de todos los usuarios registrados en la base de datos.
     *
     * @return Lista de usuarios.
     */
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

    /**
     * Obtiene la cantidad total de usuarios registrados en la base de datos.
     *
     * @return Número de usuarios.
     */
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