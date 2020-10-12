package practicalTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practicalTask.domain.Organization;
import practicalTask.service.organization.OrganizationService;
import practicalTask.utils.response.ResultContainer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Контроллер для обработки запросов связанных с организациями
 * В данной версии обрабатывает  get by id, get list by parameters, save, update запросы
 */
@Validated
@RestController
public class OrganizationController {

    @Autowired
    @Qualifier("organizationServiceImpl")
    OrganizationService organizationService;

    /**
     * Возвращает  организацию по ее айди
     * Если такой нет, то возвращает сообщение об ошибке. Обработка ошибки происходит в ExceptionInterceptor
     *
     * @param id - вводимый пользователем айди, по нему ведется поиск
     * @return ResponseEntity с DataContainer, который содержит информацию об организации
     * @see ExceptionInterceptor
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/organization/{id}")
    public @ResponseBody
    ResponseEntity getOrganization(@PathVariable @NotNull final Long id) {
        Organization organization = organizationService.getOrganization(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResultContainer.DataContainer(organization));
    }

    /**
     * Возвращает  список организаций по параметрам
     * Обработка ошибки происходит в ExceptionInterceptor
     *
     * @param name     - имя, по нему ведется поиск
     * @param inn      - инн, по нему ведется поиск. Необязательный параметр
     * @param isActive - активность, по ней ведется поиск. Необязательный параметр
     * @return список организаций
     * @see ExceptionInterceptor
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/organization/list")
    public ResponseEntity getOrganizationList(@RequestParam @NotBlank String name,
                                              @RequestParam(required = false) String inn,
                                              @RequestParam(required = false, defaultValue = "true") boolean isActive) {

        List<Organization> organizationList = organizationService.getOrganizationList(name, inn, isActive);
        return ResponseEntity.status(HttpStatus.OK).body(new ResultContainer.DataContainer(organizationList));
    }

    /**
     * Сохраняет новую оганизацию
     * Возвращает success , если операция была успешна
     * Обработка ошибки происходит в ExceptionInterceptor
     * Непонятно, каким должно быть поведение параметра isActive, в задании он всегда true
     *
     * @param newOrganization - сущность, создаваемая из введенных пользователем параметров
     * @return сообщение о результате операции: success/error
     * @see ExceptionInterceptor
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/organization/save")
    public @ResponseBody
    ResponseEntity saveNewOrganization(Organization newOrganization) {
        newOrganization.setActive(true);
        organizationService.save(newOrganization);
        return ResponseEntity.status(HttpStatus.OK).body(new ResultContainer("success"));
    }

    /**
     * Обновляет старую оганизацию
     * Возвращает success , если операция была успешна
     * Обработка ошибки происходит в ExceptionInterceptor
     * Непонятно, каким должно быть поведение параметра isActive, в задании он всегда true
     *
     * @param newOrganization сущность, создаваемая из введенных пользователем параметров, хранит в себе новые данные
     *                        и айди обновляемой организации
     * @return сообщение о результате операции: success/error
     * @see ExceptionInterceptor
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/organization/update")
    public @ResponseBody
    ResponseEntity updateOrganization(Organization newOrganization) {
        newOrganization.setActive(true);
        organizationService.update(newOrganization);
        return ResponseEntity.status(HttpStatus.OK).body(new ResultContainer("success"));
    }
}
