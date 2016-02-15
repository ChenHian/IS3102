package entity;

import entity.Privilege;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-02-15T17:12:57")
@StaticMetamodel(Role.class)
public class Role_ { 

    public static volatile SingularAttribute<Role, Boolean> privilege3;
    public static volatile SetAttribute<Role, Privilege> privileges;
    public static volatile SingularAttribute<Role, Boolean> privilege2;
    public static volatile SingularAttribute<Role, Boolean> privilege1;
    public static volatile SingularAttribute<Role, Long> roleId;
    public static volatile SingularAttribute<Role, String> roleName;
    public static volatile SingularAttribute<Role, String> listOfPrivileges;

}