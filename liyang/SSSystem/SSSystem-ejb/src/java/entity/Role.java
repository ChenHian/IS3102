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
    private boolean privilege5;
    private boolean privilege6;
    private boolean privilege7;
    private boolean privilege8;
    private boolean privilege9;
    private boolean privilege10;
    private boolean privilege11;
    private boolean privilege12;
    private boolean privilege13;
    private boolean privilege14;
    private boolean privilege15;
    private boolean privilege16;
    private boolean privilege17;
    private boolean privilege18;
    private boolean privilege19;
    private boolean privilege20;
    private boolean privilege21;
    private boolean privilege22;
    private boolean privilege23;
    private boolean privilege24;
    private boolean privilege25;
    private boolean privilege26;
    private boolean privilege27;
    String listOfPrivileges;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<Privilege> privileges = new HashSet<Privilege>();

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

    public boolean isPrivilege4() {
        return privilege4;
    }

    public void setPrivilege4(boolean privilege4) {
        this.privilege4 = privilege4;
    }

    public boolean isPrivilege5() {
        return privilege5;
    }

    public void setPrivilege5(boolean privilege5) {
        this.privilege5 = privilege5;
    }

    public boolean isPrivilege6() {
        return privilege6;
    }

    public void setPrivilege6(boolean privilege6) {
        this.privilege6 = privilege6;
    }

    public boolean isPrivilege7() {
        return privilege7;
    }

    public void setPrivilege7(boolean privilege7) {
        this.privilege7 = privilege7;
    }

    public boolean isPrivilege8() {
        return privilege8;
    }

    public void setPrivilege8(boolean privilege8) {
        this.privilege8 = privilege8;
    }

    public boolean isPrivilege9() {
        return privilege9;
    }

    public void setPrivilege9(boolean privilege9) {
        this.privilege9 = privilege9;
    }

    public boolean isPrivilege10() {
        return privilege10;
    }

    public void setPrivilege10(boolean privilege10) {
        this.privilege10 = privilege10;
    }

    public boolean isPrivilege11() {
        return privilege11;
    }

    public void setPrivilege11(boolean privilege11) {
        this.privilege11 = privilege11;
    }

    public boolean isPrivilege12() {
        return privilege12;
    }

    public void setPrivilege12(boolean privilege12) {
        this.privilege12 = privilege12;
    }

    public boolean isPrivilege13() {
        return privilege13;
    }

    public void setPrivilege13(boolean privilege13) {
        this.privilege13 = privilege13;
    }

    public boolean isPrivilege14() {
        return privilege14;
    }

    public void setPrivilege14(boolean privilege14) {
        this.privilege14 = privilege14;
    }

    public boolean isPrivilege15() {
        return privilege15;
    }

    public void setPrivilege15(boolean privilege15) {
        this.privilege15 = privilege15;
    }

    public boolean isPrivilege16() {
        return privilege16;
    }

    public void setPrivilege16(boolean privilege16) {
        this.privilege16 = privilege16;
    }

    public boolean isPrivilege17() {
        return privilege17;
    }

    public void setPrivilege17(boolean privilege17) {
        this.privilege17 = privilege17;
    }

    public boolean isPrivilege18() {
        return privilege18;
    }

    public void setPrivilege18(boolean privilege18) {
        this.privilege18 = privilege18;
    }

    public boolean isPrivilege19() {
        return privilege19;
    }

    public void setPrivilege19(boolean privilege19) {
        this.privilege19 = privilege19;
    }

    public boolean isPrivilege20() {
        return privilege20;
    }

    public void setPrivilege20(boolean privilege20) {
        this.privilege20 = privilege20;
    }

    public boolean isPrivilege21() {
        return privilege21;
    }

    public void setPrivilege21(boolean privilege21) {
        this.privilege21 = privilege21;
    }

    public boolean isPrivilege22() {
        return privilege22;
    }

    public void setPrivilege22(boolean privilege22) {
        this.privilege22 = privilege22;
    }

    public boolean isPrivilege23() {
        return privilege23;
    }

    public void setPrivilege23(boolean privilege23) {
        this.privilege23 = privilege23;
    }

    public boolean isPrivilege24() {
        return privilege24;
    }

    public void setPrivilege24(boolean privilege24) {
        this.privilege24 = privilege24;
    }

    public boolean isPrivilege25() {
        return privilege25;
    }

    public void setPrivilege25(boolean privilege25) {
        this.privilege25 = privilege25;
    }

    public boolean isPrivilege26() {
        return privilege26;
    }

    public void setPrivilege26(boolean privilege26) {
        this.privilege26 = privilege26;
    }

    public boolean isPrivilege27() {
        return privilege27;
    }

    public void setPrivilege27(boolean privilege27) {
        this.privilege27 = privilege27;
    }

    
    
    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
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
