package practicalTask.utils.dto.organization;

public class OrganizationListDto {

    private String name;

    private String inn;

    private boolean isActive;

    /**
     * Конструктор. Используется в сервисе при передаче результата поиска с заданными парамтреами
     */
    public OrganizationListDto(String name, String inn, boolean isActive) {
        this.name = name;
        this.inn = inn;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public String getInn() {
        return inn;
    }

    public boolean isActive() {
        return isActive;
    }
}
