package Debug.LSM.controller;

import Debug.LSM.DTO.PurchaseHistoryDTO;
import Debug.LSM.domain.Purchase_History;
import Debug.LSM.service.PayService;
import Debug.LSM.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/pay")
public class PayController {

    private final PayService payService;

    public PayController(PayService payService) {
        this.payService = payService;
    }


    //결제 정보 저장
    //POST
    @PostMapping("/saveClass")
//    상품이름:name
//    결제금액:amount
//    주문번호:merchant_uid
//    카드승인번호:apply_num
    public ResponseEntity saveClass(@RequestParam("email") String email,
                                    @RequestParam("name") String name,
                                    @RequestParam("amount") String amount,
                                    @RequestParam("merchant_uid") String merchant_uid,
                                    @RequestParam("apply_num") String apply_num) {

        User user = User.builder()._id(email).build();
        PurchaseHistoryDTO purchaseHistoryDTO = PurchaseHistoryDTO.builder().
                apply_num(apply_num).
                name(name).
                amount(amount).
                merchant_uid(merchant_uid).
                user(user).build();

        return payService.saveClass(purchaseHistoryDTO);
    }


    //구매내역 가져오기
    @GetMapping("/getPurchaseHistory")
    public ResponseEntity<List<Purchase_History>> getPurchaseHistory(@RequestParam("email") String email) {
        return payService.getPurchaseHistory(email);
    }
}
