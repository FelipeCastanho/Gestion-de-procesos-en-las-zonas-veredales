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
import modelo.Integrante;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Autoridadingresazonaveredal;
import modelo.Zonaveredal;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Jennifer
 */
public class ZonaveredalJpaController implements Serializable {

    public ZonaveredalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public ZonaveredalJpaController(){
        this.emf = Persistence.createEntityManagerFactory("ProyectoDSIIPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Zonaveredal zonaveredal) {
        if (zonaveredal.getIntegranteList() == null) {
            zonaveredal.setIntegranteList(new ArrayList<Integrante>());
        }
        if (zonaveredal.getAutoridadingresazonaveredalList() == null) {
            zonaveredal.setAutoridadingresazonaveredalList(new ArrayList<Autoridadingresazonaveredal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Integrante> attachedIntegranteList = new ArrayList<Integrante>();
            for (Integrante integranteListIntegranteToAttach : zonaveredal.getIntegranteList()) {
                integranteListIntegranteToAttach = em.getReference(integranteListIntegranteToAttach.getClass(), integranteListIntegranteToAttach.getCedulaIntegrante());
                attachedIntegranteList.add(integranteListIntegranteToAttach);
            }
            zonaveredal.setIntegranteList(attachedIntegranteList);
            List<Autoridadingresazonaveredal> attachedAutoridadingresazonaveredalList = new ArrayList<Autoridadingresazonaveredal>();
            for (Autoridadingresazonaveredal autoridadingresazonaveredalListAutoridadingresazonaveredalToAttach : zonaveredal.getAutoridadingresazonaveredalList()) {
                autoridadingresazonaveredalListAutoridadingresazonaveredalToAttach = em.getReference(autoridadingresazonaveredalListAutoridadingresazonaveredalToAttach.getClass(), autoridadingresazonaveredalListAutoridadingresazonaveredalToAttach.getIdIngreso());
                attachedAutoridadingresazonaveredalList.add(autoridadingresazonaveredalListAutoridadingresazonaveredalToAttach);
            }
            zonaveredal.setAutoridadingresazonaveredalList(attachedAutoridadingresazonaveredalList);
            em.persist(zonaveredal);
            for (Integrante integranteListIntegrante : zonaveredal.getIntegranteList()) {
                Zonaveredal oldIdZonaVeredalOfIntegranteListIntegrante = integranteListIntegrante.getIdZonaVeredal();
                integranteListIntegrante.setIdZonaVeredal(zonaveredal);
                integranteListIntegrante = em.merge(integranteListIntegrante);
                if (oldIdZonaVeredalOfIntegranteListIntegrante != null) {
                    oldIdZonaVeredalOfIntegranteListIntegrante.getIntegranteList().remove(integranteListIntegrante);
                    oldIdZonaVeredalOfIntegranteListIntegrante = em.merge(oldIdZonaVeredalOfIntegranteListIntegrante);
                }
            }
            for (Autoridadingresazonaveredal autoridadingresazonaveredalListAutoridadingresazonaveredal : zonaveredal.getAutoridadingresazonaveredalList()) {
                Zonaveredal oldIdZonaVeredalOfAutoridadingresazonaveredalListAutoridadingresazonaveredal = autoridadingresazonaveredalListAutoridadingresazonaveredal.getIdZonaVeredal();
                autoridadingresazonaveredalListAutoridadingresazonaveredal.setIdZonaVeredal(zonaveredal);
                autoridadingresazonaveredalListAutoridadingresazonaveredal = em.merge(autoridadingresazonaveredalListAutoridadingresazonaveredal);
                if (oldIdZonaVeredalOfAutoridadingresazonaveredalListAutoridadingresazonaveredal != null) {
                    oldIdZonaVeredalOfAutoridadingresazonaveredalListAutoridadingresazonaveredal.getAutoridadingresazonaveredalList().remove(autoridadingresazonaveredalListAutoridadingresazonaveredal);
                    oldIdZonaVeredalOfAutoridadingresazonaveredalListAutoridadingresazonaveredal = em.merge(oldIdZonaVeredalOfAutoridadingresazonaveredalListAutoridadingresazonaveredal);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Zonaveredal zonaveredal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Zonaveredal persistentZonaveredal = em.find(Zonaveredal.class, zonaveredal.getIdZonaVeredal());
            List<Integrante> integranteListOld = persistentZonaveredal.getIntegranteList();
            List<Integrante> integranteListNew = zonaveredal.getIntegranteList();
            List<Autoridadingresazonaveredal> autoridadingresazonaveredalListOld = persistentZonaveredal.getAutoridadingresazonaveredalList();
            List<Autoridadingresazonaveredal> autoridadingresazonaveredalListNew = zonaveredal.getAutoridadingresazonaveredalList();
            List<Integrante> attachedIntegranteListNew = new ArrayList<Integrante>();
            for (Integrante integranteListNewIntegranteToAttach : integranteListNew) {
                integranteListNewIntegranteToAttach = em.getReference(integranteListNewIntegranteToAttach.getClass(), integranteListNewIntegranteToAttach.getCedulaIntegrante());
                attachedIntegranteListNew.add(integranteListNewIntegranteToAttach);
            }
            integranteListNew = attachedIntegranteListNew;
            zonaveredal.setIntegranteList(integranteListNew);
            List<Autoridadingresazonaveredal> attachedAutoridadingresazonaveredalListNew = new ArrayList<Autoridadingresazonaveredal>();
            for (Autoridadingresazonaveredal autoridadingresazonaveredalListNewAutoridadingresazonaveredalToAttach : autoridadingresazonaveredalListNew) {
                autoridadingresazonaveredalListNewAutoridadingresazonaveredalToAttach = em.getReference(autoridadingresazonaveredalListNewAutoridadingresazonaveredalToAttach.getClass(), autoridadingresazonaveredalListNewAutoridadingresazonaveredalToAttach.getIdIngreso());
                attachedAutoridadingresazonaveredalListNew.add(autoridadingresazonaveredalListNewAutoridadingresazonaveredalToAttach);
            }
            autoridadingresazonaveredalListNew = attachedAutoridadingresazonaveredalListNew;
            zonaveredal.setAutoridadingresazonaveredalList(autoridadingresazonaveredalListNew);
            zonaveredal = em.merge(zonaveredal);
            for (Integrante integranteListOldIntegrante : integranteListOld) {
                if (!integranteListNew.contains(integranteListOldIntegrante)) {
                    integranteListOldIntegrante.setIdZonaVeredal(null);
                    integranteListOldIntegrante = em.merge(integranteListOldIntegrante);
                }
            }
            for (Integrante integranteListNewIntegrante : integranteListNew) {
                if (!integranteListOld.contains(integranteListNewIntegrante)) {
                    Zonaveredal oldIdZonaVeredalOfIntegranteListNewIntegrante = integranteListNewIntegrante.getIdZonaVeredal();
                    integranteListNewIntegrante.setIdZonaVeredal(zonaveredal);
                    integranteListNewIntegrante = em.merge(integranteListNewIntegrante);
                    if (oldIdZonaVeredalOfIntegranteListNewIntegrante != null && !oldIdZonaVeredalOfIntegranteListNewIntegrante.equals(zonaveredal)) {
                        oldIdZonaVeredalOfIntegranteListNewIntegrante.getIntegranteList().remove(integranteListNewIntegrante);
                        oldIdZonaVeredalOfIntegranteListNewIntegrante = em.merge(oldIdZonaVeredalOfIntegranteListNewIntegrante);
                    }
                }
            }
            for (Autoridadingresazonaveredal autoridadingresazonaveredalListOldAutoridadingresazonaveredal : autoridadingresazonaveredalListOld) {
                if (!autoridadingresazonaveredalListNew.contains(autoridadingresazonaveredalListOldAutoridadingresazonaveredal)) {
                    autoridadingresazonaveredalListOldAutoridadingresazonaveredal.setIdZonaVeredal(null);
                    autoridadingresazonaveredalListOldAutoridadingresazonaveredal = em.merge(autoridadingresazonaveredalListOldAutoridadingresazonaveredal);
                }
            }
            for (Autoridadingresazonaveredal autoridadingresazonaveredalListNewAutoridadingresazonaveredal : autoridadingresazonaveredalListNew) {
                if (!autoridadingresazonaveredalListOld.contains(autoridadingresazonaveredalListNewAutoridadingresazonaveredal)) {
                    Zonaveredal oldIdZonaVeredalOfAutoridadingresazonaveredalListNewAutoridadingresazonaveredal = autoridadingresazonaveredalListNewAutoridadingresazonaveredal.getIdZonaVeredal();
                    autoridadingresazonaveredalListNewAutoridadingresazonaveredal.setIdZonaVeredal(zonaveredal);
                    autoridadingresazonaveredalListNewAutoridadingresazonaveredal = em.merge(autoridadingresazonaveredalListNewAutoridadingresazonaveredal);
                    if (oldIdZonaVeredalOfAutoridadingresazonaveredalListNewAutoridadingresazonaveredal != null && !oldIdZonaVeredalOfAutoridadingresazonaveredalListNewAutoridadingresazonaveredal.equals(zonaveredal)) {
                        oldIdZonaVeredalOfAutoridadingresazonaveredalListNewAutoridadingresazonaveredal.getAutoridadingresazonaveredalList().remove(autoridadingresazonaveredalListNewAutoridadingresazonaveredal);
                        oldIdZonaVeredalOfAutoridadingresazonaveredalListNewAutoridadingresazonaveredal = em.merge(oldIdZonaVeredalOfAutoridadingresazonaveredalListNewAutoridadingresazonaveredal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = zonaveredal.getIdZonaVeredal();
                if (findZonaveredal(id) == null) {
                    throw new NonexistentEntityException("The zonaveredal with id " + id + " no longer exists.");
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
            Zonaveredal zonaveredal;
            try {
                zonaveredal = em.getReference(Zonaveredal.class, id);
                zonaveredal.getIdZonaVeredal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The zonaveredal with id " + id + " no longer exists.", enfe);
            }
            List<Integrante> integranteList = zonaveredal.getIntegranteList();
            for (Integrante integranteListIntegrante : integranteList) {
                integranteListIntegrante.setIdZonaVeredal(null);
                integranteListIntegrante = em.merge(integranteListIntegrante);
            }
            List<Autoridadingresazonaveredal> autoridadingresazonaveredalList = zonaveredal.getAutoridadingresazonaveredalList();
            for (Autoridadingresazonaveredal autoridadingresazonaveredalListAutoridadingresazonaveredal : autoridadingresazonaveredalList) {
                autoridadingresazonaveredalListAutoridadingresazonaveredal.setIdZonaVeredal(null);
                autoridadingresazonaveredalListAutoridadingresazonaveredal = em.merge(autoridadingresazonaveredalListAutoridadingresazonaveredal);
            }
            em.remove(zonaveredal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Zonaveredal> findZonaveredalEntities() {
        return findZonaveredalEntities(true, -1, -1);
    }

    public List<Zonaveredal> findZonaveredalEntities(int maxResults, int firstResult) {
        return findZonaveredalEntities(false, maxResults, firstResult);
    }

    private List<Zonaveredal> findZonaveredalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Zonaveredal.class));
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

    public Zonaveredal findZonaveredal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Zonaveredal.class, id);
        } finally {
            em.close();
        }
    }

    public int getZonaveredalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Zonaveredal> rt = cq.from(Zonaveredal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
