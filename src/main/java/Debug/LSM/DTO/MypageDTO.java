package Debug.LSM.DTO;

import Debug.LSM.domain.BroadCast;
import Debug.LSM.domain.YearTotalData;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class MypageDTO {

    private List<BroadCast> broadCasts = new ArrayList<BroadCast>();
    private List<YearTotalData> years = new ArrayList<YearTotalData>();
}
