/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateful;

import javax.ejb.Remote;

/**
 *
 * @author Chen Hian
 */
@Remote
public interface AccessManagerBeanRemote {
    public boolean userPrivilege4(int roleid); 
}
