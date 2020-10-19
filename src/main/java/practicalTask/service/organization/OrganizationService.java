package practicalTask.service.organization;

import practicalTask.domain.Organization;
import practicalTask.utils.dto.OrganizationDto;

import java.util.List;

public interface OrganizationService {

    OrganizationDto getOrganization(Long id);

    List<OrganizationDto> getOrganizationList(String name, String inn, boolean isActive);

    void save(Organization organization);

    void update(Long orgId, Organization organization);

}
