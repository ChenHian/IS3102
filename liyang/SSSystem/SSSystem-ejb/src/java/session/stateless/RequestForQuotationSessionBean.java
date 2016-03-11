/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Item;
import entity.RequestForQuotation;
import entity.Supplier;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Hongyi
 */
@Stateless
@LocalBean
public class RequestForQuotationSessionBean implements RequestForQuotationSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    EntityManager em;
    
    public RequestForQuotationSessionBean() {
    }
    
    public RequestForQuotation addRequest(Set<Supplier> supplierList, Date deadline, int quantity, Item item){
        
        //add to request for quotation table
        Date today = new Date();
        RequestForQuotation rfq = new RequestForQuotation();
        
        for(Supplier s:supplierList){
            s = em.find(Supplier.class, s.getSupplierId());

            rfq.getSuppliers().add(s);
        }
                
        rfq.setDeadline(new java.sql.Date(deadline.getTime()));
        rfq.setDateRequested(new java.sql.Date(today.getTime()));
        rfq.setStatus("Open");
        rfq.setQuantity(quantity);
        rfq.setItem(item);
        em.persist(rfq);
        em.flush();
        
        return rfq;
    }
    
    public List<RequestForQuotation> getRequestForQuotations(){
        Query q = em.createQuery("SELECT r FROM RequestForQuotation r");
      
        return q.getResultList();
    }
    
    public RequestForQuotation getRequestForQuotationsById(Long id){
        RequestForQuotation rfq; 
        
        Query q = em.createQuery("SELECT r FROM RequestForQuotation r WHERE r.requestForQuotationId = :requestForQuotationId");
        q.setParameter("requestForQuotationId", id);
        
        if(!q.getResultList().isEmpty()){
            rfq = (RequestForQuotation) q.getSingleResult();
        }else{
            rfq = new RequestForQuotation();
            rfq.setItem(null);
        }
        return rfq;
    }
    
    /*public void closeRfq(Long id){
        RequestForQuotation rfq = em.find(RequestForQuotation.class, id);
        
        rfq.setStatus("Completed");
        em.persist(rfq);
        em.flush();
    }*/
    
}
