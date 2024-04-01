package Debug.LSM.domain;


import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Viewrs")
@Getter
@Setter
public class Viewer {

    @Id
    private String _id;
    private String pw;
    private String name;
    private String birth;
    private boolean sex;
    @Email
    private String email;
    private Integer[] categorys;


}
