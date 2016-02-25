/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.warehouse;

import java.util.List;
import javax.ejb.Local;
import entity.warehouse_entity.DistributionCenter;
import entity.warehouse_entity.DistributionCenterInventory;
/**
 *
 * @author yingweiliu
 */
@Local
public interface WarehouseSessionBeanLocal {
    public List<String> getAllDistributionCenterNames();
    public List<String> viewAllDistributionCenters();
    public List<DistributionCenter> getAllDistributionCenters();
    public List<DistributionCenterInventory> getDCinventory();
    
}
