package by.bestwork.domain;

import by.bestwork.dto.DocsType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
public class Docs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private DocsType docsType;
    private Date date;
    private String status;
    private Long userId;
    private String adresat;
}
