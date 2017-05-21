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
import modelo.Zonaveredal;
import modelo.Integranteasistecapacitacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Informacion;
import modelo.Permisosalida;
import modelo.Arma;
import modelo.Integrante;
import modelo.Pena;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Jennifer
 */
public class IntegranteJpaController implements Serializable {

    public IntegranteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public IntegranteJpaController(){
        this.emf = Persistence.createEntityManagerFactory("ProyectoDSIIPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Integrante integrante) throws PreexistingEntityException, Exception {
        if (integrante.getIntegranteasistecapacitacionList() == null) {
            integrante.setIntegranteasistecapacitacionList(new ArrayList<Integranteasistecapacitacion>());
        }
        if (integrante.getInformacionList() == null) {
            integrante.setInformacionList(new ArrayList<Informacion>());
        }
        if (integrante.getPermisosalidaList() == null) {
            integrante.setPermisosalidaList(new ArrayList<Permisosalida>());
        }
        if (integrante.getArmaList() == null) {
            integrante.setArmaList(new ArrayList<Arma>());
        }
        if (integrante.getPenaList() == null) {
            integrante.setPenaList(new ArrayList<Pena>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Zonaveredal idZonaVeredal = integrante.getIdZonaVeredal();
            if (idZonaVeredal != null) {
                idZonaVeredal = em.getReference(idZonaVeredal.getClass(), idZonaVeredal.getIdZonaVeredal());
                integrante.setIdZonaVeredal(idZonaVeredal);
            }
            List<Integranteasistecapacitacion> attachedIntegranteasistecapacitacionList = new ArrayList<Integranteasistecapacitacion>();
            for (Integranteasistecapacitacion integranteasistecapacitacionListIntegranteasistecapacitacionToAttach : integrante.getIntegranteasistecapacitacionList()) {
                integranteasistecapacitacionListIntegranteasistecapacitacionToAttach = em.getReference(integranteasistecapacitacionListIntegranteasistecapacitacionToAttach.getClass(), integranteasistecapacitacionListIntegranteasistecapacitacionToAttach.getIdAsiste());
                attachedIntegranteasistecapacitacionList.add(integranteasistecapacitacionListIntegranteasistecapacitacionToAttach);
            }
            integrante.setIntegranteasistecapacitacionList(attachedIntegranteasistecapacitacionList);
            List<Informacion> attachedInformacionList = new ArrayList<Informacion>();
            for (Informacion informacionListInformacionToAttach : integrante.getInformacionList()) {
                informacionListInformacionToAttach = em.getReference(informacionListInformacionToAttach.getClass(), informacionListInformacionToAttach.getIdInformacion());
                attachedInformacionList.add(informacionListInformacionToAttach);
            }
            integrante.setInformacionList(attachedInformacionList);
            List<Permisosalida> attachedPermisosalidaList = new ArrayList<Permisosalida>();
            for (Permisosalida permisosalidaListPermisosalidaToAttach : integrante.getPermisosalidaList()) {
                permisosalidaListPermisosalidaToAttach = em.getReference(permisosalidaListPermisosalidaToAttach.getClass(), permisosalidaListPermisosalidaToAttach.getIdSalida());
                attachedPermisosalidaList.add(permisosalidaListPermisosalidaToAttach);
            }
            integrante.setPermisosalidaList(attachedPermisosalidaList);
            List<Arma> attachedArmaList = new ArrayList<Arma>();
            for (Arma armaListArmaToAttach : integrante.getArmaList()) {
                armaListArmaToAttach = em.getReference(armaListArmaToAttach.getClass(), armaListArmaToAttach.getIdArma());
                attachedArmaList.add(armaListArmaToAttach);
            }
            integrante.setArmaList(attachedArmaList);
            List<Pena> attachedPenaList = new ArrayList<Pena>();
            for (Pena penaListPenaToAttach : integrante.getPenaList()) {
                penaListPenaToAttach = em.getReference(penaListPenaToAttach.getClass(), penaListPenaToAttach.getIdPena());
                attachedPenaList.add(penaListPenaToAttach);
            }
            integrante.setPenaList(attachedPenaList);
            em.persist(integrante);
            if (idZonaVeredal != null) {
                idZonaVeredal.getIntegranteList().add(integrante);
                idZonaVeredal = em.merge(idZonaVeredal);
            }
            for (Integranteasistecapacitacion integranteasistecapacitacionListIntegranteasistecapacitacion : integrante.getIntegranteasistecapacitacionList()) {
                Integrante oldCedulaIntegranteOfIntegranteasistecapacitacionListIntegranteasistecapacitacion = integranteasistecapacitacionListIntegranteasistecapacitacion.getCedulaIntegrante();
                integranteasistecapacitacionListIntegranteasistecapacitacion.setCedulaIntegrante(integrante);
                integranteasistecapacitacionListIntegranteasistecapacitacion = em.merge(integranteasistecapacitacionListIntegranteasistecapacitacion);
                if (oldCedulaIntegranteOfIntegranteasistecapacitacionListIntegranteasistecapacitacion != null) {
                    oldCedulaIntegranteOfIntegranteasistecapacitacionListIntegranteasistecapacitacion.getIntegranteasistecapacitacionList().remove(integranteasistecapacitacionListIntegranteasistecapacitacion);
                    oldCedulaIntegranteOfIntegranteasistecapacitacionListIntegranteasistecapacitacion = em.merge(oldCedulaIntegranteOfIntegranteasistecapacitacionListIntegranteasistecapacitacion);
                }
            }
            for (Informacion informacionListInformacion : integrante.getInformacionList()) {
                Integrante oldCedulaIntegranteOfInformacionListInformacion = informacionListInformacion.getCedulaIntegrante();
                informacionListInformacion.setCedulaIntegrante(integrante);
                informacionListInformacion = em.merge(informacionListInformacion);
                if (oldCedulaIntegranteOfInformacionListInformacion != null) {
                    oldCedulaIntegranteOfInformacionListInformacion.getInformacionList().remove(informacionListInformacion);
                    oldCedulaIntegranteOfInformacionListInformacion = em.merge(oldCedulaIntegranteOfInformacionListInformacion);
                }
            }
            for (Permisosalida permisosalidaListPermisosalida : integrante.getPermisosalidaList()) {
                Integrante oldCedulaIntegranteOfPermisosalidaListPermisosalida = permisosalidaListPermisosalida.getCedulaIntegrante();
                permisosalidaListPermisosalida.setCedulaIntegrante(integrante);
                permisosalidaListPermisosalida = em.merge(permisosalidaListPermisosalida);
                if (oldCedulaIntegranteOfPermisosalidaListPermisosalida != null) {
                    oldCedulaIntegranteOfPermisosalidaListPermisosalida.getPermisosalidaList().remove(permisosalidaListPermisosalida);
                    oldCedulaIntegranteOfPermisosalidaListPermisosalida = em.merge(oldCedulaIntegranteOfPermisosalidaListPermisosalida);
                }
            }
            for (Arma armaListArma : integrante.getArmaList()) {
                Integrante oldCedulaIntegranteOfArmaListArma = armaListArma.getCedulaIntegrante();
                armaListArma.setCedulaIntegrante(integrante);
                armaListArma = em.merge(armaListArma);
                if (oldCedulaIntegranteOfArmaListArma != null) {
                    oldCedulaIntegranteOfArmaListArma.getArmaList().remove(armaListArma);
                    oldCedulaIntegranteOfArmaListArma = em.merge(oldCedulaIntegranteOfArmaListArma);
                }
            }
            for (Pena penaListPena : integrante.getPenaList()) {
                Integrante oldCedulaIntegranteOfPenaListPena = penaListPena.getCedulaIntegrante();
                penaListPena.setCedulaIntegrante(integrante);
                penaListPena = em.merge(penaListPena);
                if (oldCedulaIntegranteOfPenaListPena != null) {
                    oldCedulaIntegranteOfPenaListPena.getPenaList().remove(penaListPena);
                    oldCedulaIntegranteOfPenaListPena = em.merge(oldCedulaIntegranteOfPenaListPena);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIntegrante(integrante.getCedulaIntegrante()) != null) {
                throw new PreexistingEntityException("Integrante " + integrante + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Integrante integrante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Integrante persistentIntegrante = em.find(Integrante.class, integrante.getCedulaIntegrante());
            Zonaveredal idZonaVeredalOld = persistentIntegrante.getIdZonaVeredal();
            Zonaveredal idZonaVeredalNew = integrante.getIdZonaVeredal();
            List<Integranteasistecapacitacion> integranteasistecapacitacionListOld = persistentIntegrante.getIntegranteasistecapacitacionList();
            List<Integranteasistecapacitacion> integranteasistecapacitacionListNew = integrante.getIntegranteasistecapacitacionList();
            List<Informacion> informacionListOld = persistentIntegrante.getInformacionList();
            List<Informacion> informacionListNew = integrante.getInformacionList();
            List<Permisosalida> permisosalidaListOld = persistentIntegrante.getPermisosalidaList();
            List<Permisosalida> permisosalidaListNew = integrante.getPermisosalidaList();
            List<Arma> armaListOld = persistentIntegrante.getArmaList();
            List<Arma> armaListNew = integrante.getArmaList();
            List<Pena> penaListOld = persistentIntegrante.getPenaList();
            List<Pena> penaListNew = integrante.getPenaList();
            if (idZonaVeredalNew != null) {
                idZonaVeredalNew = em.getReference(idZonaVeredalNew.getClass(), idZonaVeredalNew.getIdZonaVeredal());
                integrante.setIdZonaVeredal(idZonaVeredalNew);
            }
            List<Integranteasistecapacitacion> attachedIntegranteasistecapacitacionListNew = new ArrayList<Integranteasistecapacitacion>();
            for (Integranteasistecapacitacion integranteasistecapacitacionListNewIntegranteasistecapacitacionToAttach : integranteasistecapacitacionListNew) {
                integranteasistecapacitacionListNewIntegranteasistecapacitacionToAttach = em.getReference(integranteasistecapacitacionListNewIntegranteasistecapacitacionToAttach.getClass(), integranteasistecapacitacionListNewIntegranteasistecapacitacionToAttach.getIdAsiste());
                attachedIntegranteasistecapacitacionListNew.add(integranteasistecapacitacionListNewIntegranteasistecapacitacionToAttach);
            }
            integranteasistecapacitacionListNew = attachedIntegranteasistecapacitacionListNew;
            integrante.setIntegranteasistecapacitacionList(integranteasistecapacitacionListNew);
            List<Informacion> attachedInformacionListNew = new ArrayList<Informacion>();
            for (Informacion informacionListNewInformacionToAttach : informacionListNew) {
                informacionListNewInformacionToAttach = em.getReference(informacionListNewInformacionToAttach.getClass(), informacionListNewInformacionToAttach.getIdInformacion());
                attachedInformacionListNew.add(informacionListNewInformacionToAttach);
            }
            informacionListNew = attachedInformacionListNew;
            integrante.setInformacionList(informacionListNew);
            List<Permisosalida> attachedPermisosalidaListNew = new ArrayList<Permisosalida>();
            for (Permisosalida permisosalidaListNewPermisosalidaToAttach : permisosalidaListNew) {
                permisosalidaListNewPermisosalidaToAttach = em.getReference(permisosalidaListNewPermisosalidaToAttach.getClass(), permisosalidaListNewPermisosalidaToAttach.getIdSalida());
                attachedPermisosalidaListNew.add(permisosalidaListNewPermisosalidaToAttach);
            }
            permisosalidaListNew = attachedPermisosalidaListNew;
            integrante.setPermisosalidaList(permisosalidaListNew);
            List<Arma> attachedArmaListNew = new ArrayList<Arma>();
            for (Arma armaListNewArmaToAttach : armaListNew) {
                armaListNewArmaToAttach = em.getReference(armaListNewArmaToAttach.getClass(), armaListNewArmaToAttach.getIdArma());
                attachedArmaListNew.add(armaListNewArmaToAttach);
            }
            armaListNew = attachedArmaListNew;
            integrante.setArmaList(armaListNew);
            List<Pena> attachedPenaListNew = new ArrayList<Pena>();
            for (Pena penaListNewPenaToAttach : penaListNew) {
                penaListNewPenaToAttach = em.getReference(penaListNewPenaToAttach.getClass(), penaListNewPenaToAttach.getIdPena());
                attachedPenaListNew.add(penaListNewPenaToAttach);
            }
            penaListNew = attachedPenaListNew;
            integrante.setPenaList(penaListNew);
            integrante = em.merge(integrante);
            if (idZonaVeredalOld != null && !idZonaVeredalOld.equals(idZonaVeredalNew)) {
                idZonaVeredalOld.getIntegranteList().remove(integrante);
                idZonaVeredalOld = em.merge(idZonaVeredalOld);
            }
            if (idZonaVeredalNew != null && !idZonaVeredalNew.equals(idZonaVeredalOld)) {
                idZonaVeredalNew.getIntegranteList().add(integrante);
                idZonaVeredalNew = em.merge(idZonaVeredalNew);
            }
            for (Integranteasistecapacitacion integranteasistecapacitacionListOldIntegranteasistecapacitacion : integranteasistecapacitacionListOld) {
                if (!integranteasistecapacitacionListNew.contains(integranteasistecapacitacionListOldIntegranteasistecapacitacion)) {
                    integranteasistecapacitacionListOldIntegranteasistecapacitacion.setCedulaIntegrante(null);
                    integranteasistecapacitacionListOldIntegranteasistecapacitacion = em.merge(integranteasistecapacitacionListOldIntegranteasistecapacitacion);
                }
            }
            for (Integranteasistecapacitacion integranteasistecapacitacionListNewIntegranteasistecapacitacion : integranteasistecapacitacionListNew) {
                if (!integranteasistecapacitacionListOld.contains(integranteasistecapacitacionListNewIntegranteasistecapacitacion)) {
                    Integrante oldCedulaIntegranteOfIntegranteasistecapacitacionListNewIntegranteasistecapacitacion = integranteasistecapacitacionListNewIntegranteasistecapacitacion.getCedulaIntegrante();
                    integranteasistecapacitacionListNewIntegranteasistecapacitacion.setCedulaIntegrante(integrante);
                    integranteasistecapacitacionListNewIntegranteasistecapacitacion = em.merge(integranteasistecapacitacionListNewIntegranteasistecapacitacion);
                    if (oldCedulaIntegranteOfIntegranteasistecapacitacionListNewIntegranteasistecapacitacion != null && !oldCedulaIntegranteOfIntegranteasistecapacitacionListNewIntegranteasistecapacitacion.equals(integrante)) {
                        oldCedulaIntegranteOfIntegranteasistecapacitacionListNewIntegranteasistecapacitacion.getIntegranteasistecapacitacionList().remove(integranteasistecapacitacionListNewIntegranteasistecapacitacion);
                        oldCedulaIntegranteOfIntegranteasistecapacitacionListNewIntegranteasistecapacitacion = em.merge(oldCedulaIntegranteOfIntegranteasistecapacitacionListNewIntegranteasistecapacitacion);
                    }
                }
            }
            for (Informacion informacionListOldInformacion : informacionListOld) {
                if (!informacionListNew.contains(informacionListOldInformacion)) {
                    informacionListOldInformacion.setCedulaIntegrante(null);
                    informacionListOldInformacion = em.merge(informacionListOldInformacion);
                }
            }
            for (Informacion informacionListNewInformacion : informacionListNew) {
                if (!informacionListOld.contains(informacionListNewInformacion)) {
                    Integrante oldCedulaIntegranteOfInformacionListNewInformacion = informacionListNewInformacion.getCedulaIntegrante();
                    informacionListNewInformacion.setCedulaIntegrante(integrante);
                    informacionListNewInformacion = em.merge(informacionListNewInformacion);
                    if (oldCedulaIntegranteOfInformacionListNewInformacion != null && !oldCedulaIntegranteOfInformacionListNewInformacion.equals(integrante)) {
                        oldCedulaIntegranteOfInformacionListNewInformacion.getInformacionList().remove(informacionListNewInformacion);
                        oldCedulaIntegranteOfInformacionListNewInformacion = em.merge(oldCedulaIntegranteOfInformacionListNewInformacion);
                    }
                }
            }
            for (Permisosalida permisosalidaListOldPermisosalida : permisosalidaListOld) {
                if (!permisosalidaListNew.contains(permisosalidaListOldPermisosalida)) {
                    permisosalidaListOldPermisosalida.setCedulaIntegrante(null);
                    permisosalidaListOldPermisosalida = em.merge(permisosalidaListOldPermisosalida);
                }
            }
            for (Permisosalida permisosalidaListNewPermisosalida : permisosalidaListNew) {
                if (!permisosalidaListOld.contains(permisosalidaListNewPermisosalida)) {
                    Integrante oldCedulaIntegranteOfPermisosalidaListNewPermisosalida = permisosalidaListNewPermisosalida.getCedulaIntegrante();
                    permisosalidaListNewPermisosalida.setCedulaIntegrante(integrante);
                    permisosalidaListNewPermisosalida = em.merge(permisosalidaListNewPermisosalida);
                    if (oldCedulaIntegranteOfPermisosalidaListNewPermisosalida != null && !oldCedulaIntegranteOfPermisosalidaListNewPermisosalida.equals(integrante)) {
                        oldCedulaIntegranteOfPermisosalidaListNewPermisosalida.getPermisosalidaList().remove(permisosalidaListNewPermisosalida);
                        oldCedulaIntegranteOfPermisosalidaListNewPermisosalida = em.merge(oldCedulaIntegranteOfPermisosalidaListNewPermisosalida);
                    }
                }
            }
            for (Arma armaListOldArma : armaListOld) {
                if (!armaListNew.contains(armaListOldArma)) {
                    armaListOldArma.setCedulaIntegrante(null);
                    armaListOldArma = em.merge(armaListOldArma);
                }
            }
            for (Arma armaListNewArma : armaListNew) {
                if (!armaListOld.contains(armaListNewArma)) {
                    Integrante oldCedulaIntegranteOfArmaListNewArma = armaListNewArma.getCedulaIntegrante();
                    armaListNewArma.setCedulaIntegrante(integrante);
                    armaListNewArma = em.merge(armaListNewArma);
                    if (oldCedulaIntegranteOfArmaListNewArma != null && !oldCedulaIntegranteOfArmaListNewArma.equals(integrante)) {
                        oldCedulaIntegranteOfArmaListNewArma.getArmaList().remove(armaListNewArma);
                        oldCedulaIntegranteOfArmaListNewArma = em.merge(oldCedulaIntegranteOfArmaListNewArma);
                    }
                }
            }
            for (Pena penaListOldPena : penaListOld) {
                if (!penaListNew.contains(penaListOldPena)) {
                    penaListOldPena.setCedulaIntegrante(null);
                    penaListOldPena = em.merge(penaListOldPena);
                }
            }
            for (Pena penaListNewPena : penaListNew) {
                if (!penaListOld.contains(penaListNewPena)) {
                    Integrante oldCedulaIntegranteOfPenaListNewPena = penaListNewPena.getCedulaIntegrante();
                    penaListNewPena.setCedulaIntegrante(integrante);
                    penaListNewPena = em.merge(penaListNewPena);
                    if (oldCedulaIntegranteOfPenaListNewPena != null && !oldCedulaIntegranteOfPenaListNewPena.equals(integrante)) {
                        oldCedulaIntegranteOfPenaListNewPena.getPenaList().remove(penaListNewPena);
                        oldCedulaIntegranteOfPenaListNewPena = em.merge(oldCedulaIntegranteOfPenaListNewPena);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = integrante.getCedulaIntegrante();
                if (findIntegrante(id) == null) {
                    throw new NonexistentEntityException("The integrante with id " + id + " no longer exists.");
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
            Integrante integrante;
            try {
                integrante = em.getReference(Integrante.class, id);
                integrante.getCedulaIntegrante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The integrante with id " + id + " no longer exists.", enfe);
            }
            Zonaveredal idZonaVeredal = integrante.getIdZonaVeredal();
            if (idZonaVeredal != null) {
                idZonaVeredal.getIntegranteList().remove(integrante);
                idZonaVeredal = em.merge(idZonaVeredal);
            }
            List<Integranteasistecapacitacion> integranteasistecapacitacionList = integrante.getIntegranteasistecapacitacionList();
            for (Integranteasistecapacitacion integranteasistecapacitacionListIntegranteasistecapacitacion : integranteasistecapacitacionList) {
                integranteasistecapacitacionListIntegranteasistecapacitacion.setCedulaIntegrante(null);
                integranteasistecapacitacionListIntegranteasistecapacitacion = em.merge(integranteasistecapacitacionListIntegranteasistecapacitacion);
            }
            List<Informacion> informacionList = integrante.getInformacionList();
            for (Informacion informacionListInformacion : informacionList) {
                informacionListInformacion.setCedulaIntegrante(null);
                informacionListInformacion = em.merge(informacionListInformacion);
            }
            List<Permisosalida> permisosalidaList = integrante.getPermisosalidaList();
            for (Permisosalida permisosalidaListPermisosalida : permisosalidaList) {
                permisosalidaListPermisosalida.setCedulaIntegrante(null);
                permisosalidaListPermisosalida = em.merge(permisosalidaListPermisosalida);
            }
            List<Arma> armaList = integrante.getArmaList();
            for (Arma armaListArma : armaList) {
                armaListArma.setCedulaIntegrante(null);
                armaListArma = em.merge(armaListArma);
            }
            List<Pena> penaList = integrante.getPenaList();
            for (Pena penaListPena : penaList) {
                penaListPena.setCedulaIntegrante(null);
                penaListPena = em.merge(penaListPena);
            }
            em.remove(integrante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Integrante> findIntegranteEntities() {
        return findIntegranteEntities(true, -1, -1);
    }

    public List<Integrante> findIntegranteEntities(int maxResults, int firstResult) {
        return findIntegranteEntities(false, maxResults, firstResult);
    }

    private List<Integrante> findIntegranteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Integrante.class));
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

    public Integrante findIntegrante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Integrante.class, id);
        } finally {
            em.close();
        }
    }

    public int getIntegranteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Integrante> rt = cq.from(Integrante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
