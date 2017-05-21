/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Zonaveredal;
import modelo.Autoridad;
import modelo.Autoridadingresazonaveredal;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Jennifer
 */
public class AutoridadingresazonaveredalJpaController implements Serializable {

    public AutoridadingresazonaveredalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
     public AutoridadingresazonaveredalJpaController(){
        this.emf = Persistence.createEntityManagerFactory("ProyectoDSIIPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Autoridadingresazonaveredal autoridadingresazonaveredal) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Zonaveredal idZonaVeredal = autoridadingresazonaveredal.getIdZonaVeredal();
            if (idZonaVeredal != null) {
                idZonaVeredal = em.getReference(idZonaVeredal.getClass(), idZonaVeredal.getIdZonaVeredal());
                autoridadingresazonaveredal.setIdZonaVeredal(idZonaVeredal);
            }
            Autoridad idAutoridad = autoridadingresazonaveredal.getIdAutoridad();
            if (idAutoridad != null) {
                idAutoridad = em.getReference(idAutoridad.getClass(), idAutoridad.getIdAutoridad());
                autoridadingresazonaveredal.setIdAutoridad(idAutoridad);
            }
            em.persist(autoridadingresazonaveredal);
            if (idZonaVeredal != null) {
                idZonaVeredal.getAutoridadingresazonaveredalList().add(autoridadingresazonaveredal);
                idZonaVeredal = em.merge(idZonaVeredal);
            }
            if (idAutoridad != null) {
                idAutoridad.getAutoridadingresazonaveredalList().add(autoridadingresazonaveredal);
                idAutoridad = em.merge(idAutoridad);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAutoridadingresazonaveredal(autoridadingresazonaveredal.getIdIngreso()) != null) {
                throw new PreexistingEntityException("Autoridadingresazonaveredal " + autoridadingresazonaveredal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Autoridadingresazonaveredal autoridadingresazonaveredal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autoridadingresazonaveredal persistentAutoridadingresazonaveredal = em.find(Autoridadingresazonaveredal.class, autoridadingresazonaveredal.getIdIngreso());
            Zonaveredal idZonaVeredalOld = persistentAutoridadingresazonaveredal.getIdZonaVeredal();
            Zonaveredal idZonaVeredalNew = autoridadingresazonaveredal.getIdZonaVeredal();
            Autoridad idAutoridadOld = persistentAutoridadingresazonaveredal.getIdAutoridad();
            Autoridad idAutoridadNew = autoridadingresazonaveredal.getIdAutoridad();
            if (idZonaVeredalNew != null) {
                idZonaVeredalNew = em.getReference(idZonaVeredalNew.getClass(), idZonaVeredalNew.getIdZonaVeredal());
                autoridadingresazonaveredal.setIdZonaVeredal(idZonaVeredalNew);
            }
            if (idAutoridadNew != null) {
                idAutoridadNew = em.getReference(idAutoridadNew.getClass(), idAutoridadNew.getIdAutoridad());
                autoridadingresazonaveredal.setIdAutoridad(idAutoridadNew);
            }
            autoridadingresazonaveredal = em.merge(autoridadingresazonaveredal);
            if (idZonaVeredalOld != null && !idZonaVeredalOld.equals(idZonaVeredalNew)) {
                idZonaVeredalOld.getAutoridadingresazonaveredalList().remove(autoridadingresazonaveredal);
                idZonaVeredalOld = em.merge(idZonaVeredalOld);
            }
            if (idZonaVeredalNew != null && !idZonaVeredalNew.equals(idZonaVeredalOld)) {
                idZonaVeredalNew.getAutoridadingresazonaveredalList().add(autoridadingresazonaveredal);
                idZonaVeredalNew = em.merge(idZonaVeredalNew);
            }
            if (idAutoridadOld != null && !idAutoridadOld.equals(idAutoridadNew)) {
                idAutoridadOld.getAutoridadingresazonaveredalList().remove(autoridadingresazonaveredal);
                idAutoridadOld = em.merge(idAutoridadOld);
            }
            if (idAutoridadNew != null && !idAutoridadNew.equals(idAutoridadOld)) {
                idAutoridadNew.getAutoridadingresazonaveredalList().add(autoridadingresazonaveredal);
                idAutoridadNew = em.merge(idAutoridadNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = autoridadingresazonaveredal.getIdIngreso();
                if (findAutoridadingresazonaveredal(id) == null) {
                    throw new NonexistentEntityException("The autoridadingresazonaveredal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autoridadingresazonaveredal autoridadingresazonaveredal;
            try {
                autoridadingresazonaveredal = em.getReference(Autoridadingresazonaveredal.class, id);
                autoridadingresazonaveredal.getIdIngreso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autoridadingresazonaveredal with id " + id + " no longer exists.", enfe);
            }
            Zonaveredal idZonaVeredal = autoridadingresazonaveredal.getIdZonaVeredal();
            if (idZonaVeredal != null) {
                idZonaVeredal.getAutoridadingresazonaveredalList().remove(autoridadingresazonaveredal);
                idZonaVeredal = em.merge(idZonaVeredal);
            }
            Autoridad idAutoridad = autoridadingresazonaveredal.getIdAutoridad();
            if (idAutoridad != null) {
                idAutoridad.getAutoridadingresazonaveredalList().remove(autoridadingresazonaveredal);
                idAutoridad = em.merge(idAutoridad);
            }
            em.remove(autoridadingresazonaveredal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Autoridadingresazonaveredal> findAutoridadingresazonaveredalEntities() {
        return findAutoridadingresazonaveredalEntities(true, -1, -1);
    }

    public List<Autoridadingresazonaveredal> findAutoridadingresazonaveredalEntities(int maxResults, int firstResult) {
        return findAutoridadingresazonaveredalEntities(false, maxResults, firstResult);
    }

    private List<Autoridadingresazonaveredal> findAutoridadingresazonaveredalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Autoridadingresazonaveredal.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Autoridadingresazonaveredal findAutoridadingresazonaveredal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Autoridadingresazonaveredal.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutoridadingresazonaveredalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Autoridadingresazonaveredal> rt = cq.from(Autoridadingresazonaveredal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
