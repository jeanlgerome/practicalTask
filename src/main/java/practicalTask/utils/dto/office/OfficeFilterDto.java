package practicalTask.utils.dto.office;

import javax.validation.constraints.NotNull;

public class OfficeFilterDto {

    @NotNull
    private Long orgId;

    private String name;

    private String phone;

    boolean isActive;

    public OfficeFilterDto() {
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getOrgId() {
        return orgId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isActive() {
        return isActive;
    }
}
