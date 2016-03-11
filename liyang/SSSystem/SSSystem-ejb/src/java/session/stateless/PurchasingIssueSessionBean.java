/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;


import entity.ReturnSupplier;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author duxianqi
 */
@Stateless
@LocalBean
public class PurchasingIssueSessionBean implements PurchasingIssueSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;

    public  PurchasingIssueSessionBean() {
    }

    public List<ReturnSupplier> getReturnSupplier() {
        Query q = em.createQuery("SELECT i FROM  ReturnSupplier i");
        return q.getResultList();
    }
}
