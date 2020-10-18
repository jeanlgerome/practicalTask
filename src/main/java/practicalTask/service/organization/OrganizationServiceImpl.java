package practicalTask.service.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practicalTask.dao.organization.OrganizationDao;
import practicalTask.domain.Organization;
import practicalTask.utils.ArgChecker;

import java.util.List;

/**
 * Organization Сервис, формирует и предоставляет данные контроллеру
 * использует OrganizationDao
 *
 * @see OrganizationDao
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    @Qualifier("organizationDaoImpl")
    OrganizationDao organizationDao;

    /**
     * Поиск организации по айди. Внутри используется метод organizationDao.findOne
     * Полученный результат проверяется на пустоту
     *
     * @param id айди организации
     * @return организацию с айди = id
     * @throws IllegalArgumentException, если такая организация не найдена
     * @see OrganizationDao
     */
    @Override
    public Organization getOrganization(Long id) {
        ArgChecker.requireNonNull(id, "id");
        Organization organization = organizationDao.findOne(id);

        if (organization == null) {
            throw new IllegalArgumentException("Organization not found");
        }
        return organization;
    }

    /**
     * Поиск по имени и необязательным инн/статусу активности
     * Внутри используется метод organizationDao.findAll
     *
     * @param name     название организации
     * @param inn      инн организации
     * @param isActive статус активности
     * @return список организаций с подходящими параметрами
     * @throws IllegalArgumentException если отсутствуют обязательные параметры
     * @see OrganizationDao
     */
    @Override
    public List<Organization> getOrganizationList(String name, String inn, boolean isActive) {
        ArgChecker.requireNonBlank(name, "name");
        List<Organization> organizationList;
        organizationList = organizationDao.findAll(name, inn, isActive);
        return organizationList;
    }

    /**
     * Сохраняет новую организацию
     * Внутри используется метод organizationDao.save
     *
     * @param organization новая организацая
     * @throws IllegalArgumentException, если organization пуста
     * @see OrganizationDao
     */

    @Override
    public void save(Organization organization) {
        ArgChecker.requireNonNull(organization, "organization");
        organizationDao.save(organization);
    }

    /**
     * Обновляет старую организацию
     *
     * @param orgId               айди обновляемой организации
     *                            Внутри используется методы organizationDao.findOne и organizationDao.update
     * @param newOrganizationData объект с новыми данными
     * @throws IllegalArgumentException если orgId или newOrganizationData пусты
     * @see OrganizationDao
     */
    @Override
    public void update(Long orgId, Organization newOrganizationData) {
        ArgChecker.requireNonNull(orgId, "orgId");
        ArgChecker.requireNonNull(newOrganizationData, "newOrganizationData");
        Organization organizationOld = getOrganization(orgId);
        organizationOld.updateData(newOrganizationData);
        organizationDao.update(organizationOld);
    }
}
