package practicalTask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import practicalTask.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Entity класс гражданства, содержит код страны citizenshipCode, который однозначно определяет гражданство и
 * связывает гражданство с пользователем.
 * Также содержит String citizenshipName - название страны с данным кодом
 * Сущность связана с User entity, как OneToMany CascadeType.ALL, FetchType.LAZY
 */
@Entity
@Table(name = "citizenship", indexes = {@Index(name = "IX_citizenship_citizenshipCode", columnList = "citizenshipCode")})
public class Citizenship {

    @Id
    private Long citizenshipCode;

    @NotNull
    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String citizenshipName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "citizenship")
    private Set<User> userSet;

    public Citizenship() {
    }

    public Citizenship(@NotNull Long citizenshipCode, @NotNull @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH) String citizenshipName) {
        this.citizenshipCode = citizenshipCode;
        this.citizenshipName = citizenshipName;
    }

    public Long getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Long citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}

