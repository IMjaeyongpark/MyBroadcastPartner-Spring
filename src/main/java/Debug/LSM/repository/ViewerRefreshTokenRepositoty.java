package Debug.LSM.repository;

import Debug.LSM.domain.RefreshTokenEntity;
import Debug.LSM.domain.Viewer;
import Debug.LSM.domain.ViewerRefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewerRefreshTokenRepositoty extends JpaRepository<ViewerRefreshTokenEntity, String> {
    ViewerRefreshTokenEntity findOneByRefreshToken(String refreshToken);
}
