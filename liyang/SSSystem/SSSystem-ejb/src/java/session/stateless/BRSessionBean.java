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
import entity.BatchReceipt;
import entity.FeedbackOnSupplier;
import entity.Item;
import entity.PurchaseOrder;
import entity.PurchaseRequisition;
import entity.Supplier;

@Stateless
@LocalBean
public class BRSessionBean implements BRSessionBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;

    public BRSessionBean() {
    }

    public Long createBR(Long purchaseOrderId, java.util.Date dateReceived2, java.util.Date expiryDate2, String selectedCenterName, String batchNumber, Integer quantityIn, Integer quantityRejected, Integer availableQuantity) {
        BatchReceipt br = new BatchReceipt();

        java.sql.Date dateReceived = new java.sql.Date(dateReceived2.getTime());
        java.sql.Date expiryDate = new java.sql.Date(expiryDate2.getTime());

        br.setDateReceived(dateReceived);
        br.setExpiryDate(expiryDate);
        br.setCenterName(selectedCenterName);
        br.setBatchNumber(batchNumber);
        br.setQuantityIn(quantityIn);
        br.setQuantityRejected(quantityRejected);
        br.setAvailableQuantity(availableQuantity);

        Query q = entityManager.createQuery("SELECT p FROM PurchaseOrder AS p WHERE p.purchaseOrderId=:purchaseOrderId");
        q.setParameter("purchaseOrderId", purchaseOrderId);

        if (!q.getResultList().isEmpty()) {
            Object obj = q.getSingleResult();
            PurchaseOrder po = (PurchaseOrder) obj;

            br.setPo(po);
            po.setStatus("Completed");
            po.getPurchaseRequisition().setStatus("Completed");
            System.out.println(po.getPurchaseOrderId());
        }
        entityManager.persist(br);
        return br.getBatchReceiptId();
    }

    public void addFeedbackOnSupplier(long supplierToFeedback, String feedbackDeliveryContent, int feedbackDeliveryRating, String feedbackStaffContent, int feedbackStaffRating, String feedbackItemContent, int feedbackItemRating) {

        FeedbackOnSupplier f = new FeedbackOnSupplier();
        Supplier s = new Supplier();
        Object obj = new Object();

        Query q = entityManager.createQuery("SELECT s FROM Supplier s WHERE s.supplierId = :supplierId");
        q.setParameter("supplierId", supplierToFeedback);

        if (!q.getResultList().isEmpty()) {
            obj = q.getSingleResult();
            s = (Supplier) obj;

            q = entityManager.createQuery("SELECT max(f.feedbackOnSupplierId) FROM FeedbackOnSupplier f");
            if (!q.getResultList().isEmpty()) {
                obj = q.getSingleResult();
                long maxFeedbackToSupplierId = 0;
                if (obj != null) {
                    maxFeedbackToSupplierId = (Long) obj;
                    f.setFeedbackOnSupplierId(maxFeedbackToSupplierId + 1);
                } else {
                    f.setFeedbackOnSupplierId(Long.valueOf(1));
                }
                System.out.println("SupplierSessionBean: addFeedbackToSupplier() --> the maxFeedbackToSupplierId is " + maxFeedbackToSupplierId);
            }

            if (feedbackDeliveryContent != null && !feedbackDeliveryContent.equals("")) {
                f.setFeedbackDeliveryContent(feedbackDeliveryContent.trim());
            }

            if (feedbackStaffContent != null && !feedbackStaffContent.equals("")) {
                f.setFeedbackStaffContent(feedbackStaffContent.trim());
            }
            if (feedbackItemContent != null && !feedbackItemContent.equals("")) {
                f.setFeedbackItemContent(feedbackItemContent.trim());
            }

            f.setFeedbackDeliveryRating(feedbackDeliveryRating);
            f.setFeedbackStaffRating(feedbackStaffRating);
            f.setFeedbackItemRating(feedbackItemRating);

            // generate time stamp and add into database
            //Timestamp stamp = new Timestamp(System.currentTimeMillis());
            //Date date = new Date(stamp.getTime());
            Date dateDummy = new Date();
            java.sql.Date date = new java.sql.Date(dateDummy.getTime());

            System.out.println(date);

            f.setDate(date);

            entityManager.persist(f);
            s.getFeedbackOnSupplier().add(f);
            entityManager.merge(s);

            //return true; to check status if needed
        }
        // return false;
    }

    public Map<Long, String> getSuppliersBasicList() {
        Map<Long, String> supplierList = new LinkedHashMap<Long, String>();

        Query q = entityManager.createQuery("SELECT s FROM Supplier s WHERE s.isDelete= false");
        List<Object> qResultList = q.getResultList();
        Object obj;

        for (int i = 0; i < qResultList.size(); i++) {
            obj = qResultList.get(i);
            Supplier s = (Supplier) obj;

            supplierList.put(s.getSupplierId(), s.getCompanyName());
        }
        return supplierList;
    }

    public Supplier findSupplierName(Long purchaseOrderId) {
        PurchaseOrder po = new PurchaseOrder();
        Query q = entityManager.createQuery("SELECT p FROM PurchaseOrder AS p WHERE p.purchaseOrderId=:purchaseOrderId");
        q.setParameter("purchaseOrderId", purchaseOrderId);
        po = (PurchaseOrder) q.getSingleResult();
        return po.getSupplier();
    }

    public List<BatchReceipt> getAllBRs() {
        Query q = entityManager.createQuery("SELECT b FROM BatchReceipt b");
        return q.getResultList();
    }
}
