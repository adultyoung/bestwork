package by.bestwork.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;

@Data
@ToString
@EqualsAndHashCode
@Embeddable
public class Request {

    private RequestType requestType;

    private String lastName;
    private String firstName;
    private String otchestvo;
    private String email;
    private String tel;
    private String whoTellsThem;

}
