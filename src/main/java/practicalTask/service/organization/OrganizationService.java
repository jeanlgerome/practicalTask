package practicalTask.service.organization;

import practicalTask.utils.dto.organization.OrganizationDto;
import practicalTask.utils.dto.organization.OrganizationListDto;

import java.util.List;

public interface OrganizationService {

    OrganizationDto getOrganization(Long id);

    List<OrganizationListDto> getOrganizationList(String name, String inn, boolean isActive);

    void save(OrganizationDto organization);

    void update(Long orgId, OrganizationDto organization);

}
