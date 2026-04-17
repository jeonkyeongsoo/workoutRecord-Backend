package kr.co.side.healthcare.repository.user;

import kr.co.side.healthcare.domain.user.LoginUserResVO;
import kr.co.side.healthcare.domain.user.UserVO;
import kr.co.side.healthcare.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class UserRepoImpl implements UserRepo{

    private final UserMapper userMapper;

    @Override
    public LoginUserResVO findUser(String username) {
        return userMapper.findUser(username);
    }

    @Override
    public void increaseLoginFailCnt(LoginUserResVO user) {
        userMapper.increaseLoginFailCnt(user);
    }

    @Override
    public void resetLoginFailCnt(LoginUserResVO user) {
        userMapper.resetLoginFailCnt(user);
    }

    @Override
    public int insertSignupInfo(UserVO userVO) {
        return userMapper.insertSignupInfo(userVO);
    }

    @Override
    public boolean confirmUserByLoginId(String loginId) {
        return userMapper.confirmUserByLoginId(loginId);
    }

    @Override
    public UserVO getPassword(UserVO userVO) {
        return userMapper.getPassword(userVO);
    }

    @Override
    public void resetPassword(UserVO userVO) {
        userMapper.resetPassword(userVO);
    }
}
