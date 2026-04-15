package kr.co.side.healthcare.repository.user;

import kr.co.side.healthcare.domain.user.LoginUserResVO;
import kr.co.side.healthcare.domain.user.UserVO;

import java.util.Map;

public interface UserRepo {
    LoginUserResVO findUser(String username);

    void increaseLoginFailCnt(LoginUserResVO user);

    void resetLoginFailCnt(LoginUserResVO user);

    int insertSignupInfo(UserVO userVO);

    boolean confirmUserByLoginId(String loginId);
}
