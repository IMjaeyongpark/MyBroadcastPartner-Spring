package Debug.LSM;

import Debug.LSM.DTO.ViewersignupDTO;
import Debug.LSM.service.ViewerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LsmApplicationTests {



    private final ViewerService viewerService;

    @Autowired
    public LsmApplicationTests(ViewerService viewerService) {
        this.viewerService = viewerService;
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
            int[] a = {12,3,4,5};
            testViewer.setCategory(a);
            viewerService.signup(testViewer);
        }
    }



}
