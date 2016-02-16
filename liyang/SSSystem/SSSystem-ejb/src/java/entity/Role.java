package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String roleName;
    private boolean privilege1;
    private boolean privilege2;     
    private boolean privilege3;
    private boolean privilege4;
    String listOfPrivileges;
    
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<Privilege> privileges = new HashSet<Privilege>();

    public Role() {
    }

    public boolean isPrivilege4() {
        return privilege4;
    }

    public void setPrivilege4(boolean privilege4) {
        this.privilege4 = privilege4;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

      public boolean isPrivilege1() {
        return privilege1;
    }
 
    public void setPrivilege1(boolean privilege1) {
        this.privilege1 = privilege1;
    }
 
    public boolean isPrivilege2() {
        return privilege2;
    }
 
    public void setPrivilege2(boolean privilege2) {
        this.privilege2 = privilege2;
    }
    
    public boolean isPrivilege3() {
        return privilege3;
    }
 
    public void setPrivilege3(boolean privilege3) {
        this.privilege3 = privilege3;
    }

    public Set<Privilege> getPrivileges(){
        return privileges;
    }

     public void setPrivileges (Set<Privilege> privileges){
         this.privileges = privileges;
     }

    public String getListOfPrivileges() {
        return listOfPrivileges;
    }

    public void setListOfPrivileges(String listOfPrivileges) {
        this.listOfPrivileges = listOfPrivileges;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
// TODO: Warning - this method won't work in the case the eventId fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.roleId == null && other.roleId != null)
                || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Role[id=" + roleId + "]";
    }
}
