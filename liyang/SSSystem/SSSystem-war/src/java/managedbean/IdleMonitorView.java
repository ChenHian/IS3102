/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import auditLog.Audit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
@ManagedBean
public class IdleMonitorView {
     
    public void onIdle() {
        HttpSession session = Util.getSession();
        Audit au =new Audit();
        @SuppressWarnings("unchecked")                     
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        String adt=session.getAttribute("username") + " timeout @ " + dateFormat.format(date); // audit message to display
        System.out.println(adt);

        try{
        au.auditIt(adt); //pass the message to variable
        }

        catch(Exception ex)
        {}
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Your session is closed", "You have been idle for at least 15 minutes"));

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.getSession().invalidate();
            try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("timeout.xhtml");
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
    }
 
    public void onActive() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        "Welcome Back", "Well, that's a long coffee break!"));
    }
}