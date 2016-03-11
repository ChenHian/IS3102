/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.PurchaseOrder;
import java.util.Date;
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
public class PurchasingSessionBean implements PurchasingSessionBeanLocal {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    
     public PurchasingSessionBean() {
    }

    public List<PurchaseOrder> getPurchaseOrder() {
        
        Date dateDummy = new Date();
        java.sql.Date dateD = new java.sql.Date(dateDummy.getTime());
        
        Query q = em.createQuery("SELECT i FROM PurchaseOrder i WHERE i.expectedDeliveryDate < :currentDate AND i.status!='Completed'");
        q.setParameter("currentDate", dateD);
        return q.getResultList();
    }

}
