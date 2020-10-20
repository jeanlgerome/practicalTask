package practicalTask.service.handbookService;

import practicalTask.model.Citizenship;
import practicalTask.model.DocConcrete;
import practicalTask.utils.dto.handbook.CitizenshipDto;
import practicalTask.utils.dto.handbook.DocTypeDto;
import practicalTask.utils.dto.user.UserDto;

import java.util.List;

public interface HandbookService {

    List<DocTypeDto> getDocTypes();

    List<CitizenshipDto> getCitizenships();

    DocConcrete parseDoc(UserDto userDto);

    Citizenship parseCitizenship(UserDto userDto);
}
