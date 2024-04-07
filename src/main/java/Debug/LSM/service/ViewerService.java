package Debug.LSM.service;

import Debug.LSM.DTO.ViewerLoginResponseDTO;
import Debug.LSM.domain.RefreshTokenEntity;
import Debug.LSM.domain.Viewer;
import Debug.LSM.domain.ViewerRefreshTokenEntity;
import Debug.LSM.repository.ViewerRefreshTokenRepositoty;
import Debug.LSM.repository.ViewerRepository;
import Debug.LSM.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ViewerService {
    @Value("${jwt.secret}")
    private String secretKey;

    //30분
    private Long accessTokenExpiredMs = 1000 * 60 * 30L;

    //1일
    private Long refreshTokenExpiredMs = 1000 * 60 * 60 * 24L;

    private final ViewerRepository viewerRepository;
    private final ViewerRefreshTokenRepositoty viewerRefreshTokenRepositoty;

    @Autowired
    public ViewerService(ViewerRepository viewerRepository,
                         ViewerRefreshTokenRepositoty viewerRefreshTokenRepositoty) {
        this.viewerRepository = viewerRepository;
        this.viewerRefreshTokenRepositoty = viewerRefreshTokenRepositoty;

    }

    //시청자 회원가임
    public ResponseEntity newViewer(Viewer viewer) {
        try {
            viewerRepository.save(viewer);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //시청자 로그인
    public ResponseEntity<ViewerLoginResponseDTO> findViewer(String ID, String password) {

        try {

            Viewer viewer = viewerRepository.findById(ID).get();
            if (!viewer.getPw().equals(password)) {
                System.out.println("password no!");
                return ResponseEntity.badRequest().build();
            }
            //accessToken,refreshToken 생성
            String accessToken = JwtUtil.creatAccessToken(ID, secretKey, accessTokenExpiredMs);
            String refreshToken = JwtUtil.createRefreshToken(secretKey, refreshTokenExpiredMs);

            ViewerLoginResponseDTO viewerLoginResponseDTO = ViewerLoginResponseDTO.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .viwer(viewer).build();

            ViewerRefreshTokenEntity viewerRefreshToken = new ViewerRefreshTokenEntity();
            viewerRefreshToken.setId(ID);
            viewerRefreshToken.setRefreshToken(refreshToken);

            viewerRefreshTokenRepositoty.save(viewerRefreshToken);


            return ResponseEntity.ok(viewerLoginResponseDTO);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    public ResponseEntity idCheck(String ID) {
        Viewer tmp = new Viewer();
        tmp.setId(ID);
        Optional<Viewer> viewer = viewerRepository.findById(ID);
        if (!viewer.isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    public ResponseEntity changePW(String id, String pw) {
        Optional<Viewer> viewerOP = viewerRepository.findById(id);
        if(viewerOP.isPresent()){
            Viewer viewer = viewerOP.get();
            viewer.setPw(pw);
            viewerRepository.save(viewer);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}
