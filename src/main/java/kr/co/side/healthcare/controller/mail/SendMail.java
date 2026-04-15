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
public class SendMail {

    private final SendMailServiceImpl sendMailService;

    @PostMapping("/api/sendMail")
    public ResponseEntity sendMail(@RequestBody Map<String, String> request){

        sendMailService.subConDecision(request.get("email"), request.get("type"));

        return null;
    }
}
