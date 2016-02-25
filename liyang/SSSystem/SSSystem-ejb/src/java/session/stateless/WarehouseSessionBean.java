/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import java.util.ArrayList;
import entity.DistributionCenter;
import entity.DistributionCenterInventory;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author yingweiliu
 */
@Stateless
@LocalBean
public class WarehouseSessionBean implements WarehouseSessionBeanLocal {
    
    @PersistenceContext  
    private EntityManager entityManager;
    
    private int distributionCenterId;

    public WarehouseSessionBean() {}
    
    @Override
    public List<String> getAllDistributionCenterNames(){
        Query q = entityManager.createQuery("SELECT d.name FROM Distributioncenter d");
        return q.getResultList();
    }
    
    
    @Override
    public List<DistributionCenter> getAllDistributionCenters() {
        Query q = entityManager.createQuery("SELECT d FROM Distributioncenter d");
        return q.getResultList();
    }
    
    @Override
    public List <String> viewAllDistributionCenters(){
        
        Query q = entityManager.createQuery("SELECT d FROM Distributioncenter d");
        
        List <String> DCinfo = new ArrayList<String>();

        if(q.getResultList().isEmpty())
            return DCinfo;
        else{
            Object o = q.getSingleResult();
                DistributionCenter dc = (DistributionCenter) o;      
                DCinfo.add(dc.getName());
         return DCinfo;
        }   
    }      
    
    @Override
    public List<DistributionCenterInventory> getDCinventory() {
        Query q = entityManager.createQuery("SELECT d FROM Distributioncenterinventory d");
        return q.getResultList();
    }
}
