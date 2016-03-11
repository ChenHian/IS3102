/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procurementManagedBean;

import entity.Contract;
import entity.Item;
import entity.PurchaseOrder;
import entity.Quotation;
import entity.Supplier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import session.stateless.SupplierSessionBean;

/**
 *
 * @author QianJun
 */
@Named(value = "supplierDataTableBean")
@RequestScoped
public class SupplierDataTableBean {
    @EJB
    private SupplierSessionBean supplierSessionBean;
    
    //for edit and view supplier
    private Supplier selectedSupplier;  
    private List<Supplier> filteredSuppliers;
    private long selectedSupplierId;
    private String selectedSupplierCompanyName;
    private String selectedSupplierEmail;
    private String selectedSupplierAddress;
    private String selectedSupplierContactPerson;
    private String selectedSupplierContactNumber;
    private String supplierWeightedRating;
    private String supplierAverageDeliveryRating;
    private String supplierAverageStaffRating;
    private String supplierAverageItemRating;
    private boolean disabled = true;
    
    //for addFeedbackToSupplier
    private long supplierToFeedback;
    private String feedbackDeliveryContent;
    private int feedbackDeliveryRating;
    private String feedbackStaffContent;
    private int feedbackStaffRating;
    private String feedbackItemContent;
    private int feedbackItemRating;
    
    //for add Supplier
    private String newCompanyName;
    private String newEmail;
    private String newAddress;
    private String newContactPerson;
    private String newContactNumber;
    
    
    public SupplierDataTableBean() {
    }
    
    //for view supplier
    public List<Supplier> getSuppliers(){
        return supplierSessionBean.getSuppliers();
    }
    
    public List<Quotation> getSupplierQuotation(){
        System.out.println("SupplierDataTableBean -> getSupplierQuotation() -> seletedSupplierId = " + selectedSupplierId);
        return supplierSessionBean.getSupplierQuotation(selectedSupplierId);
    }
    
    public List<Contract> getSupplierContract(){
        System.out.println("SupplierDataTableBean -> getSupplierContract() -> seletedSupplierId = " + selectedSupplierId);
        return supplierSessionBean.getSupplierContract(selectedSupplierId);
    }
    
    public List<PurchaseOrder> getSupplierPurchase(){
        System.out.println("SupplierDataTableBean -> getSupplierPurchaseHistory() -> seletedSupplierId = " + selectedSupplierId);
        return supplierSessionBean.getSupplierPurchase(selectedSupplierId);
    }
    
    public void getSupplierAverageRating(){
        System.out.println("SupplierDataTableBean -> getSupplierAverageDeliveryRating() -> seletedSupplierId = " + selectedSupplierId);
        ArrayList<String> averageRating = supplierSessionBean.getSupplierAverageRating(selectedSupplierId);
        
        supplierAverageDeliveryRating = averageRating.get(0);
        supplierAverageStaffRating = averageRating.get(1);
        supplierAverageItemRating = averageRating.get(2);
        supplierWeightedRating = averageRating.get(3);
        System.out.println("in get supplier average rating of dt bean");
    }


    /**
     * @return the selectedSupplier
     */
    public Supplier getSelectedSupplier() {
        if(selectedSupplier != null){
            selectedSupplierId = selectedSupplier.getSupplierId();
            getSupplierAverageRating();
            disabled = false;
        }
        return selectedSupplier;
    }

    /**
     * @param selectedSupplier the selectedSupplier to set
     */
    public void setSelectedSupplier(Supplier selectedSupplier) {
        this.selectedSupplier = selectedSupplier;
    }

    /**
     * @return the filteredSuppliers
     */
    public List<Supplier> getFilteredSuppliers() {
        return filteredSuppliers;
    }

    /**
     * @param filteredSuppliers the filteredSuppliers to set
     */
    public void setFilteredSuppliers(List<Supplier> filteredSuppliers) {
        this.filteredSuppliers = filteredSuppliers;
    }

    /**
     * @return the selectedSupplierId
     */
    public long getSelectedSupplierId() {
        return selectedSupplierId;
    }

    /**
     * @param selectedSupplierId the selectedSupplierId to set
     */
    public void setSelectedSupplierId(long selectedSupplierId) {
        this.selectedSupplierId = selectedSupplierId;
    }

    /**
     * @return the selectedSupplierCompanyName
     */
    public String getSelectedSupplierCompanyName() {
        return selectedSupplierCompanyName;
    }

    /**
     * @param selectedSupplierCompanyName the selectedSupplierCompanyName to set
     */
    public void setSelectedSupplierCompanyName(String selectedSupplierCompanyName) {
        this.selectedSupplierCompanyName = selectedSupplierCompanyName;
    }

    /**
     * @return the selectedSupplierEmail
     */
    public String getSelectedSupplierEmail() {
        return selectedSupplierEmail;
    }

    /**
     * @param selectedSupplierEmail the selectedSupplierEmail to set
     */
    public void setSelectedSupplierEmail(String selectedSupplierEmail) {
        this.selectedSupplierEmail = selectedSupplierEmail;
    }

    /**
     * @return the selectedSupplierAddress
     */
    public String getSelectedSupplierAddress() {
        return selectedSupplierAddress;
    }

    /**
     * @param selectedSupplierAddress the selectedSupplierAddress to set
     */
    public void setSelectedSupplierAddress(String selectedSupplierAddress) {
        this.selectedSupplierAddress = selectedSupplierAddress;
    }

    /**
     * @return the selectedSupplierContactPerson
     */
    public String getSelectedSupplierContactPerson() {
        return selectedSupplierContactPerson;
    }

    /**
     * @param selectedSupplierContactPerson the selectedSupplierContactPerson to set
     */
    public void setSelectedSupplierContactPerson(String selectedSupplierContactPerson) {
        this.selectedSupplierContactPerson = selectedSupplierContactPerson;
    }

    /**
     * @return the selectedSupplierContactNumber
     */
    public String getSelectedSupplierContactNumber() {
        return selectedSupplierContactNumber;
    }

    /**
     * @param selectedSupplierContactNumber the selectedSupplierContactNumber to set
     */
    public void setSelectedSupplierContactNumber(String selectedSupplierContactNumber) {
        this.selectedSupplierContactNumber = selectedSupplierContactNumber;
    }
            
    public void editSupplierProfileAction(ActionEvent actionEvent) {
        supplierSessionBean.updateSupplier(selectedSupplierId, selectedSupplierCompanyName, selectedSupplierEmail, selectedSupplierAddress, selectedSupplierContactPerson, selectedSupplierContactNumber);
        addMessage("Supplier record has been updated successfully!");
    }
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addFeedbackOnSupplierAction(ActionEvent actionEvent) {
        System.out.println("Supplier selected = " + supplierToFeedback);
        System.out.println("delivery rating = " + feedbackDeliveryRating);
        supplierSessionBean.addFeedbackOnSupplier(supplierToFeedback, feedbackDeliveryContent, feedbackDeliveryRating, feedbackStaffContent, feedbackStaffRating, feedbackItemContent, feedbackItemRating);
        addMessage("Feedback on Supplier has been added successfully!");
        
    }

    /**
     * @return the supplierToFeedback
     */
    public long getSupplierToFeedback() {
        return supplierToFeedback;
    }

    /**
     * @param supplierToFeedback the supplierToFeedback to set
     */
    public void setSupplierToFeedback(long supplierToFeedback) {
        this.supplierToFeedback = supplierToFeedback;
    }
    
    public Map<Long,String> getSuppliersBasicList(){
            return supplierSessionBean.getSuppliersBasicList();
    }
    
    public void addSupplierAction(ActionEvent actionEvent) {
        supplierSessionBean.addSupplier(newCompanyName, newEmail, newAddress, newContactPerson, newContactNumber);
        addMessage("A new supplier record has been added successfully!");
        
    }
    
    public void removeSupplierAction(ActionEvent actionEvent) {
        
        if(selectedSupplier != null){
            supplierSessionBean.removeSupplier(selectedSupplier.getSupplierId());
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
    

    /**
     * @return the newCompanyName
     */
    public String getNewCompanyName() {
        return newCompanyName;
    }

    /**
     * @param newCompanyName the newCompanyName to set
     */
    public void setNewCompanyName(String newCompanyName) {
        this.newCompanyName = newCompanyName;
    }

    /**
     * @return the newEmail
     */
    public String getNewEmail() {
        return newEmail;
    }

    /**
     * @param newEmail the newEmail to set
     */
    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    /**
     * @return the newAddress
     */
    public String getNewAddress() {
        return newAddress;
    }

    /**
     * @param newAddress the newAddress to set
     */
    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }

    /**
     * @return the newContactPerson
     */
    public String getNewContactPerson() {
        return newContactPerson;
    }

    /**
     * @param newContactPerson the newContactPerson to set
     */
    public void setNewContactPerson(String newContactPerson) {
        this.newContactPerson = newContactPerson;
    }

    /**
     * @return the newContactNumber
     */
    public String getNewContactNumber() {
        return newContactNumber;
    }

    /**
     * @param newContactNumber the newContactNumber to set
     */
    public void setNewContactNumber(String newContactNumber) {
        this.newContactNumber = newContactNumber;
    }

    /**
     * @return the feedbackDeliveryContent
     */
    public String getFeedbackDeliveryContent() {
        return feedbackDeliveryContent;
    }

    /**
     * @param feedbackDeliveryContent the feedbackDeliveryContent to set
     */
    public void setFeedbackDeliveryContent(String feedbackDeliveryContent) {
        this.feedbackDeliveryContent = feedbackDeliveryContent;
    }

    /**
     * @return the feedbackDeliveryRating
     */
    public int getFeedbackDeliveryRating() {
        return feedbackDeliveryRating;
    }

    /**
     * @param feedbackDeliveryRating the feedbackDeliveryRating to set
     */
    public void setFeedbackDeliveryRating(int feedbackDeliveryRating) {
        this.feedbackDeliveryRating = feedbackDeliveryRating;
    }

    /**
     * @return the feedbackStaffContent
     */
    public String getFeedbackStaffContent() {
        return feedbackStaffContent;
    }

    /**
     * @param feedbackStaffContent the feedbackStaffContent to set
     */
    public void setFeedbackStaffContent(String feedbackStaffContent) {
        this.feedbackStaffContent = feedbackStaffContent;
    }

    /**
     * @return the feedbackStaffRating
     */
    public int getFeedbackStaffRating() {
        return feedbackStaffRating;
    }

    /**
     * @param feedbackStaffRating the feedbackStaffRating to set
     */
    public void setFeedbackStaffRating(int feedbackStaffRating) {
        this.feedbackStaffRating = feedbackStaffRating;
    }

    /**
     * @return the feedbackItemContent
     */
    public String getFeedbackItemContent() {
        return feedbackItemContent;
    }

    /**
     * @param feedbackItemContent the feedbackItemContent to set
     */
    public void setFeedbackItemContent(String feedbackItemContent) {
        this.feedbackItemContent = feedbackItemContent;
    }

    /**
     * @return the feedbackItemRating
     */
    public int getFeedbackItemRating() {
        return feedbackItemRating;
    }

    /**
     * @param feedbackItemRating the feedbackItemRating to set
     */
    public void setFeedbackItemRating(int feedbackItemRating) {
        this.feedbackItemRating = feedbackItemRating;
    }

    /**
     * @return the supplierWeightedRating
     */
    public String getSupplierWeightedRating() {
        return supplierWeightedRating;
    }

    /**
     * @param supplierWeightedRating the supplierWeightedRating to set
     */
    public void setSupplierWeightedRating(String supplierWeightedRating) {
        this.supplierWeightedRating = supplierWeightedRating;
    }

    /**
     * @param supplierAverageDeliveryRating the supplierAverageDeliveryRating to set
     */
    public void setSupplierAverageDeliveryRating(String supplierAverageDeliveryRating) {
        this.supplierAverageDeliveryRating = supplierAverageDeliveryRating;
    }

    /**
     * @param supplierAverageStaffRating the supplierAverageStaffRating to set
     */
    public void setSupplierAverageStaffRating(String supplierAverageStaffRating) {
        this.supplierAverageStaffRating = supplierAverageStaffRating;
    }

    /**
     * @param supplierAverageItemRating the supplierAverageItemRating to set
     */
    public void setSupplierAverageItemRating(String supplierAverageItemRating) {
        this.supplierAverageItemRating = supplierAverageItemRating;
    }

    /**
     * @return the supplierAverageDeliveryRating
     */
    public String getSupplierAverageDeliveryRating() {
        return supplierAverageDeliveryRating;
    }

    /**
     * @return the supplierAverageStaffRating
     */
    public String getSupplierAverageStaffRating() {
        return supplierAverageStaffRating;
    }

    /**
     * @return the supplierAverageItemRating
     */
    public String getSupplierAverageItemRating() {
        return supplierAverageItemRating;
    }

    /**
     * @return the disabled
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * @param disabled the disabled to set
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void isEmailValid(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
        String newEmail = value.toString();

        Pattern _pattern = Pattern.compile("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)");

        if (!_pattern.matcher(newEmail).matches()) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Email: Validation Error: Invalid Email Format", null));
        }
    }
    
}
