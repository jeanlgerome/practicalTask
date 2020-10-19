package practicalTask.service.handbookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import practicalTask.dao.handbook.HandbookDao;
import practicalTask.domain.Citizenship;
import practicalTask.domain.DocConcrete;
import practicalTask.domain.DocType;
import practicalTask.utils.ArgChecker;
import practicalTask.utils.dto.CitizenshipDto;
import practicalTask.utils.dto.DocTypeDto;
import practicalTask.utils.dto.UserDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис работы с справочниками
 */
@Service
public class HandbookServiceImpl implements HandbookService {

    private final HandbookDao handbookDao;

    @Autowired
    public HandbookServiceImpl(@Qualifier("handbookDaoImpl") HandbookDao handbookDao) {
        this.handbookDao = handbookDao;
    }

    /**
     * @return dto всех типов документов
     */
    @Override
    public List<DocTypeDto> getDocTypes() {
        List<DocType> docTypeList = handbookDao.findAllDocTypes();
        List<DocTypeDto> dtoList = new ArrayList<>();
        for (DocType docType : docTypeList) {
            dtoList.add(new DocTypeDto(docType));
        }
        return dtoList;
    }

    /**
     * @return dto всех гражданств
     */
    @Override
    public List<CitizenshipDto> getCitizenships() {
        List<Citizenship> citizenshipList = handbookDao.findAllCitizenships();
        List<CitizenshipDto> dtoList = new ArrayList<>();
        for (Citizenship citizenship : citizenshipList) {
            dtoList.add(new CitizenshipDto(citizenship));
        }
        return dtoList;
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
