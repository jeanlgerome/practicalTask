package practicalTask.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practicalTask.dao.office.OfficeDao;
import practicalTask.dao.user.UserDao;
import practicalTask.model.User;
import practicalTask.service.handbookService.HandbookService;
import practicalTask.utils.ArgChecker;
import practicalTask.utils.dto.user.UserDto;
import practicalTask.utils.dto.user.UserListDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final OfficeDao officeDao;

    private final HandbookService handbookService;

    @Autowired
    public UserServiceImpl( @Qualifier("userDaoImpl")UserDao userDao, @Qualifier("officeDaoImpl")OfficeDao officeDao, @Qualifier("handbookServiceImpl")HandbookService handbookService) {
        this.userDao= userDao;
        this.officeDao = officeDao;
        this.handbookService = handbookService;
    }

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
    public List<UserListDto> getUserList(Long officeId, String firstName, String lastName, String middleName, String position,
                                     String docCode, String citizenshipCode) {
        ArgChecker.requireNonNull(officeId, "officeId");
        officeDao.findOne(officeId);
        List<User> userList = userDao.findAll(officeId, firstName, lastName, middleName, position, docCode, citizenshipCode);
        List<UserListDto> dtoList = new ArrayList<>();
        for (User user : userList) {
            dtoList.add(new UserListDto(user.getFirstName(), user.getSecondName(), user.getMiddleName(), user.getPosition(), user.getDocCode(), user.getCitizenshipCode()));
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
        User newUser = new User();
        newUser.update(userDto);
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
