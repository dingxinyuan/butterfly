package com.ayding.butterfly;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * @Created with IntelliJ IDEA.
 * @author: dingxy
 * @date: 2020/09/16
 * @Description:
 */
public class AydingTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String ss = "1ySTdyV6o02AVWriKwQBh0WKLYoa/Kzp5djdeV6+fGzIIFVowov7tHeEVt4NNjfptvjHG3c3LFdRhcyDmWKEBw==";
        //MXlTVGR5VjZvMDJBVldyaUt3UUJoMFdLTFlvYS9LenA1ZGpkZVY2K2ZHeklJRlZvd292N3RIZUVWdDROTmpmcHZicEhLNXBscG1Zc3E2M1EyNVp6NVE9PQ==
        //MXlTVGR5VjZvMDJBVldyaUt3UUJoMFdLTFlvYS9LenA1ZGpkZVY2K2ZHeklJRlZvd292N3RIZUVWdDROTmpmcHZicEhLNXBscG1Zc3E2M1EyNVp6NVE9PQ==
        String encode = Base64.getEncoder().encodeToString("111111".getBytes());
        String base64Decode = new String(Base64.getDecoder().decode("MTExMTEx"));
        String encode1 = URLEncoder.encode(ss,"UTF-8");
        String decode = URLDecoder.decode(encode1, "UTF-8");
        System.err.println("======================BASE64encode:"+encode);
        System.err.println("======================BASE64decode:"+base64Decode);
        System.err.println("======================encode:"+encode1);
        System.err.println("======================decode:"+decode);
    }
}
