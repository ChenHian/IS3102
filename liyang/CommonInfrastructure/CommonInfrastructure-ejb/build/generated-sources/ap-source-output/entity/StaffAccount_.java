package entity;

import entity.Role;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-02-16T23:32:08")
@StaticMetamodel(StaffAccount.class)
public class StaffAccount_ { 

    public static volatile SingularAttribute<StaffAccount, String> staffAccountName;
    public static volatile SingularAttribute<StaffAccount, String> contactNumber;
    public static volatile SingularAttribute<StaffAccount, String> email;
    public static volatile SingularAttribute<StaffAccount, Role> role;
    public static volatile SingularAttribute<StaffAccount, Long> staffAccountId;
    public static volatile SingularAttribute<StaffAccount, String> password;

}