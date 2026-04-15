package kr.co.side.healthcare.controller.user;

import kr.co.side.healthcare.domain.user.RequestSignupVO;
import kr.co.side.healthcare.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserServiceImpl userService;

    @PostMapping("/api/signup")
    public Map<String, Object> signup(@RequestBody RequestSignupVO vo) {
        return userService.insertSignupInfo(vo);
    }

    @PostMapping("/api/forgotPassword/confirmUserByLoginId")
    public ResponseEntity confirmUserByLoginId(@RequestBody Map<String, Object> req) {

        String loginId = (String) req.get("loginId");

        boolean isUser = userService.confirmUserByLoginId(loginId);

        if(isUser){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("가입된 아이디가 아닙니다. 다시 확인해 주세요.");
        }
    }

}
