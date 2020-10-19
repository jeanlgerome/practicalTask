package practicalTask.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practicalTask.dao.office.OfficeDao;
import practicalTask.dao.user.UserDao;
import practicalTask.domain.User;
import practicalTask.service.handbookService.HandbookService;
import practicalTask.utils.ArgChecker;
import practicalTask.utils.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userDaoImpl")
    UserDao userDao;
    @Autowired
    @Qualifier("officeDaoImpl")
    OfficeDao officeDao;
    @Autowired
    @Qualifier("handbookServiceImpl")
    HandbookService handbookService;

    /**
     * Поиск пользователя по айди
     *
     * @param id айди пользователя
     * @return Dto пользователя с айди = id
     * @see OfficeDao
     */

    @Override
    public UserDto getUser(Long id) {
        ArgChecker.requireNonNull(id, "id");
        User user = userDao.findOne(id);
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
    public List<UserDto> getUserList(Long officeId, String firstName, String lastName, String middleName, String position,
                                     String docCode, String citizenshipCode) {
        ArgChecker.requireNonNull(officeId, "officeId");
        officeDao.findOne(officeId);
        List<User> userList = userDao.findAll(officeId, firstName, lastName, middleName, position, docCode, citizenshipCode);
        List<UserDto> dtoList = new ArrayList<>();
        for (User user : userList) {
            dtoList.add(new UserDto(user.getFirstName(), user.getSecondName(), user.getMiddleName(), user.getPosition(), user.getDocConcrete().getDocType().getDocCode(), user.getCitizenship().getCitizenshipCode()));
        }
        return dtoList;
    }

    /**
     * Сохраняет новую организацию
     *
     * @param officeId айди офиса пользователя
     * @param userDto  данные пользователя
     */
    @Override
    @Transactional
    public void save(Long officeId, UserDto userDto) {
        ArgChecker.requireNonNull(officeId, "officeId");
        ArgChecker.requireNonNull(userDto, "userDto");
        User newUser = new User(userDto);
        newUser.setDocConcrete(handbookService.parseDoc(userDto));
        newUser.setCitizenship(handbookService.parseCitizenship(userDto));
        newUser.setOffice(officeDao.findOne(officeId));
        userDao.save(newUser);
    }

    /**
     * Обновляет старый офис
     *
     * @param userId  айди обновляемого пользователя
     * @param userDto новые данные
     */
    @Override
    @Transactional
    public void update(Long userId, Long officeId, UserDto userDto) {
        ArgChecker.requireNonNull(userId, "userId");
        ArgChecker.requireNonNull(userDto, "userDto");
        User oldUser = userDao.findOne(userId);
        oldUser.update(userDto);

        if (officeId != null) {
            oldUser.setOffice(officeDao.findOne(officeId));
        }
        oldUser.setDocConcrete(handbookService.parseDoc(userDto));
        oldUser.setCitizenship(handbookService.parseCitizenship(userDto));
        userDao.update(oldUser);
    }
}
