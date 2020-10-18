package practicalTask.dao.user;

import practicalTask.domain.User;

import java.util.List;

/**
 * Интерфейс UserDao
 * Предоставляет методы для работы с базой пользователей
 */
public interface UserDao {

    User findOne(final Long id);


    List<User> findAll(Long officeId, String firstName, String lastName, String middleName, String position,
                       String docCode, String citizenshipCode);

    void save(final User user);

    User update(final User user);
}
