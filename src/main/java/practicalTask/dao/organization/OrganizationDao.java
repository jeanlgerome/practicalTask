package practicalTask.dao.organization;

import practicalTask.model.Organization;
import practicalTask.utils.dto.organization.OrgFilterDto;

import java.util.List;

/**
 * Интерфейс OrganizationDao
 * Предоставляет методы для работы с базой организаций
 */
public interface OrganizationDao {

    Organization findOne(final Long id);

    Organization findReference(Long id);

    List<Organization> findAll(OrgFilterDto orgFilterDto);

    void save(final Organization organization);



}
