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
public class Contract implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contractId;
    private Date contractStartDate;
    private Date contractEndDate;
    private String paymentTerms;
    private String deliveryTerms;
    private String purchaseTerms;
    private String returnTerms;
    private int deliveryLeadTime;
    private boolean automaticPurchase;
    private int quotationIdReference;
    
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy="contract")
    private Collection<ContractItem> contractItem = new ArrayList<ContractItem>();
    @ManyToOne
    private Supplier supplier = new Supplier();
            
    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContractItem)) {
            return false;
        }
        ContractItem other = (ContractItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ContractItem[ id=" + id + " ]";
    }*/

    /**
     * @return the contractItemId
     */
    public Long getContractId() {
        return contractId;
    }

    /**
     * @param contractItemId the contractItemId to set
     */
    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    /**
     * @return the paymentTerms
     */
    public String getPaymentTerms() {
        return paymentTerms;
    }

    /**
     * @param paymentTerms the paymentTerms to set
     */
    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    /**
     * @return the deliveryTerms
     */
    public String getDeliveryTerms() {
        return deliveryTerms;
    }

    /**
     * @param deliveryTerms the deliveryTerms to set
     */
    public void setDeliveryTerms(String deliveryTerms) {
        this.deliveryTerms = deliveryTerms;
    }

    /**
     * @return the purchaseTerms
     */
    public String getPurchaseTerms() {
        return purchaseTerms;
    }

    /**
     * @param purchaseTerms the purchaseTerms to set
     */
    public void setPurchaseTerms(String purchaseTerms) {
        this.purchaseTerms = purchaseTerms;
    }

    /**
     * @return the returnTerms
     */
    public String getReturnTerms() {
        return returnTerms;
    }

    /**
     * @param returnTerms the returnTerms to set
     */
    public void setReturnTerms(String returnTerms) {
        this.returnTerms = returnTerms;
    }

    /**
     * @return the automaticPurchase
     */
    public boolean isAutomaticPurchase() {
        return automaticPurchase;
    }

    /**
     * @param automaticPurchase the automaticPurchase to set
     */
    public void setAutomaticPurchase(boolean automaticPurchase) {
        this.automaticPurchase = automaticPurchase;
    }

    /**
     * @return the quotationIdReference
     */
    public int getQuotationIdReference() {
        return quotationIdReference;
    }

    /**
     * @param quotationIdReference the quotationIdReference to set
     */
    public void setQuotationIdReference(int quotationIdReference) {
        this.quotationIdReference = quotationIdReference;
    }

    /**
     * @return the contractItem
     */
    public Collection<ContractItem> getContractItem() {
        return contractItem;
    }

    /**
     * @param contractItem the contractItem to set
     */
    public void setContractItem(Collection<ContractItem> contractItem) {
        this.contractItem = contractItem;
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
     * @return the contractStartDate
     */
    public Date getContractStartDate() {
        return contractStartDate;
    }

    /**
     * @param contractStartDate the contractStartDate to set
     */
    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    /**
     * @return the contractEndDate
     */
    public Date getContractEndDate() {
        return contractEndDate;
    }

    /**
     * @param contractEndDate the contractEndDate to set
     */
    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    /**
     * @return the deliveryLeadTime
     */
    public int getDeliveryLeadTime() {
        return deliveryLeadTime;
    }

    /**
     * @param deliveryLeadTime the deliveryLeadTime to set
     */
    public void setDeliveryLeadTime(int deliveryLeadTime) {
        this.deliveryLeadTime = deliveryLeadTime;
    }

}
