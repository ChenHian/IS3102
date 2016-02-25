package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
    private String email;
    private int contactNumber;
    
    

    public Long getStaffAccountId() {
        return staffAccountId;
    }

    public void setStaffAccountId(Long staffAccountId) {
        this.staffAccountId = staffAccountId;
    }

    public String getStaffAccountName() {
        return staffAccountName;
    }

    public void setStaffAccountName(String staffAccountName) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
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
}
