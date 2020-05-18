package by.bestwork.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
public class SocialNetwork implements Serializable {

    private String typeOfSocial;
    private String url;
}
