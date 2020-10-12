package practicalTask.service.organization;

import practicalTask.domain.Organization;

import java.util.List;

/**
 * Интерфейс Organization сервиса
 */
public interface OrganizationService {

    /**
     * Поиск организации по айди
     *
     * @param id айди организации
     * @return организацию с айди = id
     */
    Organization getOrganization(Long id);

    /**
     * Поиск по имени и необязательным инн/статусу активности
     *
     * @param name     название организации
     * @param inn      инн организации
     * @param isActive статус активности
     * @return список организаций с подходящими параметрами
     */
    List<Organization> getOrganizationList(String name, String inn, boolean isActive);

    /**
     * Сохраняет новую организацию
     * Внутри используется метод organizationDao.save
     *
     * @param organization новая организацая
     * @throws IllegalArgumentException, если organization пуста
     */
    void save(Organization organization);


    /**
     * Обновляет старую организацию:
     * Использует айди из объекта с новыми данными, чтоб найти старую сущность, затем обновляет поля в старой сущности
     * и сохраняет её.
     *
     * @param organization - объект с новыми данными
     * @throws IllegalArgumentException, если newOrganizationData пуста
     */
    void update(Organization organization);

}
