package by.bestwork.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Embeddable
public class StudentRequest extends Request {

    private String yoName;
    private String rectorName;
    private String facultet;
    private String specialization;
    private String rukovoditelYO;
    private String yearOfGraduation;
    private String fioTvor;
    private String fioRod;
}
