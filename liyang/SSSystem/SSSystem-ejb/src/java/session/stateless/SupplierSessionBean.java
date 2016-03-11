/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.Contract;
import entity.FeedbackOnSupplier;
import entity.Item;
import entity.PurchaseOrder;
import entity.Quotation;
import entity.Supplier;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ejb.LocalBean;

/**
 *
 * @author QianJun
 */
@Stateless
@LocalBean
public class SupplierSessionBean implements SupplierSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    EntityManager em;
    
    public SupplierSessionBean(){}
    /*
    public List<String> getAllSuppliersNames(){
        Query q = em.createQuery("SELECT s.companyName FROM Supplier s");
        return q.getResultList();
    }
    */
    public List<Supplier> getSuppliers(){
        Query q = em.createQuery("SELECT s FROM Supplier s WHERE s.isDelete= false");
      /*  List<Object> qResultList = q.getResultList();
        List<Supplier> supplierList = new ArrayList<Supplier>();
        Query r;
        Object obj;
        
        for(int i=0; i<qResultList.size(); i++){
            obj = qResultList.get(i);
            Supplier s = (Supplier) obj;
            r = em.createQuery("Select f FROM FeedbackOnSupplier f WHERE f.supplier.supplierId = :supplierid");
            r.setParameter("supplierid", s.getSupplierId());
            s.setFeedbackOnSupplier(r.getResultList());
            supplierList.add(s);
        }
*/
        return q.getResultList();
    }
    
    public List<Quotation> getSupplierQuotation(long selectedSupplierId){
       // Query query = em.createQuery("SELECT q FROM Quotation q WHERE q.supplier.supplierId = :supplierId AND q.status=:status" );
       
        
        Query query =  em.createQuery("SELECT i FROM Quotation i WHERE i.supplier.supplierId = :supplierId AND i.status = :status order by i.quotationId DESC" );
        query.setParameter("supplierId", selectedSupplierId);
        query.setParameter("status", "Approved");
        //  query.setParameter("validEndDate", "");
        
        return query.getResultList();
    }
    
    public List<Contract> getSupplierContract(long selectedSupplierId){
       // Query query = em.createQuery("SELECT q FROM Quotation q WHERE q.supplier.supplierId = :supplierId AND q.status=:status" );
       
        
        Query query =  em.createQuery("SELECT c FROM Contract c WHERE c.supplier.supplierId = :supplierId order by c.contractId DESC");
        query.setParameter("supplierId", selectedSupplierId);
        //  query.setParameter("validEndDate", "");
        
        return query.getResultList();
    }
    
    public List<PurchaseOrder> getSupplierPurchase(long selectedSupplierId){
       // Query query = em.createQuery("SELECT q FROM Quotation q WHERE q.supplier.supplierId = :supplierId AND q.status=:status" );
       
        
        Query query =  em.createQuery("SELECT p FROM PurchaseOrder p WHERE p.supplier.supplierId = :supplierId order by p.purchaseOrderId DESC");
        query.setParameter("supplierId", selectedSupplierId);
        //query.setParameter("status", "Void");
        //  query.setParameter("validEndDate", "");
        
        return query.getResultList();
    }
   
    public void updateSupplier(long supplierId, String companyName, String email, String address, String contactPerson, String contactNumber){

        Query q = em.createQuery("SELECT s FROM Supplier s WHERE s.supplierId = :supplierId");
        q.setParameter("supplierId", supplierId);
        
       // boolean status = false; check status if needed
        
        if(!q.getResultList().isEmpty()){
            Object o = q.getSingleResult();
            Supplier s = (Supplier) o;
            
            if(companyName != null){
                s.setCompanyName(companyName);
                em.merge(s);
            }
            if(email != null){
                s.setEmail(email);
                em.merge(s);
            }
            if(address != null){
                s.setAddress(address);
                em.merge(s);
            }
            if(contactPerson != null){
                s.setContactPerson(contactPerson);
                em.merge(s);
            }
            if(contactNumber != null){
                s.setContactNumber(contactNumber);
                em.merge(s);
            }
           // status = true;
            
        }
    }
    
    public Map<Long, String> getSuppliersBasicList(){
        Map<Long, String> supplierList = new LinkedHashMap<Long, String>();
                
        Query q = em.createQuery("SELECT s FROM Supplier s WHERE s.isDelete= false");
        List<Object> qResultList = q.getResultList();
        Object obj;
        
        for(int i=0; i<qResultList.size(); i++){
            obj = qResultList.get(i);
            Supplier s = (Supplier) obj;
            
            supplierList.put(s.getSupplierId(), s.getCompanyName());
        }

        return supplierList;
    }
    
    public void addFeedbackOnSupplier(long supplierToFeedback, String feedbackDeliveryContent, int feedbackDeliveryRating, String feedbackStaffContent, int feedbackStaffRating, String feedbackItemContent, int feedbackItemRating){

        FeedbackOnSupplier f = new FeedbackOnSupplier();
        Supplier s = new Supplier();
        Object obj = new Object();
        
        Query q = em.createQuery("SELECT s FROM Supplier s WHERE s.supplierId = :supplierId");
        q.setParameter("supplierId", supplierToFeedback);
        
        if(!q.getResultList().isEmpty()){
            obj = q.getSingleResult();
            s = (Supplier) obj;
           
            q = em.createQuery("SELECT max(f.feedbackOnSupplierId) FROM FeedbackOnSupplier f");
            if(!q.getResultList().isEmpty()){
                obj = q.getSingleResult();
                long maxFeedbackToSupplierId = 0;
                if(obj != null){
                    maxFeedbackToSupplierId = (Long) obj;
                    f.setFeedbackOnSupplierId(maxFeedbackToSupplierId + 1);
                }
                else{
                    f.setFeedbackOnSupplierId(Long.valueOf(1));
                }
                System.out.println("SupplierSessionBean: addFeedbackToSupplier() --> the maxFeedbackToSupplierId is " + maxFeedbackToSupplierId);
            }
            
            if(feedbackDeliveryContent != null && !feedbackDeliveryContent.equals("")){
                f.setFeedbackDeliveryContent(feedbackDeliveryContent.trim());
            }

            if(feedbackStaffContent != null && !feedbackStaffContent.equals("")){
                f.setFeedbackStaffContent(feedbackStaffContent.trim());
            }
            if(feedbackItemContent != null && !feedbackItemContent.equals("")){
                f.setFeedbackItemContent(feedbackItemContent.trim());
            }
            
            f.setFeedbackDeliveryRating(feedbackDeliveryRating);
            f.setFeedbackStaffRating(feedbackStaffRating);
            f.setFeedbackItemRating(feedbackItemRating);

            // generate time stamp and add into database
            //Timestamp stamp = new Timestamp(System.currentTimeMillis());
            //Date date = new Date(stamp.getTime());
            Date dateDummy = new Date();
            java.sql.Date date= new java.sql.Date(dateDummy.getTime());
            
            //System.out.println(date);

            f.setDate(date);
            
            em.persist(f);
            s.getFeedbackOnSupplier().add(f);
            em.merge(s);
            
            
            //return true; to check status if needed
        }
        // return false;
    }
    
    public void addSupplier(String companyName, String email, String address, String contactPerson, String contactNumber){

        Supplier s = new Supplier();
        //Object obj = new Object();
        
        //Query q = em.createQuery("SELECT s FROM Supplier s WHERE s.supplierId = :supplierId");
        //q.setParameter("supplierId", supplierToFeedback);
        
        //if(!q.getResultList().isEmpty()){
          //  obj = q.getSingleResult();
          //  s = (Supplier) obj;
           
            Query q = em.createQuery("SELECT max(s.supplierId) FROM Supplier s");
            if(!q.getResultList().isEmpty()){
                Object obj = q.getSingleResult();
                long maxSupplierId = 0;
                if(obj != null){
                    maxSupplierId = (Long) obj;
                    s.setSupplierId(Long.valueOf(maxSupplierId + 1));
                }
                else{
                    s.setSupplierId(Long.valueOf(1));
                }
                System.out.println("SupplierSessionBean: add supplier() --> the maxSupplierId is " + maxSupplierId);
            }
            
            s.setCompanyName(companyName);
            s.setAddress(address);
            s.setEmail(email);
            s.setContactPerson(contactPerson);
            s.setContactNumber(contactNumber);
            s.setIsDelete(false);
            
            em.persist(s);
            //return true; to check status if needed
        //}
        // return false;
    }        
    
     public void removeSupplier(long supplierId){
        System.out.println("removing supplier");
        Query q = em.createQuery("SELECT s FROM Supplier s WHERE s.supplierId = :supplierId");
        q.setParameter("supplierId", supplierId);
        
       // boolean status = false; check status if needed
        
        if(!q.getResultList().isEmpty()){
            Object o = q.getSingleResult();
            Supplier s = (Supplier) o;
            
            s.setIsDelete(true);
            em.merge(s);
           // status = true;
            
        }
    }
     
    public ArrayList<String> getSupplierAverageRating(long selectedSupplierId){
        int deliveryTotal = 0;
        int staffTotal = 0;
        int itemTotal = 0;
        ArrayList<String> averageRating = new ArrayList<String>();
        
        Query q = em.createQuery("SELECT s FROM Supplier s WHERE s.supplierId = :supplierId");
        q.setParameter("supplierId", selectedSupplierId);
        
        if(!q.getResultList().isEmpty()){
            Object o = q.getSingleResult();
            Supplier s = (Supplier) o;

            Collection<FeedbackOnSupplier> feedbackCollection = new HashSet<FeedbackOnSupplier>(s.getFeedbackOnSupplier());
            List<FeedbackOnSupplier> feedbackList = new ArrayList<FeedbackOnSupplier>(feedbackCollection);
            FeedbackOnSupplier f = new FeedbackOnSupplier();
            
           
            for(int i=0; i< feedbackList.size(); i++){
                f = feedbackList.get(i);
                deliveryTotal += f.getFeedbackDeliveryRating();
                staffTotal += f.getFeedbackStaffRating();
                itemTotal += f.getFeedbackItemRating();
            }
            
            if(feedbackList.size() != 0){
                DecimalFormat df = new DecimalFormat("#.##"); 
                
                double deliveryAverage = (double) deliveryTotal / (double) feedbackList.size();
                averageRating.add(df.format(deliveryAverage));
                
                double staffAverage = (double) staffTotal / (double) feedbackList.size();
                averageRating.add(df.format(staffAverage));
                
                double itemAverage = (double) itemTotal / (double) feedbackList.size();
                averageRating.add(df.format(itemAverage));
                
                double weightedAverage = (deliveryAverage * 2 + staffAverage + itemAverage * 2) / 5;
                averageRating.add(df.format(weightedAverage));
                
            }
               
        }
        
        if(averageRating.size()== 0){
            averageRating.add("No feedback on Delivery yet");
            averageRating.add("No feedback on Staff yet");
            averageRating.add("No feedback on Item yet");
            averageRating.add("Not available");
        
        }
        
        return averageRating;

    }
    
 /*   public String getSupplierAverageStaffRating(long selectedSupplierId){
        int total = 0;
        double average=0;
        int count=0;
        
        Query q = em.createQuery("SELECT s FROM Supplier s WHERE s.supplierId = :supplierId");
        q.setParameter("supplierId", selectedSupplierId);
        
        if(!q.getResultList().isEmpty()){
            Object o = q.getSingleResult();
            Supplier s = (Supplier) o;

            Collection<FeedbackOnSupplier> feedbackCollection = new HashSet<FeedbackOnSupplier>(s.getFeedbackOnSupplier());
            List<FeedbackOnSupplier> feedbackList = new ArrayList<FeedbackOnSupplier>(feedbackCollection);
           
            
            for(int i=0; i< feedbackList.size(); i++){
                if(feedbackList.get(i).getFeedbackType().equals("Supplier's Staff")){
                    total += feedbackList.get(i).getFeedbackRating();
                    count++;
                }
            }
            
            if(count != 0){
                average = (double) total / (double)count;
            }
               System.out.println("total = " + total);
               System.out.println("count = " + count);
               System.out.println("average = " + average);
               
        }
        
        DecimalFormat df = new DecimalFormat("#.##"); 
        
        if(average == 0)
            return "No feedback on Staff yet";
        else
            return df.format(average);
    }
    
*/    
   /* public List<FeedbackOnSupplier> getFeedbackOnSupplier(long supplierId){
        Query q = em.createQuery("Select f FROM FeedbackOnSupplier WHERE f.supplier.supplierId = :supplierid");
        q.setParameter("supplierid", supplierId);
        return q.getResultList();
    }
    */
    /*public List <String> viewSupplier(){
        
        Query q = em.createQuery("Select s from Supplier s");
        
        List <String> supplierInfo = new ArrayList<String>();

        if(q.getResultList().isEmpty())
            return supplierInfo;
        else{
            Object o = q.getSingleResult();
                Supplier s = (Supplier) o;
                
                supplierInfo.add(s.getCompanyName());
               
                
           return supplierInfo;
        }
    }
    */
    
    
    
    public Supplier getSupplierById(Long id){
        Supplier supp = new Supplier();
        Query q = em.createQuery("SELECT s FROM Supplier s WHERE s.supplierId = :supplierId");
        q.setParameter("supplierId", id);
        
        if(!q.getResultList().isEmpty()){
            supp = (Supplier) q.getSingleResult();
        }
        return supp;
    }
    
}
