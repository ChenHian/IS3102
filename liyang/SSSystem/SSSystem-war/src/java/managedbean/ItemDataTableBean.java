package managedbean;

import entity.Brand;
import entity.Item;
import entity.ItemType;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import session.stateless.ItemSessionBean;

/**
 *
 * @author QianJun
 */

@Named(value = "itemDataTableBean")
@RequestScoped
public class ItemDataTableBean {

    @EJB
    private ItemSessionBean itemSessionBean;

    //for edit and view supplier
    private Item selectedItem;
    private Brand selectedBrand;
    private ItemType selectedItemType;
    private List<Item> filteredItems;
    private List<Brand> filteredBrand;
    private List<ItemType> filteredItemType;
    private Long selectedItemId;
    private String selectedItemName;
    private String selectedItemDescription;
    private boolean selectedApprovalStatus;
    private boolean selectedItemReturnable;
    private boolean selectedAllowSubscription;
    private int selectedReturnablePeriod;
    private Long selectedBrandId;
    private String selectedBrandName;
    private String selectedBrandDescription;
    private Long selectedItemTypeId;
    private String selectedItemTypeDescription;
    private String selectedStorageType;
    private boolean selectedIsPerishable;
    private String selectedMeasurementType;
    private String selectedItemCategory;
    private String selectedItemSubCategory;

    //for add Item
    private Long newItemId;
    private String newItemName;
    private String newItemDescription;
    private boolean newApprovalStatus;
    private boolean newItemReturnable;
    private boolean newAllowSubscription;
    private int newReturnablePeriod;
    private Long newBrandId;
    private String newBrandName;
    private String newBrandDescription;
    private Long newItemTypeId;
    private String newItemTypeDescription;
    private String newStorageType;
    private boolean newIsPerishable;
    private String newMeasurementType;
    private String newItemCategory;
    private String newItemSubCategory;
    
    //Link to brandId, itemTypeId
    private Long brandIdItem;
    private Long itemTypeIdItem;

    public ItemDataTableBean() {
    }

    //for view supplier
    public List<Item> getItems(){
        return itemSessionBean.getItems();
    }
    
    public List<Brand> getBrand(){
        return itemSessionBean.getBrand();
    }
    
    public List<ItemType> getItemType(){
        return itemSessionBean.getItemType();
    }
    
    /**
     * @return the selectedItem
     */
    public Item getSelectedItem() {
        return selectedItem;
    }

    /**
     * @param selectedItem the selectedItem to set
     */
    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * @return the filteredItems
     */
    public List<Item> getFilteredItems() {
        return filteredItems;
    }

    /**
     * @param filteredItems the filteredItems to set
     */
    public void setFilteredItems(List<Item> filteredItems) {
        this.filteredItems = filteredItems;
    }

    /**
     * @return the selectedItemId
     */
    public Long getSelectedItemId() {
        return selectedItemId;
    }

    /**
     * @param selectedItemId the selectedItemId to set
     */
    public void setSelectedItemId(Long selectedItemId) {
        this.selectedItemId = selectedItemId;
    }

    /**
     * @return the selectedItemName
     */
    public String getSelectedItemName() {
        return selectedItemName;
    }

    /**
     * @param selectedItemName the selectedItemName to set
     */
    public void setSelectedItemName(String selectedItemName) {
        this.selectedItemName = selectedItemName;
    }

    /**
     * @return the selectedItemDescription
     */
    public String getSelectedItemDescription() {
        return selectedItemDescription;
    }

    /**
     * @param selectedItemDescription the selectedItemDescription to set
     */
    public void setSelectedItemDescription(String selectedItemDescription) {
        this.selectedItemDescription = selectedItemDescription;
    }

    /**
     * @return the selectedApprovalStatus
     */
    public boolean getSelectedApprovalStatus() {
        return selectedApprovalStatus;
    }

    /**
     * @param selectedApprovalStatus the selectedApprovalStatus to set
     */
    public void setSelectedApprovalStatus(boolean selectedApprovalStatus) {
        this.selectedApprovalStatus = selectedApprovalStatus;
    }

    /**
     * @return the selectedItemReturnable
     */
    public boolean getSelectedItemReturnable() {
        return selectedItemReturnable;
    }

    /**
     * @param selectedItemReturnable the selectedItemReturnable to set
     */
    public void setSelectedItemReturnable(boolean selectedItemReturnable) {
        this.selectedItemReturnable = selectedItemReturnable;
    }

    /**
     * @return the selectedAllowSubscription
     */
    public boolean getSelectedAllowSubscription() {
        return selectedAllowSubscription;
    }

    /**
     * @param selectedAllowSubscription the selectedAllowSubscription to set
     */
    public void setSelectedAllowSubscription(boolean selectedAllowSubscription) {
        this.selectedAllowSubscription = selectedAllowSubscription;
    }

    /**
     * @return the selectedReturnablePeriod
     */
    public int getSelectedReturnablePeriod() {
        return selectedReturnablePeriod;
    }

    /**
     * @param selectedReturnablePeriod the selectedReturnablePeriod to set
     */
    public void setSelectedReturnablePeriod(int selectedReturnablePeriod) {
        this.selectedReturnablePeriod = selectedReturnablePeriod;
    }

    /**
     * @return the selectedBrandId
     */
    public Long getSelectedBrandId() {
        return selectedBrandId;
    }

    /**
     * @param selectedBrandId the selectedBrandId to set
     */
    public void setSelectedBrandId(Long selectedBrandId) {
        this.selectedBrandId = selectedBrandId;
    }

    /**
     * @return the selectedBrandName
     */
    public String getSelectedBrandName() {
        return selectedBrandName;
    }

    /**
     * @param selectedBrandName the selectedBrandName to set
     */
    public void setSelectedBrandName(String selectedBrandName) {
        this.selectedBrandName = selectedBrandName;
    }

    /**
     * @return the selectedBrandDescription
     */
    public String getSelectedBrandDescription() {
        return selectedBrandDescription;
    }

    /**
     * @param selectedBrandDescription the selectedBrandDescription to set
     */
    public void setSelectedBrandDescription(String selectedBrandDescription) {
        this.selectedBrandDescription = selectedBrandDescription;
    }

    /**
     * @return the selectedItemTypeId
     */
    public Long getSelectedItemTypeId() {
        return selectedItemTypeId;
    }

    /**
     * @param selectedItemTypeId the selectedItemTypeId to set
     */
    public void setSelectedItemTypeId(Long selectedItemTypeId) {
        this.selectedItemTypeId = selectedItemTypeId;
    }

    /**
     * @return the selectedItemTypeDescription
     */
    public String getSelectedItemTypeDescription() {
        return selectedItemTypeDescription;
    }

    /**
     * @param selectedItemTypeDescription the selectedItemTypeDescription to set
     */
    public void setSelectedItemTypeDescription(String selectedItemTypeDescription) {
        this.selectedItemTypeDescription = selectedItemTypeDescription;
    }

    /**
     * @return the selectedStorageType
     */
    public String getSelectedStorageType() {
        return selectedStorageType;
    }

    /**
     * @param selectedStorageType the selectedStorageType to set
     */
    public void setSelectedStorageType(String selectedStorageType) {
        this.selectedStorageType = selectedStorageType;
    }

    /**
     * @return the selectedIsPerishable
     */
    public boolean isSelectedIsPerishable() {
        return selectedIsPerishable;
    }

    /**
     * @param selectedIsPerishable the selectedIsPerishable to set
     */
    public void setSelectedIsPerishable(boolean selectedIsPerishable) {
        this.selectedIsPerishable = selectedIsPerishable;
    }

    /**
     * @return the selectedMeasurementType
     */
    public String getSelectedMeasurementType() {
        return selectedMeasurementType;
    }

    /**
     * @param selectedMeasurementType the selectedMeasurementType to set
     */
    public void setSelectedMeasurementType(String selectedMeasurementType) {
        this.selectedMeasurementType = selectedMeasurementType;
    }

    /**
     * @return the selectedItemCategory
     */
    public String getSelectedItemCategory() {
        return selectedItemCategory;
    }

    /**
     * @param selectedItemCategory the selectedItemCategory to set
     */
    public void setSelectedItemCategory(String selectedItemCategory) {
        this.selectedItemCategory = selectedItemCategory;
    }

    /**
     * @return the selectedItemSubCategory
     */
    public String getSelectedItemSubCategory() {
        return selectedItemSubCategory;
    }

    /**
     * @param selectedItemSubCategory the selectedItemSubCategory to set
     */
    public void setSelectedItemSubCategory(String selectedItemSubCategory) {
        this.selectedItemSubCategory = selectedItemSubCategory;
    }

    /**
     *
     * @param actionEvent
     */
    public void editItemProfileAction(ActionEvent actionEvent) {

        itemSessionBean.updateItem(
                selectedItemId, selectedItemName, selectedItemDescription,
                selectedItemReturnable,selectedReturnablePeriod);
        addMessage("Item record has been updated successfully!");
    }
    
     public void editBrandProfileAction(ActionEvent actionEvent) {

        itemSessionBean.updateBrand(
                selectedBrandId, selectedBrandName, selectedBrandDescription);
        addMessage("Brand record has been updated successfully!");
    }
     
       public void editItemTypeProfileAction(ActionEvent actionEvent) {

        itemSessionBean.updateItemType(
                selectedItemTypeId, selectedItemTypeDescription, selectedItemCategory, selectedItemSubCategory,
                selectedStorageType, selectedIsPerishable,selectedMeasurementType);
        addMessage("Brand record has been updated successfully!");
    }
    
    
     public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    
    public void addItemAction(ActionEvent actionEvent) {
        //System.out.println("addItemAction: " + newItemReturnable);
        itemSessionBean.addItem(newItemName,newItemDescription,
                newItemReturnable,newReturnablePeriod, brandIdItem,itemTypeIdItem);
        addMessage("A new supplier record has been added successfully!");
    }

     public void addBrandAction(ActionEvent actionEvent) {
        //System.out.println("addItemAction: " + newItemReturnable);
        itemSessionBean.addBrand(newBrandName,newBrandDescription);
        addMessage("A new supplier record has been added successfully!");
    }
     
     public void addItemTypeAction(ActionEvent actionEvent) {
        //System.out.println("addItemAction: " + newItemReturnable);
        itemSessionBean.addItemType(newItemTypeDescription, newItemCategory, newItemSubCategory,
                                    newStorageType, newIsPerishable,newMeasurementType);
        addMessage("A new supplier record has been added successfully!");
    }
     
    public void removeItemAction(ActionEvent actionEvent) {
        
        if(selectedItem!=null){
            itemSessionBean.removeItem(selectedItem.getItemId());
            addMessage("The selected supplier record has been removed from the list successfully!");
        }
        else{
            errorMessage("Please select a supplier to remove from the list!");
        }
    }
    
     public void removeBrandAction(ActionEvent actionEvent) {
        
        if(selectedBrand!=null){
            itemSessionBean.removeBrand(selectedBrand.getBrandId());
            addMessage("The selected supplier record has been removed from the list successfully!");
        }
        else{
            errorMessage("Please select a supplier to remove from the list!");
        }
    }
     
      public void removeItemTypeAction(ActionEvent actionEvent) {
        
        if(selectedItemType!=null){
            itemSessionBean.removeItemType(selectedItemType.getItemTypeId());
            addMessage("The selected supplier record has been removed from the list successfully!");
        }
        else{
            errorMessage("Please select a supplier to remove from the list!");
        }
    }
     
    public void errorMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


   
    public Map<Long,String> getBrandBasicList(){
            return itemSessionBean.getBrandBasicList();
    }
    
    
    public Map<Long,String> getItemTypeBasicList(){
            return itemSessionBean.getItemTypeBasicList();
    }
    
    
    
    
   
   
    /**
     * @return the newItemId
     */
    public Long getNewItemId() {
        return newItemId;
    }

    /**
     * @param newItemId the newItemId to set
     */
    public void setNewItemId(Long newItemId) {
        this.newItemId = newItemId;
    }

    /**
     * @return the newItemName
     */
    public String getNewItemName() {
        return newItemName;
    }

    /**
     * @param newItemName the newItemName to set
     */
    public void setNewItemName(String newItemName) {
        this.newItemName = newItemName;
    }

    /**
     * @return the newItemDescription
     */
    public String getNewItemDescription() {
        return newItemDescription;
    }

    /**
     * @param newItemDescription the newItemDescription to set
     */
    public void setNewItemDescription(String newItemDescription) {
        this.newItemDescription = newItemDescription;
    }

    /**
     * @return the newApprovalStatus
     */
    public boolean isNewApprovalStatus() {
        return newApprovalStatus;
    }

    /**
     * @param newApprovalStatus the newApprovalStatus to set
     */
    public void setNewApprovalStatus(boolean newApprovalStatus) {
        this.newApprovalStatus = newApprovalStatus;
    }

    /**
     * @return the newItemReturnable
     */
    public boolean isNewItemReturnable() {
        return newItemReturnable;
    }

    /**
     * @param newItemReturnable the newItemReturnable to set
     */
    public void setNewItemReturnable(boolean newItemReturnable) {
        this.newItemReturnable = newItemReturnable;
    }

    /**
     * @return the newAllowSubscription
     */
    public boolean isNewAllowSubscription() {
        return newAllowSubscription;
    }

    /**
     * @param newAllowSubscription the newAllowSubscription to set
     */
    public void setNewAllowSubscription(boolean newAllowSubscription) {
        this.newAllowSubscription = newAllowSubscription;
    }

    /**
     * @return the newReturnablePeriod
     */
    public int getNewReturnablePeriod() {
        return newReturnablePeriod;
    }

    /**
     * @param newReturnablePeriod the newReturnablePeriod to set
     */
    public void setNewReturnablePeriod(int newReturnablePeriod) {
        this.newReturnablePeriod = newReturnablePeriod;
    }

    /**
     * @return the newBrandId
     */
    public Long getNewBrandId() {
        return newBrandId;
    }

    /**
     * @param newBrandId the newBrandId to set
     */
    public void setNewBrandId(Long newBrandId) {
        this.newBrandId = newBrandId;
    }

    /**
     * @return the newBrandName
     */
    public String getNewBrandName() {
        return newBrandName;
    }

    /**
     * @param newBrandName the newBrandName to set
     */
    public void setNewBrandName(String newBrandName) {
        this.newBrandName = newBrandName;
    }

    /**
     * @return the newBrandDescription
     */
    public String getNewBrandDescription() {
        return newBrandDescription;
    }

    /**
     * @param newBrandDescription the newBrandDescription to set
     */
    public void setNewBrandDescription(String newBrandDescription) {
        this.newBrandDescription = newBrandDescription;
    }

    /**
     * @return the newItemTypeId
     */
    public Long getNewItemTypeId() {
        return newItemTypeId;
    }

    /**
     * @param newItemTypeId the newItemTypeId to set
     */
    public void setNewItemTypeId(Long newItemTypeId) {
        this.newItemTypeId = newItemTypeId;
    }

    /**
     * @return the newItemTypeDescription
     */
    public String getNewItemTypeDescription() {
        return newItemTypeDescription;
    }

    /**
     * @param newItemTypeDescription the newItemTypeDescription to set
     */
    public void setNewItemTypeDescription(String newItemTypeDescription) {
        this.newItemTypeDescription = newItemTypeDescription;
    }

    /**
     * @return the newStorageType
     */
    public String getNewStorageType() {
        return newStorageType;
    }

    /**
     * @param newStorageType the newStorageType to set
     */
    public void setNewStorageType(String newStorageType) {
        this.newStorageType = newStorageType;
    }

    /**
     * @return the newIsPerishable
     */
    public boolean isNewIsPerishable() {
        return newIsPerishable;
    }

    /**
     * @param newIsPerishable the newIsPerishable to set
     */
    public void setNewIsPerishable(boolean newIsPerishable) {
        this.newIsPerishable = newIsPerishable;
    }

    /**
     * @return the newMeasurementType
     */
    public String getNewMeasurementType() {
        return newMeasurementType;
    }

    /**
     * @param newMeasurementType the newMeasurementType to set
     */
    public void setNewMeasurementType(String newMeasurementType) {
        this.newMeasurementType = newMeasurementType;
    }

    /**
     * @return the newItemCategory
     */
    public String getNewItemCategory() {
        return newItemCategory;
    }

    /**
     * @param newItemCategory the newItemCategory to set
     */
    public void setNewItemCategory(String newItemCategory) {
        this.newItemCategory = newItemCategory;
    }

    /**
     * @return the newItemSubCategory
     */
    public String getNewItemSubCategory() {
        return newItemSubCategory;
    }

    /**
     * @param newItemSubCategory the newItemSubCategory to set
     */
    public void setNewItemSubCategory(String newItemSubCategory) {
        this.newItemSubCategory = newItemSubCategory;
    }

    /**
     * @return the filteredBrand
     */
    public List<Brand> getFilteredBrand() {
        return filteredBrand;
    }

    /**
     * @param filteredBrand the filteredBrand to set
     */
    public void setFilteredBrand(List<Brand> filteredBrand) {
        this.filteredBrand = filteredBrand;
    }

    /**
     * @return the selectedBrand
     */
    public Brand getSelectedBrand() {
        return selectedBrand;
    }

    /**
     * @param selectedBrand the selectedBrand to set
     */
    public void setSelectedBrand(Brand selectedBrand) {
        this.selectedBrand = selectedBrand;
    }

    /**
     * @return the brandIdItem
     */
    public Long getBrandIdItem() {
        return brandIdItem;
    }

    /**
     * @param brandIdItem the brandIdItem to set
     */
    public void setBrandIdItem(Long brandIdItem) {
        this.brandIdItem = brandIdItem;
    }

    /**
     * @return the itemTypeIdItem
     */
    public Long getItemTypeIdItem() {
        return itemTypeIdItem;
    }

    /**
     * @param itemTypeIdItem the itemTypeIdItem to set
     */
    public void setItemTypeIdItem(Long itemTypeIdItem) {
        this.itemTypeIdItem = itemTypeIdItem;
    }

    /**
     * @return the selectedItemType
     */
    public ItemType getSelectedItemType() {
        return selectedItemType;
    }

    /**
     * @param selectedItemType the selectedItemType to set
     */
    public void setSelectedItemType(ItemType selectedItemType) {
        this.selectedItemType = selectedItemType;
    }

    /**
     * @return the filteredItemType
     */
    public List<ItemType> getFilteredItemType() {
        return filteredItemType;
    }

    /**
     * @param filteredItemType the filteredItemType to set
     */
    public void setFilteredItemType(List<ItemType> filteredItemType) {
        this.filteredItemType = filteredItemType;
    }

}
