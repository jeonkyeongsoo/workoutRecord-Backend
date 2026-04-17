package kr.co.side.healthcare.domain.mail.history;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendMailHistoryVO {

    private Long seq;
    private String loginId;
    private String email;
    private String type;
    private String authCode;
    private String createdAt;
    private String endAt;
    private String isVerified;
    private String mailSuccessYn;
    private LocalDateTime expiredAt;
}
