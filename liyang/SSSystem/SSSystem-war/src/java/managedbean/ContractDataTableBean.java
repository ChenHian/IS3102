
package procurementManagedBean;


import entity.Contract;
import entity.Item;
import entity.ItemType;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import session.stateless.ContractSessionBean;
import session.stateless.ItemSessionBean;
import session.stateless.ItemTypeSessionBean;

/**
 *
 * @author duxianqi
 */
@Named(value = "contractDataTableBean")
@ManagedBean
@ViewScoped
//@RequestScoped
public class ContractDataTableBean {
    
     @EJB
    private ContractSessionBean contractSessionBean;
     @EJB
    private ItemSessionBean itemSessionBean;
     @EJB
    private ItemTypeSessionBean itemTypeSessionBean;
    

    
     private Date todayDate = new Date();

    //for edit and view contract
     private Contract selectedContract;
     private List<Contract> filteredContract;
     private Long selectedContractId;
     private java.util.Date selectedContractStartDate;
     private java.util.Date selectedContractEndDate;
     private String selectedPaymentTerms;
     private String selectedDeliveryTerms;
     private String selectedPurchaseTerms;
     private String selectedReturnTerms;
     private int selectedDeliveryLeadTime;
     private boolean selectedAutomaticPurchase;
     private double selectedPrice;
     private String selectedItem;
     private boolean disabled=true;
     
     private String selectedContractStartDateForEdit;
     private String selectedContractEndDateForEdit;
     
     // for add Contract
     private Long newContractId;
     private java.util.Date newContractStartDate;
     private java.util.Date newContractEndDate;
     private String newPaymentTerms;
     private String newDeliveryTerms;
     private String newPurchaseTerms;
     private String newReturnTerms;
     private int newDeliveryLeadTime;
     private boolean newAutomaticPurchase;
     private double newPrice;
     
     //Link Supplier and Contract
     private Long supplierIdContract;
     private Long itemm;
     private Long itemTypeId;
           
     //try try
     private String editPaymentTerms;
     
    //for item type
    //private Map<Long, String> itemTypes = new HashMap<Long, String>();
    /*private ItemType itemType;
    private Long itemTypeId;
    private String itemTypeDescription;
    
    //for item
    private Map<Long, String> items = new HashMap<Long, String>();
    private Item item;
    private Long itemId;
    private String itemName;*/
     
     
      public ContractDataTableBean() {
    }
    

        

      public List<Contract> getContract(){
      return contractSessionBean.getContract();
    }

    /**
     * @return the selectedContract
     */
    public Contract getSelectedContract() {
        if(selectedContract!=null){
            disabled=false;
            setSelectedContractId(selectedContract.getContractId());
            setSelectedContractStartDate(selectedContract.getContractStartDate());
            setSelectedContractEndDate(selectedContract.getContractEndDate());
            setSelectedPaymentTerms(selectedContract.getPaymentTerms());
            setSelectedDeliveryTerms(selectedContract.getDeliveryTerms());
            setSelectedPurchaseTerms(selectedContract.getPurchaseTerms());
            setSelectedReturnTerms(selectedContract.getReturnTerms());
            setSelectedDeliveryLeadTime(selectedContract.getDeliveryLeadTime());
            setSelectedAutomaticPurchase(selectedContract.getAutomaticPurchase());
            setSelectedPrice(selectedContract.getPrice());
            
        }
        return selectedContract;
    }

    /**
     * @param selectedContract the selectedContract to set
     */
    public void setSelectedContract(Contract selectedContract) {
        this.selectedContract = selectedContract;
    }

    /**
     * @return the filteredContract
     */
    public List<Contract> getFilteredContract() {
        return filteredContract;
    }

    /**
     * @param filteredContract the filteredContract to set
     */
    public void setFilteredContract(List<Contract> filteredContract) {
        this.filteredContract = filteredContract;
    }

    /**
     * @return the selectedContractId
     */
    public Long getSelectedContractId() {
        return selectedContractId;
    }

    /**
     * @param selectedContractId the selectedContractId to set
     */
    public void setSelectedContractId(Long selectedContractId) {
        this.selectedContractId = selectedContractId;
    }

   
    /**
     * @return the selectedPaymentTerms
     */
    public String getSelectedPaymentTerms() {
        return selectedPaymentTerms;
    }

    /**
     * @param selectedPaymentTerms the selectedPaymentTerms to set
     */
    public void setSelectedPaymentTerms(String selectedPaymentTerms) {
        this.selectedPaymentTerms = selectedPaymentTerms;
    }

    /**
     * @return the selectedDeliveryTerms
     */
    public String getSelectedDeliveryTerms() {
        return selectedDeliveryTerms;
    }

    /**
     * @param selectedDeliveryTerms the selectedDeliveryTerms to set
     */
    public void setSelectedDeliveryTerms(String selectedDeliveryTerms) {
        this.selectedDeliveryTerms = selectedDeliveryTerms;
    }

    /**
     * @return the selectedPurchaseTerms
     */
    public String getSelectedPurchaseTerms() {
        return selectedPurchaseTerms;
    }

    /**
     * @param selectedPurchaseTerms the selectedPurchaseTerms to set
     */
    public void setSelectedPurchaseTerms(String selectedPurchaseTerms) {
        this.selectedPurchaseTerms = selectedPurchaseTerms;
    }

    /**
     * @return the selectedReturnTerms
     */
    public String getSelectedReturnTerms() {
        return selectedReturnTerms;
    }

    /**
     * @param selectedReturnTerms the selectedReturnTerms to set
     */
    public void setSelectedReturnTerms(String selectedReturnTerms) {
        this.selectedReturnTerms = selectedReturnTerms;
    }

    /**
     * @return the selectedDeliveryLeadTime
     */
    public int getSelectedDeliveryLeadTime() {
        return selectedDeliveryLeadTime;
    }

    /**
     * @param selectedDeliveryLeadTime the selectedDeliveryLeadTime to set
     */
    public void setSelectedDeliveryLeadTime(int selectedDeliveryLeadTime) {
        this.selectedDeliveryLeadTime = selectedDeliveryLeadTime;
    }

    /**
     * @return the selectedAutomaticPurchase
     */
    public boolean getSelectedAutomaticPurchase() {
        return selectedAutomaticPurchase;
    }

    /**
     * @param selectedAutomaticPurchase the selectedAutomaticPurchase to set
     */
    public void setSelectedAutomaticPurchase(boolean selectedAutomaticPurchase) {
        this.selectedAutomaticPurchase = selectedAutomaticPurchase;
    }

 
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
     public void errorMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

     public void removeContractAction(ActionEvent actionEvent) {
        
        if(selectedContract!=null){
            contractSessionBean.removeContract(selectedContract.getContractId());
            addMessage("The selected contract record has been removed from the list successfully!");
        }
        else{
            errorMessage("Please select a contract to remove from the list!");
        }
    }
     
    
    /**
     * @return the disabled
     */
    public boolean getDisabled() {
        return disabled;
    }

    /**
     * @param disabled the disabled to set
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * @return the selectedPrice
     */
    public double getSelectedPrice() {
        return selectedPrice;
    }

    /**
     * @param selectedPrice the selectedPrice to set
     */
    public void setSelectedPrice(double selectedPrice) {
        this.selectedPrice = selectedPrice;
    }
 
    /**
     * @return the newContractId
     */
    public Long getNewContractId() {
        return newContractId;
    }

    /**
     * @param newContractId the newContractId to set
     */
    public void setNewContractId(Long newContractId) {
        this.newContractId = newContractId;
    }

    /**
     * @return the newPaymentTerms
     */
    public String getNewPaymentTerms() {
        return newPaymentTerms;
    }

    /**
     * @param newPaymentTerms the newPaymentTerms to set
     */
    public void setNewPaymentTerms(String newPaymentTerms) {
        this.newPaymentTerms = newPaymentTerms;
    }

    /**
     * @return the newDeliveryTerms
     */
    public String getNewDeliveryTerms() {
        return newDeliveryTerms;
    }

    /**
     * @param newDeliveryTerms the newDeliveryTerms to set
     */
    public void setNewDeliveryTerms(String newDeliveryTerms) {
        this.newDeliveryTerms = newDeliveryTerms;
    }

    /**
     * @return the newPurchaseTerms
     */
    public String getNewPurchaseTerms() {
        return newPurchaseTerms;
    }

    /**
     * @param newPurchaseTerms the newPurchaseTerms to set
     */
    public void setNewPurchaseTerms(String newPurchaseTerms) {
        this.newPurchaseTerms = newPurchaseTerms;
    }

    /**
     * @return the newReturnTerms
     */
    public String getNewReturnTerms() {
        return newReturnTerms;
    }

    /**
     * @param newReturnTerms the newReturnTerms to set
     */
    public void setNewReturnTerms(String newReturnTerms) {
        this.newReturnTerms = newReturnTerms;
    }

    /**
     * @return the newDeliveryLeadTime
     */
    public int getNewDeliveryLeadTime() {
        return newDeliveryLeadTime;
    }

    /**
     * @param newDeliveryLeadTime the newDeliveryLeadTime to set
     */
    public void setNewDeliveryLeadTime(int newDeliveryLeadTime) {
        this.newDeliveryLeadTime = newDeliveryLeadTime;
    }

    /**
     * @return the newAutomaticPurchase
     */
    public boolean getNewAutomaticPurchase() {
        return newAutomaticPurchase;
    }

    /**
     * @param newAutomaticPurchase the newAutomaticPurchase to set
     */
    public void setNewAutomaticPurchase(boolean newAutomaticPurchase) {
        this.newAutomaticPurchase = newAutomaticPurchase;
    }

    /**
     * @return the newPrice
     */
    public double getNewPrice() {
        return newPrice;
    }

    /**
     * @param newPrice the newPrice to set
     */
    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    /**
     * @return the supplierIdContract
     */
    public Long getSupplierIdContract() {
        return supplierIdContract;
    }

    /**
     * @param supplierIdContract the supplierIdContract to set
     */
    public void setSupplierIdContract(Long supplierIdContract) {
        this.supplierIdContract = supplierIdContract;
    }
    
     public void addContractAction(ActionEvent actionEvent) {
         
        
         
        contractSessionBean.addContract(newContractId,newContractStartDate, newContractEndDate,
               newPaymentTerms, newDeliveryTerms,newPurchaseTerms,newReturnTerms,newDeliveryLeadTime,
                newAutomaticPurchase, newPrice,supplierIdContract, getItemm(), getItemTypeId());
        addMessage("A new supplier record has been added successfully!");
    }
     
     

    /**
     * @return the todayDate
     */
    public Date getTodayDate() {
        return todayDate;
    }
    
    public Map<Long,String> getItemTypes(){
            return contractSessionBean.getItemTypes();
    }
    
    public Map<Long,String> getSupplierBasicList(){
            return contractSessionBean.getSupplierBasicList();
    }
    
    public Map<Long,String> getItems(){
            return contractSessionBean.getActiveItems();
    }

    /**
     * @param todayDate the todayDate to set
     */
    public void setTodayDate(Date todayDate) {
        this.todayDate = todayDate;
    }

    /**
     * @return the editPaymentTerms
     */
    public String getEditPaymentTerms() {
        return editPaymentTerms;
    }

    /**
     * @param editPaymentTerms the editPaymentTerms to set
     */
    public void setEditPaymentTerms(String editPaymentTerms) {
        this.editPaymentTerms = editPaymentTerms;
    }

   
   
     public void editContractProfileAction(ActionEvent actionEvent) {
         //System.out.println("selected Automatic purchase = " + selectedAutomaticPurchase);
         //System.out.println("selected contract id = " + selectedContractId);
         contractSessionBean.updateContract(selectedContractId,selectedAutomaticPurchase);
       /*contractSessionBean.updateContract(selectedContractId, selectedContractStartDate, selectedContractEndDate,
         selectedPaymentTerms,selectedDeliveryTerms,selectedPurchaseTerms,selectedReturnTerms,
           selectedDeliveryLeadTime,selectedAutomaticPurchase,selectedPrice);*/
        addMessage("Contract record has been updated successfully!");
    }

    /**
     * @return the selectedContractStartDate
     */
    public java.util.Date getSelectedContractStartDate() {
        return selectedContractStartDate;
    }

    /**
     * @param selectedContractStartDate the selectedContractStartDate to set
     */
    public void setSelectedContractStartDate(java.util.Date selectedContractStartDate) {
        this.selectedContractStartDate = selectedContractStartDate;
    }

    /**
     * @return the selectedContractEndDate
     */
    public java.util.Date getSelectedContractEndDate() {
        return selectedContractEndDate;
    }

    /**
     * @param selectedContractEndDate the selectedContractEndDate to set
     */
    public void setSelectedContractEndDate(java.util.Date selectedContractEndDate) {
        this.selectedContractEndDate = selectedContractEndDate;
    }

    /**
     * @return the newContractStartDate
     */
    public java.util.Date getNewContractStartDate() {
        return newContractStartDate;
    }

    /**
     * @param newContractStartDate the newContractStartDate to set
     */
    public void setNewContractStartDate(java.util.Date newContractStartDate) {
        this.newContractStartDate = newContractStartDate;
    }

    /**
     * @return the newContractEndDate
     */
    public java.util.Date getNewContractEndDate() {
        return newContractEndDate;
    }

    /**
     * @param newContractEndDate the newContractEndDate to set
     */
    public void setNewContractEndDate(java.util.Date newContractEndDate) {
        this.newContractEndDate = newContractEndDate;
    }

    /**
     * @return the selectedItem
     */
    public String getSelectedItem() {
        return selectedItem;
    }

    /**
     * @param selectedItem the selectedItem to set
     */
    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * @param itemm the itemm to set
     */
    public void setItemm(Long itemm) {
        this.itemm = itemm;
    }

    /**
     * @return the itemm
     */
    public Long getItemm() {
        return itemm;
    }

    /**
     * @return the itemTypeId
     */
    public Long getItemTypeId() {
        return itemTypeId;
    }

    /**
     * @param itemTypeId the itemTypeId to set
     */
    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }
    
    public void editContractAction(ActionEvent actionEvent){
        System.out.println("in editcontractaction: the selected contract is = " + selectedContract);
        
        System.out.println("in editcontractaction: the selected contract is = " + selectedContract.getContractStartDate());
        System.out.println("in editcontractaction: the selected contract is = " + selectedContractStartDate);
    }

    /**
     * @return the selectedContractStartDateForEdit
     */
    public String getSelectedContractStartDateForEdit() {
        return selectedContractStartDateForEdit;
    }

    /**
     * @param selectedContractStartDateForEdit the selectedContractStartDateForEdit to set
     */
    public void setSelectedContractStartDateForEdit(String selectedContractStartDateForEdit) {
        this.selectedContractStartDateForEdit = selectedContractStartDateForEdit;
    }

    /**
     * @return the selectedContractEndDateForEdit
     */
    public String getSelectedContractEndDateForEdit() {
        return selectedContractEndDateForEdit;
    }

    /**
     * @param selectedContractEndDateForEdit the selectedContractEndDateForEdit to set
     */
    public void setSelectedContractEndDateForEdit(String selectedContractEndDateForEdit) {
        this.selectedContractEndDateForEdit = selectedContractEndDateForEdit;
    }



}


    
