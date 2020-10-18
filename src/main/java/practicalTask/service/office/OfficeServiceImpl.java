package practicalTask.service.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practicalTask.dao.office.OfficeDao;
import practicalTask.domain.Office;
import practicalTask.domain.Organization;
import practicalTask.service.organization.OrganizationService;
import practicalTask.utils.ArgChecker;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    @Qualifier("officeDaoImpl")
    OfficeDao officeDao;
    @Autowired
    @Qualifier("organizationServiceImpl")
    OrganizationService organizationService;

    /**
     * Поиск офиса по айди
     * Полученный результат проверяется на пустоту
     *
     * @param id айди офиса
     * @return офис с айди = id
     * @throws IllegalArgumentException, если такой офис не найден
     * @see OfficeDao
     */
    @Override
    public Office getOffice(Long id) {
        Office office = officeDao.findOne(id);

        if (office == null) {
            throw new IllegalArgumentException("Office not found");
        }
        return office;
    }


    /**
     * Поиск офисов по параметрам
     *
     * @param orgId    айди организации. Обязательный параметр
     * @param name     название офиса
     * @param phone    телефон офиса
     * @param isActive статус активности
     * @return список организаций с подходящими параметрами
     * @throws IllegalArgumentException если отсутствуют обязательные параметры
     * @see OfficeDao
     */
    @Override
    public List<Office> getOfficeList(Long orgId, String name, String phone, boolean isActive) {
        organizationService.getOrganization(orgId);
        List<Office> officeList = officeDao.findAll(orgId, name, phone, isActive);
        return officeList;
    }

    /**
     * Сохраняет новый офис
     *
     * @param orgId  айди организации
     * @param office новый офис
     * @throws IllegalArgumentException, если нет organization с таким айди или входные параметры пусты
     * @see OfficeDao
     * @see OrganizationService
     */

    @Override
    public void save(Long orgId, Office office) {
        ArgChecker.requireNonNull(office, "office");
        ArgChecker.requireNonNull(orgId, "orgId");
        Organization organization = organizationService.getOrganization(orgId);
        office.setOrganization(organization);
        officeDao.save(office);
    }

    /**
     * Обновляет старый офис:
     *
     * @param officeId      айди обновляемого офиса
     * @param newOfficeData объект с новыми данными
     * @throws IllegalArgumentException, если officeId или newOfficeData пусты
     * @see OfficeDao
     */
    @Override
    public void update(Long officeId, Office newOfficeData) {
        ArgChecker.requireNonNull(officeId, "officeId");
        ArgChecker.requireNonNull(newOfficeData, "office");
        Office oldOffice = getOffice(officeId);
        oldOffice.update(newOfficeData);
        officeDao.update(oldOffice);
    }
}
