package practicalTask.dao.office;

import practicalTask.model.Office;
import practicalTask.utils.dto.office.OfficeFilterDto;

import java.util.List;

/**
 * Интерфейс OfficeDao
 * Предоставляет методы для работы с базой офисов
 */
public interface OfficeDao {

    Office findOne(final Long id);

    List<Office> findAll(OfficeFilterDto officeFilterDto);

    void save(final Office office);

    Office update(final Office office);
}
