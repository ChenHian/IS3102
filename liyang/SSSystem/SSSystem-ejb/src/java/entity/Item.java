/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author duxianqi
 */
@Entity
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long itemId;
    private String itemName;
    private String itemDescription;
    private boolean approvalStatus;
    private boolean itemReturnable;
    private boolean allowSubscription;
    private boolean isDelete;
    private int returnablePeriod;
    @ManyToMany(cascade={CascadeType.ALL}, mappedBy="items")
    private Set<Supplier> suppliers = new HashSet<Supplier>();
    
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="item")
    private Collection<PurchaseRequisition> purchaseRequisition = new ArrayList<PurchaseRequisition>();

    @ManyToOne(cascade={CascadeType.ALL})
    private Brand brand = new Brand();

@ManyToOne(cascade={CascadeType.ALL})
    private ItemType itemType = new ItemType();

@OneToMany(cascade = {CascadeType.ALL}, mappedBy="item")
    private Collection<PurchaseOrder> purchaseOrder = new ArrayList<PurchaseOrder>();

@OneToMany(cascade = {CascadeType.ALL}, mappedBy="item")
    private Collection<Contract> contract = new ArrayList<Contract>();

@OneToMany(cascade={CascadeType.ALL}, mappedBy="item")
    private Collection<RequestForQuotation> requestForQuotations = new ArrayList<RequestForQuotation>();

@OneToMany(mappedBy = "item")
    private Collection<BatchReceipt> batchReceipts = new ArrayList<BatchReceipt>();



//add mapping
    @OneToMany
    private Collection<DistributionCenterInventory> distributionCenterInventory = new ArrayList<DistributionCenterInventory>();    
    
    public  Collection<DistributionCenterInventory> getDistributionCenterInventory(){
        return distributionCenterInventory;
    }   
    public void setDistributionCenterInventory(Collection<DistributionCenterInventory> distributionCenterInventory){
        this.distributionCenterInventory= distributionCenterInventory;
    }   
//

 

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    
    public Brand getBrand(){
        return brand;
    }
    
    public void setBrand(Brand brand){
        this.brand = brand;
    }
    
    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
    
    
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public boolean getApprovalStatus() {
        return isApprovalStatus();
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public boolean getItemReturnable() {
        return isItemReturnable();
    }

    public void setItemReturnable(boolean itemReturnable) {
        this.itemReturnable = itemReturnable;
    }

    public boolean getAllowSubscription() {
        return isAllowSubscription();
    }

    public void setAllowSubscription(boolean allowSubscription) {
        this.allowSubscription = allowSubscription;
    }

    public int getReturnablePeriod() {
        return returnablePeriod;
    }

    public void setReturnablePeriod(int returnablePeriod) {
        this.returnablePeriod = returnablePeriod;
    }

    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Item[ itemId=" + itemId + " ]";
    }*/

    /**
     * @return the suppliers
     */
    public Set<Supplier> getSuppliers() {
        return suppliers;
    }
    
    @Override
    public String toString() {
        return itemName;
    }

    /**
     * @param suppliers the suppliers to set
     */
    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }


    /**
     * @return the approvalStatus
     */
    public boolean isApprovalStatus() {
        return approvalStatus;
    }

    /**
     * @return the itemReturnable
     */
    public boolean isItemReturnable() {
        return itemReturnable;
    }

    /**
     * @return the allowSubscription
     */
    public boolean isAllowSubscription() {
        return allowSubscription;
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

    /**
     * @return the purchaseOrder
     */
    public Collection<PurchaseOrder> getPurchaseOrder() {
        return purchaseOrder;
    }

    /**
     * @param purchaseOrder the purchaseOrder to set
     */
    public void setPurchaseOrder(Collection<PurchaseOrder> purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    /**
     * @return the purchaseRequisition
     */
    public Collection<PurchaseRequisition> getPurchaseRequisition() {
        return purchaseRequisition;
    }

    /**
     * @param purchaseRequisition the purchaseRequisition to set
     */
    public void setPurchaseRequisition(Collection<PurchaseRequisition> purchaseRequisition) {
        this.purchaseRequisition = purchaseRequisition;
    }

    /**
     * @return the requestForQuotations
     */
    public Collection<RequestForQuotation> getRequestForQuotations() {
        return requestForQuotations;
    }

    /**
     * @param requestForQuotations the requestForQuotations to set
     */
    public void setRequestForQuotations(Collection<RequestForQuotation> requestForQuotations) {
        this.requestForQuotations = requestForQuotations;
    }

    /**
     * @return the contract
     */
    public Collection<Contract> getContract() {
        return contract;
    }

    /**
     * @param contract the contract to set
     */
    public void setContract(Collection<Contract> contract) {
        this.contract = contract;
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



    public Collection<BatchReceipt> getBatchReceipts() {
        return batchReceipts;
    }

    public void setBatchReceipts(Collection<BatchReceipt> batchReceipts) {
        this.batchReceipts = batchReceipts;
    }

    
    
}
