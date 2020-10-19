package practicalTask.utils.dto;

import practicalTask.domain.Citizenship;
import practicalTask.utils.ArgChecker;

public class CitizenshipDto {

    private  String name;

    private  Long code;

    public CitizenshipDto(Citizenship citizenship) {
        this.name =  ArgChecker.requireNonBlank(citizenship.getCitizenshipName(),"CitizenshipName");
        this.code =  ArgChecker.requireNonNull(citizenship.getCitizenshipCode(), "CitizenshipCode") ;
    }

    public String getName() {
        return name;
    }

    public Long getCode() {
        return code;
    }

}
