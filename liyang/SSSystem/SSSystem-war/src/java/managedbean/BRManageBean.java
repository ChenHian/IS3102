package managedbean;

import java.io.Serializable;
import static java.lang.Math.abs;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import entity.DistributionCenter;
import entity.BatchOut;
import entity.BatchReceipt;
import entity.ItemDisposal;
import entity.PurchaseOrder;
import session.stateless.BRSessionBean;
import session.stateless.ViewAllDCSessionBean;

@Named(value = "BRManageBean")
@RequestScoped
@ManagedBean
public class BRManageBean implements Serializable {

    private Long newBRId;
    private Long newBOId;
    private String statusMessage;
    //private Long poId;
    private java.util.Date dateReceived2;
    @EJB
    private ViewAllDCSessionBean viewAllDCSessionBean;
    private String selectedCenterName;
    private DistributionCenter selectedDC;
    private String batchNumber;
    private java.util.Date expiryDate2;
    private Integer quantityIn;
    private Integer quantityRejected;
    // private String supplierName;

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
    private Long selectedBRId;
    private String selectedBRitemName;
    private String selectedBatchNumber;
    private int selectedAvailableQuantity;
    private java.util.Date selectedDateReceived2;
    private boolean disabled = true;

    //for batch out
    private long drId;
    private Date dateLeaveDC2;
    private Integer quantityOut;
    private BatchOut selectedBO;

    private int daysToExpiry;
    private String daysToExpiryInfo;
    private java.util.Date todayDate = new Date();
    private java.util.Date selectedDateExpiry2;

    //item disposal
    private Long newItemDisposalId;
    private Integer disposedQuantity;
    private java.util.Date disposalDate2;
    private String reasonForDisposal;
    private ItemDisposal selectedItemDisposal;

    //purchase order
    private PurchaseOrder selectedPurchaseOrder;
    private Long selectedPurchaseOrderId;
    private String selectedPurchaseOrderCenterName;
    private String selectedPurchaseOrderItemName;
    private String selectedPurchaseOrderCompanyName;
    private Integer selectedPurchaseOrderQuantity;
    private Long supplierId;

    public BRManageBean() {
    }

    public void createBR(ActionEvent event) {
        setNewBRId(brSessionBean.createBR(selectedPurchaseOrderId, dateReceived2, expiryDate2, selectedPurchaseOrderCenterName, batchNumber, quantityIn, quantityRejected));
        setSelectedPurchaseOrderCompanyName(brSessionBean.findSupplierName(selectedPurchaseOrderId).getCompanyName());
        setSupplierId(brSessionBean.findSupplierName(selectedPurchaseOrderId).getSupplierId());
        setStatusMessage("New BR Saved Successfully");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Create New BR Result: "
                + getStatusMessage() + " (New BR ID is " + getNewBRId() + ")", ""));
    }

    public void createBO(ActionEvent actionEvent) {
        setNewBOId(brSessionBean.createBO(selectedBRId, drId, dateLeaveDC2, quantityOut));
        System.out.println(String.valueOf(selectedBRId));
        setStatusMessage("New BO Saved Successfully");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Create New BO Result: "
                + getStatusMessage() + " (New BO ID is " + getNewBOId() + ")", ""));
    }

    public void createItemDisposal(ActionEvent actionEvent) {
        System.out.println("" + getSelectedBRId());
        setNewItemDisposalId(brSessionBean.createItemDisposal(selectedBRId, disposedQuantity, disposalDate2, reasonForDisposal));
        setStatusMessage("New Item Disposal Saved Successfully");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Create New Item Disposal Result: "
                + getStatusMessage() + " (New Item Disposal ID is " + getNewItemDisposalId() + ")", ""));
    }

    public void addFeedbackOnSupplierAction(ActionEvent actionEvent) {
        System.out.println("Supplier selected = " + supplierId);
        System.out.println("delivery rating = " + getFeedbackDeliveryRating());
        brSessionBean.addFeedbackOnSupplier(supplierId, getFeedbackDeliveryContent(), getFeedbackDeliveryRating(), getFeedbackStaffContent(), getFeedbackStaffRating(), getFeedbackItemContent(), getFeedbackItemRating());
        addMessage("Feedback on Supplier has been added successfully!");

    }

    public BatchReceipt getSelectedBR() {
        if (selectedBR != null) {
            setDisabled(false);
            //expiry date
            long diff = selectedBR.getExpiryDate().getTime() - todayDate.getTime();
            Long daysToExpiryLong = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            daysToExpiry = (int) (long) daysToExpiryLong;
            if (daysToExpiry < 0) {
                setDaysToExpiryInfo("Expired " + abs(daysToExpiry) + " days");
            } else {
                setDaysToExpiryInfo("Remaining " + daysToExpiry + " days");
            }
        }
        return selectedBR;
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<BatchReceipt> getAllBRs() {
        return brSessionBean.getAllBRs();
    }

    public List<BatchOut> getAllBOs() {
        return brSessionBean.getAllBOs();
    }

    public List<ItemDisposal> getAllItemDisposal() {
        return brSessionBean.getAllItemDisposal();
    }

    public List<PurchaseOrder> getAllPOs() {
        return brSessionBean.getAllPOs();
    }

    public Map<Long, String> getSuppliersBasicList() {
        return brSessionBean.getSuppliersBasicList();
    }

    public Map<String, String> getAllDistributioncenterNames() {
        return getViewAllDCSessionBean().getAllDistributioncenterNames();
    }

    public void setSelectedBR(BatchReceipt selectedBR) {
        this.selectedBR = selectedBR;
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

    public java.util.Date getTodayDate() {
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

    public BRSessionBean getBrSessionBean() {
        return brSessionBean;
    }

    public void setBrSessionBean(BRSessionBean brSessionBean) {
        this.brSessionBean = brSessionBean;
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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public long getDrId() {
        return drId;
    }

    public void setDrId(long drId) {
        this.drId = drId;
    }

    public Date getDateLeaveDC2() {
        return dateLeaveDC2;
    }

    public void setDateLeaveDC2(Date dateLeaveDC2) {
        this.dateLeaveDC2 = dateLeaveDC2;
    }

    public Integer getQuantityOut() {
        return quantityOut;
    }

    public void setQuantityOut(Integer quantityOut) {
        this.quantityOut = quantityOut;
    }

    public Long getNewBOId() {
        return newBOId;
    }

    public void setNewBOId(Long newBOId) {
        this.newBOId = newBOId;
    }

    public BatchOut getSelectedBO() {
        return selectedBO;
    }

    public void setSelectedBO(BatchOut selectedBO) {
        this.selectedBO = selectedBO;
    }

    public Long getSelectedBRId() {
        return selectedBRId;
    }

    public void setSelectedBRId(Long selectedBRId) {
        this.selectedBRId = selectedBRId;
    }

    public String getSelectedBRitemName() {
        return selectedBRitemName;
    }

    public void setSelectedBRitemName(String selectedBRitemName) {
        this.selectedBRitemName = selectedBRitemName;
    }

    public String getSelectedBatchNumber() {
        return selectedBatchNumber;
    }

    public void setSelectedBatchNumber(String selectedBatchNumber) {
        this.selectedBatchNumber = selectedBatchNumber;
    }

    public int getSelectedAvailableQuantity() {
        return selectedAvailableQuantity;
    }

    public void setSelectedAvailableQuantity(int selectedAvailableQuantity) {
        this.selectedAvailableQuantity = selectedAvailableQuantity;
    }

    public java.util.Date getSelectedDateReceived2() {
        return selectedDateReceived2;
    }

    public void setSelectedDateReceived2(java.util.Date selectedDateReceived2) {
        this.selectedDateReceived2 = selectedDateReceived2;
    }

    public int getDaysToExpiry() {
        return daysToExpiry;
    }

    public void setDaysToExpiry(int daysToExpiry) {
        this.daysToExpiry = daysToExpiry;
    }

    public java.util.Date getSelectedDateExpiry2() {
        return selectedDateExpiry2;
    }

    public void setSelectedDateExpiry2(java.util.Date selectedDateExpiry2) {
        this.selectedDateExpiry2 = selectedDateExpiry2;
    }

    public String getDaysToExpiryInfo() {
        return daysToExpiryInfo;
    }

    public void setDaysToExpiryInfo(String daysToExpiryInfo) {
        this.daysToExpiryInfo = daysToExpiryInfo;
    }

    public Integer getDisposedQuantity() {
        return disposedQuantity;
    }

    public void setDisposedQuantity(Integer disposedQuantity) {
        this.disposedQuantity = disposedQuantity;
    }

    public java.util.Date getDisposalDate2() {
        return disposalDate2;
    }

    public void setDisposalDate2(java.util.Date disposalDate2) {
        this.disposalDate2 = disposalDate2;
    }

    public String getReasonForDisposal() {
        return reasonForDisposal;
    }

    public void setReasonForDisposal(String reasonForDisposal) {
        this.reasonForDisposal = reasonForDisposal;
    }

    public Long getNewItemDisposalId() {
        return newItemDisposalId;
    }

    public void setNewItemDisposalId(Long newItemDisposalId) {
        this.newItemDisposalId = newItemDisposalId;
    }

    public ItemDisposal getSelectedItemDisposal() {
        return selectedItemDisposal;
    }

    public void setSelectedItemDisposal(ItemDisposal selectedItemDisposal) {
        this.selectedItemDisposal = selectedItemDisposal;
    }

    public PurchaseOrder getSelectedPurchaseOrder() {
        return selectedPurchaseOrder;
    }

    public void setSelectedPurchaseOrder(PurchaseOrder selectedPurchaseOrder) {
        this.selectedPurchaseOrder = selectedPurchaseOrder;
    }

    public Long getSelectedPurchaseOrderId() {
        return selectedPurchaseOrderId;
    }

    public void setSelectedPurchaseOrderId(Long selectedPurchaseOrderId) {
        this.selectedPurchaseOrderId = selectedPurchaseOrderId;
    }

    public String getSelectedPurchaseOrderCenterName() {
        return selectedPurchaseOrderCenterName;
    }

    public void setSelectedPurchaseOrderCenterName(String selectedPurchaseOrderCenterName) {
        this.selectedPurchaseOrderCenterName = selectedPurchaseOrderCenterName;
    }

    public String getSelectedPurchaseOrderItemName() {
        return selectedPurchaseOrderItemName;
    }

    public void setSelectedPurchaseOrderItemName(String selectedPurchaseOrderItemName) {
        this.selectedPurchaseOrderItemName = selectedPurchaseOrderItemName;
    }

    public String getSelectedPurchaseOrderCompanyName() {
        return selectedPurchaseOrderCompanyName;
    }

    public void setSelectedPurchaseOrderCompanyName(String selectedPurchaseOrderCompanyName) {
        this.selectedPurchaseOrderCompanyName = selectedPurchaseOrderCompanyName;
    }

    public Integer getSelectedPurchaseOrderQuantity() {
        return selectedPurchaseOrderQuantity;
    }

    public void setSelectedPurchaseOrderQuantity(Integer selectedPurchaseOrderQuantity) {
        this.selectedPurchaseOrderQuantity = selectedPurchaseOrderQuantity;
    }
}
