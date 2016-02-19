package managedbean;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import session.stateless.RoleSessionBean;

@Named(value = "roleManagerBean")
@RequestScoped
public class RoleManagerBean {

    @EJB
    private RoleSessionBean roleSessionBean;
    private String roleName;
    private String roleNameLength;
    private String statusMessage;
    private Long newRoleId;
    private boolean value1;
    private boolean value2;
    private boolean value3;
    private boolean value4;
    private boolean value5;
    private boolean value6;
    private boolean value7;
    private boolean value8;
    private boolean value9;
    private boolean value10;
    private boolean value11;
    private boolean value12;
    private boolean value13;
    private boolean value14;
    private boolean value15;
    private boolean value16;
    private boolean value17;
    private boolean value18;
    private boolean value19;
    private boolean value20;
    private boolean value21;
    private boolean value22;
    private boolean value23;
    private boolean value24;
    private boolean value25;
    private boolean value26;
    private boolean value27;

    public RoleManagerBean() {
        roleNameLength = "Current role name length is less than 4.";
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String eventName) {
        this.roleName = eventName;
    }

    public void setEventNameLength(String eventNameLength) {
        this.roleNameLength = eventNameLength;
    }

    public String getEventNameLength() {
        return roleNameLength;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Long getNewRoleId() {
        return newRoleId;
    }

    public void setNewRoleId(Long newRoleId) {
        this.newRoleId = newRoleId;
    }

    public boolean isValue1() {
        return value1;
    }

    public void setValue1(boolean value1) {
        this.value1 = value1;
    }

    public boolean isValue2() {
        return value2;
    }

    public void setValue2(boolean value2) {
        this.value2 = value2;
    }

    public boolean isValue3() {
        return value3;
    }

    public void setValue3(boolean value3) {
        this.value3 = value3;
    }

    public boolean isValue4() {
        return value4;
    }

    public void setValue4(boolean value4) {
        this.value4 = value4;
    }

    public boolean isValue5() {
        return value5;
    }

    public void setValue5(boolean value5) {
        this.value5 = value5;
    }

    public boolean isValue6() {
        return value6;
    }

    public void setValue6(boolean value6) {
        this.value6 = value6;
    }

    public boolean isValue7() {
        return value7;
    }

    public void setValue7(boolean value7) {
        this.value7 = value7;
    }

    public boolean isValue8() {
        return value8;
    }

    public void setValue8(boolean value8) {
        this.value8 = value8;
    }

    public boolean isValue9() {
        return value9;
    }

    public void setValue9(boolean value9) {
        this.value9 = value9;
    }

    public boolean isValue10() {
        return value10;
    }

    public void setValue10(boolean value10) {
        this.value10 = value10;
    }

    public boolean isValue11() {
        return value11;
    }

    public void setValue11(boolean value11) {
        this.value11 = value11;
    }

    public boolean isValue12() {
        return value12;
    }

    public void setValue12(boolean value12) {
        this.value12 = value12;
    }

    public boolean isValue13() {
        return value13;
    }

    public void setValue13(boolean value13) {
        this.value13 = value13;
    }

    public boolean isValue14() {
        return value14;
    }

    public void setValue14(boolean value14) {
        this.value14 = value14;
    }

    public boolean isValue15() {
        return value15;
    }

    public void setValue15(boolean value15) {
        this.value15 = value15;
    }

    public boolean isValue16() {
        return value16;
    }

    public void setValue16(boolean value16) {
        this.value16 = value16;
    }

    public boolean isValue17() {
        return value17;
    }

    public void setValue17(boolean value17) {
        this.value17 = value17;
    }

    public boolean isValue18() {
        return value18;
    }

    public void setValue18(boolean value18) {
        this.value18 = value18;
    }

    public boolean isValue19() {
        return value19;
    }

    public void setValue19(boolean value19) {
        this.value19 = value19;
    }

    public boolean isValue20() {
        return value20;
    }

    public void setValue20(boolean value20) {
        this.value20 = value20;
    }

    public boolean isValue21() {
        return value21;
    }

    public void setValue21(boolean value21) {
        this.value21 = value21;
    }

    public boolean isValue22() {
        return value22;
    }

    public void setValue22(boolean value22) {
        this.value22 = value22;
    }

    public boolean isValue23() {
        return value23;
    }

    public void setValue23(boolean value23) {
        this.value23 = value23;
    }

    public boolean isValue24() {
        return value24;
    }

    public void setValue24(boolean value24) {
        this.value24 = value24;
    }

    public boolean isValue25() {
        return value25;
    }

    public void setValue25(boolean value25) {
        this.value25 = value25;
    }

    public boolean isValue26() {
        return value26;
    }

    public void setValue26(boolean value26) {
        this.value26 = value26;
    }

    public boolean isValue27() {
        return value27;
    }

    public void setValue27(boolean value27) {
        this.value27 = value27;
    }

    
    
    public void saveNewRole(ActionEvent event) {
        newRoleId = roleSessionBean.addNewRole(roleName, value1, value2, value3, value4, value5, value6, value7, value8, 
                value9, value10, value11, value12, value13, value14, value15, value16, value17, value18, value19, value20,
                value21, value22, value23, value24, value25, value26, value27);
        statusMessage = "New Role Saved Successfully";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Add New Role Result: "
                + statusMessage + " (New Role ID is " + newRoleId + ")", ""));
    }
}
