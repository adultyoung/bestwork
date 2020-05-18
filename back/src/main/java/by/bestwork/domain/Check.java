package by.bestwork.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "user_check")
@Entity
@Data
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String file;
    private Date date;
    private double sum;
    private long userId;
    private boolean uploaded;
    private boolean verified;
    private boolean isToDelete;
}
