package managedbean;
 
import entity.DistributionCenterInventory;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import loginAuthentication.Database;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import session.stateless.StaffAccountSessionBean;
import session.stateless.WarehouseSessionBean;

 
@ManagedBean
@SessionScoped
public class EditViewWarehouseInventory implements Serializable {

    @EJB
    private WarehouseSessionBean warehouseSessionBean;
    
    private List<DistributionCenterInventory> DCinventory;

    public List<DistributionCenterInventory> getDCinventory() {
        DCinventory = warehouseSessionBean.getDCinventory();
        return DCinventory;
    }
    
    @PostConstruct
    public void init() {
        DCinventory = warehouseSessionBean.getDCinventory();
    }
    
    
    public void onContactCellEdit(CellEditEvent event) {
        System.out.println("onCellEdit");
        Object oldValue = event.getOldValue();
        System.out.println(oldValue.toString());
        Object newValue = event.getNewValue();
        System.out.println(newValue.toString());
        String column = event.getColumn().getHeaderText();
        
        DataTable table = (DataTable) event.getSource();
        
        DistributionCenterInventory distributionCenterInventory  = (DistributionCenterInventory) table.getRowData();
        
        switch(column) {
            case "Available Quantity" : {
                distributionCenterInventory.setAvailableQuantity(Integer.parseInt(newValue.toString()));
                break;
            }
            case "Reserved for Customer Orders" : {
                if (distributionCenterInventory.getAvailableQuantity()-Integer.parseInt(newValue.toString())<0) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Note that the warehouse currently do not have enough quantity on hand to supply for customers.", null);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
                distributionCenterInventory.setReservedForCustomerOrders(Integer.parseInt(newValue.toString()));
                distributionCenterInventory.setAvailableQuantity(distributionCenterInventory.getAvailableQuantity()-(Integer.parseInt(newValue.toString()))-Integer.parseInt(oldValue.toString()));
            }
            case "Alert Threshold" : {
                distributionCenterInventory.setThresholdAlert(Integer.parseInt(newValue.toString()));
                break;
            }
            case "For Return" : {
                
                if (distributionCenterInventory.getAvailableQuantity()-Integer.parseInt(newValue.toString())<0) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "You cannot have more returns than you hava stock available, please check your entries .", null);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    break;
                }
                distributionCenterInventory.setAvailableQuantity(distributionCenterInventory.getAvailableQuantity()-(Integer.parseInt(newValue.toString()))-Integer.parseInt(oldValue.toString()));
                distributionCenterInventory.setBlockedForReturn(Integer.parseInt(newValue.toString()));
                break;
            }
            
            case "Reserved for Transfer" : {
                
                if (distributionCenterInventory.getAvailableQuantity()-Integer.parseInt(newValue.toString())<0) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Note that the warehouse currently do not have enough quantity on hand to handle all transfers.", null);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
                distributionCenterInventory.setAvailableQuantity(distributionCenterInventory.getAvailableQuantity()-(Integer.parseInt(newValue.toString()))-Integer.parseInt(oldValue.toString()));
                distributionCenterInventory.setReservedForTransfer(Integer.parseInt(newValue.toString()));
                break;
            }
                
        }
        

        warehouseSessionBean.edit(distributionCenterInventory);

    }
    
    
}
