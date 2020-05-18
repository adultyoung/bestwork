package by.bestwork.domain;

import by.bestwork.dto.StudentRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class PractiseRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDateTime dateOfPractiseStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDateTime dateOfPractiseEnd;
    private String practiseTime;
    private Long userId;


    @Embedded
    private StudentRequest request;
}
