package session.stateless;

import entity.Contract;
import entity.DistributionCenter;
import entity.PurchaseOrder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entity.Item;
import entity.PurchaseRequisition;
import entity.Supplier;

@Stateless
@LocalBean
public class PRSessionBean implements PRSessionBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;
    
    private NotificationSessionBean notificationSessionBean;

    public PRSessionBean() {
    }

    public Map<String, String> getAllItemName() {
        Map<String, String> ItemNameList = new LinkedHashMap<String, String>();

        Query q = entityManager.createQuery("SELECT it FROM Item it WHERE it.isDelete= false");
        List<Object> qResultList = q.getResultList();
        Object obj;

        for (int i = 0; i < qResultList.size(); i++) {
            obj = qResultList.get(i);
            Item it = (Item) obj;

            ItemNameList.put(it.getItemName(), it.getItemName());
        }
        return ItemNameList;
    }

    public Item findItem(String itemName) {
        Item item = new Item();
        Query q = entityManager.createQuery("SELECT t FROM Item AS t WHERE t.itemName=:itemName");
        q.setParameter("itemName", itemName);
        item = (Item) q.getSingleResult();
        return item;
    }
    
    
    public Long createPR(java.util.Date dateCreated2, java.util.Date dateRequest2, String selectedCenterName, String status, Integer quantityRequested, String itemName) {
        PurchaseRequisition pr = new PurchaseRequisition();

        java.sql.Date dateCreated = new java.sql.Date(dateCreated2.getTime());
        java.sql.Date dateRequest = new java.sql.Date(dateRequest2.getTime());

        pr.setDateCreated(dateCreated);
        pr.setDateRequest(dateRequest);
        pr.setCenterName(selectedCenterName);
        pr.setQuantityRequested(quantityRequested);
        pr.setStatus(status);

        Query q = entityManager.createQuery("SELECT t FROM Item AS t WHERE t.itemName=:itemName");
        q.setParameter("itemName", itemName);

        if (!q.getResultList().isEmpty()) {
            Object obj = q.getSingleResult();
            Item item = (Item) obj;

            pr.setItem(item);
            //System.out.println(item.getItemId());
            //System.out.println(item.getItemName());
        }
        entityManager.persist(pr);
        
        Date dateDummy = new Date();
            java.sql.Date dateD = new java.sql.Date(dateDummy.getTime());
        Query newQ = entityManager.createQuery("SELECT c FROM Contract c WHERE c.item.itemName = :itemName AND c.automaticPurchase = true AND c.contractEndDate >= :currentDate AND c.contractStartDate<=:currentDate");
            newQ.setParameter("itemName", itemName);
            newQ.setParameter("currentDate", dateD);
            
        
        if(!newQ.getResultList().isEmpty()){
            Object obj = newQ.getSingleResult() ;
            Contract c = (Contract) obj;
            
            Query newQ2 = entityManager.createQuery("SELECT dc FROM DistributionCenter dc WHERE dc.address");
            obj = newQ2.getSingleResult() ;
            DistributionCenter dc = (DistributionCenter) obj;
            createPurchaseOrderC(pr.getPurchaseRequisitionId(), selectedCenterName, dc.getAddress(), quantityRequested, dateRequest2, "Contract", c.getContractId(), itemName);
        }
        
        return pr.getPurchaseRequisitionId();
    }

   

    public List<PurchaseRequisition> getAllPRs() {
        Query q = entityManager.createQuery("SELECT p FROM PurchaseRequisition p");
        return q.getResultList();
    }
    
    public String getSelectedCenterAddress(String selectedCenterName){
        if(selectedCenterName.substring(0, 2).equals("DC")){
            System.out.println("selectedCenterName = " + selectedCenterName);
            Query q = entityManager.createQuery("SELECT dc from DistributionCenter dc WHERE dc.name = :dcName");
            q.setParameter("dcName", selectedCenterName);
            if(!q.getResultList().isEmpty()){
                Object o = q.getSingleResult();
                DistributionCenter dc = (DistributionCenter) o;
                System.out.println("DCAddress = " + dc.getAddress());
                return dc.getAddress();
                
            }
         
        
        }
        else{
            System.out.println("Store center detected");
        
        }
            
        return "";
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
                
                /*FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Email Sent...",
                                "Please check your email for new password..."));
                        */

            } catch (MessagingException mex) {
                System.out.println("Error in sending purchase order email to supplier!");
                mex.printStackTrace();
                /*FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Error: ",
                                "Not able to send email..."));
                */
            }
        } else {
            System.out.println("Error in sending purchase order to supplier!");
            /*FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Error: ",
                            "Not able to save email..."));
            */
        }
    }
     
     public PurchaseOrder createPurchaseOrderC(long selectedPurchaseRequisitionId, String selectedCenterName, String selectedCenterAddress, int selectedQuantity, Date expectedDeliveryDate, String referenceType, long selectedReferenceId, String itemName){
        System.out.println("test");
        //ystem.out.println("selectedItemId = " + itemId);
      //  System.out.println("selectedItemId = " + selectedItemId);
        PurchaseOrder p = new PurchaseOrder();
        PurchaseRequisition pr = new PurchaseRequisition();
        Item i = new Item();
        Supplier s = new Supplier();
        
        Date dateDummy = new Date();
        java.sql.Date date= new java.sql.Date(dateDummy.getTime());
        p.setDateCreated(date);
        date= new java.sql.Date(expectedDeliveryDate.getTime());
        p.setExpectedDeliveryDate(date);
        p.setDeliverToCentreName(selectedCenterName);
        p.setDeliverToAddress(selectedCenterAddress);
        p.setDocumentReferenceType(referenceType);
        p.setDocumentReferenceNumber(selectedQuantity);
        p.setQuantity(selectedQuantity);
        p.setStatus("Open");
        
        Query q = entityManager.createQuery("SELECT i from Item i WHERE i.itemName = :itemName");
        q.setParameter("itemName", itemName);
        if(!q.getResultList().isEmpty()){
                Object o = q.getSingleResult();
                i = (Item) o;
                p.setItem(i);
                
        }
        
        q = entityManager.createQuery("SELECT c from Contract c WHERE c.contractId = :contractId");
        q.setParameter("contractId", selectedReferenceId);
        if(!q.getResultList().isEmpty()){
                Object o = q.getSingleResult();
                Contract c = (Contract) o;
                p.setUnitPrice(c.getPrice());
                p.setTotalAmount(c.getPrice() * selectedQuantity);
                
                Query q2 = entityManager.createQuery("SELECT s from Supplier s WHERE s.supplierId = :supplierId");
                q2.setParameter("supplierId", c.getSupplier().getSupplierId());
                if(!q2.getResultList().isEmpty()){
                    Object obj = q2.getSingleResult();
                    s = (Supplier) obj;   
                    p.setSupplier(s);
                }
        }
        
        q = entityManager.createQuery("SELECT max(po.purchaseOrderId) FROM PurchaseOrder po");
            if(!q.getResultList().isEmpty()){
                Object obj = q.getSingleResult();
                long maxPurchaseOrderId = 0;
                if(obj != null){
                    maxPurchaseOrderId = (Long) obj;
                    p.setPurchaseOrderId(maxPurchaseOrderId + 1);
                }
                else{
                    p.setPurchaseOrderId(Long.valueOf(1));
                }
        }
            
        q = entityManager.createQuery("SELECT pr FROM PurchaseRequisition pr WHERE pr.purchaseRequisitionId = :purchaseRequisitionId");
        q.setParameter("purchaseRequisitionId", selectedPurchaseRequisitionId);
        if(!q.getResultList().isEmpty()){
                Object o = q.getSingleResult();
                pr = (PurchaseRequisition) o;
                p.setPurchaseRequisition(pr);
                pr.setStatus("Order Placed");
                
        }
        
        entityManager.merge(p);
        pr.setPurchaseOrder(p);
        entityManager.merge(pr);
        s.getPurchaseOrders().add(p);
        entityManager.merge(s);
        i.getPurchaseOrder().add(p);
        entityManager.merge(i);
        
                notificationSessionBean.createNotification("WAREHOUSE DIVISION","PURCHASING DIVISION","Normal","A purchase order has been made for " +p.getDeliverToCentreName() + ". Item ordered: " + i.getItemName());
        
        return p;
    }
}
