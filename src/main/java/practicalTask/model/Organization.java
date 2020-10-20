package practicalTask.model;

import org.hibernate.validator.constraints.Length;
import practicalTask.utils.Constants;
import practicalTask.utils.dto.organization.OrganizationDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Entity класс организации, содержит все поля-данные.
 * Есть 2 public конструктора с параметрами (один из них без айди для операции save, другой с айди для update)
 * В случае save айди генерируется автоматически, в случае update созданный объект является DTO и
 * Содержит данные для обновления старой сущности, которая ищется по айди
 * <p>
 * В конструкторах и сеттерах проверяются параметры
 * Все String поля имеют ограничение по длине от 1 до 255
 * Имеются индексы для полей name, inn, isActive
 * <p>
 * Сущность связана с Office entity, как OneToMany, FetchType.LAZY, CascadeType.ALL
 */
@Entity
@Table(name = "organization", indexes = {@Index(name = "IX_organization_name", columnList = "name"),
        @Index(name = "UX_organization_inn", columnList = "inn"),
        @Index(name = "IX_organization_isActive", columnList = "isActive")
})

public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Version
    private Long version;

    @NotNull
    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String name;

    @NotNull
    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String fullName;

    @NotNull
    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String inn;

    @NotNull
    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String kpp;

    @NotNull
    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String adress;

    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String phone;

    @NotNull
    private boolean isActive;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "organization")
    private Set<Office> officeSet;

    public Organization() {
    }

    /**
     * Метод обновляет поля в this объекте данными из newData
     *
     * @param newData объект с новыми данными
     * @throws IllegalArgumentException если обнаружены некооректные данные
     */
    public void updateData(OrganizationDto newData) {
        this.name = newData.getName();
        this.fullName = newData.getFullName();
        this.inn = newData.getInn();
        this.kpp = newData.getKpp();
        this.adress = newData.getAdress();
        this.phone = newData.getPhone();
        this.isActive = newData.isActive();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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

    public Set<Office> getOfficeSet() {
        return officeSet;
    }

    public void setOfficeSet(Set<Office> officeSet) {
        this.officeSet = officeSet;
    }
}