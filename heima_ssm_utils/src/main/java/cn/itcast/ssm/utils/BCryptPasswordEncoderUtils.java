package cn.itcast.ssm.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//加密工具:String-->new String
public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        System.out.println(encode("123"));//$2a$10$.36EBNSMhhfPMCk.xYZYd.2M/E2tEORoLllMsNvV/HfbTGZDgfJ6m
    }
}
