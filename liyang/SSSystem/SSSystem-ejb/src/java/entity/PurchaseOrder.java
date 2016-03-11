/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author duxianqi
 */
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
    private Date expectedDeliveryDate;
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
    
     /**
     * @return the purchaseOrderId
     */
    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    /**
     * @param purchaseOrderId the purchaseOrderId to set
     */
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
*/
    @Override
    public String toString() {
        return "pid "+purchaseOrderId + "";
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

    /**
     * @return the documentReferenceType
     */
    public String getDocumentReferenceType() {
        return documentReferenceType;
    }

    /**
     * @param documentReferenceType the documentReferenceType to set
     */
    public void setDocumentReferenceType(String documentReferenceType) {
        this.documentReferenceType = documentReferenceType;
    }

    /**
     * @return the supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the totalAmount
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return the deliverToAddress
     */
    public String getDeliverToAddress() {
        return deliverToAddress;
    }

    /**
     * @param deliverToAddress the deliverToAddress to set
     */
    public void setDeliverToAddress(String deliverToAddress) {
        this.deliverToAddress = deliverToAddress;
    }

    /**
     * @return the deliverToCentreName
     */
    public String getDeliverToCentreName() {
        return deliverToCentreName;
    }

    /**
     * @param deliverToCentreName the deliverToCentreName to set
     */
    public void setDeliverToCentreName(String deliverToCentreName) {
        this.deliverToCentreName = deliverToCentreName;
    }

    /**
     * @return the unitPrice
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @return the purchaseRequisition
     */
    public PurchaseRequisition getPurchaseRequisition() {
        return purchaseRequisition;
    }

    /**
     * @param purchaseRequisition the purchaseRequisition to set
     */
    public void setPurchaseRequisition(PurchaseRequisition purchaseRequisition) {
        this.purchaseRequisition = purchaseRequisition;
    }

    /**
     * @return the expectedDeliveryDate
     */
    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    /**
     * @param expectedDeliveryDate the expectedDeliveryDate to set
     */
    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }



    /**
     * @return the batchReceipts
     */
    public Collection<BatchReceipt> getBatchReceipts() {
        return batchReceipts;
    }

    /**
     * @param batchReceipts the batchReceipts to set
     */
    public void setBatchReceipts(Collection<BatchReceipt> batchReceipts) {
        this.batchReceipts = batchReceipts;
    }
    
    


    public int getDocumentReferenceNumber() {
        return documentReferenceNumber;
    }

    public void setDocumentReferenceNumber(int documentReferenceNumber) {
        this.documentReferenceNumber = documentReferenceNumber;
    }

  

   
}
