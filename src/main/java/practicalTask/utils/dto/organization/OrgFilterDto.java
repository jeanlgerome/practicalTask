package practicalTask.utils.dto.organization;

import javax.validation.constraints.NotBlank;

public class OrgFilterDto {

    @NotBlank
    private String name;

    private String inn;

    private boolean isActive;

    public OrgFilterDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
