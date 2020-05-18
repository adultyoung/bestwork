package by.bestwork.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
public class PersonInfo implements Serializable {

    private String name;
    private String tel;
    private String comment;
}
