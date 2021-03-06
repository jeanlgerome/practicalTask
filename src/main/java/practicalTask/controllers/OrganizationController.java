package practicalTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import practicalTask.service.organization.OrganizationService;
import practicalTask.utils.dto.organization.OrgFilterDto;
import practicalTask.utils.dto.organization.OrganizationDto;
import practicalTask.utils.dto.organization.OrganizationListDto;
import practicalTask.utils.response.ResultContainer;

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
    public OrganizationDto getOrganization(@PathVariable @NotNull final Long id) {
        return organizationService.getOrganization(id);
    }

    /**
     * Метод получает  из сервиса список организаций, соответствующих параметрам,
     *
     * @param orgFilterDto дто с параметрами
     * @return DataContainer с списком организаций
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/organization/list")

    public List<OrganizationListDto> getOrganizationList(@Validated OrgFilterDto orgFilterDto) {
        return organizationService.getOrganizationList(orgFilterDto);
    }

    /**
     * Сохраняет новую оганизацию
     * Возвращает success , если операция была успешна
     * <p>
     * Непонятно, каким должно быть поведение параметра isActive, в задании он всегда true
     *
     * @param newOrganization - объект, создаваемый из введенных пользователем параметров
     * @return ResultContainer с сообщением success, если операция прошла успешно
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/organization/save")
    public void saveNewOrganization(@Validated OrganizationDto newOrganization) {
        organizationService.save(newOrganization);
    }

    /**
     * Обновляет старую оганизацию
     * Возвращает success , если операция была успешна
     * Непонятно, каким должно быть поведение параметра isActive, в задании он всегда true
     *
     * @param newOrganization объект, создаваемый из введенных пользователем параметров, хранит в себе новые данные
     *                        и айди обновляемой организации
     * @return ResultContainer с сообщением success, если операция прошла успешно
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/organization/update")
    public void updateOrganization(@Validated OrganizationDto newOrganization) {
        organizationService.update(newOrganization);
    }
}