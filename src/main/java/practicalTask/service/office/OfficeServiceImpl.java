package practicalTask.service.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practicalTask.dao.office.OfficeDao;
import practicalTask.dao.organization.OrganizationDao;
import practicalTask.model.Office;
import practicalTask.model.Organization;
import practicalTask.service.organization.OrganizationService;
import practicalTask.utils.ArgChecker;
import practicalTask.utils.dto.office.OfficeDto;
import practicalTask.utils.dto.office.OfficeListDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;

    private final OrganizationDao organizationDao;

    @Autowired
    public OfficeServiceImpl(@Qualifier("officeDaoImpl") OfficeDao officeDao, @Qualifier("organizationDaoImpl") OrganizationDao organizationDao) {
        this.officeDao = officeDao;
        this.organizationDao = organizationDao;
    }

    /**
     * Поиск офиса по айди
     *
     * @param id айди офиса
     * @return офис с айди = id
     * @see OfficeDao
     */
    @Override
    public OfficeDto getOffice(Long id) {
        ArgChecker.requireNonNull(id, "id");
        Office office = officeDao.findOne(id);
        return new OfficeDto(office);
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
    public List<OfficeListDto> getOfficeList(Long orgId, String name, String phone, boolean isActive) {
        ArgChecker.requireNonNull(orgId, "orgId");
        organizationDao.findOne(orgId);
        List<Office> officeList = officeDao.findAll(orgId, name, phone, isActive);
        List<OfficeListDto> dtoList = new ArrayList<>();
        for (Office office : officeList) {
            dtoList.add(new OfficeListDto(office.getId(), office.getName(), office.isActive()));
        }
        return dtoList;
    }

    /**
     * Сохраняет новый офис
     *
     * @param orgId  айди организации
     * @param officeDto данные нового офиса
     * @throws IllegalArgumentException, если нет organization с таким айди или входные параметры пусты
     * @see OfficeDao
     * @see OrganizationService
     */

    @Override
    @Transactional
    public void save(Long orgId, OfficeDto officeDto) {
        ArgChecker.requireNonNull(officeDto, "office");
        ArgChecker.requireNonNull(orgId, "orgId");
        Organization organization = organizationDao.findOne(orgId);
        Office newOffice = new Office();
        newOffice.update(officeDto);
        newOffice.setOrganization(organization);
        officeDao.save(newOffice);
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
    @Transactional
    public void update(Long officeId, OfficeDto newOfficeData) {
        ArgChecker.requireNonNull(officeId, "officeId");
        ArgChecker.requireNonNull(newOfficeData, "office");
        Office oldOffice = officeDao.findOne(officeId);
        oldOffice.update(newOfficeData);
        officeDao.update(oldOffice);
    }
}
