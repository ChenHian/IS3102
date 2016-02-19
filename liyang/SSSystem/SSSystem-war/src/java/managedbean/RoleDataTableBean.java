package managedbean;

import entity.Role;
import entity.Privilege;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import session.stateless.RoleSessionBean;

@Named(value = "roleDataTableBean")
@RequestScoped
public class RoleDataTableBean {

    @EJB
    private RoleSessionBean roleSessionBean;
    private Role selectedRole;

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

    public Role getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Role selectedRole) {
        this.selectedRole = selectedRole;
    }
    
        public void deleteRole() {
        roleSessionBean.deleteRole(selectedRole);
        selectedRole = null;
    }
}
