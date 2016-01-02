/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkinglot;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import parkinglot.exceptions.NonexistentEntityException;

/**
 *
 * @author Tomas Šiukščius
 */
public class ParkingLot_1JpaController implements Serializable {

    public ParkingLot_1JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ParkingLot_1 parkingLot_1) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lessor lessor = parkingLot_1.getLessor();
            if (lessor != null) {
                lessor = em.getReference(lessor.getClass(), lessor.getId());
                parkingLot_1.setLessor(lessor);
            }
            em.persist(parkingLot_1);
            if (lessor != null) {
                lessor.getParkingLotCollection().add(parkingLot_1);
                lessor = em.merge(lessor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ParkingLot_1 parkingLot_1) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ParkingLot_1 persistentParkingLot_1 = em.find(ParkingLot_1.class, parkingLot_1.getId());
            Lessor lessorOld = persistentParkingLot_1.getLessor();
            Lessor lessorNew = parkingLot_1.getLessor();
            if (lessorNew != null) {
                lessorNew = em.getReference(lessorNew.getClass(), lessorNew.getId());
                parkingLot_1.setLessor(lessorNew);
            }
            parkingLot_1 = em.merge(parkingLot_1);
            if (lessorOld != null && !lessorOld.equals(lessorNew)) {
                lessorOld.getParkingLotCollection().remove(parkingLot_1);
                lessorOld = em.merge(lessorOld);
            }
            if (lessorNew != null && !lessorNew.equals(lessorOld)) {
                lessorNew.getParkingLotCollection().add(parkingLot_1);
                lessorNew = em.merge(lessorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = parkingLot_1.getId();
                if (findParkingLot_1(id) == null) {
                    throw new NonexistentEntityException("The parkingLot_1 with id " + id + " no longer exists.");
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
            ParkingLot_1 parkingLot_1;
            try {
                parkingLot_1 = em.getReference(ParkingLot_1.class, id);
                parkingLot_1.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The parkingLot_1 with id " + id + " no longer exists.", enfe);
            }
            Lessor lessor = parkingLot_1.getLessor();
            if (lessor != null) {
                lessor.getParkingLotCollection().remove(parkingLot_1);
                lessor = em.merge(lessor);
            }
            em.remove(parkingLot_1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ParkingLot_1> findParkingLot_1Entities() {
        return findParkingLot_1Entities(true, -1, -1);
    }

    public List<ParkingLot_1> findParkingLot_1Entities(int maxResults, int firstResult) {
        return findParkingLot_1Entities(false, maxResults, firstResult);
    }

    private List<ParkingLot_1> findParkingLot_1Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ParkingLot_1.class));
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

    public ParkingLot_1 findParkingLot_1(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ParkingLot_1.class, id);
        } finally {
            em.close();
        }
    }

    public int getParkingLot_1Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ParkingLot_1> rt = cq.from(ParkingLot_1.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
