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
import javax.persistence.OneToOne;

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
    private double price;
    
    private boolean isDelete;
    
    @ManyToOne
    private Supplier supplier = new Supplier();
    @ManyToOne(cascade={CascadeType.ALL})
    private Item item = new Item();
    //private Set<Item> items = new HashSet<Item>();
    @OneToOne(mappedBy="contract")
    private Quotation quotation;
            
   
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
    public boolean getAutomaticPurchase() {
        return automaticPurchase;
    }

    /**
     * @param automaticPurchase the automaticPurchase to set
     */
    public void setAutomaticPurchase(boolean automaticPurchase) {
        this.automaticPurchase = automaticPurchase;
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
     * @return the quotation
     */
    public Quotation getQuotation() {
        return quotation;
    }

    /**
     * @param quotation the quotation to set
     */
    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
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
