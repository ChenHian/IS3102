package warehouse_entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;
    private String itemName;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "item")
    private Collection<PurchaseRequisition> purchaseRequisitionItems = new ArrayList<PurchaseRequisition>();

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "item")
    private Collection<DistributionCenterInventory> distributionCenterInventorys = new ArrayList<DistributionCenterInventory>();

    @OneToMany(mappedBy = "item")
    private Collection<BatchReceipt> batchReceipts = new ArrayList<BatchReceipt>();

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Collection<PurchaseRequisition> getPurchaseRequisitionItems() {
        return purchaseRequisitionItems;
    }

    public void setPurchaseRequisitionItems(Collection<PurchaseRequisition> purchaseRequisitionItems) {
        this.purchaseRequisitionItems = purchaseRequisitionItems;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getItemId() != null ? getItemId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.getItemId() == null && other.getItemId() != null) || (this.getItemId() != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "warehouse_entity.Item[ id=" + getItemId() + " ]";
    }

    public Collection<DistributionCenterInventory> getDistributionCenterInventorys() {
        return distributionCenterInventorys;
    }

    public void setDistributionCenterInventorys(Collection<DistributionCenterInventory> distributionCenterInventorys) {
        this.distributionCenterInventorys = distributionCenterInventorys;
    }

    public Collection<BatchReceipt> getBatchReceipts() {
        return batchReceipts;
    }

    public void setBatchReceipts(Collection<BatchReceipt> batchReceipts) {
        this.batchReceipts = batchReceipts;
    }
}
