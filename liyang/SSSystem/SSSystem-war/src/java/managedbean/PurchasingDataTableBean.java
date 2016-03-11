/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.PurchaseOrder;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import session.stateless.PurchasingSessionBean;

/**
 *
 * @author QianJun
 */
@Named(value = "purchasingDataTableBean")
@RequestScoped
public class PurchasingDataTableBean {

    @EJB
    private PurchasingSessionBean purchasingSessionBean;


    //for edit and view supplier
    private PurchaseOrder selectedPurchaseOrder;
    private List<PurchaseOrder> filteredPurchaseOrder;
    private Long selectedPurchaseOrderId;
    private java.util.Date selecteddateCreated;
    private String selectedstatus;
    private String selectedDocumentReferenceType;
    private boolean selectedDocumentReferenceNumber;
    private java.util.Date selectedExpectedDeliveryDate;
    private double selectedTotalAmount;
    private String selectedDeliverToAddress;
    private String selectedDeliverToCentreName;
    private Long selectedUnitPrice;
    private String selectedQuantity;
   
    //private boolean disabled=true;
    
     public PurchasingDataTableBean() {
    }
        

      public List<PurchaseOrder> getPurchaseOrder(){
      return purchasingSessionBean.getPurchaseOrder();
    }

    /**
     * @return the selectedPurchaseOrder
     */
    public PurchaseOrder getSelectedPurchaseOrder() {
        //if(selectedPurchaseOrder!=null)
        //disabled=false;
        return selectedPurchaseOrder;
    }

    /**
     * @param selectedPurchaseOrder the selectedPurchaseOrder to set
     */
    public void setSelectedPurchaseOrder(PurchaseOrder selectedPurchaseOrder) {
        this.selectedPurchaseOrder = selectedPurchaseOrder;
    }

    /**
     * @return the filteredBPurchaseOrder
     */
    public List<PurchaseOrder> getFilteredPurchaseOrder() {
        return filteredPurchaseOrder;
    }

    /**
     * @param filteredBPurchaseOrder the filteredBPurchaseOrder to set
     */
    public void setFilteredPurchaseOrder(List<PurchaseOrder> filteredPurchaseOrder) {
        this.filteredPurchaseOrder = filteredPurchaseOrder;
    }

    /**
     * @return the selectedPurchaseOrderId
     */
    public Long getSelectedPurchaseOrderId() {
        return selectedPurchaseOrderId;
    }

    /**
     * @param selectedPurchaseOrderId the selectedPurchaseOrderId to set
     */
    public void setSelectedPurchaseOrderId(Long selectedPurchaseOrderId) {
        this.selectedPurchaseOrderId = selectedPurchaseOrderId;
    }

    /**
     * @return the selecteddateCreated
     */
    public java.util.Date getSelecteddateCreated() {
        return selecteddateCreated;
    }

    /**
     * @param selecteddateCreated the selecteddateCreated to set
     */
    public void setSelecteddateCreated(java.util.Date selecteddateCreated) {
        this.selecteddateCreated = selecteddateCreated;
    }

    /**
     * @return the selectedstatus
     */
    public String getSelectedstatus() {
        return selectedstatus;
    }

    /**
     * @param selectedstatus the selectedstatus to set
     */
    public void setSelectedstatus(String selectedstatus) {
        this.selectedstatus = selectedstatus;
    }

    /**
     * @return the selectedDocumentReferenceType
     */
    public String getSelectedDocumentReferenceType() {
        return selectedDocumentReferenceType;
    }

    /**
     * @param selectedDocumentReferenceType the selectedDocumentReferenceType to set
     */
    public void setSelectedDocumentReferenceType(String selectedDocumentReferenceType) {
        this.selectedDocumentReferenceType = selectedDocumentReferenceType;
    }

    /**
     * @return the selectedDocumentReferenceNumber
     */
    public boolean getSelectedDocumentReferenceNumber() {
        return selectedDocumentReferenceNumber;
    }

    /**
     * @param selectedDocumentReferenceNumber the selectedDocumentReferenceNumber to set
     */
    public void setSelectedDocumentReferenceNumber(boolean selectedDocumentReferenceNumber) {
        this.selectedDocumentReferenceNumber = selectedDocumentReferenceNumber;
    }

    /**
     * @return the selectedExpectedDeliveryDate
     */
    public java.util.Date getSelectedExpectedDeliveryDate() {
        return selectedExpectedDeliveryDate;
    }

    /**
     * @param selectedExpectedDeliveryDate the selectedExpectedDeliveryDate to set
     */
    public void setSelectedExpectedDeliveryDate(java.util.Date selectedExpectedDeliveryDate) {
        this.selectedExpectedDeliveryDate = selectedExpectedDeliveryDate;
    }

    /**
     * @return the selectedTotalAmount
     */
    public double getSelectedTotalAmount() {
        return selectedTotalAmount;
    }

    /**
     * @param selectedTotalAmount the selectedTotalAmount to set
     */
    public void setSelectedTotalAmount(double selectedTotalAmount) {
        this.selectedTotalAmount = selectedTotalAmount;
    }

    /**
     * @return the selectedDeliverToAddress
     */
    public String getSelectedDeliverToAddress() {
        return selectedDeliverToAddress;
    }

    /**
     * @param selectedDeliverToAddress the selectedDeliverToAddress to set
     */
    public void setSelectedDeliverToAddress(String selectedDeliverToAddress) {
        this.selectedDeliverToAddress = selectedDeliverToAddress;
    }

    /**
     * @return the selectedDeliverToCentreName
     */
    public String getSelectedDeliverToCentreName() {
        return selectedDeliverToCentreName;
    }

    /**
     * @param selectedDeliverToCentreName the selectedDeliverToCentreName to set
     */
    public void setSelectedDeliverToCentreName(String selectedDeliverToCentreName) {
        this.selectedDeliverToCentreName = selectedDeliverToCentreName;
    }

    /**
     * @return the selectedUnitPrice
     */
    public Long getSelectedUnitPrice() {
        return selectedUnitPrice;
    }

    /**
     * @param selectedUnitPrice the selectedUnitPrice to set
     */
    public void setSelectedUnitPrice(Long selectedUnitPrice) {
        this.selectedUnitPrice = selectedUnitPrice;
    }

    /**
     * @return the selectedQuantity
     */
    public String getSelectedQuantity() {
        return selectedQuantity;
    }

    /**
     * @param selectedQuantity the selectedQuantity to set
     */
    public void setSelectedQuantity(String selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }

    /**
     * @return the disabled
     */
    /*public boolean getDisabled() {
        return disabled;
    }*/

    /**
     * @param disabled the disabled to set
     */
    /*public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }*/
    
}
 