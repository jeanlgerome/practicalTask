package practicalTask.service.user;

import practicalTask.utils.dto.user.UserDto;
import practicalTask.utils.dto.user.UserFilterDto;
import practicalTask.utils.dto.user.UserListDto;

import java.util.List;


public interface UserService {

    UserDto getUser(Long id);

    List<UserListDto> getUserList(UserFilterDto userFilterDto);

    void save( UserDto userDto);

    void update(UserDto userDto);

}
