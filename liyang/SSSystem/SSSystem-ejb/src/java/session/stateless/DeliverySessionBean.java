package session.stateless;

import entity.DeliveryArrangement;
import entity.DeliveryRequest;
import entity.Item;
import entity.WarehouseFloorItem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class DeliverySessionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public List<DeliveryRequest> getAllDeliveryRequests() {
        Query query = entityManager.createQuery("SELECT d FROM DeliveryRequest d");
        return query.getResultList();
    }
    
        public List<WarehouseFloorItem> getAllWarehouseFloorItems() {
        Query query = entityManager.createQuery("SELECT w FROM WarehouseFloorItem w");
        return query.getResultList();
    }
    
        public List<Item> getAllItems() {
        Query query = entityManager.createQuery("SELECT i FROM Item i");
        return query.getResultList();
    }

    public List<DeliveryRequest> getAllDeliveryArrangements() {
        Query query = entityManager.createQuery("SELECT da FROM DeliveryArrangement da");
        return query.getResultList();
    }
    
        private DeliveryRequest getDeliveryRequest(Long deliveryRequestId) {
        DeliveryRequest deliveryRequest = entityManager.find(DeliveryRequest.class,
                deliveryRequestId);
        return deliveryRequest;
    }
        
                private WarehouseFloorItem getWarehouseFloorItem(Long warehouseFloorItemId) {
        WarehouseFloorItem warehouseFloorItem = entityManager.find(WarehouseFloorItem.class,
                warehouseFloorItemId);
        return warehouseFloorItem;
    }

    public long addNewDeliveryArrangement(String deliverFrom, String deliverTo, Date deliveryDate, long selectedDeliveryRequestId, DeliveryRequest selectedDeliveryRequest) {
        DeliveryArrangement da = new DeliveryArrangement(); 
        da.setPickupLocation(deliverFrom);
        da.setDropoffLocation(deliverTo);
        da.setDeliveryDate(deliveryDate);
        da.setFormattedDate(deliveryDate);
        da.setDeliveryRequest(getDeliveryRequest(selectedDeliveryRequestId));
        da.setIsNotDelivered("true");
        da.setStatus("Not Delivered");
        selectedDeliveryRequest.setStatus("Arranged");
        entityManager.persist(da);
        entityManager.merge(selectedDeliveryRequest);
        entityManager.flush();
        return da.getDeliveryArrangementId();
    }
    
    public String getItem1(){
        Long itemId = Long.valueOf(1);
        WarehouseFloorItem wfl = entityManager.find(WarehouseFloorItem.class, itemId);
        return wfl.getItemName();
    }
    
    public long addItem1(String item1){
        Long itemId = Long.valueOf(1);
        WarehouseFloorItem wfl = entityManager.find(WarehouseFloorItem.class, itemId);
        System.out.println(item1);
                System.out.println(wfl.getItemName());
        wfl.setItemName(item1);
        System.out.println(wfl.getItemName());
        entityManager.persist(wfl);
        entityManager.flush();
        System.out.println("add successful");
        return wfl.getWarehouseFloorItemId();
    }
    
        public String getItem2(){
        Long itemId = Long.valueOf(2);
        WarehouseFloorItem wfl = entityManager.find(WarehouseFloorItem.class, itemId);
        return wfl.getItemName();
    }
        public long addItem2(String item2){
        Long itemId = Long.valueOf(2);
        WarehouseFloorItem wfl = entityManager.find(WarehouseFloorItem.class, itemId);
        System.out.println(item2);
                System.out.println(wfl.getItemName());
        wfl.setItemName(item2);
        System.out.println(wfl.getItemName());
        entityManager.persist(wfl);
        entityManager.flush();
        System.out.println("add successful");
        return wfl.getWarehouseFloorItemId();
    }
        
            public String getItem3(){
        Long itemId = Long.valueOf(3);
        WarehouseFloorItem wfl = entityManager.find(WarehouseFloorItem.class, itemId);
        return wfl.getItemName();
    }
                public long addItem3(String item3){
        Long itemId = Long.valueOf(3);
        WarehouseFloorItem wfl = entityManager.find(WarehouseFloorItem.class, itemId);
        System.out.println(item3);
                System.out.println(wfl.getItemName());
        wfl.setItemName(item3);
        System.out.println(wfl.getItemName());
        entityManager.persist(wfl);
        entityManager.flush();
        System.out.println("add successful");
        return wfl.getWarehouseFloorItemId();
    }

    public void updateStatus(Long deliveryRequestId, String newStatus) {
        DeliveryArrangement d = entityManager.find(DeliveryArrangement.class,Long.valueOf(2252));
        d.setStatus(newStatus);
        entityManager.persist(d);
        entityManager.flush();
    }

    /**
     * * public Long addNewRole(String roleName, boolean value1, boolean value2,
     * boolean value3, boolean value4, boolean value5, boolean value6, boolean
     * value7, boolean value8, boolean value9, boolean value10, boolean value11,
     * boolean value12, boolean value13, boolean value14, boolean value15,
     * boolean value16, boolean value17, boolean value18, boolean value19,
     * boolean value20, boolean value21, boolean value22, boolean value23,
     * boolean value24, boolean value25, boolean value26, boolean value27) {
     * Role role = new Role(); role.setRoleName(roleName);
     * entityManager.persist(role); entityManager.flush(); return
     * role.getRoleId(); }
     *
     * public void deleteRole(Role seletedRole) { Role role; role =
     * entityManager.find(Role.class, seletedRole.getRoleId());
     * entityManager.remove(role); }
     *
     * public Long addNewStaffAccount(String email, String staffAccountName, int
     * contactNumber, String name) { StaffAccount staffAccount = new
     * StaffAccount(); staffAccount.setPassword("password");
     * staffAccount.setEmail(email);
     * staffAccount.setStaffAccountName(staffAccountName);
     * staffAccount.setContactNumber(contactNumber);
     * staffAccount.setRole(getRole(name).get(0));
     * entityManager.persist(staffAccount); entityManager.flush(); return
     * staffAccount.getStaffAccountId(); } public void
     * deleteStaffAccount(StaffAccount selectedStaffAccount) { StaffAccount
     * staffAccount; staffAccount = entityManager.find(StaffAccount.class,
     * selectedStaffAccount.getStaffAccountId());
     * entityManager.remove(staffAccount); } **
     */

                
                
                
}
