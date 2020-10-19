package practicalTask.utils.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import practicalTask.model.User;
import practicalTask.utils.ArgChecker;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;

    private String firstName;

    private String secondName;

    private String middleName;

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
     * Конструктор. Используется в сервисе при передаче результата поиска с заданными парамтреами
     */
    public UserDto( String firstName, String secondName, String middleName, String position, Long docCode, Long citizenshipCode) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.docCode = docCode;
        this.citizenshipCode = citizenshipCode;
    }

    /**
     * Конструктор. Используется  при передаче данных из контроллера в сервис
     */
    public UserDto(Long id, String firstName, String secondName, String middleName, String position, String phone, Long docCode, String docName, String docNumber, String docDate, Long citizenshipCode, boolean isIdentified) {
        this.id = id;
        this.firstName = ArgChecker.requireNonBlank(firstName, "firstName");
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = ArgChecker.requireNonBlank(position, "position");
        this.phone = phone;
        this.docCode = docCode;
        this.docName = docName;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.citizenshipCode = citizenshipCode;
        this.isIdentified = isIdentified;
    }

    /**
     * Конструктор. Используется в сервисе при передаче результата поиска по айди
     */
    public UserDto(User userFromDb) {
        ArgChecker.requireNonNull(userFromDb, "userFromDb");
        this.id = userFromDb.getId();
        this.firstName = userFromDb.getFirstName();
        this.secondName = userFromDb.getSecondName();
        this.middleName = userFromDb.getMiddleName();
        this.position = userFromDb.getPosition();
        this.phone = userFromDb.getPhone();
        this.docCode = userFromDb.getDocCode();
        this.citizenshipCode = userFromDb.getCitizenshipCode();
        if(userFromDb.getDocConcrete()!=null&&userFromDb.getDocConcrete().getDocType()!=null){
            this.docName = userFromDb.getDocConcrete().getDocType().getDocName();
        }else {
            this.docName = null;
        }
        if(userFromDb.getDocConcrete()!=null){
            this.docNumber = userFromDb.getDocConcrete().getDocNumber();
            this.docDate = userFromDb.getDocConcrete().getDocDate().toString();
        }else {
            this.docNumber = null;
            this.docDate = null;
        }
        this.isIdentified = userFromDb.isIdentified();
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
