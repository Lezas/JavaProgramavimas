/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkinglot;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import parkinglot.exceptions.NonexistentEntityException;

/**
 *
 * @author Tomas Šiukščius
 */
public class LessorJpaController implements Serializable {

    public LessorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lessor lessor) {
        if (lessor.getParkingLotCollection() == null) {
            lessor.setParkingLotCollection(new ArrayList<ParkingLot_1>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ParkingLot_1> attachedParkingLotCollection = new ArrayList<ParkingLot_1>();
            for (ParkingLot_1 parkingLotCollectionParkingLot_1ToAttach : lessor.getParkingLotCollection()) {
                parkingLotCollectionParkingLot_1ToAttach = em.getReference(parkingLotCollectionParkingLot_1ToAttach.getClass(), parkingLotCollectionParkingLot_1ToAttach.getId());
                attachedParkingLotCollection.add(parkingLotCollectionParkingLot_1ToAttach);
            }
            lessor.setParkingLotCollection(attachedParkingLotCollection);
            em.persist(lessor);
            for (ParkingLot_1 parkingLotCollectionParkingLot_1 : lessor.getParkingLotCollection()) {
                Lessor oldLessorOfParkingLotCollectionParkingLot_1 = parkingLotCollectionParkingLot_1.getLessor();
                parkingLotCollectionParkingLot_1.setLessor(lessor);
                parkingLotCollectionParkingLot_1 = em.merge(parkingLotCollectionParkingLot_1);
                if (oldLessorOfParkingLotCollectionParkingLot_1 != null) {
                    oldLessorOfParkingLotCollectionParkingLot_1.getParkingLotCollection().remove(parkingLotCollectionParkingLot_1);
                    oldLessorOfParkingLotCollectionParkingLot_1 = em.merge(oldLessorOfParkingLotCollectionParkingLot_1);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lessor lessor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lessor persistentLessor = em.find(Lessor.class, lessor.getId());
            Collection<ParkingLot_1> parkingLotCollectionOld = persistentLessor.getParkingLotCollection();
            Collection<ParkingLot_1> parkingLotCollectionNew = lessor.getParkingLotCollection();
            Collection<ParkingLot_1> attachedParkingLotCollectionNew = new ArrayList<ParkingLot_1>();
            for (ParkingLot_1 parkingLotCollectionNewParkingLot_1ToAttach : parkingLotCollectionNew) {
                parkingLotCollectionNewParkingLot_1ToAttach = em.getReference(parkingLotCollectionNewParkingLot_1ToAttach.getClass(), parkingLotCollectionNewParkingLot_1ToAttach.getId());
                attachedParkingLotCollectionNew.add(parkingLotCollectionNewParkingLot_1ToAttach);
            }
            parkingLotCollectionNew = attachedParkingLotCollectionNew;
            lessor.setParkingLotCollection(parkingLotCollectionNew);
            lessor = em.merge(lessor);
            for (ParkingLot_1 parkingLotCollectionOldParkingLot_1 : parkingLotCollectionOld) {
                if (!parkingLotCollectionNew.contains(parkingLotCollectionOldParkingLot_1)) {
                    parkingLotCollectionOldParkingLot_1.setLessor(null);
                    parkingLotCollectionOldParkingLot_1 = em.merge(parkingLotCollectionOldParkingLot_1);
                }
            }
            for (ParkingLot_1 parkingLotCollectionNewParkingLot_1 : parkingLotCollectionNew) {
                if (!parkingLotCollectionOld.contains(parkingLotCollectionNewParkingLot_1)) {
                    Lessor oldLessorOfParkingLotCollectionNewParkingLot_1 = parkingLotCollectionNewParkingLot_1.getLessor();
                    parkingLotCollectionNewParkingLot_1.setLessor(lessor);
                    parkingLotCollectionNewParkingLot_1 = em.merge(parkingLotCollectionNewParkingLot_1);
                    if (oldLessorOfParkingLotCollectionNewParkingLot_1 != null && !oldLessorOfParkingLotCollectionNewParkingLot_1.equals(lessor)) {
                        oldLessorOfParkingLotCollectionNewParkingLot_1.getParkingLotCollection().remove(parkingLotCollectionNewParkingLot_1);
                        oldLessorOfParkingLotCollectionNewParkingLot_1 = em.merge(oldLessorOfParkingLotCollectionNewParkingLot_1);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lessor.getId();
                if (findLessor(id) == null) {
                    throw new NonexistentEntityException("The lessor with id " + id + " no longer exists.");
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
            Lessor lessor;
            try {
                lessor = em.getReference(Lessor.class, id);
                lessor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lessor with id " + id + " no longer exists.", enfe);
            }
            Collection<ParkingLot_1> parkingLotCollection = lessor.getParkingLotCollection();
            for (ParkingLot_1 parkingLotCollectionParkingLot_1 : parkingLotCollection) {
                parkingLotCollectionParkingLot_1.setLessor(null);
                parkingLotCollectionParkingLot_1 = em.merge(parkingLotCollectionParkingLot_1);
            }
            em.remove(lessor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lessor> findLessorEntities() {
        return findLessorEntities(true, -1, -1);
    }

    public List<Lessor> findLessorEntities(int maxResults, int firstResult) {
        return findLessorEntities(false, maxResults, firstResult);
    }

    private List<Lessor> findLessorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lessor.class));
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

    public Lessor findLessor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lessor.class, id);
        } finally {
            em.close();
        }
    }

    public int getLessorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lessor> rt = cq.from(Lessor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
