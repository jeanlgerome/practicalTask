package practicalTask.dao.organization;

import practicalTask.domain.Organization;

import java.util.List;

/**
 * Интерфейс OrganizationDao
 */
public interface OrganizationDao {

    Organization findOne(final Long id);

    List<Organization> findAll(String name, String inn, boolean isActive);

    void save(final Organization entity);

    Organization update(final Organization entity);
}
