package Debug.LSM.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table (name = "viewer")
@Data
@Setter
public class Viewer {

    @Id
    private String id;
    private String pw;
    private String Name;
    private String birth;
    private boolean sex;
    @Email
    @Column(name = "email")
    private String _id;


}
