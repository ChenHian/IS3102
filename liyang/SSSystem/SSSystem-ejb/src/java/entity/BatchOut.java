/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author WenDi
 */
@Entity
public class BatchOut implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long batchOutId;
    private Long batchId;
    
    @ManyToOne
    private BatchReceipt br = new BatchReceipt();

    public BatchReceipt getBr() {
        return br;
    }

    public void setBr(BatchReceipt br) {
        this.br = br;
    }
    
    

    public Long getbatchOutId() {
        return batchOutId;
    }

    public void setbatchOutId(Long batchOutId) {
        this.batchOutId = batchOutId;
    }
    
    public Long getBatchId() {
        return batchId;
    }
    
    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

 
    
}
