package practicalTask.dao.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import practicalTask.model.Office;
import practicalTask.model.Organization;
import practicalTask.utils.dto.office.OfficeFilterDto;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
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

    private CriteriaBuilder cb;

    private Predicate mainPredicate;

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
        cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> officeQuery = cb.createQuery(Office.class);
        Root<Office> officeRoot = officeQuery.from(Office.class);
        Join<Office, Organization> joinOrganization = officeRoot.join("organization");

        officeQuery.select(officeRoot);
        mainPredicate = cb.conjunction();
        mainPredicate = cb.and(mainPredicate, cb.equal(joinOrganization.get("id"), officeFilterDto.getOrgId()));

        addPredicate(officeRoot.get("name"), officeFilterDto.getName());
        addPredicate(officeRoot.get("phone"), officeFilterDto.getPhone());
        officeQuery.where(mainPredicate);
        return entityManager.createQuery(officeQuery).getResultList();
    }

    private void addPredicate(Expression expr, String param) {

        if (param != null && !param.isEmpty()) {
            Predicate p = cb.equal(expr, param);
            mainPredicate = cb.and(mainPredicate, p);
        }
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

}
