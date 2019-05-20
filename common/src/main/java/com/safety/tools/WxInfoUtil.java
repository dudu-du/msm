package com.safety.tools;

import com.safety.exception.ProgramException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WxInfoUtil {

//    @Autowired
    private RestTemplate restTemplate;

    {
        restTemplate = new RestTemplate();
    }

    protected final String url = "http://api.tianapi.com/txapi/wxinfo/";

    protected final String key = "fa283b76c818584be6c992971dd12ece";

    public List getNewslist(String word, String biz) throws Exception{
        ResponseEntity<WxNewslist> forEntity = null;
        WxNewslist body = null;
        try {
            HttpHeaders requestHeaders = new HttpHeaders();
            HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
            forEntity = restTemplate
                    .exchange(url + "?key=" + key + "&word="+ word + "&biz="+ biz,
                            HttpMethod.POST, requestEntity,
                            WxNewslist.class);
            body = forEntity.getBody();
        } catch (RestClientException e) {
            log.error("公众号接口异常",e);
            throw new ProgramException("公众号接口异常");
        }
        if(body!=null && body.getCode().equals("200") && body.getMsg().equals("success")){
            return body.getNewslist();
        }
        return new ArrayList();
    }
}
