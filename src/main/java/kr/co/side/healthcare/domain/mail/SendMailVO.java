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

}
