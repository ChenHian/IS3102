/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Contract;
import entity.Item;
import entity.ItemType;
import entity.Supplier;
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
 * @author duxianqi
 */
@Stateless
@LocalBean
public class ContractSessionBean implements ContractSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;

    public ContractSessionBean() {
    }

    public List<Contract> getContract() {
        Query q = em.createQuery("SELECT i FROM Contract i WHERE i.isDelete= false");
        return q.getResultList();
    }

    public void removeContract(Long contractId) {

        Query q = em.createQuery("SELECT i FROM Contract i WHERE i.contractId = :contractId");
        q.setParameter("contractId", contractId);

        // boolean status = false; check status if needed
        if (!q.getResultList().isEmpty()) {
            Object o = q.getSingleResult();
            Contract i = (Contract) o;

            i.setIsDelete(true);
            em.merge(i);

        }
    }

    public Contract getContractById(Long id) {
        Query q = em.createNamedQuery("SELECT c FROM Contract c WHERE i.contractId = :contractId");
        q.setParameter("contractId", id);

        Contract contract = new Contract();

        if (!q.getResultList().isEmpty()) {
            Object o = q.getSingleResult();
            contract = (Contract) o;

        }
        return contract;
    }

    public void addContract(Long contractId, java.util.Date contractStartDate, java.util.Date contractEndDate,
            String paymentTerms, String deliveryTerms, String purchaseTerms, String returnTerms,
            int deliveryLeadTime, boolean automaticPurchase, double price, Long supplierIdContract,Long itemm, Long itemTypeId) {

        Contract i = new Contract();
        /*Query q = em.createQuery("SELECT max(i.contractId) FROM Contract i");
        if (!q.getResultList().isEmpty()) {
            Object obj = q.getSingleResult();
            long maxContractId = 0;
            if (obj != null) {
                maxContractId = (Long) obj;
                i.setContractId(maxContractId + 1);
            } else {
                i.setContractId(Long.valueOf(1));
            }
            System.out.println("ContractSessionBean: add contract() --> the  maxContractId is " + maxContractId);
        }*/

        i.setPaymentTerms(paymentTerms);
        i.setDeliveryTerms(deliveryTerms);
        i.setPurchaseTerms(purchaseTerms);
        i.setReturnTerms(returnTerms);
        i.setDeliveryLeadTime(deliveryLeadTime);
        i.setAutomaticPurchase(automaticPurchase);
        i.setPrice(price);

        java.sql.Date startDate = new java.sql.Date(contractStartDate.getTime());
        i.setContractStartDate(startDate);

        java.sql.Date endDate = new java.sql.Date(contractEndDate.getTime());
        i.setContractEndDate(endDate);

        Query q = em.createQuery("SELECT b from Supplier b where b.supplierId=:supplierId");
        
        q.setParameter("supplierId", supplierIdContract);

        if (!q.getResultList().isEmpty()) {
            Object obj = q.getSingleResult();
            Supplier supplier = (Supplier) obj;

            i.setSupplier(supplier);

        }
        
         q = em.createQuery("SELECT i from Item i where i.itemId=:itemId");
         
         q.setParameter("itemId", itemm);

          if (!q.getResultList().isEmpty()) {
            Object obj = q.getSingleResult();
            Item item = (Item) obj;

            i.setItem(item);

        }
         
        
        em.merge(i);

    }
    
   /*public void addContract(java.util.Date contractStartDate, java.util.Date contractEndDate,
            String paymentTerms, String deliveryTerms, String purchaseTerms, String returnTerms,
            int deliveryLeadTime, boolean automaticPurchase, double price, Long supplierIdContract) {
        
            Contract i = new Contract();

        java.sql.Date startDate = new java.sql.Date(contractStartDate.getTime());
        i.setContractStartDate(startDate);

        java.sql.Date endDate = new java.sql.Date(contractEndDate.getTime());
        i.setContractEndDate(endDate);

        i.setPaymentTerms(paymentTerms);
        i.setDeliveryTerms(deliveryTerms);
        i.setPurchaseTerms(purchaseTerms);
        i.setReturnTerms(returnTerms);
        i.setDeliveryLeadTime(deliveryLeadTime);
        i.setAutomaticPurchase(automaticPurchase);
        i.setPrice(price);
        
         Query q = em.createQuery("SELECT b from Supplier b where b.supplierId=:supplierId");
         q.setParameter("supplierId", supplierIdContract);

        if (!q.getResultList().isEmpty()) {
            Object obj = q.getSingleResult();
            Supplier supplier = (Supplier) obj;

            i.setSupplier(supplier);

            //System.out.println(item.getItemId());
            //System.out.println(item.getItemName());
        }
        em.persist(i);
        return i.getContractId();
        
    }*/
 

    public Map<Long, String> getSupplierBasicList() {
        Map<Long, String> supplierList = new LinkedHashMap<Long, String>();

        Query q = em.createQuery("SELECT b FROM Supplier b");
        List<Object> qResultList = q.getResultList();
        Object obj;

        for (int j = 0; j < qResultList.size(); j++) {
            obj = qResultList.get(j);
            Supplier i = (Supplier) obj;

            supplierList.put(i.getSupplierId(), i.getCompanyName());
        }

        return supplierList;
    }
    
    public Map<Long, String> getItemTypes(){
        Map<Long, String> itemTypeList = new LinkedHashMap<Long, String>();

        Query q = em.createQuery("SELECT b FROM ItemType b");
        List<Object> qResultList = q.getResultList();
        Object obj;

        for (int j = 0; j < qResultList.size(); j++) {
            obj = qResultList.get(j);
            ItemType i = (ItemType) obj;

            itemTypeList.put(i.getItemTypeId(), i.getItemTypeDescription());
        }

        return itemTypeList;
    }
    
    public Map<Long, String> getItems(){
        Map<Long, String> itemList = new LinkedHashMap<Long, String>();

        Query q = em.createQuery("SELECT b FROM Item b");
        List<Object> qResultList = q.getResultList();
        Object obj;

        for (int j = 0; j < qResultList.size(); j++) {
            obj = qResultList.get(j);
            Item i = (Item) obj;

            itemList.put(i.getItemId(), i.getItemName());
        }

        return itemList;
    }

    
            /*java.util.Date contractStartDate, 
            java.util.Date contractEndDate,String paymentTerms, String deliveryTerms, String purchaseTerms, 
            String returnTerms,int deliveryLeadTime,,double price*/
    public void updateContract(Long contractId,boolean automaticPurchase) {
        
          Query q = em.createQuery("SELECT k FROM Contract k WHERE k.contractId = :contractId");
          q.setParameter("contractId", contractId); 
        
          //System.out.println("contractId1 in session bean = " + contractId);
         if(!q.getResultList().isEmpty()){
            Object o = q.getSingleResult();
            Contract i = (Contract) o;
  
             /*if(paymentTerms!=null){
             i.setPaymentTerms(paymentTerms);
             em.merge(i);
             }
             if(deliveryTerms!=null){
             i.setDeliveryTerms(deliveryTerms);
             em.merge(i);
             }
              if(purchaseTerms!=null){
             i.setPurchaseTerms(purchaseTerms);
             em.merge(i);
              }
              if(returnTerms!=null){
             i.setReturnTerms(returnTerms);
             em.merge(i);
               }
             i.setDeliveryLeadTime(deliveryLeadTime);
             em.merge(i);*/

             i.setAutomaticPurchase(automaticPurchase);
             em.merge(i);

            /* i.setPrice(price);
             em.merge(i);

             java.sql.Date startDate = new java.sql.Date(contractStartDate.getTime());
             i.setContractStartDate(startDate);
             em.merge(i);

             java.sql.Date endDate = new java.sql.Date(contractEndDate.getTime());
             i.setContractEndDate(endDate);
             em.merge(i);*/
        }

    }

    public Map<Long, String> getActiveItems() {
        Map<Long, String> itemList = new LinkedHashMap<Long, String>();

        Query q = em.createQuery("SELECT b FROM Item b WHERE b.isDelete = false");
        List<Object> qResultList = q.getResultList();
        Object obj;

        for (int j = 0; j < qResultList.size(); j++) {
            obj = qResultList.get(j);
            Item i = (Item) obj;

            itemList.put(i.getItemId(), i.getItemName());
        }

        return itemList;
    }
    
   
}
