package practicalTask.service.organization;

import practicalTask.domain.Organization;

import java.util.List;

public interface OrganizationService {

    Organization getOrganization(Long id);

    List<Organization> getOrganizationList(String name, String inn, boolean isActive);

    void save(Organization organization);

    void update(Long orgId, Organization organization);

}
