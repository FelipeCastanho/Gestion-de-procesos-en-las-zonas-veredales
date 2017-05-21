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
import modelo.Capacitacion;
import modelo.Integrante;
import modelo.Integranteasistecapacitacion;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Jennifer
 */
public class IntegranteasistecapacitacionJpaController implements Serializable {

    public IntegranteasistecapacitacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public IntegranteasistecapacitacionJpaController(){
        this.emf = Persistence.createEntityManagerFactory("ProyectoDSIIPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Integranteasistecapacitacion integranteasistecapacitacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Capacitacion idCapacitacion = integranteasistecapacitacion.getIdCapacitacion();
            if (idCapacitacion != null) {
                idCapacitacion = em.getReference(idCapacitacion.getClass(), idCapacitacion.getIdCapacitacion());
                integranteasistecapacitacion.setIdCapacitacion(idCapacitacion);
            }
            Integrante cedulaIntegrante = integranteasistecapacitacion.getCedulaIntegrante();
            if (cedulaIntegrante != null) {
                cedulaIntegrante = em.getReference(cedulaIntegrante.getClass(), cedulaIntegrante.getCedulaIntegrante());
                integranteasistecapacitacion.setCedulaIntegrante(cedulaIntegrante);
            }
            em.persist(integranteasistecapacitacion);
            if (idCapacitacion != null) {
                idCapacitacion.getIntegranteasistecapacitacionList().add(integranteasistecapacitacion);
                idCapacitacion = em.merge(idCapacitacion);
            }
            if (cedulaIntegrante != null) {
                cedulaIntegrante.getIntegranteasistecapacitacionList().add(integranteasistecapacitacion);
                cedulaIntegrante = em.merge(cedulaIntegrante);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Integranteasistecapacitacion integranteasistecapacitacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Integranteasistecapacitacion persistentIntegranteasistecapacitacion = em.find(Integranteasistecapacitacion.class, integranteasistecapacitacion.getIdAsiste());
            Capacitacion idCapacitacionOld = persistentIntegranteasistecapacitacion.getIdCapacitacion();
            Capacitacion idCapacitacionNew = integranteasistecapacitacion.getIdCapacitacion();
            Integrante cedulaIntegranteOld = persistentIntegranteasistecapacitacion.getCedulaIntegrante();
            Integrante cedulaIntegranteNew = integranteasistecapacitacion.getCedulaIntegrante();
            if (idCapacitacionNew != null) {
                idCapacitacionNew = em.getReference(idCapacitacionNew.getClass(), idCapacitacionNew.getIdCapacitacion());
                integranteasistecapacitacion.setIdCapacitacion(idCapacitacionNew);
            }
            if (cedulaIntegranteNew != null) {
                cedulaIntegranteNew = em.getReference(cedulaIntegranteNew.getClass(), cedulaIntegranteNew.getCedulaIntegrante());
                integranteasistecapacitacion.setCedulaIntegrante(cedulaIntegranteNew);
            }
            integranteasistecapacitacion = em.merge(integranteasistecapacitacion);
            if (idCapacitacionOld != null && !idCapacitacionOld.equals(idCapacitacionNew)) {
                idCapacitacionOld.getIntegranteasistecapacitacionList().remove(integranteasistecapacitacion);
                idCapacitacionOld = em.merge(idCapacitacionOld);
            }
            if (idCapacitacionNew != null && !idCapacitacionNew.equals(idCapacitacionOld)) {
                idCapacitacionNew.getIntegranteasistecapacitacionList().add(integranteasistecapacitacion);
                idCapacitacionNew = em.merge(idCapacitacionNew);
            }
            if (cedulaIntegranteOld != null && !cedulaIntegranteOld.equals(cedulaIntegranteNew)) {
                cedulaIntegranteOld.getIntegranteasistecapacitacionList().remove(integranteasistecapacitacion);
                cedulaIntegranteOld = em.merge(cedulaIntegranteOld);
            }
            if (cedulaIntegranteNew != null && !cedulaIntegranteNew.equals(cedulaIntegranteOld)) {
                cedulaIntegranteNew.getIntegranteasistecapacitacionList().add(integranteasistecapacitacion);
                cedulaIntegranteNew = em.merge(cedulaIntegranteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = integranteasistecapacitacion.getIdAsiste();
                if (findIntegranteasistecapacitacion(id) == null) {
                    throw new NonexistentEntityException("The integranteasistecapacitacion with id " + id + " no longer exists.");
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
            Integranteasistecapacitacion integranteasistecapacitacion;
            try {
                integranteasistecapacitacion = em.getReference(Integranteasistecapacitacion.class, id);
                integranteasistecapacitacion.getIdAsiste();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The integranteasistecapacitacion with id " + id + " no longer exists.", enfe);
            }
            Capacitacion idCapacitacion = integranteasistecapacitacion.getIdCapacitacion();
            if (idCapacitacion != null) {
                idCapacitacion.getIntegranteasistecapacitacionList().remove(integranteasistecapacitacion);
                idCapacitacion = em.merge(idCapacitacion);
            }
            Integrante cedulaIntegrante = integranteasistecapacitacion.getCedulaIntegrante();
            if (cedulaIntegrante != null) {
                cedulaIntegrante.getIntegranteasistecapacitacionList().remove(integranteasistecapacitacion);
                cedulaIntegrante = em.merge(cedulaIntegrante);
            }
            em.remove(integranteasistecapacitacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Integranteasistecapacitacion> findIntegranteasistecapacitacionEntities() {
        return findIntegranteasistecapacitacionEntities(true, -1, -1);
    }

    public List<Integranteasistecapacitacion> findIntegranteasistecapacitacionEntities(int maxResults, int firstResult) {
        return findIntegranteasistecapacitacionEntities(false, maxResults, firstResult);
    }

    private List<Integranteasistecapacitacion> findIntegranteasistecapacitacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Integranteasistecapacitacion.class));
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

    public Integranteasistecapacitacion findIntegranteasistecapacitacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Integranteasistecapacitacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getIntegranteasistecapacitacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Integranteasistecapacitacion> rt = cq.from(Integranteasistecapacitacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
