package practicalTask.dao.organization;

import org.springframework.stereotype.Repository;
import practicalTask.domain.Organization;
import practicalTask.utils.ArgChecker;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        return entityManager.find(Organization.class, id);
    }

    /**
     * Поиск списка организаций по параметрам.
     * Если inn или isActive не заданы, то ищет любые организации
     * с заданными параметрами
     *
     * @param name     параметр поиска
     * @param inn      параметр поиска
     * @param isActive параметр поиска
     * @return список организаций с подходящими параметрами
     * @throws IllegalArgumentException если параметры некорректны
     */
    @Override
    public List<Organization> findAll(String name, String inn, boolean isActive) {
        ArgChecker.requireNonBlank(name, "name");
        Query query = entityManager.createQuery("SELECT o FROM Organization o WHERE (o.name like :name ) and (:inn is null"
                + " or o.inn = :inn) and (o.isActive = :isActive)");
        query.setParameter("name", "%" + name + "%");
        query.setParameter("inn", inn);
        query.setParameter("isActive", isActive);
        return query.getResultList();
    }

    /**
     * Сохраняет данную организацию
     *
     * @param organization  сохраняемая сущность
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
     * @param organization  обновляемая сущность
     * @return обновленную организацию
     * @throws IllegalArgumentException если organization == null
     */
    @Override
    public Organization update(Organization organization) {
        ArgChecker.requireNonNull(organization, "organization");
        return entityManager.merge(organization);
    }


}
