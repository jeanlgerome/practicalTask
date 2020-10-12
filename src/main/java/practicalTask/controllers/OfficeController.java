package practicalTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import practicalTask.service.office.OfficeService;

@RestController
public class OfficeController {

    @Autowired
    @Qualifier("officeServiceImpl")
    OfficeService OfficeService;
}
