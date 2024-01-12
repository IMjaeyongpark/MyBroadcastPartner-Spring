package Debug.LSM.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO {
    private JSONObject Viewer;
    private String published;

    @Builder.Default
    public List<Chat> chats = new ArrayList<Chat>();
}
