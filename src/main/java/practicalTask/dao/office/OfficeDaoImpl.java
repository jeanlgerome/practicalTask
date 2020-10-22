package practicalTask.dao.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import practicalTask.model.Office;
import practicalTask.model.Organization;
import practicalTask.utils.dto.office.OfficeFilterDto;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

/**
 * Реализация интерфейса OfficeDao
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {


    private final EntityManager entityManager;

    @Autowired
    public OfficeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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
        Objects.requireNonNull(id, "id");
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
     * @param officeFilterDto дто с параметрами
     * @return список офисов с подходящими параметрами
     * @throws IllegalArgumentException если orgId не задан
     */
    @Override
    public List<Office> findAll(OfficeFilterDto officeFilterDto) {
        Objects.requireNonNull(officeFilterDto.getOrgId(), "orgId");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> officeQuery = cb.createQuery(Office.class);
        Root<Office> officeRoot = officeQuery.from(Office.class);
        Join<Office, Organization> joinOrganization = officeRoot.join("organization");

        officeQuery.select(officeRoot);
        Predicate mainPredicate = cb.conjunction();
        mainPredicate = cb.and(mainPredicate, cb.equal(joinOrganization.get("id"), officeFilterDto.getOrgId()));

        if (officeFilterDto.getName() != null && !officeFilterDto.getName().isEmpty()) {
            Predicate p = cb.like(officeRoot.get("name"), officeFilterDto.getName());
            mainPredicate = cb.and(mainPredicate, p);
        }
        if (officeFilterDto.getPhone() != null && !officeFilterDto.getPhone().isEmpty()) {
            Predicate p = cb.like(officeRoot.get("phone"), officeFilterDto.getPhone());
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
        Objects.requireNonNull(office, "office");
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
        Objects.requireNonNull(office, "office");
        return entityManager.merge(office);
    }
}
