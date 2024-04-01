package Debug.LSM.DTO;

import Debug.LSM.domain.User;
import Debug.LSM.domain.Viewer;
import lombok.Builder;

@Builder
public class ViewrLoginResponseDTO {
    private String accessToken;
    private String refreshToken;
    private Viewer viwer;
}
