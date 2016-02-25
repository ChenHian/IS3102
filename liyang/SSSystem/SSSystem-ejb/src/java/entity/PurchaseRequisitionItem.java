package entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PurchaseRequisitionItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long purchaseRequisitionItemId;
    private int quantityRequested;
    private String status;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PurchaseRequisition purchaseRequisition  = new PurchaseRequisition();
    
    @ManyToOne(cascade={CascadeType.ALL})
    private Item item = new Item();

    public Long getPurchaseRequisitionItemId() {
        return purchaseRequisitionItemId;
    }

    public void setPurchaseRequisitionItemId(Long purchaseRequisitionItemId) {
        this.purchaseRequisitionItemId = purchaseRequisitionItemId;
    }

    public int getQuantityRequested() {
        return quantityRequested;
    }


    public void setQuantityRequested(int quantityRequested) {
        this.quantityRequested = quantityRequested;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public PurchaseRequisition getPurchaseRequisition() {
        return purchaseRequisition;
    }

    public void setPurchaseRequisition(PurchaseRequisition purchaseRequisition) {
        this.purchaseRequisition = purchaseRequisition;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchaseRequisitionItemId != null ? purchaseRequisitionItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseRequisitionItem)) {
            return false;
        }
        PurchaseRequisitionItem other = (PurchaseRequisitionItem) object;
        if ((this.purchaseRequisitionItemId == null && other.purchaseRequisitionItemId != null) || (this.purchaseRequisitionItemId != null && !this.purchaseRequisitionItemId.equals(other.purchaseRequisitionItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "warehouse_entity.PurchaseRequisitionItem[ id=" + purchaseRequisitionItemId + " ]";
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
