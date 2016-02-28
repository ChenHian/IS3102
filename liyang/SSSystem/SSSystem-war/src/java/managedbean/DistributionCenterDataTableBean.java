package managedbean;

import entity.Brand;
import entity.DistributionCenter;
import entity.Item;
import entity.ItemType;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import session.stateless.ItemSessionBean;
import session.stateless.WarehouseSessionBean;

/**
 *
 * @author QianJun
 */

@Named(value = "distributionCenterDataTableBean")
@RequestScoped
public class DistributionCenterDataTableBean {

    @EJB
    private WarehouseSessionBean warehouseSessionBean;
    
    private String distributionCenter;
    private String item;
   
    public DistributionCenterDataTableBean() {
    }

    public String getDistributionCenter() {
        return distributionCenter;
    }

    public void setDistributionCenter(String distributionCenter) {
        this.distributionCenter = distributionCenter;
    }
    
    public List<DistributionCenter> getAllDistributionCenters() {
        return warehouseSessionBean.getAllDistributionCenters();
    }
    
    public List<String> getAllDistributionCenterNames() {
        return warehouseSessionBean.getAllDistributionCenterNames();
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    
    public void saveNewItem() {
        warehouseSessionBean.saveNewItem(item,distributionCenter);
    }

   
}
