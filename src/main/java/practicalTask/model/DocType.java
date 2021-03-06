package practicalTask.model;

import org.hibernate.validator.constraints.Length;
import practicalTask.utils.Constants;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Entity класс типа документа, содержит название документа docName, который однозначно определяет документ,
 * <p>
 * Сущность связана с DocConcrete entity, как OneToMany CascadeType.ALL, fetch = FetchType.LAZY
 */
@Entity
@Table(name = "doc_type", indexes = {@Index(name = "IX_doc_type_docCode", columnList = "docCode")})
public class DocType {

    @Id
    private Long docCode;

    @NotNull
    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String docName;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "docType")
    private Set<DocConcrete> docConcreteSet;

    public DocType() {
    }

    public DocType(@NotNull Long docCode, @NotNull @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH) String docName) {
        this.docCode = docCode;
        this.docName = docName;
    }

    public Long getDocCode() {
        return docCode;
    }

    public void setDocCode(Long docCode) {
        this.docCode = docCode;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Set<DocConcrete> getDocConcreteSet() {
        return docConcreteSet;
    }

    public void setDocConcreteSet(Set<DocConcrete> docConcreteSet) {
        this.docConcreteSet = docConcreteSet;
    }
}
