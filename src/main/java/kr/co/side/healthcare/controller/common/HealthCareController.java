package kr.co.side.healthcare.controller.common;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HealthCareController {

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/api/health")
    public Map<String, Object> health() {
        return Map.of(
                "status", "ok",
                "msg", "연결됨"
        );
    }

    /*@PostMapping("api/login")
    public Map<String, String> login(@RequestParam String loginId, @RequestParam String password) {

        String encodePwd = passwordEncoder.encode(password);
        System.out.println(encodePwd);

        return Map.of(
                    "res", "로그인 요청 잘들어옴"
        );
    }*/
}
