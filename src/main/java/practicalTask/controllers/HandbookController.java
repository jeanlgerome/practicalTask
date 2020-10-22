package practicalTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import practicalTask.service.handbookService.HandbookService;
import practicalTask.utils.dto.handbook.CitizenshipDto;
import practicalTask.utils.dto.handbook.DocTypeDto;

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
    public List<DocTypeDto> getDocTypes() {
        return handbookService.getDocTypes();
    }

    /**
     * api countries
     *
     * @return список гражданств с их кодами
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/countries")
    public List<CitizenshipDto> getCitizenships() {
        return handbookService.getCitizenships();
    }
}
