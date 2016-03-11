package session.stateless;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entity.BatchOut;
import entity.BatchReceipt;
import entity.DeliveryRequest;
import entity.DistributionCenterInventory;
import entity.FeedbackOnSupplier;
import entity.Item;
import entity.ItemDisposal;
import entity.PurchaseOrder;
import entity.Supplier;

@Stateless
@LocalBean
public class BRSessionBean implements BRSessionBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;

    public BRSessionBean() {
    }

    public Long createBR(Long purchaseOrderId, java.util.Date dateReceived2, java.util.Date expiryDate2, String selectedCenterName, String batchNumber, Integer quantityIn, Integer quantityRejected) {
        BatchReceipt br = new BatchReceipt();

        java.sql.Date dateReceived = new java.sql.Date(dateReceived2.getTime());
        java.sql.Date expiryDate = new java.sql.Date(expiryDate2.getTime());

        br.setDateReceived(dateReceived);
        br.setExpiryDate(expiryDate);
        br.setCenterName(selectedCenterName);
        br.setBatchNumber(batchNumber);
        br.setQuantityIn(quantityIn);
        br.setQuantityRejected(quantityRejected);
        br.setAvailableQuantity(quantityIn);

        Query q = entityManager.createQuery("SELECT p FROM PurchaseOrder AS p WHERE p.purchaseOrderId=:purchaseOrderId");
        q.setParameter("purchaseOrderId", purchaseOrderId);

        if (!q.getResultList().isEmpty()) {
            Object obj = q.getSingleResult();
            PurchaseOrder po = (PurchaseOrder) obj;
            po.getPurchaseRequisition().setStatus("Completed");
            po.setStatus("Completed");
            br.setPo(po);

            Long itemId = br.getPo().getItem().getItemId();
            
            Query itemQ = entityManager.createQuery("SELECT i FROM Item i WHERE i.itemId = :itemId");
            itemQ.setParameter("itemId", itemId);
            if (!itemQ.getResultList().isEmpty()) {
               Object obj3 = itemQ.getSingleResult();
               Item i = (Item) obj3;
               br.setItem(i);
            }
            
            Query qq = entityManager.createQuery("SELECT dci FROM DistributionCenterInventory dci WHERE dci.item.itemId = :itemId");
            qq.setParameter("itemId", itemId);
            if (!qq.getResultList().isEmpty()) {
                Object obj2 = qq.getSingleResult();
                DistributionCenterInventory dci = (DistributionCenterInventory) obj2;
                int itemNowQuanity = dci.getItemAvailableQuantity();
                int itemUpdatedQuanity = itemNowQuanity + quantityIn;
                dci.setItemAvailableQuantity(itemUpdatedQuanity);
                System.out.println("dci.getItem() is " + dci.getItem());
                
            }

        }
        entityManager.persist(br);
        
        
        
        return br.getBatchReceiptId();
    }

    public Long createBO(Long batchReceiptId, Long deliveryRequestId, java.util.Date dateLeaveDC2, Integer quantityOut) {
        BatchOut bo = new BatchOut();
        java.sql.Date dateLeaveDC = new java.sql.Date(dateLeaveDC2.getTime());

        bo.setDateLeaveDC(dateLeaveDC);
        bo.setQuantityOut(quantityOut);

        Query q = entityManager.createQuery("SELECT d FROM DeliveryRequest AS d WHERE d.deliveryRequestId=:deliveryRequestId");
        q.setParameter("deliveryRequestId", deliveryRequestId);
        if (!q.getResultList().isEmpty()) {
            Object obj = q.getSingleResult();
            DeliveryRequest dr = (DeliveryRequest) obj;
            bo.setDr(dr);
        }

        Query qq = entityManager.createQuery("SELECT b FROM BatchReceipt AS b WHERE b.batchReceiptId=:batchReceiptId");
        qq.setParameter("batchReceiptId", batchReceiptId);
        if (!qq.getResultList().isEmpty()) {
            Object obj = qq.getSingleResult();
            BatchReceipt br = (BatchReceipt) obj;
            java.util.Date dateReceived2 = br.getDateReceived();
            java.sql.Date dateReceived = new java.sql.Date(dateReceived2.getTime());

            int updatedavailableQuanity;
            updatedavailableQuanity = br.getAvailableQuantity() - quantityOut;
            br.setAvailableQuantity(updatedavailableQuanity);

            Long itemId = br.getPo().getItem().getItemId();
            Query qqq = entityManager.createQuery("SELECT dci FROM DistributionCenterInventory dci WHERE dci.item.itemId = :itemId");
            qqq.setParameter("itemId", itemId);
            if (!qqq.getResultList().isEmpty()) {
                Object obj2 = qqq.getSingleResult();
                DistributionCenterInventory dci = (DistributionCenterInventory) obj2;
                int itemNowQuanity = dci.getItemAvailableQuantity();
                int itemUpdatedQuanity = itemNowQuanity - quantityOut;
                dci.setItemAvailableQuantity(itemUpdatedQuanity);
            }
            bo.setBr(br);

            long diff = dateLeaveDC2.getTime() - dateReceived.getTime();
            Long daysInDCLong = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            Integer daysInDC = (int) (long) daysInDCLong;
            System.out.println("Days in DC: " + daysInDC);
            bo.setDaysInDC(daysInDC);
            entityManager.persist(bo);
        }
        return bo.getBatchOutId();
    }

    public Long createItemDisposal(Long batchReceiptId, Integer disposedQuantity, java.util.Date disposalDate2, String reasonForDisposal) {
        ItemDisposal itemDisposal = new ItemDisposal();
        java.sql.Date disposalDate = new java.sql.Date(disposalDate2.getTime());

        itemDisposal.setDisposedQuantity(disposedQuantity);
        itemDisposal.setDisposalDate(disposalDate);
        itemDisposal.setReasonForDisposal(reasonForDisposal);

        Query qq = entityManager.createQuery("SELECT b FROM BatchReceipt AS b WHERE b.batchReceiptId=:batchReceiptId");
        qq.setParameter("batchReceiptId", batchReceiptId);
        if (!qq.getResultList().isEmpty()) {
            Object obj = qq.getSingleResult();
            BatchReceipt br = (BatchReceipt) obj;

            int updatedavailableQuanity2;
            updatedavailableQuanity2 = br.getAvailableQuantity() - disposedQuantity;
            br.setAvailableQuantity(updatedavailableQuanity2);

            Long itemId = br.getPo().getItem().getItemId();
            Query qqq = entityManager.createQuery("SELECT dci FROM DistributionCenterInventory dci WHERE dci.item.itemId = :itemId");
            qqq.setParameter("itemId", itemId);
            if (!qqq.getResultList().isEmpty()) {
                Object obj2 = qqq.getSingleResult();
                DistributionCenterInventory dci = (DistributionCenterInventory) obj2;
                int itemNowQuanity = dci.getItemAvailableQuantity();
                int itemUpdatedQuanity = itemNowQuanity - disposedQuantity;
                dci.setItemAvailableQuantity(itemUpdatedQuanity);
            }
            itemDisposal.setBr(br);
            entityManager.persist(itemDisposal);
        }
        return itemDisposal.getItemDisposalId();
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
        }
    }

    public List<BatchReceipt> getAllBRs() {
        Query q = entityManager.createQuery("SELECT b FROM BatchReceipt b");
        return q.getResultList();
    }

    public List<BatchOut> getAllBOs() {
        Query q = entityManager.createQuery("SELECT o FROM BatchOut o");
        return q.getResultList();
    }

    public List<ItemDisposal> getAllItemDisposal() {
        Query q = entityManager.createQuery("SELECT item FROM ItemDisposal item");
        return q.getResultList();
    }

    public List<PurchaseOrder> getAllPOs() {
        Query q = entityManager.createQuery("SELECT p FROM PurchaseOrder p");
        return q.getResultList();
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
}
