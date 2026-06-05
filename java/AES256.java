package bim.service.account.base.utilities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import bim.service.account.base.keyword.JWTKeys;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AES256 {

    private static final String AESKey="skfjlsejglekfesjrlskjerker3awrwr";
    public static String encrypt(String tenantId, String userId){
        String rs=null;
        try {
            String AESIv=AESIv(AESKey);

            String plaintext = String.join(":", tenantId, userId);
            
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // 키로 비밀키 생성
            SecretKeySpec keySpec = new SecretKeySpec(AESKey.getBytes(), "AES");

            // iv 로 spec 생성
            IvParameterSpec ivParamSpec = new IvParameterSpec(AESIv.getBytes());

            // 암호화 적용
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

            // 암호화 실행
            byte[] encrypted = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
            rs = Base64.getEncoder().encodeToString(encrypted);
        }

        catch (Exception e) {
            log.info(e);
        }

        return rs;
    }

    private static String AESKey(String userId) throws Exception{
        UUID uuid = UUID.nameUUIDFromBytes(userId.getBytes(StandardCharsets.UTF_8));
        String rs = uuid.toString().replace("-", "");
        return rs;
    }

    private static String AESIv(String aesKey) throws NoSuchAlgorithmException{
        byte[] shaBytes=MessageDigest.getInstance("SHA-256").digest(aesKey.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(String.format("%02x", shaBytes[i]));
        }
        return sb.toString();
    }

    public static Map<String,String> decrypt(String key) {
        Map<String, String> rs = null;
        try {
            String AESIv = AESIv(AESKey);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec keySpec = new SecretKeySpec(AESKey.getBytes(), "AES");

            IvParameterSpec ivParamSpec = new IvParameterSpec(AESIv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            byte[] decodedBytes = Base64.getDecoder().decode(key);
            byte[] decrypted = cipher.doFinal(decodedBytes);

            String plaintext = new String(decrypted, StandardCharsets.UTF_8);
            String[] plaintexts=plaintext.split(":");
            rs=Map.of(JWTKeys.TENANTID,plaintexts[0],JWTKeys.USERID,plaintexts[1]);
        }

        catch (Exception e) {
            log.info(e);
        }

        return rs;
    }
}
