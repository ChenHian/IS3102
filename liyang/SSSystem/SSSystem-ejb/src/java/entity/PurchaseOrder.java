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
import javax.persistence.OneToOne;

@Entity
public class PurchaseOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long purchaseOrderId;
    private Date dateCreated;
    private String status;
    private String documentReferenceType;
    private int documentReferenceNumber;
    private String expectedDeliveryDate;
    private double totalAmount;
    private String deliverToAddress;
    private String deliverToCentreName;
    private double unitPrice;
    private int quantity;
    
    @OneToOne (mappedBy="purchaseOrder")
    private PurchaseRequisition purchaseRequisition = new PurchaseRequisition();
    
    @ManyToOne
    private Item item = new Item();
    
    @ManyToOne
    private Supplier supplier= new Supplier();
    
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "po")
    private Collection<BatchReceipt> batchReceipts = new ArrayList<BatchReceipt>();
    
    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    
    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseOrder)) {
            return false;
        }
        PurchaseOrder other = (PurchaseOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PurchaseOrder[ id=" + id + " ]";
    }*/


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDocumentReferenceType() {
        return documentReferenceType;
    }

    public void setDocumentReferenceType(String documentReferenceType) {
        this.documentReferenceType = documentReferenceType;
    }

    public int getDocumentReferenceNumber() {
        return documentReferenceNumber;
    }

    public void setDocumentReferenceNumber(int documentReferenceNumber) {
        this.documentReferenceNumber = documentReferenceNumber;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(String expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDeliverToAddress() {
        return deliverToAddress;
    }

    public void setDeliverToAddress(String deliverToAddress) {
        this.deliverToAddress = deliverToAddress;
    }

    public String getDeliverToCentreName() {
        return deliverToCentreName;
    }

    public void setDeliverToCentreName(String deliverToCentreName) {
        this.deliverToCentreName = deliverToCentreName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public Collection<BatchReceipt> getBatchReceipts() {
        return batchReceipts;
    }

    public void setBatchReceipts(Collection<BatchReceipt> batchReceipts) {
        this.batchReceipts = batchReceipts;
    }
}
