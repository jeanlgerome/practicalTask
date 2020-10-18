package practicalTask.service.handbookService;

import practicalTask.domain.Citizenship;
import practicalTask.domain.DocConcrete;
import practicalTask.domain.DocType;
import practicalTask.utils.dto.UserDto;

import java.util.List;

public interface HandbookService {

    List<DocType> getDocTypes();

    List<Citizenship> getCitizenships();

    DocConcrete parseDoc(UserDto userDto);

    Citizenship parseCitizenship(UserDto userDto);
}
