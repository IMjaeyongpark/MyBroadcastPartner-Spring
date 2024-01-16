package Debug.LSM.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class UserServiceTest {

    private final UserService userService;

    UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    void find_User() {
        //given

        //when

        //then
    }
}