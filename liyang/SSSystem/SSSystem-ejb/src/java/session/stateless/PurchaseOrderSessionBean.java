/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Contract;
import entity.DistributionCenter;
import entity.Item;
import entity.PurchaseOrder;
import entity.PurchaseRequisition;
import entity.Quotation;
import entity.Supplier;
import java.util.Date;
import java.util.List;
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
public class PurchaseOrderSessionBean implements PurchaseOrderSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    public PurchaseOrderSessionBean(){}
    
    private NotificationSessionBean notificationSessionBean;
    private long selectedItemId;
    
    public List<Quotation> getPurchaseReference(){

        Date dateDummy = new Date();
        java.sql.Date dateD= new java.sql.Date(dateDummy.getTime());
      
        Query q = em.createQuery("SELECT q FROM Quotation q WHERE q.validEndDate >= :currentDate AND q.validStartDate <= :currentDate AND q.status = 'Approved' AND q.item.itemId = :itemId ORDER BY q.price");
        System.out.println("session bean in getpurcasereference itemid = " + selectedItemId); 
        q.setParameter("currentDate", dateD);
         q.setParameter("itemId", selectedItemId);
         System.out.println("session bean in getpurcasereference2 itemid = " + selectedItemId);
        
        return q.getResultList();
    }
    
    public List<Contract> getPurchaseReferenceC(){

        Date dateDummy = new Date();
        java.sql.Date dateD= new java.sql.Date(dateDummy.getTime());
      System.out.println("C session bean in getpurcasereference itemid = " + selectedItemId); 
        Query q = em.createQuery("SELECT c FROM Contract c WHERE c.contractEndDate >= :currentDate AND c.contractStartDate <= :currentDate AND c.item.itemId = :itemId ORDER BY c.price");
         q.setParameter("currentDate", dateD);
         
         q.setParameter("itemId", selectedItemId);
        // q.setParameter("itemId", itemId);
        
        return q.getResultList();
    }
    
    public List<PurchaseOrder> getPurchaseOrder(){
      
        Query q = em.createQuery("SELECT p FROM PurchaseOrder p WHERE p.status != 'Completed' AND p.status != 'Void'");
        
        return q.getResultList();
    }
    
    public List<PurchaseOrder> getPurchaseOrderH(){
      
        Query q = em.createQuery("SELECT p FROM PurchaseOrder p WHERE p.status = 'Completed' OR p.status = 'Void'");
        
        return q.getResultList();
    }
    
    public PurchaseOrder createPurchaseOrderQ(long selectedPurchaseRequisitionId, String selectedCenterName, String selectedCenterAddress, int selectedQuantity, Date expectedDeliveryDate, String referenceType, long selectedReferenceId){
        System.out.println("test");
        
        PurchaseOrder p = new PurchaseOrder();
        PurchaseRequisition pr = new PurchaseRequisition();
        Item i = new Item();
        Supplier s = new Supplier();
        
        Date dateDummy = new Date();
        java.sql.Date date= new java.sql.Date(dateDummy.getTime());
        p.setDateCreated(date);
        date= new java.sql.Date(expectedDeliveryDate.getTime());
        p.setExpectedDeliveryDate(date);
        p.setDeliverToCentreName(selectedCenterName);
        
        p.setDeliverToAddress(selectedCenterAddress);
        p.setDocumentReferenceType(referenceType);
        p.setDocumentReferenceNumber(selectedQuantity);
        p.setQuantity(selectedQuantity);
        p.setStatus("Open");
        
        Query q = em.createQuery("SELECT i from Item i WHERE i.itemId = :itemId");
        q.setParameter("itemId", selectedItemId);
        if(!q.getResultList().isEmpty()){
                Object o = q.getSingleResult();
                i = (Item) o;
                p.setItem(i);
                
        }
        
        q = em.createQuery("SELECT qu from Quotation qu WHERE qu.quotationId = :quotationId");
        q.setParameter("quotationId", selectedReferenceId);
        if(!q.getResultList().isEmpty()){
                Object o = q.getSingleResult();
                Quotation qu = (Quotation) o;
                p.setUnitPrice(qu.getPrice());
                p.setTotalAmount(qu.getPrice() * selectedQuantity);
                
                Query q2 = em.createQuery("SELECT s from Supplier s WHERE s.supplierId = :supplierId");
                q2.setParameter("supplierId", qu.getSupplier().getSupplierId());
                if(!q2.getResultList().isEmpty()){
                    Object obj = q2.getSingleResult();
                    s = (Supplier) obj;   
                    p.setSupplier(s);
                }
        }
        
        q = em.createQuery("SELECT max(po.purchaseOrderId) FROM PurchaseOrder po");
            if(!q.getResultList().isEmpty()){
                Object obj = q.getSingleResult();
                long maxPurchaseOrderId = 0;
                if(obj != null){
                    maxPurchaseOrderId = (Long) obj;
                    p.setPurchaseOrderId(maxPurchaseOrderId + 1);
                }
                else{
                    p.setPurchaseOrderId(Long.valueOf(1));
                }
        }
            
        q = em.createQuery("SELECT pr FROM PurchaseRequisition pr WHERE pr.purchaseRequisitionId = :purchaseRequisitionId");
        q.setParameter("purchaseRequisitionId", selectedPurchaseRequisitionId);
        if(!q.getResultList().isEmpty()){
                Object o = q.getSingleResult();
                pr = (PurchaseRequisition) o;
                p.setPurchaseRequisition(pr);
                pr.setStatus("Order Placed");
                
        }
        
        em.merge(p);
        pr.setPurchaseOrder(p);
        em.merge(pr);
        s.getPurchaseOrders().add(p);
        em.merge(s);
        i.getPurchaseOrder().add(p);
        em.merge(i);
        
       // notificationSessionBean.createNotification("WAREHOUSE DIVISION","PURCHASING DIVISION","Normal","A purchase order has been made for " +p.getDeliverToCentreName() + ". Item ordered: " + i.getItemName());
        
        return p;
    }
    
    public PurchaseOrder createPurchaseOrderC(long selectedPurchaseRequisitionId, String selectedCenterName, String selectedCenterAddress, int selectedQuantity, Date expectedDeliveryDate, String referenceType, long selectedReferenceId){
        System.out.println("test");
        //ystem.out.println("selectedItemId = " + itemId);
        System.out.println("selectedItemId = " + selectedItemId);
        PurchaseOrder p = new PurchaseOrder();
        PurchaseRequisition pr = new PurchaseRequisition();
        Item i = new Item();
        Supplier s = new Supplier();
        
        Date dateDummy = new Date();
        java.sql.Date date= new java.sql.Date(dateDummy.getTime());
        p.setDateCreated(date);
        date= new java.sql.Date(expectedDeliveryDate.getTime());
        p.setExpectedDeliveryDate(date);
        p.setDeliverToCentreName(selectedCenterName);
        p.setDeliverToAddress(selectedCenterAddress);
        p.setDocumentReferenceType(referenceType);
        p.setDocumentReferenceNumber(selectedQuantity);
        p.setQuantity(selectedQuantity);
        p.setStatus("Open");
        System.out.println("check 1");
        Query q = em.createQuery("SELECT i from Item i WHERE i.itemId = :itemId");
        q.setParameter("itemId", selectedItemId);
        if(!q.getResultList().isEmpty()){
                Object o = q.getSingleResult();
                i = (Item) o;
                p.setItem(i);
                
        }
        System.out.println("check 2");
        q = em.createQuery("SELECT c from Contract c WHERE c.contractId = :contractId");
        q.setParameter("contractId", selectedReferenceId);
        if(!q.getResultList().isEmpty()){
                Object o = q.getSingleResult();
                Contract c = (Contract) o;
                p.setUnitPrice(c.getPrice());
                p.setTotalAmount(c.getPrice() * selectedQuantity);
                
                Query q2 = em.createQuery("SELECT s from Supplier s WHERE s.supplierId = :supplierId");
                q2.setParameter("supplierId", c.getSupplier().getSupplierId());
                if(!q2.getResultList().isEmpty()){
                    Object obj = q2.getSingleResult();
                    s = (Supplier) obj;   
                    p.setSupplier(s);
                }
        }
        System.out.println("check 3");
        q = em.createQuery("SELECT max(po.purchaseOrderId) FROM PurchaseOrder po");
            if(!q.getResultList().isEmpty()){
                Object obj = q.getSingleResult();
                long maxPurchaseOrderId = 0;
                if(obj != null){
                    maxPurchaseOrderId = (Long) obj;
                    p.setPurchaseOrderId(maxPurchaseOrderId + 1);
                }
                else{
                    p.setPurchaseOrderId(Long.valueOf(1));
                }
        }
        System.out.println("check 4"); 
        q = em.createQuery("SELECT pr FROM PurchaseRequisition pr WHERE pr.purchaseRequisitionId = :purchaseRequisitionId");
        q.setParameter("purchaseRequisitionId", selectedPurchaseRequisitionId);
        if(!q.getResultList().isEmpty()){
                Object o = q.getSingleResult();
                pr = (PurchaseRequisition) o;
                p.setPurchaseRequisition(pr);
                pr.setStatus("Order Placed");
                
        }
        System.out.println("check 5");
        em.merge(p);
        pr.setPurchaseOrder(p);
        em.merge(pr);
        s.getPurchaseOrders().add(p);
        em.merge(s);
        i.getPurchaseOrder().add(p);
        em.merge(i);
        System.out.println("check 6");
                notificationSessionBean.createNotification("WAREHOUSE DIVISION","PURCHASING DIVISION","Normal","A purchase order has been made for " +p.getDeliverToCentreName() + ". Item ordered: " + i.getItemName());
        
        return p;
    }
    
    public String getSelectedCenterAddress(String selectedCenterName){
        if(selectedCenterName.substring(0, 2).equals("DC")){
            System.out.println("selectedCenterName = " + selectedCenterName);
            Query q = em.createQuery("SELECT dc from DistributionCenter dc WHERE dc.name = :dcName");
            q.setParameter("dcName", selectedCenterName);
            if(!q.getResultList().isEmpty()){
                Object o = q.getSingleResult();
                DistributionCenter dc = (DistributionCenter) o;
                System.out.println("DCAddress = " + dc.getAddress());
                return dc.getAddress();
                
            }
         
        
        }
        else{
            System.out.println("Store center detected");
        
        }
            
        return "";
    }

    /**
     * @return the selectedItemId
     */
    public long getSelectedItemId() {
        return selectedItemId;
    }

    /**
     * @param selectedItemId the selectedItemId to set
     */
    public void setSelectedItemId(long selectedItemId) {
        this.selectedItemId = selectedItemId;
    }

    public PurchaseOrder voidPurchaseOrder(long purchaseOrderId){
        PurchaseOrder p = new PurchaseOrder();
        Query q = em.createQuery("SELECT p FROM PurchaseOrder p WHERE p.purchaseOrderId = :purchaseOrderId");
        q.setParameter("purchaseOrderId", purchaseOrderId);
        
       // boolean status = false; check status if needed
        
        if(!q.getResultList().isEmpty()){
            Object o = q.getSingleResult();
            p = (PurchaseOrder) o;
            
            
           q = em.createQuery("SELECT pr FROM PurchaseRequisition pr WHERE pr.purchaseRequisitionId = :purchaseRequisitionId");
           q.setParameter("purchaseRequisitionId", p.getPurchaseRequisition().getPurchaseRequisitionId());
           
           if(!q.getResultList().isEmpty()){
                Object obj = q.getSingleResult();
                PurchaseRequisition pr = (PurchaseRequisition) obj;
                pr.setStatus("Open");
                pr.setPurchaseOrder(null);
                
                p.setStatus("Void");
                p.setPurchaseRequisition(null);
                em.merge(pr);
                em.merge(p);
           }
           System.out.println("TESTing = " + p.getPurchaseRequisition());
            
        }
        return p;
    }
    
    
}
