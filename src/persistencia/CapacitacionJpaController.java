/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Integranteasistecapacitacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Capacitacion;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Jennifer
 */
public class CapacitacionJpaController implements Serializable {

    public CapacitacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
     public CapacitacionJpaController(){
        this.emf = Persistence.createEntityManagerFactory("ProyectoDSIIPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Capacitacion capacitacion) {
        if (capacitacion.getIntegranteasistecapacitacionList() == null) {
            capacitacion.setIntegranteasistecapacitacionList(new ArrayList<Integranteasistecapacitacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Integranteasistecapacitacion> attachedIntegranteasistecapacitacionList = new ArrayList<Integranteasistecapacitacion>();
            for (Integranteasistecapacitacion integranteasistecapacitacionListIntegranteasistecapacitacionToAttach : capacitacion.getIntegranteasistecapacitacionList()) {
                integranteasistecapacitacionListIntegranteasistecapacitacionToAttach = em.getReference(integranteasistecapacitacionListIntegranteasistecapacitacionToAttach.getClass(), integranteasistecapacitacionListIntegranteasistecapacitacionToAttach.getIdAsiste());
                attachedIntegranteasistecapacitacionList.add(integranteasistecapacitacionListIntegranteasistecapacitacionToAttach);
            }
            capacitacion.setIntegranteasistecapacitacionList(attachedIntegranteasistecapacitacionList);
            em.persist(capacitacion);
            for (Integranteasistecapacitacion integranteasistecapacitacionListIntegranteasistecapacitacion : capacitacion.getIntegranteasistecapacitacionList()) {
                Capacitacion oldIdCapacitacionOfIntegranteasistecapacitacionListIntegranteasistecapacitacion = integranteasistecapacitacionListIntegranteasistecapacitacion.getIdCapacitacion();
                integranteasistecapacitacionListIntegranteasistecapacitacion.setIdCapacitacion(capacitacion);
                integranteasistecapacitacionListIntegranteasistecapacitacion = em.merge(integranteasistecapacitacionListIntegranteasistecapacitacion);
                if (oldIdCapacitacionOfIntegranteasistecapacitacionListIntegranteasistecapacitacion != null) {
                    oldIdCapacitacionOfIntegranteasistecapacitacionListIntegranteasistecapacitacion.getIntegranteasistecapacitacionList().remove(integranteasistecapacitacionListIntegranteasistecapacitacion);
                    oldIdCapacitacionOfIntegranteasistecapacitacionListIntegranteasistecapacitacion = em.merge(oldIdCapacitacionOfIntegranteasistecapacitacionListIntegranteasistecapacitacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Capacitacion capacitacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Capacitacion persistentCapacitacion = em.find(Capacitacion.class, capacitacion.getIdCapacitacion());
            List<Integranteasistecapacitacion> integranteasistecapacitacionListOld = persistentCapacitacion.getIntegranteasistecapacitacionList();
            List<Integranteasistecapacitacion> integranteasistecapacitacionListNew = capacitacion.getIntegranteasistecapacitacionList();
            List<Integranteasistecapacitacion> attachedIntegranteasistecapacitacionListNew = new ArrayList<Integranteasistecapacitacion>();
            for (Integranteasistecapacitacion integranteasistecapacitacionListNewIntegranteasistecapacitacionToAttach : integranteasistecapacitacionListNew) {
                integranteasistecapacitacionListNewIntegranteasistecapacitacionToAttach = em.getReference(integranteasistecapacitacionListNewIntegranteasistecapacitacionToAttach.getClass(), integranteasistecapacitacionListNewIntegranteasistecapacitacionToAttach.getIdAsiste());
                attachedIntegranteasistecapacitacionListNew.add(integranteasistecapacitacionListNewIntegranteasistecapacitacionToAttach);
            }
            integranteasistecapacitacionListNew = attachedIntegranteasistecapacitacionListNew;
            capacitacion.setIntegranteasistecapacitacionList(integranteasistecapacitacionListNew);
            capacitacion = em.merge(capacitacion);
            for (Integranteasistecapacitacion integranteasistecapacitacionListOldIntegranteasistecapacitacion : integranteasistecapacitacionListOld) {
                if (!integranteasistecapacitacionListNew.contains(integranteasistecapacitacionListOldIntegranteasistecapacitacion)) {
                    integranteasistecapacitacionListOldIntegranteasistecapacitacion.setIdCapacitacion(null);
                    integranteasistecapacitacionListOldIntegranteasistecapacitacion = em.merge(integranteasistecapacitacionListOldIntegranteasistecapacitacion);
                }
            }
            for (Integranteasistecapacitacion integranteasistecapacitacionListNewIntegranteasistecapacitacion : integranteasistecapacitacionListNew) {
                if (!integranteasistecapacitacionListOld.contains(integranteasistecapacitacionListNewIntegranteasistecapacitacion)) {
                    Capacitacion oldIdCapacitacionOfIntegranteasistecapacitacionListNewIntegranteasistecapacitacion = integranteasistecapacitacionListNewIntegranteasistecapacitacion.getIdCapacitacion();
                    integranteasistecapacitacionListNewIntegranteasistecapacitacion.setIdCapacitacion(capacitacion);
                    integranteasistecapacitacionListNewIntegranteasistecapacitacion = em.merge(integranteasistecapacitacionListNewIntegranteasistecapacitacion);
                    if (oldIdCapacitacionOfIntegranteasistecapacitacionListNewIntegranteasistecapacitacion != null && !oldIdCapacitacionOfIntegranteasistecapacitacionListNewIntegranteasistecapacitacion.equals(capacitacion)) {
                        oldIdCapacitacionOfIntegranteasistecapacitacionListNewIntegranteasistecapacitacion.getIntegranteasistecapacitacionList().remove(integranteasistecapacitacionListNewIntegranteasistecapacitacion);
                        oldIdCapacitacionOfIntegranteasistecapacitacionListNewIntegranteasistecapacitacion = em.merge(oldIdCapacitacionOfIntegranteasistecapacitacionListNewIntegranteasistecapacitacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = capacitacion.getIdCapacitacion();
                if (findCapacitacion(id) == null) {
                    throw new NonexistentEntityException("The capacitacion with id " + id + " no longer exists.");
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
            Capacitacion capacitacion;
            try {
                capacitacion = em.getReference(Capacitacion.class, id);
                capacitacion.getIdCapacitacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The capacitacion with id " + id + " no longer exists.", enfe);
            }
            List<Integranteasistecapacitacion> integranteasistecapacitacionList = capacitacion.getIntegranteasistecapacitacionList();
            for (Integranteasistecapacitacion integranteasistecapacitacionListIntegranteasistecapacitacion : integranteasistecapacitacionList) {
                integranteasistecapacitacionListIntegranteasistecapacitacion.setIdCapacitacion(null);
                integranteasistecapacitacionListIntegranteasistecapacitacion = em.merge(integranteasistecapacitacionListIntegranteasistecapacitacion);
            }
            em.remove(capacitacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Capacitacion> findCapacitacionEntities() {
        return findCapacitacionEntities(true, -1, -1);
    }

    public List<Capacitacion> findCapacitacionEntities(int maxResults, int firstResult) {
        return findCapacitacionEntities(false, maxResults, firstResult);
    }

    private List<Capacitacion> findCapacitacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Capacitacion.class));
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

    public Capacitacion findCapacitacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Capacitacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCapacitacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Capacitacion> rt = cq.from(Capacitacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
