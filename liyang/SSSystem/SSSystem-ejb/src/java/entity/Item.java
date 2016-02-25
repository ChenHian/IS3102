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
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *
 * @author duxianqi
 */
@Entity
public class Item implements Serializable {
    @OneToMany(mappedBy = "item")
    private List<DistributionCenterInventory> distributionCenterInventorys;
    private static final long serialVersionUID = 1L;
    @Id
    private Long itemId;
    private String itemName;
    private String itemDescription;
    private boolean approvalStatus;
    private boolean itemReturnable;
    private boolean allowSubscription;
    private int returnablePeriod;
    private boolean isDelete;
    @ManyToMany(cascade={CascadeType.ALL}, mappedBy="items")
    private Set<Supplier> suppliers = new HashSet<Supplier>();
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="item")
    private Collection<QuotationItem> quotationItems = new ArrayList<QuotationItem>();
    

@ManyToOne
    private Brand brand = new Brand();

@ManyToOne
    private ItemType itemType = new ItemType();

@OneToMany(cascade = {CascadeType.ALL}, mappedBy="item")
    private Collection<PurchaseOrderItem> purchaseOrderItems = new ArrayList<PurchaseOrderItem>();
   
   
@OneToMany(cascade = {CascadeType.ALL}, mappedBy="item")
    private Collection<ContractItem> contractItem = new ArrayList<ContractItem>();


@OneToMany(cascade = {CascadeType.ALL}, mappedBy="item")
    private Collection<PurchaseRequisitionItem> purchaseRequisitionItem = new ArrayList<PurchaseRequisitionItem>();

    public Collection<PurchaseOrderItem> getPurchaseOrderItems(){
        return purchaseOrderItems;
    }
    
    public void setItems(Collection<PurchaseOrderItem> PurchaseOrderItems){
        this.setPurchaseOrderItems(purchaseOrderItems);
    }
 /**
     * @return the contract
     */
    public Collection<ContractItem> getContractItem() {
        return contractItem;
    }

    /**
     * @param contractItem
     */
    public void setContractItem(Collection<ContractItem> contractItem) {
        this.contractItem = contractItem;
    }

    
    /**
     * @return the purchaseRequisitionItem
     */
    public Collection<PurchaseRequisitionItem> getPurchaseRequisitionItem() {
        return purchaseRequisitionItem;
    }

    /**
     * @param purchaseRequisitionItem the purchaseRequisitionItem to set
     */
    public void setPurchaseRequisitionItem(Collection<PurchaseRequisitionItem> purchaseRequisitionItem) {
        this.purchaseRequisitionItem = purchaseRequisitionItem;
    }

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
    } */

    @Override
    public String toString() {
        return  "" + itemId;
    }

    /**
     * @return the suppliers
     */
    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    /**
     * @param suppliers the suppliers to set
     */
    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    /**
     * @return the quotationItems
     */
    public Collection<QuotationItem> getQuotationItems() {
        return quotationItems;
    }

    /**
     * @param quotationItems the quotationItems to set
     */
    public void setQuotationItems(Collection<QuotationItem> quotationItems) {
        this.quotationItems = quotationItems;
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
     * @param purchaseOrderItems the purchaseOrderItems to set
     */
    public void setPurchaseOrderItems(Collection<PurchaseOrderItem> purchaseOrderItems) {
        this.purchaseOrderItems = purchaseOrderItems;
    }

    /**
     * @return the isDelete
     */
    public boolean getIsDelete() {
        return isDelete;
    }

    /**
     * @param isDelete the isDelete to set
     */
    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    
    
}
