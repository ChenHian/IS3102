/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author QianJun
 */
@Entity
public class Quotation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quotationId;
    private Long requestForQuotationId;
    private String status;
    private Long approvedBy;
    private int deliveryLeadTime;
    private Date validStartDate;
    private Date validEndDate;
    private String purchaseTerms;
    private String paymentTerms;
    private String deliveryTerms;
    private String returnTerms;
    private Date dateSubmitted;
    private Long acceptedBy;
    private double price;
    @ManyToOne
    private Supplier supplier = new Supplier();
    @ManyToOne
    private Item item = new Item();
    @OneToOne(cascade={CascadeType.ALL})
    private Contract contract;

    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quotation)) {
            return false;
        }
        Quotation other = (Quotation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
*/
    @Override
    public String toString() {
        return "entity.Quotation[ id=" + getQuotationId() + " ]";
    }

    /**
     * @return the quotationId
     */
    public Long getQuotationId() {
        return quotationId;
    }

    /**
     * @param quotationId the quotationId to set
     */
    public void setQuotationId(Long quotationId) {
        this.quotationId = quotationId;
    }

    /**
     * @return the requestForQuotationId
     */
    public Long getRequestForQuotationId() {
        return requestForQuotationId;
    }

    /**
     * @param requestForQuotationId the requestForQuotationId to set
     */
    public void setRequestForQuotationId(Long requestForQuotationId) {
        this.requestForQuotationId = requestForQuotationId;
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
     * @return the approvedBy
     */
    public Long getApprovedBy() {
        return approvedBy;
    }

    /**
     * @param approvedBy the approvedBy to set
     */
    public void setApprovedBy(Long approvedBy) {
        this.approvedBy = approvedBy;
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
     * @return the acceptedBy
     */
    public Long getAcceptedBy() {
        return acceptedBy;
    }

    /**
     * @param acceptedBy the acceptedBy to set
     */
    public void setAcceptedBy(Long acceptedBy) {
        this.acceptedBy = acceptedBy;
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
     * @return the validStartDate
     */
    public Date getValidStartDate() {
        return validStartDate;
    }

    /**
     * @param validStartDate the validStartDate to set
     */
    public void setValidStartDate(Date validStartDate) {
        this.validStartDate = validStartDate;
    }

    /**
     * @return the validEndDate
     */
    public Date getValidEndDate() {
        return validEndDate;
    }

    /**
     * @param validEndDate the validEndDate to set
     */
    public void setValidEndDate(Date validEndDate) {
        this.validEndDate = validEndDate;
    }

    /**
     * @return the dateSubmitted
     */
    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    /**
     * @param dateSubmitted the dateSubmitted to set
     */
    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
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

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
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
     * @return the contract
     */
    public Contract getContract() {
        return contract;
    }

    /**
     * @param contract the contract to set
     */
    public void setContract(Contract contract) {
        this.contract = contract;
    }
    
}
