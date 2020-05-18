package by.bestwork.domain;

import by.bestwork.dto.CompanyType;
import by.bestwork.dto.StudentRequest;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PereRaspredRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private CompanyType companyType;
    private String fioOfDirector;
    private Long userId;
    private String rykovoditelRaspred;
    private String baseOfRaspred;
    private String rykovoditelOfRaspred;
    private String job;


    @Embedded
    private StudentRequest request;
}
