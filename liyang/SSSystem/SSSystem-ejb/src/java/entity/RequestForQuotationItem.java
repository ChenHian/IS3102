/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author QianJun
 */
@Entity
public class RequestForQuotationItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long requestForQuotationId;
    private int quantity;
    @ManyToOne(cascade={CascadeType.ALL})
    private Item item = new Item();

/*
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof requestForQuotationItem)) {
            return false;
        }
        requestForQuotationItem other = (requestForQuotationItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
*/
    @Override
    public String toString() {
        return "entity.requestForQuotationItem[ id=" + requestForQuotationId + " ]";
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
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
    
}
