/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.ReturnSupplier;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import session.stateless.PurchasingIssueSessionBean;
    
/**
 *
 * @author duxianqi
 */
@Named(value = "purchasingIssueDataTableBean")
@RequestScoped
public class PurchasingIssueDataTableBean implements Serializable{
    
    @EJB
    private PurchasingIssueSessionBean purchasingIssueSessionBean;
    
    //for view
    private ReturnSupplier selectedReturnSupplier;
    private List<ReturnSupplier> filteredReturnSupplier;
    private Long selectedReturnSupplierId;
    private int selectedQuantity;
    private java.util.Date selectedReturnDate;
    private String selectedReturnStatus;
    
    public PurchasingIssueDataTableBean() {
    }

    //for view supplier
    public List<ReturnSupplier> getReturnSupplier(){
        return purchasingIssueSessionBean.getReturnSupplier();
    }

    /**
     * @return the selectedReturnSupplier
     */
    public ReturnSupplier getSelectedReturnSupplier() {
        return selectedReturnSupplier;
    }

    /**
     * @param selectedReturnSupplier the selectedReturnSupplier to set
     */
    public void setSelectedReturnSupplier(ReturnSupplier selectedReturnSupplier) {
        this.selectedReturnSupplier = selectedReturnSupplier;
    }

    /**
     * @return the filteredReturnSupplier
     */
    public List<ReturnSupplier> getFilteredReturnSupplier() {
        return filteredReturnSupplier;
    }

    /**
     * @param filteredReturnSupplier the filteredReturnSupplier to set
     */
    public void setFilteredReturnSupplier(List<ReturnSupplier> filteredReturnSupplier) {
        this.filteredReturnSupplier = filteredReturnSupplier;
    }

    /**
     * @return the selectedReturnSupplierId
     */
    public Long getSelectedReturnSupplierId() {
        return selectedReturnSupplierId;
    }

    /**
     * @param selectedReturnSupplierId the selectedReturnSupplierId to set
     */
    public void setSelectedReturnSupplierId(Long selectedReturnSupplierId) {
        this.selectedReturnSupplierId = selectedReturnSupplierId;
    }

    /**
     * @return the selectedQuantity
     */
    public int getSelectedQuantity() {
        return selectedQuantity;
    }

    /**
     * @param selectedQuantity the selectedQuantity to set
     */
    public void setSelectedQuantity(int selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }

    /**
     * @return the returnDate
     */
    public java.util.Date getSelectedReturnDate() {
        return selectedReturnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setSelectedReturnDate(java.util.Date returnDate) {
        this.selectedReturnDate = returnDate;
    }

    /**
     * @return the returnStatus
     */
    public String getSelectedReturnStatus() {
        return selectedReturnStatus;
    }

    /**
     * @param returnStatus the returnStatus to set
     */
    public void setSelectedReturnStatus(String returnStatus) {
        this.selectedReturnStatus = returnStatus;
    }
    
}