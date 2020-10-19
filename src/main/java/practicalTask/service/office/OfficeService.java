package practicalTask.service.office;

import practicalTask.domain.Office;
import practicalTask.utils.dto.OfficeDto;

import java.util.List;

public interface OfficeService {

    OfficeDto getOffice(Long id);

    List<OfficeDto> getOfficeList(Long orgId, String name, String phone, boolean isActive);

    void save(Long orgId, Office office);

    void update(Long officeId, Office office);
}
