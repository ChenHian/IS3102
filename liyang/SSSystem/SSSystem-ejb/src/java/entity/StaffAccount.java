package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class StaffAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffAccountId;
    private String staffAccountName;
    private String password;
    @OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Role role;

    public Long getSystemUserId() {
        return staffAccountId;
    }

    public void setStaffAccountId(Long systemUserId) {
        this.staffAccountId = staffAccountId;
    }

    public String getStaffAccountName() {
        return staffAccountName;
    }

    public void setStaffAccountName(String userName) {
        this.staffAccountName = staffAccountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   public Role getRole(){
       return role;
   }
   
   public void setRole (Role role){
       this.role = role;
   }
   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (staffAccountId != null ? staffAccountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof StaffAccount)) {
            return false;
        }
        StaffAccount other = (StaffAccount) object;
        if ((this.staffAccountId == null && other.staffAccountId != null)
                || (this.staffAccountId != null && !this.staffAccountId.equals(other.staffAccountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SystemUser[id=" + staffAccountId + "]";
    }
}
