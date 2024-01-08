package Debug.LSM.domain;

import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @Email
    //사용자 Email
    private String _id;

    private String Name;

    private String Picture;

    private String Channels_Id;
    private String class_name = "basic";
    private LocalDateTime date = null;
}
