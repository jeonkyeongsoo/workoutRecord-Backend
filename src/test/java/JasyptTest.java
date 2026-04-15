import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.NoIvGenerator;
import org.jasypt.iv.RandomIvGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest
public class JasyptTest {

    @Value("${jasypt.encryptor.password}")
    private String jasyptPassword;


    @Test
    void encryptTest() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("workoutrecord");  // 암호화 키
        encryptor.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        encryptor.setIvGenerator(new RandomIvGenerator());

        String encrypt = encryptor.encrypt("admin12!@");

        System.out.println("암호화 값: " + encrypt);
    }

    @Test
    void decryptTest() {
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword("workoutrecord");
        decryptor.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        decryptor.setIvGenerator(new RandomIvGenerator());

        String decrypt = decryptor.decrypt("uuvM3M4xSQZr4yDKTFF8xGqVeAipE/qlL8Unv9vwUzP21j1rxfDSdGKIQZaoOW0T7R4w6UHHaLvS30WWwaK8kOm1PxxRovUuojnST/zEvf5wBFLQ64yDFUZwRtgrWlAmAe5U9UlXoGWn3+b4hjqljg==");
        System.out.println("복호화 값: " + decrypt);
    }
}
