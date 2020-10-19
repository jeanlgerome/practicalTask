package practicalTask.dao.office;

import org.springframework.stereotype.Repository;
import practicalTask.model.Office;
import practicalTask.model.Organization;
import practicalTask.utils.ArgChecker;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Реализация интерфейса OfficeDao
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * Возвращает офис с требуемым айди. Если офиса нет, то возвращает null,
     *
     * @param id параметр поиска офиса
     * @return офис с требуемым айди
     * @throws IllegalArgumentException  если id == null
     * @throws IllegalArgumentException, если такой офис не найден
     */
    @Override
    public Office findOne(Long id) {
        ArgChecker.requireNonNull(id, "id");
        Office office = entityManager.find(Office.class, id);
        if (office == null) {
            throw new IllegalArgumentException("Office not found");
        }
        return office;
    }

    /**
     * Поиск офиса по параметрам.
     * При этом необязательные параметры могут отсутствовать
     *
     * @param orgId    айди организации. Обязательный параметр поиска
     * @param name     название
     * @param phone    телефон
     * @param isActive активность
     * @return список офисов с подходящими параметрами
     * @throws IllegalArgumentException если orgId не задан
     */
    @Override
    public List<Office> findAll(Long orgId, String name, String phone, boolean isActive) {
        ArgChecker.requireNonNull(orgId, "orgId");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> officeQuery = cb.createQuery(Office.class);
        Root<Office> officeRoot = officeQuery.from(Office.class);
        Join<Office, Organization> joinOrganization = officeRoot.join("organization");

        officeQuery.select(officeRoot);
        Predicate mainPredicate = cb.conjunction();
        mainPredicate = cb.and(mainPredicate, cb.equal(joinOrganization.get("id"), orgId));

        if (name != null && !name.isEmpty()) {
            Predicate p = cb.like(officeRoot.get("name"), name);
            mainPredicate = cb.and(mainPredicate, p);
        }
        if (phone != null && !phone.isEmpty()) {
            Predicate p = cb.like(officeRoot.get("phone"), phone);
            mainPredicate = cb.and(mainPredicate, p);
        }
        officeQuery.where(mainPredicate);
        return entityManager.createQuery(officeQuery).getResultList();
    }

    /**
     * Сохраняет данный офис
     *
     * @param office сохраняемая сущность
     * @throws IllegalArgumentException если office == null
     */
    @Override
    public void save(Office office) {
        ArgChecker.requireNonNull(office, "office");
        entityManager.persist(office);
    }

    /**
     * Обновляет данный офис
     *
     * @param office обновляемая сущность
     * @return обновленный офис
     * @throws IllegalArgumentException если office == null
     */
    @Override
    public Office update(Office office) {
        ArgChecker.requireNonNull(office, "office");
        return entityManager.merge(office);
    }
}
