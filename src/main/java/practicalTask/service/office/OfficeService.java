package practicalTask.service.office;

import practicalTask.domain.Office;

import java.util.List;

public interface OfficeService {

    Office getOffice(Long id);

    List<Office> getOfficeList(Long orgId, String name, String phone, boolean isActive);

    void save(Long orgId, Office office);

    void update(Long officeId, Office office);
}
