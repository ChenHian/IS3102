package managedbean;

import entity.Role;
import entity.Privilege;
import entity.StaffAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpSession;
import loginAuthentication.Database;
import session.stateless.RoleSessionBean;
import session.stateless.StaffAccountSessionBean;

@Named(value = "roleDataTableBean")
@RequestScoped
public class RoleDataTableBean {

    @EJB
    private RoleSessionBean roleSessionBean;
    @EJB
    private StaffAccountSessionBean staffAccountSessionBean;
    
    private Role selectedRole;
    private StaffAccount selectedStaffAccount;
    
    private List<StaffAccount> staffAccounts;
    private List<StaffAccount> filteredStaffAccounts;
    
    private List<Role> roles;
    private List<Role> filteredRoles;
    
    private String staffAccountName;
    private String staffAccount;

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setFilteredStaffAccounts(List<StaffAccount> filteredStaffAccounts) {
        this.filteredStaffAccounts = filteredStaffAccounts;
    }

    public List<StaffAccount> getFilteredStaffAccounts() {
        return filteredStaffAccounts;
    }
    
    @PostConstruct
    public void init() {
        staffAccounts = staffAccountSessionBean.getAllStaffAccounts();
        roles = roleSessionBean.getAllRoles();
        //filteredStaffAccounts = staffAccounts;
    }

    public void setStaffAccounts(List<StaffAccount> staffAccounts) {
        this.staffAccounts = staffAccounts;
    }
    
    

    public RoleDataTableBean() {
    }

    public List<Privilege> getPrivileges(){
        return roleSessionBean.getAllPrivileges();
    }
    
    public List<String> getPrivilegeNames(){
        return roleSessionBean.getAllPrivilegeNames();
    }
    
    public List<Role> getRoles() {
        return roles;
    }
    
    public List<String> getRoleNames() {
        return roleSessionBean.getAllRoleNames();
    }
    
    public List<StaffAccount> getStaffAccounts(){
        return staffAccounts;
        //return staffAccountSessionBean.getAllStaffAccounts();
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

    public Role getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Role selectedRole) {
        this.selectedRole = selectedRole;
    }

    public StaffAccount getSelectedStaffAccount() {
        return selectedStaffAccount;
    }

    public void setSelectedStaffAccount(StaffAccount selectedStaffAccount) {
        this.selectedStaffAccount = selectedStaffAccount;
    }
    
    
    public void deleteRole() {
        roleSessionBean.deleteRole(selectedRole);
        selectedRole = null;
    }
 
    public void deleteStaffAccount() {
        staffAccountSessionBean.deleteStaffAccount(selectedStaffAccount);
        selectedStaffAccount = null;
    }
        
}
