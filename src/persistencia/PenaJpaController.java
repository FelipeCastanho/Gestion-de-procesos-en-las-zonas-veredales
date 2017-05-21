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
import modelo.Integrante;
import modelo.Pena;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Jennifer
 */
public class PenaJpaController implements Serializable {

    public PenaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public PenaJpaController(){
        this.emf = Persistence.createEntityManagerFactory("ProyectoDSIIPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pena pena) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Integrante cedulaIntegrante = pena.getCedulaIntegrante();
            if (cedulaIntegrante != null) {
                cedulaIntegrante = em.getReference(cedulaIntegrante.getClass(), cedulaIntegrante.getCedulaIntegrante());
                pena.setCedulaIntegrante(cedulaIntegrante);
            }
            em.persist(pena);
            if (cedulaIntegrante != null) {
                cedulaIntegrante.getPenaList().add(pena);
                cedulaIntegrante = em.merge(cedulaIntegrante);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pena pena) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pena persistentPena = em.find(Pena.class, pena.getIdPena());
            Integrante cedulaIntegranteOld = persistentPena.getCedulaIntegrante();
            Integrante cedulaIntegranteNew = pena.getCedulaIntegrante();
            if (cedulaIntegranteNew != null) {
                cedulaIntegranteNew = em.getReference(cedulaIntegranteNew.getClass(), cedulaIntegranteNew.getCedulaIntegrante());
                pena.setCedulaIntegrante(cedulaIntegranteNew);
            }
            pena = em.merge(pena);
            if (cedulaIntegranteOld != null && !cedulaIntegranteOld.equals(cedulaIntegranteNew)) {
                cedulaIntegranteOld.getPenaList().remove(pena);
                cedulaIntegranteOld = em.merge(cedulaIntegranteOld);
            }
            if (cedulaIntegranteNew != null && !cedulaIntegranteNew.equals(cedulaIntegranteOld)) {
                cedulaIntegranteNew.getPenaList().add(pena);
                cedulaIntegranteNew = em.merge(cedulaIntegranteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pena.getIdPena();
                if (findPena(id) == null) {
                    throw new NonexistentEntityException("The pena with id " + id + " no longer exists.");
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
            Pena pena;
            try {
                pena = em.getReference(Pena.class, id);
                pena.getIdPena();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pena with id " + id + " no longer exists.", enfe);
            }
            Integrante cedulaIntegrante = pena.getCedulaIntegrante();
            if (cedulaIntegrante != null) {
                cedulaIntegrante.getPenaList().remove(pena);
                cedulaIntegrante = em.merge(cedulaIntegrante);
            }
            em.remove(pena);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pena> findPenaEntities() {
        return findPenaEntities(true, -1, -1);
    }

    public List<Pena> findPenaEntities(int maxResults, int firstResult) {
        return findPenaEntities(false, maxResults, firstResult);
    }

    private List<Pena> findPenaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pena.class));
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

    public Pena findPena(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pena.class, id);
        } finally {
            em.close();
        }
    }

    public int getPenaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pena> rt = cq.from(Pena.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
