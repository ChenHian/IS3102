/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author QianJun
 */
@Entity
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long notificationId;
    
    
    private String receivingRole;
    private String sendingRole;
    
    
    
    private String notificationType;
    private String notificationMessage;
    private Timestamp timestamp;

 /*   @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
*/
    @Override
    public String toString() {
        return "entity.Notification[ id=" + notificationId + " ]";
    }

    /**
     * @return the notificationId
     */
    public Long getNotificationId() {
        return notificationId;
    }

    /**
     * @param notificationId the notificationId to set
     */
    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    /**
     * @return the receivingDivision
     */
    public String getReceivingRole() {
        return receivingRole;
    }

    /**
     * @param receivingDivision the receivingDivision to set
     */
    public void setReceivingRole(String receivingRole) {
        this.receivingRole = receivingRole;
    }

    /**
     * @return the sendingDivision
     */
    public String getSendingRole() {
        return sendingRole;
    }

    /**
     * @param sendingDivision the sendingDivision to set
     */
    public void setSendingRole(String sendingRole) {
        this.sendingRole = sendingRole;
    }

    /**
     * @return the notificationType
     */
    public String getNotificationType() {
        return notificationType;
    }

    /**
     * @param notificationType the notificationType to set
     */
    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    /**
     * @return the notificationMessage
     */
    public String getNotificationMessage() {
        return notificationMessage;
    }

    /**
     * @param notificationMessage the notificationMessage to set
     */
    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    /**
     * @return the date
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * @param date the date to set
     */
    public void setTimestamp(Timestamp date) {
        this.timestamp = date;
    }
    
    

    
}
