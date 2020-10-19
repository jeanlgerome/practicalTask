package practicalTask.service.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practicalTask.dao.organization.OrganizationDao;
import practicalTask.domain.Organization;
import practicalTask.utils.ArgChecker;
import practicalTask.utils.dto.OrganizationDto;

import java.util.ArrayList;
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
     *
     * @param id айди организации
     * @return организацию с айди = id
     * @see OrganizationDao
     */
    @Override
    public OrganizationDto getOrganization(Long id) {
        ArgChecker.requireNonNull(id, "id");
        Organization organization = organizationDao.findOne(id);
        return new OrganizationDto(organization);
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
    public List<OrganizationDto> getOrganizationList(String name, String inn, boolean isActive) {
        ArgChecker.requireNonBlank(name, "name");
        List<Organization> organizationList;
        organizationList = organizationDao.findAll(name, inn, isActive);
        List<OrganizationDto> dtoList = new ArrayList<>();
        for (Organization org: organizationList) {
            dtoList.add(new OrganizationDto(org.getName(), org.getInn(), org.isActive()));
        }
        return dtoList;
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
    @Transactional
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
    @Transactional
    public void update(Long orgId, Organization newOrganizationData) {
        ArgChecker.requireNonNull(orgId, "orgId");
        ArgChecker.requireNonNull(newOrganizationData, "newOrganizationData");
        Organization oldOrganization = organizationDao.findOne(orgId);
        oldOrganization.updateData(newOrganizationData);
        organizationDao.update(oldOrganization);
    }
}
