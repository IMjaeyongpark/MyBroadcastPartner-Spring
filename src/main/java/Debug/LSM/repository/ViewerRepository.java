package Debug.LSM.repository;

import Debug.LSM.domain.User;
import Debug.LSM.domain.Viewer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ViewerRepository extends MongoRepository<Viewer, String> {
    Viewer findOneBy_id(String id);
}
