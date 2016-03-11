/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FeedbackOnSupplier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long feedbackOnSupplierId;
    private String feedbackDeliveryContent;
    private int feedbackDeliveryRating;
    private String feedbackStaffContent;
    private int feedbackStaffRating;
    private String feedbackItemContent;
    private int feedbackItemRating;
    private Date date;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedbackOnSupplierId != null ? feedbackOnSupplierId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeedbackOnSupplier)) {
            return false;
        }
        FeedbackOnSupplier other = (FeedbackOnSupplier) object;
        if ((this.feedbackOnSupplierId == null && other.feedbackOnSupplierId != null) || (this.feedbackOnSupplierId != null && !this.feedbackOnSupplierId.equals(other.feedbackOnSupplierId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FeedbackOnSupplier[ id=" + getFeedbackOnSupplierId() + " ]";
    }

    public Long getFeedbackOnSupplierId() {
        return feedbackOnSupplierId;
    }

    public void setFeedbackOnSupplierId(Long feedbackOnSupplierId) {
        this.feedbackOnSupplierId = feedbackOnSupplierId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFeedbackDeliveryContent() {
        return feedbackDeliveryContent;
    }

    public void setFeedbackDeliveryContent(String feedbackDeliveryContent) {
        this.feedbackDeliveryContent = feedbackDeliveryContent;
    }

    public int getFeedbackDeliveryRating() {
        return feedbackDeliveryRating;
    }

    public void setFeedbackDeliveryRating(int feedbackDeliveryRating) {
        this.feedbackDeliveryRating = feedbackDeliveryRating;
    }

    public String getFeedbackStaffContent() {
        return feedbackStaffContent;
    }

    public void setFeedbackStaffContent(String feedbackStaffContent) {
        this.feedbackStaffContent = feedbackStaffContent;
    }

    public int getFeedbackStaffRating() {
        return feedbackStaffRating;
    }

    public void setFeedbackStaffRating(int feedbackStaffRating) {
        this.feedbackStaffRating = feedbackStaffRating;
    }

    public String getFeedbackItemContent() {
        return feedbackItemContent;
    }

    public void setFeedbackItemContent(String feedbackItemContent) {
        this.feedbackItemContent = feedbackItemContent;
    }

    public int getFeedbackItemRating() {
        return feedbackItemRating;
    }

    public void setFeedbackItemRating(int feedbackItemRating) {
        this.feedbackItemRating = feedbackItemRating;
    }

}