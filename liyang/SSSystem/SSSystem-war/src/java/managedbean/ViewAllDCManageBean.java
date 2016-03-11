package managedbean;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import entity.DistributionCenter;
import session.stateless.ViewAllDCSessionBean;

@Named(value = "ViewAllDCManageBean")
@RequestScoped
public class ViewAllDCManageBean {

    @EJB
    private ViewAllDCSessionBean viewAllDCSessionBean;
    private DistributionCenter selectedDC;

    public ViewAllDCManageBean() {
    }

    public List<DistributionCenter> getAllDistributioncenters() {
        return viewAllDCSessionBean.getAllDistributioncenters();
    }

    public DistributionCenter getSelectedDC() {
        return selectedDC;
    }

    public void setSelectedDC(DistributionCenter selectedDC) {
        this.selectedDC = selectedDC;
    }
}