package managedbean;

import entity.Item;
import entity.WarehouseFloorItem;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import session.stateless.DeliverySessionBean;

@Named(value = "floorPlanManagerBean")
@RequestScoped
public class FloorPlanManagerBean {
    
        @EJB
    private DeliverySessionBean deliverySessionBean;
    private long newWarehouseFloorItemId;
    
    private String item1;
    private String item2;
    private String item3;
    private String displayItem1;
    private String displayItem2;
    private String displayItem3;

    public FloorPlanManagerBean() {
    }
    
    @PostConstruct
    public void init(){
        displayItem1 = deliverySessionBean.getItem1();
        displayItem2 = deliverySessionBean.getItem2();
        displayItem3 = deliverySessionBean.getItem3();
    }
            
        public List<WarehouseFloorItem> getWarehouseFloorItems() {
        return deliverySessionBean.getAllWarehouseFloorItems();
    }
        public List<Item> getItems() {
        return deliverySessionBean.getAllItems();
    }
        
    public void addItem1(ActionEvent event) {
        System.out.println(item1);
        newWarehouseFloorItemId = deliverySessionBean.addItem1(item1);
    }
    public void addItem2(ActionEvent event) {
        System.out.println(item2);
        newWarehouseFloorItemId = deliverySessionBean.addItem2(item2);
    }
    
        public void addItem3(ActionEvent event) {
        System.out.println(item3);
        newWarehouseFloorItemId = deliverySessionBean.addItem3(item3);
    }
    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getDisplayItem1() {
        return displayItem1;
    }

    public void setDisplayItem1(String displayItem1) {
        this.displayItem1 = displayItem1;
    }

    public String getDisplayItem2() {
        return displayItem2;
    }

    public void setDisplayItem2(String displayItem2) {
        this.displayItem2 = displayItem2;
    }

    public String getDisplayItem3() {
        return displayItem3;
    }

    public void setDisplayItem3(String displayItem3) {
        this.displayItem3 = displayItem3;
    }



        
        

}
