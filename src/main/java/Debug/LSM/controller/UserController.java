package Debug.LSM.controller;

import Debug.LSM.DTO.LoginResponseDTO;
import Debug.LSM.DTO.RefreshTokenDTO;
import Debug.LSM.DTO.TokenDTO;
import Debug.LSM.DTO.ViewrLoginResponseDTO;
import Debug.LSM.domain.Viewer;
import Debug.LSM.service.UserService;
import Debug.LSM.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;


    @GetMapping("/test")
    public ResponseEntity<LoginResponseDTO> test() {
        return userService.test();
    }

    @GetMapping("/test2")
    public ResponseEntity<String> test2(Authentication authentication) {
        return ResponseEntity.ok(authentication.getName());
    }


    //사용자 정보 가져오기
    //없으면 회원가입
    @GetMapping("/login")
    public ResponseEntity<LoginResponseDTO> find_User(@RequestParam("id_token") String idToken,
                                                      @RequestParam("access_token") String access_token) {

        //jwt PAYLOAD부분 추출
        String payload = idToken.split("[.]")[1];

        return userService.find_User(payload, access_token);
    }



    @DeleteMapping("/logout")
    public ResponseEntity logout(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        return userService.logout(refreshTokenDTO);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<LoginResponseDTO> refreshToken(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        return userService.refreshToken(refreshTokenDTO);
    }

    @GetMapping("/google")
    public void google(@RequestParam("access_token") String access_token) {
        System.out.println(access_token);
        System.out.println(userService.google(access_token));
    }


}
