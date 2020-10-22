package practicalTask.utils.dto.user;

public class UserFilterDto {

    private Long officeId;

    private String firstName;

    private String lastName;

    private String middleName;

    private String position;

    private String docCode;

    private String citizenshipCode;

    public UserFilterDto() {
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeI) {
        this.officeId = officeI;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }
}
