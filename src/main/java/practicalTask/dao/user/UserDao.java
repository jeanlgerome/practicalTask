package practicalTask.dao.user;

import practicalTask.model.User;
import practicalTask.utils.dto.user.UserFilterDto;

import java.util.List;

/**
 * Интерфейс UserDao
 * Предоставляет методы для работы с базой пользователей
 */
public interface UserDao {

    User findOne(final Long id);


    List<User> findAll(UserFilterDto userFilterDto);

    void save(final User user);

    User update(final User user);
}
