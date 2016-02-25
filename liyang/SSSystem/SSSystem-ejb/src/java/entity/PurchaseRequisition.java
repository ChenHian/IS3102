package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PurchaseRequisition implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long purchaseRequisitionId;
    private String centerName;
    private Date DateCreated;
    private Date dateRequest;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "purchaseRequisition")
    private List<PurchaseRequisitionItem> itemList = new ArrayList<PurchaseRequisitionItem>();

    public Long getPurchaseRequisitionId() {
        return purchaseRequisitionId;
    }

    public void setPurchaseRequisitionId(Long purchaseRequisitionId) {
        this.purchaseRequisitionId = purchaseRequisitionId;
    }

    public Date getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(Date DateCreated) {
        this.DateCreated = DateCreated;
    }

    public Date getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchaseRequisitionId != null ? purchaseRequisitionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseRequisition)) {
            return false;
        }
        PurchaseRequisition other = (PurchaseRequisition) object;
        if ((this.purchaseRequisitionId == null && other.purchaseRequisitionId != null) || (this.purchaseRequisitionId != null && !this.purchaseRequisitionId.equals(other.purchaseRequisitionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "warehouse_entity.Purchaserequisition[ purchaseRequisitionId=" + purchaseRequisitionId + " ]";
    }

    private void setID(long nanoTime) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the itemList
     */
    public List<PurchaseRequisitionItem> getItemList() {
        return itemList;
    }

    /**
     * @param itemList the itemList to set
     */
    public void setItemList(List<PurchaseRequisitionItem> itemList) {
        this.itemList = itemList;
    }

}
