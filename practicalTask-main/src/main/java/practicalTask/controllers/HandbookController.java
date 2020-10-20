package practicalTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import practicalTask.service.handbookService.HandbookService;
import practicalTask.utils.dto.CitizenshipDto;
import practicalTask.utils.dto.DocTypeDto;
import practicalTask.utils.response.DataContainer;

import java.util.List;

/**
 * Контроллер для обработки запросов связанных с справочниками
 */
@RestController
public class HandbookController {

    private final HandbookService handbookService;

    @Autowired
    public HandbookController(@Qualifier("handbookServiceImpl") HandbookService handbookService) {
        this.handbookService = handbookService;
    }

    /**
     * api docs
     *
     * @return список типов документов с их кодами
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/docs")
    public DataContainer getDocTypes() {
        List<DocTypeDto> docTypeDtoList = handbookService.getDocTypes();
        return new DataContainer(docTypeDtoList);
    }

    /**
     * api countries
     *
     * @return список гражданств с их кодами
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/countries")
    public DataContainer getCitizenships() {
        List<CitizenshipDto> citizenshipDtoList = handbookService.getCitizenships();
        return new DataContainer(citizenshipDtoList);
    }
}
