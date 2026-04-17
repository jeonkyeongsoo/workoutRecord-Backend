package kr.co.side.healthcare.domain.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendMailVO {

    private String to;
    private List<String> toList;
    private String subject;
    private String content;

    private String loginId;

    // 인증코드 비교 로직에서 사용할 seq 값
    private Long seq;

}
