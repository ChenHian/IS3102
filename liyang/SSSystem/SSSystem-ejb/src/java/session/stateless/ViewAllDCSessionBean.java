package session.stateless;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entity.DistributionCenter;

@Stateless
@LocalBean
public class ViewAllDCSessionBean implements ViewAllDCSessionBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;

    private int distributionCenterId;

    public ViewAllDCSessionBean() {
    }

    public Map<String, String> getAllDistributioncenterNames() {
        Map<String, String> DCNameList = new LinkedHashMap<String, String>();

        Query q = entityManager.createQuery("SELECT d FROM DistributionCenter d");
        List<Object> qResultList = q.getResultList();
        Object obj;

        for (int i = 0; i < qResultList.size(); i++) {
            obj = qResultList.get(i);
            DistributionCenter d = (DistributionCenter) obj;

            DCNameList.put(d.getName(), d.getName());
        }
        return DCNameList;
    }

    public List<DistributionCenter> getAllDistributioncenters() {
        Query q = entityManager.createQuery("SELECT d FROM DistributionCenter d");
        return q.getResultList();
    }

    public List<String> viewAllDistributioncenters() {

        Query q = entityManager.createQuery("SELECT d FROM DistributionCenter d");

        List<String> DCinfo = new ArrayList<String>();

        if (q.getResultList().isEmpty()) {
            return DCinfo;
        } else {
            Object o = q.getSingleResult();
            DistributionCenter dc = (DistributionCenter) o;
            DCinfo.add(dc.getName());
            return DCinfo;

        }
    }
}
