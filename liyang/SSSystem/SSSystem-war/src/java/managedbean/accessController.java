/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import loginAuthentication.Database;



/**
 *
 * @author Chen Hian
 */
@ManagedBean
public class accessController {
    private String userRole; 
    private boolean privilege;

    @PersistenceContext(unitName = "CommonInfrastructure-ejbPU")
    private EntityManager entityManager;
    
    
    public boolean userPrivilege4(int roleid) {
        Role role = entityManager.find(Role.class, roleid) ; //To change body of generated methods, choose Tools | Templates.
        System.out.println("Privilege 4: " + role.isPrivilege4());
        return role.isPrivilege4();
    }
    

    
    public boolean getAdminAccess() {
        String user = "temp user";
        System.out.println(user);
        HttpSession session = Util.getSession();
        user= (String)session.getAttribute("username");
        System.out.println("Session user = " + user);
        Connection con = null;
        PreparedStatement ps = null;
        
        long roleid = 0;
        try {
            System.out.println("Making connection");
            con = Database.getConnection();
            System.out.println("Connection made: "+ con.getCatalog());
            ps = con.prepareStatement(
                    "SELECT role_roleid FROM staffaccount WHERE email= ?");
            ps.setString(1, user);
            //ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            System.out.println("After Query");
            
            if(rs.next()) {
                //System.out.println("Result found");
                //System.out.println("accessController: Roleid getString" + rs.getString("ROLE_ROLEID"));
                roleid = Long.parseLong(rs.getString("ROLE_ROLEID"));
                System.out.println("roleId = "+ roleid);
            }
            else {
                System.out.println("No RS result");
            }

            System.out.println("Privilege 4:" + entityManager.find(Role.class, roleid).isPrivilege4());
            return entityManager.find(Role.class, roleid).isPrivilege4();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean procurementAccess() {
        HttpSession session = Util.getSession();
        String user= (String)session.getAttribute("username");
        Connection con = null;
        PreparedStatement ps = null;
        long roleid = 0;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "SELECT ROLE_ROLEID FROM staffaccount WHERE email= ?");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                System.out.println(rs.getString("ROLE_ROLEID"));
                roleid = Long.parseLong(rs.getString("ROLE_ROLEID"));
            }
            //Role userRole = entityManager.find(Role.class,roleid);
            //System.out.println(userRole.isPrivilege1());
            //return userRole.isPrivilege1(); //Select according to privilege
            System.out.println("Privilege 4:" + entityManager.find(Role.class, roleid).isPrivilege4());
            return entityManager.find(Role.class, roleid).isPrivilege1();
        }
        catch (Exception ex) {
        }
        return false;
    }
    }

