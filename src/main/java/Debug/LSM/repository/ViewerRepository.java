package Debug.LSM.repository;

import Debug.LSM.domain.Viewer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewerRepository extends JpaRepository<Viewer, String> {

}
