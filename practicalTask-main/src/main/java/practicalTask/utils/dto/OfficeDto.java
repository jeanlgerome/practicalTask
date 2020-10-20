package practicalTask.utils.dto;

import practicalTask.model.Office;
import practicalTask.utils.ArgChecker;


public class OfficeDto {

    private Long id;

    private String name;

    private String adress;

    private String phone;

    private boolean isActive;

    /**
     * Конструктор. Используется в сервисе при передаче результата поиска с заданными парамтреами
     */
    public OfficeDto(Long id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    /**
     * Конструктор. Используется в сервисе при передаче результата поиска по айди
     */
    public OfficeDto(Office office) {
        this.id = office.getId();
        this.name = office.getName();
        this.adress =office.getAdress();
        this.phone = office.getPhone();
        this.isActive = office.isActive();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isActive() {
        return isActive;
    }
}
