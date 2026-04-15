package kr.co.side.healthcare.service.user;

import kr.co.side.healthcare.domain.user.LoginUserResVO;
import kr.co.side.healthcare.domain.user.RequestSignupVO;

import java.util.Map;

public interface UserService {
    LoginUserResVO findUser(String username);

    void increaseLoginFailCnt(LoginUserResVO user);

    void resetLoginFailCnt(LoginUserResVO user);

    Map<String, Object> insertSignupInfo(RequestSignupVO vo);

    boolean confirmUserByLoginId(String loginId);
}
