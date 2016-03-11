/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Item;
import entity.Quotation;
import entity.RequestForQuotation;
import entity.Supplier;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

/**
 *
 * @author Hongyi
 */
@Stateless
@LocalBean
public class QuotationSessionBean implements QuotationSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;

    public QuotationSessionBean() {
    }

    public List<Quotation> getAllQuotations() {
        Query q = em.createQuery("SELECT q FROM Quotation q");

        return q.getResultList();
    }

    public List<Quotation> getPendingQuotations() {
        Query q = em.createQuery("SELECT q FROM Quotation q WHERE q.status = 'Pending'");

        return q.getResultList();
    }

    public List<Quotation> getAcceptedQuotations() {
        Query q = em.createQuery("SELECT q FROM Quotation q WHERE q.status = 'Accepted'");

        return q.getResultList();
    }

    public void addQuotation(Long rfqId, Double price, int deliveryLeadTime, java.util.Date validStartDate, java.util.Date validEndDate, String purchaseTerms, String paymentTerms, String deliveryTerms, String returnTerms, Long supplierId, Long itemId) {
        Quotation quotation = new Quotation();

        Supplier supp = em.find(Supplier.class, supplierId);
        Item item = em.find(Item.class, itemId);
        java.util.Date today = new java.util.Date();

        quotation.setRequestForQuotationId(rfqId);
        quotation.setPrice(price);
        quotation.setDeliveryLeadTime(deliveryLeadTime);
        quotation.setValidStartDate(new java.sql.Date(validStartDate.getTime()));
        quotation.setValidEndDate(new java.sql.Date(validEndDate.getTime()));
        quotation.setPurchaseTerms(purchaseTerms);
        quotation.setPaymentTerms(paymentTerms);
        quotation.setDeliveryTerms(deliveryTerms);
        quotation.setReturnTerms(returnTerms);
        quotation.setItem(item);
        quotation.setSupplier(supp);
        quotation.setStatus("Pending");
        quotation.setDateSubmitted(new java.sql.Date(today.getTime()));

        em.persist(quotation);
        em.flush();
    }

    public List<String> getFilteredSuppliers() {

        List<String> suppList = new ArrayList<String>();

        Query q = em.createQuery("SELECT s FROM Supplier s");

        for (Object o : q.getResultList()) {
            Supplier supp = (Supplier) o;
            suppList.add(supp.getCompanyName());
        }
        return suppList;
    }

    public List<Quotation> getQuotationsByRfqId(Long id) {
        List<Quotation> quotationList = new ArrayList<Quotation>();

        Query q = em.createQuery("SELECT q FROM Quotation q WHERE q.requestForQuotationId = :rfqId");
        q.setParameter("rfqId", id);

        if (!q.getResultList().isEmpty()) {
            for (Object o : q.getResultList()) {
                Quotation quotation = (Quotation) o;

                quotationList.add(quotation);
            }
        }

        return quotationList;
    }

    public void acceptQuotation(Long id, Long userId) throws MessagingException {
        Quotation quotation = em.find(Quotation.class, id);

        String msg = "";
        Long rfqId = quotation.getRequestForQuotationId();

        //update selected quotation
        quotation.setStatus("Accepted");
        quotation.setAcceptedBy(userId);
        em.persist(quotation);
        em.flush();
        boolean sent = sendEmail(quotation.getSupplier(), quotation, true);

        List<Quotation> quotationList = getQuotationsByRfqId(rfqId);
        for (Quotation q : quotationList) {
            if (!q.equals(quotation)) {
                q.setStatus("Declined");
                em.persist(quotation);
                em.flush();
                sent = sendEmail(q.getSupplier(), q, false);
            }
        }

        //update request for quotation
        RequestForQuotation rfq = em.find(RequestForQuotation.class, rfqId);

        rfq.setStatus("Completed");
        em.persist(rfq);
        em.flush();

    }

    public void approveQuotation(Long id, Long userId) {
        Quotation quotation = em.find(Quotation.class, id);

        //update selected quotation
        quotation.setStatus("Approved");
        quotation.setApprovedBy(userId);
        em.persist(quotation);
        em.flush();
    }

    public boolean sendEmail(Supplier supp, Quotation quotation, boolean result) throws MessagingException {
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
            message.setSubject("S-MART - Quotation Result for " + quotation.getItem().getItemName());

            String msg = "<p>Dear " + supp.getCompanyName() + ": </p>";
            msg += "<br/>";

            if (result) {
                msg += "<p>We are pleased to inform you that the quotation for " + quotation.getItem().getItemName() + " had been accepted!<p>";
            } else {
                msg += "<p>We regret to inform you that the quotation for " + quotation.getItem().getItemName() + " had been declined!<p>";
            }
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
}
