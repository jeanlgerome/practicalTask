package practicalTask.service.organization;

import practicalTask.utils.dto.organization.OrgFilterDto;
import practicalTask.utils.dto.organization.OrganizationDto;
import practicalTask.utils.dto.organization.OrganizationListDto;

import java.util.List;

public interface OrganizationService {

    OrganizationDto getOrganization(Long id);

    List<OrganizationListDto> getOrganizationList(OrgFilterDto orgFilterDto);

    void save(OrganizationDto organization);

    void update(OrganizationDto organization);

}
