/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procurementManagedBean;

import entity.PurchaseOrder;
import entity.Supplier;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import session.stateless.PurchasingIssueSessionBean;

/**
 *
 * @author duxianqi
 */
@Named(value = "PurchasingIssueDataTableBean")
@RequestScoped
public class PurchasingIssueDataTableBean {
    @EJB
    private PurchasingIssueSessionBean purchasingIssueSessionBean;
    
    //for view
    private Supplier selectedSupplier;
    private List<Supplier> filteredSupplier;
    private PurchaseOrder selectedPurchaseOrder;
    private List<PurchaseOrder> filteredPurchaseOrder;
    
     public PurchasingIssueDataTableBean() {
    }

    /**
     * @return the purchasingIssueSessionBean
     */
    public PurchasingIssueSessionBean getPurchasingIssueSessionBean() {
        return purchasingIssueSessionBean;
    }

    /**
     * @param purchasingIssueSessionBean the purchasingIssueSessionBean to set
     */
    public void setPurchasingIssueSessionBean(PurchasingIssueSessionBean purchasingIssueSessionBean) {
        this.purchasingIssueSessionBean = purchasingIssueSessionBean;
    }

    /**
     * @return the selectedSupplier
     */
    public Supplier getSelectedSupplier() {
        return selectedSupplier;
    }

    /**
     * @param selectedSupplier the selectedSupplier to set
     */
    public void setSelectedSupplier(Supplier selectedSupplier) {
        this.selectedSupplier = selectedSupplier;
    }

    /**
     * @return the filteredSupplier
     */
    public List<Supplier> getFilteredSupplier() {
        return filteredSupplier;
    }

    /**
     * @param filteredSupplier the filteredSupplier to set
     */
    public void setFilteredSupplier(List<Supplier> filteredSupplier) {
        this.filteredSupplier = filteredSupplier;
    }

    /**
     * @return the selectedPurchaseOrder
     */
    public PurchaseOrder getSelectedPurchaseOrder() {
        return selectedPurchaseOrder;
    }

    /**
     * @param selectedPurchaseOrder the selectedPurchaseOrder to set
     */
    public void setSelectedPurchaseOrder(PurchaseOrder selectedPurchaseOrder) {
        this.selectedPurchaseOrder = selectedPurchaseOrder;
    }

    /**
     * @return the filteredPurchaseOrder
     */
    public List<PurchaseOrder> getFilteredPurchaseOrder() {
        return filteredPurchaseOrder;
    }

    /**
     * @param filteredPurchaseOrder the filteredPurchaseOrder to set
     */
    public void setFilteredPurchaseOrder(List<PurchaseOrder> filteredPurchaseOrder) {
        this.filteredPurchaseOrder = filteredPurchaseOrder;
    }
    
    
    
    
    
    }
