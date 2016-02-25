package session.stateless;

import entity.Role;
import entity.StaffAccount;
import entity.Privilege;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class StaffAccountSessionBean {


    @PersistenceContext
    private EntityManager entityManager;

    public List<Privilege> getAllPrivileges() {
        Query query = entityManager.createQuery("SELECT p FROM Privilege p");
        return query.getResultList();
    }
    
    public List<StaffAccount> getAllStaffAccounts(){
        Query query = entityManager.createQuery("SELECT s FROM StaffAccount s");
        return query.getResultList();
    }

    public List<String> getAllPrivilegeNames() {
        Query query = entityManager.createQuery("SELECT p.privilegeName FROM Privilege p");
        return query.getResultList();
    }
    
        public List<String> getAllRoleNames() {
        Query query = entityManager.createQuery("SELECT r.roleName FROM Role r");
        return query.getResultList();
    }

    public String getPrivilegeName(Long privilegeId) {
        Privilege privilege = entityManager.find(Privilege.class, privilegeId);
        String privilegeName = privilege.getPrivilegeName();
        return privilegeName;
    }

    private Privilege getPrivilege(Long privilegeId) {
        Privilege privilege = entityManager.find(Privilege.class, privilegeId);
        return privilege;
    }

    // staffAccountId can be changed to staffAccountName for easier search
    public StaffAccount getStaffAccount(Long staffAccountId) {
        StaffAccount staffAccount = entityManager.find(StaffAccount.class,
                staffAccountId);
        return staffAccount;
    }

    public List<Role> getAllRoles() {
        Query query = entityManager.createQuery("SELECT r FROM Role r");
        return query.getResultList();
    }

    
    public List<Role> getRole(String name){
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.roleName LIKE :rolename").setParameter("rolename", name).getResultList();
       
    }

    public Role getMyRole(Long staffAccountId) {
        StaffAccount staffAccount = getStaffAccount(staffAccountId);
        return staffAccount.getRole();
    }

    public Long addNewRole(String roleName, boolean value1, boolean value2, boolean value3, boolean value4, boolean value5, boolean value6, boolean value7, boolean value8,
            boolean value9, boolean value10, boolean value11, boolean value12, boolean value13, boolean value14, boolean value15, boolean value16, boolean value17, boolean value18, boolean value19, boolean value20,
            boolean value21, boolean value22, boolean value23, boolean value24, boolean value25, boolean value26, boolean value27) {
        Role role = new Role();
        role.setRoleName(roleName);
        entityManager.persist(role);
        entityManager.flush();
        return role.getRoleId();
    }

    public void deleteRole(Role seletedRole) {
        Role role;
        role = entityManager.find(Role.class, seletedRole.getRoleId());
        entityManager.remove(role);
    }

    public Long addNewStaffAccount(String email, String staffAccountName, String contactNumber, String name) {
        StaffAccount staffAccount = new StaffAccount();
        
        String password = "password";
        String passwordMD5 = "";
                try { 
                    MessageDigest md5 = MessageDigest.getInstance("MD5");
                    byte[] tmp = password.getBytes();
                    md5.update(tmp);
                    passwordMD5 = byteArrToString(md5.digest());
                }
                catch (NoSuchAlgorithmException ex) {  
                }
            
        staffAccount.setPassword(passwordMD5);
        staffAccount.setEmail(email);
        staffAccount.setStaffAccountName(staffAccountName);
        staffAccount.setContactNumber(contactNumber);
        staffAccount.setRole(getRole(name).get(0));
        entityManager.persist(staffAccount);
        entityManager.flush();
        return staffAccount.getStaffAccountId();
    }
        public void deleteStaffAccount(StaffAccount selectedStaffAccount) {
        StaffAccount staffAccount;
        staffAccount = entityManager.find(StaffAccount.class, selectedStaffAccount.getStaffAccountId());
        entityManager.remove(staffAccount);
    }
        
        private static String byteArrToString(byte[] b) {
        String res = null; 
        StringBuffer sb = new StringBuffer(b.length * 2); 
        for (int i = 0; i < b.length; i++){
            int j = b[i] & 0xff; 
            if (j < 16) { 
               sb.append('0'); 
            } 
            sb.append(Integer.toHexString(j)); 
        } 
        res = sb.toString();
        return res.toUpperCase(); 
    }
        
        public String resetPassword(Long staffAccountId) {
        StaffAccount staffAccount = entityManager.find(StaffAccount.class, staffAccountId);
        //SecureRandom random = new SecureRandom();
        String password = UUID.randomUUID().toString().substring(0, 8);
        String passwordMD5 = "";
            try { 
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                byte[] tmp = password.getBytes();
                md5.update(tmp);
                passwordMD5 = byteArrToString(md5.digest());
            }
            catch (NoSuchAlgorithmException ex) {  
            }
            
        
        System.out.println("New Password =" + password);        
        staffAccount.setPassword(passwordMD5);
        entityManager.persist(staffAccount);
        entityManager.flush();
        return password;
    }

    public void setContactNumber(Long staffAccountId, String contactNumber) {
        System.out.println(staffAccountId);
        StaffAccount staffAccount = entityManager.find(StaffAccount.class, staffAccountId);
        staffAccount.setContactNumber(contactNumber);
        System.out.println(contactNumber);
        entityManager.persist(staffAccount);
        entityManager.flush();
    }
    
    public void changePassword(String newPassword, Long accountId) {
        StaffAccount staffAccount = entityManager.find(StaffAccount.class, accountId);
        String passwordMD5 = "";
            try { 
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                byte[] tmp = newPassword.getBytes();
                md5.update(tmp);
                passwordMD5 = byteArrToString(md5.digest());
            }
            catch (NoSuchAlgorithmException ex) {  
            }
            staffAccount.setPassword(passwordMD5);
            entityManager.persist(staffAccount);
            entityManager.flush();
    }
    
        
        
        
    
    
}
