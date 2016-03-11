/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Item;
import entity.PurchaseRequisition;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import session.stateless.PurchaseOrderSessionBean;
import session.stateless.PurchaseRequisitionSessionBean;

/**
 *
 * @author QianJun
 */
@Named(value = "purchaseRequisitionDataTableBean")
@RequestScoped
public class PurchaseRequisitionDataTableBean{
    @EJB
    private PurchaseRequisitionSessionBean purchaseRequisitionSessionBean;
    
    @EJB
    private PurchaseOrderSessionBean purchaseOrderSessionBean;
    
   // private long selectedItemId;
    private List<PurchaseRequisition> selectedPRItems;
    private PurchaseRequisition selectedPurchaseRequisitionW; 
    private PurchaseRequisition selectedPurchaseRequisitionWO; 
    private List<PurchaseRequisition> filteredPurchaseRequisitionW;
    private List<PurchaseRequisition> filteredPurchaseRequisitionWO;
    private List<Item> itemWO;
    
    
    public PurchaseRequisitionDataTableBean() {
    }
    
    public List<PurchaseRequisition> getPurchaseRequisitionWith(){
         //  System.out.println("DataTableBean: getPurchaseRequisitionItems() --> selectedItemId = " + selectedItemId);
        return purchaseRequisitionSessionBean.getPurchaseRequisitionWith();
    }
    
    public List<PurchaseRequisition> getPurchaseRequisitionWithout(){
         //  System.out.println("DataTableBean: getPurchaseRequisitionItems() --> selectedItemId = " + selectedItemId);
        List<PurchaseRequisition>  PRWithout = purchaseRequisitionSessionBean.getPurchaseRequisitionWithout();
        
        itemWO = purchaseRequisitionSessionBean.getItemWOList();
        
        return PRWithout;
    }
    
    
    // to remove
 /*   public List<Purchaserequisitionitem> getSelectedItem(){
        return purchaseRequisitionSessionBean.getSelectedItem(Long.valueOf(1));
    }
    
    public int getTotalQuantity(){
        return purchaseRequisitionSessionBean.getTotalQuantity(1);
    }
    
    public Map<Long,String> getItemBasicList(){
            return purchaseRequisitionSessionBean.getItemBasicList();
    }
*/
    /**
     * @return the selectedItemId
     */
 //   public long getSelectedItemId() {
   //     return selectedItemId;
   // }

    /**
     * @param selectedItemId the selectedItemId to set
     */
  //  public void setSelectedItemId(long selectedItemId) {
  //      this.selectedItemId = selectedItemId;
  //  }
  /*          
    public void getUpdatePurchaseRequisitionStatusAction(ActionEvent actionEvent) {

        if(selectedPRItems != null){
            System.out.println("DataTableBean:  getUpdatePRStatusAction: selectedPRItems.size = " + selectedPRItems.size());
          //  purchaseRequisitionSessionBean.updatePurchaseRequisitionStatusAction(selectedPRItems);
        }
        else{
            errorMessage("Please select an item first!");
        }
    }
   */ 
    public void errorMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
        public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
        
    /**
     * @return the selectedPRItems
     */
    public List<PurchaseRequisition> getSelectedPRItems() {
        return selectedPRItems;
    }

    /**
     * @param selectedPRItems the selectedPRItems to set
     */
    public void setSelectedPRItems(List<PurchaseRequisition> selectedPRItems) {
        this.selectedPRItems = selectedPRItems;
    }

    /**
     * @return the filteredPurchaseRequisitionWO
     */
    public List<PurchaseRequisition> getFilteredPurchaseRequisitionWO() {
        return filteredPurchaseRequisitionWO;
    }

    /**
     * @param filteredPurchaseRequisitionWO the filteredPurchaseRequisitionWO to set
     */
    public void setFilteredPurchaseRequisitionWO(List<PurchaseRequisition> filteredPurchaseRequisitionWO) {
        this.filteredPurchaseRequisitionWO = filteredPurchaseRequisitionWO;
    }

    /**
     * @return the selectedPurchaseRequisitionWO
     */
    public PurchaseRequisition getSelectedPurchaseRequisitionWO() {
        return selectedPurchaseRequisitionWO;
    }

    /**
     * @param selectedPurchaseRequisitionWO the selectedPurchaseRequisitionWO to set
     */
    public void setSelectedPurchaseRequisitionWO(PurchaseRequisition selectedPurchaseRequisitionWO) {
        this.selectedPurchaseRequisitionWO = selectedPurchaseRequisitionWO;
    }

    /**
     * @return the selectedPurchaseRequisitionW
     */
    public PurchaseRequisition getSelectedPurchaseRequisitionW() {
        return selectedPurchaseRequisitionW;
    }

    /**
     * @param selectedPurchaseRequisitionW the selectedPurchaseRequisitionW to set
     */
    public void setSelectedPurchaseRequisitionW(PurchaseRequisition selectedPurchaseRequisitionW) {
        this.selectedPurchaseRequisitionW = selectedPurchaseRequisitionW;
    }

    /**
     * @return the filteredPurchaseRequisitionW
     */
    public List<PurchaseRequisition> getFilteredPurchaseRequisitionW() {
        return filteredPurchaseRequisitionW;
    }

    /**
     * @param filteredPurchaseRequisitionW the filteredPurchaseRequisitionW to set
     */
    public void setFilteredPurchaseRequisitionW(List<PurchaseRequisition> filteredPurchaseRequisitionW) {
        this.filteredPurchaseRequisitionW = filteredPurchaseRequisitionW;
    }

    /**
     * @return the itemWO
     */
    public List<Item> getItemWO() {
        return itemWO;
    }

    /**
     * @param itemWO the itemWO to set
     */
    public void setItemWO(List<Item> itemWO) {
        this.itemWO = itemWO;
    }

    
}
