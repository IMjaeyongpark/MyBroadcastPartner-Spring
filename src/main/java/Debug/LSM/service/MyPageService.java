package Debug.LSM.service;

import Debug.LSM.DTO.MypageDTO;
import Debug.LSM.repository.BroadCastRepository;
import Debug.LSM.repository.YearRepositoy;
import Debug.LSM.domain.BroadCast;
import Debug.LSM.domain.User;
import Debug.LSM.domain.YearTotalData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class MyPageService {

    private final BroadCastRepository broadCastRepository;
    private final YearRepositoy yearRepositoy;

    public MyPageService(BroadCastRepository broadCastRepository, YearRepositoy yearRepositoy) {
        this.broadCastRepository = broadCastRepository;
        this.yearRepositoy = yearRepositoy;
    }

    //방송 데이터 가져오기
    public ResponseEntity<MypageDTO> mypageData(User user) {

        try {
            List<BroadCast> broadCasts = broadCastRepository.findByUser(user);
            List<YearTotalData> years = yearRepositoy.findByUser(user);
            Collections.sort(years, (o1, o2) ->
                    Integer.parseInt(o1.get_id().substring(0, 4)) - Integer.parseInt(o2.get_id().substring(0, 4)));
            MypageDTO mypageDTO = MypageDTO.builder().broadCasts(broadCasts).years(years).build();

            return ResponseEntity.ok(mypageDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
