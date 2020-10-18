package practicalTask.dao.handbook;

import practicalTask.domain.Citizenship;
import practicalTask.domain.DocType;

import java.util.List;

/**
 * Интерфейс HandbookDao
 * Предоставляет методы для работы с базой справочников
 */
public interface HandbookDao {

    List<DocType> findAllDocTypes();

    List<Citizenship> findAllCitizenships();

}
