package Debug.LSM.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Setter;


@Entity
@Table (name = "viewer")
@Data
@Setter
public class Viewer {

    @Id
    private String id;
    private String pw;
    private String name;
    private String birth;
    private boolean sex;
    @Email
    @Column(name = "email")
    private String email;


}
