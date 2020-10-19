package practicalTask.utils.dto;

import practicalTask.domain.DocType;
import practicalTask.utils.ArgChecker;

public class DocTypeDto {

    private  String name;

    private  Long code;

    public DocTypeDto(DocType docType) {
        this.name = ArgChecker.requireNonBlank(docType.getDocName(), "DocName" );
        this.code = ArgChecker.requireNonNull(docType.getDocCode(), "DocCode)");
    }

    public String getName() {
        return name;
    }

    public Long getCode() {
        return code;
    }
}
