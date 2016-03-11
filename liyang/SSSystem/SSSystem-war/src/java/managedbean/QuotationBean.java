/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Item;
import entity.Quotation;
import entity.RequestForQuotation;
import entity.Supplier;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;
import session.stateless.QuotationSessionBean;
import session.stateless.RequestForQuotationSessionBean;

/**
 *
 * @author Hongyi
 */
@ManagedBean(name = "QuotationBean")
@SessionScoped
public class QuotationBean implements Serializable{

    @EJB
    private RequestForQuotationSessionBean requestForQuotationSessionBean;
    
    @EJB
    private QuotationSessionBean quotationSessionBean;
    
    private Date todayDate = new Date();
    //quotation
    private Item item;
    private RequestForQuotation rfq;
    private Long requestForQuotationId;
    private Double price;
    private int deliveryLeadTime;
    private Date validStartDate;
    private Date validEndDate;
    private String purchaseTerms;
    private String paymentTerms;
    private String deliveryTerms;
    private String returnTerms;
    private Long supplierId;
    private List<String> statusList;
    Map<Long, String> suppliers = new HashMap<Long, String>();
    
    private boolean disabled = true;
    private List<Quotation> filteredQuotation;
    private Quotation selectedQuotation;
    private List<Quotation> pendingQuotations;
    private List<String> filteredSuppliers;
    private List<Quotation> acceptedQuotations;
    private List<Quotation> allQuotations;
    
    
    
    public QuotationBean() {
        
    }
    
    
    
    @PostConstruct
    public void init() {
        statusList = new ArrayList<String>();
        statusList.add("Declined");
        statusList.add("Accepted");
        statusList.add("Pending");
        statusList.add("Approved");
    }

    public Map<Long, String> getSuppliers() {
        return suppliers;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setTodayDate(Date todayDate) {
        this.todayDate = todayDate;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public void setSuppliers(Map<Long, String> suppliers) {
        this.suppliers = suppliers;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setPendingQuotations(List<Quotation> pendingQuotations) {
        this.pendingQuotations = pendingQuotations;
    }

    public void setFilteredSuppliers(List<String> filteredSuppliers) {
        this.filteredSuppliers = filteredSuppliers;
    }

    public void setAcceptedQuotations(List<Quotation> acceptedQuotations) {
        this.acceptedQuotations = acceptedQuotations;
    }

    public void setAllQuotations(List<Quotation> allQuotations) {
        this.allQuotations = allQuotations;
    }
    
    
    
    public Long getRequestForQuotationId() {
        return requestForQuotationId;
    }

    public void setRequestForQuotationId(Long requestForQuotationId) {
        this.requestForQuotationId = requestForQuotationId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public RequestForQuotation getRfq() {
        return rfq;
    }

    public void setRfq(RequestForQuotation rfq) {
        this.rfq = rfq;
    }
    
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getDeliveryLeadTime() {
        return deliveryLeadTime;
    }

    public void setDeliveryLeadTime(int deliveryLeadTime) {
        this.deliveryLeadTime = deliveryLeadTime;
    }

    public Date getValidStartDate() {
        return validStartDate;
    }

    public void setValidStartDate(Date validStartDate) {
        this.validStartDate = validStartDate;
    }

    public Date getValidEndDate() {
        return validEndDate;
    }

    public void setValidEndDate(Date validEndDate) {
        this.validEndDate = validEndDate;
    }

    public String getPurchaseTerms() {
        return purchaseTerms;
    }

    public void setPurchaseTerms(String purchaseTerms) {
        this.purchaseTerms = purchaseTerms;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getDeliveryTerms() {
        return deliveryTerms;
    }

    public void setDeliveryTerms(String deliveryTerms) {
        this.deliveryTerms = deliveryTerms;
    }

    public String getReturnTerms() {
        return returnTerms;
    }

    public void setReturnTerms(String returnTerms) {
        this.returnTerms = returnTerms;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Date getTodayDate() {
        return todayDate;
    }
    
    public List<Quotation> getFilteredQuotation() {
        return filteredQuotation;
    }

    public void setFilteredQuotation(List<Quotation> filteredQuotation) {
        this.filteredQuotation = filteredQuotation;
    }

    public Quotation getSelectedQuotation() {
        return selectedQuotation;
    }

    public void setSelectedQuotation(Quotation selectedQuotation) {
        this.selectedQuotation = selectedQuotation;
    }
    
    public List<Quotation> getPendingQuotations(){
        return pendingQuotations = quotationSessionBean.getPendingQuotations();
    }
    
    public List<Quotation> getAcceptedQuotations(){
        return acceptedQuotations = quotationSessionBean.getAcceptedQuotations();
    }
    
    public List<Quotation> getAllQuotations(){
        return allQuotations = quotationSessionBean.getAllQuotations();
    }
    
    public boolean getDisabled(){
        return disabled;
    }

    public List<String> getStatusList() {
    
        return statusList;
    }

    public List<String> getFilteredSuppliers(){
        return filteredSuppliers = quotationSessionBean.getFilteredSuppliers();
    }
    
    public RequestForQuotation getRfq(String id){
        if (id.length() < 1) {
            return rfq;
        }
        
        Long rfqID = Long.parseLong(id);
        
        RequestForQuotation rfq = requestForQuotationSessionBean.getRequestForQuotationsById(rfqID);
        
        this.requestForQuotationId = rfq.getRequestForQuotationId();
        
        return rfq;
    }
    
    public Map<Long, String> getSuppliers(String id) {
        
        if (id.length() < 1) {
            return suppliers;
        }

        Long rfqID = Long.parseLong(id);

        RequestForQuotation rfq = requestForQuotationSessionBean.getRequestForQuotationsById(rfqID);

        Set<Supplier> supplierList = rfq.getSuppliers();

        if (!supplierList.isEmpty()) {
            for (Supplier supp : supplierList) {
                suppliers.put(supp.getSupplierId(), supp.getCompanyName());
            }
        }
        return suppliers;
    }
    //End getter and setter

    public Item getItem(String id) {
        item = new Item();
        if (id.length() < 1) {

            item.setItemName("*Product Not Found*");
        } else {
            Long rfqID = Long.parseLong(id);

            RequestForQuotation rfq = requestForQuotationSessionBean.getRequestForQuotationsById(rfqID);

            if (rfq.getItem() == null) {

                item.setItemName("*Product Not Found*");
            } else {
                item = rfq.getItem();
            }
        }
        return item;
    }

    public void addQuotation() {
        Long itemId;
        
        if(item != null){
            itemId = item.getItemId();
        
            quotationSessionBean.addQuotation(requestForQuotationId,price, deliveryLeadTime,
                validStartDate, validEndDate, purchaseTerms, paymentTerms, deliveryTerms,
                returnTerms, supplierId, itemId);
            
            FacesMessage message = new FacesMessage();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Created successfully", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }
    
    public void acceptQuotation(ActionEvent event){
        FacesMessage message = new FacesMessage();
        
        HttpServletRequest request = (HttpServletRequest)javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        Long userId = Long.parseLong(session.getAttribute("userId").toString());
        if(userId==null){
            LoginController.logout();
        }
        
        if(selectedQuotation!=null){
            Long id = selectedQuotation.getQuotationId();
            try{
                quotationSessionBean.acceptQuotation(id,userId);
            }catch(Exception e){
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Email not sent...", null);
            }
            
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Quotation Accepted...", null);
            
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Something Wrong...", null);
            
        }
        
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void approveQuotation(ActionEvent event){
        FacesMessage message = new FacesMessage();
        
        HttpServletRequest request = (HttpServletRequest)javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        Long userId = Long.parseLong(session.getAttribute("userId").toString());
        if(userId==null){
            LoginController.logout();  
        }
        
        if(selectedQuotation!=null){
            if(userId.equals(selectedQuotation.getAcceptedBy())){
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Cannot Approve Quotation that you Accepted...", null);
            }else{
            
                Long id = selectedQuotation.getQuotationId();
                quotationSessionBean.approveQuotation(id,userId);

                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Quotation Approved...", null);
            }
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Something Wrong...", null);
            
        }
        
        FacesContext.getCurrentInstance().addMessage(null, message);
        
    }
    
    //change select view button
    public void onRowSelect(SelectEvent event) {
        disabled = false;
    }
}
