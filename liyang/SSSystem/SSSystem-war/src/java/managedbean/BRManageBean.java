package managedbean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import session.stateless.ViewAllDCSessionBean;
import entity.DistributionCenter;
import session.stateless.BRSessionBean;
import entity.BatchReceipt;
import entity.PurchaseOrder;
import entity.PurchaseRequisition;

@Named(value = "BRManageBean")
@RequestScoped
@ManagedBean
public class BRManageBean implements Serializable {

    private Long newBRId;
    private String statusMessage;
    private Long poId;
    private java.util.Date dateReceived2;
    @EJB
    private ViewAllDCSessionBean viewAllDCSessionBean;
    private String selectedCenterName;
    private DistributionCenter selectedDC;
    private String batchNumber;
    private java.util.Date expiryDate2;
    private Integer quantityIn;
    private Integer quantityRejected;
    private Integer availableQuantity;
    private String supplierName;
    private Long supplierId;
    private Date todayDate = new Date();
    private Long selectedPoId;
    @EJB
    private BRSessionBean brSessionBean;

    //for addFeedbackToSupplier
    private long supplierToFeedback;
    private String feedbackDeliveryContent;
    private int feedbackDeliveryRating;
    private String feedbackStaffContent;
    private int feedbackStaffRating;
    private String feedbackItemContent;
    private int feedbackItemRating;

    private BatchReceipt selectedBR;

    private List<BatchReceipt> filteredBRs;
    
    
    
    public BRManageBean() {
    }

    public void createBR(ActionEvent event) {
        setNewBRId(brSessionBean.createBR(poId, dateReceived2, expiryDate2, getSelectedCenterName(), batchNumber, quantityIn, quantityRejected, availableQuantity));
        setSupplierName(brSessionBean.findSupplierName(poId).getCompanyName());
        setSupplierId(brSessionBean.findSupplierName(poId).getSupplierId());
        setStatusMessage("New BR Saved Successfully");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Create New BR Result: "
                + getStatusMessage() + " (New BR ID is " + getNewBRId() + ")", ""));
    }

    public void addFeedbackOnSupplierAction(ActionEvent actionEvent) {
        System.out.println("Supplier selected = " + supplierId);
        System.out.println("delivery rating = " + getFeedbackDeliveryRating());
        brSessionBean.addFeedbackOnSupplier(supplierId, getFeedbackDeliveryContent(), getFeedbackDeliveryRating(), getFeedbackStaffContent(), getFeedbackStaffRating(), getFeedbackItemContent(), getFeedbackItemRating());
        addMessage("Feedback on Supplier has been added successfully!");

    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Map<Long, String> getSuppliersBasicList() {
        return brSessionBean.getSuppliersBasicList();
    }

    public Map<String, String> getAllDistributioncenterNames() {
        return getViewAllDCSessionBean().getAllDistributioncenterNames();
    }

    public String getSelectedCenterName() {
        return selectedCenterName;
    }

    public void setSelectedCenterName(String selectedCenterName) {
        this.selectedCenterName = selectedCenterName;
    }

    public DistributionCenter getSelectedDC() {
        return selectedDC;
    }

    public void setSelectedDC(DistributionCenter selectedDC) {
        this.selectedDC = selectedDC;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Date getTodayDate() {
        return todayDate;
    }

    public Long getNewBRId() {
        return newBRId;
    }

    public void setNewBRId(Long newBRId) {
        this.newBRId = newBRId;
    }

    public java.util.Date getDateReceived2() {
        return dateReceived2;
    }

    public void setDateReceived2(java.util.Date dateReceived2) {
        this.dateReceived2 = dateReceived2;
    }

    public ViewAllDCSessionBean getViewAllDCSessionBean() {
        return viewAllDCSessionBean;
    }

    public void setViewAllDCSessionBean(ViewAllDCSessionBean viewAllDCSessionBean) {
        this.viewAllDCSessionBean = viewAllDCSessionBean;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public java.util.Date getExpiryDate2() {
        return expiryDate2;
    }

    public void setExpiryDate2(java.util.Date expiryDate2) {
        this.expiryDate2 = expiryDate2;
    }

    public Integer getQuantityIn() {
        return quantityIn;
    }

    public void setQuantityIn(Integer quantityIn) {
        this.quantityIn = quantityIn;
    }

    public Integer getQuantityRejected() {
        return quantityRejected;
    }

    public void setQuantityRejected(Integer quantityRejected) {
        this.quantityRejected = quantityRejected;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Long getPoId() {
        return poId;
    }

    public void setPoId(Long poId) {
        this.poId = poId;
    }

    public BRSessionBean getBrSessionBean() {
        return brSessionBean;
    }

    public void setBrSessionBean(BRSessionBean brSessionBean) {
        this.brSessionBean = brSessionBean;
    }

    public long getSupplierToFeedback() {
        return supplierToFeedback;
    }

    public void setSupplierToFeedback(long supplierToFeedback) {
        this.supplierToFeedback = supplierToFeedback;
    }

    public String getFeedbackDeliveryContent() {
        return feedbackDeliveryContent;
    }

    public void setFeedbackDeliveryContent(String feedbackDeliveryContent) {
        this.feedbackDeliveryContent = feedbackDeliveryContent;
    }

    public int getFeedbackDeliveryRating() {
        return feedbackDeliveryRating;
    }

    public void setFeedbackDeliveryRating(int feedbackDeliveryRating) {
        this.feedbackDeliveryRating = feedbackDeliveryRating;
    }

    public String getFeedbackStaffContent() {
        return feedbackStaffContent;
    }

    public void setFeedbackStaffContent(String feedbackStaffContent) {
        this.feedbackStaffContent = feedbackStaffContent;
    }

    public int getFeedbackStaffRating() {
        return feedbackStaffRating;
    }

    public void setFeedbackStaffRating(int feedbackStaffRating) {
        this.feedbackStaffRating = feedbackStaffRating;
    }

    public String getFeedbackItemContent() {
        return feedbackItemContent;
    }

    public void setFeedbackItemContent(String feedbackItemContent) {
        this.feedbackItemContent = feedbackItemContent;
    }

    public int getFeedbackItemRating() {
        return feedbackItemRating;
    }

    public void setFeedbackItemRating(int feedbackItemRating) {
        this.feedbackItemRating = feedbackItemRating;
    }

    public Long getSelectedPoId() {
        return selectedPoId;
    }

    public void setSelectedPoId(Long selectedPoId) {
        this.selectedPoId = selectedPoId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public BatchReceipt getSelectedBR() {
        return selectedBR;
    }

    public void setSelectedBR(BatchReceipt selectedBR) {
        this.selectedBR = selectedBR;
    }

    public List<BatchReceipt> getAllBRs() {
        return brSessionBean.getAllBRs();
    }
    
    
    
    

}
