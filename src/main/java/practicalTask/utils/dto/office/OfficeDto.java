package practicalTask.utils.dto.office;

import practicalTask.model.Office;


public class OfficeDto {

    private Long id;

    private String name;

    private String adress;

    private String phone;

    private boolean isActive;

    public OfficeDto() {
    }

    /**
     * Конструктор. Используется в сервисе при передаче результата поиска по айди
     */
    public OfficeDto(Office office) {
        this.id = office.getId();
        this.name = office.getName();
        this.adress = office.getAdress();
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
