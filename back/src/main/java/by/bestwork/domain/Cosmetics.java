package by.bestwork.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Cosmetics {

    @Id
    private String code;
    private String name;
    private String price;
}
