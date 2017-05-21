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
import modelo.Autoridadingresazonaveredal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Autoridad;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Jennifer
 */
public class AutoridadJpaController implements Serializable {

    public AutoridadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
     public AutoridadJpaController(){
        this.emf = Persistence.createEntityManagerFactory("ProyectoDSIIPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Autoridad autoridad) throws PreexistingEntityException, Exception {
        if (autoridad.getAutoridadingresazonaveredalList() == null) {
            autoridad.setAutoridadingresazonaveredalList(new ArrayList<Autoridadingresazonaveredal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Autoridadingresazonaveredal> attachedAutoridadingresazonaveredalList = new ArrayList<Autoridadingresazonaveredal>();
            for (Autoridadingresazonaveredal autoridadingresazonaveredalListAutoridadingresazonaveredalToAttach : autoridad.getAutoridadingresazonaveredalList()) {
                autoridadingresazonaveredalListAutoridadingresazonaveredalToAttach = em.getReference(autoridadingresazonaveredalListAutoridadingresazonaveredalToAttach.getClass(), autoridadingresazonaveredalListAutoridadingresazonaveredalToAttach.getIdIngreso());
                attachedAutoridadingresazonaveredalList.add(autoridadingresazonaveredalListAutoridadingresazonaveredalToAttach);
            }
            autoridad.setAutoridadingresazonaveredalList(attachedAutoridadingresazonaveredalList);
            em.persist(autoridad);
            for (Autoridadingresazonaveredal autoridadingresazonaveredalListAutoridadingresazonaveredal : autoridad.getAutoridadingresazonaveredalList()) {
                Autoridad oldIdAutoridadOfAutoridadingresazonaveredalListAutoridadingresazonaveredal = autoridadingresazonaveredalListAutoridadingresazonaveredal.getIdAutoridad();
                autoridadingresazonaveredalListAutoridadingresazonaveredal.setIdAutoridad(autoridad);
                autoridadingresazonaveredalListAutoridadingresazonaveredal = em.merge(autoridadingresazonaveredalListAutoridadingresazonaveredal);
                if (oldIdAutoridadOfAutoridadingresazonaveredalListAutoridadingresazonaveredal != null) {
                    oldIdAutoridadOfAutoridadingresazonaveredalListAutoridadingresazonaveredal.getAutoridadingresazonaveredalList().remove(autoridadingresazonaveredalListAutoridadingresazonaveredal);
                    oldIdAutoridadOfAutoridadingresazonaveredalListAutoridadingresazonaveredal = em.merge(oldIdAutoridadOfAutoridadingresazonaveredalListAutoridadingresazonaveredal);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAutoridad(autoridad.getIdAutoridad()) != null) {
                throw new PreexistingEntityException("Autoridad " + autoridad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Autoridad autoridad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autoridad persistentAutoridad = em.find(Autoridad.class, autoridad.getIdAutoridad());
            List<Autoridadingresazonaveredal> autoridadingresazonaveredalListOld = persistentAutoridad.getAutoridadingresazonaveredalList();
            List<Autoridadingresazonaveredal> autoridadingresazonaveredalListNew = autoridad.getAutoridadingresazonaveredalList();
            List<Autoridadingresazonaveredal> attachedAutoridadingresazonaveredalListNew = new ArrayList<Autoridadingresazonaveredal>();
            for (Autoridadingresazonaveredal autoridadingresazonaveredalListNewAutoridadingresazonaveredalToAttach : autoridadingresazonaveredalListNew) {
                autoridadingresazonaveredalListNewAutoridadingresazonaveredalToAttach = em.getReference(autoridadingresazonaveredalListNewAutoridadingresazonaveredalToAttach.getClass(), autoridadingresazonaveredalListNewAutoridadingresazonaveredalToAttach.getIdIngreso());
                attachedAutoridadingresazonaveredalListNew.add(autoridadingresazonaveredalListNewAutoridadingresazonaveredalToAttach);
            }
            autoridadingresazonaveredalListNew = attachedAutoridadingresazonaveredalListNew;
            autoridad.setAutoridadingresazonaveredalList(autoridadingresazonaveredalListNew);
            autoridad = em.merge(autoridad);
            for (Autoridadingresazonaveredal autoridadingresazonaveredalListOldAutoridadingresazonaveredal : autoridadingresazonaveredalListOld) {
                if (!autoridadingresazonaveredalListNew.contains(autoridadingresazonaveredalListOldAutoridadingresazonaveredal)) {
                    autoridadingresazonaveredalListOldAutoridadingresazonaveredal.setIdAutoridad(null);
                    autoridadingresazonaveredalListOldAutoridadingresazonaveredal = em.merge(autoridadingresazonaveredalListOldAutoridadingresazonaveredal);
                }
            }
            for (Autoridadingresazonaveredal autoridadingresazonaveredalListNewAutoridadingresazonaveredal : autoridadingresazonaveredalListNew) {
                if (!autoridadingresazonaveredalListOld.contains(autoridadingresazonaveredalListNewAutoridadingresazonaveredal)) {
                    Autoridad oldIdAutoridadOfAutoridadingresazonaveredalListNewAutoridadingresazonaveredal = autoridadingresazonaveredalListNewAutoridadingresazonaveredal.getIdAutoridad();
                    autoridadingresazonaveredalListNewAutoridadingresazonaveredal.setIdAutoridad(autoridad);
                    autoridadingresazonaveredalListNewAutoridadingresazonaveredal = em.merge(autoridadingresazonaveredalListNewAutoridadingresazonaveredal);
                    if (oldIdAutoridadOfAutoridadingresazonaveredalListNewAutoridadingresazonaveredal != null && !oldIdAutoridadOfAutoridadingresazonaveredalListNewAutoridadingresazonaveredal.equals(autoridad)) {
                        oldIdAutoridadOfAutoridadingresazonaveredalListNewAutoridadingresazonaveredal.getAutoridadingresazonaveredalList().remove(autoridadingresazonaveredalListNewAutoridadingresazonaveredal);
                        oldIdAutoridadOfAutoridadingresazonaveredalListNewAutoridadingresazonaveredal = em.merge(oldIdAutoridadOfAutoridadingresazonaveredalListNewAutoridadingresazonaveredal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = autoridad.getIdAutoridad();
                if (findAutoridad(id) == null) {
                    throw new NonexistentEntityException("The autoridad with id " + id + " no longer exists.");
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
            Autoridad autoridad;
            try {
                autoridad = em.getReference(Autoridad.class, id);
                autoridad.getIdAutoridad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autoridad with id " + id + " no longer exists.", enfe);
            }
            List<Autoridadingresazonaveredal> autoridadingresazonaveredalList = autoridad.getAutoridadingresazonaveredalList();
            for (Autoridadingresazonaveredal autoridadingresazonaveredalListAutoridadingresazonaveredal : autoridadingresazonaveredalList) {
                autoridadingresazonaveredalListAutoridadingresazonaveredal.setIdAutoridad(null);
                autoridadingresazonaveredalListAutoridadingresazonaveredal = em.merge(autoridadingresazonaveredalListAutoridadingresazonaveredal);
            }
            em.remove(autoridad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Autoridad> findAutoridadEntities() {
        return findAutoridadEntities(true, -1, -1);
    }

    public List<Autoridad> findAutoridadEntities(int maxResults, int firstResult) {
        return findAutoridadEntities(false, maxResults, firstResult);
    }

    private List<Autoridad> findAutoridadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Autoridad.class));
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

    public Autoridad findAutoridad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Autoridad.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutoridadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Autoridad> rt = cq.from(Autoridad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
