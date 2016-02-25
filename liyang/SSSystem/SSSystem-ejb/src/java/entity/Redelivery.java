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
public class Redelivery implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long RedeliveryId;
    private Long referenceGoodsReceiptId;
    private String receivingCentreName;
    private Date dateCreated;
    private String status;
    
    @OneToMany(cascade={CascadeType.ALL})
    private Collection<RedeliveryItem> redeliveryItem = new ArrayList<RedeliveryItem>();
    @ManyToOne(cascade={CascadeType.ALL})
    private Supplier supplier = new Supplier();
    
    /**
     * @return the redeliveryItem
     */
    public Collection<RedeliveryItem> getRedeliveryItem() {
        return redeliveryItem;
    }

    /**
     * @param redeliveryItem the redeliveryItem to set
     */
    public void setRedeliveryItem(Collection<RedeliveryItem> redeliveryItem) {
        this.redeliveryItem = redeliveryItem;
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
        if (!(object instanceof Redelivery)) {
            return false;
        }
        Redelivery other = (Redelivery) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Redelivery[ id=" + id + " ]";
    }*/

    /**
     * @return the RedeliveryId
     */
    public Long getRedeliveryId() {
        return RedeliveryId;
    }

    /**
     * @param RedeliveryId the RedeliveryId to set
     */
    public void setRedeliveryId(Long RedeliveryId) {
        this.RedeliveryId = RedeliveryId;
    }

    /**
     * @return the referenceGoodsReceiptId
     */
    public Long getReferenceGoodsReceiptId() {
        return referenceGoodsReceiptId;
    }

    /**
     * @param referenceGoodsReceiptId the referenceGoodsReceiptId to set
     */
    public void setReferenceGoodsReceiptId(Long referenceGoodsReceiptId) {
        this.referenceGoodsReceiptId = referenceGoodsReceiptId;
    }

    /**
     * @return the receivingCentreName
     */
    public String getReceivingCentreName() {
        return receivingCentreName;
    }

    /**
     * @param receivingCentreName the receivingCentreName to set
     */
    public void setReceivingCentreName(String receivingCentreName) {
        this.receivingCentreName = receivingCentreName;
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
