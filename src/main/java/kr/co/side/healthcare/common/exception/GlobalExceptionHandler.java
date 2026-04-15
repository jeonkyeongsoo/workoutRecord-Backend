package kr.co.side.healthcare.common.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<?> handleDuplicateKey(DuplicateKeyException e){
        Map<String, Object> result = new HashMap<>();
        result.put("result", "fail");

        String msg = e.getMessage();

        if(msg.contains("tb_user_unique_login_id")){
            result.put("msg", "아이디가 중복되었습니다.");
        } else if(msg.contains("tb_user_unique_email")){
            result.put("msg", "이메일이 중복되었습니다.");
        } else if(msg.contains("tb_user_unique_phone")){
            result.put("msg", "휴대폰 번호가 중복되었습니다.");
        } else {
            result.put("msg", "중복된 데이터가 존재합니다.");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @ExceptionHandler(MailSendException.class)
    public ResponseEntity<?> handleMailSendException(MailSendException e){
        return ResponseEntity.status(500).body(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException e){
        return ResponseEntity.status(500).body(e.getMessage());
    }



}
