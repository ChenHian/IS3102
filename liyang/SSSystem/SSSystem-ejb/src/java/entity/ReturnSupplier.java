/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author duxianqi
 */
@Entity
public class ReturnSupplier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long returnSupplierId;
    private int quantity;
    private Date returnDate;
    private String returnStatus;
    
    @ManyToOne(cascade={CascadeType.ALL})
    private Item item  = new Item();
    @ManyToOne(cascade={CascadeType.ALL})
    private Supplier supplier  = new Supplier();
    @ManyToOne(cascade={CascadeType.ALL})
    private BatchReceipt batchReceipt = new BatchReceipt();
    
    public Long getreturnSupplierId() {
        return returnSupplierId;
    }

    public void setreturnSupplierId(Long returnSupplierId) {
        this.returnSupplierId = returnSupplierId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (returnSupplierId != null ? returnSupplierId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReturnSupplier)) {
            return false;
        }
        ReturnSupplier other = (ReturnSupplier) object;
        if ((this.returnSupplierId == null && other.returnSupplierId != null) || (this.returnSupplierId != null && !this.returnSupplierId.equals(other.returnSupplierId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ReturnSupplier[ id=" + returnSupplierId + " ]";
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
     * @return the returnDate
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * @return the returnStatus
     */
    public String getReturnStatus() {
        return returnStatus;
    }

    /**
     * @param returnStatus the returnStatus to set
     */
    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
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
     * @return the batchReceipt
     */
    public BatchReceipt getBatchReceipt() {
        return batchReceipt;
    }

    /**
     * @param batchReceipt the batchReceipt to set
     */
    public void setBatchReceipt(BatchReceipt batchReceipt) {
        this.batchReceipt = batchReceipt;
    }
    
}
