package practicalTask.dao.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import practicalTask.model.Organization;
import practicalTask.utils.dto.organization.OrgFilterDto;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

/**
 * Реализация интерфейса OrganizationDao
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {


    private final EntityManager entityManager;

    private CriteriaBuilder cb;

    private Predicate mainPredicate;

    @Autowired
    public OrganizationDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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
        Objects.requireNonNull(id, "id");
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
        Objects.requireNonNull(id, "orgId");
        return entityManager.getReference(Organization.class, id);
    }

    /**
     * Поиск организаций по параметрам.
     * При этом необязательные параметры могут отсутствовать
     *
     * @param orgFilterDto дто с параметрами
     * @return список организаций с подходящими параметрами
     * @throws IllegalArgumentException если name не задан
     */
    @Override
    public List<Organization> findAll(OrgFilterDto orgFilterDto) {
        Objects.requireNonNull(orgFilterDto, "orgFilterDto");
        cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Organization> organizationQuery = cb.createQuery(Organization.class);
        Root<Organization> organizationRoot = organizationQuery.from(Organization.class);

        organizationQuery.select(organizationRoot);
        mainPredicate = cb.conjunction();
        mainPredicate = cb.and(mainPredicate, cb.equal(organizationRoot.get("name"), orgFilterDto.getName()));

        addPredicate(organizationRoot.get("inn"), orgFilterDto.getInn());
        organizationQuery.where(mainPredicate);
        return entityManager.createQuery(organizationQuery).getResultList();
    }

    private void addPredicate(Expression expr, String param) {

        if (param != null && !param.isEmpty()) {
            Predicate p = cb.equal(expr, param);
            mainPredicate = cb.and(mainPredicate, p);
        }
    }

    /**
     * Сохраняет данную организацию
     *
     * @param organization сохраняемая сущность
     * @throws IllegalArgumentException если organization == null
     */

    @Override
    public void save(Organization organization) {
        Objects.requireNonNull(organization, "organization");
        entityManager.persist(organization);
    }




}
