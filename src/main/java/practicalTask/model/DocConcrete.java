package practicalTask.model;


import org.hibernate.validator.constraints.Length;
import practicalTask.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Entity класс конкретного документа, содержит номер docNumber, который однозначно определяет документ,
 * docDate - дата выдачи документа, DocType - тип документа, DocType привязан к DocConcrete через лонг значение - код документа,
 * как OneToMany
 * Также сущность связана с User entity, как OneToOne CascadeType.ALL
 */
@Entity
@Table(name = "doc_concrete", indexes = {@Index(name = "IX_doc_concrete_docType", columnList = "doc_code")})
public class DocConcrete {


    @Id
    @Length(min = Constants.MIN_VARCHAR_LENGTH, max = Constants.MAX_VARCHAR_LENGTH)
    private String docNumber;

    @NotNull
    private LocalDate docDate;

    @ManyToOne
    @JoinColumn(name = "doc_code", nullable = false)
    private DocType docType;

    @MapsId("doc_number")
    @OneToOne(mappedBy = "docConcrete", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private User user;

    public DocConcrete() {
    }

    public DocConcrete(String docNumber, DocType docType, LocalDate docDate) {
        this.docNumber = docNumber;
        this.docType = docType;
        this.docDate = docDate;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    public LocalDate getDocDate() {
        return docDate;
    }

    public void setDocDate(LocalDate docDate) {
        this.docDate = docDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
