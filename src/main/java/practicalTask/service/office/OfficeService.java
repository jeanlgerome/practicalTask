package practicalTask.service.office;

import practicalTask.utils.dto.office.OfficeDto;
import practicalTask.utils.dto.office.OfficeListDto;

import java.util.List;

public interface OfficeService {

    OfficeDto getOffice(Long id);

    List<OfficeListDto> getOfficeList(Long orgId, String name, String phone, boolean isActive);

    void save(Long orgId, OfficeDto office);

    void update(Long officeId, OfficeDto office);
}
