package com.boomboo.demo.java;

import com.boomboo.demo.dto.OkHttpResp;
import com.boomboo.demo.util.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class XiaoCaoRegisterTest {

    @Test
    public void testRegister() {
        Map<String, String> headers = new HashMap<>();
        headers.put("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        headers.put("upgrade-insecure-requests", "1");
        headers.put("cookie", "__cfduid=d0ba689ef5277cc396ae10015f5838faa1532448153; UM_distinctid=164cd0726fa8c0-0cf71867ec6bfb-163c6952-13c680-164cd0726fb48; PHPSESSID=scu520nuo8jltpu66hu8u78cn0; 227c9_lastvisit=0%091536082694%09%2Fread.php%3Ftid%3D3266399%26fpage%3D0%26toread%3D%26page%3D1; CNZZDATA950900=cnzz_eid%3D520782604-1532444311-https%253A%252F%252Fwww.xypcxk.com%252F%26ntime%3D1536078605");


        Map<String, String> body = new HashMap<>();
        body.put("reginvcode", "3acd49a0dd9aeed2");
        body.put("action", "reginvcodeck");

        String one = "3acd4";
        String two = "a0dd";
        String three = "aeed2";

        try {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    body.put("reginvcode", new StringBuilder().append(one).append(i).append(two).append(j).append(three).toString());
                    OkHttpResp resp = OkHttpUtil.post("https://cc.qn5.win/register.php", headers, body);
                    if (!StringUtils.isEmpty(resp.getBody())) {
                        log.info("resp:{}", resp);
                    }
                    Thread.sleep(500);
                }
            }
        } catch (Exception e) {
            log.error("", e);
        }

    }

    @Test
    public void testJdkHome() {
        //jdk home
        log.info(" home:{}", System.getProperty("java.home"));
        log.info(" home:{}", System.getProperties());
        try {
            throw new Exception("test");
            //catch two exception
        } catch (RuntimeException | AccessDeniedException arithmeticException) {

        } catch (Exception ex) {

        }

        //@Since jdk 1.7
        // each resource opened in try() is closed at the end of the statement.


    }
}
