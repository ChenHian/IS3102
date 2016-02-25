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

/**
 *
 * @author QianJun
 */
@Entity
public class FeedbackOnSupplier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long feedbackOnSupplierId;
    private String feedbackContent;
    private Date date;
    private String feedbackType;

    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeedbackOnSupplier)) {
            return false;
        }
        FeedbackOnSupplier other = (FeedbackOnSupplier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
*/
    @Override
    public String toString() {
        return "entity.FeedbackOnSupplier[ id=" + getFeedbackOnSupplierId() + " ]";
    }

    /**
     * @return the feedbackOnSupplierId
     */
    public Long getFeedbackOnSupplierId() {
        return feedbackOnSupplierId;
    }

    /**
     * @param feedbackOnSupplierId the feedbackOnSupplierId to set
     */
    public void setFeedbackOnSupplierId(Long feedbackOnSupplierId) {
        this.feedbackOnSupplierId = feedbackOnSupplierId;
    }

    /**
     * @return the feedbackContent
     */
    public String getFeedbackContent() {
        return feedbackContent;
    }

    /**
     * @param feedbackContent the feedbackContent to set
     */
    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    /**
     * @return the feedbackType
     */
    public String getFeedbackType() {
        return feedbackType;
    }

    /**
     * @param feedbackType the feedbackType to set
     */
    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }


    
}
