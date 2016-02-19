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

    public Long addNewRole(String roleName, boolean value1, boolean value2, boolean value3, boolean value4, boolean value5, boolean value6, boolean value7, boolean value8,
            boolean value9, boolean value10, boolean value11, boolean value12, boolean value13, boolean value14, boolean value15, boolean value16, boolean value17, boolean value18, boolean value19, boolean value20,
            boolean value21, boolean value22, boolean value23, boolean value24, boolean value25, boolean value26, boolean value27) {
        Role role = new Role();
        listOfPrivileges = "- ";
        role.setRoleName(roleName);
        role.setPrivilege1(value1);
        role.setPrivilege2(value2);
        role.setPrivilege3(value3);
        role.setPrivilege4(value4);
        role.setPrivilege5(value5);
        role.setPrivilege6(value6);
        role.setPrivilege7(value7);
        role.setPrivilege8(value8);
        role.setPrivilege9(value9);
        role.setPrivilege10(value10);
        role.setPrivilege11(value11);
        role.setPrivilege12(value12);
        role.setPrivilege13(value13);
        role.setPrivilege14(value14);
        role.setPrivilege15(value15);
        role.setPrivilege16(value16);
        role.setPrivilege17(value17);
        role.setPrivilege18(value18);
        role.setPrivilege19(value19);
        role.setPrivilege20(value20);
        role.setPrivilege21(value21);
        role.setPrivilege2(value22);
        role.setPrivilege23(value23);
        role.setPrivilege24(value24);
        role.setPrivilege25(value25);
        role.setPrivilege26(value26);
        role.setPrivilege27(value27);
        if (value1) {
            listOfPrivileges = listOfPrivileges.concat("User Account Management Module. ");
        }
        if (value2) {
            listOfPrivileges = listOfPrivileges.concat("User Access Control Module. ");
        }
        if (value3) {
            listOfPrivileges = listOfPrivileges.concat("Account Administration. ");
        }
        if (value4) {
            listOfPrivileges = listOfPrivileges.concat("Audit Management Module. ");
        }
        if (value5) {
            listOfPrivileges = listOfPrivileges.concat("Reporting Module. ");
        }
        if (value6) {
            listOfPrivileges = listOfPrivileges.concat("Supplier Module. ");
        }
        if (value7) {
            listOfPrivileges = listOfPrivileges.concat("Purchase Request Module. ");
        }
        if (value8) {
            listOfPrivileges = listOfPrivileges.concat("Purchasing Module. ");
        }
        if (value9) {
            listOfPrivileges = listOfPrivileges.concat("Purchase Contract Module. ");
        }
        if (value10) {
            listOfPrivileges = listOfPrivileges.concat("Item Management Module. ");
        }
        if (value11) {
            listOfPrivileges = listOfPrivileges.concat("Purchasing Issue Module. ");
        }
        if (value12) {
            listOfPrivileges = listOfPrivileges.concat("Notification Module. ");
        }
        if (value13) {
            listOfPrivileges = listOfPrivileges.concat("Warehouse Inventory Management Module. ");
        }
        if (value14) {
            listOfPrivileges = listOfPrivileges.concat("Purchase Management Module. ");
        }
        if (value15) {
            listOfPrivileges = listOfPrivileges.concat("Delivery Management Module. ");
        }
        if (value16) {
            listOfPrivileges = listOfPrivileges.concat("Supplies Management Module. ");
        }
        if (value17) {
            listOfPrivileges = listOfPrivileges.concat("Transaction Management Module. ");
        }
        if (value18) {
            listOfPrivileges = listOfPrivileges.concat("Customer Management Module. ");
        }
        if (value19) {
            listOfPrivileges = listOfPrivileges.concat("Price Management Module. ");
        }
        if (value20) {
            listOfPrivileges = listOfPrivileges.concat("Promotion Management Module. ");
        }
        if (value21) {
            listOfPrivileges = listOfPrivileges.concat("Membership Management Module. ");
        }
        if (value22) {
            listOfPrivileges = listOfPrivileges.concat("Advertising Management Module. ");
        }
        if (value23) {
            listOfPrivileges = listOfPrivileges.concat("Sales and Marketing Approval Rights. ");
        }
        if (value24) {
            listOfPrivileges = listOfPrivileges.concat("Supplier Payment Module. ");
        }
        if (value25) {
            listOfPrivileges = listOfPrivileges.concat("Online Sales Payment Module. ");
        }
        if (value26) {
            listOfPrivileges = listOfPrivileges.concat("Refund Management Module. ");
        }
        if (value27) {
            listOfPrivileges = listOfPrivileges.concat("Web Store Content Management. ");
        }
        role.setListOfPrivileges(listOfPrivileges);
        entityManager.persist(role);
        entityManager.flush();
        return role.getRoleId();
    }

    public void deleteRole(Role seletedRole) {
        Role role;
        role = entityManager.find(Role.class, seletedRole.getRoleId());
        entityManager.remove(role);
    }
}
