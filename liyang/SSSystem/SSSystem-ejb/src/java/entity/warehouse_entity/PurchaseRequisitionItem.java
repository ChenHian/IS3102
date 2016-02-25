/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.warehouse_entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yingweiliu
 */
@Entity
@Table(name = "purchaserequsitionitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Purchaserequsitionitem.findAll", query = "SELECT p FROM Purchaserequsitionitem p"),
    @NamedQuery(name = "Purchaserequsitionitem.findByPurchaseRequisitionId", query = "SELECT p FROM Purchaserequsitionitem p WHERE p.purchaseRequisitionId = :purchaseRequisitionId"),
    @NamedQuery(name = "Purchaserequsitionitem.findByItemId", query = "SELECT p FROM Purchaserequsitionitem p WHERE p.itemId = :itemId"),
    @NamedQuery(name = "Purchaserequsitionitem.findByQuantity", query = "SELECT p FROM Purchaserequsitionitem p WHERE p.quantity = :quantity")})
public class PurchaseRequisitionItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "purchaseRequisitionId")
    private Integer purchaseRequisitionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "itemId")
    private int itemId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "quantity")
    private Integer quantity;

    public PurchaseRequisitionItem() {
    }

    public PurchaseRequisitionItem(Integer purchaseRequisitionId) {
        this.purchaseRequisitionId = purchaseRequisitionId;
    }

    public PurchaseRequisitionItem(Integer purchaseRequisitionId, int itemId, Integer quantity) {
        this.purchaseRequisitionId = purchaseRequisitionId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Integer getPurchaseRequisitionId() {
        return purchaseRequisitionId;
    }

    public void setPurchaseRequisitionId(Integer purchaseRequisitionId) {
        this.purchaseRequisitionId = purchaseRequisitionId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchaseRequisitionId != null ? purchaseRequisitionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseRequisitionItem)) {
            return false;
        }
        PurchaseRequisitionItem other = (PurchaseRequisitionItem) object;
        if ((this.purchaseRequisitionId == null && other.purchaseRequisitionId != null) || (this.purchaseRequisitionId != null && !this.purchaseRequisitionId.equals(other.purchaseRequisitionId))) {
            return false;
        }
        return true;
    }


}
