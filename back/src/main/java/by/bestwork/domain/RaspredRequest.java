package by.bestwork.domain;

import by.bestwork.dto.CompanyType;
import by.bestwork.dto.StudentRequest;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class RaspredRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;
    private String fio;
    private String tel;
    private String email;
    private Date date;

    private CompanyType companyType;

    @Embedded
    private StudentRequest request;
}
