package Debug.LSM.controller;

import Debug.LSM.DTO.ViewerLoginResponseDTO;
import Debug.LSM.domain.Viewer;
import Debug.LSM.service.UserService;
import Debug.LSM.service.ViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viewer")
@CrossOrigin("*")
public class ViewerController {
    @Autowired
    public ViewerController(ViewerService viewerService) {
        this.viewerService = viewerService;
    }

    private final ViewerService viewerService;

    @PostMapping("/new")
    public ResponseEntity newViewer(@RequestBody Viewer viewer) {
        System.out.println(viewer.getId());
        return viewerService.newViewer(viewer);
    }

    @GetMapping("/login")
    public ResponseEntity<ViewerLoginResponseDTO> findViewer(@RequestParam("ID") String ID,
                                                             @RequestParam("password") String password) {
        return viewerService.findViewer(ID, password);
    }

    @GetMapping("/idCheck")
    public ResponseEntity idCheck(@RequestParam("ID") String ID) {
        return viewerService.idCheck(ID);
    }

    @PostMapping("/changePW")
    public ResponseEntity changePW(Authentication authentication,
                                   @RequestParam("PW") String pw) {
        String id = authentication.getName();
        return viewerService.changePW(id,pw);
    }
}
