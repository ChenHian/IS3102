/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Item;
import entity.PurchaseRequisition;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author QianJun
 */
@Stateless
@LocalBean
public class PurchaseRequisitionSessionBean implements PurchaseRequisitionSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    public PurchaseRequisitionSessionBean(){}
            
    public List<PurchaseRequisition> getPurchaseRequisitionWith(){

        Date dateDummy = new Date();
        java.sql.Date dateD= new java.sql.Date(dateDummy.getTime());

       // q = em.createQuery("SELECT p FROM PurchaseRequisition p WHERE p.status='Open' AND (p.item.contractItem.contract.contractEndDate = '2016-02-17') ORDER BY p.dateCreated, p.dateRequest");
      
        Query q = em.createQuery("SELECT p FROM PurchaseRequisition p WHERE p.status='Open' AND ((p.item.itemId IN (Select c.item.itemId FROM Contract c WHERE c.contractEndDate >= :currentDate AND c.contractStartDate <= :currentDate)) OR (p.item.itemId IN (Select q.item.itemId FROM Quotation q WHERE q.validEndDate >= :currentDate AND q.validStartDate <= :currentDate))) ORDER BY p.dateCreated, p.dateRequest");
         q.setParameter("currentDate", dateD);
        
        return q.getResultList();
    }
    
    public List<PurchaseRequisition> getPurchaseRequisitionWithout(){

        Date dateDummy = new Date();
        java.sql.Date dateD= new java.sql.Date(dateDummy.getTime());

       // q = em.createQuery("SELECT p FROM PurchaseRequisition p WHERE p.status='Open' AND (p.item.contractItem.contract.contractEndDate = '2016-02-17') ORDER BY p.dateCreated, p.dateRequest");
      
        Query q = em.createQuery("SELECT p FROM PurchaseRequisition p WHERE p.status='Open' AND (p.item.itemId NOT IN (Select c.item.itemId FROM Contract c WHERE c.contractEndDate >= :currentDate AND c.contractStartDate <= :currentDate)) AND (p.item.itemId NOT IN (Select q.item.itemId FROM Quotation q WHERE q.validEndDate >= :currentDate AND q.validStartDate <= :currentDate)) ORDER BY p.dateCreated, p.dateRequest");
         q.setParameter("currentDate", dateD);
        
        return q.getResultList();
    }
    
    public List<Item> getItemWOList(){
        Date dateDummy = new Date();
        java.sql.Date dateD= new java.sql.Date(dateDummy.getTime());

       // q = em.createQuery("SELECT p FROM PurchaseRequisition p WHERE p.status='Open' AND (p.item.contractItem.contract.contractEndDate = '2016-02-17') ORDER BY p.dateCreated, p.dateRequest");
      
        Query q = em.createQuery("SELECT DISTINCT p.item FROM PurchaseRequisition p WHERE p.status='Open' AND (p.item.itemId NOT IN (Select c.item.itemId FROM Contract c WHERE c.contractEndDate >= :currentDate AND c.contractStartDate <= :currentDate)) AND (p.item.itemId NOT IN (Select q.item.itemId FROM Quotation q WHERE q.validEndDate >= :currentDate AND q.validStartDate <= :currentDate)) ORDER BY p.dateCreated, p.dateRequest");
         q.setParameter("currentDate", dateD);
        
        return q.getResultList();
        
    }
    
    // to remove
   /* public List<PurchaseRequisition> getSelectedItem(Long selectedItemId){
        Query q = em.createQuery("SELECT p FROM PurchaseRequisition p WHERE p.item.itemId = :selectedItemId");
        q.setParameter("selectedItemId", selectedItemId);
        //change query to status = open after updating the entity
        
        

        return q.getResultList();
    }
   */ 
  /*  public int getTotalQuantity(int selectedItemId){
        int total = 0;
        
        Query q = em.createQuery("SELECT p FROM PurchaseRequisitionItem p where p.item.itemId = :selectedItemId");
        q.setParameter("selectedItemId", selectedItemId);
        //change query to status = open after updating the entity
        if(!q.getResultList().isEmpty()){
            Object o = new Object();
           
            PurchaseRequisitionItem prItem = new PurchaseRequisitionItem();
            for(int i = 0; i<q.getResultList().size(); i++){
                o = q.getResultList().get(i);
                prItem = (PurchaseRequisitionItem) o;
                total += prItem.getQuantityRequested();
            }
            
        }

        return total;
    }
   */
    /*
    public Map<Long, String> getItemBasicList(){
        Map<Long, String> itemList = new LinkedHashMap<Long, String>();
                
        Query q = em.createQuery("SELECT distinct p.item.itemId FROM PurchaseRequisitionItem p");
        System.out.println("Passed 1st query");
       // Query r = em.createQuery("SELECT distinct p.item.itemId FROM Purchaserequisitionitem p");
        List<Object> qResultList = q.getResultList();
        Object obj;
        Long id;
        Item i = new Item();
        
        for(int j=0; j<qResultList.size(); j++){
            obj = qResultList.get(j);
            id = (Long) obj;
            System.out.println("item id = " + id);
            q = em.createQuery("SELECT i FROM Item i WHERE i.itemId = :itemId");
            System.out.println("Passed here");
            q.setParameter("itemId", id);
            
            if(!q.getResultList().isEmpty()){
                obj = q.getSingleResult();
                i = (Item) obj;

                itemList.put(i.getItemId(), i.getItemName());
            }
        }

        return itemList;
    }
    */
  /*  public void updatePurchaseRequisitionStatusAction(List<PurchaseRequisitionItem> selectedPRItems){

        Query q;

        for(int i = 0; i<selectedPRItems.size(); i++){
            q = em.createQuery("SELECT p FROM PurchaseRequisitionItem p WHERE p.purchaseRequisition.purchaseRequisitionId = :prId AND p.purchaseRequisitionItemId = :prItemId");
            q.setParameter("prId", selectedPRItems.get(i).getPurchaseRequisition().getPurchaseRequisitionId());
            q.setParameter("prItemId", selectedPRItems.get(i).getPurchaseRequisitionItemId());
            
            if(!q.getResultList().isEmpty()){
                Object o = q.getSingleResult();
                PurchaseRequisitionItem p = (PurchaseRequisitionItem) o;
                
                System.out.println("PR SESSION BEAN --> updatePRStatus passed 1 time");
                //set status and merge
                //em.merge(p);


            }
        }

    }
    */
}
