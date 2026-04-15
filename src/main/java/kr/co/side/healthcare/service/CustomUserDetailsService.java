package kr.co.side.healthcare.service;

import kr.co.side.healthcare.domain.CustomUserDetails;
import kr.co.side.healthcare.domain.user.LoginUserResVO;
import kr.co.side.healthcare.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUserResVO user = userService.findUser(username);

        if(user == null) {
            throw new BadCredentialsException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        return new CustomUserDetails(user);
    }
}
