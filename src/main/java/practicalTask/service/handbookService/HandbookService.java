package practicalTask.service.handbookService;

import practicalTask.domain.Citizenship;
import practicalTask.domain.DocConcrete;
import practicalTask.utils.dto.CitizenshipDto;
import practicalTask.utils.dto.DocTypeDto;
import practicalTask.utils.dto.UserDto;

import java.util.List;

public interface HandbookService {

    List<DocTypeDto> getDocTypes();

    List<CitizenshipDto> getCitizenships();

    DocConcrete parseDoc(UserDto userDto);

    Citizenship parseCitizenship(UserDto userDto);
}
