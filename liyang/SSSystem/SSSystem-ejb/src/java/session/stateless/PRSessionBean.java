package session.stateless;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entity.Item;
import entity.PurchaseRequisition;

@Stateless
@LocalBean
public class PRSessionBean implements PRSessionBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;

    public PRSessionBean() {
    }

    public Map<String, String> getAllItemName() {
        Map<String, String> ItemNameList = new LinkedHashMap<String, String>();

        Query q = entityManager.createQuery("SELECT it FROM Item it");
        List<Object> qResultList = q.getResultList();
        Object obj;

        for (int i = 0; i < qResultList.size(); i++) {
            obj = qResultList.get(i);
            Item it = (Item) obj;

            ItemNameList.put(it.getItemName(), it.getItemName());
        }
        return ItemNameList;
    }

    public Item findItem(String itemName) {
        Item item = new Item();
        Query q = entityManager.createQuery("SELECT t FROM Item AS t WHERE t.itemName=:itemName");
        q.setParameter("itemName", itemName);
        item = (Item) q.getSingleResult();
        return item;
    }

    public Long createPR(java.util.Date dateCreated2, java.util.Date dateRequest2, String selectedCenterName, String status, Integer quantityRequested, String itemName) {
        PurchaseRequisition pr = new PurchaseRequisition();

        java.sql.Date dateCreated = new java.sql.Date(dateCreated2.getTime());
        java.sql.Date dateRequest = new java.sql.Date(dateRequest2.getTime());

        pr.setDateCreated(dateCreated);
        pr.setDateRequest(dateRequest);
        pr.setCenterName(selectedCenterName);
        pr.setQuantityRequested(quantityRequested);
        pr.setStatus(status);

        Query q = entityManager.createQuery("SELECT t FROM Item AS t WHERE t.itemName=:itemName");
        q.setParameter("itemName", itemName);

        if (!q.getResultList().isEmpty()) {
            Object obj = q.getSingleResult();
            Item item = (Item) obj;

            pr.setItem(item);
            //System.out.println(item.getItemId());
            //System.out.println(item.getItemName());
        }
        entityManager.persist(pr);
        return pr.getPurchaseRequisitionId();
    }

    public List<PurchaseRequisition> getAllPRs() {
        Query q = entityManager.createQuery("SELECT p FROM PurchaseRequisition p");
        return q.getResultList();
    }

}
