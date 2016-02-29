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
import entity.PurchaseRequisition;
import javax.annotation.PostConstruct;
import session.stateless.PRSessionBean;

@Named(value = "PRManageBean")
@RequestScoped
@ManagedBean
public class PRManageBean implements Serializable {

    @EJB
    private PRSessionBean prSessionBean;
    private PurchaseRequisition selectedPR;
    private Long newPRId;
    private String statusMessage;
    private java.util.Date dateCreated2;
    private java.util.Date dateRequest2;
    private String selectedItemName;

    private String status;
    private Integer quantityRequested;

    @EJB
    private ViewAllDCSessionBean viewAllDCSessionBean;
    private String selectedCenterName;
    private DistributionCenter selectedDC;
    
    private List<PurchaseRequisition> allPRs;
    private List<PurchaseRequisition> filteredPRs;
    
    private Date todayDate= new Date();
    
    @PostConstruct
    public void init() {
        allPRs = prSessionBean.getAllPRs();
    }

    public void setAllPRs(List<PurchaseRequisition> allPRs) {
        this.allPRs = allPRs;
    }
    
    public PRManageBean() {
    }

    public void setFilteredPRs(List<PurchaseRequisition> filteredPRs) {
        this.filteredPRs = filteredPRs;
    }

    public List<PurchaseRequisition> getFilteredPRs() {
        return filteredPRs;
    }

    public java.util.Date getDateCreated2() {
        return dateCreated2;
    }

    public void setDateCreated2(Date dateCreated2) {
        this.dateCreated2 = dateCreated2;
    }

    public java.util.Date getDateRequest2() {
        return dateRequest2;
    }

    public void setDateRequest2(Date dateRequest2) {
        this.dateRequest2 = dateRequest2;
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

    public Long getNewPRId() {
        return newPRId;
    }

    public void setNewPRId(Long newPRId) {
        this.newPRId = newPRId;
    }

    public Integer getQuantityRequested() {
        return quantityRequested;
    }

    public void setQuantityRequested(Integer quantityRequested) {
        this.quantityRequested = quantityRequested;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSelectedItemName() {
        return selectedItemName;
    }

    public void setSelectedItemName(String selectedItemName) {
        this.selectedItemName = selectedItemName;
    }

    public Map<String, String> getAllDistributioncenterNames() {
        return viewAllDCSessionBean.getAllDistributioncenterNames();
    }

    public Map<String, String> getAllItemName() {
        return prSessionBean.getAllItemName();
    }

    public void createPR(ActionEvent event) {

        setNewPRId(prSessionBean.createPR(dateCreated2, dateRequest2, getSelectedCenterName(), status, quantityRequested, getSelectedItemName()));
        setStatusMessage("New PR Saved Successfully");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Create New PR Result: "
                + getStatusMessage() + " (New PR ID is " + getNewPRId() + ")", ""));
    }
    
    public List<PurchaseRequisition> getAllPRs() {
        return allPRs;
    }

    public PurchaseRequisition getSelectedPR() {
        return selectedPR;
    }

    public void setSelectedPR(PurchaseRequisition selectedPR) {
        this.selectedPR = selectedPR;
    }

    public Date getTodayDate() {
        return todayDate;
    }
}
