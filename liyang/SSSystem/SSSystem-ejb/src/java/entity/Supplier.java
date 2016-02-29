package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supplierId;
    private String companyName;
    private boolean isDelete;
    
    
    @OneToMany(cascade = (CascadeType.ALL))
    private Collection<FeedbackOnSupplier> feedbackOnSupplier = new ArrayList<FeedbackOnSupplier>();

    @ManyToMany(cascade = {CascadeType.ALL})
    private Set<Item> items = new HashSet<Item>();

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "supplier")
    private Collection<PurchaseOrder> purchaseOrders = new ArrayList<PurchaseOrder>();

    @Override
    public String toString() {
        return "entity.Supplier[ id=" + getSupplierId() + " ]";
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Collection<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(Collection<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supplierId != null ? supplierId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.supplierId == null && other.supplierId != null) || (this.supplierId != null && !this.supplierId.equals(other.supplierId))) {
            return false;
        }
        return true;
    }

    public Collection<FeedbackOnSupplier> getFeedbackOnSupplier() {
        return feedbackOnSupplier;
    }

    public void setFeedbackOnSupplier(Collection<FeedbackOnSupplier> feedbackOnSupplier) {
        this.feedbackOnSupplier = feedbackOnSupplier;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the isDelete
     */
    public boolean isIsDelete() {
        return isDelete;
    }

    /**
     * @param isDelete the isDelete to set
     */
    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

}
