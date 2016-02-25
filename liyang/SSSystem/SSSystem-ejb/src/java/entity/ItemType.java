/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author duxianqi
 */
@Entity
public class ItemType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long itemTypeId;
    private String itemTypeDescription;
    private String storageType;
    private boolean isPerishable;
    private String measurementType;
    private String itemCategory;
    private String itemSubCategory;
    private boolean isDelete;
    
@OneToMany(cascade = {CascadeType.ALL}, mappedBy="itemType")
    private Collection<Item> items = new ArrayList<Item>();
    
    public Collection<Item> getItems(){
        return items;
    }

    public Long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getItemTypeDescription() {
        return itemTypeDescription;
    }

    public void setItemTypeDescription(String itemTypeDescription) {
        this.itemTypeDescription = itemTypeDescription;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public boolean getIsPerishable() {
        return isIsPerishable();
    }

    public void setIsPerishable(boolean isPerishable) {
        this.isPerishable = isPerishable;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemSubCategory() {
        return itemSubCategory;
    }

    public void setItemSubCategory(String itemSubCategory) {
        this.itemSubCategory = itemSubCategory;
    }
    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (itemTypeId != null ? itemTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemType)) {
            return false;
        }
        ItemType other = (ItemType) object;
        if ((this.itemTypeId == null && other.itemTypeId != null) || (this.itemTypeId != null && !this.itemTypeId.equals(other.itemTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ItemType[ itemTypeId=" + itemTypeId + " ]";
    }*/

    /**
     * @return the isPerishable
     */
    public boolean isIsPerishable() {
        return isPerishable;
    }

    /**
     * @param items the items to set
     */
    public void setItems(Collection<Item> items) {
        this.items = items;
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
