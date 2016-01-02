/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorinis7;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import laboratorinis7.exceptions.NonexistentEntityException;

/**
 *
 * @author Vartotojas
 */
public class ExpensesJpaController implements Serializable {

    public ExpensesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Expenses expenses) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Taxuser taxuser = expenses.getTaxuser();
            if (taxuser != null) {
                taxuser = em.getReference(taxuser.getClass(), taxuser.getId());
                expenses.setTaxuser(taxuser);
            }
            em.persist(expenses);
            if (taxuser != null) {
                taxuser.getExpensesCollection().add(expenses);
                taxuser = em.merge(taxuser);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Expenses expenses) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Expenses persistentExpenses = em.find(Expenses.class, expenses.getId());
            Taxuser taxuserOld = persistentExpenses.getTaxuser();
            Taxuser taxuserNew = expenses.getTaxuser();
            if (taxuserNew != null) {
                taxuserNew = em.getReference(taxuserNew.getClass(), taxuserNew.getId());
                expenses.setTaxuser(taxuserNew);
            }
            expenses = em.merge(expenses);
            if (taxuserOld != null && !taxuserOld.equals(taxuserNew)) {
                taxuserOld.getExpensesCollection().remove(expenses);
                taxuserOld = em.merge(taxuserOld);
            }
            if (taxuserNew != null && !taxuserNew.equals(taxuserOld)) {
                taxuserNew.getExpensesCollection().add(expenses);
                taxuserNew = em.merge(taxuserNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = expenses.getId();
                if (findExpenses(id) == null) {
                    throw new NonexistentEntityException("The expenses with id " + id + " no longer exists.");
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
            Expenses expenses;
            try {
                expenses = em.getReference(Expenses.class, id);
                expenses.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The expenses with id " + id + " no longer exists.", enfe);
            }
            Taxuser taxuser = expenses.getTaxuser();
            if (taxuser != null) {
                taxuser.getExpensesCollection().remove(expenses);
                taxuser = em.merge(taxuser);
            }
            em.remove(expenses);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Expenses> findExpensesEntities() {
        return findExpensesEntities(true, -1, -1);
    }

    public List<Expenses> findExpensesEntities(int maxResults, int firstResult) {
        return findExpensesEntities(false, maxResults, firstResult);
    }

    private List<Expenses> findExpensesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Expenses.class));
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

    public Expenses findExpenses(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Expenses.class, id);
        } finally {
            em.close();
        }
    }

    public int getExpensesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Expenses> rt = cq.from(Expenses.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
