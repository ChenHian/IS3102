/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author QianJun
 */
@Entity
public class RequestForQuotation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long requestForQuotationId;
    private Date dateRequested;
    private String status;
    private Date deadline;
    private int quantity;
    @ManyToOne
    private Item item = new Item();
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable (name="RequestForQuotation_Supplier")
    private Set<Supplier> suppliers = new HashSet<Supplier>();
    @OneToMany(cascade={CascadeType.ALL})
    private Collection<Quotation> quotations = new ArrayList<Quotation>();

  /*  @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequestForQuotation)) {
            return false;
        }
        RequestForQuotation other = (RequestForQuotation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
*/
    @Override
    public String toString() {
        return "entity.RequestForQuotation[ id=" + requestForQuotationId + " ]";
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
     * @return the dateRequested
     */
    public Date getDateRequested() {
        return dateRequested;
    }

    /**
     * @param dateRequested the dateRequested to set
     */
    public void setDateRequested(Date dateRequested) {
        this.dateRequested = dateRequested;
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
     * @return the deadline
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * @param deadline the deadline to set
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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
     * @return the quotations
     */
    public Collection<Quotation> getQuotations() {
        return quotations;
    }

    /**
     * @param quotations the quotations to set
     */
    public void setQuotations(Collection<Quotation> quotations) {
        this.quotations = quotations;
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
