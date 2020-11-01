package practicalTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import practicalTask.service.office.OfficeService;
import practicalTask.utils.dto.office.OfficeDto;
import practicalTask.utils.dto.office.OfficeFilterDto;
import practicalTask.utils.dto.office.OfficeListDto;
import practicalTask.utils.response.ResultContainer;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Контроллер для обработки запросов связанных с офисами
 * Обрабатывает  get by id, get list by parameters, save, update запросы
 */
@Validated
@RestController
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(@Qualifier("officeServiceImpl") OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * Возвращает офис по его айди
     * Если такого офиса нет, то выбрасывает исключение
     *
     * @param id - вводимый айди, по нему ведется поиск
     * @return DataContainer, который содержит информацию об офисе
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/office/{id}")
    public OfficeDto getOffice(@PathVariable @NotNull final Long id) {
        return officeService.getOffice(id);
    }

    /**
     * Метод получает  из сервиса список офисов, соответствующих параметрам,
     *
     * @param officeFilterDto дто с параметрами
     * @return DataContainer с списком офисов
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/office/list")
    public List<OfficeListDto> getOfficeList(@Validated OfficeFilterDto officeFilterDto) {
        return officeService.getOfficeList(officeFilterDto);
    }

    /**
     * Сохраняет новый офис
     * Возвращает success, если операция была успешна
     *
     * @param newOffice - сущность, создаваемая из введенных пользователем параметров
     * @return ResultContainer с сообщением success, если операция прошла успешно
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/office/save")
    public void saveNewOffice(@Validated OfficeDto newOffice) {
        officeService.save(newOffice);
    }

    /**
     * Обновляет старый офис
     *
     * @param officeDto дто с данными
     * @return ResultContainer с сообщением о результате операции: success/error
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/office/update")
    public void updateOffice(@Validated OfficeDto officeDto) {
        officeService.update(officeDto);
    }
}
