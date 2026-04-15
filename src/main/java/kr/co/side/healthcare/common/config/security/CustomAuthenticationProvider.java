package kr.co.side.healthcare.common.config.security;

import kr.co.side.healthcare.domain.CustomUserDetails;
import kr.co.side.healthcare.service.CustomUserDetailsService;
import kr.co.side.healthcare.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        CustomUserDetails user = (CustomUserDetails) customUserDetailsService.loadUserByUsername(username);

        if(!user.isEnabled()) {
            throw new BadCredentialsException("계정이 잠금상태입니다. 먼저 잠금을 풀어주세요.");
        }

        if("Y".equals(user.getUser().getLockedYn())){
            throw new BadCredentialsException("비밀번호 5회 이상 오류로 계정이 잠금되었습니다. 비밀번호를 변경해주세요.");
        } else {
            if(!passwordEncoder.matches(password, user.getPassword())){
                // 비밀번호 틀리면 횟수 증가 및 5회이상 틀렸을 시에 locked_yn 값 Y 로 변경
                userService.increaseLoginFailCnt(user.getUser());
                throw new BadCredentialsException("아이디 또는 비밀번호가 올바르지 않습니다.");
            } else{
                // 비밀번호가 맞으면 횟수 초기화
                userService.resetLoginFailCnt(user.getUser());
            }
        }

        if("Y".equals(user.getUser().getDeletedYn())){
            throw new BadCredentialsException("삭제신청된 계정입니다.");
        }

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
