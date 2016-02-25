/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.warehouse;

import entity.warehouse_entity.PurchaseRequsition;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author yingweiliu
 */
@Stateless
@LocalBean

public class PRSessionBean implements PRSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    private EntityManager entityManager;

     public Long createPR(String status){
        PurchaseRequsition pr = new PurchaseRequsition(); 
        pr.setStatus(status);
        entityManager.persist(pr);
        entityManager.flush();
        return pr.getPurchaseRequisitionId();
    }
  
}
