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
import modelo.Informacion;
import modelo.Integrante;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Jennifer
 */
public class InformacionJpaController implements Serializable {

    public InformacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
     public InformacionJpaController(){
        this.emf = Persistence.createEntityManagerFactory("ProyectoDSIIPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Informacion informacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Integrante cedulaIntegrante = informacion.getCedulaIntegrante();
            if (cedulaIntegrante != null) {
                cedulaIntegrante = em.getReference(cedulaIntegrante.getClass(), cedulaIntegrante.getCedulaIntegrante());
                informacion.setCedulaIntegrante(cedulaIntegrante);
            }
            em.persist(informacion);
            if (cedulaIntegrante != null) {
                cedulaIntegrante.getInformacionList().add(informacion);
                cedulaIntegrante = em.merge(cedulaIntegrante);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Informacion informacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Informacion persistentInformacion = em.find(Informacion.class, informacion.getIdInformacion());
            Integrante cedulaIntegranteOld = persistentInformacion.getCedulaIntegrante();
            Integrante cedulaIntegranteNew = informacion.getCedulaIntegrante();
            if (cedulaIntegranteNew != null) {
                cedulaIntegranteNew = em.getReference(cedulaIntegranteNew.getClass(), cedulaIntegranteNew.getCedulaIntegrante());
                informacion.setCedulaIntegrante(cedulaIntegranteNew);
            }
            informacion = em.merge(informacion);
            if (cedulaIntegranteOld != null && !cedulaIntegranteOld.equals(cedulaIntegranteNew)) {
                cedulaIntegranteOld.getInformacionList().remove(informacion);
                cedulaIntegranteOld = em.merge(cedulaIntegranteOld);
            }
            if (cedulaIntegranteNew != null && !cedulaIntegranteNew.equals(cedulaIntegranteOld)) {
                cedulaIntegranteNew.getInformacionList().add(informacion);
                cedulaIntegranteNew = em.merge(cedulaIntegranteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = informacion.getIdInformacion();
                if (findInformacion(id) == null) {
                    throw new NonexistentEntityException("The informacion with id " + id + " no longer exists.");
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
            Informacion informacion;
            try {
                informacion = em.getReference(Informacion.class, id);
                informacion.getIdInformacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The informacion with id " + id + " no longer exists.", enfe);
            }
            Integrante cedulaIntegrante = informacion.getCedulaIntegrante();
            if (cedulaIntegrante != null) {
                cedulaIntegrante.getInformacionList().remove(informacion);
                cedulaIntegrante = em.merge(cedulaIntegrante);
            }
            em.remove(informacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Informacion> findInformacionEntities() {
        return findInformacionEntities(true, -1, -1);
    }

    public List<Informacion> findInformacionEntities(int maxResults, int firstResult) {
        return findInformacionEntities(false, maxResults, firstResult);
    }

    private List<Informacion> findInformacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Informacion.class));
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

    public Informacion findInformacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Informacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getInformacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Informacion> rt = cq.from(Informacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
