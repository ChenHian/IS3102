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
}
