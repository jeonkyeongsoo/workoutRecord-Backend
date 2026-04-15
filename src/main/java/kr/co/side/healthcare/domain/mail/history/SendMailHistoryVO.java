package kr.co.side.healthcare.domain.mail.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendMailHistoryVO {

    private String loginId;
    private String email;
    private String type;
    private String authCode;
    private String createdAt;
    private String endAt;
    private String isVerified;
    private String mailSuccessYn;
}
