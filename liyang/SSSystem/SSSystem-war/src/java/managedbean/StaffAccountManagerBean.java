package managedbean;

import entity.Role;
import entity.StaffAccount;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import session.stateless.StaffAccountSessionBean;

@Named(value = "staffAccountManagerBean")
@RequestScoped
public class StaffAccountManagerBean {

    @EJB
    private StaffAccountSessionBean staffAccountSessionBean;
    private String email;
    private String emailLength;
    private String staffAccountName;
    private String password;
    private String contactNumber;
    private String statusMessage;
    private Long newStaffAccountId;
    private String role;
    private List<String> roleNames;
    private List <Role>roles =  new ArrayList<Role>();

    public StaffAccountManagerBean() {
        emailLength = "Current staff name length is less than 4.";
        //roles = staffAccountSessionBean.getAllRoles();
        //int i = 0;
        //for (i=0; i < roles.size(); i++){
            //roleNames.add(roles.get(i).getRoleName());
        //}
    }

    public StaffAccountSessionBean getStaffAccountSessionBean() {
        return staffAccountSessionBean;
    }

    public void setStaffAccountSessionBean(StaffAccountSessionBean staffAccountSessionBean) {
        this.staffAccountSessionBean = staffAccountSessionBean;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailLength() {
        return emailLength;
    }

    public void setEmailLength(String emailLength) {
        this.emailLength = emailLength;
    }

    public String getStaffAccountName() {
        return staffAccountName;
    }

    public void setStaffAccountName(String staffAccountName) {
        this.staffAccountName = staffAccountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Long getNewStaffAccountId() {
        return newStaffAccountId;
    }

    public void setNewStaffAccountId(Long newStaffAccountId) {
        this.newStaffAccountId = newStaffAccountId;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

       public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
 
    
    public void saveNewStaffAccount(ActionEvent event) {
        newStaffAccountId = staffAccountSessionBean.addNewStaffAccount(email, staffAccountName, contactNumber, role);
        statusMessage = "New Staff Account Saved Successfully";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Add New Staff Account Result: "
                + statusMessage + " (New Staff Account ID is " + newStaffAccountId + ")", ""));


    }   
}
