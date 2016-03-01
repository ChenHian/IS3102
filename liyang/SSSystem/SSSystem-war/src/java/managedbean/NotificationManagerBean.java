package managedbean;

import entity.Notification;
import entity.Role;
import entity.Privilege;
import entity.StaffAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import loginAuthentication.Database;
import session.stateless.NotificationSessionBean;
import session.stateless.RoleSessionBean;
import session.stateless.StaffAccountSessionBean;

@Named(value = "notificationManagerBean")
@RequestScoped
public class NotificationManagerBean {

    
    @PersistenceContext(unitName = "CommonInfrastructure-ejbPU")
    private EntityManager entityManager;
    
    @EJB
    private RoleSessionBean roleSessionBean;
    @EJB
    private NotificationSessionBean notificationSessionBean;
    @EJB
    private StaffAccountSessionBean staffAccountSessionBean;
    
    private AccessController accessController;
    
    
    private String receivingRole;
    private String message;
    private String notificationType;
    
    private Role selectedRole;
    private StaffAccount selectedStaffAccount;
    
    private List<Notification> notifications;
    private List<Notification> availableNotifications;
    private List<Notification> filteredNotifications;
    private List<String> notificationTypes;
    
    
    private List<Role> roles;
    private List<Role> filteredRoles;
    
    private String staffAccountName;
    private String staffAccount;

    public List<Notification> getAvailableNotifications() {
        return availableNotifications;
    }

    public void setAvailableNotifications(List<Notification> availableNotifications) {
        this.availableNotifications = availableNotifications;
    }

    
    
    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public List<String> getNotificationTypes() {
        return notificationTypes;
    }

    public void setNotificationTypes(List<String> notificationTypes) {
        this.notificationTypes = notificationTypes;
    }

    
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    
    public String getReceivingRole() {
        return receivingRole;
    }

    public void setReceivingRole(String role) {
        this.receivingRole = role;
    }

    
    
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setFilteredNotifications(List<Notification> filteredNotifications) {
        this.filteredNotifications = filteredNotifications;
    }

    public List<Notification> getFilteredNotifications() {
        return filteredNotifications;
    }
    
    @PostConstruct
    public void init() {
        notifications = notificationSessionBean.getAllNotifications();
        availableNotifications = notificationSessionBean.getAvailableNotification(getSessionUserRole());
        notificationTypes = Arrays.asList("Low", "Normal", "Critical");
        //roles = roleSessionBean.getAllRoles();
        //filteredStaffAccounts = staffAccounts;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
    
    

    public NotificationManagerBean() {
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
    
    public List<Notification> getNotifications(){
        return notifications;
        //return staffAccountSessionBean.getAllStaffAccounts();
    }
    
    
    public void sendNotification() {
        String sendingRole = getSessionUserRole();
        System.out.println(receivingRole + " " + sendingRole + " " + notificationType + " " + message);
        notificationSessionBean.createNotification(receivingRole,sendingRole,notificationType,message);
        String statusMessage = "New Notification Sent Successfully";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Add New Notification Result: "
               + statusMessage, ""));
    }
    
    public String getSessionUserRole() {
        String user = "";
        HttpSession session = Util.getSession();
        user= (String)session.getAttribute("username");
        //System.out.println("Session user = " + user);
        Connection con = null;
        PreparedStatement ps = null;
        
        long roleid = 0;
        try {
            //System.out.println("Making connection");
            con = Database.getConnection();
            //System.out.println("Connection made: "+ con.getCatalog());
            ps = con.prepareStatement(
                    "SELECT role_roleid FROM staffaccount WHERE email= ?");
            ps.setString(1, user);
            //ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            //System.out.println("After Query");
            
            if(rs.next()) {
                //System.out.println("Result found");
                //System.out.println("accessController: Roleid getString" + rs.getString("ROLE_ROLEID"));
                roleid = Long.parseLong(rs.getString("ROLE_ROLEID"));
                return entityManager.find(Role.class, roleid).getRoleName();
            }
            else {
                System.out.println("No RS result");
            }
  
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "None found";
    }
 
   
}
