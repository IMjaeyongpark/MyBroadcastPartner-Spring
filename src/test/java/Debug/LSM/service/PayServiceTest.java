package Debug.LSM.service;

import Debug.LSM.DTO.PurchaseHistoryDTO;
import Debug.LSM.domain.Purchase_History;
import Debug.LSM.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
class PayServiceTest {

    @Autowired
    PayService payService;

    @Test
    void savePurchaseHistory(){

        User user = User.builder()._id("testemail").build();
        PurchaseHistoryDTO purchaseHistoryDTO = PurchaseHistoryDTO.builder().
                apply_num("test_apply_num").
                name("test_name").
                amount("test_amount").
                merchant_uid("test_ merchant_uid").
                user(user).build();

        payService.saveClass(purchaseHistoryDTO);

    }

    @Test
    void getPurchaseHistory() {
        String email = "qkrodyd306@gmail.com";
        List<Purchase_History> l = payService.getPurchaseHistory(email).getBody();

        for(Purchase_History ph : l){
            System.out.println(ph.get_id());
        }

    }
}