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
        
        
        Object newValue = event.getNewValue();
        System.out.println(newValue.toString());
        Integer n = Integer.parseInt(newValue.toString());
        String column = event.getColumn().getHeaderText();
        
        DataTable table = (DataTable) event.getSource();
        
        DistributionCenterInventory distributionCenterInventory  = (DistributionCenterInventory) table.getRowData();
        Integer oldAvailable= distributionCenterInventory.getAvailableQuantity();
        switch(column) {
            case "Available Quantity" : {
                
                System.out.println(oldAvailable);
                distributionCenterInventory.setAvailableQuantity(n);
                break;
            }
            case "Reserved for Customer Orders" : {
                Integer oldRC = distributionCenterInventory.getReservedForCustomerOrders();
                System.out.println(oldRC);
                
                if (oldAvailable - n <0) {
                    
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Note that the warehouse currently do not have enough quantity on hand to supply for customers.", null);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
                distributionCenterInventory.setReservedForCustomerOrders(n);
                distributionCenterInventory.setAvailableQuantity(oldAvailable-(n-oldRC));
            }
            case "Alert Threshold" : {
                Integer oldThreshold = distributionCenterInventory.getThresholdAlert();
                System.out.println(oldThreshold);
                distributionCenterInventory.setThresholdAlert(n);
                break;
            }
            case "For Return" : {
                Integer oldReturn = distributionCenterInventory.getBlockedForReturn();
                
                if (oldAvailable - n<0) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "You cannot have more returns than you hava stock available, please check your entries .", null);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    break;
                }
                distributionCenterInventory.setAvailableQuantity(oldAvailable -(n -oldReturn));
                distributionCenterInventory.setBlockedForReturn(Integer.parseInt(newValue.toString()));
                break;
            }
            
            case "Reserved for Transfer" : {
                Integer oldTransfer = distributionCenterInventory.getReservedForTransfer();
                System.out.println(oldTransfer.toString());
                if (distributionCenterInventory.getAvailableQuantity()-Integer.parseInt(newValue.toString())<0) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Note that the warehouse currently do not have enough quantity on hand to handle all transfers.", null);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
                distributionCenterInventory.setAvailableQuantity(oldAvailable -(n-oldTransfer));
                distributionCenterInventory.setReservedForTransfer(n);
                break;
            }
                
        }
        

        warehouseSessionBean.edit(distributionCenterInventory);

    }
    
    
}
