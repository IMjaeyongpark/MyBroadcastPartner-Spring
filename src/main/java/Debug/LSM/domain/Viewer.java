package Debug.LSM.domain;


import jakarta.validation.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Viewrs")
public class Viewer {

    @Id
    private String id;
    private String pw;
    private String name;
    private String birth;
    private boolean sex;
    @Email
    private String email;
    private Integer[] categorys;


}
