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
    
    @ManyToOne
    private Supplier supplier= new Supplier();
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy="purchaseOrder")
    private Collection<PurchaseOrderItem> purchaseOrderItems = new ArrayList<PurchaseOrderItem>();
    /**
     * @return the purchaseOrderItems
     */
    public Collection<PurchaseOrderItem> getPurchaseOrderItems() {
        return purchaseOrderItems;
    }

    /**
     * @param purchaseOrderItems the purchaseOrderItems to set
     */
    public void setPurchaseOrderItems(Collection<PurchaseOrderItem> purchaseOrderItems) {
        this.purchaseOrderItems = purchaseOrderItems;
    }
    
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

    @Override
    public String toString() {
        return "entity.PurchaseOrder[ id=" + id + " ]";
    }*/

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
     * @return the documentReferenceNumber
     */
    public int getDocumentReferenceNumber() {
        return documentReferenceNumber;
    }

    /**
     * @param documentReferenceNumber the documentReferenceNumber to set
     */
    public void setDocumentReferenceNumber(int documentReferenceNumber) {
        this.documentReferenceNumber = documentReferenceNumber;
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

   
}
