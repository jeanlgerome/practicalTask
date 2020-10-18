package practicalTask.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practicalTask.dao.office.OfficeDao;
import practicalTask.dao.user.UserDao;
import practicalTask.domain.User;
import practicalTask.service.handbookService.HandbookService;
import practicalTask.service.office.OfficeService;
import practicalTask.utils.ArgChecker;
import practicalTask.utils.dto.UserDto;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userDaoImpl")
    UserDao userDao;
    @Autowired
    @Qualifier("officeServiceImpl")
    OfficeService officeService;
    @Autowired
    @Qualifier("handbookServiceImpl")
    HandbookService handbookService;

    /**
     * Поиск пользователя по айди
     * Полученный результат проверяется на пустоту
     * Здесь возвращается дто, т.к. иначе выбрасывается exception при трансляции юзера в json,
     * из-за связей с документом и гражданством
     *
     * @param id айди пользователя
     * @return Dto пользователя с айди = id
     * @throws IllegalArgumentException если такой пользователь не найден
     * @see OfficeDao
     */

    @Override
    public UserDto getUser(Long id) {
        ArgChecker.requireNonNull(id, "id");
        User user = userDao.findOne(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        UserDto userDto = new UserDto(user);
        return userDto;
    }

    /**
     * Поиск офисов по параметрам
     *
     * @param officeId
     * @param firstName
     * @param lastName
     * @param middleName
     * @param position
     * @param docCode
     * @param citizenshipCode
     * @return
     */
    @Override
    public List<User> getUserList(Long officeId, String firstName, String lastName, String middleName, String position,
                                  String docCode, String citizenshipCode) {
        ArgChecker.requireNonNull(officeId, "officeId");
        List<User> userList = userDao.findAll(officeId, firstName, lastName, middleName, position, docCode, citizenshipCode);
        return userList;
    }

    /**
     * Сохраняет новую организацию
     *
     * @param officeId айди офиса пользователя
     * @param userDto  данные пользователя
     */
    @Override
    public void save(Long officeId, UserDto userDto) {
        ArgChecker.requireNonNull(officeId, "officeId");
        ArgChecker.requireNonNull(userDto, "userDto");
        User newUser = new User(userDto);
        newUser.setDocConcrete(handbookService.parseDoc(userDto));
        newUser.setCitizenship(handbookService.parseCitizenship(userDto));
        newUser.setOffice(officeService.getOffice(officeId));
        userDao.save(newUser);
    }

    /**
     * Обновляет старый офис
     *
     * @param userId  айди обновляемого пользователя
     * @param userDto новые данные
     */
    @Override
    public void update(Long userId, Long officeId, UserDto userDto) {
        ArgChecker.requireNonNull(userId, "userId");
        ArgChecker.requireNonNull(userDto, "userDto");
        User oldUser = userDao.findOne(userId);
        oldUser.update(userDto);

        if (officeId != null) {
            oldUser.setOffice(officeService.getOffice(officeId));
        }
        oldUser.setDocConcrete(handbookService.parseDoc(userDto));
        oldUser.setCitizenship(handbookService.parseCitizenship(userDto));
        userDao.update(oldUser);
    }
}
