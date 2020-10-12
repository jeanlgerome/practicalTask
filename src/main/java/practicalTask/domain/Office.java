package practicalTask.domain;

import org.hibernate.validator.constraints.Length;
import practicalTask.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "office", indexes = {@Index(name = "IX_office_organization", columnList = "organization_id"),
        @Index(name = "IX_office_name", columnList = "name"),
        @Index(name = "UX_office_phone", columnList = "phone", unique = true),
        @Index(name = "IX_office_isActive", columnList = "isActive")

})
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @NotNull
    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String name;

    @NotNull
    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String adress;

    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String phone;

    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "office")
    private Set<User> userSet;

    public Office() {
    }

    public Office(Long version, String name, String adress, String phone, boolean isActive) {
        this.version = version;
        this.name = name;
        this.adress = adress;
        this.phone = phone;
        this.isActive = isActive;
    }

}
