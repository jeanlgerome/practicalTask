package practicalTask.utils.dto.user;

public class UserListDto {

    private String firstName;

    private String secondName;

    private String middleName;

    private String position;

    private Long docCode;

    private Long citizenshipCode;

    /**
     * Конструктор. Используется в сервисе при передаче результата поиска с заданными параметреами
     */
    public UserListDto(String firstName, String secondName, String middleName, String position, Long docCode, Long citizenshipCode) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.docCode = docCode;
        this.citizenshipCode = citizenshipCode;
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

    public Long getDocCode() {
        return docCode;
    }

    public Long getCitizenshipCode() {
        return citizenshipCode;
    }
}
