package practicalTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import practicalTask.domain.Citizenship;
import practicalTask.domain.DocType;
import practicalTask.service.handbookService.HandbookService;
import practicalTask.utils.response.DataContainer;

import java.util.List;

/**
 * Контроллер для обработки запросов связанных с справочниками
 */
@RestController
public class HandbookController {

    @Autowired
    @Qualifier("handbookServiceImpl")
    HandbookService handbookService;

    /**
     * api docs
     *
     * @return список типов документов с их кодами
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/docs")
    public DataContainer getDocTypes() {
        List<DocType> docTypeList = handbookService.getDocTypes();
        return new DataContainer(docTypeList);
    }

    /**
     * api countries
     *
     * @return список гражданств с их кодами
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/countries")
    public DataContainer getCitizenships() {
        List<Citizenship> citizenshipList = handbookService.getCitizenships();
        return new DataContainer(citizenshipList);
    }
}