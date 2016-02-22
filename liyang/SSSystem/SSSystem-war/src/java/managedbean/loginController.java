package managedbean;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import loginAuthentication.authentication;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.SessionScoped;

import auditLog.Audit;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import loginAuthentication.Database;
import org.primefaces.context.RequestContext;
 
@ManagedBean
@SessionScoped
public class loginController implements Serializable{
     
    private String username;
     
    private String password;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
   
    public String login() {
        String home = "home";
        //System.out.println("User: " + username + "_Password: " + password);
        boolean result = authentication.login(username, password);
        if (result) {
            // get Http Session and store username
            System.out.println("Authenticated");
            HttpSession session = Util.getSession();
            session.setAttribute("username", username);
            Connection con = null;
        PreparedStatement ps = null;
        //System.out.print(password);
        try {
            con = Database.getConnection();
            //System.out.println("authentication "+ con.getCatalog());
            ps = con.prepareStatement(
                    "SELECT staffAccountId FROM staffaccount WHERE email= ?");
            ps.setString(1, username);
            

            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { //found
                //System.out.println(rs.getString("email"));
                session.setAttribute("userId", rs.getString("staffAccountId"));
                System.out.println(rs.getString("staffAccountId"));
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
          
        Audit au = new Audit();
        @SuppressWarnings("unchecked")                     
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        String adt=username + " logged in @ " + dateFormat.format(date); // audit message to display

        try{
        au.auditIt(adt); //pass the message to variable
        }

        catch(Exception ex)
        {}
        return "home";
            
        } else {
 
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login!",
                    "Please Try Again!"));
 
            // invalidate session, and redirect to other pages
 
            //message = "Invalid Login. Please Try Again!";
            return "login";
        }
    }
    public static String logout() {
      HttpSession session = Util.getSession();
      Audit au =new Audit();
        @SuppressWarnings("unchecked")                     
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        String adt=session.getAttribute("username") + " logged out @ " + dateFormat.format(date); // audit message to display
        System.out.println(adt);

        try{
        au.auditIt(adt); //pass the message to variable
        }

        catch(Exception ex)
        {}
      session.invalidate();
      
      return "logout";
   }
}