package by.bestwork.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class Mail {

    private String from;
    private String to;
    private String subject;
    private String content;
    private Map<String, Object> model;

    @Override
    public String toString() {
        return "Mail{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
