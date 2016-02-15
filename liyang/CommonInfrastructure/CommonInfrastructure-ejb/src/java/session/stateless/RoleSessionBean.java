package session.stateless;

import entity.Role;
import entity.StaffAccount;
import entity.Privilege;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class RoleSessionBean {

    @PersistenceContext
    private EntityManager entityManager;
    private String listOfPrivileges;

    public List<Privilege> getAllPrivileges() {
        Query query = entityManager.createQuery("SELECT p FROM Privilege p");
        return query.getResultList();
    }
    
    public List<String> getAllPrivilegeNames() {
        Query query = entityManager.createQuery("SELECT p.privilegeName FROM Privilege p");
        return query.getResultList();
    }

    public String getPrivilegeName(Long privilegeId){
        Privilege privilege = entityManager.find(Privilege.class, privilegeId);
        String privilegeName = privilege.getPrivilegeName();
        return privilegeName;
    }
    
    private Privilege getPrivilege(Long privilegeId) {
        Privilege privilege = entityManager.find(Privilege.class, privilegeId);
        return privilege;
    }
   
    // staffAccountId can be changed to staffAccountName for easier search
    private StaffAccount getStaffAccount(Long staffAccountId) {
        StaffAccount staffAccount = entityManager.find(StaffAccount.class,
                staffAccountId);
        return staffAccount;
    }
    public List<Role> getAllRoles() {
        Query query = entityManager.createQuery("SELECT r FROM Role r");
        return query.getResultList();
    }

    public Role getMyRole(Long staffAccountId) {
        StaffAccount staffAccount = getStaffAccount(staffAccountId);
        return staffAccount.getRole();
    }

    public Long addNewRole(String roleName, boolean value1, boolean value2, boolean value3)  {
            Role role = new Role();
            listOfPrivileges = "- ";
            role.setRoleName(roleName);
            role.setPrivilege1(value1);
            role.setPrivilege2(value2);
            role.setPrivilege3(value3);
            if (value1) listOfPrivileges=listOfPrivileges.concat("Supplies Management Module. ");
            if (value2) listOfPrivileges=listOfPrivileges.concat("Transaction Management Module. ");
            if (value3) listOfPrivileges=listOfPrivileges.concat("Customer Management Module. ");
            role.setListOfPrivileges(listOfPrivileges);
            entityManager.persist(role);
            entityManager.flush();
            return role.getRoleId();
        } 
}
