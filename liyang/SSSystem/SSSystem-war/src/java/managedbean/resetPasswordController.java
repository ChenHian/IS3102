package managedbean;
 
import entity.StaffAccount;
import javax.faces.bean.ManagedBean;
import loginAuthentication.authentication;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.bean.SessionScoped;

import java.util.Properties;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
import javax.mail.internet.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import loginAuthentication.Database;
import session.stateless.StaffAccountSessionBean;
 
@ManagedBean
@SessionScoped
public class resetPasswordController implements Serializable{
     
    private String username;
    
    @EJB
    private StaffAccountSessionBean staffAccountSessionBean;
     

 
    public String getusername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
   
    public String resetPassword() throws MessagingException {

        Connection con = null;
        PreparedStatement ps = null;
        //System.out.print(password);
        try {
            con = Database.getConnection();
            //System.out.println("authentication "+ con.getCatalog());
            ps = con.prepareStatement(
                    "SELECT email, staffAccountId FROM staffaccount WHERE email= ?");
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { //found
                System.out.println("email:" + rs.getString("email"));
                System.out.println("id" + rs.getString("staffAccountId"));
                Long staffAccountId = Long.parseLong(rs.getString("staffAccountId"));
                //System.out.println("Privilege 7:" + entityManager.find(Role.class, roleid).isPrivilege7());
                //System.out.println(entityManager.find(StaffAccount.class, staffAccountId).getEmail());
                String newPassword = staffAccountSessionBean.resetPassword(staffAccountId);
                
                if(!newPassword.equals("Email not found")) {
                    
                    
                    
        //send email tbd
        String to = this.username;
        final String from = "SMartSystemPT02@hotmail.com";
        //String host = "localhost";
        final String password  = "SMartPT02";
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
        trans.connect("smtp.live.com",587,"SMartSystemPT02@hotmail.com","SMartPT02");
        try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("Password Reset");

         // Send the actual HTML message, as big as you like
         message.setContent("<h1>Your new password is " + newPassword + "</h1>", "text/html" );

         // Send message
         trans.send(message);
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
        }
    }
        
    else {
        FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "User was not found in database.",
                    "Please check that you have entered a valid username."));
 
            // invalidate session, and redirect to other pages
 
            //message = "Invalid Login. Please Try Again!";
            return "failure";
    }
    
    }
    }
        
        catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            ex.printStackTrace();
        }
        return "success";
    }

    private PasswordAuthentication getPasswordAuthentication() {
        String username = "SMartSystemPT02@hotmail.com";
 
    String password = "SMartPT02";
    return new PasswordAuthentication(username, password);
    }

        
    public class SmtpAuthenticator extends Authenticator {
        public SmtpAuthenticator() {
        super();
}

}
}