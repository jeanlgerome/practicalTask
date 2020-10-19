package practicalTask.service.user;

import practicalTask.domain.User;
import practicalTask.utils.dto.UserDto;

import java.util.List;


public interface UserService {

    UserDto getUser(Long id);

    List<UserDto> getUserList(Long officeId, String firstName, String lastName, String middleName, String position,
                           String docCode, String citizenshipCode);

    void save(Long officeId, UserDto userDto);

    void update(Long userId, Long officeId, UserDto userDto);

}
