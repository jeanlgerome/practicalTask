package practicalTask.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practicalTask.dao.office.OfficeDao;
import practicalTask.dao.user.UserDao;
import practicalTask.model.User;
import practicalTask.service.handbookService.HandbookService;
import practicalTask.utils.dto.user.UserDto;
import practicalTask.utils.dto.user.UserFilterDto;
import practicalTask.utils.dto.user.UserListDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final OfficeDao officeDao;

    private final HandbookService handbookService;

    @Autowired
    public UserServiceImpl(@Qualifier("userDaoImpl") UserDao userDao, @Qualifier("officeDaoImpl") OfficeDao officeDao, @Qualifier("handbookServiceImpl") HandbookService handbookService) {
        this.userDao = userDao;
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
        Objects.requireNonNull(id, "id");
        User user = userDao.findOne(id);
        UserDto userDto = new UserDto(user);
        return userDto;
    }

    /**
     * Поиск офисов по параметрам
     *
     * @param userFilterDto дто с параметрами
     * @return
     */
    @Override
    public List<UserListDto> getUserList(UserFilterDto userFilterDto) {
        Objects.requireNonNull(userFilterDto.getOfficeId(), "officeId");
        officeDao.findOne(userFilterDto.getOfficeId());
        List<User> userList = userDao.findAll(userFilterDto);
        List<UserListDto> dtoList = new ArrayList<>();
        for (User user : userList) {
            dtoList.add(new UserListDto(user.getFirstName(), user.getSecondName(), user.getMiddleName(), user.getPosition(), user.getDocCode(), user.getCitizenshipCode()));
        }
        return dtoList;
    }

    /**
     * Сохраняет новую организацию
     *
     * @param userDto данные пользователя
     */
    @Override
    @Transactional
    public void save(UserDto userDto) {
        Objects.requireNonNull(userDto.getOfficeId(), "officeId");
        Objects.requireNonNull(userDto, "userDto");
        User newUser = new User();
        setUserData(newUser, userDto);
        newUser.setDocConcrete(handbookService.parseDoc(userDto));
        newUser.setCitizenship(handbookService.parseCitizenship(userDto));
        newUser.setOffice(officeDao.findOne(userDto.getOfficeId()));
        userDao.save(newUser);
    }

    /**
     * Обновляет старый офис
     *
     * @param userDto новые данные
     */
    @Override
    @Transactional
    public void update(UserDto userDto) {
        Objects.requireNonNull(userDto.getId(), "userId");
        Objects.requireNonNull(userDto, "userDto");
        User oldUser = userDao.findOne(userDto.getId());
        setUserData(oldUser, userDto);

        if (userDto.getOfficeId() != null) {
            oldUser.setOffice(officeDao.findOne(userDto.getOfficeId()));
        }
        oldUser.setDocConcrete(handbookService.parseDoc(userDto));
        oldUser.setCitizenship(handbookService.parseCitizenship(userDto));

    }

    private void setUserData(User user, UserDto newUserData) {
        user.setFirstName(newUserData.getFirstName());
        user.setSecondName(newUserData.getSecondName());
        user.setMiddleName(newUserData.getMiddleName());
        user.setPosition(newUserData.getPosition());
        user.setPhone(newUserData.getPhone());
        user.setIdentified(newUserData.isIdentified());
    }
}
