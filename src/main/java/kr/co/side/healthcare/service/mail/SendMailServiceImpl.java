package kr.co.side.healthcare.service.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kr.co.side.healthcare.domain.mail.SendMailVO;
import kr.co.side.healthcare.domain.mail.history.SendMailHistoryVO;
import kr.co.side.healthcare.mapper.mail.sendHistory.SendHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSendException;
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

    private final SendHistoryMapper sendHistoryMapper;

    private final Map<String, String> authCodeMap = new HashMap<>();

    public void sendMail(SendMailVO sendMailVO){
        MimeMessage message = mailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(sendMailVO.getTo());
            helper.setSubject(sendMailVO.getSubject());
            helper.setText(sendMailVO.getContent(), true);
            mailSender.send(message);

            SendMailHistoryVO sendMailHistoryVO = SendMailHistoryVO.builder()
                    .mailSuccessYn("Y")
                    .loginId(sendMailVO.getLoginId())
                    .build();
            sendHistoryMapper.updateMailSuccessYn(sendMailHistoryVO);
        } catch (MessagingException e) {
            throw new MailSendException("이메일 전송 실패. 다시 시도해주세요.");
        } catch (Exception e) {
            System.out.println("실제예외: " + e.getClass().getName());
            throw new MailSendException("이메일 전송 실패. 다시 시도해주세요.");
        }
    }

    @Override
    public void subConDecision(String email, String type, String loginId) {

        SendMailVO sendMailVO = new SendMailVO();
        String code = generatedCode();
        authCodeMap.put(email, code);

        SendMailHistoryVO sendMailHistoryVO = SendMailHistoryVO.builder()
                .loginId(loginId)
                .email(email)
                .type(type)
                .authCode(code)
                .build();

        sendHistoryMapper.insertSendHistory(sendMailHistoryVO);

        switch (type) {
            case "resetPassword" ->
                    sendMailVO = SendMailVO.builder()
                            .to(email)
                            .loginId(loginId)
                            .subject("[WORKOUT RECORD] 비밀번호 찾기 인증번호")
                            .content("<h1>인증번호: " + code + "</h1>")
                            .build();
        }

        if(sendMailVO != null){
            sendMail(sendMailVO);
        }

    }

    @Override
    public void verifyAuthCode(String email, String authCode) {
        String savedCode = authCodeMap.get(email);

        try{
            if(savedCode.equals(authCode)){
                removeAuthCode(email);
            } else{

            }
        } catch (NullPointerException e){
            throw new NullPointerException("인증번호가 없습니다. 다시 시도해주세요.");
        }


    }

    public void removeAuthCode(String email) {
        authCodeMap.remove(email);
    }

    public String generatedCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }
}
