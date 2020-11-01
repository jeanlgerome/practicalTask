package practicalTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import practicalTask.service.user.UserService;
import practicalTask.utils.dto.user.UserDto;
import practicalTask.utils.dto.user.UserFilterDto;
import practicalTask.utils.dto.user.UserListDto;
import practicalTask.utils.response.ResultContainer;

import java.util.List;


/**
 * Контроллер для обработки запросов связанных с пользователями
 * Обрабатывает  get by id, get list by parameters, save, update запросы
 */
@Validated
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    /**
     * Возвращает пользователя по его айди
     * Если такого пользователя нет, то выбрасывается исключение
     *
     * @param id - вводимый айди, по нему ведется поиск
     * @return DataContainer, который содержит информацию об пользователе
     */
    @Transactional
    @RequestMapping(method = RequestMethod.GET, value = "/api/user/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    /**
     * Метод получает  из сервиса список пользователей, соответствующих параметрам,
     *
     * @param userFilterDto дто с параметрами
     * @return DataContainer с списком пользователей
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/user/list")
    public List<UserListDto> getUserList(@Validated UserFilterDto userFilterDto) {

        return userService.getUserList(userFilterDto);
    }

    /**
     * Сохраняет нового пользователя
     * Возвращает success, если операция была успешна
     *
     * @param userDto дто с данными
     * @return ResultContainer с сообщением success, если операция прошла успешно
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/user/save")
    public void saveNewUser(@Validated UserDto userDto) {
        userService.save(userDto);
    }

    /**
     * Обновляет данные пользователя
     *
     * @param userDto  новые данные
     * @return ResultContainer с сообщением success, если операция прошла успешно
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/user/update")
    public void updateUser(@Validated UserDto userDto) {
        userService.update(userDto);
    }
}
