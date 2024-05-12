package Debug.LSM;

import Debug.LSM.DTO.ViewersignupDTO;
import Debug.LSM.domain.Shorts;
import Debug.LSM.repository.mongoCBrepository.ShortsRepository;
import Debug.LSM.service.MyPageService;
import Debug.LSM.service.ViewerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import Debug.LSM.domain.User;

@SpringBootTest
class LsmApplicationTests {


    private final ViewerService viewerService;
    private final ShortsRepository shortsRepository;
    private final MyPageService myPageService;

    @Autowired
    public LsmApplicationTests(ViewerService viewerService, ShortsRepository shortsRepository,
                               MyPageService myPageService) {
        this.viewerService = viewerService;
        this.shortsRepository = shortsRepository;
        this.myPageService = myPageService;
    }


    @Test
    void contextLoads() {
    }

    @Test
    public void newViewertest() {
        for (int i = 0; i < 10; i++) {
            ViewersignupDTO testViewer = new ViewersignupDTO();
            testViewer.setId("testViewer" + i);
            testViewer.setPw("testPWD" + i);
            testViewer.setSex(true);
            testViewer.setName("testUser" + i);
            testViewer.setBirth("testBirth" + i);
            testViewer.setEmail("testEmail" + i + "@test.com");
            int[] a = {12, 3, 4, 5};
            testViewer.setCategory(a);
            viewerService.signup(testViewer);
        }
    }

    @Test
    public void savashortstest() {

        String test = "testurlz2z";
        User user = User.builder().email("qkrodyd306@gmail.com").build();
        myPageService.saveShorts(user, test);
    }

}
