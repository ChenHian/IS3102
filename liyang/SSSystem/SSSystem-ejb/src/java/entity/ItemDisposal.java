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
public class ItemDisposal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemDisposalId;
    private Integer disposedQuantity;
    private Date disposalDate;
    private String reasonForDisposal;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private BatchReceipt br = new BatchReceipt(); // get batchReceiptId & ItemId

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getItemDisposalId() != null ? getItemDisposalId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemDisposal)) {
            return false;
        }
        ItemDisposal other = (ItemDisposal) object;
        if ((this.getItemDisposalId() == null && other.getItemDisposalId() != null) || (this.getItemDisposalId() != null && !this.itemDisposalId.equals(other.itemDisposalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "warehouse_entity.ItemDisposal[ itemDisposalId=" + getItemDisposalId() + " ]";
    }

    public Long getItemDisposalId() {
        return itemDisposalId;
    }

    public void setItemDisposalId(Long itemDisposalId) {
        this.itemDisposalId = itemDisposalId;
    }

    public Integer getDisposedQuantity() {
        return disposedQuantity;
    }

    public void setDisposedQuantity(Integer disposedQuantity) {
        this.disposedQuantity = disposedQuantity;
    }

    public Date getDisposalDate() {
        return disposalDate;
    }

    public void setDisposalDate(Date disposalDate) {
        this.disposalDate = disposalDate;
    }

    public String getReasonForDisposal() {
        return reasonForDisposal;
    }

    public void setReasonForDisposal(String reasonForDisposal) {
        this.reasonForDisposal = reasonForDisposal;
    }

    public BatchReceipt getBr() {
        return br;
    }

    public void setBr(BatchReceipt br) {
        this.br = br;
    }

}
