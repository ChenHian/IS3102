package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DeliveryRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long deliveryRequestId;
    private Long storeId;
    private String requestType;
    private String deliverFrom;
    private String deliverTo;
    
    
    
    private Date requestDate; 
    private String formattedDate;
    private String status;
    private String isArranged;
    
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "dr")
    private Collection<BatchOut> batchOuts = new ArrayList<BatchOut>();

    public Long getDeliveryRequestId() {
        return deliveryRequestId;
    }

    public void setDeliveryRequestId(Long deliveryRequestId) {
        this.deliveryRequestId = deliveryRequestId;
    }
    
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
    
    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
    
    public String getDeliverFrom() {
        return deliverFrom;
    }

    public void setDeliverFrom(String deliverFrom) {
        this.deliverFrom = deliverFrom;
    }
    
    public String getDeliverTo() {
        return deliverTo;
    }

    public void setDeliverTo(String deliverTo) {
        this.deliverTo = deliverTo;
    }
    
    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(Date requestDate) {
        this.formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(requestDate);
    }
    
    public String getIsArranged() {
        return isArranged;
    }

    public void setIsArranged(String isArranged) {
        this.isArranged = isArranged;
    }
  

    public Collection<BatchOut> getBatchOuts() {
        return batchOuts;
    }

    public void setBatchOuts(Collection<BatchOut> batchOuts) {
        this.batchOuts = batchOuts;
    }
    
     @Override
    public String toString() {
        return "" + deliveryRequestId + "";
    }

    /*
     @Override
     public int hashCode() {
     int hash = 0;
     hash += (id != null ? id.hashCode() : 0);
     return hash;
     }

     @Override
     public boolean equals(Object object) {
     // TODO: Warning - this method won't work in the case the id fields are not set
     if (!(object instanceof Deliveryrequest)) {
     return false;
     }
     Deliveryrequest other = (Deliveryrequest) object;
     if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
     return false;
     }
     return true;
     }

     @Override
     public String toString() {
     return "warehouse_entity.Deliveryrequest[ id=" + id + " ]";
     }
     */
    /**
     * @return the status
     */
}
