/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.ItemType;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Hongyi
 */
@Stateless
@LocalBean
public class ItemTypeSessionBean implements ItemTypeSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    EntityManager em;

    public ItemTypeSessionBean() {
    }
    
    public List<ItemType> getItemTypes(){
        Query q = em.createQuery("SELECT i FROM ItemType i");
        
        return q.getResultList();
    }
}
