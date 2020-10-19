package practicalTask.dao.organization;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import practicalTask.domain.Organization;
import practicalTask.utils.ArgChecker;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Реализация интерфейса OrganizationDao
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * Возвращает организацию с требуемым айди. Если организации нет, то возвращает null,
     * что вызывает выброс исключения в OrganizationService
     *
     * @param id параметр поиска организации
     * @return организацию с требуемым айди
     * @throws IllegalArgumentException если id == null
     */
    @Override
    public Organization findOne(Long id) {
        ArgChecker.requireNonNull(id, "id");
        Organization organization = entityManager.find(Organization.class, id);
        if (organization == null) {
            throw new IllegalArgumentException("Organization not found");
        }
        return organization;
    }

    /**
     * Возвращает ссылку на организацию с требуемым айди. Если организации нет, то возвращает null,
     * что вызывает выброс исключения в OrganizationService
     * Используется в случаях, когда нет необходимости загружать поля сущности
     *
     * @param id параметр поиска организации
     * @return организацию с требуемым айди
     * @throws IllegalArgumentException если id == null
     */
    @Override
    public Organization findReference(Long id) {
        ArgChecker.requireNonNull(id, "orgId");
        return entityManager.getReference(Organization.class, id);
    }

    /**
     * Поиск организаций по параметрам.
     * При этом необязательные параметры могут отсутствовать
     *
     * @param name     обязательный   параметр поиска
     * @param inn      параметр поиска
     * @param isActive параметр поиска
     * @return список организаций с подходящими параметрами
     * @throws IllegalArgumentException если name не задан
     */
    @Override
    public List<Organization> findAll(String name, String inn, boolean isActive) {
        ArgChecker.requireNonBlank(name, "name");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Organization> organizationQuery = cb.createQuery(Organization.class);
        Root<Organization> organizationRoot = organizationQuery.from(Organization.class);

        Predicate mainPredicate = cb.conjunction();
        mainPredicate = cb.and(mainPredicate, cb.equal(organizationRoot.get("name"), name));
        organizationQuery.select(organizationRoot);

        if (inn != null && !inn.isEmpty()) {
            Predicate p = cb.equal(organizationRoot.get("inn"), inn);
            mainPredicate = cb.and(mainPredicate, p);
        }
        organizationQuery.where(mainPredicate);
        return entityManager.createQuery(organizationQuery).getResultList();
    }

    /**
     * Сохраняет данную организацию
     *
     * @param organization сохраняемая сущность
     * @throws IllegalArgumentException если organization == null
     */

    @Override
    public void save(Organization organization) {
        ArgChecker.requireNonNull(organization, "organization");
        entityManager.persist(organization);
    }

    /**
     * Обновляет данную организацию
     *
     * @param organization обновляемая сущность
     * @return обновленную организацию
     * @throws IllegalArgumentException если organization == null
     */
    @Override
    public Organization update(Organization organization) {
        ArgChecker.requireNonNull(organization, "organization");
        return entityManager.merge(organization);
    }


}
