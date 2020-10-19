package practicalTask.utils.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import practicalTask.model.Organization;
import practicalTask.utils.ArgChecker;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationDto {

    private Long id;

    private String name;

    private String fullName;

    private String inn;

    private String kpp;

    private String adress;

    private String phone;

    private boolean isActive;

    /**
     * Конструктор. Используется в сервисе при передаче результата поиска с заданными парамтреами
     */
    public OrganizationDto(String name, String inn, boolean isActive) {
        this.name = ArgChecker.requireNonBlank(name, "name");
        this.inn = ArgChecker.requireNonBlank(inn, "inn");
        this.isActive = isActive;
    }

    /**
     * Конструктор. Используется в сервисе при передаче результата поиска по айди
     */
    public OrganizationDto(Organization organization) {
        this.id = ArgChecker.requireNonNull(organization.getId(), "Id");
        this.name = ArgChecker.requireNonBlank(organization.getName(), "name");
        this.fullName = ArgChecker.requireNonBlank(organization.getFullName(), "fullName");
        this.inn = ArgChecker.requireNonBlank(organization.getInn(), "inn");
        this.kpp = ArgChecker.requireNonBlank(organization.getKpp(), "kpp");
        this.adress = ArgChecker.requireNonBlank(organization.getAdress(), "address");
        this.phone = organization.getPhone();
        this.isActive = organization.isActive();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getInn() {
        return inn;
    }

    public String getKpp() {
        return kpp;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isActive() {
        return isActive;
    }
}
