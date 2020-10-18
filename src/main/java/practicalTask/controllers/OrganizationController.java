package practicalTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practicalTask.domain.Organization;
import practicalTask.service.organization.OrganizationService;
import practicalTask.utils.response.DataContainer;
import practicalTask.utils.response.ResultContainer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Контроллер для обработки запросов связанных с организациями
 * Обрабатывает  get by id, get list by parameters, save, update запросы
 */
@Validated
@RestController
public class OrganizationController {

    @Autowired
    @Qualifier("organizationServiceImpl")
    OrganizationService organizationService;

    /**
     * Возвращает  организацию по ее айди
     * Если такой нет, то возвращает сообщение об ошибке
     *
     * @param id - вводимый айди, по нему ведется поиск
     * @return DataContainer, который содержит информацию об организации
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/organization/{id}")
    public DataContainer getOrganization(@PathVariable @NotNull final Long id) {
        Organization organization = organizationService.getOrganization(id);
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

        List<Organization> organizationList = organizationService.getOrganizationList(name, inn, isActive);
        List<Map<String, String>> output = new ArrayList<>();
        for (Organization org : organizationList) {
            Map<String, String> orgInfo = new LinkedHashMap<>();
            orgInfo.put("name", org.getName());
            orgInfo.put("id", org.getId().toString());
            orgInfo.put("isActive", Boolean.toString(org.isActive()));
            output.add(orgInfo);
        }
        return new DataContainer(output);
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
    public ResultContainer saveNewOrganization(Organization newOrganization) {
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
    public ResultContainer updateOrganization(@RequestParam Long id, Organization newOrganization) {
        newOrganization.setActive(true);
        organizationService.update(id, newOrganization);
        return new ResultContainer("success");
    }
}
