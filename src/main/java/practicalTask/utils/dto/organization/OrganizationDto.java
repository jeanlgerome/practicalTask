package practicalTask.utils.dto.organization;

import practicalTask.model.Organization;

import javax.validation.constraints.NotBlank;


public class OrganizationDto {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String fullName;

    @NotBlank
    private String inn;

    @NotBlank
    private String kpp;

    @NotBlank
    private String adress;

    private String phone;

    private boolean isActive;

    public OrganizationDto() {
    }

    /**
     * Конструктор. Используется в сервисе при передаче результата поиска по айди и при передаче данных из save, update
     */
    public OrganizationDto(Organization organization) {
        this.id = organization.getId();
        this.name = organization.getName();
        this.fullName = organization.getFullName();
        this.inn = organization.getInn();
        this.kpp = organization.getKpp();
        this.adress = organization.getAdress();
        this.phone = organization.getPhone();
        this.isActive = organization.isActive();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
