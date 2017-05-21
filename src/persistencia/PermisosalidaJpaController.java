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
import modelo.Permisosalida;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Jennifer
 */
public class PermisosalidaJpaController implements Serializable {

    public PermisosalidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public PermisosalidaJpaController(){
        this.emf = Persistence.createEntityManagerFactory("ProyectoDSIIPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Permisosalida permisosalida) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Integrante cedulaIntegrante = permisosalida.getCedulaIntegrante();
            if (cedulaIntegrante != null) {
                cedulaIntegrante = em.getReference(cedulaIntegrante.getClass(), cedulaIntegrante.getCedulaIntegrante());
                permisosalida.setCedulaIntegrante(cedulaIntegrante);
            }
            em.persist(permisosalida);
            if (cedulaIntegrante != null) {
                cedulaIntegrante.getPermisosalidaList().add(permisosalida);
                cedulaIntegrante = em.merge(cedulaIntegrante);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Permisosalida permisosalida) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Permisosalida persistentPermisosalida = em.find(Permisosalida.class, permisosalida.getIdSalida());
            Integrante cedulaIntegranteOld = persistentPermisosalida.getCedulaIntegrante();
            Integrante cedulaIntegranteNew = permisosalida.getCedulaIntegrante();
            if (cedulaIntegranteNew != null) {
                cedulaIntegranteNew = em.getReference(cedulaIntegranteNew.getClass(), cedulaIntegranteNew.getCedulaIntegrante());
                permisosalida.setCedulaIntegrante(cedulaIntegranteNew);
            }
            permisosalida = em.merge(permisosalida);
            if (cedulaIntegranteOld != null && !cedulaIntegranteOld.equals(cedulaIntegranteNew)) {
                cedulaIntegranteOld.getPermisosalidaList().remove(permisosalida);
                cedulaIntegranteOld = em.merge(cedulaIntegranteOld);
            }
            if (cedulaIntegranteNew != null && !cedulaIntegranteNew.equals(cedulaIntegranteOld)) {
                cedulaIntegranteNew.getPermisosalidaList().add(permisosalida);
                cedulaIntegranteNew = em.merge(cedulaIntegranteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = permisosalida.getIdSalida();
                if (findPermisosalida(id) == null) {
                    throw new NonexistentEntityException("The permisosalida with id " + id + " no longer exists.");
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
            Permisosalida permisosalida;
            try {
                permisosalida = em.getReference(Permisosalida.class, id);
                permisosalida.getIdSalida();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The permisosalida with id " + id + " no longer exists.", enfe);
            }
            Integrante cedulaIntegrante = permisosalida.getCedulaIntegrante();
            if (cedulaIntegrante != null) {
                cedulaIntegrante.getPermisosalidaList().remove(permisosalida);
                cedulaIntegrante = em.merge(cedulaIntegrante);
            }
            em.remove(permisosalida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Permisosalida> findPermisosalidaEntities() {
        return findPermisosalidaEntities(true, -1, -1);
    }

    public List<Permisosalida> findPermisosalidaEntities(int maxResults, int firstResult) {
        return findPermisosalidaEntities(false, maxResults, firstResult);
    }

    private List<Permisosalida> findPermisosalidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Permisosalida.class));
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

    public Permisosalida findPermisosalida(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Permisosalida.class, id);
        } finally {
            em.close();
        }
    }

    public int getPermisosalidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Permisosalida> rt = cq.from(Permisosalida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
