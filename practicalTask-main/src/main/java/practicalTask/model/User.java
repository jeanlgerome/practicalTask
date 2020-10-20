package practicalTask.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import practicalTask.utils.Constants;
import practicalTask.utils.dto.UserDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "usr", indexes = {@Index(name = "IX_usr_office", columnList = "office_id"),
        @Index(name = "IX_usr_firstName", columnList = "firstName"),
        @Index(name = "IX_usr_secondName", columnList = "secondName"),
        @Index(name = "IX_usr_middleName", columnList = "middleName"),
        @Index(name = "IX_usr_position", columnList = "position"),
        @Index(name = "IX_usr_office", columnList = "office_id"),

        @Index(name = "IX_usr_citizenship", columnList = "citizenship_code")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Version
    private Long version;

    @NotNull
    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String firstName;

    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String secondName;

    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String middleName;

    @NotNull
    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String position;

    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String phone;

    @NotNull
    private boolean isIdentified;

    @OneToOne(cascade = CascadeType.ALL)
    private DocConcrete docConcrete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_code")
    private Citizenship citizenship;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;

    public User() {
    }

    public User(String firstName, String secondName, String middleName, String position, String phone, boolean isIdentified) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.isIdentified = isIdentified;
    }

    public User(UserDto userDto) {
        this.firstName = userDto.getFirstName();
        this.secondName = userDto.getSecondName();
        this.middleName = userDto.getMiddleName();
        this.position = userDto.getPosition();
        this.phone = userDto.getPhone();
        this.isIdentified = userDto.isIdentified();
    }


    public void update(UserDto newUserData) {
        this.firstName = newUserData.getFirstName();
        this.secondName = newUserData.getSecondName();
        this.middleName = newUserData.getMiddleName();
        this.position = newUserData.getPosition();
        this.phone = newUserData.getPhone();
        this.isIdentified = newUserData.isIdentified();
    }

    public Long getDocCode() {
        if (docConcrete != null && docConcrete.getDocType() != null) {
            return docConcrete.getDocType().getDocCode();
        }
        return null;
    }

    public Long getCitizenshipCode() {
        if (citizenship != null && citizenship.getCitizenshipCode() != null) {
            return citizenship.getCitizenshipCode();
        }
        return null;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DocConcrete getDocConcrete() {
        return docConcrete;
    }

    public void setDocConcrete(DocConcrete docConcrete) {
        this.docConcrete = docConcrete;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Office getOffice() {
        return office;
    }

    public Citizenship getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(Citizenship citizenship) {
        this.citizenship = citizenship;
    }

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }
}