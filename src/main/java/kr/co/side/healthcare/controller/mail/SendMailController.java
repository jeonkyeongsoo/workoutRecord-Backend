package kr.co.side.healthcare.controller.mail;

import kr.co.side.healthcare.service.mail.SendMailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SendMailController {

    private final SendMailServiceImpl sendMailService;

    @PostMapping("/api/sendMail")
    public ResponseEntity sendMail(@RequestBody Map<String, String> request) {

        sendMailService.subConDecision(request.get("email"), request.get("type"), request.get("loginId"));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/verifyCode")
    public ResponseEntity verifyCode(@RequestBody Map<String, String> request) {

        sendMailService.verifyAuthCode(request.get("email"), request.get("authCode"));

        return ResponseEntity.ok().body("인증번호 검증 성공");
    }
}
