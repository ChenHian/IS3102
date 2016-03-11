/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Item;
import entity.Role;
import entity.StaffAccount;
import java.sql.ResultSet;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Chen Hian
 */
@Stateless
@LocalBean
public class AccessControlSessionBean implements AccessControlSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;
    
    
    
    public String getUserRole(String user) {
        try {
            Query q = em.createQuery("SELECT s FROM StaffAccount s WHERE s.email ='" + user + "'");        
            if(!q.getResultList().isEmpty()){
            StaffAccount s = (StaffAccount) q.getSingleResult();
            return s.getRole().getRoleName();
        }
           
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Role Not Found";
    }

    public String getUserName(String email) {
        try {
            Query q = em.createQuery("SELECT s FROM StaffAccount s WHERE s.email ='" + email + "'");        
            if(!q.getResultList().isEmpty()){
            StaffAccount s = (StaffAccount) q.getSingleResult();
            return s.getStaffAccountName();
            }
                    
           
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Error finding name, please contact your system administrator";
    }
    
    public boolean userAccountManagementModuleAccess(String email) {

        long roleid = 0;
         Query q = em.createQuery("SELECT s FROM StaffAccount s WHERE s.email ='" + email + "'");        
            if(!q.getResultList().isEmpty()){
            StaffAccount s = (StaffAccount) q.getSingleResult();
            return s.getRole().isPrivilege1();
            }
           
            else {
                System.out.println("No RS result");
            }
        return false;
    }
    
    public boolean userAccessControlModuleAccess(String email) {

        long roleid = 0;
         Query q = em.createQuery("SELECT s FROM StaffAccount s WHERE s.email ='" + email + "'");        
            if(!q.getResultList().isEmpty()){
            StaffAccount s = (StaffAccount) q.getSingleResult();
            return s.getRole().isPrivilege2();
            }
           
            else {
                System.out.println("No RS result");
            }
        return false;
    }
    
    public boolean accountAdministrationModuleAccess(String email) {

        long roleid = 0;
         Query q = em.createQuery("SELECT s FROM StaffAccount s WHERE s.email ='" + email + "'");        
            if(!q.getResultList().isEmpty()){
            StaffAccount s = (StaffAccount) q.getSingleResult();
            return s.getRole().isPrivilege3();
            }
           
            else {
                System.out.println("No RS result");
            }
        return false;
    }
    
    public boolean supplierManagementModuleAccess(String email) {

        long roleid = 0;
         Query q = em.createQuery("SELECT s FROM StaffAccount s WHERE s.email ='" + email + "'");        
            if(!q.getResultList().isEmpty()){
            StaffAccount s = (StaffAccount) q.getSingleResult();
            return s.getRole().isPrivilege6();
            }
           
            else {
                System.out.println("No RS result");
            }
        return false;
    }
    
    public boolean purchasingRequestModuleAccess(String email) {

        long roleid = 0;
         Query q = em.createQuery("SELECT s FROM StaffAccount s WHERE s.email ='" + email + "'");        
            if(!q.getResultList().isEmpty()){
            StaffAccount s = (StaffAccount) q.getSingleResult();
            return s.getRole().isPrivilege7();
            }
           
            else {
                System.out.println("No RS result");
            }
        return false;
    }
    
    public boolean purchasingModuleAccess(String email) {
        Query q = em.createQuery("SELECT s FROM StaffAccount s WHERE s.email ='" + email + "'");        
            if(!q.getResultList().isEmpty()){
            StaffAccount s = (StaffAccount) q.getSingleResult();
            return s.getRole().isPrivilege8();
            }
           
            else {
                System.out.println("No RS result");
            }
        return false;
    }

    public boolean purchasingContractModuleAccess(String email) {
        Query q = em.createQuery("SELECT s FROM StaffAccount s WHERE s.email ='" + email + "'");        
            if(!q.getResultList().isEmpty()){
            StaffAccount s = (StaffAccount) q.getSingleResult();
            return s.getRole().isPrivilege9();
            }
           
            else {
                System.out.println("No RS result");
            }
        return false;
    }
    
    public boolean itemManagementModuleAccess(String email) {
        Query q = em.createQuery("SELECT s FROM StaffAccount s WHERE s.email ='" + email + "'");        
            if(!q.getResultList().isEmpty()){
            StaffAccount s = (StaffAccount) q.getSingleResult();
            return s.getRole().isPrivilege10();
            }
           
            else {
                System.out.println("No RS result");
            }
        return false;
    }
    
    public boolean purchasingIssueModuleAccess(String email) {
        Query q = em.createQuery("SELECT s FROM StaffAccount s WHERE s.email ='" + email + "'");        
            if(!q.getResultList().isEmpty()){
            StaffAccount s = (StaffAccount) q.getSingleResult();
            return s.getRole().isPrivilege11();
            }
           
            else {
                System.out.println("No RS result");
            }
        return false;
    }
    
    public boolean notificationModuleAccess(String email) {
        Query q = em.createQuery("SELECT s FROM StaffAccount s WHERE s.email ='" + email + "'");        
            if(!q.getResultList().isEmpty()){
            StaffAccount s = (StaffAccount) q.getSingleResult();
            return s.getRole().isPrivilege12();
            }
           
            else {
                System.out.println("No RS result");
            }
        return false;
    }
    
    public boolean warehouseInventoryModuleAccess(String email) {
        Query q = em.createQuery("SELECT s FROM StaffAccount s WHERE s.email ='" + email + "'");        
            if(!q.getResultList().isEmpty()){
            StaffAccount s = (StaffAccount) q.getSingleResult();
            return s.getRole().isPrivilege13();
            }
           
            else {
                System.out.println("No RS result");
            }
        return false;
    }
    
    public boolean purchaseManagementModuleAccess(String email) {
        Query q = em.createQuery("SELECT s FROM StaffAccount s WHERE s.email ='" + email + "'");        
            if(!q.getResultList().isEmpty()){
            StaffAccount s = (StaffAccount) q.getSingleResult();
            return s.getRole().isPrivilege14();
            }
           
            else {
                System.out.println("No RS result");
            }
        return false;
    }
    
    public boolean deliveryManagementModuleAccess(String email) {
        Query q = em.createQuery("SELECT s FROM StaffAccount s WHERE s.email ='" + email + "'");        
            if(!q.getResultList().isEmpty()){
            StaffAccount s = (StaffAccount) q.getSingleResult();
            return s.getRole().isPrivilege15();
            }
           
            else {
                System.out.println("No RS result");
            }
        return false;
    }
    
    

    
    
    
}


