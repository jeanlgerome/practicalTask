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
import practicalTask.utils.dto.office.OfficeDto;
import practicalTask.utils.dto.office.OfficeFilterDto;
import practicalTask.utils.dto.office.OfficeListDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        Objects.requireNonNull(id, "id");
        Office office = officeDao.findOne(id);
        return new OfficeDto(office);
    }

    /**
     * Поиск офисов по параметрам
     *
     * @param officeFilterDto дто с параметрами
     * @return список организаций с подходящими параметрами
     * @throws IllegalArgumentException если отсутствуют обязательные параметры
     * @see OfficeDao
     */
    @Override
    public List<OfficeListDto> getOfficeList(OfficeFilterDto officeFilterDto) {
        Objects.requireNonNull(officeFilterDto.getOrgId(), "orgId");
        organizationDao.findOne(officeFilterDto.getOrgId());
        List<Office> officeList = officeDao.findAll(officeFilterDto);
        List<OfficeListDto> dtoList = new ArrayList<>();
        for (Office office : officeList) {
            dtoList.add(new OfficeListDto(office.getId(), office.getName(), office.isActive()));
        }
        return dtoList;
    }

    /**
     * Сохраняет новый офис
     *
     * @param officeDto данные нового офиса
     * @throws IllegalArgumentException, если нет organization с таким айди или входные параметры пусты
     * @see OfficeDao
     * @see OrganizationService
     */

    @Override
    @Transactional
    public void save(OfficeDto officeDto) {
        Objects.requireNonNull(officeDto, "office");
        Objects.requireNonNull(officeDto.getOrgId(), "orgId");
        Organization organization = organizationDao.findOne(officeDto.getOrgId());
        Office newOffice = new Office();
        setOfficeData(newOffice, officeDto);
        newOffice.setOrganization(organization);
        officeDao.save(newOffice);
    }

    /**
     * Обновляет старый офис:
     *
     * @param newOfficeData объект с новыми данными
     * @throws IllegalArgumentException, если officeId или newOfficeData пусты
     * @see OfficeDao
     */
    @Override
    @Transactional
    public void update(OfficeDto newOfficeData) {
        Objects.requireNonNull(newOfficeData, "office");
        if (newOfficeData.getId() == null) {
            throw new IllegalArgumentException();
        }
        Office oldOffice = officeDao.findOne(newOfficeData.getId());
        setOfficeData(oldOffice, newOfficeData);
    }

    private void setOfficeData(Office office, OfficeDto newOfficeData) {
        office.setName(newOfficeData.getName());
        office.setAdress(newOfficeData.getAdress());
        office.setPhone(newOfficeData.getPhone());
        office.setActive(newOfficeData.isActive());
    }
}
