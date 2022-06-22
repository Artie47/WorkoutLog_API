package REST_API.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDTO {
    private String name;
    private String sportkind;
    private  String nextLesson;
    private String groupName;
    private String abonement_time;
    private String price;
}
