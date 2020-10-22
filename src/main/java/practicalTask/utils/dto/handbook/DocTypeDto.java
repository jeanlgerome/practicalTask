package practicalTask.utils.dto.handbook;

import practicalTask.model.DocType;

public class DocTypeDto {

    private String name;

    private Long code;

    public DocTypeDto(DocType docType) {
        this.name = docType.getDocName();
        this.code = docType.getDocCode();
    }

    public String getName() {
        return name;
    }

    public Long getCode() {
        return code;
    }
}
