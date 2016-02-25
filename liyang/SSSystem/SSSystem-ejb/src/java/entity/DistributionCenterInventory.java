/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.Item;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WenDi
 */
@Entity

public class DistributionCenterInventory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long distributionCenterInventoryId;
    private Long distributionCenterId;
    private Item item;
    private Integer availableQuantity;
    private Integer reservedForCustomerOrders;
    private Integer reservedForTransfer; 
    private Integer blockedForReturn;
    private Integer thresholdAlert;
    

     
    public Long getDistributionCenterInventoryId() {
        return distributionCenterInventoryId;
    }

    public void setDistributionCenterInventoryId(Long distributionCenterInventoryId) {
        this.distributionCenterInventoryId = distributionCenterInventoryId;
    }

    public Long getDistributionCenterId() {
        return distributionCenterId;
    }

    public void setDistributionCenterId(Long distributionCenterId) {
        this.distributionCenterId = distributionCenterId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Integer getReservedForCustomerOrders() {
        return reservedForCustomerOrders;
    }

    public void setReservedForCustomerOrders(Integer reservedForCustomerOrders) {
        this.reservedForCustomerOrders = reservedForCustomerOrders;
    }

    public Integer getReservedForTransfer() {
        return reservedForTransfer;
    }

    public void setReservedForTransfer(Integer reservedForTransfer) {
        this.reservedForTransfer = reservedForTransfer;
    }

    public Integer getBlockedForReturn() {
        return blockedForReturn;
    }

    public void setBlockedForReturn(Integer blockedForReturn) {
        this.blockedForReturn = blockedForReturn;
    }

    public Integer getThresholdAlert() {
        return thresholdAlert;
    }

    public void setThresholdAlert(Integer thresholdAlert) {
        this.thresholdAlert = thresholdAlert;
    }
    
}