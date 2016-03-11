/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Computer
 */
@Entity
public class WarehouseFloorItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long warehouseFloorItemId;
    private String itemName;
    private String position;

    public Long getWarehouseFloorItemId() {
        return warehouseFloorItemId;
    }

    public void setWarehouseFloorItemId(Long warehouseFloorItemId) {
        this.warehouseFloorItemId = warehouseFloorItemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (warehouseFloorItemId != null ? warehouseFloorItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WarehouseFloorItem)) {
            return false;
        }
        WarehouseFloorItem other = (WarehouseFloorItem) object;
        if ((this.warehouseFloorItemId == null && other.warehouseFloorItemId != null) || (this.warehouseFloorItemId != null && !this.warehouseFloorItemId.equals(other.warehouseFloorItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.WarehouseFloorItem[ id=" + warehouseFloorItemId + " ]";
    }
    
}
