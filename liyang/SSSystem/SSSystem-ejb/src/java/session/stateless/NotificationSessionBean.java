package session.stateless;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entity.BatchReceipt;
import entity.FeedbackOnSupplier;
import entity.Item;
import entity.Notification;
import entity.PurchaseOrder;
import entity.PurchaseRequisition;
import entity.Role;
import entity.Supplier;

@Stateless
@LocalBean
public class NotificationSessionBean implements NotificationSessionBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;
    
    private Notification selectedNotification;

    public Notification getSelectedNotification() {
        return selectedNotification;
    }

    public void setSelectedNotification(Notification selectedNotification) {
        this.selectedNotification = selectedNotification;
    }
    
    
    
    
    public List<Notification> getAllNotifications() {   
        Query q = entityManager.createQuery("SELECT n FROM Notification n");
        return q.getResultList();
        
    }


    public NotificationSessionBean() {
    }

    public void createNotification(String receivingRole, String sendingRole, String notificationType, String notificationMessage) {
        Date date = new Date();
        System.out.println(date);
        
        java.sql.Timestamp dateSent = new java.sql.Timestamp(date.getTime());
        System.out.println(dateSent);
        
        Notification notification = new Notification();
        notification.setReceivingRole(receivingRole);
        notification.setSendingRole(sendingRole);
        notification.setNotificationType(notificationType);
        notification.setNotificationMessage(notificationMessage);
        notification.setTimestamp(dateSent);
        
        entityManager.persist(notification);
        entityManager.flush();
        
    }

    
    public List<Role> getRole(String name){
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.roleName LIKE :rolename").setParameter("rolename", name).getResultList();
       
    }

    public List<Notification> getAvailableNotification(String sessionUserRole) {
        if(sessionUserRole.equals("master")) {
            Query q = entityManager.createQuery("SELECT n FROM Notification n");
        return q.getResultList();    
        }
        else {
            Query q = entityManager.createQuery("SELECT n FROM Notification n WHERE n.receivingRole LIKE :sessionUserRole OR n.sendingRole LIKE :sessionUserRole");
            q.setParameter("sessionUserRole", sessionUserRole);
            return q.getResultList();
        }
    }
}
