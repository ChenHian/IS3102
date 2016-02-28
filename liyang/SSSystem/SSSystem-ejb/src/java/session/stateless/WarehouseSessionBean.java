/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import java.util.ArrayList;
import entity.DistributionCenter;
import entity.DistributionCenterInventory;
import entity.Item;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author yingweiliu
 */
@Stateless
@LocalBean
public class WarehouseSessionBean implements WarehouseSessionBeanLocal {
    
    @PersistenceContext  
    private EntityManager entityManager;
    
    private int distributionCenterId;

    public WarehouseSessionBean() {}
    
    @Override
    public List<String> getAllDistributionCenterNames(){
        Query q = entityManager.createQuery("SELECT d.name FROM DistributionCenter d");
        return q.getResultList();
    }
    
    
    @Override
    public List<DistributionCenter> getAllDistributionCenters() {
        Query q = entityManager.createQuery("SELECT d FROM DistributionCenter d");
        return q.getResultList();
    }
    
    @Override
    public List <String> viewAllDistributionCenters(){
        
        Query q = entityManager.createQuery("SELECT d FROM DistributionCenter d");
        
        List <String> DCinfo = new ArrayList<String>();

        if(q.getResultList().isEmpty())
            return DCinfo;
        else{
            Object o = q.getSingleResult();
                DistributionCenter dc = (DistributionCenter) o;      
                DCinfo.add(dc.getName());
         return DCinfo;
        }   
    }      
    
    @Override
    public List<DistributionCenterInventory> getDCinventory() {
        Query q = entityManager.createQuery("SELECT d FROM DistributionCenterInventory d");
        return q.getResultList();
    }
    
    public void setAvailableQuantity() {
        
    }

    public void edit(DistributionCenterInventory updated) {
        Long id = updated.getDistributionCenterInventoryId();
        DistributionCenterInventory old = entityManager.find(DistributionCenterInventory.class, id);
        
        old.setAvailableQuantity(updated.getAvailableQuantity());
        old.setBlockedForReturn(updated.getBlockedForReturn());
        old.setReservedForCustomerOrders(updated.getReservedForCustomerOrders());
        old.setReservedForTransfer(updated.getReservedForTransfer());
        old.setThresholdAlert(updated.getThresholdAlert());
        
        entityManager.persist(old);
        
    }

    public void saveNewItem(String itemName, String distributionCenterName) {
        DistributionCenter distributionCenter = getDistributionCenter(distributionCenterName).get(0);
        Item item = getItem(itemName).get(0);
                
        
        Query q = entityManager.createQuery("SELECT d FROM DistributionCenterInventory d WHERE d.distributionCenterId = :dcId AND d.item.itemId = :itemId ");
        q.setParameter("itemId", item.getItemId());
        q.setParameter("dcId", distributionCenter.getDistributionCenterId()); 
        
        List <String> DCinfo = new ArrayList<String>();

        if(q.getResultList().isEmpty()) {
        
        DistributionCenterInventory dc = new DistributionCenterInventory();
        dc.setItem(item);
        dc.setDistributionCenterId(distributionCenter.getDistributionCenterId());
        dc.setAvailableQuantity(0);
        dc.setBlockedForReturn(0);
        dc.setReservedForCustomerOrders(0);
        dc.setReservedForTransfer(0);
        dc.setThresholdAlert(1000);
        entityManager.persist(dc);
        entityManager.flush();
        }
    }
    public List<DistributionCenter> getDistributionCenter(String name){
        return entityManager.createQuery("SELECT d FROM DistributionCenter d WHERE d.name LIKE :dcName").setParameter("dcName", name).getResultList();
       
    }

    private List<Item> getItem(String itemName) {
        return entityManager.createQuery("SELECT i FROM Item i WHERE i.itemName LIKE :itemName").setParameter("itemName", itemName).getResultList();
    }

    
}
