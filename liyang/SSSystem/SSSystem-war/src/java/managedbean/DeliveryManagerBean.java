package managedbean;

import entity.DeliveryRequest;
import entity.DeliveryArrangement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import session.stateless.DeliverySessionBean;
import session.stateless.StaffAccountSessionBean;

@Named(value = "deliveryManagerBean")
@RequestScoped
public class DeliveryManagerBean {

    @EJB
    private DeliverySessionBean deliverySessionBean;
    private String pickupLocation;
    private String dropoffLocation;
    private Date deliveryDate;
    private DeliveryRequest selectedDeliveryRequest;
    private DeliveryArrangement selectedDeliveryArrangement;
    private long newDeliveryArrangementId;
    private String statusMessage;
    
    
    private String newStatus;
     private List<String> statusTypes;

    public DeliveryManagerBean() {
    }
    
    @PostConstruct
    public void init() {
        
        statusTypes = Arrays.asList("Not Delivered","Delivered", "Ready For Pickup");
        //roles = roleSessionBean.getAllRoles();
        //filteredStaffAccounts = staffAccounts;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }
    
    

    public List<String> getStatusTypes() {
        return statusTypes;
    }

    public void setStatusTypes(List<String> statusTypes) {
        this.statusTypes = statusTypes;
    }
    
    

    private Date currentDate = new Date();

    public Date getCurrentDate() {

        return currentDate;
    }

    public List<DeliveryRequest> getDeliveryRequests() {
        return deliverySessionBean.getAllDeliveryRequests();
    }

    public List<DeliveryRequest> getDeliveryArrangements() {
        return deliverySessionBean.getAllDeliveryArrangements();
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public DeliveryRequest getSelectedDeliveryRequest() {
        return selectedDeliveryRequest;
    }

    public void setSelectedDeliveryRequest(DeliveryRequest selectedDeliveryRequest) {
        this.selectedDeliveryRequest = selectedDeliveryRequest;
    }

    public DeliveryArrangement getSelectedDeliveryArrangement() {
        return selectedDeliveryArrangement;
    }

    public void setSelectedDeliveryArrangement(DeliveryArrangement selectedDeliveryArrangement) {
        this.selectedDeliveryArrangement = selectedDeliveryArrangement;
    }

    public long getNewDeliveryArrangementId() {
        return newDeliveryArrangementId;
    }

    public void setNewDeliveryArrangementId(long newDeliveryArrangementId) {
        this.newDeliveryArrangementId = newDeliveryArrangementId;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    
    public void saveNewDeliveryArrangement(ActionEvent event) {
        newDeliveryArrangementId = deliverySessionBean.addNewDeliveryArrangement(selectedDeliveryRequest.getDeliverFrom(), selectedDeliveryRequest.getDeliverTo(), deliveryDate, selectedDeliveryRequest.getDeliveryRequestId(), selectedDeliveryRequest);
        statusMessage = "New Delivery Arrangement Saved Successfully";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Add New Delivery Arrangement Result: "
                + statusMessage + " (New Delivery Arrangement ID is " + newDeliveryArrangementId + ")", ""));


    }  
    public void updateStatus() {
        System.out.println(newStatus);
        deliverySessionBean.updateStatus(Long.parseLong("601"),newStatus);
    }
    
     
}
