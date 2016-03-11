package warehouse_entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity

public class DistributionCenterInventory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long distributionCenterInventoryId;
    private Integer itemAvailableQuantity;
    //private Integer reservedForCustomerOrders;
    //private Integer reservedForTransfer; 
    private Integer thresholdAlert;

    @ManyToOne(cascade = {CascadeType.ALL})
    private DistributionCenter distributionCenter = new DistributionCenter();

    @ManyToOne(cascade = {CascadeType.ALL})
    private Item item = new Item();

    public DistributionCenter getDistributionCenter() {
        return distributionCenter;
    }

    public void setDistributionCenter(DistributionCenter distributionCenter) {
        this.distributionCenter = distributionCenter;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getDistributionCenterInventoryId() {
        return distributionCenterInventoryId;
    }

    public void setDistributionCenterInventoryId(Long distributionCenterInventoryId) {
        this.distributionCenterInventoryId = distributionCenterInventoryId;
    }

    public Integer getThresholdAlert() {
        return thresholdAlert;
    }

    public void setThresholdAlert(Integer thresholdAlert) {
        this.thresholdAlert = thresholdAlert;
    }

    /*
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

     */

    public Integer getItemAvailableQuantity() {
        return itemAvailableQuantity;
    }

    public void setItemAvailableQuantity(Integer itemAvailableQuantity) {
        this.itemAvailableQuantity = itemAvailableQuantity;
    }
}
