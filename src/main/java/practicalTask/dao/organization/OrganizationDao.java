package practicalTask.dao.organization;

import practicalTask.model.Organization;

import java.util.List;

/**
 * Интерфейс OrganizationDao
 * Предоставляет методы для работы с базой организаций
 */
public interface OrganizationDao {

    Organization findOne(final Long id);

    Organization findReference(Long id);

    List<Organization> findAll(String name, String inn, boolean isActive);

    void save(final Organization organization);

    Organization update(final Organization organization);


}
