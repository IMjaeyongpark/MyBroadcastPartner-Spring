package Debug.LSM.controller;


import Debug.LSM.DTO.ChatDTO;
import Debug.LSM.DTO.FeedbackDTO;
import Debug.LSM.DTO.TopicDTO;
import Debug.LSM.DTO.ViewerDTO;
import Debug.LSM.service.BroadCastService;
import Debug.LSM.DTO.Chat;
import Debug.LSM.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/broadcast")
public class BroadCastController {
    @Autowired
    public BroadCastController(BroadCastService broadCastService) {
        this.broadCastService = broadCastService;
    }

    private final BroadCastService broadCastService;

    @GetMapping("/test")
    public ResponseEntity test() {


        return ResponseEntity.ok().build();
    }

    //방송정보 저장, 있으면 반환
    //계정 맞는지 확인하는 인증 추가 예정
    @GetMapping("/identification")
    public ResponseEntity<String> identification(Authentication authentication,
                                                 @RequestParam("URI") String URI) {

        User user = new User();
        user.set_id(authentication.getName());
        //URI에서 BCID추출
        String BCID = URI.replace("https://", "").replace("www.", "")
                .replace("youtube.com/watch?v=", "");

        return broadCastService.identification(user, BCID);
    }


    //채팅 저장 및 전달
    @PostMapping("/chat")
    public ResponseEntity saveChat(Authentication authentication,
                                   @RequestParam("BCID") String BCID,
                                   @RequestParam("name") String name,
                                   @RequestBody Chat chat) {
        ChatDTO chatDTO = ChatDTO.builder().user(User.builder()._id(authentication.getName()).build())
                .BCID(BCID).name(name).chat(chat).build();
        return broadCastService.saveChat(chatDTO);
    }

    //시청자수 저장
    @PostMapping("/saveViewer")
    public ResponseEntity saveViewer(@RequestParam("BCID") String BCID,
                                     @RequestParam("sec") String sec,
                                     @RequestParam("viewer") String viewer) {

        return broadCastService.saveViewer(ViewerDTO.builder().BCID(BCID).sec(sec)
                .viewer(Integer.parseInt(viewer)).build());
    }

    @GetMapping("/getChat")
    public ResponseEntity<FeedbackDTO> getChat(@RequestParam("BCID") String BCID) {
        return broadCastService.getChat(BCID);
    }

    @GetMapping("getTopic")
    public ResponseEntity<TopicDTO> getTopic(@RequestParam("BCID") String BCID) {
        return broadCastService.getTopic(BCID);
    }


}
