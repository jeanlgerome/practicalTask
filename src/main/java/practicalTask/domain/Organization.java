package practicalTask.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import practicalTask.controllers.OrganizationController;
import practicalTask.utils.ArgChecker;
import practicalTask.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity класс организации, содержит все поля-данные.
 * Есть 2 public конструктора с параметрами (один из них без айди для операции save, другой с айди для update)
 * В случае save айди генерируется автоматически, в случае update созданный объект является DTO и
 * Содержит данные для обновления старой сущности, которая ищется по айди
 *
 * В конструкторах и сеттерах проверяются параметры
 * Все String поля имеют ограничение по длине от 1 до 255
 * Имеются индексы для полей name, inn, isActive
 *
 * Сущность связана с Office entity, как OneToMany, FetchType.LAZY, CascadeType.ALL
 */
@Entity
@Table(name = "organization", indexes = {@Index(name = "IX_organization_name", columnList = "name"),
        @Index(name = "UX_organization_inn", columnList = "inn", unique = true),
        @Index(name = "IX_organization_isActive", columnList = "isActive")
})
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    private boolean isActive;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "organization")
    private Set<Office> officeSet;

    /**
     * Конструктор, используется в контроллере OrganizationController в методе saveNewOrganization
     * В него передаются пареметры из POST запроса, затем сущность сохраняется
     *
     * @param name
     * @param fullName
     * @param inn
     * @param kpp
     * @param adress
     * @param phone
     * @param isActive
     * @throws IllegalArgumentException если обнаружены некооректные параметры
     * @see OrganizationController
     */
    public Organization(String name, String fullName, String inn, String kpp, String adress, String phone, boolean isActive) {
        this.name = ArgChecker.requireNonBlank(name, "name");
        this.fullName = ArgChecker.requireNonBlank(fullName, "fullName");
        this.inn = ArgChecker.requireNonBlank(inn, "inn");
        this.kpp = ArgChecker.requireNonBlank(kpp, "kpp");
        this.adress = ArgChecker.requireNonBlank(adress, "adress");
        this.phone = (phone == null || phone.trim().isEmpty()) ? "no phone" : phone;
        this.isActive = isActive;
        this.officeSet = new HashSet<>();
    }

    /**
     * Конструктор, используется в контроллере OrganizationController в методе updateOrganization
     * В него передаются пареметры из POST запроса, объект выступает в роли DTO, данные из него
     * используется для поиска и обновления старой сущности
     *
     * @param id
     * @param name
     * @param fullName
     * @param inn
     * @param kpp
     * @param adress
     * @param phone
     * @param isActive
     * @throws IllegalArgumentException если обнаружены некооректные параметры
     * @see OrganizationController
     */
    public Organization(Long id, String name, String fullName, String inn, String kpp, String adress, String phone, boolean isActive) {
        this(name, fullName, inn, kpp, adress, phone, true);
        ArgChecker.requireNonNull(id, "id");
        this.setId(id);
    }

    public Organization() {
    }

    /**
     * Метод обновляет поля в this объекте данными из newData
     *
     * @param newData объект с новыми данными
     * @throws IllegalArgumentException если обнаружены некооректные данные
     */
    public void updateData(Organization newData) {
        this.name = ArgChecker.requireNonBlank(newData.name, "name");
        this.fullName = ArgChecker.requireNonBlank(newData.fullName, "fullName");
        this.inn = ArgChecker.requireNonBlank(newData.inn, "inn");
        this.kpp = ArgChecker.requireNonBlank(newData.kpp, "kpp");
        this.adress = ArgChecker.requireNonBlank(newData.adress, "adress");
        this.phone = (newData.phone == null || newData.phone.trim().isEmpty()) ? "no phone" : newData.phone;
        this.isActive = newData.isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = ArgChecker.requireNonNull(id, "id");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = ArgChecker.requireNonBlank(name, "name");
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = ArgChecker.requireNonBlank(fullName, "fullName");
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = ArgChecker.requireNonBlank(inn, "inn");
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = ArgChecker.requireNonBlank(kpp, "kpp");
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = ArgChecker.requireNonBlank(adress, "adress");
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = ArgChecker.requireNonBlank(phone, "phone");
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
