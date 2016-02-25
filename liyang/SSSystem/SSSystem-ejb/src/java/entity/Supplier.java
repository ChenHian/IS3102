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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author QianJun
 */
@Entity
public class Supplier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supplierId;
    private String companyName;
    private String email;
    private String address;
    private String contactPerson;
    private String contactNumber;
    private boolean isDelete;

    @OneToMany(cascade=(CascadeType.ALL))
    private Collection<FeedbackOnSupplier> feedbackOnSupplier = new ArrayList<FeedbackOnSupplier>();
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="supplier")
    private Collection<Quotation> quotations = new ArrayList<Quotation>();
    @ManyToMany(cascade={CascadeType.ALL})
    private Set<Item> items = new HashSet<Item>();
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="supplier")
    private Collection<Contract> contracts = new ArrayList<Contract>();
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="supplier")
    private Collection<PurchaseOrder> purchaseOrders = new ArrayList<PurchaseOrder>();
    
    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (supplierId != null ? supplierId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.supplierId == null && other.supplierId != null) || (this.supplierId != null && !this.supplierId.equals(other.supplierId))) {
            return false;
        }
        return true;
    }
*/
    @Override
    public String toString() {
        return "entity.Supplier[ id=" + getSupplierId() + " ]";
    }

    /**
     * @return the supplierId
     */
    public Long getSupplierId() {
        return supplierId;
    }

    /**
     * @param supplierId the supplierId to set
     */
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the contactPerson
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * @param contactPerson the contactPerson to set
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    /**
     * @return the contactNumber
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * @param contactNumber the contactNumber to set
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * @return the feedbackOnSupplier
     */
    public Collection<FeedbackOnSupplier> getFeedbackOnSupplier() {
        return feedbackOnSupplier;
    }

    /**
     * @param feedbackOnSupplier the feedbackOnSupplier to set
     */
    public void setFeedbackOnSupplier(Collection<FeedbackOnSupplier> feedbackOnSupplier) {
        this.feedbackOnSupplier = feedbackOnSupplier;
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
     * @return the items
     */
    public Set<Item> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(Set<Item> items) {
        this.items = items;
    }

    /**
     * @return the contracts
     */
    public Collection<Contract> getContracts() {
        return contracts;
    }

    /**
     * @param contracts the contracts to set
     */
    public void setContracts(Collection<Contract> contracts) {
        this.contracts = contracts;
    }

    /**
     * @return the purchaseOrders
     */
    public Collection<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    /**
     * @param purchaseOrders the purchaseOrders to set
     */
    public void setPurchaseOrders(Collection<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
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
