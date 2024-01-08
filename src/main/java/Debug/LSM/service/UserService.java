package Debug.LSM.service;

import Debug.LSM.repository.UserRepository;
import Debug.LSM.utils.JwtUtil;
import Debug.LSM.utils.YoutubeUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Debug.LSM.domain.User;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;

@Service
@Transactional
public class UserService {

    @Value("${jwt.secret}")
    private String secretKey;

    private Long expiredMs = 1000 * 60 * 60L;


    private final UserRepository user_repository;

    @Autowired
    public UserService(UserRepository user_repository) {
        this.user_repository = user_repository;
    }

    public ResponseEntity<String> test() {
        return ResponseEntity.ok(JwtUtil.creatJwt("test", secretKey, expiredMs));
    }

    //사용자 정보 가져오기
    public ResponseEntity<User> find_User(String token, String access_token) {
        String channels_Id = YoutubeUtil.getChannelId(access_token);
        //바디 디코딩 후 json형태로 변환
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String subject = new String(decoder.decode(token));
        JSONObject payload = new JSONObject(subject);
        //값 가져오기
        User user = User.builder().Channels_Id(channels_Id).Name(payload.getString("name"))
                ._id(payload.getString("email")).Picture(payload.getString("picture")).build();

        User u = user_repository.findOneBy_id(payload.getString("email"));
        if (u == null || u.getDate() == null || u.getDate().isBefore(LocalDateTime.now())) {
            user.setClass_name("베이직");
            user.setDate(null);
        } else {
            user.setClass_name(u.getClass_name());
            user.setDate(u.getDate());
        }
        user_repository.save(user);

        return ResponseEntity.ok(user);
    }

    public String google(String access_token) {
        String url = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + access_token;

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonString = response.body();
            return jsonString;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }

    }


}
