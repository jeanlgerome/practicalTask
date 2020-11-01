package practicalTask.service.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practicalTask.dao.organization.OrganizationDao;
import practicalTask.model.Organization;
import practicalTask.utils.dto.organization.OrgFilterDto;
import practicalTask.utils.dto.organization.OrganizationDto;
import practicalTask.utils.dto.organization.OrganizationListDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Organization Сервис, формирует и предоставляет данные контроллеру
 * использует OrganizationDao
 *
 * @see OrganizationDao
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;

    @Autowired
    public OrganizationServiceImpl(@Qualifier("organizationDaoImpl") OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    /**
     * Поиск организации по айди
     *
     * @param id айди организации
     * @return организацию с айди = id
     * @see OrganizationDao
     */
    @Override
    public OrganizationDto getOrganization(Long id) {
        Objects.requireNonNull(id, "id");
        Organization organization = organizationDao.findOne(id);
        return new OrganizationDto(organization);
    }

    /**
     * Поиск по имени и необязательным инн/статусу активности
     *
     * @param orgFilterDto дто с параметрами
     * @return список организаций с подходящими параметрами
     * @throws IllegalArgumentException если отсутствуют обязательные параметры
     * @see OrganizationDao
     */
    @Override
    public List<OrganizationListDto> getOrganizationList(OrgFilterDto orgFilterDto) {
        Objects.requireNonNull(orgFilterDto, "orgFilterDto");
        List<Organization> organizationList;
        organizationList = organizationDao.findAll(orgFilterDto);
        List<OrganizationListDto> dtoList = new ArrayList<>();
        for (Organization org : organizationList) {
            dtoList.add(new OrganizationListDto(org.getName(), org.getInn(), org.isActive()));
        }
        return dtoList;
    }

    /**
     * Сохраняет новую организацию
     *
     * @param organizationDto данные новой организации
     * @throws IllegalArgumentException, если organization пуста
     * @see OrganizationDao
     */

    @Override
    @Transactional
    public void save(OrganizationDto organizationDto) {
        Objects.requireNonNull(organizationDto, "organization");
        Organization organization = new Organization();
        setOrgData(organization, organizationDto);
        organizationDao.save(organization);
    }

    /**
     * Обновляет старую организацию
     *
     * @param newOrganizationData объект с новыми данными
     * @throws IllegalArgumentException если orgId или newOrganizationData пусты
     * @see OrganizationDao
     */
    @Override
    @Transactional
    public void update(OrganizationDto newOrganizationData) {
        Objects.requireNonNull(newOrganizationData.getId());
        Objects.requireNonNull(newOrganizationData, "newOrganizationData");
        Organization oldOrganization = organizationDao.findOne(newOrganizationData.getId());
        setOrgData(oldOrganization, newOrganizationData);

    }

    private void setOrgData(Organization organization, OrganizationDto newData) {
        organization.setName(newData.getName());
        organization.setFullName(newData.getFullName());
        organization.setInn(newData.getInn());
        organization.setKpp(newData.getKpp());
        organization.setAdress(newData.getAdress());
        organization.setPhone(newData.getPhone());
        organization.setActive(newData.isActive());
    }
}