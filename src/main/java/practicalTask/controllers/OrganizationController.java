package practicalTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practicalTask.service.organization.OrganizationService;
import practicalTask.utils.dto.organization.OrganizationDto;
import practicalTask.utils.dto.organization.OrganizationListDto;
import practicalTask.utils.response.DataContainer;
import practicalTask.utils.response.ResultContainer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Контроллер для обработки запросов связанных с организациями
 * Обрабатывает  get by id, get list by parameters, save, update запросы
 */
@Validated
@RestController
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(@Qualifier("organizationServiceImpl") OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Возвращает  организацию по ее айди
     * Если такой нет, то возвращает сообщение об ошибке
     *
     * @param id - вводимый айди, по нему ведется поиск
     * @return DataContainer, который содержит информацию об организации
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/organization/{id}")
    public DataContainer getOrganization(@PathVariable @NotNull final Long id) {
        OrganizationDto organization = organizationService.getOrganization(id);
        return new DataContainer(organization);
    }

    /**
     * Метод получает  из сервиса список организаций, соответствующих параметрам,
     * после чего помещает необходимые данные в output,
     * затем output заворачивается в DataContainer
     *
     * @param name     - имя, по нему ведется поиск
     * @param inn      - инн, по нему ведется поиск. Необязательный параметр
     * @param isActive - активность, по ней ведется поиск. Необязательный параметр
     * @return DataContainer с списком организаций
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/organization/list")
    public DataContainer getOrganizationList(@RequestParam @NotBlank String name,
                                             @RequestParam(required = false) String inn,
                                             @RequestParam(required = false, defaultValue = "true") boolean isActive) {

        List<OrganizationListDto> organizationList = organizationService.getOrganizationList(name, inn, isActive);
        return new DataContainer(organizationList);
    }

    /**
     * Сохраняет новую оганизацию
     * Возвращает success , если операция была успешна
     * <p>
     * Непонятно, каким должно быть поведение параметра isActive, в задании он всегда true
     *
     * @param newOrganization - сущность, создаваемая из введенных пользователем параметров
     * @return ResultContainer с сообщением success, если операция прошла успешно
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/organization/save")
    public ResultContainer saveNewOrganization(OrganizationDto newOrganization) {
        organizationService.save(newOrganization);
        return new ResultContainer("success");
    }

    /**
     * Обновляет старую оганизацию
     * Возвращает success , если операция была успешна
     * Непонятно, каким должно быть поведение параметра isActive, в задании он всегда true
     *
     * @param id              айди обновляемой организации
     * @param newOrganization сущность, создаваемая из введенных пользователем параметров, хранит в себе новые данные
     *                        и айди обновляемой организации
     * @return ResultContainer с сообщением success, если операция прошла успешно
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/organization/update")
    public ResultContainer updateOrganization(@RequestParam Long id, OrganizationDto newOrganization) {
        organizationService.update(id, newOrganization);
        return new ResultContainer("success");
    }
}