/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Contract;
import entity.PurchaseOrder;
import entity.PurchaseRequisition;
import entity.Quotation;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import session.stateless.PurchaseOrderSessionBean;

/**
 *
 * @author QianJun
 */
@Named(value = "purchaseOrderDataTableBean")
@RequestScoped
public class PurchaseOrderDataTableBean {
    @EJB
    private PurchaseOrderSessionBean purchaseOrderSessionBean;
    
   

    
    
    private PurchaseRequisition selectedPurchaseRequisitionToConvert;
    
    //values transfer from viewPurchaseRequisition and used to display in createPurchaseOrder
    private long selectedPurchaseRequisitionToConvertId;
    private long selectedPurchaseRequisitionId;
    private String selectedCenterName;
    private long selectedItemId;
    private String selectedItemName;
    private int selectedQuantity;
    private String selectedMeasurementUnit;
    private Date expectedDeliveryDate;
    private String selectedCenterAddress;
    
    private Quotation selectedQuotationReference;
    private List<Quotation> filteredPurchaseReference;
    private Contract selectedContractReference;
    private List<Contract> filteredPurchaseReferenceC;
    
    //dropdownlist
    private String referenceType;
    
    private Date todayDate = new Date();
    
    //view purchase order
    private PurchaseOrder selectedPurchaseOrder;
    private List<PurchaseOrder> filteredPurchaseOrder;
    private PurchaseOrder selectedPurchaseOrderH;
    private List<PurchaseOrder> filteredPurchaseOrderH;
    
    //newly added purchase order
    private PurchaseOrder purchaseOrderCreated;
    
    public PurchaseOrderDataTableBean() {
    }

    /**
     * @return the selectedPurchaseRequisitionToConvert
     */
    public PurchaseRequisition getSelectedPurchaseRequisitionToConvert() {
        return selectedPurchaseRequisitionToConvert;
    }

    /**
     * @param selectedPurchaseRequisitionToConvert the selectedPurchaseRequisitionToConvert to set
     */
    public void setSelectedPurchaseRequisitionToConvert(PurchaseRequisition selectedPurchaseRequisitionToConvert) {
        this.selectedPurchaseRequisitionToConvert = selectedPurchaseRequisitionToConvert;
    }

    /**
     * @return the selectedPurchaseRequisitionToConvertId
     */
    public long getSelectedPurchaseRequisitionToConvertId() {
        return selectedPurchaseRequisitionToConvertId;
    }

    /**
     * @param selectedPurchaseRequisitionToConvertId the selectedPurchaseRequisitionToConvertId to set
     */
    public void setSelectedPurchaseRequisitionToConvertId(long selectedPurchaseRequisitionToConvertId) {
        this.selectedPurchaseRequisitionToConvertId = selectedPurchaseRequisitionToConvertId;
    }

    /**
     * @return the selectedPurchaseRequisitionId
     */
    public long getSelectedPurchaseRequisitionId() {
        return selectedPurchaseRequisitionId;
    }

    /**
     * @param selectedPurchaseRequisitionId the selectedPurchaseRequisitionId to set
     */
    public void setSelectedPurchaseRequisitionId(long selectedPurchaseRequisitionId) {
        this.selectedPurchaseRequisitionId = selectedPurchaseRequisitionId;
    }
    
    

    /**
     * @return the selectedItemId
     */
    public long getSelectedItemId() {
        return selectedItemId;
    }

    /**
     * @param selectedItemId the selectedItemId to set
     */
    public void setSelectedItemId(long selectedItemId) {
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
     * @return the selectedQuantity
     */
    public int getSelectedQuantity() {
        return selectedQuantity;
    }

    /**
     * @param selectedQuantity the selectedQuantity to set
     */
    public void setSelectedQuantity(int selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }

    /**
     * @return the selectedMeasurementUnit
     */
    public String getSelectedMeasurementUnit() {
        return selectedMeasurementUnit;
    }

    /**
     * @param selectedMeasurementUnit the selectedMeasurementUnit to set
     */
    public void setSelectedMeasurementUnit(String selectedMeasurementUnit) {
        this.selectedMeasurementUnit = selectedMeasurementUnit;
    }

    /**
     * @return the selectedCenterName
     */
    public String getSelectedCenterName() {
        return selectedCenterName;
    }

    /**
     * @param selectedCenterName the selectedCenterName to set
     */
    public void setSelectedCenterName(String selectedCenterName) {
        this.selectedCenterName = selectedCenterName;
    }

    /**
     * @return the expectedDeliveryDate
     */
    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    /**
     * @param expectedDeliveryDate the expectedDeliveryDate to set
     */
    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public List<Quotation> getPurchaseReference(){
        System.out.println("in getpurchaseref selectedItem id = " + selectedItemId);
      //  purchaseOrderSessionBean.setSelectedItemId(selectedItemId);
        return getPurchaseOrderSessionBean().getPurchaseReference();

    }

    public List<Contract> getPurchaseReferenceC(){
        System.out.println("from purchaseR C -> selectedItem id = " + selectedItemId);
        return getPurchaseOrderSessionBean().getPurchaseReferenceC();

    }
    
    /**
     * @return the selectedQuotationReference
     */
    public Quotation getSelectedQuotationReference() {
        System.out.println("will pass by here2");
        System.out.println("selectedQ R =" + selectedQuotationReference);
        if(selectedQuotationReference != null){
           // selectedItemId = selectedQuotationReference.getItem().getItemId();
           // System.out.println("in getselectedQ ref = " + selectedItemId);
           // purchaseOrderSessionBean.setSelectedItemId(selectedItemId);
          //  disabled = false;
        }
        return selectedQuotationReference;
    }

    /**
     * @param selectedQuotationReference the selectedQuotationReference to set
     */
    public void setSelectedQuotationReference(Quotation selectedQuotationReference) {
        System.out.println("will pass by here");
        this.selectedQuotationReference = selectedQuotationReference;
    }

    /**
     * @return the selectedContractReference
     */
    public Contract getSelectedContractReference() {
        return selectedContractReference;
    }

    /**
     * @param selectedContractReference the selectedContractReference to set
     */
    public void setSelectedContractReference(Contract selectedContractReference) {
        this.selectedContractReference = selectedContractReference;
    }

    /**
     * @return the filteredPurchaseReference
     */
    public List<Quotation> getFilteredPurchaseReference() {
        return filteredPurchaseReference;
    }

    /**
     * @param filteredPurchaseReference the filteredPurchaseReference to set
     */
    public void setFilteredPurchaseReference(List<Quotation> filteredPurchaseReference) {
        this.filteredPurchaseReference = filteredPurchaseReference;
    }

    /**
     * @return the filteredPurchaseReferenceC
     */
    public List<Contract> getFilteredPurchaseReferenceC() {
        return filteredPurchaseReferenceC;
    }

    /**
     * @param filteredPurchaseReferenceC the filteredPurchaseReferenceC to set
     */
    public void setFilteredPurchaseReferenceC(List<Contract> filteredPurchaseReferenceC) {
        this.filteredPurchaseReferenceC = filteredPurchaseReferenceC;
    }

    /**
     * @return the referenceType
     */
    public String getReferenceType() {
        return referenceType;
    }

    /**
     * @param referenceType the referenceType to set
     */
    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }
    
    public void onReferenceTypeSelected() {
        if(referenceType !=null && !referenceType.equals("")){
      
        }

    }
    
    public List<PurchaseOrder> getPurchaseOrder(){
         //  System.out.println("DataTableBean: getPurchaseRequisitionItems() --> selectedItemId = " + selectedItemId);
        return getPurchaseOrderSessionBean().getPurchaseOrder();
    }
    
    public List<PurchaseOrder> getPurchaseOrderH(){
         //  System.out.println("DataTableBean: getPurchaseRequisitionItems() --> selectedItemId = " + selectedItemId);
        return getPurchaseOrderSessionBean().getPurchaseOrderH();
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
    
    public void createPurchaseOrderAction(ActionEvent actionEvent) {
        System.out.println("in create purchase order action :referenceType =" + referenceType);
        System.out.println("selectedCRef = " + getSelectedContractReference());

        if(referenceType == null){
            errorMessage("Please select a reference type and the corresponding reference document from the table!");
        }
        else if(referenceType.equals("Quotation") && selectedQuotationReference != null){
            System.out.println("selectedPurchaseRequisitionId =" + selectedPurchaseRequisitionId);
            System.out.println("selectedCenterName =" + selectedCenterName);
            System.out.println("selectedCenterAddress =" + selectedCenterAddress);
            System.out.println("selectedItemId =" + selectedItemId);
            // to include address of the store or dc also
            System.out.println("selectedQuantity =" + selectedQuantity);
            System.out.println("expectedDeliveryDate =" + expectedDeliveryDate);
            System.out.println("referenceType =" + referenceType);

            purchaseOrderCreated = getPurchaseOrderSessionBean().createPurchaseOrderQ(selectedPurchaseRequisitionId, selectedCenterName, getSelectedCenterAddress(), selectedQuantity, expectedDeliveryDate,referenceType, selectedQuotationReference.getQuotationId());
             addMessage("A new purchase order has been created successfully!");
             try{
                 sendPurchaseOrderToSupplier(purchaseOrderCreated);
             }catch(Exception ex){
                 errorMessage("Purchase Order not sent to supplier yet!");
             }
        }
        else if(referenceType.equals("Contract") && selectedContractReference != null){
            System.out.println("selectedPurchaseRequisitionId =" + selectedPurchaseRequisitionId);
            System.out.println("selectedCenterName =" + selectedCenterName);
            System.out.println("selectedCenterAddress =" + getSelectedCenterAddress());
            System.out.println("selectedItemId =" + selectedItemId);
            // to include address of the store or dc also
            System.out.println("selectedQuantity =" + selectedQuantity);
            System.out.println("expectedDeliveryDate =" + expectedDeliveryDate);
            System.out.println("referenceType =" + referenceType);
            System.out.println("selectedContractId" + selectedContractReference.getContractId());
            purchaseOrderCreated = getPurchaseOrderSessionBean().createPurchaseOrderC(selectedPurchaseRequisitionId, selectedCenterName, getSelectedCenterAddress(), selectedQuantity, expectedDeliveryDate,referenceType, selectedContractReference.getContractId());
             addMessage("A new purchase order has been created successfully!");

             try{
                 sendPurchaseOrderToSupplier(purchaseOrderCreated);
             }catch(Exception ex){
                 errorMessage("Purchase Order not sent to supplier yet!");
             }
        }
        else{
            errorMessage("Please select a corresponding reference document from the table!");
        }
        
    }
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void errorMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public Date getTodayDate() {
        return todayDate;
    }

    /**
     * @return the selectedCenterAddress
     */
    public String getSelectedCenterAddress() {
        if(selectedCenterName != null){
            return purchaseOrderSessionBean.getSelectedCenterAddress(selectedCenterName);
        }
        return selectedCenterAddress;
    }

    /**
     * @param selectedCenterAddress the selectedCenterAddress to set
     */
    public void setSelectedCenterAddress(String selectedCenterAddress) {
        this.selectedCenterAddress = selectedCenterAddress;
    }

    /**
     * @return the selectedPurchaseOrderH
     */
    public PurchaseOrder getSelectedPurchaseOrderH() {
        return selectedPurchaseOrderH;
    }

    /**
     * @param selectedPurchaseOrderH the selectedPurchaseOrderH to set
     */
    public void setSelectedPurchaseOrderH(PurchaseOrder selectedPurchaseOrderH) {
        this.selectedPurchaseOrderH = selectedPurchaseOrderH;
    }

    /**
     * @return the filteredPurchaseOrderH
     */
    public List<PurchaseOrder> getFilteredPurchaseOrderH() {
        return filteredPurchaseOrderH;
    }

    /**
     * @param filteredPurchaseOrderH the filteredPurchaseOrderH to set
     */
    public void setFilteredPurchaseOrderH(List<PurchaseOrder> filteredPurchaseOrderH) {
        this.filteredPurchaseOrderH = filteredPurchaseOrderH;
    }
    
    public void voidPurchaseOrderAction(ActionEvent actionEvent) {
        PurchaseOrder poToVoid = new PurchaseOrder();
        
        if(selectedPurchaseOrder != null){
            poToVoid = getPurchaseOrderSessionBean().voidPurchaseOrder(selectedPurchaseOrder.getPurchaseOrderId());
            addMessage("The selected purchase order has been voided!");
            try{
                 sendVoidPurchaseOrderEmailToSupplier(poToVoid);
             }catch(Exception ex){
                 errorMessage("Purchase Order not sent to supplier yet!");
             }
        }
        else{
            errorMessage("Please select a purchase order to void!");
        }
    }
    
    public void sendVoidPurchaseOrderEmailToSupplier(PurchaseOrder purchaseOrder) throws MessagingException {
        
        if (purchaseOrder != null) {

            // to supplier email
            String to = purchaseOrder.getSupplier().getEmail();
            
            final String from = "purchasing.SMart@hotmail.com";
            final String password = "SMartPT02";
            //String host = "localhost";

            int port = 25;
            String host = "smtp.live.com";
            boolean auth = true;

            boolean debug = true;

            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            //props.put("mail.smtp.ssl.enable", true);
            props.put("mail.smtp.starttls.enable", "true");

            Authenticator authenticator = null;

            if (auth) {
                props.put("mail.smtp.auth", true);
                authenticator = new Authenticator() {
                    private PasswordAuthentication pa = new PasswordAuthentication(from, password);

                    @Override
                    public PasswordAuthentication getPasswordAuthentication() {
                        return pa;
                    }
                };
            }

            Session session = Session.getInstance(props, authenticator);
            session.setDebug(debug);
            Transport trans = session.getTransport("smtp");
            trans.connect("smtp.live.com", 587, "purchasing.SMart@hotmail.com", "SMartPT02");

            try {
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // Set Subject: header field
                message.setSubject("S-MART - New Purchase Order");

                // Send the actual HTML message, as big as you like
                String msg = "<p>Dear Supplier,</p>";
                msg += "<br/>";
                msg += "<p>Please be informed that due to certain reason(s) the following purchase order sent on " + purchaseOrder.getDateCreated() + " has been VOIDED.<p>";
                msg += "<br/>";
                msg += "<p><b>Purchase Order Id:</b> " + purchaseOrder.getPurchaseOrderId() + "</p>";
                msg += "<p><b>Deliver To:</b> " + purchaseOrder.getDeliverToCentreName() + "<p>";
                msg += "<p><b>Address:</b> " + purchaseOrder.getDeliverToAddress() + "</p>";
                msg += "<p><b>Expected Delivery Date:</b> " + purchaseOrder.getExpectedDeliveryDate() + "</p>";
                msg += "<br/>";
                msg += "<p><b>Item:</b> " + purchaseOrder.getItem().getItemName() + "</p>";
                msg += "<p><b>Quantity:</b> " + purchaseOrder.getQuantity() + " " + purchaseOrder.getItem().getItemType().getMeasurementType() +"</p>";
                msg += "<p><b>Unit Price:</b> " + purchaseOrder.getUnitPrice() + "</p>";
                msg += "<p><b>Total Amount:</b> " + purchaseOrder.getTotalAmount() + "</p>";
                msg += "<br/>";
                msg += "<p>Please do not hesitate to contact us should you have any questions.</p>";
                msg += "<br/>";
                msg += "<br/>";
                msg += "<p><i>Best Regards,<i></p>";
                msg += "<p><b>S-Mart Support Team</b></p>";

                message.setContent(msg, "text/html");

                // Send message
                trans.send(message);
                System.out.println("Sent purchase order email to supplier successfully....");

                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Email Sent...",
                                "Please check your email for new password..."));

            } catch (MessagingException mex) {
                
                mex.printStackTrace();
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Error: ",
                                "Not able to send email..."));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Error: ",
                            "Not able to save email..."));
        }
    }

    /**
     * @return the purchaseOrderSessionBean
     */
    public PurchaseOrderSessionBean getPurchaseOrderSessionBean() {
        return purchaseOrderSessionBean;
    }

    /**
     * @param purchaseOrderSessionBean the purchaseOrderSessionBean to set
     */
    public void setPurchaseOrderSessionBean(PurchaseOrderSessionBean purchaseOrderSessionBean) {
        this.purchaseOrderSessionBean = purchaseOrderSessionBean;
    }
    
     public void sendPurchaseOrderToSupplier(PurchaseOrder purchaseOrder) throws MessagingException {
        
        if (purchaseOrder != null) {

            // to supplier email
            String to = purchaseOrder.getSupplier().getEmail();
            
            final String from = "purchasing.SMart@hotmail.com";
            final String password = "SMartPT02";
            //String host = "localhost";

            int port = 25;
            String host = "smtp.live.com";
            boolean auth = true;

            boolean debug = true;

            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            //props.put("mail.smtp.ssl.enable", true);
            props.put("mail.smtp.starttls.enable", "true");

            Authenticator authenticator = null;

            if (auth) {
                props.put("mail.smtp.auth", true);
                authenticator = new Authenticator() {
                    private PasswordAuthentication pa = new PasswordAuthentication(from, password);

                    @Override
                    public PasswordAuthentication getPasswordAuthentication() {
                        return pa;
                    }
                };
            }

            Session session = Session.getInstance(props, authenticator);
            session.setDebug(debug);
            Transport trans = session.getTransport("smtp");
            trans.connect("smtp.live.com", 587, "purchasing.SMart@hotmail.com", "SMartPT02");

            try {
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // Set Subject: header field
                message.setSubject("S-MART - New Purchase Order");

                // Send the actual HTML message, as big as you like
                String msg = "<p>Dear Supplier,</p>";
                msg += "<br/>";
                msg += "<p>S-Mart has a new purchase order that requires your attention.<p>";
                msg += "<br/>";
                msg += "<p><b>Purchase Order Id:</b> " + purchaseOrder.getPurchaseOrderId() + "</p>";
                msg += "<p><b>Deliver To:</b> " + purchaseOrder.getDeliverToCentreName() + "<p>";
                msg += "<p><b>Address:</b> " + purchaseOrder.getDeliverToAddress() + "</p>";
                msg += "<p><b>Expected Delivery Date:</b> " + purchaseOrder.getExpectedDeliveryDate() + "</p>";
                msg += "<br/>";
                msg += "<p><b>Item:</b> " + purchaseOrder.getItem().getItemName() + "</p>";
                msg += "<p><b>Quantity:</b> " + purchaseOrder.getQuantity() + " " + purchaseOrder.getItem().getItemType().getMeasurementType() +"</p>";
                msg += "<p><b>Unit Price:</b> " + purchaseOrder.getUnitPrice() + "</p>";
                msg += "<p><b>Total Amount:</b> " + purchaseOrder.getTotalAmount() + "</p>";
                msg += "<br/>";
                msg += "<p>*The amount is calculated based on " + purchaseOrder.getDocumentReferenceType() + " " + purchaseOrder.getDocumentReferenceNumber()  + "</p>";
                msg += "<br/>";
                msg += "<br/>";
                msg += "<p><i>Best Regards,<i></p>";
                msg += "<p><b>S-Mart Support Team</b></p>";

                message.setContent(msg, "text/html");

                // Send message
                trans.send(message);
                System.out.println("Sent purchase order email to supplier successfully....");

                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Email Sent...",
                                "Please check your email for new password..."));

            } catch (MessagingException mex) {
                
                mex.printStackTrace();
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Error: ",
                                "Not able to send email..."));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Error: ",
                            "Not able to save email..."));
        }
    }

    /**
     * @return the purchaseOrderCreated
     */
    public PurchaseOrder getPurchaseOrderCreated() {
        return purchaseOrderCreated;
    }

    /**
     * @param purchaseOrderCreated the purchaseOrderCreated to set
     */
    public void setPurchaseOrderCreated(PurchaseOrder purchaseOrderCreated) {
        this.purchaseOrderCreated = purchaseOrderCreated;
    }

}
