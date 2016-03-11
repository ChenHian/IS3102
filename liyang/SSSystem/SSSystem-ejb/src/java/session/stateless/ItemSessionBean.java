/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;


import entity.Item;
import entity.Brand;
import entity.DistributionCenter;
import entity.DistributionCenterInventory;
import entity.ItemType;
import entity.Supplier;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ejb.LocalBean;

/**
 *
 * @author QianJun
 */
@Stateless
@LocalBean
public class ItemSessionBean implements ItemSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    EntityManager em;
    
    //private int itemId;
    
    public ItemSessionBean(){}
    /*
   
    }
    */
    public List<Item> getItems(){
        Query q = em.createQuery("SELECT i FROM Item i WHERE i.isDelete= false ");
        return q.getResultList();
    }
    
    public List<Item> getItemsByTypeId(Long id){
        Query q = em.createQuery("SELECT i FROM Item i WHERE i.itemType.itemTypeId ='" + id + "' AND i.isDelete = false" );
        
        return q.getResultList();
    }
    
    public Item getItemsById(Long id){
        Query q = em.createQuery("SELECT i FROM Item i WHERE i.itemId ='" + id + "'");
        
        Item item = new Item();
        
        if(!q.getResultList().isEmpty()){
            item = (Item) q.getSingleResult();
        }
        
        return item;
    }
    
    public List<Supplier> getSuppliers(Item it){
        Query q = em.createQuery("SELECT s FROM Supplier s, SUPPLIER_ITEM si WHERE  ='" + it.getItemId() + "'");
    
        Object obj = q.getSingleResult();
        Item i = (Item) obj;
        
        return new ArrayList<Supplier>(i.getSuppliers());
    }
    
    /**
     *
     * @return
     */
    public List<Brand> getBrand(){
        Query q = em.createQuery("SELECT j FROM Brand j WHERE j.isDelete=false");
        return q.getResultList();
    }
    
    
    /*Long brandId, String brandName, String brandDescription, Long itemTypeId, String itemTypeDescription,
    String itemCategory, String itemSubCategory, String storageType, boolean isPerishable, String measurementType*/
    
    public void updateItem(Long itemId, String itemName, String itemDescription, 
            boolean itemReturnable, int returnablePeriod) {
        
         Query q = em.createQuery("SELECT k FROM Item k WHERE k.itemId = :itemId");
         q.setParameter("itemId", itemId); 
        
       // boolean status = false; check status if needed
        
         if(!q.getResultList().isEmpty()){
            Object o = q.getSingleResult();
            Item k = (Item) o;
 
            if(itemName != null){
                k.setItemName(itemName);
                em.merge(k);
            }
            if(itemDescription != null){
                k.setItemDescription(itemDescription);
                em.merge(k);
            }

                k.setItemReturnable(itemReturnable);
                em.merge(k);

                k.setReturnablePeriod(returnablePeriod);
                em.merge(k);
   
        }
    }

    public void addItem(String itemName, String itemDescription,
            boolean itemReturnable,int returnablePeriod, Long brandIdItem, Long itemTypeIdItem) {
        
            Item i = new Item();
            Query q = em.createQuery("SELECT max(i.itemId) FROM Item i");
            if(!q.getResultList().isEmpty()){
                Object obj = q.getSingleResult();
                long maxItemId = 0;
                if(obj != null){
                    maxItemId = (Long) obj;
                    i.setItemId(maxItemId + 1);
                }
                else{
                    i.setItemId(Long.valueOf(1));
                }
                System.out.println("ItemSessionBean: add item() --> the maxItemId is " + maxItemId);
            }
            

            i.setItemName(itemName);
            i.setItemDescription(itemDescription);
            i.setItemReturnable(itemReturnable);
            i.setReturnablePeriod(returnablePeriod);
            i.setIsDelete(false);
            
            
            q = em.createQuery("SELECT b from Brand b where b.brandId=:brandId");
            
             q.setParameter("brandId", brandIdItem);
           
            if(!q.getResultList().isEmpty()){
                Object obj = q.getSingleResult();
                Brand brand = (Brand) obj;
            
                i.setBrand(brand);
                 
            //em.persist(i);   
            }
            
            q = em.createQuery("SELECT i from ItemType i where i.itemTypeId=:itemTypeId");
            q.setParameter("itemTypeId", itemTypeIdItem);
            
            if(!q.getResultList().isEmpty()){
                Object obj=q.getSingleResult();
            
                ItemType itemType = (ItemType) obj;
                i.setItemType(itemType);
                 
            //em.persist(i);
               
                
            }
            
           
            em.persist(i);
   
    }


    public void removeItem(Long itemId){

        Query q = em.createQuery("SELECT i FROM Item i WHERE i.itemId = :itemId");
        q.setParameter("itemId", itemId);
        
       // boolean status = false; check status if needed
        
        if(!q.getResultList().isEmpty()){
            Object o = q.getSingleResult();
            Item i = (Item) o;
            
            i.setIsDelete(true);
            em.merge(i);
           // status = true;
            
        }
    }


    public Map<Long, String> getBrandBasicList() {
        Map<Long, String> brandList = new LinkedHashMap<Long, String>();
                
        Query q = em.createQuery("SELECT b FROM Brand b");
        List<Object> qResultList = q.getResultList();
        Object obj;
        
        for(int j=0; j<qResultList.size(); j++){
            obj = qResultList.get(j);
            Brand i = (Brand) obj;
            
            brandList.put(i.getBrandId(), i.getBrandName());
        }

        return brandList;
    }
    
       public Map<Long, String> getBrandActiveList() {
        Map<Long, String> brandList = new LinkedHashMap<Long, String>();
                
        Query q = em.createQuery("SELECT b FROM Brand b WHERE b.isDelete = false");
        List<Object> qResultList = q.getResultList();
        Object obj;
        
        for(int j=0; j<qResultList.size(); j++){
            obj = qResultList.get(j);
            Brand i = (Brand) obj;
            
            brandList.put(i.getBrandId(), i.getBrandName());
        }

        return brandList;
    }
       

   

    public Map<Long, String> getItemTypeBasicList() {
        Map<Long, String> ItemTypeList = new LinkedHashMap<Long, String>();
                
        Query q = em.createQuery("SELECT i FROM ItemType i");
        List<Object> qResultList = q.getResultList();
        Object obj;
        
        for(int j=0; j<qResultList.size(); j++){
            obj = qResultList.get(j);
            ItemType i = (ItemType) obj;
            
            ItemTypeList.put(i.getItemTypeId(), i.getItemTypeDescription());
        }

        return ItemTypeList;
    }

    public void updateBrand(Long brandId, String brandName, String brandDescription) {
         Query q = em.createQuery("SELECT b FROM Brand b WHERE b.brandId = :brandId");
         q.setParameter("brandId", brandId);
        
       // boolean status = false; check status if needed
        
        if(!q.getResultList().isEmpty()){
            Object o = q.getSingleResult();
            Brand b = (Brand) o;

            if(brandName != null){
                b.setBrandName(brandName);
                em.merge(b);
            }
            if(brandDescription != null){
                b.setBrandDescription(brandDescription);
                em.merge(b);
            }
            
        }
    }

    public void removeBrand(Long brandId) {
        
          Query q = em.createQuery("SELECT b FROM Brand b WHERE b.brandId = :brandId");
        q.setParameter("brandId", brandId);
        
       // boolean status = false; check status if needed
        
        if(!q.getResultList().isEmpty()){
            Object o = q.getSingleResult();
            Brand i = (Brand) o;
            
            i.setIsDelete(true);
            em.merge(i);
           // status = true;
            
        }
        
    }

    public void addBrand(String brandName, String brandDescription) {
          Brand b = new Brand();
            Query q = em.createQuery("SELECT max(b.brandId) FROM Brand b");
            if(!q.getResultList().isEmpty()){
                Object obj = q.getSingleResult();
                long maxBrandId = 0;
                if(obj != null){
                    maxBrandId = (Long) obj;
                    b.setBrandId(maxBrandId + 1);
                }
                else{
                    b.setBrandId(Long.valueOf(1));
                }
                System.out.println("ItemSessionBean: add Brand() --> the maxBrandId is " + maxBrandId);
            }
            
            b.setBrandName(brandName);
            b.setBrandDescription(brandDescription);
            
            
            em.persist(b);
            //return true; to check status if needed
        //}
        // return false;
        
    }

    public List<ItemType> getItemType() {
       Query q = em.createQuery("SELECT t FROM ItemType t WHERE t.isDelete= false ");
        return q.getResultList();
    }

     public void updateItemType(Long itemTypeId, String itemTypeDescription, String itemCategory, 
            String itemSubCategory, String storageType, boolean isPerishable, String measurementType) {
    
          Query q = em.createQuery("SELECT t FROM ItemType t WHERE t.itemTypeId = :itemTypeId");
        q.setParameter("itemTypeId", itemTypeId);
        
       // boolean status = false; check status if needed
        
        if(!q.getResultList().isEmpty()){
            Object o = q.getSingleResult();
            ItemType t = (ItemType) o;
            
           
            if(itemTypeDescription != null){
                t.setItemTypeDescription(itemTypeDescription);
                em.merge(t);
            }
             if(itemCategory != null){
                t.setItemCategory(itemCategory);
                em.merge(t);
            }
              if(itemSubCategory != null){
                t.setItemSubCategory(itemSubCategory);
                em.merge(t);
            }
            if(storageType != null){
                t.setStorageType(storageType);
                em.merge(t);
            }
                t.setIsPerishable(isPerishable);
                em.merge(t);

            if(measurementType != null){
                t.setMeasurementType(measurementType);
                em.merge(t);
            }

        }
        
    }

    public void removeItemType(Long itemTypeId) {
        
          Query q = em.createQuery("SELECT t FROM ItemType t WHERE t.itemTypeId = :itemTypeId");
        q.setParameter("itemTypeId", itemTypeId);
        
       // boolean status = false; check status if needed
        
        if(!q.getResultList().isEmpty()){
            Object o = q.getSingleResult();
            ItemType i = (ItemType) o;
            
            i.setIsDelete(true);
            em.merge(i);
        }
    }

    public void addItemType(String itemTypeDescription, String itemCategory,String itemSubCategory, 
                            String storageType, boolean isPerishable, String measurementType) {
        
       ItemType b = new ItemType();
            Query q = em.createQuery("SELECT max(b.itemTypeId) FROM ItemType b");
            if(!q.getResultList().isEmpty()){
                Object obj = q.getSingleResult();
                long maxItemTypeId = 0;
                if(obj != null){
                    maxItemTypeId = (Long) obj;
                    b.setItemTypeId(maxItemTypeId + 1);
                }
                else{
                    b.setItemTypeId(Long.valueOf(1));
                }
                System.out.println("ItemSessionBean: add ItemType() --> the maxItemTypeId is " + maxItemTypeId);
            }
            
            b.setItemTypeDescription(itemTypeDescription);
            b.setItemCategory(itemCategory);
            b.setItemSubCategory(itemSubCategory);
            b.setStorageType(storageType);
            b.setIsPerishable(isPerishable);
            b.setMeasurementType(measurementType);
           
            
            
            
            em.persist(b);
        
    }
    

    //CH code
    public void addDistributionCenterItem(Item i, Long distributionCenterId) {
        DistributionCenterInventory distributionCenterInventory = new DistributionCenterInventory();
        distributionCenterInventory.setDistributionCenter(em.find(DistributionCenter.class, distributionCenterId));
        distributionCenterInventory.setItemAvailableQuantity(0);
        //distributionCenterInventory.setBlockedForReturn(0);
        //distributionCenterInventory.setReservedForCustomerOrders(0);
        //distributionCenterInventory.setReservedForTransfer(0);
        distributionCenterInventory.setThresholdAlert(100);
        distributionCenterInventory.setItem(i);
        em.persist(distributionCenterInventory);
    }

 
    
    
    
}
