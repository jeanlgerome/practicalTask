package practicalTask.service.office;

import practicalTask.utils.dto.office.OfficeDto;
import practicalTask.utils.dto.office.OfficeFilterDto;
import practicalTask.utils.dto.office.OfficeListDto;

import java.util.List;

public interface OfficeService {

    OfficeDto getOffice(Long id);

    List<OfficeListDto> getOfficeList(OfficeFilterDto officeFilterDto);

    void save( OfficeDto office);

    void update( OfficeDto office);
}
