/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Item;
import entity.ItemType;
import entity.Quotation;
import entity.RequestForQuotation;
import entity.Supplier;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
import org.primefaces.event.SelectEvent;
import session.stateless.ContractSessionBean;
import session.stateless.ItemSessionBean;
import session.stateless.ItemTypeSessionBean;
import session.stateless.RequestForQuotationSessionBean;
import session.stateless.SupplierSessionBean;

/**
 *
 * @author Hongyi
 */

@ManagedBean(name = "RequestForQuotationBean")
@SessionScoped
public class RequestForQuotationBean implements Serializable{

    @EJB
    private ItemTypeSessionBean itemTypeSessionBean;
    @EJB
    private ItemSessionBean itemSessionBean;
    @EJB
    private SupplierSessionBean supplierSessionBean;
    @EJB
    private RequestForQuotationSessionBean requestForQuotationSessionBean;

    //for supplier
    private Map<Long, String> suppliers;// = new HashMap<Long, String>();
    private Supplier supplier;
    private Long supplierId;
    private Long[] selectedSuppliers;
    private String companyName;

    //for item type
    private Map<Long, String> itemTypes = new HashMap<Long, String>();
    private ItemType itemType;
    private Long itemTypeId;
    private String itemTypeDescription;
    //private String storageType;
    //private boolean isPerishable;
    //private String measurementType;
    //private String itemCategory;
    //private String itemSubCategory;
    //private boolean isDelete;

    //for item
    private Map<Long, String> items = new HashMap<Long, String>();
    private Item item;
    private Long itemId;
    private String itemName;
    //private String itemDescription;
    //private boolean approvalStatus;
    //private boolean itemReturnable;
    //private boolean allowSubscription;
    //private int returnablePeriod;
    //private boolean isDelete;

    //for request for quotation
    private Date deadline;
    private int quantity;
    private Date todayDate = new Date();
    private boolean disabled = true;
    private List<RequestForQuotation> filteredRfq;
    private RequestForQuotation selectedRfq;

    //for quotation
    private List<Quotation> quotations;
    @EJB
    private ContractSessionBean contractSessionBean;
    
    @PostConstruct
    public void init() {
        List<ItemType> itemTypeList = new ArrayList<ItemType>();

        itemTypeList = itemTypeSessionBean.getItemTypes();

        for (ItemType type : itemTypeList) {
            itemTypes.put(type.getItemTypeId(), type.getItemTypeDescription());
        }
        
        
    }
    

    public RequestForQuotationBean() {
    }

    public void setSuppliers(Map<Long, String> suppliers) {
        this.suppliers = suppliers;
    }

    public void setItemTypes(Map<Long, String> itemTypes) {
        this.itemTypes = itemTypes;
    }

    public void setItems(Map<Long, String> items) {
        this.items = items;
    }

    public void setTodayDate(Date todayDate) {
        this.todayDate = todayDate;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    


    public Map<Long, String> getSuppliers() {
        return suppliers;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long[] getSelectedSuppliers() {
        return selectedSuppliers;
    }

    public void setSelectedSuppliers(Long[] selectedSuppliers) {
        this.selectedSuppliers = selectedSuppliers;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Map<Long, String> getItemTypes() {
        return itemTypes;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getItemTypeDescription() {
        return itemTypeDescription;
    }

    public void setItemTypeDescription(String itemTypeDescription) {
        this.itemTypeDescription = itemTypeDescription;
    }

    public Map<Long, String> getItems() {
        return items;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Quotation> getQuotations() {
        return quotations;
    }

    public void setQuotations(List<Quotation> quotations) {
        this.quotations = quotations;
    }

    public List<RequestForQuotation> getRequestForQuotations() {
        return requestForQuotationSessionBean.getRequestForQuotations();
    }

    public RequestForQuotationSessionBean getRequestForQuotationSessionBean() {
        return requestForQuotationSessionBean;
    }

    public void setRequestForQuotationSessionBean(RequestForQuotationSessionBean requestForQuotationSessionBean) {
        this.requestForQuotationSessionBean = requestForQuotationSessionBean;
    }

    public RequestForQuotation getSelectedRfq() {
        return selectedRfq;
    }

    public void setSelectedRfq(RequestForQuotation selectedRfq) {
        this.selectedRfq = selectedRfq;
    }

    public List<RequestForQuotation> getFilteredRfq() {
        return filteredRfq;
    }

    public void setFilteredRfq(List<RequestForQuotation> filteredRfq) {
        this.filteredRfq = filteredRfq;
    }

    public boolean isDisabled() {
        return disabled;
    }

    //end getter and setter
    public void handleItemTypeChange() {

        if (itemTypeId != null) {
            suppliers = new HashMap<Long, String>();
            //System.out.println(itemTypeId);
            List<Item> itemList = new ArrayList<Item>();
            itemList = itemSessionBean.getItemsByTypeId(itemTypeId);

            if (!itemList.isEmpty()) {
                items = new HashMap<Long, String>();
                for (Item it : itemList) {
                    items.put(it.getItemId(), it.getItemName());
                }
            }
        }
    }

    public void handleItemChange() {
        if (itemId != null) {
            Item it = new Item();
            it = itemSessionBean.getItemsById(itemId);

            List<Supplier> supplierList = new ArrayList<Supplier>();
            //supplierList = itemSessionBean.getSuppliers(it);
            supplierList = new ArrayList<Supplier>(it.getSuppliers());

            if (!supplierList.isEmpty()) {
                suppliers = new HashMap<Long, String>();
                for (Supplier s : supplierList) {
                    suppliers.put(s.getSupplierId(), s.getCompanyName());
                }
            }

        } else {
            System.out.println("Empty");
        }
    }

    public String addRequestForQuotation() {
        Item temp = itemSessionBean.getItemsById(itemId);

        Set<Supplier> supplierList = new HashSet<Supplier>();

        for (int i = 0; i < selectedSuppliers.length; i++) {
            supplierList.add(supplierSessionBean.getSupplierById(selectedSuppliers[i]));

        }

        RequestForQuotation rfq = requestForQuotationSessionBean.addRequest(supplierList, deadline, quantity, temp);

        FacesMessage message = new FacesMessage();
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Created successfully", null);

        for (Supplier supp : supplierList) {
            try {
                boolean sent = sendEmail(supp,rfq);

                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Created successfully", null);

            } catch (MessagingException ex) {
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Email not Sent", null);

                Logger.getLogger(RequestForQuotationBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "index";
    }

    public boolean sendEmail(Supplier supp, RequestForQuotation rfq) throws MessagingException {
        boolean sent;
        String to = supp.getEmail();
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
            message.setSubject("S-MART - Invitation for Quotation");

            HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            
            String url = req.getRequestURL().toString();
            String root = url.substring(0, url.length() - req.getRequestURI().length()) + req.getContextPath() + "/";
            
            System.out.println(root);
            
            // Send the actual HTML message, as big as you like
            String msg = "<p>Dear " + supp.getCompanyName() + ": </p>";
            msg += "<br/>";
            msg += "<p>Please complete the quotation form for the product.<p>";
            msg += "<br/>";
            msg += "<p><b>Quotation Form Link: </b> </p>";
            msg += "<a>" + root + "PurchasingModule/quotationForm.xhtml?id=" + rfq.getRequestForQuotationId() + "</a>";
            msg += "<br/>";
            msg += "<br/>";
            msg += "<p>If you have any queries, please contact us!</p>";
            msg += "<br/>";
            msg += "<br/>";
            msg += "<p><i>Best Regard,<i></p>";
            msg += "<p><b>S-Mart Procurement Team</b></p>";

            message.setContent(msg, "text/html");

            // Send message
            trans.send(message);
            System.out.println("Sent message successfully....");
            sent = true;

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Email Sent...",
                            ""));

        } catch (MessagingException mex) {
            mex.printStackTrace();
            sent = false;
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Error: ",
                            "Not able to send email..."));
        }

        return sent;
    }


    public Date getTodayDate() {
        return todayDate;
    }

    //change select view button
    public void onRowSelect(SelectEvent event) {
        disabled = false;
    }
}
