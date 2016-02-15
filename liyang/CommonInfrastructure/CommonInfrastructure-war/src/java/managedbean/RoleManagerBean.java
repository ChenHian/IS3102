package managedbean;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import session.stateless.RoleSessionBean;

@Named(value = "roleManagerBean")
@RequestScoped
public class RoleManagerBean {

    @EJB
    private RoleSessionBean roleSessionBean;
    private String roleName;
    private String roleNameLength;
    private String statusMessage;
    private Long newRoleId;
    private boolean value1;
    private boolean value2;
    private boolean value3;
    
    
    public RoleManagerBean() {
        roleNameLength = "Current event name length is less than 4.";
    }
            
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String eventName) {
        this.roleName = eventName;
    }
      public void setEventNameLength(String eventNameLength) {
        this.roleNameLength = eventNameLength;
    }

    public String getEventNameLength() {
        return roleNameLength;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Long getNewRoleId() {
        return newRoleId;
    }

    public void setNewRoleId(Long newRoleId) {
        this.newRoleId = newRoleId;
    }

     public boolean isValue1() {
        return value1;
    }
 
    public void setValue1(boolean value1) {
        this.value1 = value1;
    }
 
    public boolean isValue2() {
        return value2;
    }
 
    public void setValue2(boolean value2) {
        this.value2 = value2;
    }
    
    public boolean isValue3() {
        return value3;
    }
 
    public void setValue3(boolean value3) {
        this.value3 = value3;
    }
    
    public void saveNewRole(ActionEvent event) {
        newRoleId = roleSessionBean.addNewRole(roleName, value1, value2, value3);
        statusMessage = "New Role Saved Successfully";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Add New Role Result: "
        + statusMessage + " (New Role ID is " + newRoleId + ")", ""));
        }
    }
