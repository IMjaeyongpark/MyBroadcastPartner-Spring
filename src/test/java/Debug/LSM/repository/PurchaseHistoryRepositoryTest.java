package Debug.LSM.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PurchaseHistoryRepositoryTest {

    @Autowired
    PurchaseHistoryRepository purchaseHistoryRepository;

    @Test
    void findByUser() {

    }
}