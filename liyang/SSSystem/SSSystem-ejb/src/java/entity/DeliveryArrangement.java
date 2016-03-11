/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Computer
 */
@Entity
public class DeliveryArrangement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long deliveryArrangementId;
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;
    
@OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private DeliveryRequest deliveryRequest;
    private String pickupLocation;
    private String dropoffLocation;
    private String formattedDate;
    private String isNotDelivered;
    private String status;

    public Long getDeliveryArrangementId() {
        return deliveryArrangementId;
    }

    public void setDeliveryArrangementId(Long deliveryArrangementId) {
        this.deliveryArrangementId = deliveryArrangementId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public DeliveryRequest getDeliveryRequest() {
        return deliveryRequest;
    }

    public void setDeliveryRequest(DeliveryRequest deliveryRequest) {
        this.deliveryRequest = deliveryRequest;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(Date deliveryDate) {
        this.formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(deliveryDate);
    }

    public String getIsNotDelivered() {
        return isNotDelivered;
    }

    public void setIsNotDelivered(String isNotDelivered) {
        this.isNotDelivered = isNotDelivered;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deliveryArrangementId!= null ? deliveryArrangementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeliveryArrangement)) {
            return false;
        }
        DeliveryArrangement other = (DeliveryArrangement) object;
        if ((this.deliveryArrangementId == null && other.deliveryArrangementId != null) || (this.deliveryArrangementId != null && !this.deliveryArrangementId.equals(other.deliveryArrangementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DeliveryArrangement[ id=" + deliveryArrangementId + " ]";
    }
    
}