package kr.co.side.healthcare.service.user;

import kr.co.side.healthcare.domain.user.LoginUserResVO;
import kr.co.side.healthcare.domain.user.RequestSignupVO;
import kr.co.side.healthcare.domain.user.UserVO;
import kr.co.side.healthcare.repository.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.http.HttpResponse;
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
}
