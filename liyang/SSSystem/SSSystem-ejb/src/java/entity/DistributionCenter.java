package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class DistributionCenter implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long distributionCenterId;
    private String name;
    private String address;
    private String country;
    private String area;
    private String storageFacilities;
    private int numberOfShelves;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "distributionCenter")
    private Collection<DistributionCenterInventory> distributionCenterInventorys = new ArrayList<DistributionCenterInventory>();

    public Long getDistributionCenterId() {
        return distributionCenterId;
    }

    public void setDistributionCenterId(Long distributionCenterId) {
        this.distributionCenterId = distributionCenterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStorageFacilities() {
        return storageFacilities;
    }

    public void setStorageFacilities(String storageFacilities) {
        this.storageFacilities = storageFacilities;
    }

    public int getNumberOfShelves() {
        return numberOfShelves;
    }

    public void setNumberOfShelves(int numberOfShelves) {
        this.numberOfShelves = numberOfShelves;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (distributionCenterId != null ? distributionCenterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DistributionCenter)) {
            return false;
        }
        DistributionCenter other = (DistributionCenter) object;
        if ((this.distributionCenterId == null && other.distributionCenterId != null) || (this.distributionCenterId != null && !this.distributionCenterId.equals(other.distributionCenterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "warehouse_entity.DistributionCenter[ id=" + distributionCenterId + " ]";
    }

    public Collection<DistributionCenterInventory> getDistributionCenterInventorys() {
        return distributionCenterInventorys;
    }

    public void setDistributionCenterInventorys(Collection<DistributionCenterInventory> distributionCenterInventorys) {
        this.distributionCenterInventorys = distributionCenterInventorys;
    }

}