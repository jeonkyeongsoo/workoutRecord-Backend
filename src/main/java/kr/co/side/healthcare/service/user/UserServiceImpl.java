package kr.co.side.healthcare.service.user;

import kr.co.side.healthcare.common.exception.CustomException;
import kr.co.side.healthcare.domain.user.LoginUserResVO;
import kr.co.side.healthcare.domain.user.RequestSignupVO;
import kr.co.side.healthcare.domain.user.UserVO;
import kr.co.side.healthcare.repository.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginUserResVO findUser(String username) {
        return userRepo.findUser(username);
    }

    @Override
    @Transactional
    public void increaseLoginFailCnt(LoginUserResVO user) {
        userRepo.increaseLoginFailCnt(user);
    }

    @Override
    @Transactional
    public void resetLoginFailCnt(LoginUserResVO user) {
        userRepo.resetLoginFailCnt(user);
    }

    @Override
    @Transactional
    public Map<String, Object> insertSignupInfo(RequestSignupVO vo) {

        UUID uuid = UUID.randomUUID();
        UserVO userVO = new UserVO();

        String password = vo.getPassword();
        String encodePwd = passwordEncoder.encode(password);

        if("".equals(vo.getPhone()) || vo.getPhone() == null){
            userVO = UserVO.builder()
                    .userId(uuid.toString().replace("-", ""))
                    .loginId(vo.getLoginId())
                    .password(encodePwd)
                    .name(vo.getName())
                    .birth(vo.getBirth())
                    .email(vo.getEmail())
                    .roleId(1)
                    .build();
        } else {
            userVO = UserVO.builder()
                    .userId(uuid.toString().replace("-", ""))
                    .loginId(vo.getLoginId())
                    .password(encodePwd)
                    .name(vo.getName())
                    .birth(vo.getBirth())
                    .phone(vo.getPhone())
                    .email(vo.getEmail())
                    .roleId(1)
                    .build();
        }
        Map<String, Object> map = new HashMap<>();


        int n = userRepo.insertSignupInfo(userVO);
        if(n > 0){
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }


        return map;
    }

    @Override
    public boolean confirmUserByLoginId(String loginId) {
        return userRepo.confirmUserByLoginId(loginId);
    }

    @Override
    public void resetPassword(UserVO userVO) {
        UserVO getPassword = userRepo.getPassword(userVO);

        boolean isPassword = passwordEncoder.matches(userVO.getPassword(), getPassword.getPassword());

        if(!isPassword){
            String encryptPassword = passwordEncoder.encode(userVO.getPassword());
            userVO = UserVO.builder()
                    .password(encryptPassword)
                    .loginId(userVO.getLoginId())
                    .build();

            try{
                userRepo.resetPassword(userVO);
            } catch (SqlSessionException e){
                throw new SqlSessionException("비밀번호 변경에 실패했습니다. 다시 시도해주세요.");
            }
        } else{
            throw new CustomException("기존 비밀번호와 일치합니다. 다시 시도해주세요.", 400);
        }

    }
}
