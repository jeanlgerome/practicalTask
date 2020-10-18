package practicalTask.service.handbookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practicalTask.dao.handbook.HandbookDao;
import practicalTask.domain.Citizenship;
import practicalTask.domain.DocConcrete;
import practicalTask.domain.DocType;
import practicalTask.utils.ArgChecker;
import practicalTask.utils.dto.UserDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Сервис работы с справочниками
 */
@Service
public class HandbookServiceImpl implements HandbookService {

    @Autowired
    @Qualifier("handbookDaoImpl")
    HandbookDao handbookDao;

    /**
     * @return все типы документов
     */
    @Override
    public List<DocType> getDocTypes() {
        List<DocType> docTypeList = handbookDao.findAllDocTypes();
        return docTypeList;
    }

    /**
     * @return все гражданства
     */
    @Override
    public List<Citizenship> getCitizenships() {
        return handbookDao.findAllCitizenships();
    }

    /**
     * Парсит дто на документ, если данных недостаточно, то возвращает null
     *
     * @param userDto
     * @return DocConcrete
     * @throws IllegalArgumentException если такой тип документа не найден
     */
    @Override
    public DocConcrete parseDoc(UserDto userDto) {
        ArgChecker.requireNonNull(userDto, "userDto");
        if (userDto.getDocNumber() == null || userDto.getDocDate() == null || (userDto.getDocCode() == null && userDto.getDocName() == null)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate docDate = LocalDate.parse(userDto.getDocDate(), formatter);
        DocType docType = parseDocType(userDto);
        String docNumber = userDto.getDocNumber();
        return new DocConcrete(docNumber, docType, docDate);
    }

    /**
     * Парсит дто на гражданство, если данных недостаточно, то возвращает null
     *
     * @param userDto
     * @return Citizenship
     * @throws IllegalArgumentException если такое гражданство не найдено
     */
    @Override
    public Citizenship parseCitizenship(UserDto userDto) {
        if (userDto == null || userDto.getCitizenshipCode() == null) {
            return null;
        }
        List<Citizenship> citizenshipList = handbookDao.findAllCitizenships();
        for (Citizenship citizenship : citizenshipList) {
            if (citizenship.getCitizenshipCode().equals(userDto.getCitizenshipCode())) {
                return new Citizenship(citizenship.getCitizenshipCode(), citizenship.getCitizenshipName());
            }
        }
        throw new IllegalArgumentException("Гражданство не найдено ");
    }

    private DocType parseDocType(UserDto userDto) {
        List<DocType> docTypeList = handbookDao.findAllDocTypes();

        for (DocType docType : docTypeList) {

            if ((userDto.getDocCode() == null || userDto.getDocCode().equals(docType.getDocCode())) && (userDto.getDocName() == null || userDto.getDocName().equals(docType.getDocName()))) {
                return new DocType(docType.getDocCode(), docType.getDocName());
            }
        }
        throw new IllegalArgumentException("Тип документа не найден ");
    }

}