/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.warehouse_entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WenDi
 */
@Entity
public class ReceivingBay implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long receivingBayId;
    private Long distributionCenterId;
    private Double dedicatedArea;

    public Long getReceivingBayId() {
        return receivingBayId;
    }

    public void setReceivingBayId(Long receivingBayid) {
        this.receivingBayId = receivingBayId;
    }

    public Long getDistributionCenterId() {
        return distributionCenterId;
    }

    public void setDistributionCenterId(Long distributionCenterId) {
        this.distributionCenterId = distributionCenterId;
    }
    
    public Double getDedicatedArea() {
        return dedicatedArea;
    }
    
    public void setDedicatedArea() {
        this.dedicatedArea = dedicatedArea;
    }

}