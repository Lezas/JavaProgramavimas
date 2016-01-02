/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorinis7;

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
import laboratorinis7.exceptions.NonexistentEntityException;

/**
 *
 * @author Vartotojas
 */
public class TaxuserJpaController implements Serializable {

    public TaxuserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Taxuser taxuser) {
        if (taxuser.getExpensesCollection() == null) {
            taxuser.setExpensesCollection(new ArrayList<Expenses>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Expenses> attachedExpensesCollection = new ArrayList<Expenses>();
            for (Expenses expensesCollectionExpensesToAttach : taxuser.getExpensesCollection()) {
                expensesCollectionExpensesToAttach = em.getReference(expensesCollectionExpensesToAttach.getClass(), expensesCollectionExpensesToAttach.getId());
                attachedExpensesCollection.add(expensesCollectionExpensesToAttach);
            }
            taxuser.setExpensesCollection(attachedExpensesCollection);
            em.persist(taxuser);
            for (Expenses expensesCollectionExpenses : taxuser.getExpensesCollection()) {
                Taxuser oldTaxuserOfExpensesCollectionExpenses = expensesCollectionExpenses.getTaxuser();
                expensesCollectionExpenses.setTaxuser(taxuser);
                expensesCollectionExpenses = em.merge(expensesCollectionExpenses);
                if (oldTaxuserOfExpensesCollectionExpenses != null) {
                    oldTaxuserOfExpensesCollectionExpenses.getExpensesCollection().remove(expensesCollectionExpenses);
                    oldTaxuserOfExpensesCollectionExpenses = em.merge(oldTaxuserOfExpensesCollectionExpenses);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Taxuser taxuser) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Taxuser persistentTaxuser = em.find(Taxuser.class, taxuser.getId());
            Collection<Expenses> expensesCollectionOld = persistentTaxuser.getExpensesCollection();
            Collection<Expenses> expensesCollectionNew = taxuser.getExpensesCollection();
            Collection<Expenses> attachedExpensesCollectionNew = new ArrayList<Expenses>();
            for (Expenses expensesCollectionNewExpensesToAttach : expensesCollectionNew) {
                expensesCollectionNewExpensesToAttach = em.getReference(expensesCollectionNewExpensesToAttach.getClass(), expensesCollectionNewExpensesToAttach.getId());
                attachedExpensesCollectionNew.add(expensesCollectionNewExpensesToAttach);
            }
            expensesCollectionNew = attachedExpensesCollectionNew;
            taxuser.setExpensesCollection(expensesCollectionNew);
            taxuser = em.merge(taxuser);
            for (Expenses expensesCollectionOldExpenses : expensesCollectionOld) {
                if (!expensesCollectionNew.contains(expensesCollectionOldExpenses)) {
                    expensesCollectionOldExpenses.setTaxuser(null);
                    expensesCollectionOldExpenses = em.merge(expensesCollectionOldExpenses);
                }
            }
            for (Expenses expensesCollectionNewExpenses : expensesCollectionNew) {
                if (!expensesCollectionOld.contains(expensesCollectionNewExpenses)) {
                    Taxuser oldTaxuserOfExpensesCollectionNewExpenses = expensesCollectionNewExpenses.getTaxuser();
                    expensesCollectionNewExpenses.setTaxuser(taxuser);
                    expensesCollectionNewExpenses = em.merge(expensesCollectionNewExpenses);
                    if (oldTaxuserOfExpensesCollectionNewExpenses != null && !oldTaxuserOfExpensesCollectionNewExpenses.equals(taxuser)) {
                        oldTaxuserOfExpensesCollectionNewExpenses.getExpensesCollection().remove(expensesCollectionNewExpenses);
                        oldTaxuserOfExpensesCollectionNewExpenses = em.merge(oldTaxuserOfExpensesCollectionNewExpenses);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = taxuser.getId();
                if (findTaxuser(id) == null) {
                    throw new NonexistentEntityException("The taxuser with id " + id + " no longer exists.");
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
            Taxuser taxuser;
            try {
                taxuser = em.getReference(Taxuser.class, id);
                taxuser.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The taxuser with id " + id + " no longer exists.", enfe);
            }
            Collection<Expenses> expensesCollection = taxuser.getExpensesCollection();
            for (Expenses expensesCollectionExpenses : expensesCollection) {
                expensesCollectionExpenses.setTaxuser(null);
                expensesCollectionExpenses = em.merge(expensesCollectionExpenses);
            }
            em.remove(taxuser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Taxuser> findTaxuserEntities() {
        return findTaxuserEntities(true, -1, -1);
    }

    public List<Taxuser> findTaxuserEntities(int maxResults, int firstResult) {
        return findTaxuserEntities(false, maxResults, firstResult);
    }

    private List<Taxuser> findTaxuserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Taxuser.class));
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

    public Taxuser findTaxuser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Taxuser.class, id);
        } finally {
            em.close();
        }
    }

    public int getTaxuserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Taxuser> rt = cq.from(Taxuser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
