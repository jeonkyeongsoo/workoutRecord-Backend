package kr.co.side.healthcare.service.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kr.co.side.healthcare.domain.mail.SendMailVO;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SendMailServiceImpl implements SendMailService {

    private final JavaMailSender mailSender;

    private final Map<String, String> authCodeMap = new HashMap<>();

    public void sendMail(SendMailVO sendMailVO){
        MimeMessage message = mailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(sendMailVO.getTo());
            helper.setSubject(sendMailVO.getSubject());
            helper.setText(sendMailVO.getContent(), true);
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void subConDecision(String email, String type) {

        SendMailVO sendMailVO = new SendMailVO();
        String code = generatedCode();
        authCodeMap.put(email, code);

        switch (type) {
            case "resetPassword" ->
                    sendMailVO = SendMailVO.builder()
                            .to(email)
                            .subject("[WORKOUT RECORD] 비밀번호 찾기 인증번호")
                            .content("<h1>인증번호: " + code + "</h1>")
                            .build();
        }

        if(sendMailVO != null){
            sendMail(sendMailVO);
        }

    }

    public boolean verifyAuthCode(String email, String inputCode) {
        String savedCode = authCodeMap.get(email);

        if(savedCode == null) {
            return false;
        }

        if(savedCode.equals(inputCode)){
            removeAuthCode(email);
            return true;
        }

        return false;
    }

    public void removeAuthCode(String email) {
        authCodeMap.remove(email);
    }

    public String generatedCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }
}
