package practicalTask.dao.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import practicalTask.domain.*;
import practicalTask.utils.ArgChecker;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Реализация интерфейса UserDao
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * Возвращает пользователя с требуемым айди. Если пользователя нет, то возвращает null,
     *
     * @param id айди
     * @return пользователь с требуемым айди
     * @throws IllegalArgumentException если id == null
     * @throws IllegalArgumentException если такой пользователь не найден
     *  @throws IllegalArgumentException, если такая организация не найдена
     */
    @Transactional
    @Override
    public User findOne(Long id) {
        ArgChecker.requireNonNull(id, "id");
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
     * @param officeId        обязательный параметр поиска
     * @param firstName       имя
     * @param lastName        имя
     * @param middleName      имя
     * @param position        должность
     * @param docCode         код документа
     * @param citizenshipCode код гражд.
     * @return список офисов с подходящими параметрами
     * @throws IllegalArgumentException если officeId не задан
     */
    @Override
    public List<User> findAll(Long officeId, String firstName, String lastName, String middleName, String position,
                              String docCode, String citizenshipCode) {
        ArgChecker.requireNonNull(officeId, "officeId");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> userQuery = cb.createQuery(User.class);
        Root<User> userRoot = userQuery.from(User.class);
        Join<User, Office> officeJoin = userRoot.join("office");

        userQuery.select(userRoot);
        Predicate mainPredicate = cb.conjunction();
        mainPredicate = cb.and(mainPredicate, cb.equal(officeJoin.get("id"), officeId));

        if (firstName != null && !firstName.isEmpty()) {
            Predicate p = cb.equal(userRoot.get("firstName"), firstName);
            mainPredicate = cb.and(mainPredicate, p);
        }
        if (lastName != null && !lastName.isEmpty()) {
            Predicate p = cb.equal(userRoot.get("lastName"), lastName);
            mainPredicate = cb.and(mainPredicate, p);
        }
        if (middleName != null && !middleName.isEmpty()) {
            Predicate p = cb.equal(userRoot.get("middleName"), middleName);
            mainPredicate = cb.and(mainPredicate, p);
        }
        if (position != null && !position.isEmpty()) {
            Predicate p = cb.equal(userRoot.get("position"), position);
            mainPredicate = cb.and(mainPredicate, p);
        }
        if (docCode != null && !docCode.isEmpty()) {
            Join<User, DocConcrete> docConcreteJoin = userRoot.join("docConcrete");
            Join<DocConcrete, DocType> docTypeJoin = docConcreteJoin.join("docType");
            Predicate p = cb.equal(docTypeJoin.get("docCode"), docCode);
            mainPredicate = cb.and(mainPredicate, p);
        }
        if (citizenshipCode != null && !citizenshipCode.isEmpty()) {
            Join<User, Citizenship> citizenshipJoin = userRoot.join("citizenship");
            Predicate p = cb.equal(citizenshipJoin.get("citizenshipCode"), citizenshipCode);
            mainPredicate = cb.and(mainPredicate, p);
        }
        userQuery.where(mainPredicate);

        return entityManager.createQuery(userQuery).getResultList();
    }

    /**
     * Сохраняет данного пользователя
     *
     * @param user сохраняемая сущность
     * @throws IllegalArgumentException если user == null
     */
    @Override
    public void save(User user) {
        ArgChecker.requireNonNull(user, "user");
        entityManager.persist(user);
    }

    /**
     * Обновляет данного пользователя
     *
     * @param user обновляемая сущность
     * @return обновленный пользователь
     * @throws IllegalArgumentException если user == null
     */
    @Override
    public User update(User user) {
        ArgChecker.requireNonNull(user, "user");
        return entityManager.merge(user);
    }
}
