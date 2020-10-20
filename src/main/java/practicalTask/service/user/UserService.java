package practicalTask.service.user;

import practicalTask.utils.dto.user.UserDto;
import practicalTask.utils.dto.user.UserListDto;

import java.util.List;


public interface UserService {

    UserDto getUser(Long id);

    List<UserListDto> getUserList(Long officeId, String firstName, String lastName, String middleName, String position,
                                  String docCode, String citizenshipCode);

    void save(Long officeId, UserDto userDto);

    void update(Long userId, Long officeId, UserDto userDto);

}
