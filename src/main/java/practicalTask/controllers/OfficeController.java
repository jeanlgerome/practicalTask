package practicalTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practicalTask.domain.Office;
import practicalTask.service.office.OfficeService;
import practicalTask.utils.response.DataContainer;
import practicalTask.utils.response.ResultContainer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Контроллер для обработки запросов связанных с офисами
 * Обрабатывает  get by id, get list by parameters, save, update запросы
 */
@Validated
@RestController
public class OfficeController {

    @Autowired
    @Qualifier("officeServiceImpl")
    OfficeService officeService;

    /**
     * Возвращает офис по его айди
     * Если такого офиса нет, то выбрасывает исключение
     *
     * @param id - вводимый айди, по нему ведется поиск
     * @return DataContainer, который содержит информацию об офисе
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/office/{id}")
    public DataContainer getOffice(@PathVariable @NotNull final Long id) {
        Office office = officeService.getOffice(id);
        return new DataContainer(office);
    }

    /**
     * Метод получает  из сервиса список офисов, соответствующих параметрам,
     * после чего помещает необходимые данные в output,
     * затем output заворачивается в DataContainer
     *
     * @param orgId    - айди организации, по нему ведется поиск. Обязательный параметр
     * @param name     - название офиса, по нему ведется поиск.
     * @param phone    - телефон, по нему ведется поиск
     * @param isActive - активность, по ней ведется поиск
     * @return DataContainer с списком офисов
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/office/list")
    public DataContainer getOfficeList(@RequestParam @NotNull Long orgId, @RequestParam(required = false) String name,
                                       @RequestParam(required = false) String phone,
                                       @RequestParam(required = false) boolean isActive) {
        List<Office> officeList = officeService.getOfficeList(orgId, name, phone, isActive);
        List<Map<String, String>> output = new ArrayList<>();
        for (Office office : officeList) {
            Map<String, String> officeInfo = new LinkedHashMap<>();
            officeInfo.put("id", office.getId().toString());
            officeInfo.put("name", office.getName());
            officeInfo.put("isActive", Boolean.toString(office.isActive()));
            output.add(officeInfo);
        }
        return new DataContainer(output);
    }

    /**
     * Сохраняет новый офис
     * Возвращает success, если операция была успешна
     *
     * @param newOffice - сущность, создаваемая из введенных пользователем параметров
     * @return ResultContainer с сообщением success, если операция прошла успешно
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/office/save")
    public ResultContainer saveNewOffice(@RequestParam @NotNull Long orgId, Office newOffice) {
        officeService.save(orgId, newOffice);
        return new ResultContainer("success");
    }

    /**
     * Обновляет старый офис
     *
     * @param id       айди обновляемого офиса. Обязательный параметр
     * @param name     название. Обязательный параметр
     * @param address  адрес. Обязательный параметр
     * @param phone    телефон
     * @param isActive статус активности
     * @return ResultContainer с сообщением о результате операции: success/error
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/office/update")
    public ResultContainer updateOffice(@RequestParam Long id, @RequestParam @NotBlank String name,
                                        @RequestParam @NotBlank String address, @RequestParam(required = false) String phone,
                                        @RequestParam(defaultValue = "true") boolean isActive) {
        Office office = new Office(name, address, phone, isActive);
        officeService.update(id, office);
        return new ResultContainer("success");
    }
}
