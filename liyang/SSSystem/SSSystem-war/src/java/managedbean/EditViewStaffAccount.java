package managedbean;
 
import entity.StaffAccount;
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

 
@ManagedBean
@SessionScoped
public class EditViewStaffAccount implements Serializable {

    @EJB
    private StaffAccountSessionBean staffAccountSessionBean;
    
    private StaffAccount staffAccount;

    public StaffAccount getStaffAccount() {
        return staffAccount;
    }
    
    @PostConstruct
    public void init() {
        staffAccount = this.getCurrentStaffAccount();
    }
    
    
    public StaffAccount getCurrentStaffAccount(){
        HttpSession session = Util.getSession();
        String user= (String)session.getAttribute("username");
        Connection con = null;
        PreparedStatement ps = null;
        long staffAccountId = 0;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "SELECT staffAccountId FROM staffaccount WHERE email= ?");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
            //    System.out.println(rs.getString("ROLE_ROLEID"));
                staffAccountId = Long.parseLong(rs.getString("staffAccountId"));
                //System.out.println("Privilege 7:" + entityManager.find(Role.class, roleid).isPrivilege7());
                return staffAccountSessionBean.getStaffAccount(staffAccountId);
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void onContactCellEdit(CellEditEvent event) {
        System.out.println("onCellEdit");
        Object oldValue = event.getOldValue();
        System.out.println(oldValue.toString());
        Object newValue = event.getNewValue();
        System.out.println(newValue.toString());
        
        if(Integer.parseInt(newValue.toString())<10000000) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Please enter a valid phone number.", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
        }
        
        
        
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
                staffAccountSessionBean.setContactNumber(staffAccountId, Integer.parseInt(newValue.toString()));
            }
        }
        catch (Exception ex) {ex.printStackTrace();}
    }
    
    public boolean isValidNumber() {
        return false;
        
    }
}
