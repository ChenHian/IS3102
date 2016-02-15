package entity;

import entity.Role;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-02-15T17:12:57")
@StaticMetamodel(StaffAccount.class)
public class StaffAccount_ { 

    public static volatile SingularAttribute<StaffAccount, String> password;
    public static volatile SingularAttribute<StaffAccount, Role> role;
    public static volatile SingularAttribute<StaffAccount, Long> staffAccountId;
    public static volatile SingularAttribute<StaffAccount, String> staffAccountName;

}