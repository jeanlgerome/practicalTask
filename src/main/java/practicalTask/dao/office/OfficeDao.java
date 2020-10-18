package practicalTask.dao.office;

import practicalTask.domain.Office;

import java.util.List;

/**
 * Интерфейс OfficeDao
 * Предоставляет методы для работы с базой офисов
 */
public interface OfficeDao {

    Office findOne(final Long id);

    List<Office> findAll(Long orgId, String name, String phone, boolean isActive);

    void save(final Office office);

    Office update(final Office office);
}
