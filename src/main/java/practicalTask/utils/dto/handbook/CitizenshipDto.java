package practicalTask.utils.dto.handbook;

import practicalTask.model.Citizenship;

public class CitizenshipDto {

    private String name;

    private Long code;

    public CitizenshipDto(Citizenship citizenship) {
        this.name = citizenship.getCitizenshipName();
        this.code = citizenship.getCitizenshipCode();
    }

    public String getName() {
        return name;
    }

    public Long getCode() {
        return code;
    }

}
