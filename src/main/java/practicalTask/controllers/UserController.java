package practicalTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practicalTask.domain.User;
import practicalTask.service.user.UserService;
import practicalTask.utils.dto.UserDto;
import practicalTask.utils.response.DataContainer;
import practicalTask.utils.response.ResultContainer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Контроллер для обработки запросов связанных с пользователями
 * Обрабатывает  get by id, get list by parameters, save, update запросы
 */
@Validated
@RestController
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    /**
     * Возвращает пользователя по его айди
     * Если такого пользователя нет, то выбрасывается исключение
     *
     * @param id - вводимый айди, по нему ведется поиск
     * @return DataContainer, который содержит информацию об пользователе
     */
    @Transactional
    @RequestMapping(method = RequestMethod.GET, value = "/api/user/{id}")
    public DataContainer getUser(@PathVariable Long id) {
        UserDto user = userService.getUser(id);
        return new DataContainer(user);
    }

    /**
     * Метод получает  из сервиса список пользователей, соответствующих параметрам,
     * после чего помещает необходимые данные в output,
     * затем output заворачивается в DataContainer
     *
     * @param officeId        айди офиса пользователя, обязательный параметр
     * @param firstName
     * @param lastName
     * @param middleName
     * @param position        должность
     * @param docCode         код документа пользователя, необязательный параметр
     * @param citizenshipCode код гражданства пользователя, необязательный параметр
     * @return DataContainer с списком пользователей
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/user/list")
    public DataContainer getUserList(@RequestParam Long officeId, @RequestParam(required = false) String firstName,
                                     @RequestParam(required = false) String lastName, @RequestParam(required = false) String middleName,
                                     @RequestParam(required = false) String position, @RequestParam(required = false) String docCode,
                                     @RequestParam(required = false) String citizenshipCode) {
        List<User> userList = userService.getUserList(officeId, firstName, lastName, middleName, position, docCode, citizenshipCode);
        List<Map<String, String>> output = new ArrayList<>();
        for (User user : userList) {
            Map<String, String> userInfo = new LinkedHashMap<>();
            userInfo.put("id", user.getId().toString());
            userInfo.put("firstName", user.getFirstName());
            userInfo.put("secondName", user.getSecondName());
            userInfo.put("middleName", user.getMiddleName());
            userInfo.put("position", user.getPosition());
            output.add(userInfo);
        }
        return new DataContainer(output);

    }

    /**
     * Сохраняет нового пользователя
     * Возвращает success, если операция была успешна
     *
     * @param officeId айди офиса пользователя, обязательный параметр
     * @param userDto  данные пользователя, имеет обязательные поля
     * @return ResultContainer с сообщением success, если операция прошла успешно
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/user/save")
    public ResultContainer saveNewUser(@RequestParam Long officeId, UserDto userDto) {
        userService.save(officeId, userDto);
        return new ResultContainer("success");
    }

    /**
     * Обновляет данные пользователя
     *
     * @param id       айди обновляемого пользователя, обязательный параметр
     * @param officeId айди офиса пользователя
     * @param userDto  новые данные, имеет обязательные поля
     * @return ResultContainer с сообщением success, если операция прошла успешно
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/user/update")
    public ResultContainer updateUser(@RequestParam Long id, @RequestParam(required = false) Long officeId, UserDto userDto) {
        userService.update(id, officeId, userDto);
        return new ResultContainer("success");
    }
}
