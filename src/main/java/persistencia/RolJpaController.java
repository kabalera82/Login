package persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import controlador.Rol;

/**
 * Controlador JPA para la entidad {@link Rol}.
 * Estructura paralela a UsuarioJpaController.
 *
 * Requiere que la entidad Rol esté mapeada y incluida en la unidad de persistencia.
 * El nombre de la PU debe coincidir con persistence.xml.
 */
public class RolJpaController implements Serializable {

    private static final String PU_NAME = "loginPU";

    private static EntityManagerFactory defaultEmf;

    private final EntityManagerFactory emf;

    public RolJpaController() {
        synchronized (RolJpaController.class) {
            if (defaultEmf == null) {
                defaultEmf = Persistence.createEntityManagerFactory(PU_NAME);
            }
        }
        this.emf = defaultEmf;
    }

    public RolJpaController(EntityManagerFactory emf) {
        this.emf = (emf != null) ? emf : getDefaultEmf();
    }

    private static EntityManagerFactory getDefaultEmf() {
        synchronized (RolJpaController.class) {
            if (defaultEmf == null) {
                defaultEmf = Persistence.createEntityManagerFactory(PU_NAME);
            }
            return defaultEmf;
        }
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rol rol) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(rol);
            em.getTransaction().commit();
        } catch (Exception ex) {
            safeRollback(em);
            throw new RuntimeException("Error al crear Rol", ex);
        } finally {
            em.close();
        }
    }

    public void edit(Rol rol) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(rol);
            em.getTransaction().commit();
        } catch (Exception ex) {
            safeRollback(em);
            throw new RuntimeException("Error al editar Rol id=" + rol.getId(), ex);
        } finally {
            em.close();
        }
    }

    public void destroy(int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Rol rol;
            try {
                rol = em.getReference(Rol.class, id);
                rol.getId(); // fuerza carga
            } catch (EntityNotFoundException enfe) {
                throw new RuntimeException("El rol con id " + id + " no existe.", enfe);
            }
            em.remove(rol);
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            safeRollback(em);
            throw ex;
        } finally {
            em.close();
        }
    }

    public Rol findRol(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rol.class, id);
        } finally {
            em.close();
        }
    }

    public List<Rol> findRolEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Rol> q = em.createQuery("SELECT r FROM Rol r", Rol.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Rol> findRolEntities(int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Rol> q = em.createQuery("SELECT r FROM Rol r", Rol.class);
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getRolCount() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Long> q = em.createQuery("SELECT COUNT(r) FROM Rol r", Long.class);
            return q.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

    /** Búsqueda por nombre exacto del rol */
    public Rol findByNombreRol(String nombreRol) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Rol> q = em.createQuery(
                "SELECT r FROM Rol r WHERE r.nombreRol = :nombreRol", Rol.class);
            q.setParameter("nombreRol", nombreRol);
            return q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }
    }

    private void safeRollback(EntityManager em) {
        try {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } catch (Exception ignore) {}
    }
}
