package kr.co.side.healthcare.mapper.user;

import kr.co.side.healthcare.domain.user.LoginUserResVO;
import kr.co.side.healthcare.domain.user.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    LoginUserResVO findUser(String username);

    void increaseLoginFailCnt(LoginUserResVO user);

    void resetLoginFailCnt(LoginUserResVO user);

    int insertSignupInfo(UserVO userVO);

    boolean confirmUserByLoginId(String loginId);
}
