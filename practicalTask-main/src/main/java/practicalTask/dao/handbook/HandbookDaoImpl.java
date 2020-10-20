package practicalTask.dao.handbook;

import org.springframework.stereotype.Repository;
import practicalTask.model.Citizenship;
import practicalTask.model.DocType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Реализация интерфейса HandbookDao
 */
@Repository
public class HandbookDaoImpl implements HandbookDao {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * Поиск типов документов
     *
     * @return возвращает список всех типов документов
     */
    @Override
    public List<DocType> findAllDocTypes() {
        Query query = entityManager.createQuery("SELECT d FROM DocType d");
        return query.getResultList();
    }

    /**
     * Поиск гражданств
     *
     * @return возвращает список всех гражданств
     */
    @Override
    public List<Citizenship> findAllCitizenships() {
        Query query = entityManager.createQuery("SELECT c FROM Citizenship c");
        return query.getResultList();
    }


}
