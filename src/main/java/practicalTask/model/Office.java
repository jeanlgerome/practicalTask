package practicalTask.model;


import org.hibernate.validator.constraints.Length;

import practicalTask.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "office", indexes = {@Index(name = "IX_office_organization", columnList = "organization_id"),
        @Index(name = "IX_office_name", columnList = "name"),
        @Index(name = "UX_office_phone", columnList = "phone"),
        @Index(name = "IX_office_isActive", columnList = "isActive")

})

public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Version
    private Long version;


    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String name;

    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String adress;

    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String phone;

    @NotNull
    private boolean isActive;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "office")
    private Set<User> userSet;

    public Office() {
    }

    public Office(String name, String adress, String phone, boolean isActive) {
        this.name = name;
        this.adress = adress;
        this.phone = phone;
        this.isActive = isActive;
    }

    public void update(Office newData) {
        this.name = newData.name;
        this.adress = newData.adress;
        this.phone = newData.phone;
        this.isActive = newData.isActive;
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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}
