package kr.co.side.healthcare.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestSignupVO {

    private String loginId;
    private String password;
    private String email;
    private String name;
    private String phone;
    private String birth;
}
