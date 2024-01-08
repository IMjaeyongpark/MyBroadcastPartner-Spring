package Debug.LSM.repository;

import Debug.LSM.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findOneBy_id(String id);
}
