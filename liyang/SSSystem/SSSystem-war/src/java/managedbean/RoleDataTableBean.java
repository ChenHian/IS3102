package managedbean;

import entity.Role;
import entity.Privilege;
import entity.StaffAccount;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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

    public RoleDataTableBean() {
    }

    public List<Privilege> getPrivileges(){
        return roleSessionBean.getAllPrivileges();
    }
    
    public List<String> getPrivilegeNames(){
        return roleSessionBean.getAllPrivilegeNames();
    }
    
    public List<Role> getRoles() {
        return roleSessionBean.getAllRoles();
    }
    
    public List<String> getRoleNames() {
        return roleSessionBean.getAllRoleNames();
    }
    
    public List<StaffAccount> getStaffAccounts(){
        return staffAccountSessionBean.getAllStaffAccounts();
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
