package kr.co.side.healthcare.service.mail;

public interface SendMailService {

    void subConDecision(String email, String type, String loginId);

    void verifyAuthCode(String email, String authCode);
}
