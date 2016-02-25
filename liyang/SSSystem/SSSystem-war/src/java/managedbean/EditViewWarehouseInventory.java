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
        
        
        
            HttpSession session = Util.getSession();
            String username= (String)session.getAttribute("username");
            System.out.println(username);
            Connection con = null;
        PreparedStatement ps = null;
        //System.out.print(password);
        try {
            con = Database.getConnection();
            //System.out.println("authentication "+ con.getCatalog());
            ps = con.prepareStatement(
                    "SELECT email, staffAccountId FROM staffaccount WHERE email= ?");
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { //found

                Long staffAccountId = Long.parseLong(rs.getString("staffAccountId"));
                System.out.println("newvalue = " + newValue.toString());
                //System.out.println("Privilege 7:" + entityManager.find(Role.class, roleid).isPrivilege7());
                //System.out.println(entityManager.find(StaffAccount.class, staffAccountId).getEmail());
                //staffAccountSessionBean.setContactNumber(staffAccountId, newValue.toString());
            }
        }
        catch (Exception ex) {ex.printStackTrace();}
    }
}
