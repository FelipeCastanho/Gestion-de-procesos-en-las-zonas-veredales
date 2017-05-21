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
import modelo.Arma;
import modelo.Integrante;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Jennifer
 */
public class ArmaJpaController implements Serializable {

    public ArmaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public ArmaJpaController(){
        this.emf = Persistence.createEntityManagerFactory("ProyectoDSIIPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Arma arma) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Integrante cedulaIntegrante = arma.getCedulaIntegrante();
            if (cedulaIntegrante != null) {
                cedulaIntegrante = em.getReference(cedulaIntegrante.getClass(), cedulaIntegrante.getCedulaIntegrante());
                arma.setCedulaIntegrante(cedulaIntegrante);
            }
            em.persist(arma);
            if (cedulaIntegrante != null) {
                cedulaIntegrante.getArmaList().add(arma);
                cedulaIntegrante = em.merge(cedulaIntegrante);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Arma arma) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Arma persistentArma = em.find(Arma.class, arma.getIdArma());
            Integrante cedulaIntegranteOld = persistentArma.getCedulaIntegrante();
            Integrante cedulaIntegranteNew = arma.getCedulaIntegrante();
            if (cedulaIntegranteNew != null) {
                cedulaIntegranteNew = em.getReference(cedulaIntegranteNew.getClass(), cedulaIntegranteNew.getCedulaIntegrante());
                arma.setCedulaIntegrante(cedulaIntegranteNew);
            }
            arma = em.merge(arma);
            if (cedulaIntegranteOld != null && !cedulaIntegranteOld.equals(cedulaIntegranteNew)) {
                cedulaIntegranteOld.getArmaList().remove(arma);
                cedulaIntegranteOld = em.merge(cedulaIntegranteOld);
            }
            if (cedulaIntegranteNew != null && !cedulaIntegranteNew.equals(cedulaIntegranteOld)) {
                cedulaIntegranteNew.getArmaList().add(arma);
                cedulaIntegranteNew = em.merge(cedulaIntegranteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = arma.getIdArma();
                if (findArma(id) == null) {
                    throw new NonexistentEntityException("The arma with id " + id + " no longer exists.");
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
            Arma arma;
            try {
                arma = em.getReference(Arma.class, id);
                arma.getIdArma();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The arma with id " + id + " no longer exists.", enfe);
            }
            Integrante cedulaIntegrante = arma.getCedulaIntegrante();
            if (cedulaIntegrante != null) {
                cedulaIntegrante.getArmaList().remove(arma);
                cedulaIntegrante = em.merge(cedulaIntegrante);
            }
            em.remove(arma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Arma> findArmaEntities() {
        return findArmaEntities(true, -1, -1);
    }

    public List<Arma> findArmaEntities(int maxResults, int firstResult) {
        return findArmaEntities(false, maxResults, firstResult);
    }

    private List<Arma> findArmaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Arma.class));
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

    public Arma findArma(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Arma.class, id);
        } finally {
            em.close();
        }
    }

    public int getArmaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Arma> rt = cq.from(Arma.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
