package practicalTask.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import practicalTask.model.Citizenship;
import practicalTask.model.DocConcrete;
import practicalTask.model.DocType;
import practicalTask.model.Office;
import practicalTask.model.User;
import practicalTask.utils.dto.user.UserFilterDto;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

/**
 * Реализация интерфейса UserDao
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    private CriteriaBuilder cb;

    private Predicate mainPredicate;


    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Возвращает пользователя с требуемым айди. Если пользователя нет, то возвращает null,
     *
     * @param id айди
     * @return пользователь с требуемым айди
     * @throws IllegalArgumentException  если id == null
     * @throws IllegalArgumentException  если такой пользователь не найден
     * @throws IllegalArgumentException, если такая организация не найдена
     */

    @Override
    public User findOne(Long id) {
        Objects.requireNonNull(id, "id");
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return user;
    }

    /**
     * Поиск пользователя по параметрам.
     * При этом необязательные параметры могут отсутствовать
     *
     * @param userFilterDto дто с параметрами поиска
     * @return список офисов с подходящими параметрами
     * @throws IllegalArgumentException если officeId не задан
     */
    @Override
    public List<User> findAll(UserFilterDto userFilterDto) {
        Objects.requireNonNull(userFilterDto.getOfficeId(), "officeId");
        cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> userQuery = cb.createQuery(User.class);
        Root<User> userRoot = userQuery.from(User.class);
        Join<User, Office> officeJoin = userRoot.join("office");

        userQuery.select(userRoot);
        mainPredicate = cb.conjunction();
        mainPredicate = cb.and(mainPredicate, cb.equal(officeJoin.get("id"), userFilterDto.getOfficeId()));
        addPredicate(userRoot.get("firstName"), userFilterDto.getFirstName());
        addPredicate(userRoot.get("secondName"), userFilterDto.getLastName());
        addPredicate(userRoot.get("middleName"), userFilterDto.getMiddleName());
        addPredicate(userRoot.get("position"), userFilterDto.getPosition());

        Join<User, DocConcrete> docConcreteJoin = userRoot.join("docConcrete", JoinType.LEFT);
        Join<DocConcrete, DocType> docTypeJoin = docConcreteJoin.join("docType", JoinType.LEFT);
        Join<User, Citizenship> citizenshipJoin = userRoot.join("citizenship", JoinType.LEFT);
        addPredicate(docTypeJoin.get("docCode"), userFilterDto.getDocCode());
        addPredicate(citizenshipJoin.get("citizenshipCode"), userFilterDto.getCitizenshipCode());
        userQuery.where(mainPredicate);
        return entityManager.createQuery(userQuery).getResultList();
    }

    private void addPredicate(Expression expr, String param) {

        if (param != null && !param.isEmpty()) {
            Predicate p = cb.equal(expr, param);
            mainPredicate = cb.and(mainPredicate, p);
        }
    }

    /**
     * Сохраняет данного пользователя
     *
     * @param user сохраняемая сущность
     * @throws IllegalArgumentException если user == null
     */
    @Override
    public void save(User user) {
        Objects.requireNonNull(user, "user");
        entityManager.persist(user);
    }


}
