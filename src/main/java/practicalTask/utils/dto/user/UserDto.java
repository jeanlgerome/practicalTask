package practicalTask.utils.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import practicalTask.model.User;

import javax.validation.constraints.NotBlank;
import java.util.Objects;


public class UserDto {

    @JsonIgnore
    private Long officeId;

    private Long id;

    @NotBlank
    private String firstName;

    private String secondName;

    private String middleName;

    @NotBlank
    private String position;

    private String phone;

    private Long docCode;

    private String docName;

    private String docNumber;

    private String docDate;

    private Long citizenshipCode;

    private boolean isIdentified;

    public UserDto() {
    }

    /**
     * Конструктор. Используется в сервисе при передаче результата поиска по айди
     */
    public UserDto(User userFromDb) {
        Objects.requireNonNull(userFromDb, "userFromDb");
        this.id = userFromDb.getId();
        this.firstName = userFromDb.getFirstName();
        this.secondName = userFromDb.getSecondName();
        this.middleName = userFromDb.getMiddleName();
        this.position = userFromDb.getPosition();
        this.phone = userFromDb.getPhone();
        this.docCode = userFromDb.getDocCode();
        this.citizenshipCode = userFromDb.getCitizenshipCode();
        if (userFromDb.getDocConcrete() != null && userFromDb.getDocConcrete().getDocType() != null) {
            this.docName = userFromDb.getDocConcrete().getDocType().getDocName();
        } else {
            this.docName = null;
        }
        if (userFromDb.getDocConcrete() != null) {
            this.docNumber = userFromDb.getDocConcrete().getDocNumber();
            this.docDate = userFromDb.getDocConcrete().getDocDate().toString();
        } else {
            this.docNumber = null;
            this.docDate = null;
        }
        this.isIdentified = userFromDb.isIdentified();
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPosition() {
        return position;
    }

    public String getPhone() {
        return phone;
    }

    public Long getDocCode() {
        return docCode;
    }

    public String getDocName() {
        return docName;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public String getDocDate() {
        return docDate;
    }

    public Long getCitizenshipCode() {
        return citizenshipCode;
    }

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDocCode(Long docCode) {
        this.docCode = docCode;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public void setCitizenshipCode(Long citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }
}
