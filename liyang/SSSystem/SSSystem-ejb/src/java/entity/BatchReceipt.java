package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class BatchReceipt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long batchReceiptId;
    private String centerName;
    private String batchNumber;
    private Date dateReceived;
    private Date expiryDate;
    private Integer quantityIn;
    private Integer quantityRejected;
    private Integer availableQuantity;

    @ManyToOne
    private PurchaseOrder po = new PurchaseOrder();

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "br")
    private Collection<BatchOut> batchOuts = new ArrayList<BatchOut>();

    public Long getBatchReceiptId() {
        return batchReceiptId;
    }

    public void setBatchReceiptId(Long batchReceiptId) {
        this.batchReceiptId = batchReceiptId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getQuantityIn() {
        return quantityIn;
    }

    public void setQuantityIn(Integer quantityIn) {
        this.quantityIn = quantityIn;
    }

    public Integer getQuantityRejected() {
        return quantityRejected;
    }

    public void setQuantityRejected(Integer quantityRejected) {
        this.quantityRejected = quantityRejected;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public PurchaseOrder getPo() {
        return po;
    }

    public void setPo(PurchaseOrder po) {
        this.po = po;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getBatchReceiptId() != null ? getBatchReceiptId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BatchReceipt)) {
            return false;
        }
        BatchReceipt other = (BatchReceipt) object;
        if ((this.getBatchReceiptId() == null && other.getBatchReceiptId() != null) || (this.getBatchReceiptId() != null && !this.batchReceiptId.equals(other.batchReceiptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "warehouse_entity.BatchReceipt[ id=" + getBatchReceiptId() + " ]";
    }

    public Collection<BatchOut> getBatchOuts() {
        return batchOuts;
    }

    public void setBatchOuts(Collection<BatchOut> batchOuts) {
        this.batchOuts = batchOuts;
    }
}
